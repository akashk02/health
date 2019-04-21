package arkaa.health.user.arkaahealthcare.Pharmacy.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.ListOfPharmacyGetResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.ListOfPharmacyGetResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacyDetails extends AppCompatActivity {

    private String token;
    private List<Datum> listOfPharmacy;
    private String PharmacyIdIntent;

    private TextView viewAlltestsTexiView;
    private TextView PharmacyNameTextView ;
    private TextView PharmacyAddressTextView ;
    private TextView PharmacyContactNumberTextview ;
    private TextView PharmacyIdTextView ;

    private Button orderNowButton;
    private Button sendPrescriptionButton;
    private Button startAddingYourMedicinesButton;
    private Button bookNowButton;

    private static final int PRESCRIPTION_RESULT = 2;

    private int prescriptionId ;
    private String pharmacyUniqueId;
    private String patientUniueId;

    @BindView(R.id.monday)
    TextView mondayTextview;

    @BindView(R.id.tuesday)
    TextView tuesdayTextview;

    @BindView(R.id.wednesday)
    TextView wednesdayTextview;

    @BindView(R.id.thrusday)
    TextView thrusdayTextview;

    @BindView(R.id.friday)
    TextView fridayTextview;

    @BindView(R.id.saturday)
    TextView saturdayTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_details_beta);

        ButterKnife.bind(this);

        PharmacyIdIntent = getIntent().getStringExtra("PHARMACY_ID");
        Log.v("lab_details","labId = "+PharmacyIdIntent);

        PharmacyNameTextView = findViewById(R.id.pharmacy_name);
        PharmacyAddressTextView = findViewById(R.id.pharmacy_address);
        PharmacyContactNumberTextview =findViewById(R.id.pharmacy_contact_no);
        PharmacyIdTextView = findViewById(R.id.Pharmacy_id);
        viewAlltestsTexiView = findViewById(R.id.view_all_tests);
        startAddingYourMedicinesButton = findViewById(R.id.start_adding_your_medicines);
        bookNowButton = findViewById(R.id.order_now_upload_prescription);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");


        listOfPharmacy = new ArrayList<>();





        orderNowButton = findViewById(R.id.order_now);
        orderNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(PharmacyDetails.this,schedule.class);
//                intent.putExtra("lab","LAB");
//                startActivity(intent);
            }
        });

        TextView testsTextView = findViewById(R.id.view_all_tests);
        testsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PharmacyDetails.this,viewAllText.class);
//                intent.putExtra("LAB_ID",PharmacyIdIntent);
//
//                startActivity(intent);
            }
        });


        // del later
        sendPrescriptionButton = findViewById(R.id.send_prescription);
        sendPrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Intent intent = new Intent(PharmacyDetails.this,PharmacyOrderDetailsBeta.class);
                intent.putExtra("ACTIVITY_TYPE","PHARMACY");
                intent.putExtra("BOOKING_TYPE","uploadPrescription");
                intent.putExtra("PHARMACY_UNIQUE_ID",pharmacyUniqueId);
                startActivityForResult(intent,PRESCRIPTION_RESULT);

            }
        });

        // del later

        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Intent intent = new Intent(PharmacyDetails.this,PharmacyOrderDetailsBeta.class);
                intent.putExtra("ACTIVITY_TYPE","PHARMACY");
                intent.putExtra("BOOKING_TYPE","uploadPrescription");
                intent.putExtra("PHARMACY_UNIQUE_ID",pharmacyUniqueId);
                startActivityForResult(intent,PRESCRIPTION_RESULT);

            }
        });
        
        


        viewAlltestsTexiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PharmacyDetails.this,view_all_medicines.class);
                intent.putExtra("PHARMACY_UNIQUE_ID",pharmacyUniqueId);
                startActivity(intent);
            }
        });

        startAddingYourMedicinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PharmacyDetails.this,view_all_medicines.class);
                intent.putExtra("PHARMACY_UNIQUE_ID",pharmacyUniqueId);
                startActivity(intent);
            }
        });



        getData();









    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PRESCRIPTION_RESULT){
            if(resultCode == Activity.RESULT_OK){
                prescriptionId = data.getIntExtra("PRESCRIPTION_ID",555);
                Log.v("prescriptionId","prescriptionId"+prescriptionId);
                if(patientUniueId != null) {
                     // call send prescription api for pharmacy
                }
            }


        }


    }

    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfDoctors)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/pharmacy/profile/"+PharmacyIdIntent;

        Call<ListOfPharmacyGetResponse> postList = listOfPharmacyApi.getService().listOfPharmacyApi(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<ListOfPharmacyGetResponse>() {
            @Override
            public void onResponse(Call<ListOfPharmacyGetResponse> call, Response<ListOfPharmacyGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(lab_details)profile get = success");

                ListOfPharmacyGetResponse postList1 = response.body();
                Log.v("SignUp", "(lab_details)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(lab_details)profile success get =" + postList1.getSuccess());

                    listOfPharmacy.addAll(postList1.getData());
//                    listOfLabs = postList1.getData();

                    String pharmacyName = listOfPharmacy.get(0).getPharmacyName();
                    String pharmacyAddress = listOfPharmacy.get(0).getAddress();
                    String pharmacyContactNo = listOfPharmacy.get(0).getContact();
                    pharmacyUniqueId = listOfPharmacy.get(0).getUniqueId();

                    Log.v("labUniqueId","labUniqueId " +pharmacyUniqueId);
                    Log.v("labUniqueId","labName "+listOfPharmacy.get(0).getPharmacyName());


                    PharmacyNameTextView.setText(""+pharmacyName);
                    PharmacyAddressTextView.setText(""+pharmacyAddress);
                    PharmacyContactNumberTextview.setText(""+pharmacyContactNo);
                    PharmacyIdTextView.setText("ID: "+pharmacyUniqueId);

                    try {
                        if (listOfPharmacy.get(0).getTimings() != null) {

                            String monday = "" + listOfPharmacy.get(0).getTimings().get(0).getDay() + "   " + listOfPharmacy.get(0).getTimings().get(0).getStartTime() + "-" + "" + listOfPharmacy.get(0).getTimings().get(0).getEndTime();
                            String tuesday = "" + listOfPharmacy.get(0).getTimings().get(1).getDay() + "   " + listOfPharmacy.get(0).getTimings().get(1).getStartTime() + "-" + "" + listOfPharmacy.get(0).getTimings().get(1).getEndTime();
                            String wednesday = "" + listOfPharmacy.get(0).getTimings().get(2).getDay() + "   " + listOfPharmacy.get(0).getTimings().get(2).getStartTime() + "-" + "" + listOfPharmacy.get(0).getTimings().get(2).getEndTime();
                            String thrusday = "" + listOfPharmacy.get(0).getTimings().get(3).getDay() + "   " + listOfPharmacy.get(0).getTimings().get(3).getStartTime() + "-" + "" + listOfPharmacy.get(0).getTimings().get(3).getEndTime();
                            String friday = "" + listOfPharmacy.get(0).getTimings().get(4).getDay() + "   " + listOfPharmacy.get(0).getTimings().get(4).getStartTime() + "-" + "" + listOfPharmacy.get(0).getTimings().get(4).getEndTime();
                            String saturday = "" + listOfPharmacy.get(0).getTimings().get(5).getDay() + "   " + listOfPharmacy.get(0).getTimings().get(5).getStartTime() + "-" + "" + listOfPharmacy.get(0).getTimings().get(5).getEndTime();


                            mondayTextview.setText(monday);
                            tuesdayTextview.setText(tuesday);
                            wednesdayTextview.setText(wednesday);
                            thrusdayTextview.setText(thrusday);
                            fridayTextview.setText(friday);
                            saturdayTextview.setText(saturday);

                        }

                    } catch (Exception e){
                        Toast.makeText(PharmacyDetails.this,"error in pharmacy timing",Toast.LENGTH_SHORT).show();
                        Log.v("pharmacyTiming","pharmacyTiming = "+e.getMessage());
                    }





                }


            }

            @Override
            public void onFailure(Call<ListOfPharmacyGetResponse> call, Throwable t) {

                Toast.makeText(PharmacyDetails.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(lab_details)error in link (get)");


            }
        });


        //post


    }









}
