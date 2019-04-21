package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Activity;

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
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Adapter.ViewQuotationAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation.LabQuotationConfirmationPost;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation.LabQuotationConfirmationResponse;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.RetrofitApi.LabAppointmentApi;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Test;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.ViewLabQuotationGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity.ViewQuotation;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationPost;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.RetrofitApi.PharmacyOrderApi;
import arkaa.health.user.arkaahealthcare.Lab.Activity.LabConfirmAppointmentBookingBeta;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;

import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import arkaa.health.user.arkaahealthcare.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Adapter.ViewQuotationAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.RetrofitApi.LabAppointmentApi;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Test;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.ViewLabQuotationGetResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewLabQuotation extends AppCompatActivity implements PaymentResultListener {


    private TextView selectedTestsTextView;
    private TextView totalPriceTextView;
    private Button confirmBookingButton;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View confirmAppointmentCardView;

    private LabBookAppointmentPost labBookAppointmentPost;

    private List<Datum> quotationDetails;
    private List<Test> quotationTestArrayList;
    private String token;
    private arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum listOfQuotationTest;
    private int orderId;
    private String labUniqueId;
    private String razorpayOrderId = "";
    private String razorpayAmount = "";


    //firebase


    //firebase

    private  String TAG ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_confirm_appointment_booking);

        Checkout.preload(getApplicationContext());
        TAG = getLocalClassName();



        selectedTestsTextView = findViewById(R.id.selected_tests);
        totalPriceTextView = findViewById(R.id.total_price);
        confirmBookingButton = findViewById(R.id.confirm_booking_button);

        selectedTestsTextView.setText("PAYMENT DETAILS");

        orderId = getIntent().getIntExtra("ORDER_ID",2);

        quotationTestArrayList = new ArrayList<>();


        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");


        confirmAppointmentCardView = findViewById(R.id.confirm_booking_card_view);
//        confirmAppointmentCardView.setVisibility(View.GONE);

        mRecyclerView = (RecyclerView) findViewById(R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ViewQuotationAdapter(quotationTestArrayList);
        mRecyclerView.setAdapter(mAdapter);

        confirmBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("confirmBookingButton","confirmBookingButton");
              //  postData();
                if(!razorpayOrderId.equals("") && !razorpayAmount.equals("")) {
                    startPayment(razorpayAmount);
                }else {
                    showSuccessDialog(" Servers are down, please try again later.");

                }
            }
        });

        getData();



    }

//    public void postData() {
//
//        Log.v("confirmBookingButton","postData");
//
//
//        String url = "https://arkaahealthapp.com/api/v1/labs/"+labUniqueId+"/appointments";
////        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);
//        LabQuotationConfirmationPost quatationConfirmatationPost = new LabQuotationConfirmationPost();
//        quatationConfirmatationPost.setId(orderId);
//        quatationConfirmatationPost.setStatusId(1);
//
//        final Call<LabQuotationConfirmationResponse> postList = LabAppointmentApi.getService().LabQuatationConfirmationApi(token, url, quatationConfirmatationPost);
//
//        postList.enqueue(new Callback<LabQuotationConfirmationResponse>() {
//            @Override
//            public void onResponse(Call<LabQuotationConfirmationResponse> call, Response<LabQuotationConfirmationResponse> response) {
//
//                final LabQuotationConfirmationResponse postList1 = response.body();
//
//
//                if (postList1.getSuccess() == true) {
//
//                    Log.v("confirmBooking", "confirmBooking = " + postList1.getSuccess());
//
//                    Log.v("confirmBookingButton","postList1.getSuccess()");
//                    showSuccessDialog();
//
//
//
//                } else {
//                    Toast.makeText(ViewLabQuotation.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();
//
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<LabQuotationConfirmationResponse> call, Throwable t) {
//
//                Toast.makeText(ViewLabQuotation.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//
//
//    }

    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfDoctors)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/labs/quotes/"+orderId;

        Log.v("labQuotationa", "lab quotation urk = " + url);




        Call<ViewLabQuotationGetResponse> postList = LabAppointmentApi.getService().viewLabQuotationApi(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<ViewLabQuotationGetResponse>() {
            @Override
            public void onResponse(Call<ViewLabQuotationGetResponse> call, Response<ViewLabQuotationGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                ViewLabQuotationGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfDoctors)profile success get =" + postList1.getSuccess());


                    quotationDetails = postList1.getData();

                    if(quotationDetails != null) {

                        totalPriceTextView.setText("RS " + quotationDetails.get(0).getQuoteCost());
                        quotationTestArrayList.addAll(quotationDetails.get(0).getTests());
                        mAdapter.notifyItemInserted(quotationTestArrayList.size() - 1);
                        labUniqueId = quotationDetails.get(0).getLabUid();

//                        razorpayAmount = ""+quotationDetails.get(0).getQuoteCost()+"00";
//
//                        razorpayOrderId = postList1.getData().get(0).getOrderId();
//                        if(razorpayOrderId == null){
//                            razorpayOrderId = "";
//                        }

                        Double TotalCost;
                        try{
                            TotalCost = quotationDetails.get(0).getQuoteCost()*100;
                        }catch (Exception e){
                            TotalCost = 0.0;
                        }

                        if(TotalCost == 0.0) {
                            razorpayAmount = "" ;
                        } else {
                            razorpayAmount = ""+TotalCost;
                        }

                    }


                }


            }

            @Override
            public void onFailure(Call<ViewLabQuotationGetResponse> call, Throwable t) {

                Toast.makeText(ViewLabQuotation.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)error in link (get)");


            }
        });


        //post


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
            options.put("amount", amount);
            options.put("order_id",razorpayOrderId);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("paymentGateway", "Error in starting Razorpay Checkout", e);
        }
    }

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
                        Intent i = new Intent(ViewLabQuotation.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public void updatePaymentStatus(String paymentKey) {


        //post



        String url = "https://arkaahealthapp.com/api/v1/lab-appointment/update-payment/"+paymentKey;

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

                Toast.makeText(ViewLabQuotation.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


        //post


    }



    @Override
    public void onPaymentSuccess(String s) {
        Log.v("paymentGatewaylab","lab quotation onPaymentSuccess"+s);
        updatePaymentStatus(s);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.v("paymentGateway","lab Quotation onPaymentError"+s);
        Log.v("razorpayErrorMessage",TAG+" ="+s);

        showSuccessDialog("Payment unsuccessfull card declined.");
    }
}
