package arkaa.health.user.arkaahealthcare.Lab.Activity;

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
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.ListOfMedicinesOrderGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.RetrofitApi.PharmacyOrderApi;
import arkaa.health.user.arkaahealthcare.Lab.Adapters.LabReviewTestAdapter;
import arkaa.health.user.arkaahealthcare.Lab.Adapters.LabReviewTestAdapterBeta;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.Firebase.LabAppointmentFirebase;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabCart.LabCartGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabCart.Test;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyCartBeta;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import arkaa.health.user.arkaahealthcare.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Lab.Adapters.LabReviewTestAdapterBeta;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.Firebase.LabAppointmentFirebase;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabCart.LabCartGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabCart.Test;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabConfirmAppointmentBookingBeta extends AppCompatActivity implements PaymentResultListener {

    private TextView selectedTestsTextView;
    private TextView totalPriceTextView;
    private Button confirmBookingButton;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View confirmAppointmentCardView;

    private LabBookAppointmentPost labBookAppointmentPost;

    private List<Datum> labAllTestsLocalStorage;
    private List<Test> labCartTests ;
    private String token;
    private  String TAG ;

    //firebase

    private FirebaseDatabase mFirebaseDataBase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListner;

    private String labUniqueId;
    private String labPatientName;
    private String patientUniqueId;
    private String razorpayOrderId;
    private String razorpayTotalAmount = "";


    private LabAppointmentFirebase labAppointmentFirebase;
    private int labAppointmentId;
    //firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_confirm_appointment_booking_beta);

        Checkout.preload(getApplicationContext());
        TAG = getLocalClassName();



        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("lab_appointments");
        labAppointmentFirebase = new LabAppointmentFirebase();

        selectedTestsTextView = findViewById(R.id.selected_tests);
        totalPriceTextView = findViewById(R.id.total_price);
        confirmBookingButton = findViewById(R.id.confirm_booking_button);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniqueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");
        labAppointmentId = getIntent().getIntExtra("LAB_APPOINTMENT_ID",00545);
        labCartTests = new ArrayList<>();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getCartData();

            }
        }, 3000);

     //   getCartData();



//        //network data
//        labBookAppointmentPost = getIntent().getParcelableExtra("LAB_BOOK_APPOINTMENT_POST_OBJ");
//        if (labBookAppointmentPost != null) {
//            Log.v("parcelableConfirm", "parcelable = " + labBookAppointmentPost.getTests().get(0));
//
//            labUniqueId = labBookAppointmentPost.getLabUid();
//            labPatientName = labBookAppointmentPost.getName();
//            patientUniqueId = labBookAppointmentPost.getUserUid();
//
//        }
//
//        labAllTestsLocalStorage = getIntent().getParcelableArrayListExtra("LAB_ALL_TESTS_LOCAL_STORAGE");
//        if (labAllTestsLocalStorage != null) {
//            Log.v("parcelablStorageConfirm", "parcelable = " + labAllTestsLocalStorage.size());
//
//            selectedTestsTextView.setText("Selected Tests = " + labAllTestsLocalStorage.size());
//            totalPriceTextView.setText("RS " + labBookAppointmentPost.getTotalCost());
//
//        }
//
//        //newtwork data





        confirmAppointmentCardView = findViewById(R.id.confirm_booking_card_view);
//        confirmAppointmentCardView.setVisibility(View.GONE);

        mRecyclerView = (RecyclerView) findViewById(R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new LabReviewTestAdapterBeta(labCartTests);
        mRecyclerView.setAdapter(mAdapter);

        confirmBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  postData();
                if(!razorpayOrderId.equals("") && !razorpayTotalAmount.equals("")) {
                    startPayment(razorpayTotalAmount);
                }else {
                    showSuccessDialog(" Servers are down, please try again later.");

                }

            }
        });

        //firebase

        mChildEventListner = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                Toast.makeText(schedule.this,"Book",Toast.LENGTH_SHORT).show();
                Log.v("dashboard","dashboard");



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mMessagesDatabaseReference.addChildEventListener(mChildEventListner);


        //firebase


    }


    public void postData() {


        String url = "https://arkaahealthapp.com/api/v1/labs/appointments";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        final Call<LabBookAppointmentResponse> postList = LabApi.getService().LabBooking(token, url, labBookAppointmentPost);

        postList.enqueue(new Callback<LabBookAppointmentResponse>() {
            @Override
            public void onResponse(Call<LabBookAppointmentResponse> call, Response<LabBookAppointmentResponse> response) {

                final LabBookAppointmentResponse postList1 = response.body();

                Log.v("objectTesting12", "name " + labBookAppointmentPost.getName());
                Log.v("objectTesting12", "address " + labBookAppointmentPost.getAddress());
                Log.v("objectTesting12", "total cost " + labBookAppointmentPost.getTotalCost());
                Log.v("objectTesting12", "email " + labBookAppointmentPost.getEmail());
                Log.v("objectTesting12", "pincode " + labBookAppointmentPost.getPincode());


                if (postList1.getSuccess() == true) {

                    Log.v("confirmBooking", "confirmBooking = " + postList1.getSuccess());

                    addDataToFireBase();
                    showSuccessDialog("");


                } else {
                    Toast.makeText(LabConfirmAppointmentBookingBeta.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<LabBookAppointmentResponse> call, Throwable t) {

                Toast.makeText(LabConfirmAppointmentBookingBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void getCartData() {


        //post

        Log.v("SignUp1", "(getCartData)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/labs/appointments/"+patientUniqueId+"/"+labAppointmentId;
        Log.v("pharmacycartbeta1","getCartData url = "+url);
        Log.v("apiUrl",getLocalClassName()+" url ="+url);


        final Call<LabCartGetResponse> postList = LabApi.getService().labCartApi(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<LabCartGetResponse>() {
            @Override
            public void onResponse(Call<LabCartGetResponse> call, Response<LabCartGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                LabCartGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1.getData() != null) {



                    labUniqueId = postList1.getData().get(0).getLabUid();
                    patientUniqueId = postList1.getData().get(0).getUserUid();
                    labPatientName = postList1.getData().get(0).getPatientDetails().get(0).getName();
                    Double totalCost;
                    try{
                        totalCost = postList1.getData().get(0).getTotalCost()*100;
                    }catch (Exception e){
                        totalCost = 0.0;
                    }

                    if(totalCost == 0.0) {
                        razorpayTotalAmount = "" ;
                    } else {
                        razorpayTotalAmount = ""+totalCost;
                    }

                    if(postList1.getData().get(0).getTests() != null) {
                        selectedTestsTextView.setText("Selected Tests = " + postList1.getData().get(0).getTests().size());
                        totalPriceTextView.setText("RS " + postList1.getData().get(0).getTotalCost());

                        labCartTests.addAll(postList1.getData().get(0).getTests());
                        mAdapter.notifyItemInserted(labCartTests.size() - 1);
                    }

                    razorpayOrderId = postList1.getData().get(0).getOrderId();
                    if(razorpayOrderId == null){
                        razorpayOrderId = "";
                    }



                }


            }

            @Override
            public void onFailure(Call<LabCartGetResponse> call, Throwable t) {

                Toast.makeText(LabConfirmAppointmentBookingBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("LabConfirmApBookingBeta", "error ="+t.getMessage());


            }
        });


        //post
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
                        Intent i = new Intent(LabConfirmAppointmentBookingBeta.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public void addDataToFireBase() {

        labAppointmentFirebase.setPatientName(labPatientName);
        labAppointmentFirebase.setPatientUniqueId(patientUniqueId);

        mMessagesDatabaseReference.child(labUniqueId).push().setValue(labAppointmentFirebase);


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

                Toast.makeText(LabConfirmAppointmentBookingBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


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


    @Override
    public void onPaymentSuccess(String s) {
        Log.v("paymentGatewaylab","onPaymentSuccess"+s);
       // postData();
        updatePaymentStatus(s);


    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.v("paymentGateway","onPaymentError"+s);
        Log.v("razorpayErrorMessage",TAG+" ="+s);
        showSuccessDialog("Payment unsuccessfull card declined.");
    }

}
