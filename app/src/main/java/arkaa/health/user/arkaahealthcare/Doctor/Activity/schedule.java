package arkaa.health.user.arkaahealthcare.Doctor.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorTiming;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Timing;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Timing_;
import arkaa.health.user.arkaahealthcare.Doctor.RetrofitApi.BookAnAppointmentApi;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.appointment.BookAnAppointmentPost;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.appointment.BookAnAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Lab.Activity.Lab_Appointment_Booking;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.Firebase.PharmacyOrderBookingFirebase;
import arkaa.health.user.arkaahealthcare.R;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.appointment.BookAnAppointmentPost;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.appointment.BookAnAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Doctor.RetrofitApi.BookAnAppointmentApi;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.Firebase.PharmacyOrderBookingFirebase;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class schedule extends AppCompatActivity {

    private static final String lab = "LAB";
    @BindView(R.id.patients_state)
    EditText stateEditText;
    @BindView(R.id.patients_city)
    EditText cityEditText;
    @BindView(R.id.patients_address)
    EditText addressEditText;
    @BindView(R.id.patients_pincode)
    EditText pincodeEditText;
    @BindView(R.id.payment_mode)
    AutoCompleteTextView paymentModeTv;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private String ans;
    private String token;
    private BookAnAppointmentPost bookAnAppointmentPost;
    private Button submitButton;
    private String selectDate;
    private String selectTimeSlot;
    private String patientName;
    private String patientMobile;
    private String patientEmail;
    private String address;
    private String state;
    private String city;
    private String pincode;
    private String paymentMode;
    private EditText selectDateEditText;
    private EditText dateOfBirthEditText;
    private AutoCompleteTextView selectTimeSlotEditText;
    private EditText patientNameEditText;
    private EditText mobileEdittext;
    private EditText emailEdittext;
    private EditText addNotesToDoctorEditText;
    private AutoCompleteTextView patientGenderEditText;
    private EditText patientAgeEditText;
    private int id;
    private int consultationTypeIdIntent;
    private String userUniqueId;
    private int officeId;
    private String doctorUniqueId;


    //firebase

    private FirebaseDatabase mFirebaseDataBase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListner;

    //firebase

    private boolean videoConsult;
    private boolean phoneConsult;
    private boolean textConsult;

    //doctor timings parcle
    List<DoctorTiming> doctorTimingsParcleIntent ;
    //doctor timings parcle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ButterKnife.bind(this);

        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("appointments");

        doctorUniqueId = getIntent().getStringExtra("doctorUniqueId");
        doctorTimingsParcleIntent = getIntent().getParcelableArrayListExtra("DOCTOR_TIMINGS_PARCLE");

        selectDateEditText = findViewById(R.id.select_date);
        selectTimeSlotEditText = findViewById(R.id.time_slot);
        patientNameEditText = findViewById(R.id.patients_name);
        mobileEdittext = findViewById(R.id.patients_mobile);
        emailEdittext = findViewById(R.id.email);
        addNotesToDoctorEditText = findViewById(R.id.add_notes_to_doc);
        patientGenderEditText = findViewById(R.id.patient_gender);
        patientAgeEditText = findViewById(R.id.patient_age);


        officeId = getIntent().getIntExtra("officeId", 123456);
        videoConsult = getIntent().getBooleanExtra("VIDEO_CONSULT", false);
        phoneConsult = getIntent().getBooleanExtra("PHONE_CONSULT", false);
        textConsult = getIntent().getBooleanExtra("TEXT_CONSULT", false);

        consultationTypeIdIntent = getIntent().getIntExtra("CONSULTATION_TYPE_ID", 0);


        Log.v("scheduleIntents", getLocalClassName() + " video Consult = " + videoConsult + " doctorUniqueId =" + doctorUniqueId + " officeId " + officeId);
        if (videoConsult || phoneConsult || textConsult) {
            // paymentModeTv.setVisibility(View.GONE);

            paymentModeTv.setText("Online Payment");
            paymentModeTv.setFocusable(false);
            paymentModeTv.setEnabled(false);

        }

        //   ans = getIntent().getStringExtra("lab");
//        if(lab.equals(ans)){
//            setContentView(R.layout.lab_schedule);
//
//        }

        submitButton = findViewById(R.id.submit123);


        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);

        token = preferences.getString("TOKEN", "123456789");

        //dropdown

        //Find TextView control
        selectTimeSlotEditText.setFocusable(false);
        //Set the number of characters the user must type before the drop down list is shown
        selectTimeSlotEditText.setThreshold(0);


        ArrayList<String> timeSlotArrayList = getTimingArray();
//        ArrayAdapter<String> timeSlotArrayAdapter;

//        timeSlotArrayAdapter = new ArrayAdapter<String>(schedule.this, android.R.layout.select_dialog_singlechoice, timeSlotArrayList);


        //Set the adapter
//        selectTimeSlotEditText.setAdapter(timeSlotArrayAdapter);
//
//        selectTimeSlotEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus)
//                    selectTimeSlotEditText.showDropDown();
//                selectTimeSlotEditText.setError(null);
//
//            }
//        });
//
//        selectTimeSlotEditText.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                selectTimeSlotEditText.showDropDown();
//                selectTimeSlotEditText.setError(null);
//
//                return false;
//            }
//        });


        //dropdown


        //payment mode
        List<String> paymentModeArrayList = new ArrayList<>();
        paymentModeArrayList.add("COD");
        paymentModeArrayList.add("Online Payment");
        ArrayAdapter<String> paymentModeAdapter = new ArrayAdapter<String>(schedule.this, android.R.layout.select_dialog_singlechoice, paymentModeArrayList);

        paymentModeTv.setFocusable(false);
        paymentModeTv.setThreshold(0);
        paymentModeTv.setAdapter(paymentModeAdapter);
        paymentModeTv.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    paymentModeTv.showDropDown();
                paymentModeTv.setError(null);

            }
        });

        paymentModeTv.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                paymentModeTv.showDropDown();
                paymentModeTv.setError(null);

                return false;
            }
        });




        //calender2
        selectDateEditText.setFocusable(false);
        myCalendar = Calendar.getInstance();


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                view.setMinDate(myCalendar.getTimeInMillis());
                updateLabel();
            }

        };


        selectDateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                selectDateEditText.setError(null);
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(schedule.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();
            }
        });


        //calender2

        //gender

        String[] genderArray = {"Male", "Female"};

        //Create Array Adapter
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(schedule.this, android.R.layout.select_dialog_singlechoice, genderArray);
        //Find TextView control
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


        bookAnAppointmentPost = new BookAnAppointmentPost();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    getData();
                }


            }
        });


        //firebase

        mChildEventListner = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                Toast.makeText(schedule.this,"Book",Toast.LENGTH_SHORT).show();

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

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String date = sdf.format(myCalendar.getTime());
        selectDateEditText.setText(date);

        if(doctorTimingsParcleIntent != null){
//            try {
                Log.v("docTimeParcel","doctorTimingsParcleIntent != null ");

                displayTimeSlots(doctorTimingsParcleIntent, date);
//            } catch (Exception e){
//                Toast.makeText(schedule.this,"Dcotor not available on this date",Toast.LENGTH_SHORT).show();
//                Log.v("tryCatch","schedule Exception e = "+e.getMessage());
//                Log.v("tryCatch","schedule Exception e = "+e.getStackTrace());
//                Log.v("docTimeParcel","Toast : displayTimeSlots exception ");
//
//
//            }
        }else {
            Toast.makeText(schedule.this,"Dcotor not available on this date",Toast.LENGTH_SHORT).show();
            Log.v("docTimeParcel","Toast : if(doctorTimingsParcleIntent != null) else body ");


        }



    }




    public void getData() {


        //post

        Log.v("schedulegetdata", "GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/appointments";

        Call<BookAnAppointmentResponse> postList = BookAnAppointmentApi.getService().BookAnAppointment(token, url, assignValues());

        Log.v("SignUp", "(post)token for header userProfile get" + token);

        postList.enqueue(new Callback<BookAnAppointmentResponse>() {
            @Override
            public void onResponse(Call<BookAnAppointmentResponse> call, Response<BookAnAppointmentResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                BookAnAppointmentResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(post)schedule get =" + postList1.getSuccess());

                    if (postList1.getSuccess() == true) {


                        PharmacyOrderBookingFirebase pharmacyOrderBookingFirebase = new PharmacyOrderBookingFirebase();
                        pharmacyOrderBookingFirebase.setPatientName(patientName);
                        pharmacyOrderBookingFirebase.setPatientUniqueId(userUniqueId);

//                        labAppointmentFirebase.setPatientName(patientName);
//                        labAppointmentFirebase.setPatientUniqueId(patientUniqueId);
//
//                        mMessagesDatabaseReference.child(labUniqueid).push().setValue(labAppointmentFirebase);

                        if (doctorUniqueId != null) {
                            mMessagesDatabaseReference.child(doctorUniqueId).push().setValue(pharmacyOrderBookingFirebase);

                        }


                        showSuccessDialog();
                        Log.v("schedule", getLocalClassName() + " =success id = " + postList1.getMessage());

                    }


                }


            }

            @Override
            public void onFailure(Call<BookAnAppointmentResponse> call, Throwable t) {

                Toast.makeText(schedule.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }

    public BookAnAppointmentPost assignValues() {

        selectDate = selectDateEditText.getText().toString();
        selectTimeSlot = selectTimeSlotEditText.getText().toString();
        patientName = patientNameEditText.getText().toString();
        patientMobile = mobileEdittext.getText().toString();
        patientEmail = emailEdittext.getText().toString();

        state = stateEditText.getText().toString();
        city = cityEditText.getText().toString();
        address = addressEditText.getText().toString();
        pincode = pincodeEditText.getText().toString();

        if (paymentModeTv.getText().toString().equals("COD")) {
            paymentMode = "1";
        } else if (paymentModeTv.getText().toString().equals("Online Payment")) {
            paymentMode = "2";
        }


        id = 0;

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);

        userUniqueId = preferences.getString("USERUNIQUEID", "123456789");

        bookAnAppointmentPost.setAppointmentDate(selectDate);
        bookAnAppointmentPost.setProbableStartTime(selectTimeSlot);
        bookAnAppointmentPost.setName(patientName);
        bookAnAppointmentPost.setMobile(patientMobile);
        bookAnAppointmentPost.setEmail(patientEmail);
        bookAnAppointmentPost.setId(id);
        bookAnAppointmentPost.setOfficeId(officeId);
        bookAnAppointmentPost.setConsultationTypeId(consultationTypeIdIntent);

        bookAnAppointmentPost.setUserUid(userUniqueId);

        bookAnAppointmentPost.setState(state);
        bookAnAppointmentPost.setCity(city);
        bookAnAppointmentPost.setAddress(address);
        bookAnAppointmentPost.setPincode(pincode);
        bookAnAppointmentPost.setPaymentmode(paymentMode);


        return bookAnAppointmentPost;


    }


    public boolean validate() {
        boolean valid = true;

        String name = patientNameEditText.getText().toString();
        String email = emailEdittext.getText().toString();
        String mobile = mobileEdittext.getText().toString();
        String date = selectDateEditText.getText().toString();
        String time = selectTimeSlotEditText.getText().toString();
        String gender = patientGenderEditText.getText().toString();
        String age = patientAgeEditText.getText().toString();

        String address = addressEditText.getText().toString();

        String paymentMode = paymentModeTv.getText().toString();

        if (address.isEmpty() || address.length() <= 10) {
            addressEditText.setError("Address should be more than 10 characters.");
            valid = false;
        } else {
            addressEditText.setError(null);
        }


        if (name.isEmpty()) {
            patientNameEditText.setError("Please enter user name.");
            valid = false;
        } else {
            patientNameEditText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            mobileEdittext.setError("Please Enter valid 10 digit Mobile Number");
            valid = false;
        } else {
            mobileEdittext.setError(null);
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


//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailEdittext.setError("Please enter a valid email address");
//            valid = false;
//        } else {
//            emailEdittext.setError(null);
//        }

        if (email.isEmpty()) {
            emailEdittext.setError("Please enter a valid email address");
            valid = false;
        } else {
            emailEdittext.setError(null);
        }


        if (date.isEmpty()) {
            selectDateEditText.setError("Please select appointment date.");
            valid = false;
        } else {
            selectDateEditText.setError(null);
        }

        if (time.isEmpty()) {
            selectTimeSlotEditText.setError("Please select time slot.");
            valid = false;
        } else {
            selectTimeSlotEditText.setError(null);
        }

        if (paymentMode.isEmpty()) {
            paymentModeTv.setError("Please Select Payment Mode");
            valid = false;
        } else {
            paymentModeTv.setError(null);
        }


        return valid;
    }

    public ArrayList<String> getTimingArray() {

        ArrayList<String> timeSlotArrayList = new ArrayList<>();


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
        SimpleDateFormat timeSlotFormat = new SimpleDateFormat(timeSlot);

        Date dateObj1 = null;
        try {
//            dateObj1 = sdf.parse(date1 + " " + time1);
            dateObj1 = sdf.parse(changedTime + " " + time1);
            Log.v("timeSlots", "date obj 1 =" + dateObj1);

        } catch (ParseException e) {
            e.printStackTrace();
            Log.v("timeSlots", "exception 1=" + e.getMessage());

        }


        Date dateObj2 = null;
        try {
//            dateObj2 = sdf.parse(date2 + " " + time2);

            dateObj2 = sdf.parse(changedTime + " " + time2);
            Log.v("timeSlots", "date obj 2 =" + dateObj2);


        } catch (ParseException e) {
            e.printStackTrace();
            Log.v("timeSlots", "exception 2=" + e.getMessage());

        }
        System.out.println("Date Start: " + dateObj1);
        System.out.println("Date End: " + dateObj2);

//Date d = new Date(dateObj1.getTime() + 3600000);


        long dif = dateObj1.getTime();
        while (dif < dateObj2.getTime()) {
            Date slot = new Date(dif);
            System.out.println("Hour Slot --->" + slot);
            Log.v("timeSlots", "time slots = " + slot);


            timeSlotArrayList.add(timeSlotFormat.format(slot));
            Log.v("timeSlots11", "time slot array list " + timeSlotArrayList.get(timeSlotArrayList.size() - 1));


            if (currentTimeConst < slot.getTime()) {

                Log.v("timeSlots11", "time slots in proper format(if statement) = " + timeSlotFormat.format(slot));

            } else {
                Log.v("timeSlots11", "time slots in proper format(else statement) = " + timeSlotFormat.format(slot));

            }
            dif += (3600000 / 2);

        }

//        Calendar rightNow = Calendar.getInstance();

        Log.v("cURRENTtIME", "time " + System.currentTimeMillis());
        Log.v("cURRENTtIME", "calender time = " + rightNow.getTime());
        Log.v("cURRENTtIME", "calender time(msec) = " + rightNow.getTimeInMillis());
        Log.v("cURRENTtIME", "calender timeZone = " + rightNow.getTimeZone());
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String changedTime = dateFormat.format(rightNow.getTime());

        Log.v("cURRENTtIME", "calender changedTime = " + changedTime);


        return timeSlotArrayList;


    }

    public void showSuccessDialog() {

        new AwesomeSuccessDialog(this)
                .setTitle(" Thank You,Please check Current Appointments for appointment status.")
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
                        Intent i = new Intent(schedule.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    //doctor appointment timings


    //wrapper function

    public void displayTimeSlots(List<DoctorTiming> doctorTimings, String appointmentDate) {

        boolean isAvailable;
        DoctorTiming doctorTime;
        String startTime = "";
        String endTime = "";
        List<String> timeslotArrayList;
        Boolean isCurrentDate = isCurrentDateGlobal(appointmentDate);
        getDayNameGlobal(appointmentDate);
        int dayValue = getDayValue(appointmentDate);
        if (dayValue != 0) {
            doctorTime = doctorTimings.get(getDayPositionGlobal(dayValue));
        } else {
            Log.v("docTimeParcel","Toast : if (dayValue != 0) else body ");

            Toast.makeText(schedule.this, "Doctor not available on this date", Toast.LENGTH_LONG).show();
            return;
        }

        isAvailable = doctorTime.getIsAvailable();
        if (!isAvailable) {
            Log.v("docTimeParcel","Toast :(!isAvailable) ");

            Toast.makeText(schedule.this, "Doctor not available on this date", Toast.LENGTH_LONG).show();
            return;
        } else {
            List<Timing_> timings = doctorTime.getTimings();
            if (timings != null) {

                if (timings.size() != 0) {

                    endTime = ""+getTwelvehourTimeFormat(timings.get(0).getEndTime());
                    startTime = ""+getTwelvehourTimeFormat(timings.get(0).getStartTime());

                    Log.v("timeParcelValue","end time ="+endTime);
                    Log.v("timeParcelValue","startTime isCurrentDate ="+startTime);


//                    if (isCurrentDate) {
//                        startTime = ""+getTwelvehourTimeFormat(getCurrentTimeGlobal());
//                        Log.v("timeParcelValue","startTime isCurrentDate ="+startTime);
//
//                    } else {
//                        startTime = ""+getTwelvehourTimeFormat(timings.get(0).getStartTime());
//                        Log.v("timeParcelValue","startTime else ="+startTime);
//
//                    }
                }

                if(!startTime.equals("") && !startTime.equals("null") && !endTime.equals("") && !endTime.equals("null")){
                    timeslotArrayList = getAllTimingArrayGlobal(isCurrentDate,getCurrentDateGlobal(),startTime,endTime);
                }else {
                    Log.v("docTimeParcel","Toast :if(!startTime.equals(\"\") && !endTime.equals(\"\")) else body ");

                    Toast.makeText(schedule.this, "Doctor not available on this date", Toast.LENGTH_LONG).show();
                    return;
                }

                enableTimeSlotEditText(timeslotArrayList);


            }
        }


    }

    public void enableTimeSlotEditText(List<String> timeSlotArrayList){

        ArrayAdapter<String> timeSlotArrayAdapter;
        timeSlotArrayAdapter = new ArrayAdapter<String>(schedule.this, android.R.layout.select_dialog_singlechoice, timeSlotArrayList);

        selectTimeSlotEditText.setAdapter(timeSlotArrayAdapter);


        selectTimeSlotEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    selectTimeSlotEditText.showDropDown();
                selectTimeSlotEditText.setError(null);

            }
        });

        selectTimeSlotEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectTimeSlotEditText.showDropDown();
                selectTimeSlotEditText.setError(null);

                return false;
            }
        });


    }

    //wrapper function


    //utils
    public boolean isCurrentDateGlobal(String date) {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();
        long currentTimeConst = currentTime.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String currentDate = dateFormat.format(currentTime);

        if (date.equals(currentDate)) {
            return true;
        } else {
            return false;
        }


    }

    public String getDayNameGlobal(String date) {

        Date dateObj = null;
        SimpleDateFormat DATEformat = new SimpleDateFormat("MM/dd/yy");

        Date newDate2 = null;
        SimpleDateFormat DAYformat = new SimpleDateFormat("EEEE");

        try {
            dateObj = DATEformat.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dateObj); // yourdate is an object of type Date
            int dayValue = c.get(Calendar.DAY_OF_WEEK);

            String dayName = DAYformat.format(dateObj);
            String dayNameUpperCase = dayName.toUpperCase();

            Log.v("getDayValueNew", "dayValue =" + dayValue);
            Log.v("getDayValueNew", "dayName =" + dayName);

            return dayNameUpperCase;


        } catch (Exception e) {
            Log.v("tryCatch", "getDayValue e = " + e.getMessage());
        }

        return null;

    }

    public int getDayValue(String date) {

        Date dateObj = null;
        SimpleDateFormat DATEformat = new SimpleDateFormat("MM/dd/yy");

        Date newDate2 = null;
        SimpleDateFormat DAYformat = new SimpleDateFormat("EEEE");
        try {
            dateObj = DATEformat.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dateObj); // yourdate is an object of type Date
            int dayValue = c.get(Calendar.DAY_OF_WEEK);

            String dayName = DAYformat.format(dateObj);

            Log.v("getDayValueNew", "dayValue =" + dayValue);
            Log.v("getDayValueNew", "dayName =" + dayName);

            Log.v("docScheduleTimeSlot", "getDayValue() (try) dayValue = " + dayValue);
            return dayValue;

        } catch (Exception e) {
            Log.v("tryCatch", "getDayValue e = " + e.getMessage());
        }

        Log.v("docScheduleTimeSlot", "getDayValue() (Error) dayValue = " + 0);

        return 0;

    }

    public int getDayPositionGlobal(int dayValue) {
        if (dayValue != 1) {
            return dayValue - 2;
        }
        return 6;

    }

    public ArrayList<String> getAllTimingArrayGlobal(boolean isCurrentDate,String date, String startTime, String endTime) {


        try {
            ArrayList<String> timeSlotArrayList = new ArrayList<>();
            Calendar rightNow = Calendar.getInstance();
            Date currentTime = rightNow.getTime();
            long currentTimeConst = currentTime.getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            String changedTime = dateFormat.format(currentTime);


            String date1 = date;//current date
            String date2 = date;//current date

//        String time1 = "09:00 AM";// start time or current time
//        String time2 = "10:00 PM";// end time

            String time1 = startTime;// start time or current time
            String time2 = endTime;// end time


            String format = "MM/dd/yy hh:mm a";
            String timeSlot = "hh:mm a";

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            SimpleDateFormat timeSlotFormat = new SimpleDateFormat(timeSlot);

            Date startTimedateObj1 = null;
            try {
//            dateObj1 = sdf.parse(date1 + " " + time1);
                startTimedateObj1 = sdf.parse(changedTime + " " + time1);
                Log.v("timeSlots", "date obj 1 =" + startTimedateObj1);

            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("timeSlots", "exception 1=" + e.getMessage());

            }


            Date endTimedateObj2 = null;
            try {
//            dateObj2 = sdf.parse(date2 + " " + time2);

                endTimedateObj2 = sdf.parse(changedTime + " " + time2);
                Log.v("timeSlots", "date obj 2 =" + endTimedateObj2);


            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("timeSlots", "exception 2=" + e.getMessage());

            }
            System.out.println("Date Start: " + startTimedateObj1);
            System.out.println("Date End: " + endTimedateObj2);

//Date d = new Date(dateObj1.getTime() + 3600000);


            long dif = startTimedateObj1.getTime();
            while (dif < endTimedateObj2.getTime()) {
                Date slot = new Date(dif);
                Log.v("timeSlots", "time slots = " + slot);


                if(!isCurrentDate){
                    timeSlotArrayList.add(timeSlotFormat.format(slot));
                    Log.v("timeSlots1123", "if(!isCurrentDate) = " + timeSlotFormat.format(slot));
                }
                //    Log.v("timeSlots11", "time slot array list " + timeSlotArrayList.get(timeSlotArrayList.size() - 1));


                if (currentTimeConst < slot.getTime()) {
                    if(isCurrentDate) {
                        timeSlotArrayList.add(timeSlotFormat.format(slot));
                    }
                    Log.v("timeSlots1123", "time slots in proper format(if statement) = " + timeSlotFormat.format(slot));

                } else {
                    if(isCurrentDate) {
                        Log.v("timeSlots1123", "time slots in proper format(else statement) = " + timeSlotFormat.format(slot));

                    }


                }
                dif += (3600000 / 2);

            }

//        Calendar rightNow = Calendar.getInstance();

            Log.v("cURRENTtIME", "time " + System.currentTimeMillis());
            Log.v("cURRENTtIME", "calender time = " + rightNow.getTime());
            Log.v("cURRENTtIME", "calender time(msec) = " + rightNow.getTimeInMillis());
            Log.v("cURRENTtIME", "calender timeZone = " + rightNow.getTimeZone());


            Log.v("cURRENTtIME", "calender changedTime = " + changedTime);


            return timeSlotArrayList;

        } catch (Exception e) {
            Log.v("tryCatch", "getAllTimeslotsGlobal Exception e =" + e.getMessage());
            Log.v("tryCatch", "getAllTimeslotsGlobal Exception e =" + e.getLocalizedMessage());
            Log.v("tryCatch", "getAllTimeslotsGlobal Exception e =" + e.getStackTrace());

            return new ArrayList<String>();

        }


    }

    public String getTwelvehourTimeFormat(String realTime) {

        String str;
        try {

            String time = realTime;

//            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            String inputPattern = "kk:mm";

            String outputPattern = "hh:mm a";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
                Log.v("new_time1", "try(time)date = " + date + "str = " + str);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("new_time1", "|(time)exception = " + e.getMessage());
                Log.v("new_time1", "|(time)exception =" + e.getStackTrace());
                Log.v("new_time1", "|(time)exception =" + e.getLocalizedMessage());


            }

        } catch (Exception e) {
            Log.v("new_time1", "|(time)exception main =" + e.getMessage());
            Log.v("new_time1", "|(time)exception main =" + e.getStackTrace());
            Log.v("new_time1", "|(time)exception main =" + e.getLocalizedMessage());


            str = "";
            return str;
        }


        Log.v("new_time1", "(time)str = " + str);
        return str;


    }

    public String getCurrentDateGlobal() {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();

        long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String currentDate = dateFormat.format(currentTime);

        Log.v("getCurrentDate", "getFormattedDateTime =" + currentDate);

        return currentDate;
    }

    public String getCurrentTimeGlobal() {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();

        //  long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        String changedTime = dateFormat.format(currentTime);

        Log.v("getFormattedTimeGlobal", "getFormattedDateTime =" + changedTime);

        return changedTime;
    }
    //utils









    //doctor appointment timings




}
