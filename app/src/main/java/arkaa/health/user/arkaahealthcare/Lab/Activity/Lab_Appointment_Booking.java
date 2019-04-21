package arkaa.health.user.arkaahealthcare.Lab.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.Firebase.LabAppointmentFirebase;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.ListOfPrescription;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.ListOfPrescription;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.Firebase.LabAppointmentFirebase;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lab_Appointment_Booking extends AppCompatActivity {

    private String token;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

    private LabBookAppointmentPost labBookAppointmentPost;
    private LabBookAppointmentPost labBookAppointmentPrescriptionPost ;
    private List<Datum> labAllTestsLocalStorage;

    private EditText patientNameEditText;
    private EditText patientMobileNoEditText;
    private EditText patientAgeEditText;
    private AutoCompleteTextView patientGenderEditText;
    private EditText patientEmailEditText;

    private EditText patientSelectDateEditText;
    private AutoCompleteTextView patientSelectTimeEditText;
    private AutoCompleteTextView patientServiceTypeEditText;


    private EditText patientLocalityEditText;
    private EditText patientAddressEditText ;
    private EditText patientLandMarkEditText;
    private EditText patientPincodeEditText;
//    private EditText patientBookingTypeEditText;

    private EditText patientAddNotesToDoctorEditText;

    private Button uploadPrescriptionButton;
    private Button continueButton;


    private String labUniqueid;
    private String userUniqueId;
    private String consultationTypeId; //1 & 4
    
    private String patientSelectDate;
    private String patientSelectTime;

    private String patientName;
    private String patientMobile;
    private String patientAge;
    private String patientGender ;
    private String patientEmail ;
    private String patientLocality;
    private String patientAddress;
    private String patientLandmark ;
    private String patientPincode ;
    private String patientConsultationTypeId ;
    private String prescriptionId ;
    private String description;


    private static final int PRESCRIPTION_RESULT = 2;
    private String bookingIntentType;

    //firebase

    private FirebaseDatabase mFirebaseDataBase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListner;
    private LabAppointmentFirebase labAppointmentFirebase;


    //firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab__appointment__booking);

        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("lab_appointments");
        labAppointmentFirebase = new LabAppointmentFirebase();


        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);

        prescriptionId = "null";
        userUniqueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");
        labUniqueid = getIntent().getStringExtra("LAB_UNIQUE_ID");
        token = preferences.getString("TOKEN", "123456789");

//        consultationTypeId = "1" ;


        patientNameEditText = findViewById(R.id.patient_name);
        patientMobileNoEditText = findViewById(R.id.patient_mobile_no);
        patientAgeEditText = findViewById(R.id.patient_age);
        patientGenderEditText = findViewById(R.id.patient_gender);
        patientEmailEditText = findViewById(R.id.patient_email_id);

        patientSelectDateEditText = findViewById(R.id.select_date);
        patientSelectTimeEditText = findViewById(R.id.select_time_slot);
        patientServiceTypeEditText = findViewById(R.id.service_type);

        patientLocalityEditText = findViewById(R.id.patient_locality);
        patientAddressEditText = findViewById(R.id.patient_address);
        patientLandMarkEditText = findViewById(R.id.patient_landmark);
        patientPincodeEditText = findViewById(R.id.patient_pincode);
//        patientBookingTypeEditText = findViewById(R.id.patient_booking_type);

        patientAddNotesToDoctorEditText = findViewById(R.id.add_notes_to_doc);

        uploadPrescriptionButton = findViewById(R.id.upload_prescription);
        continueButton = findViewById(R.id.continue_button);

        //calender2
        patientSelectDateEditText.setFocusable(false);
        myCalendar = Calendar.getInstance();


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        patientSelectDateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                patientSelectDateEditText.setError(null);
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(Lab_Appointment_Booking.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();
            }
        });



        //time_slots

        String[] languages1 = { "9:30 AM","10:30 AM","11:00 AM","4:00 PM","5:30 PM","6:30 PM","7:30 PM"};

        ArrayList<String> timeSlotArrayList = getTimingArray();





        ArrayAdapter<String> adapter1 ;

        //Create Array Adapter
        if(timeSlotArrayList == null) {
            adapter1 = new ArrayAdapter<String>(Lab_Appointment_Booking.this, android.R.layout.select_dialog_singlechoice, languages1);
        } else {
            adapter1 = new ArrayAdapter<String>(Lab_Appointment_Booking.this, android.R.layout.select_dialog_singlechoice, timeSlotArrayList);

        }
        //Find TextView control
//        final AutoCompleteTextView acTextView1 = (AutoCompleteTextView) findViewById(R.id.time_slot);
        patientSelectTimeEditText.setFocusable(false);
        //Set the number of characters the user must type before the drop down list is shown
        patientSelectTimeEditText.setThreshold(0);
        //Set the adapter
        patientSelectTimeEditText.setAdapter(adapter1);

        patientSelectTimeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    patientSelectTimeEditText.showDropDown();
                patientSelectTimeEditText.setError(null);



            }
        });

        patientSelectTimeEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                patientSelectTimeEditText.showDropDown();
                patientSelectTimeEditText.setError(null);

                return false;
            }
        });







        //time_slots



        //Service type

        String[] serviceTypeArray = { "Walk-in Lab","Home Collection"};

        //Create Array Adapter
        ArrayAdapter<String> serviceTypeAdapter = new ArrayAdapter<String>(Lab_Appointment_Booking.this,android.R.layout.select_dialog_singlechoice, serviceTypeArray);
        //Find TextView control
//        final AutoCompleteTextView acTextView1 = (AutoCompleteTextView) findViewById(R.id.time_slot);
        patientServiceTypeEditText.setFocusable(false);
        //Set the number of characters the user must type before the drop down list is shown
        patientServiceTypeEditText.setThreshold(0);
        //Set the adapter
        patientServiceTypeEditText.setAdapter(serviceTypeAdapter);

        patientServiceTypeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    patientServiceTypeEditText.showDropDown();

            }
        });

        patientServiceTypeEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                patientServiceTypeEditText.showDropDown();
                return false;
            }
        });







        //Service type



        //gender

        String[] genderArray = { "Male","Female"};

        //Create Array Adapter
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(Lab_Appointment_Booking.this,android.R.layout.select_dialog_singlechoice, genderArray);
        //Find TextView control
//        final AutoCompleteTextView acTextView1 = (AutoCompleteTextView) findViewById(R.id.time_slot);
        patientGenderEditText.setFocusable(false);
        //Set the number of characters the user must type before the drop down list is shown
        patientGenderEditText.setThreshold(0);
        //Set the adapter
        patientGenderEditText.setAdapter(genderAdapter);

        patientGenderEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    patientGenderEditText.showDropDown();
                patientGenderEditText.setError(null);



            }
        });

        patientGenderEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                patientGenderEditText.showDropDown();
                patientGenderEditText.setError(null);

                return false;
            }
        });



        //gender













        bookingIntentType = getIntent().getStringExtra("BOOKING_TYPE");
        if(bookingIntentType == null){
            bookingIntentType = "";
        }

        if(!bookingIntentType.equals("uploadPrescription")) {

            labBookAppointmentPost = getIntent().getParcelableExtra("LAB_BOOK_APPOINTMENT_POST_OBJ");
            if (labBookAppointmentPost != null) {
                Log.v("parcelable", "parcelable = " + labBookAppointmentPost.getTests().get(0));
            }

            labAllTestsLocalStorage = getIntent().getParcelableArrayListExtra("LAB_ALL_TESTS_LOCAL_STORAGE");
            if (labAllTestsLocalStorage != null) {
                Log.v("parcelableLocalStorage", "parcelable = " + labAllTestsLocalStorage.size());
            }

        }  else {
//            patientBookingTypeEditText.setVisibility(View.GONE);
        }



        labBookAppointmentPrescriptionPost = new LabBookAppointmentPost();


        uploadPrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab_Appointment_Booking.this,ListOfPrescription.class);
                intent.putExtra("ACTIVITY_TYPE","LAB");
                startActivityForResult(intent,PRESCRIPTION_RESULT);
            }
        });




        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!bookingIntentType.equals("uploadPrescription")) {

                    if(validate()) {
                        assignLabAppointmentPostObjectValue();
//                        Intent intent = new Intent(Lab_Appointment_Booking.this, LabConfirmAppointmentBooking.class);
//                        intent.putExtra("LAB_BOOK_APPOINTMENT_POST_OBJ", labBookAppointmentPost);
//                        intent.putParcelableArrayListExtra("LAB_ALL_TESTS_LOCAL_STORAGE", (ArrayList<? extends Parcelable>) labAllTestsLocalStorage);
//                        startActivity(intent);
                        postDataCart();

                    }

                } else {
                    if(validate()) {
                        postDataUploadPrescription();
                    }
                }


            }
        });



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.v("parcelable","onActivityResult");

        if(requestCode == PRESCRIPTION_RESULT){
            if(resultCode == Activity.RESULT_OK){
                prescriptionId = Integer.toString(data.getIntExtra("PRESCRIPTION_ID",0));
                Log.v("parcelable","onActivityResult pres ID = "+prescriptionId);


            }


        }



    }



    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        patientSelectDateEditText.setText(sdf.format(myCalendar.getTime()));
    }




    public void assignLabAppointmentPostObjectValue(){
        
        patientSelectDate = patientSelectDateEditText.getText().toString();
        patientSelectTime = patientSelectTimeEditText.getText().toString();

        patientName = patientNameEditText.getText().toString();
        patientMobile = patientMobileNoEditText.getText().toString();
        patientAge = patientAgeEditText.getText().toString();
        patientGender = patientGenderEditText.getText().toString();
        patientEmail = patientEmailEditText.getText().toString();
        patientLocality = patientLocalityEditText.getText().toString();
        patientAddress = patientAddressEditText.getText().toString();
        patientLandmark = patientLandMarkEditText.getText().toString();
        patientPincode = patientPincodeEditText.getText().toString();
//        patientBookingType = patientBookingTypeEditText.getText().toString();
        description = patientAddNotesToDoctorEditText.getText().toString();

        if(patientServiceTypeEditText.getText().toString().equals("Walk-in Lab")){
            patientConsultationTypeId = "1";
        }else if(patientServiceTypeEditText.getText().toString().equals("Home Collection")){
            patientConsultationTypeId = "5";
        }else {
            patientConsultationTypeId = "1";
        }

        Log.v("con_type_id","consultation type id "+patientConsultationTypeId);

        //hard coded values

        labBookAppointmentPost.setActualEndTime("00:00:00");
        labBookAppointmentPost.setConsultationTypeId(patientConsultationTypeId);
        labBookAppointmentPost.setPaymentTypeId("2");
//        labBookAppointmentPost.setDescription("");
        labBookAppointmentPost.setDescription(description);


        //hard coded values

        labBookAppointmentPost.setPrescriptionId(prescriptionId);
        Log.v("parcelable","pres Id = "+prescriptionId);

        labBookAppointmentPost.setLabUid(labUniqueid);
        labBookAppointmentPost.setUserUid(userUniqueId);
        Log.v("presunique"," lab unique id"+labUniqueid);

        labBookAppointmentPost.setAppointmentDate(patientSelectDate);
        labBookAppointmentPost.setProbableStartTime(patientSelectTime);

        labBookAppointmentPost.setName(patientName);
        labBookAppointmentPost.setMobile(patientMobile);
        labBookAppointmentPost.setAge(patientAge);
        labBookAppointmentPost.setGender(patientGender);
        labBookAppointmentPost.setEmail(patientEmail);
        labBookAppointmentPost.setLocality(patientLocality);
        labBookAppointmentPost.setAddress(patientAddress);
        labBookAppointmentPost.setLandmark(patientLandmark);
        labBookAppointmentPost.setPincode(patientPincode);
//        try {
//            labBookAppointmentPost.setBookingType(Integer.parseInt(patientBookingType));
//        } catch (Exception e) {
//            labBookAppointmentPost.setBookingType(1);
//        }

            labBookAppointmentPost.setBookingType(1);







    }

    public LabBookAppointmentPost getLabAppointmentPrescriptionObject(){

        List<Integer> integer = new ArrayList<>();

        patientSelectDate = patientSelectDateEditText.getText().toString();
        patientSelectTime = patientSelectTimeEditText.getText().toString();

        patientName = patientNameEditText.getText().toString();
        patientMobile = patientMobileNoEditText.getText().toString();
        patientAge = patientAgeEditText.getText().toString();
        patientGender = patientGenderEditText.getText().toString();
        patientEmail = patientEmailEditText.getText().toString();
        patientLocality = patientLocalityEditText.getText().toString();
        patientAddress = patientAddressEditText.getText().toString();
        patientLandmark = patientLandMarkEditText.getText().toString();
        patientPincode = patientPincodeEditText.getText().toString();
//        patientBookingType = patientBookingTypeEditText.getText().toString();
        description = patientAddNotesToDoctorEditText.getText().toString();


        if(patientServiceTypeEditText.getText().toString().equals("Walk-in Lab")){
            patientConsultationTypeId = "1";
        }else if(patientServiceTypeEditText.getText().toString().equals("Home Collection")){
            patientConsultationTypeId = "5";
        }else {
            patientConsultationTypeId = "1";
        }
        Log.v("con_type_id","consultation type id  prescription "+patientConsultationTypeId);




        //hard coded values



        labBookAppointmentPrescriptionPost.setActualEndTime("00:00:00");
        labBookAppointmentPrescriptionPost.setConsultationTypeId(patientConsultationTypeId);
        labBookAppointmentPrescriptionPost.setPaymentTypeId("2");
//        labBookAppointmentPrescriptionPost.setDescription("");
        labBookAppointmentPrescriptionPost.setDescription(description);

        labBookAppointmentPrescriptionPost.setTotalCost("0");
        labBookAppointmentPrescriptionPost.setTests(integer);

        //hard coded values

        labBookAppointmentPrescriptionPost.setPrescriptionId(prescriptionId);
        Log.v("parcelable","pres Id = "+prescriptionId);

        labBookAppointmentPrescriptionPost.setLabUid(labUniqueid);
        labBookAppointmentPrescriptionPost.setUserUid(userUniqueId);

        labBookAppointmentPrescriptionPost.setAppointmentDate(patientSelectDate);
        labBookAppointmentPrescriptionPost.setProbableStartTime(patientSelectTime);

        labBookAppointmentPrescriptionPost.setName(patientName);
        labBookAppointmentPrescriptionPost.setMobile(patientMobile);
        labBookAppointmentPrescriptionPost.setAge(patientAge);
        labBookAppointmentPrescriptionPost.setGender(patientGender);
        labBookAppointmentPrescriptionPost.setEmail(patientEmail);
        labBookAppointmentPrescriptionPost.setLocality(patientLocality);
        labBookAppointmentPrescriptionPost.setAddress(patientAddress);
        labBookAppointmentPrescriptionPost.setLandmark(patientLandmark);
        labBookAppointmentPrescriptionPost.setPincode(patientPincode);
//        try {
//            labBookAppointmentPrescriptionPost.setBookingType(Integer.parseInt(patientBookingType));
//        } catch (Exception e) {
//            labBookAppointmentPrescriptionPost.setBookingType(2);
//        }

            labBookAppointmentPrescriptionPost.setBookingType(2);




        return labBookAppointmentPrescriptionPost;


        }


    public void postDataUploadPrescription() {


        String url = "https://arkaahealthapp.com/api/v1/labs/appointments";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);
        final LabBookAppointmentPost labBookAppointmentPrescriptionPostObj = getLabAppointmentPrescriptionObject();

        final Call<LabBookAppointmentResponse> postList = LabApi.getService().LabBooking(token,url, labBookAppointmentPrescriptionPostObj);

        postList.enqueue(new Callback<LabBookAppointmentResponse>() {
            @Override
            public void onResponse(Call<LabBookAppointmentResponse> call, Response<LabBookAppointmentResponse> response) {

                final LabBookAppointmentResponse postList1 = response.body();
//
//                Log.v("objectTesting12","name "+labBookAppointmentPrescriptionPostObj.getName());
//                Log.v("objectTesting12","address "+labBookAppointmentPrescriptionPostObj.getAddress());
//                Log.v("objectTesting12","total cost "+labBookAppointmentPrescriptionPostObj.getTotalCost());
//                Log.v("objectTesting12","email "+labBookAppointmentPrescriptionPostObj.getEmail());
//                Log.v("objectTesting12","pincode "+labBookAppointmentPrescriptionPostObj.getPincode());







                if (postList1.getSuccess() == true) {

                    Log.v("confirmBooking","confirmBooking = "+postList1.getSuccess());

                    String patientName = labBookAppointmentPrescriptionPostObj.getName();
                    String patientUniqueId = labBookAppointmentPrescriptionPostObj.getUserUid();
                    String labUniqueId = labBookAppointmentPrescriptionPostObj.getLabUid();

                    addDataToFireBase(patientName,patientUniqueId,labUniqueId);

                    showSuccessDialog();




                } else {
                    Toast.makeText(Lab_Appointment_Booking.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<LabBookAppointmentResponse> call, Throwable t) {

                Toast.makeText(Lab_Appointment_Booking.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void postDataCart() {


        String url = "https://arkaahealthapp.com/api/v1/labs/appointments";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        final Call<LabBookAppointmentResponse> postList = LabApi.getService().LabBooking(token,url, labBookAppointmentPost);

        postList.enqueue(new Callback<LabBookAppointmentResponse>() {
            @Override
            public void onResponse(Call<LabBookAppointmentResponse> call, Response<LabBookAppointmentResponse> response) {

                final LabBookAppointmentResponse postList1 = response.body();
//
//                Log.v("objectTesting12","name "+labBookAppointmentPost.getName());
//                Log.v("objectTesting12","address "+labBookAppointmentPost.getAddress());
//                Log.v("objectTesting12","total cost "+labBookAppointmentPost.getTotalCost());
//                Log.v("objectTesting12","email "+labBookAppointmentPost.getEmail());
//                Log.v("objectTesting12","pincode "+labBookAppointmentPost.getPincode());







                if (postList1.getSuccess() == true) {

//                    Log.v("confirmBooking","confirmBooking = "+postList1.getSuccess());
//
//                    String patientName = labBookAppointmentPost.getName();
//                    String patientUniqueId = labBookAppointmentPost.getUserUid();
//                    String labUniqueId = labBookAppointmentPost.getLabUid();
//
//                    addDataToFireBase(patientName,patientUniqueId,labUniqueId);
//
//                    showSuccessDialog();

                    int labAppointmentId = postList1.getData().get(0).getInsertOrUpdateLabAppointment();
                    if(!(labAppointmentId == 0)){
                        Log.v("labAppointmentId","labAppointmentId"+labAppointmentId);
                        Intent intent = new Intent(Lab_Appointment_Booking.this, LabConfirmAppointmentBookingBeta.class);
                        intent.putExtra("LAB_APPOINTMENT_ID",labAppointmentId);
                        startActivity(intent);

                    }




                } else {
                    Toast.makeText(Lab_Appointment_Booking.this, "Servers are down,Please try again later", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<LabBookAppointmentResponse> call, Throwable t) {

                Toast.makeText(Lab_Appointment_Booking.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }




    public boolean validate() {
        boolean valid = true;

        String name = patientNameEditText.getText().toString();
        String mobile = patientMobileNoEditText.getText().toString();
        String age = patientAgeEditText.getText().toString();
        String gender = patientGenderEditText.getText().toString();
        String email = patientEmailEditText.getText().toString();

        String date = patientSelectDateEditText.getText().toString();
        String time = patientSelectTimeEditText.getText().toString();

        String locality = patientLocalityEditText.getText().toString();
        String address = patientAddressEditText.getText().toString();
        String landmark = patientLandMarkEditText.getText().toString();
        String pincode = patientPincodeEditText.getText().toString();
        String serviceType = patientServiceTypeEditText.getText().toString();

        if (name.isEmpty()) {
            patientNameEditText.setError("Please enter patient's name.");
            valid = false;
        } else {
            patientNameEditText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            patientMobileNoEditText.setError("Please Enter valid 10 digit Mobile Number");
            valid = false;
        } else {
            patientMobileNoEditText.setError(null);
        }

        if (age.isEmpty()) {
            patientAgeEditText.setError("Please enter patient's age.");
            valid = false;
        } else {
            patientAgeEditText.setError(null);
        }

        if (gender.isEmpty()) {
            patientGenderEditText.setError("Please select patient's gender.");
            valid = false;
        } else {
            patientGenderEditText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            patientEmailEditText.setError("Please enter a valid email address");
            valid = false;
        } else {
            patientEmailEditText.setError(null);
        }

        //date and time

        if (date.isEmpty()) {
            patientSelectDateEditText.setError("Please select appointment date.");
            valid = false;
        } else {
            patientSelectDateEditText.setError(null);
        }

        if (time.isEmpty()) {
            patientSelectTimeEditText.setError("Please select time slot.");
            valid = false;
        } else {
            patientSelectTimeEditText.setError(null);
        }

        //location details

        if (locality.isEmpty()) {
            patientLocalityEditText.setError("Please Enter Locality");
            valid = false;
        } else {
            patientLocalityEditText.setError(null);
        }

        if (address.isEmpty() || address.length() <=10) {
            Log.v("lengthVali","lab");
            patientAddressEditText.setError("Address should be more than 10 characters.");
            valid = false;
        } else {
            patientAddressEditText.setError(null);
        }

        if (landmark.isEmpty()) {
            patientLandMarkEditText.setError("Please Enter Landmark");
            valid = false;
        } else {
            patientLandMarkEditText.setError(null);
        }

        if (pincode.isEmpty()) {
            patientPincodeEditText.setError("Please Enter Pincode");
            valid = false;
        } else {
            patientPincodeEditText.setError(null);
        }

        if (serviceType.isEmpty()) {
            patientServiceTypeEditText.setError("Please Select Service Type");
            valid = false;
        } else {
            patientServiceTypeEditText.setError(null);
        }

        if(prescriptionId.equals("null")){
            valid = false;
            Toast.makeText(Lab_Appointment_Booking.this,"Please Upload Prescription",Toast.LENGTH_SHORT).show();

        }





        return valid;
    }

    public ArrayList<String> getTimingArray(){

        ArrayList<String> timeSlotArrayList =  new ArrayList<>();


        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();
        long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String changedTime = dateFormat.format(currentTime);


        String date1 = "26/02/2011";
        // String time1 = "00:00 AM";
        String date2 = "26/02/2011";
        // String time2 = "12:00 PM";

        String time1 = "09:00 AM";
        String time2 = "10:00 PM";






        String format = "dd/MM/yyyy hh:mm a";
        String timeSlot = "hh:mm a";

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat timeSlotFormat  = new SimpleDateFormat(timeSlot);

        Date dateObj1 = null;
        try {
//            dateObj1 = sdf.parse(date1 + " " + time1);
            dateObj1 = sdf.parse(changedTime + " " + time1);
            Log.v("timeSlots","date obj 1 ="+dateObj1);

        } catch (ParseException e) {
            e.printStackTrace();
            Log.v("timeSlots","exception 1="+e.getMessage());

        }


        Date dateObj2 = null;
        try {
//            dateObj2 = sdf.parse(date2 + " " + time2);

            dateObj2 = sdf.parse(changedTime + " " + time2);
            Log.v("timeSlots","date obj 2 ="+dateObj2);


        } catch (ParseException e) {
            e.printStackTrace();
            Log.v("timeSlots","exception 2="+e.getMessage());

        }
        System.out.println("Date Start: "+dateObj1);
        System.out.println("Date End: "+dateObj2);

//Date d = new Date(dateObj1.getTime() + 3600000);


        long dif = dateObj1.getTime();
        while (dif < dateObj2.getTime()) {
            Date slot = new Date(dif);
            System.out.println("Hour Slot --->" + slot);
            Log.v("timeSlots","time slots = "+slot);


            timeSlotArrayList.add(timeSlotFormat.format(slot));
            Log.v("timeSlots11","time slot array list "+timeSlotArrayList.get(timeSlotArrayList.size()-1));


            if(currentTimeConst < slot.getTime()) {

                Log.v("timeSlots11", "time slots in proper format(if statement) = " + timeSlotFormat.format(slot));

            }else {
                Log.v("timeSlots11", "time slots in proper format(else statement) = " + timeSlotFormat.format(slot));

            }
            dif += (3600000/2);

        }

//        Calendar rightNow = Calendar.getInstance();

        Log.v("cURRENTtIME","time "+System.currentTimeMillis());
        Log.v("cURRENTtIME","calender time = "+ rightNow.getTime());
        Log.v("cURRENTtIME","calender time(msec) = "+ rightNow.getTimeInMillis());
        Log.v("cURRENTtIME","calender timeZone = "+ rightNow.getTimeZone());
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String changedTime = dateFormat.format(rightNow.getTime());

        Log.v("cURRENTtIME","calender changedTime = "+ changedTime);


        return timeSlotArrayList;




    }

    public void showSuccessDialog(){

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
                .setTitle(" Thank You,Please check Inbox for appointment status.")
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
                        Intent i = new Intent(Lab_Appointment_Booking.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();




    }

    public void addDataToFireBase(String patientName , String patientUniqueId, String labUniqueid) {

        labAppointmentFirebase.setPatientName(patientName);
        labAppointmentFirebase.setPatientUniqueId(patientUniqueId);

        mMessagesDatabaseReference.child(labUniqueid).push().setValue(labAppointmentFirebase);


    }

















}






