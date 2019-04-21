package arkaa.health.user.arkaahealthcare.Doctor.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicDetail;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorDetailsResponse;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorTiming;
import arkaa.health.user.arkaahealthcare.Doctor.RetrofitApi.DoctorListApi;
import arkaa.health.user.arkaahealthcare.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsDetail extends AppCompatActivity {

    private String token;


    private TextView clinicNameTextView ;
    private TextView clinicAddress1TextView ;
    private TextView clinicAddress2TextView ;

    private TextView clinicTimingMondayTextView ;
    private TextView clinicTimingTuesdayTextView ;
    private TextView clinicTimingWednesdayTextView ;
    private TextView clinicTimingThrusdayTextView ;
    private TextView clinicTimingFridayTextView ;
    private TextView clinicTimingSaturdayTextView ;
    private TextView clinicTimingSundayTextView ;
    private Button callClinicButton ;


    private TextView consultationFeesTextView;


    private int officeId  ;

    private int index;
    private String doctorUniqueId ;
    private int specializationId;
    private String doctorUniqueIdIntent ;

    @BindView(R.id.phone_consult_tv)
    TextView phoneConsultTextView;

    @BindView(R.id.video_consult_tv)
    TextView videoConsultTextView;

    @BindView(R.id.text_consult_tv)
    TextView textConsultTextView;

    @BindView(R.id.eduLine1)
    TextView educationLine1Tv;

    @BindView(R.id.eduLine2)
    TextView educationLine2Tv;

    @BindView(R.id.view_all_clinics)
    TextView viewAllClinicsTv;

    private String TAG ;

    //consultation


    @BindView(R.id.video_consult)
    CardView VideoConsultCardView;

    @BindView(R.id.text_consult)
    CardView texConsultCardView;

    @BindView(R.id.phone_consult)
    CardView phoneConsultCardView;

    @BindView(R.id.book_now)
    Button BookClinicAppButton;


    //consultation

    //NEW CHANGES
    private int clinicIndexIntent ;
    //NEW CHANGES

    //doctor timings parcle
    List<DoctorTiming> doctorTimingsParcle ;
    //doctor timings parcle

    private String startTimeEndTimeIntent ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_detail);

        ButterKnife.bind(this);

        TAG = getLocalClassName();
        index = getIntent().getIntExtra("INDEX",0);
        Log.v("index","index = "+index);
        specializationId = getIntent().getIntExtra("SPECIALIZATION_ID",1);
        doctorUniqueIdIntent = getIntent().getStringExtra("DOCTOR_UNIQUE_ID");
        startTimeEndTimeIntent = getIntent().getStringExtra("START_TIME_END_TIME");

        //NEW CHANGES
        clinicIndexIntent = getIntent().getIntExtra("CLINIC_INDEX",12122);
        //NEW CHANGES

        Log.v("viewAllClinicData", getLocalClassName() + " index = " + index + " specializationId" + specializationId + " doctorUniqueIdIntent " + doctorUniqueIdIntent);


        callClinicButton = findViewById(R.id.call_button);
        clinicNameTextView = findViewById(R.id.clinic_name);
        clinicAddress1TextView = findViewById(R.id.clinic_address_1);
        clinicAddress2TextView = findViewById(R.id.clinic_address_2);

        clinicTimingMondayTextView = findViewById(R.id.clinic_timing_monday);
        clinicTimingTuesdayTextView = findViewById(R.id.clinic_timing_tuesday);
        clinicTimingWednesdayTextView = findViewById(R.id.clinic_timing_wednesday);
        clinicTimingThrusdayTextView = findViewById(R.id.clinic_timing_thrusday);
        clinicTimingFridayTextView = findViewById(R.id.clinic_timing_friday);
        clinicTimingSaturdayTextView = findViewById(R.id.clinic_timing_saturday);
        clinicTimingSundayTextView = findViewById(R.id.clinic_timing_sunday);
        consultationFeesTextView = findViewById(R.id.clinic_consultation_fees);


        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");

        doctorTimingsParcle = new ArrayList<>();


        getData();

        }



    public void getData(){


        //post

        Log.v("SignUp1", "(DoctorsDetails)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/doctors/specialities/"+specializationId;

        Call<DoctorDetailsResponse> postList = DoctorListApi.getService().DoctorListGetView(token, url);

        Log.v("SignUp", "(DoctorsDetails)token for header userProfile get" + token);
        Log.v("apiUrl",getLocalClassName()+" url ="+url);

        postList.enqueue(new Callback<DoctorDetailsResponse>() {
            @Override
            public void onResponse(Call<DoctorDetailsResponse> call, Response<DoctorDetailsResponse> response) {


//                    Toast.makeText(DoctorsDetail.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(DoctorsDetails)profile get = success");

                DoctorDetailsResponse postList1 = response.body();
                Log.v("SignUp", "(DoctorsDetails)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(DoctorsDetails)profile success get =" + postList1.getSuccess());

////                        listOfDoctorsArray.addAll(postList1.getData());
////                        mAdapter.notifyItemInserted(listOfDoctorsArray.size() - 1);
                    doctorUniqueId = postList1.getData().get(index).getDoctorUniqueId();
                    String clinicName = ""+postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getClinicName();
                    String clinicAddress = ""+postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getAddress();
                    String city = ""+postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getCity();
                    String state = ""+postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getState();

                    String pincode = ""+postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getPincode();

                    String clinicStartTime;
                    try {
                        clinicStartTime = "" + postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getAffiliationDetails().get(0).getDoctorTimings().get(0).getTimings().get(0).getStartTime();
                    } catch (Exception e){
                        Log.v("tryCatch","DoctorDetail:(clinicStartTime)  ="+e.getMessage());
                        Log.v("tryCatchExp",TAG+"clinicStartTime Exception e ="+e.getMessage());
                        clinicStartTime = "";
                    }

                    String clinicEndTime;
                    try {
                        clinicEndTime = postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getAffiliationDetails().get(0).getDoctorTimings().get(0).getTimings().get(0).getEndTime();
                    }catch (Exception e){
                        Log.v("tryCatch","DoctorDetail:(clinicEndTime)  ="+e.getMessage());
                        Log.v("tryCatchExp",TAG+"clinicEndTime Exception e ="+e.getMessage());

                        clinicEndTime = "";
                    }

                    Log.v("education","specialization = ");
                    String specialization = "" ;
                    try{
                        specialization = postList1.getData().get(index).getSpecilaization().get(0).getSpecializationName();
                        Log.v("education","specialization = "+specialization);

                    } catch (Exception e){
                        Log.v("tryCatchExp",TAG+"specialization Exception e ="+e.getMessage());
                        specialization = "";
                    }

                    String degree = "";
                    try{
                        for(int i=0 ; i<postList1.getData().get(index).getDegree().size();i++){
                            degree = degree+" "+postList1.getData().get(index).getDegree().get(i).getDegree();
                            if(postList1.getData().get(index).getDegree().size()-1 != i){
                                degree = degree+",";
                            }
                        }
                        Log.v("education","docDegree = "+degree);

                    }catch (Exception e){
                        Log.v("tryCatchExp",TAG+"degree Exception e ="+e.getMessage());
                        degree = "";
                    }




                    String clinicFirstConsultationFees = postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getAffiliationDetails().get(0).getFirstConsultationFee().toString();
                    final String doctorUniqueId = postList1.getData().get(index).getDoctorUniqueId();

                    final String clinicPhoneNo = ""+postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getMobile();
                    final String clinicTelephoneNo = ""+postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getTelephoneNo();



                    callClinicButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+clinicTelephoneNo));
                            startActivity(intent);
                        }
                    });

                    String fees = "";
                    String phoneConsultFees = "";
                    String textConsultFees = "";
                    String videoConsultFees = "";
                   // officeId = 0;

                    List<ClinicDetail> clinicDetailsArray = postList1.getData().get(index).getClinicDetails();

                    if (clinicDetailsArray != null) {

//                  fees = clinicDetailsArray.get(0).getFirstConsultationFee().toString();

                        if (clinicDetailsArray.get(clinicIndexIntent).getAffiliationDetails() != null) {
                            fees = "RS " + clinicDetailsArray.get(clinicIndexIntent).getAffiliationDetails().get(0).getFirstConsultationFee();


                        phoneConsultFees = "RS " + clinicDetailsArray.get(clinicIndexIntent).getAffiliationDetails().get(0).getVoiceFees();
                        textConsultFees = "RS " + clinicDetailsArray.get(clinicIndexIntent).getAffiliationDetails().get(0).getTextFees();
                        videoConsultFees = "RS " + clinicDetailsArray.get(clinicIndexIntent).getAffiliationDetails().get(0).getVideoFees();
                        officeId = postList1.getData().get(index).getClinicDetails().get(clinicIndexIntent).getAffiliationDetails().get(0).getId();

                            doctorTimingsParcle = clinicDetailsArray.get(clinicIndexIntent).getAffiliationDetails().get(0).getDoctorTimings();

                    }
                        phoneConsultTextView.setText("Consultation Fee "+phoneConsultFees);
                        videoConsultTextView.setText("Consultation Fee "+textConsultFees);
                        textConsultTextView.setText("consultation Fee "+videoConsultFees);


                        SharedPreferences sharedPreferences = getSharedPreferences("token",
                                Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("DOCTOR_UNIQUE_ID", doctorUniqueId);
                        editor.commit();
                        Log.v("UNIQUEid", "doctorUniqueId(DoctorsDetail) = " + doctorUniqueId);



                        clinicNameTextView.setText(""+clinicName);
                        clinicAddress1TextView.setText(""+clinicAddress);
                        clinicAddress2TextView.setText(state + "," + " " + city);

                        clinicTimingMondayTextView.setText(startTimeEndTimeIntent);

                        consultationFeesTextView.setText(fees+" Consultation fees");

                        educationLine1Tv.setText(specialization);
                        educationLine2Tv.setText(degree);





                    }

                    viewAllClinicsTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(v.getContext(), ViewAllClinics.class);
                            intent.putExtra("INDEX", index);
                            intent.putExtra("SPECIALIZATION_ID", specializationId);
                            intent.putExtra("DOCTOR_UNIQUE_ID", doctorUniqueIdIntent);
                            startActivity(intent);

                        }
                    });


                    //onlineConsultations



                    BookClinicAppButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DoctorsDetail.this,schedule.class);
                            intent.putExtra("officeId",officeId);
                            intent.putExtra("doctorUniqueId",doctorUniqueId);
                            intent.putExtra("CONSULTATION_TYPE_ID",1);
                            intent.putParcelableArrayListExtra("DOCTOR_TIMINGS_PARCLE",(ArrayList<DoctorTiming>) doctorTimingsParcle);



                            startActivity(intent);
                        }
                    });

                    VideoConsultCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Intent intent = new Intent(DoctorsDetail.this,MainActivityVideo.class);
//                startActivity(intent);

                            Intent intent = new Intent(DoctorsDetail.this,schedule.class);
                            intent.putExtra("officeId",officeId);
                            intent.putExtra("doctorUniqueId",doctorUniqueId);
                            intent.putExtra("VIDEO_CONSULT",true);
                            intent.putExtra("CONSULTATION_TYPE_ID",2);
                            intent.putParcelableArrayListExtra("DOCTOR_TIMINGS_PARCLE",(ArrayList<DoctorTiming>) doctorTimingsParcle);


                            startActivity(intent);

                        }
                    });

                    phoneConsultCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Intent intent = new Intent(DoctorsDetail.this,schedule.class);
//                Intent intent = new Intent(DoctorsDetail.this,LoginActivity.class);

//                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+clinicPhoneNo));
//                                startActivity(intent);

                            Intent intent = new Intent(DoctorsDetail.this,schedule.class);
                            intent.putExtra("officeId",officeId);
                            intent.putExtra("doctorUniqueId",doctorUniqueId);
                            intent.putExtra("PHONE_CONSULT",true);
                            intent.putExtra("CONSULTATION_TYPE_ID",3);
                            intent.putParcelableArrayListExtra("DOCTOR_TIMINGS_PARCLE",(ArrayList<DoctorTiming>) doctorTimingsParcle);

                            startActivity(intent);



                        }
                    });


                    texConsultCardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                Intent intent = new Intent(DoctorsDetail.this,schedule.class);
//                Intent intent = new Intent(DoctorsDetail.this,chatMainActivity.class);
//
//                startActivity(intent);
//                finish();



                            Intent intent = new Intent(DoctorsDetail.this,schedule.class);
//                                Intent intent = new Intent(DoctorsDetail.this,chatMainActivityBeta.class);
                            intent.putExtra("officeId",officeId);
                            intent.putExtra("doctorUniqueId",doctorUniqueId);
                            intent.putExtra("TEXT_CONSULT",true);
                            intent.putExtra("CONSULTATION_TYPE_ID",4);
                            intent.putParcelableArrayListExtra("DOCTOR_TIMINGS_PARCLE",(ArrayList<DoctorTiming>) doctorTimingsParcle);

                            startActivity(intent);


                        }
                    });




                    //onlineConsultations





                }






            }

            @Override
            public void onFailure(Call<DoctorDetailsResponse> call, Throwable t) {

                Toast.makeText(DoctorsDetail.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(DoctorsDetails)error in link (get)");


            }
        });


        //post


    }


    //NEW CHANGES




}

