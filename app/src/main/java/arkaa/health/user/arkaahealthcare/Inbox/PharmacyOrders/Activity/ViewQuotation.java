package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import arkaa.health.user.arkaahealthcare.Doctor.Activity.schedule;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Activity.ViewLabQuotation;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Adapter.ListOfQuatationMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.MedicineInfo;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.PharmacyQuatationGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationPost;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.RetrofitApi.PharmacyOrderApi;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.ListOfPharmacies;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyCart;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyCartBeta;
import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.ListOfPharmacyGetResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import arkaa.health.user.arkaahealthcare.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Adapter.ListOfQuatationMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.MedicineInfo;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.PharmacyQuatationGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.RetrofitApi.PharmacyOrderApi;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewQuotation extends AppCompatActivity implements PaymentResultListener {

    private TextView totalPriceTextView;
    private Button confirmBookingButton;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View confirmAppointmentCardView;
    private Button checkOutButton;

    private List<Datum> pharmacyQuatationDetails ;
    private List<MedicineInfo> listOfQuatationMedicinesArrayList ;
    String token;
    String patientUniqueId;
    Integer orderId;

    private String razorpayOrderId = "";

    private String razorpayAmount = "";

    private  String TAG ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quotation);

        Checkout.preload(getApplicationContext());
        TAG = getLocalClassName();


        checkOutButton = findViewById(R.id.check_out);
        totalPriceTextView = findViewById(R.id.total_price);
        confirmBookingButton = findViewById(R.id.check_out);
  
        orderId = getIntent().getIntExtra("orderId",1234);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniqueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");

        pharmacyQuatationDetails = new ArrayList<>();
        listOfQuatationMedicinesArrayList = new ArrayList<>();


        mRecyclerView = (RecyclerView) findViewById(R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListOfQuatationMedicinesAdapter(listOfQuatationMedicinesArrayList, ViewQuotation.this, "");
        mRecyclerView.setAdapter(mAdapter);

        getData();


        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // postData();
                if(!razorpayOrderId.equals("") && !razorpayAmount.equals("")) {
                    startPayment(razorpayAmount);
                }else {
                    showSuccessDialog(" Servers are down, please try again later.");

                }
            }
        });

    }

    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfDoctors)  GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/pharmacy/orders/quotation/"+patientUniqueId+"/"+orderId;

        Call<PharmacyQuatationGetResponse> postList = PharmacyOrderApi.getService().PharmacyQuatationApi(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<PharmacyQuatationGetResponse>() {
            @Override
            public void onResponse(Call<PharmacyQuatationGetResponse> call, Response<PharmacyQuatationGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                PharmacyQuatationGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfDoctors)profile success get =" + postList1.getSuccess());
                    pharmacyQuatationDetails = postList1.getData();

                    if(pharmacyQuatationDetails != null) {


                        totalPriceTextView.setText("RS "+pharmacyQuatationDetails.get(0).getQuoteCost().toString());
                        Log.v("quoteCost",pharmacyQuatationDetails.get(0).getQuoteCost().toString());

                        listOfQuatationMedicinesArrayList.addAll(pharmacyQuatationDetails.get(0).getMedicineInfo());
                        mAdapter.notifyItemInserted(listOfQuatationMedicinesArrayList.size() - 1);

                        //razorpayAmount = ""+ pharmacyQuatationDetails.get(0).getQuoteCost().toString()+"00";

                        Double TotalCost;
                        try{
                            TotalCost = pharmacyQuatationDetails.get(0).getQuoteCost()*100;
                        }catch (Exception e){
                            TotalCost = 0.0;
                        }

                        if(TotalCost == 0.0) {
                            razorpayAmount = "" ;
                        } else {
                            razorpayAmount = ""+TotalCost;
                        }



                        razorpayOrderId = postList1.getData().get(0).getOrderId();
                        if (razorpayOrderId == null) {
                            razorpayOrderId = "";
                        }


                        Log.v("pahrmacyCartRazorPay", "razorpayOrderId =" + razorpayOrderId);


                    }





                }






            }

            @Override
            public void onFailure(Call<PharmacyQuatationGetResponse> call, Throwable t) {

                Toast.makeText(ViewQuotation.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)error in link (get)");


            }
        });


        //post


    }

//    public void postData() {
//
//
//        String url = "https://arkaahealthapp.com/api/v1/pharmacy/user/"+patientUniqueId+"/orders";
////        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);
//        QuatationConfirmatationPost quatationConfirmatationPost = new QuatationConfirmatationPost();
//        quatationConfirmatationPost.setId(orderId);
//        quatationConfirmatationPost.setStatusId(1);
//
//        final Call<QuatationConfirmatationResponse> postList = PharmacyOrderApi.getService().QuatationConfirmationApi(token, url, quatationConfirmatationPost);
//
//        postList.enqueue(new Callback<QuatationConfirmatationResponse>() {
//            @Override
//            public void onResponse(Call<QuatationConfirmatationResponse> call, Response<QuatationConfirmatationResponse> response) {
//
//                final QuatationConfirmatationResponse postList1 = response.body();
//
//
//                if (postList1.getSuccess() == true) {
//
//                    Log.v("confirmBooking", "confirmBooking = " + postList1.getSuccess());
//                    showSuccessDialog();
//
//
//
//                } else {
//                    Toast.makeText(ViewQuotation.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();
//
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<QuatationConfirmatationResponse> call, Throwable t) {
//
//                Toast.makeText(ViewQuotation.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//
//
//    }

    public void showSuccessDialog(String message) {

//        new AwesomeSuccessDialog(this)
//                .setTitle(R.string.app_name)
//                .setMessage(R.string.app_name)
//                .setColoredCircle(R.color.dialogSuccessBackgroundColor)
//                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
//                .setCancelable(true)
//                .setPositiveButtonText(getString(R.string.dialog_yes_button))
//                .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
//                .setPositiveButtonTextColor(R.color.white)
//                .setNegativeButtonText(getString(R.string.dialog_no_button))
//                .setNegativeButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
//                .setNegativeButtonTextColor(R.color.white)
//                .setPositiveButtonClick(new Closure() {
//                    @Override
//                    public void exec() {
//                        //click
//                    }
//                })
//                .setNegativeButtonClick(new Closure() {
//                    @Override
//                    public void exec() {
//                        //click
//                    }
//                })
//                .show();


        new AwesomeSuccessDialog(this)
                .setTitle(message)
                .setMessage("")
                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_done_black_24dp, R.color.white)
                .setCancelable(true)
                .setPositiveButtonText("OK")
                .setPositiveButtonbackgroundColor(R.color.dialogErrorBackgroundColor)
                .setPositiveButtonTextColor(R.color.white)
                .setPositiveButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        //click
                        Intent i = new Intent(ViewQuotation.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public void startPayment(String amount) {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.arkaa_new_logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: Rentomojo || HasGeek etc.
             */
            options.put("name", "Arkaa");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Arkaa");

            options.put("currency", "INR");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            options.put("amount", razorpayAmount);
            options.put("order_id",razorpayOrderId);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("paymentGateway", "Error in starting Razorpay Checkout", e);
        }
    }

    public void updatePaymentStatus(String paymentKey) {


        //post



        String url = "https://arkaahealthapp.com/api/v1/pharmacy-order/update-payment/"+paymentKey;

        Call<UpdatePaymentPharmacy> postList = listOfPharmacyApi.getService().updatePaymentStatusApi(token, url);


        postList.enqueue(new Callback<UpdatePaymentPharmacy>() {
            @Override
            public void onResponse(Call<UpdatePaymentPharmacy> call, Response<UpdatePaymentPharmacy> response) {



                UpdatePaymentPharmacy postList1 = response.body();


                if (postList1 != null) {

                    if(postList1.getSuccess() == true){

                        showSuccessDialog(" Thank You,Payment successfull.");
                        Log.v("currentAppointment123","payment success");


                    }




                }

            }

            @Override
            public void onFailure(Call<UpdatePaymentPharmacy> call, Throwable t) {

                Toast.makeText(ViewQuotation.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


        //post


    }




    @Override
    public void onPaymentSuccess(String s) {

        Log.v("paymentGateway","onPaymentSuccess"+s);
        updatePaymentStatus(s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.v("paymentGateway","onPaymentError"+s);
        Log.v("razorpayErrorMessage",TAG+" ="+s);

        showSuccessDialog(" Payment unsuccessful card declined.");
    }
}
