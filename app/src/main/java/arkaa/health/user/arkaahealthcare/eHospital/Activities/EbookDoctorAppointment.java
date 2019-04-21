package arkaa.health.user.arkaahealthcare.eHospital.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import arkaa.health.user.arkaahealthcare.Doctor.Activity.schedule;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.appointment.BookAnAppointmentPost;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Lab.Activity.Lab_Appointment_Booking;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.BookDoctorAppointment.EbookDoctorAppointmentPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.BookDoctorAppointment.EbookDoctorAppointmentPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDocScheduleModalClass.Datum;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.GetDoctorScheduleResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.ElistOfDocPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;

import java.security.interfaces.DSAKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EbookDoctorAppointment extends AppCompatActivity {
    @BindView(R.id.e_select_date)
    EditText eSelectDateEditText;
//    @BindView(R.id.e_time_slot)
//    AutoCompleteTextView eSelectTimeSlotEditText;

    AutoCompleteTextView eSelectTimeSlotEditText;
    AutoCompleteTextView eAppointmentTypeEditText;


    @BindView(R.id.e_patients_name)
    EditText eSelectpatientNameEditText;
    @BindView(R.id.e_add_notes_to_doc)
    EditText eAddNotesToDocEditText;
    @BindView(R.id.submit123)
    Button submitButton;

    @BindView(R.id.e_patients_mobile)
    EditText ePatientMobNoEditText;

    @BindView(R.id.e_patient_email)
    EditText ePatientEmailEditText;

//    @BindView(R.id.e_appointment_type)
//    EditText eAppointmentTypeEditText;


    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

    private String selectDate;
    private String selectTimeSlot;
    private String patientName;
    private String notesToDoc;
    private String doctorId;
    private String patientUniqueId;

    private String patientMobileNo;
    private String patientEmail;
    private String appointmentType;

    private String appointmentTypeIntent;
    private List<arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.Datum> doctorstimingArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edoctor_appointment);

        ButterKnife.bind(this);

        doctorId = getIntent().getStringExtra("DOCTOR_ID");
        patientUniqueId = getIntent().getStringExtra("PATIENT_ID");
        appointmentTypeIntent = getIntent().getStringExtra("APPOINTMENT_TYPE");
        doctorstimingArrayList = getIntent().getParcelableArrayListExtra("DOCTORS_TIMING");

        if (doctorstimingArrayList == null) {
            Toast.makeText(EbookDoctorAppointment.this, "Doctor Not Available", Toast.LENGTH_SHORT).show();
            finish();
        }


        eSelectTimeSlotEditText = findViewById(R.id.e_time_slot);
        eAppointmentTypeEditText = findViewById(R.id.e_appointment_type);


        //calender2
        eSelectDateEditText.setFocusable(false);
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


        eSelectDateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                eSelectDateEditText.setError(null);
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(EbookDoctorAppointment.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();
            }
        });


        //calender2


        //timeslots

        //final AutoCompleteTextView acTextView1 = (AutoCompleteTextView) findViewById(R.id.e_time_slot);
        eSelectTimeSlotEditText.setFocusable(false);
        //Set the number of characters the user must type before the drop down list is shown
        eSelectTimeSlotEditText.setThreshold(0);





        //timeslots

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    postData();
                }
            }
        });


        //Appointment Type


        String[] languages2 = {"Walk-In", "Video Consult", "Text Consult", "Phone Consult"};
//        String[] languages2 = {"Walk-In"};

        ArrayAdapter<String> adapter2;

        //Create Array Adapter

        adapter2 = new ArrayAdapter<String>(EbookDoctorAppointment.this, android.R.layout.select_dialog_singlechoice, languages2);


        eAppointmentTypeEditText.setFocusable(false);
        //Set the number of characters the user must type before the drop down list is shown
        eAppointmentTypeEditText.setThreshold(0);
        //Set the adapter
        eAppointmentTypeEditText.setAdapter(adapter2);

        eAppointmentTypeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    eAppointmentTypeEditText.showDropDown();
                eAppointmentTypeEditText.setError(null);

            }
        });

        eAppointmentTypeEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                eAppointmentTypeEditText.showDropDown();
                eAppointmentTypeEditText.setError(null);

                return false;
            }
        });


//Appointment Type


    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String date = sdf.format(myCalendar.getTime());

        eSelectDateEditText.setText(date);

        try {
            Log.v("docScheduleTimeSlot","date ="+date);
            eSelectTimeSlotEditText.setEnabled(true);

            checkDoctorTimings(date);
        } catch (Exception e) {
            Toast.makeText(EbookDoctorAppointment.this, "Doctor Not Available", Toast.LENGTH_SHORT).show();
            Log.v("tryCatch", "EbookDoctorAppointment updatelabel exception e = " + e.getMessage());
            Log.v("tryCatch", "EbookDoctorAppointment updatelabel exception e = " + e.getStackTrace());
            finish();
        }
    }

    public ArrayList<String> getTimingArray() {

        ArrayList<String> timeSlots = new ArrayList<>();
        timeSlots.add("10:00 AM - 12:00 PM");
        timeSlots.add("12:00 AM - 02:00 PM");
        timeSlots.add("02:00 PM - 04:00 PM");
        timeSlots.add("04:00 PM - 06:00 PM");
        timeSlots.add("06:00 PM - 08:00 PM");
        timeSlots.add("08:00 PM - 10:00 PM");
        timeSlots.add("10:00 PM - 12:00 PM");


        return timeSlots;
    }

    public EbookDoctorAppointmentPost getAppointmentObj() {

        EbookDoctorAppointmentPost ebookDoctorAppointmentPost = new EbookDoctorAppointmentPost();

        selectDate = eSelectDateEditText.getText().toString();
        selectTimeSlot = eSelectTimeSlotEditText.getText().toString();
        patientName = eSelectpatientNameEditText.getText().toString();
        notesToDoc = eAddNotesToDocEditText.getText().toString();

        patientMobileNo = ePatientMobNoEditText.getText().toString();
        patientEmail = ePatientEmailEditText.getText().toString();
        appointmentType = appointmentTypeIntent;


        ebookDoctorAppointmentPost.setDoctorId(doctorId);
        ebookDoctorAppointmentPost.setPatientId(patientUniqueId);

        ebookDoctorAppointmentPost.setAppointmentDate(selectDate);
        ebookDoctorAppointmentPost.setAppointmentTime(selectTimeSlot);
        ebookDoctorAppointmentPost.setPatientName(patientName);
        ebookDoctorAppointmentPost.setDescription(notesToDoc);

        ebookDoctorAppointmentPost.setMobileNumber(patientMobileNo);
        ebookDoctorAppointmentPost.setEmailId(patientEmail);
        ebookDoctorAppointmentPost.setAppointmentIdType(appointmentType);


        return ebookDoctorAppointmentPost;


    }

    public boolean validate() {
        boolean valid = true;

        String name = eSelectpatientNameEditText.getText().toString();
        String date = eSelectDateEditText.getText().toString();
        String time = eSelectTimeSlotEditText.getText().toString();

        String mobileNo = ePatientMobNoEditText.getText().toString();
        String email = ePatientEmailEditText.getText().toString();
        // String appointmentType = eAppointmentTypeEditText.getText().toString();


        if (name.isEmpty()) {
            eSelectpatientNameEditText.setError("Please enter user name.");
            valid = false;
        } else {
            eSelectpatientNameEditText.setError(null);
        }


        if (date.isEmpty()) {
            eSelectDateEditText.setError("Please select appointment date.");
            valid = false;
        } else {
            eSelectDateEditText.setError(null);
        }

        if (time.isEmpty()) {
            eSelectTimeSlotEditText.setError("Please select time slot.");
            valid = false;
        } else {
            eSelectTimeSlotEditText.setError(null);
        }

        if (mobileNo.isEmpty()) {
            ePatientMobNoEditText.setError("Please enter Mobile Number.");
            valid = false;
        } else {
            ePatientMobNoEditText.setError(null);
        }

        if (email.isEmpty()) {
            ePatientEmailEditText.setError("Please Enter Patient Email Id.");
            valid = false;
        } else {
            ePatientEmailEditText.setError(null);
        }

//        if (appointmentType.isEmpty()) {
//            eAppointmentTypeEditText.setError("Please Select Appointment Type.");
//            valid = false;
//        } else {
//            eAppointmentTypeEditText.setError(null);
//        }

        if (appointmentTypeIntent == null) {
            valid = false;
        }


        return valid;
    }

    public void postData() {


        //post


        String url = "http://35.163.147.45/api/HospitalMaster/SetAppointment";


        Call<EbookDoctorAppointmentPostResponse> postList = eHospitalApi.getService().bookDocAppointmentApi(url, getAppointmentObj());


        postList.enqueue(new Callback<EbookDoctorAppointmentPostResponse>() {
            @Override
            public void onResponse(Call<EbookDoctorAppointmentPostResponse> call, Response<EbookDoctorAppointmentPostResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                EbookDoctorAppointmentPostResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());
                Log.v("SignUp", "(post)time  = " + postList1.getMessage());



                if (postList1 != null) {

                    if (postList1.getMessage().equals("success") && !postList1.getError()) {
                        //Toast.makeText(EbookDoctorAppointment.this, "Thank You", Toast.LENGTH_SHORT).show();
                        showSuccessDialog();
                        Log.v("transaction1", "transaction id = " + postList1.getTransactionId());
                    }


                }


            }

            @Override
            public void onFailure(Call<EbookDoctorAppointmentPostResponse> call, Throwable t) {

                Toast.makeText(EbookDoctorAppointment.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("transaction1", "Error = " + t.getMessage());
                Log.v("transaction1", "Error = " + t.getStackTrace());


            }
        });


        //post


    }

    public List<String> getDocSchedule(int day, List<Datum> allDocSchedule) {

        List<String> listOfTimeSlots = new ArrayList<>();

        Datum timeSlots = allDocSchedule.get(day);
        if (timeSlots == null) {
            return null;
        }

        if (timeSlots.getTime1() != null) {
            listOfTimeSlots.add(timeSlots.getTime1());
        }

        if (timeSlots.getTime2() != null) {
            listOfTimeSlots.add(timeSlots.getTime2());
        }

        if (timeSlots.getTime3() != null) {
            listOfTimeSlots.add(timeSlots.getTime3());
        }

        if (timeSlots.getTime4() != null) {
            listOfTimeSlots.add(timeSlots.getTime4());
        }

        if (timeSlots.getTime5() != null) {
            listOfTimeSlots.add(timeSlots.getTime5());
        }

        if (timeSlots.getTime6() != null) {
            listOfTimeSlots.add(timeSlots.getTime6());
        }

        if (timeSlots.getTime7() != null) {
            listOfTimeSlots.add(timeSlots.getTime7());
        }

        return listOfTimeSlots;


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
                .setTitle(" Thank You,Please check Current Appointments(E-hospital) for appointment status.")
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
                        Intent i = new Intent(EbookDoctorAppointment.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public void displayTimeSlots(ArrayList<String> listOfTimeSlots){

        String[] languages1 = {"9:30 AM", "10:30 AM", "11:00 AM", "4:00 PM", "5:30 PM", "6:30 PM", "7:30 PM"};
        ArrayList<String> timeSlotArrayList = listOfTimeSlots;

        ArrayAdapter<String> adapter1;

        //Create Array Adapter
        if (timeSlotArrayList == null) {
            adapter1 = new ArrayAdapter<String>(EbookDoctorAppointment.this, android.R.layout.select_dialog_singlechoice, languages1);
        } else {
            adapter1 = new ArrayAdapter<String>(EbookDoctorAppointment.this, android.R.layout.select_dialog_singlechoice, timeSlotArrayList);

        }


        //Find TextView control

        //Set the adapter
        eSelectTimeSlotEditText.setAdapter(adapter1);

        eSelectTimeSlotEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    eSelectTimeSlotEditText.showDropDown();
                eSelectTimeSlotEditText.setError(null);

            }
        });

        eSelectTimeSlotEditText.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                eSelectTimeSlotEditText.showDropDown();
                eSelectTimeSlotEditText.setError(null);
                Log.v("timeSlt", "ontouch");

                return false;
            }
        });



    }

    public void checkDoctorTimings(String date) {

        List<String> timeSlotsArrayList = new ArrayList<>();

        int dayValue = getDayValue(date);
        String dayName = getDayName(date);

        if(dayValue !=0 && dayName !=null  ) {


            int dayPostion = getDayPosition(dayValue);


            if (isCurrentDate(date)) {
                timeSlotsArrayList = timeSlotarrayList(dayPostion, dayName);
            } else {
                timeSlotsArrayList = timeSlotarrayListAll(dayPostion, dayName);
            }

            if (timeSlotsArrayList.size() == 0) {
                Toast.makeText(EbookDoctorAppointment.this, "Doctor is not available on this date.", Toast.LENGTH_SHORT).show();
                eSelectTimeSlotEditText.setEnabled(false);
            } else {
                displayTimeSlots((ArrayList<String>) timeSlotsArrayList);

            }

        }else {
            Toast.makeText(EbookDoctorAppointment.this,"Doctor not available",Toast.LENGTH_SHORT).show();
            finish();
        }

    }


    //E-hospital


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

            Log.v("docScheduleTimeSlot","getDayValue() (try) dayValue = "+dayValue);
            return dayValue;

        } catch (Exception e) {
            Log.v("tryCatch", "getDayValue e = " + e.getMessage());
        }

        Log.v("docScheduleTimeSlot","getDayValue() (Error) dayValue = "+0);

        return 0;

    }

    public String getDayName(String date) {

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
            Log.v("docScheduleTimeSlot","getDayName() (try) dayName = "+dayNameUpperCase);


            return dayNameUpperCase;


        } catch (Exception e) {
            Log.v("tryCatch", "getDayValue e = " + e.getMessage());
        }

        Log.v("docScheduleTimeSlot","getDayName() (Error) dayName = "+null);



        return null;

    }

    public int getDayPosition(int dayValue) {
        if (dayValue != 1) {
            Log.v("docScheduleTimeSlot","getDayPosition() (if) dayValue = "+(dayValue - 2));

            return dayValue - 2;
        }
        Log.v("docScheduleTimeSlot","getDayPosition () (else) dayValue = "+6);

        return 6;

    }

    public long getTimeInMilliSeconds(String formattedDate) {

        String format = "MM/dd/yy hh:mm a";
        Date dateObj1 = null;

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            dateObj1 = sdf.parse(formattedDate);
        } catch (Exception e) {
            Log.v("tryCatch", "getTimeInMilliSeconds" + e.getLocalizedMessage());
        }

        Log.v("getFormattedDateTime", "getTimeInMilliSeconds =" + dateObj1.getTime());

        return dateObj1.getTime();


    }

    public boolean isCurrentDate(String date) {
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

    public long getFormattedCurrentDateTime() {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();

        long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm a");
        String changedTime = dateFormat.format(currentTime);

        Log.v("getFormattedDateTime", "getFormattedDateTime =" + changedTime);

        return currentTimeConst;
    }

    public String getCurrentDate() {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();

        long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String currentDate = dateFormat.format(currentTime);

        Log.v("getCurrentDate", "getFormattedDateTime =" + currentDate);

        return currentDate;
    }

    public long getFormattedDateTime(String dateTime) {


        Date dateObj = null;
        SimpleDateFormat DATEformat = new SimpleDateFormat("MM/dd/yy hh:mm a");
        long timeInMs;


        try {
            dateObj = DATEformat.parse(dateTime);
            timeInMs = dateObj.getTime();
            Log.v("getFormattedDateTime", "getFormattedDateTime with argument =" + timeInMs);
            return timeInMs;
        } catch (Exception e) {
            Log.v("getFormattedDateTime", "getFormattedDateTime with argument Exception e =" + e.getMessage());

        }
        return 0;
    }

    public List<String> timeSlotarrayList(int position, String dayName) {

        List<String> timeSlotArrayList = new ArrayList<>();
        GetDoctorScheduleResponse getDoctorScheduleResponse = new GetDoctorScheduleResponse();
        long ct = getFormattedCurrentDateTime();

        //Time1

        if (((doctorstimingArrayList.get(position).getDay()).equals(dayName)) && !((doctorstimingArrayList.get(position).getTime1()).equals("")) && (ct < (getFormattedDateTime(getCurrentDate() + " 10:00 AM")))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime1());

        }

        //Time1


        //Time2

        if (((doctorstimingArrayList.get(position).getDay()).equals(dayName)) && !((doctorstimingArrayList.get(position).getTime2()).equals("")) && (ct < (getFormattedDateTime(getCurrentDate() + " 12:00 PM")))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime2());

        }

        //Time2

        //Time3

        if (((doctorstimingArrayList.get(position).getDay()).equals(dayName)) && !((doctorstimingArrayList.get(position).getTime3()).equals("")) && (ct < (getFormattedDateTime(getCurrentDate() + " 02:00 PM")))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime3());

        }

        //Time3


        //Time4

        if (((doctorstimingArrayList.get(position).getDay()).equals(dayName)) && !((doctorstimingArrayList.get(position).getTime4()).equals("")) && (ct < (getFormattedDateTime(getCurrentDate() + " 04:00 PM")))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime4());

        }

        //Time4

        //Time5

        if (((doctorstimingArrayList.get(position).getDay()).equals(dayName)) && !((doctorstimingArrayList.get(position).getTime5()).equals("")) && (ct < (getFormattedDateTime(getCurrentDate() + " 06:00 PM")))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime5());

        }

        //Time5

        //Time6

        if (((doctorstimingArrayList.get(position).getDay()).equals(dayName)) && !((doctorstimingArrayList.get(position).getTime6()).equals("")) && (ct <= (getFormattedDateTime(getCurrentDate() + " 08:00 PM")))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime6());

        }

        //Time6

        //Time7

        if (((doctorstimingArrayList.get(position).getDay()).equals(dayName)) && !((doctorstimingArrayList.get(position).getTime7()).equals("")) && (ct <= (getFormattedDateTime(getCurrentDate() + " 10:00 PM")))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime7());

        }

        //Time7

        Log.v("docScheduleTimeSlot","timeSlotarrayList() timeSlotArrayList.size() = "+timeSlotArrayList.size());



        return timeSlotArrayList;
    }

    public List<String> timeSlotarrayListAll(int position, String dayName) {

        List<String> timeSlotArrayList = new ArrayList<>();
        GetDoctorScheduleResponse getDoctorScheduleResponse = new GetDoctorScheduleResponse();
        
        long ct = getFormattedCurrentDateTime();

        //Time1

        if (!((doctorstimingArrayList.get(position).getTime1()).equals(""))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime1());

        }

        //Time1


        //Time2

        if (!((doctorstimingArrayList.get(position).getTime2()).equals(""))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime2());

        }

        //Time2

        //Time3

        if (!((doctorstimingArrayList.get(position).getTime3()).equals(""))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime3());

        }

        //Time3

        //Time4

        if (!((doctorstimingArrayList.get(position).getTime4()).equals(""))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime4());

        }

        //Time4

        //Time5

        if (!((doctorstimingArrayList.get(position).getTime5()).equals(""))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime5());

        }

        //Time5

        //Time6

        if (!((doctorstimingArrayList.get(position).getTime6()).equals(""))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime6());

        }

        //Time6

        //Time7

        if (!((doctorstimingArrayList.get(position).getTime7()).equals(""))) {

            timeSlotArrayList.add(doctorstimingArrayList.get(position).getTime7());

        }

        //Time7

        Log.v("docScheduleTimeSlot","timeSlotarrayListAll() timeSlotArrayList.size() = "+timeSlotArrayList.size());



        return timeSlotArrayList;
    }


    //Ehospital


}
