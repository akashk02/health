package arkaa.health.user.arkaahealthcare.Pharmacy.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
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
import arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities.currentAppointment;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.DoctorAppointmentPayment.DoctorAppointmentPaymentResponse;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.RetrofitApi.CurrentAppointmentApi;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity.ListOfPharmacyOrders;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.ListOfMedicinesOrderGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.MedicineDetail;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.RetrofitApi.PharmacyOrderApi;
import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.MainActivity;
import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesCartAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus.ChangePharmacyPaymentStatusPost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus.ChangePharmacyPaymentStatusResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.Firebase.PharmacyOrderBookingFirebase;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyNewCart.PharmacyCartResponseNewTest;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import arkaa.health.user.arkaahealthcare.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesCartAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus.ChangePharmacyPaymentStatusPost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus.ChangePharmacyPaymentStatusResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.Firebase.PharmacyOrderBookingFirebase;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacyCartBeta extends AppCompatActivity implements PaymentResultListener {

    private TextView selectedTestsTextView;
    private TextView totalPriceTextView;
    private Button confirmBookingButton;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View confirmAppointmentCardView;

    private PharmacyOrderMedicinePost pharmacyOrderMedicinePost;
    private List<Datum> pharmacyAllMedicinesLocalStorage;

    //new
    private arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum pharmacyOrderDetail;
    private List<MedicineDetail> listOfOrderdMedicinesArrayList ;

    //new
    private String token;
    private String razorpayAmount = "";





    private String patientUniueId;
    private int orderId;
    private String razorpayOrderId;

    private Button checkOutButton;

    //firebase

    private FirebaseDatabase mFirebaseDataBase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListner;

    private String pharmacyUniqueId;
    private String pharmacyPatientName;
    private String pharmacyParcelUniqueId;
    private PharmacyOrderBookingFirebase pharmacyAppointmentFirebase;

    //firebase
    private  String TAG ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_cart_beta);

        Log.v("pahrmacyCartRazorPay","pharmacy cart beta");

        Checkout.preload(getApplicationContext());
        TAG = getLocalClassName();



        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");
        orderId = getIntent().getIntExtra("ORDER_ID",1234);

//firebase
        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("pharmacy_order");
        pharmacyAppointmentFirebase = new PharmacyOrderBookingFirebase();
//firebase

        checkOutButton = findViewById(R.id.check_out);
        selectedTestsTextView = findViewById(R.id.selected_tests);
        totalPriceTextView = findViewById(R.id.total_price);
        confirmBookingButton = findViewById(R.id.confirm_booking_button);

        listOfOrderdMedicinesArrayList = new ArrayList<>();


        confirmAppointmentCardView = findViewById(R.id.confirm_booking_card_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new listOfMedicinesCartAdapter(listOfOrderdMedicinesArrayList, PharmacyCartBeta.this, "cart");
        mRecyclerView.setAdapter(mAdapter);

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  postData();

                // showSuccessDialog();
                if(!razorpayOrderId.equals("") && !razorpayAmount.equals("")){
                startPayment(razorpayAmount);
                }else {
                    showSuccessDialog(" Servers are down, please try again later.");

                }

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();


            }
        }, 3000);

       // getData();


    }

    public void postData() {


        String url = "https://arkaahealthapp.com/api/v1/pharmacy/order";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        final Call<PharmacyOrderMedicineResponse> postList = listOfPharmacyApi.getService().PharmacyOrderMedicineApi(token, url, pharmacyOrderMedicinePost);

        postList.enqueue(new Callback<PharmacyOrderMedicineResponse>() {
            @Override
            public void onResponse(Call<PharmacyOrderMedicineResponse> call, Response<PharmacyOrderMedicineResponse> response) {

                final PharmacyOrderMedicineResponse postList1 = response.body();



                if (postList1.getSuccess() == true) {

                    Log.v("confirmBooking", "confirmBooking = " + postList1.getSuccess());
                    addDataToFireBase();
                    showSuccessDialog("");


                } else {
                    Toast.makeText(PharmacyCartBeta.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<PharmacyOrderMedicineResponse> call, Throwable t) {

                Toast.makeText(PharmacyCartBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void showSuccessDialog(String text) {

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
                .setTitle(text)
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
                        Intent i = new Intent(PharmacyCartBeta.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public void addDataToFireBase() {

        pharmacyAppointmentFirebase.setPatientName(pharmacyPatientName);
        pharmacyAppointmentFirebase.setPatientUniqueId(pharmacyParcelUniqueId);

        mMessagesDatabaseReference.child(pharmacyUniqueId).push().setValue(pharmacyAppointmentFirebase);


    }

    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfDoctors)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/pharmacy/orders/userview/"+patientUniueId+"/"+orderId;
//        String url = "http://13.127.59.252:3000/api/v1/pharmacy/orders/userview/"+patientUniueId+"/"+orderId;

        Log.v("pharmacycartbeta1","url = "+url);
        Log.v("apiUrl",getLocalClassName()+" url ="+url);


        final Call<ListOfMedicinesOrderGetResponse> postList = PharmacyOrderApi.getService().ListOfPharmacyOrderApi(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<ListOfMedicinesOrderGetResponse>() {
            @Override
            public void onResponse(Call<ListOfMedicinesOrderGetResponse> call, Response<ListOfMedicinesOrderGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                ListOfMedicinesOrderGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1.getData() != null) {

                        pharmacyOrderDetail = postList1.getData().get(0);
                        listOfOrderdMedicinesArrayList.addAll(pharmacyOrderDetail.getMedicineDetail());
                        mAdapter.notifyItemInserted(listOfOrderdMedicinesArrayList.size() - 1);


                        pharmacyUniqueId = pharmacyOrderDetail.getPharmacyUid();
                        pharmacyPatientName = pharmacyOrderDetail.getName();
                        pharmacyParcelUniqueId = pharmacyOrderDetail.getUserUid();

                        String totalCost = "RS " + pharmacyOrderDetail.getCost();

                    Log.v("totalCost",TAG+" String totalCost = "+pharmacyOrderDetail.getCost());
                    Log.v("totalCost",TAG+"  order_id = "+pharmacyOrderDetail.getOrderId());


                    Log.v("totalCost123",""+pharmacyOrderDetail.getCost());





                    Double TotalCost;
                    try{
                        TotalCost = pharmacyOrderDetail.getCost()*100;
                        Log.v("totalCost",TAG+" Total cost = "+TotalCost+"pharmacyOrderDetail.getCost() =  "+pharmacyOrderDetail.getCost());

                    }catch (Exception e){
                        Log.v("totalCost",TAG+" Total cost exception = "+e.getMessage());
                        TotalCost = 0.0;
                    }

                    if(TotalCost == 0.0) {
                        razorpayAmount = "" ;
                    } else {
                        razorpayAmount = ""+TotalCost;
                    }

                        String totalMedicines = "Selected Medicines = " + pharmacyOrderDetail.getMedicineDetail().size();

                        selectedTestsTextView.setText(totalMedicines);
                        totalPriceTextView.setText(totalCost);

                        razorpayOrderId = postList1.getData().get(0).getOrderId();
                        if (razorpayOrderId == null) {
                            razorpayOrderId = "";
                        }
                        Log.v("pahrmacyCartRazorPay", "razorpayOrderId =" + razorpayOrderId);


                        //   listOfOrderdMedicinesArrayList.addAll(listOfOrderdMedicinesArrayList);


                    }



            }

            @Override
            public void onFailure(Call<ListOfMedicinesOrderGetResponse> call, Throwable t) {

                Toast.makeText(PharmacyCartBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)error in link (get)");


            }
        });


        //post


    }

//    public void getData2() {
//
//
//        //post
//
//        Log.v("SignUp1", "(ListOfDoctors)GET DATA");
//
//
//        String url = "https://arkaahealthapp.com/api/v1/pharmacy/orders/userview/"+patientUniueId+"/"+orderId;
////        String url = "http://13.127.59.252:3000/api/v1/pharmacy/orders/userview/"+patientUniueId+"/"+orderId;
//
//        Log.v("pharmacycartbeta1","url = "+url);
//        Log.v("apiUrl",getLocalClassName()+" url ="+url);
//
//
//        final Call<PharmacyCartResponseNewTest> postList = PharmacyOrderApi.getService().ListOfPharmacyOrderApi(token, url);
//
//        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);
//
//        postList.enqueue(new Callback<PharmacyCartResponseNewTest>() {
//            @Override
//            public void onResponse(Call<PharmacyCartResponseNewTest> call, Response<PharmacyCartResponseNewTest> response) {
//
//
////                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
//                Log.v("SignUp", "(ListOfDoctors)profile get = success");
//
//                PharmacyCartResponseNewTest postList1 = response.body();
//                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());
//
//
//                if (postList1.getData() != null) {
//
//                    pharmacyOrderDetail = postList1.getData().get(0);
//                    listOfOrderdMedicinesArrayList.addAll(pharmacyOrderDetail.getMedicineDetail());
//                    mAdapter.notifyItemInserted(listOfOrderdMedicinesArrayList.size() - 1);
//
//
//                    pharmacyUniqueId = pharmacyOrderDetail.getPharmacyUid();
//                    pharmacyPatientName = pharmacyOrderDetail.getName();
//                    pharmacyParcelUniqueId = pharmacyOrderDetail.getUserUid();
//
//                    String totalCost = "RS " + pharmacyOrderDetail.getCost();
//
//                    Log.v("totalCost",TAG+" String totalCost = "+pharmacyOrderDetail.getCost());
//                    Log.v("totalCost",TAG+"  order_id = "+pharmacyOrderDetail.getOrderId());
//
//
//                    Log.v("totalCost123",""+pharmacyOrderDetail.getCost());
//
//
//
//
//
//                    Double TotalCost;
//                    try{
//                        TotalCost = pharmacyOrderDetail.getCost()*100;
//                        Log.v("totalCost",TAG+" Total cost = "+TotalCost+"pharmacyOrderDetail.getCost() =  "+pharmacyOrderDetail.getCost());
//
//                    }catch (Exception e){
//                        Log.v("totalCost",TAG+" Total cost exception = "+e.getMessage());
//                        TotalCost = 0.0;
//                    }
//
//                    if(TotalCost == 0.0) {
//                        razorpayAmount = "" ;
//                    } else {
//                        razorpayAmount = ""+TotalCost;
//                    }
//
//                    String totalMedicines = "Selected Medicines = " + pharmacyOrderDetail.getMedicineDetail().size();
//
//                    selectedTestsTextView.setText(totalMedicines);
//                    totalPriceTextView.setText(totalCost);
//
//                    razorpayOrderId = postList1.getData().get(0).getOrderId();
//                    if (razorpayOrderId == null) {
//                        razorpayOrderId = "";
//                    }
//                    Log.v("pahrmacyCartRazorPay", "razorpayOrderId =" + razorpayOrderId);
//
//
//                    //   listOfOrderdMedicinesArrayList.addAll(listOfOrderdMedicinesArrayList);
//
//
//                }
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<PharmacyCartResponseNewTest> call, Throwable t) {
//
//                Toast.makeText(PharmacyCartBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
//                Log.v("SignUp", "(ListOfDoctors)error in link (get)");
//
//
//            }
//        });
//
//
//        //post
//
//
//    }


    public void changePaymentStatus(int statusId){

        ChangePharmacyPaymentStatusPost pharmacyPaymentStatusPost = new ChangePharmacyPaymentStatusPost();
        pharmacyPaymentStatusPost.setId(orderId);
        pharmacyPaymentStatusPost.setStatusId(statusId);


        String url = "https://arkaahealthapp.com/api/v1/pharmacy/user/"+patientUniueId+"/orders";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        final Call<ChangePharmacyPaymentStatusResponse> postList = listOfPharmacyApi.getService().changePharmacyPaymentStatusApi(token, url, pharmacyPaymentStatusPost);

        postList.enqueue(new Callback<ChangePharmacyPaymentStatusResponse>() {
            @Override
            public void onResponse(Call<ChangePharmacyPaymentStatusResponse> call, Response<ChangePharmacyPaymentStatusResponse> response) {

                final ChangePharmacyPaymentStatusResponse postList1 = response.body();



                if (postList1.getSuccess() == true) {

                    showSuccessDialog(" Thank You,Your appointment has been booked.");



                } else {
                    Toast.makeText(PharmacyCartBeta.this, "Sorry,Something went wrong", Toast.LENGTH_SHORT).show();
                    showSuccessDialog(" Sorry,Something went wrong.");

                }


            }

            @Override
            public void onFailure(Call<ChangePharmacyPaymentStatusResponse> call, Throwable t) {

                Toast.makeText(PharmacyCartBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });

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
            options.put("amount", "500");
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

                Toast.makeText(PharmacyCartBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


        //post


    }







}
