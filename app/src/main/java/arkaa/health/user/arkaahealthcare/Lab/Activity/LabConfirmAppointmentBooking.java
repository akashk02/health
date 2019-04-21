package arkaa.health.user.arkaahealthcare.Lab.Activity;

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
import arkaa.health.user.arkaahealthcare.Lab.Adapters.LabReviewTestAdapter;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.Firebase.LabAppointmentFirebase;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import arkaa.health.user.arkaahealthcare.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.List;

import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.Firebase.LabAppointmentFirebase;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabConfirmAppointmentBooking extends AppCompatActivity implements PaymentResultListener {


    private TextView selectedTestsTextView;
    private TextView totalPriceTextView;
    private Button confirmBookingButton;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View confirmAppointmentCardView;

    private LabBookAppointmentPost labBookAppointmentPost;

    private List<Datum> labAllTestsLocalStorage;
    private String token;

    //firebase

    private FirebaseDatabase mFirebaseDataBase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListner;

    private String labUniqueId;
    private String labPatientName;
    private String patientUniqueId;
    private LabAppointmentFirebase labAppointmentFirebase;
    //firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_confirm_appointment_booking);

        Checkout.preload(getApplicationContext());


        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("lab_appointments");
        labAppointmentFirebase = new LabAppointmentFirebase();

        selectedTestsTextView = findViewById(R.id.selected_tests);
        totalPriceTextView = findViewById(R.id.total_price);
        confirmBookingButton = findViewById(R.id.confirm_booking_button);

        labBookAppointmentPost = getIntent().getParcelableExtra("LAB_BOOK_APPOINTMENT_POST_OBJ");
        if (labBookAppointmentPost != null) {
            Log.v("parcelableConfirm", "parcelable = " + labBookAppointmentPost.getTests().get(0));

            labUniqueId = labBookAppointmentPost.getLabUid();
            labPatientName = labBookAppointmentPost.getName();
            patientUniqueId = labBookAppointmentPost.getUserUid();

        }

        labAllTestsLocalStorage = getIntent().getParcelableArrayListExtra("LAB_ALL_TESTS_LOCAL_STORAGE");
        if (labAllTestsLocalStorage != null) {
            Log.v("parcelablStorageConfirm", "parcelable = " + labAllTestsLocalStorage.size());

            selectedTestsTextView.setText("Selected Tests = " + labAllTestsLocalStorage.size());
            totalPriceTextView.setText("RS " + labBookAppointmentPost.getTotalCost());

        }

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");


        confirmAppointmentCardView = findViewById(R.id.confirm_booking_card_view);
//        confirmAppointmentCardView.setVisibility(View.GONE);

        mRecyclerView = (RecyclerView) findViewById(R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new LabReviewTestAdapter(labAllTestsLocalStorage);
        mRecyclerView.setAdapter(mAdapter);

        confirmBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  postData();
                startPayment();

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
                    showSuccessDialog();


                } else {
                    Toast.makeText(LabConfirmAppointmentBooking.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<LabBookAppointmentResponse> call, Throwable t) {

                Toast.makeText(LabConfirmAppointmentBooking.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void showSuccessDialog() {

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
                .setTitle(" Thank You,Your appointment has been booked.")
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
                        Intent i = new Intent(LabConfirmAppointmentBooking.this, FragmentActivity.class);
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

    public void startPayment() {
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
            options.put("name", "Arkaa Testing");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Arkaa description");

            options.put("currency", "INR");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            options.put("amount", "500");

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("paymentGateway", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Log.v("paymentGateway","onPaymentSuccess"+s);
          postData();


    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.v("paymentGateway","onPaymentError"+s);

    }
}
