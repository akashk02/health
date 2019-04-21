package arkaa.health.user.arkaahealthcare.Lab.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import arkaa.health.user.arkaahealthcare.Doctor.Activity.schedule;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.Datum;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.LabFullProfileGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabPrescription.LabPrescriptionApi;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabPrescription.LabPrescriptionPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabPrescription.LabPrescriptionResponse;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Doctor.Activity.schedule;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabPrescription.LabPrescriptionApi;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabPrescription.LabPrescriptionPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabPrescription.LabPrescriptionResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.Datum;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.LabFullProfileGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lab_details extends AppCompatActivity {

    private String token;
    private List<Datum> listOfLabs;
    private String labIdIntent;

    private TextView labNameTextView ;
    private TextView labAddressTextView ;
    private TextView labContactNumberTextview ;
    private TextView labIdTextView ;

    private Button bookNowButton;
    private Button sendPrescriptionButton;
    private Button startAddingYourTestsButton;
    private Button bookNowPrescriptionButton;

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




    private static final int PRESCRIPTION_RESULT = 2;

    private int prescriptionId ;
    private String labUniqueId;
    private String patientUniueId;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_2_beta);

        ButterKnife.bind(this);

        labIdIntent = getIntent().getStringExtra("LAB_ID");
        Log.v("lab_details","labId = "+labIdIntent);

        labNameTextView = findViewById(R.id.lab_name);
        labAddressTextView = findViewById(R.id.lab_address);
        labContactNumberTextview =findViewById(R.id.lab_contact_no);
        labIdTextView = findViewById(R.id.lab_id);
        bookNowPrescriptionButton = findViewById(R.id.book_now_upload_prescription);

        startAddingYourTestsButton = findViewById(R.id.start_adding_your_tests);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");


        listOfLabs = new ArrayList<>();





        bookNowButton = findViewById(R.id.book_now);
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lab_details.this,schedule.class);
                intent.putExtra("lab","LAB");
                intent.putExtra("BOOKING_TYPE","uploadPrescription");
                startActivity(intent);
            }
        });

        TextView testsTextView = findViewById(R.id.view_all_tests);
        testsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lab_details.this,viewAllTest.class);
                intent.putExtra("LAB_ID",labIdIntent);

                startActivity(intent);
            }
        });

        sendPrescriptionButton = findViewById(R.id.send_prescription);
        sendPrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(lab_details.this,Lab_Appointment_Booking.class);
                intent.putExtra("ACTIVITY_TYPE","LAB");
                intent.putExtra("BOOKING_TYPE","uploadPrescription");
                intent.putExtra("LAB_UNIQUE_ID",labUniqueId);
                startActivityForResult(intent,PRESCRIPTION_RESULT);

            }
        });

        startAddingYourTestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(lab_details.this,viewAllTest.class);
                intent.putExtra("LAB_ID",labIdIntent);

                startActivity(intent);
            }
        });

        bookNowPrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(lab_details.this,Lab_Appointment_Booking.class);
                intent.putExtra("ACTIVITY_TYPE","LAB");
                intent.putExtra("BOOKING_TYPE","uploadPrescription");
                intent.putExtra("LAB_UNIQUE_ID",labUniqueId);
                startActivityForResult(intent,PRESCRIPTION_RESULT);

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
                    sendPrescription((Integer.toString(prescriptionId)));

                }
            }


        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setQueryHint("Search for your tests");



        return true;
    }

    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfDoctors)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/lab/profile/"+labIdIntent;

        Call<LabFullProfileGetResponse> postList = LabApi.getService().ListOfLabs(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<LabFullProfileGetResponse>() {
            @Override
            public void onResponse(Call<LabFullProfileGetResponse> call, Response<LabFullProfileGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(lab_details)profile get = success");

                LabFullProfileGetResponse postList1 = response.body();
                Log.v("SignUp", "(lab_details)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(lab_details)profile success get =" + postList1.getSuccess());

                    listOfLabs.addAll(postList1.getData());
//                    listOfLabs = postList1.getData();

                    String labName = listOfLabs.get(0).getLabName();
                    String labAddress = listOfLabs.get(0).getAddress();
                    String labContactNo = listOfLabs.get(0).getContact();
                    labUniqueId = listOfLabs.get(0).getUniqueId();

                    labNameTextView.setText("" + labName);
                    labAddressTextView.setText("" + labAddress);
                    labContactNumberTextview.setText("" + labContactNo);
                    labIdTextView.setText("ID: " + labUniqueId);


                    try{
                    if(listOfLabs.get(0).getTimings() != null) {

                        String monday = "" + listOfLabs.get(0).getTimings().get(0).getDay() + "   " + listOfLabs.get(0).getTimings().get(0).getStartTime() + "-" + "" + listOfLabs.get(0).getTimings().get(0).getEndTime();
                        String tuesday = "" + listOfLabs.get(0).getTimings().get(1).getDay() + "   " + listOfLabs.get(0).getTimings().get(1).getStartTime() + "-" + "" + listOfLabs.get(0).getTimings().get(1).getEndTime();
                        String wednesday = "" + listOfLabs.get(0).getTimings().get(2).getDay() + "   " + listOfLabs.get(0).getTimings().get(2).getStartTime() + "-" + "" + listOfLabs.get(0).getTimings().get(2).getEndTime();
                        String thrusday = "" + listOfLabs.get(0).getTimings().get(3).getDay() + "   " + listOfLabs.get(0).getTimings().get(3).getStartTime() + "-" + "" + listOfLabs.get(0).getTimings().get(3).getEndTime();
                        String friday = "" + listOfLabs.get(0).getTimings().get(4).getDay() + "   " + listOfLabs.get(0).getTimings().get(4).getStartTime() + "-" + "" + listOfLabs.get(0).getTimings().get(4).getEndTime();
                        String saturday = "" + listOfLabs.get(0).getTimings().get(5).getDay() + "   " + listOfLabs.get(0).getTimings().get(5).getStartTime() + "-" + "" + listOfLabs.get(0).getTimings().get(5).getEndTime();

                        Log.v("labUniqueId", "labUniqueId " + labUniqueId);
                        Log.v("labUniqueId", "labName " + listOfLabs.get(0).getLabName());



                        mondayTextview.setText(monday);
                        tuesdayTextview.setText(tuesday);
                        wednesdayTextview.setText(wednesday);
                        thrusdayTextview.setText(thrusday);
                        fridayTextview.setText(friday);
                        saturdayTextview.setText(saturday);

                    }

                } catch (Exception e){
                        Toast.makeText(lab_details.this,"error in pharmacy timing",Toast.LENGTH_SHORT).show();
                        Log.v("labTiming","labTiming = "+e.getMessage());
                    }
                }


            }

            @Override
            public void onFailure(Call<LabFullProfileGetResponse> call, Throwable t) {

                Toast.makeText(lab_details.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(lab_details)error in link (get)");


            }
        });


        //post


    }

    public void sendPrescription(String prescriptionId){

        //post

        Log.v("schedulegetdata", "GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/labs/prescriptions";

        Call<LabPrescriptionResponse> postList = LabPrescriptionApi.getService().labPrescriptionShareApi(token, url,getLabPrescriptionPostObject(prescriptionId));

        Log.v("SignUp", "lab_details (post)token for header userProfile get" + token);

        postList.enqueue(new Callback<LabPrescriptionResponse>() {
            @Override
            public void onResponse(Call<LabPrescriptionResponse> call, Response<LabPrescriptionResponse> response) {



//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "lab_details (post)schedule = success");

                LabPrescriptionResponse postList1 = response.body();
                Log.v("SignUp", "lab_details (post)schedule = status code = " + response.code());

                if(postList1 != null){

                    Log.v("SignUp", "lab_details postlist1 success = " + postList1.getSuccess());



                }










            }

            @Override
            public void onFailure(Call<LabPrescriptionResponse> call, Throwable t) {

                Toast.makeText(lab_details.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post







    }

    public LabPrescriptionPost getLabPrescriptionPostObject(String prescriptionId){

        LabPrescriptionPost labPrescriptionPost = new LabPrescriptionPost();

        labPrescriptionPost.setPrescriptionId(Integer.parseInt(prescriptionId));
        labPrescriptionPost.setLabUid(labUniqueId);
        Log.v("labUniqueId","labUniqueId getobj"+labUniqueId);

        labPrescriptionPost.setUserUid(patientUniueId);

        labPrescriptionPost.setId("0");
        labPrescriptionPost.setEmail("email");
        labPrescriptionPost.setDescription("description");
        labPrescriptionPost.setMobile("mobile");
        labPrescriptionPost.setName("name");

        return labPrescriptionPost;






    }




}
