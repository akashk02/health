package arkaa.health.user.arkaahealthcare.Pharmacy.Activity;

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
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.Firebase.LabAppointmentFirebase;
import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.PharmacyReviewMedicineAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.Firebase.PharmacyOrderBookingFirebase;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import arkaa.health.user.arkaahealthcare.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.Firebase.PharmacyOrderBookingFirebase;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacyCart extends AppCompatActivity {

    private TextView selectedTestsTextView;
    private TextView totalPriceTextView;
    private Button confirmBookingButton;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View confirmAppointmentCardView;

    private PharmacyOrderMedicinePost pharmacyOrderMedicinePost;
    private List<Datum> pharmacyAllMedicinesLocalStorage;
    private String token;

    private String pharmacyUniqueIdIntent;

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
    private final String TAG = getLocalClassName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_cart);

//firebase
        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("pharmacy_order");
        pharmacyAppointmentFirebase = new PharmacyOrderBookingFirebase();
//firebase

        checkOutButton = findViewById(R.id.check_out);
        selectedTestsTextView = findViewById(R.id.selected_tests);
        totalPriceTextView = findViewById(R.id.total_price);
        confirmBookingButton = findViewById(R.id.confirm_booking_button);

        pharmacyUniqueIdIntent = getIntent().getStringExtra("PHARMACY_UNIQUE_ID");

        pharmacyOrderMedicinePost = getIntent().getParcelableExtra("PHARMACY_ORDER_MEDICINE_POST");
        if (pharmacyOrderMedicinePost != null) {
            Log.v("parcelablepharmacy", "parcelable = " + pharmacyOrderMedicinePost.getMedicines().get(0));

            pharmacyUniqueId = pharmacyOrderMedicinePost.getPharmacyUid();
            pharmacyPatientName = pharmacyOrderMedicinePost.getName();
            pharmacyParcelUniqueId = pharmacyOrderMedicinePost.getUserUid();


        }

        pharmacyAllMedicinesLocalStorage = getIntent().getParcelableArrayListExtra("LIST_OF_MEDICINES_LOCAL_STORAGE");
        if (pharmacyAllMedicinesLocalStorage != null) {
            Log.v("parcelablepharmacy", "parcelable  local medicine= " + pharmacyAllMedicinesLocalStorage.size());

            selectedTestsTextView.setText("Selected Medicines = " + pharmacyAllMedicinesLocalStorage.size());
            totalPriceTextView.setText("RS " + pharmacyOrderMedicinePost.getCost());

        }

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");

        confirmAppointmentCardView = findViewById(R.id.confirm_booking_card_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new listOfMedicinesAdapter(pharmacyAllMedicinesLocalStorage, PharmacyCart.this, "cart");
        mRecyclerView.setAdapter(mAdapter);

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();

            }
        });


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
                    showSuccessDialog();


                } else {
                    Toast.makeText(PharmacyCart.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<PharmacyOrderMedicineResponse> call, Throwable t) {

                Toast.makeText(PharmacyCart.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


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
                .setTitle(" Thank You,Your Order has been placed.")
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
                        Intent i = new Intent(PharmacyCart.this, FragmentActivity.class);
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

    public void getData(){

    }

}
