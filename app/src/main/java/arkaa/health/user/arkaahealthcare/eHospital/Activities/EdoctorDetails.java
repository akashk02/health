package arkaa.health.user.arkaahealthcare.eHospital.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.BookDoctorAppointment.EbookDoctorAppointmentPost;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.GetDoctorSchedulePost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.GetDoctorScheduleResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDocCategoryModalClass.ListOfDocCategoriesPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.Datum;

import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EdoctorDetails extends AppCompatActivity {

    @BindView(R.id.e_hospital_name)
    TextView hospitalNameTextView;
    @BindView(R.id.e_hospital_address)
    TextView hospitalAddressTextView;
    @BindView(R.id.e_hospital_pincode)
    TextView hospitalPincodeTextView;
    @BindView(R.id.ehospital_timing_monday)
    TextView hospitalTimMonTextView;
    @BindView(R.id.ehospital_timing_tuesday)
    TextView hospitalTimtuesTextView;
    @BindView(R.id.ehospital_timing_wednesday)
    TextView hospitalTimWedTextView;
    @BindView(R.id.ehospital_timing_thrusday)
    TextView hospitalTimThrusTextView;
    @BindView(R.id.ehospital_timing_friday)
    TextView hospitalTimFriTextView;
    @BindView(R.id.ehospital_timing_saturday)
    TextView hospitalTimSatTextView;
    @BindView(R.id.ehospital_timing_sunday)
    TextView hospitalTimSunTextView;
    @BindView(R.id.ehospital_doc_mob)
    TextView hospitalDocMobNoTextView;
    @BindView(R.id.education_line_1)
    TextView educationLine1TextView;
    @BindView(R.id.education_line_2)
    TextView educationLine2TextView;
    @BindView(R.id.education_line_3)
    TextView educationLine3TextView;
    @BindView(R.id.book_now)
    Button BookWalkInAppointment;
    @BindView(R.id.call)
    Button callButton;
    @BindView(R.id.phone_consult_card_view)
    CardView phoneConsultCardView;
    @BindView(R.id.text_consult_card_view)
    CardView textConsultCardView;
    @BindView(R.id.video_consult_card_view)
    CardView videoConsultCardView;
    @BindView(R.id.text_consult_fees)
    TextView textFeesTextView;
    @BindView(R.id.phone_consult_fees)
    TextView phoneFeesTextView;
    @BindView(R.id.video_consult_fees)
    TextView videoFeesTextView;
    String walkInFees;
    String textFees;
    String videoFees;
    String phoneFees;
    Boolean isVideoAvailable;
    Boolean isWalkInAvailable;
    Boolean isPhoneAvailable;
    Boolean isTextAvailable;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalPincode;


    //online consultation
    private String hospitalTimMon;
    private String hospitalTimTues;
    private String hospitalTimWed;


    //online consultation

    //online consultation fees
    private String hospitalThrus;
    private String hospitalFri;
    private String hospitalTimSat;

    //online consultation fees
    private String hospitalTimSun;
    private String hospitalDocMobNo;
    private String educationLine1;
    private String educationLine2;
    private String educationLine3;
    private Datum doctorDetails;
    private String userUniqueId;
    private String specialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edoctor_details);

        ButterKnife.bind(this);

        doctorDetails = getIntent().getParcelableExtra("DOCTOR_DETAIL");
        hospitalName = getIntent().getStringExtra("HOSPITAL_NAME");
        specialization = getIntent().getStringExtra("DOCTOR_SPECIALIZATION");


        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        userUniqueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");

        if (doctorDetails != null) {

            String doctorId = doctorDetails.getDoctorid();
            if (doctorId != null) {
                postData(doctorId);
            }

            hospitalAddress = "" + doctorDetails.getDoctoraddress();
            hospitalPincode = "" + doctorDetails.getPincode();

            hospitalDocMobNo = "" + doctorDetails.getDoctorphone() + " /" + "" + doctorDetails.getDoctoralternatephone();

            educationLine1 = "" + doctorDetails.getQualification();
            educationLine2 = "" + specialization;
            educationLine3 = "" + doctorDetails.getExperienceinyears() + " years of experince";

            //CONSULTATION FEES

            walkInFees = "" + doctorDetails.getWalkInFees();
            textFees = "" + doctorDetails.getTextFees();
            phoneFees = "" + doctorDetails.getVoiceFees();
            videoFees = "" + doctorDetails.getVideoFees();

            //CONSULTATION FEES

            //CONSULTATION AVAILABILITY

            isPhoneAvailable = doctorDetails.getVoiceConsult();
            isVideoAvailable = doctorDetails.getVideoConsult();
            isWalkInAvailable = doctorDetails.getWalkInConsult();
            isTextAvailable = doctorDetails.getTextConsult();

            String textFeesMess;
            String videoFeesMess;
            String phoneFeesMess;

            if (!isPhoneAvailable) {
                phoneFeesMess = "Doctor Not Available Today";
                phoneConsultCardView.setEnabled(false);
            } else {
                phoneFeesMess = "consultation Fee " + phoneFees;
            }

            if (!isVideoAvailable) {
                videoFeesMess = "Doctor Not Available Today";
                videoConsultCardView.setEnabled(false);

            } else {
                videoFeesMess = "consultation Fee " + videoFees;
            }

            if (!isTextAvailable) {
                textFeesMess = "Doctor Not Available Today";
                textConsultCardView.setEnabled(false);

            } else {
                textFeesMess = "consultation Fee " + textFees;
            }

            textFeesTextView.setText(textFeesMess);
            phoneFeesTextView.setText(phoneFeesMess);
            videoFeesTextView.setText(videoFeesMess);


            //CONSULTATION AVAILABILITY


            hospitalNameTextView.setText(hospitalName);
            hospitalAddressTextView.setText(hospitalAddress);
            hospitalPincodeTextView.setText(hospitalPincode);

            hospitalDocMobNoTextView.setText(hospitalDocMobNo);

            educationLine1TextView.setText(educationLine1);
            educationLine2TextView.setText(educationLine2);
            educationLine3TextView.setText(educationLine3);


//            BookWalkInAppointment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(EdoctorDetails.this, EbookDoctorAppointment.class);
//                    intent.putExtra("DOCTOR_ID", doctorDetails.getDoctorid());
//                    intent.putExtra("PATIENT_ID", userUniqueId);
//                    intent.putExtra("APPOINTMENT_TYPE","Walk-In");
//
//                    startActivity(intent);
//                }
//            });

//            // online consultation
//
//            textConsultCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(EdoctorDetails.this, EbookDoctorAppointment.class);
//                    intent.putExtra("DOCTOR_ID", doctorDetails.getDoctorid());
//                    intent.putExtra("PATIENT_ID", userUniqueId);
//                    intent.putExtra("APPOINTMENT_TYPE","Text Consult");
//
//
//                    startActivity(intent);
//                }
//            });
//
//            phoneConsultCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(EdoctorDetails.this, EbookDoctorAppointment.class);
//                    intent.putExtra("DOCTOR_ID", doctorDetails.getDoctorid());
//                    intent.putExtra("PATIENT_ID", userUniqueId);
//                    intent.putExtra("APPOINTMENT_TYPE","Phone Consult");
//
//
//                    startActivity(intent);
//                }
//            });
//
//            videoConsultCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(EdoctorDetails.this, EbookDoctorAppointment.class);
//                    intent.putExtra("DOCTOR_ID", doctorDetails.getDoctorid());
//                    intent.putExtra("PATIENT_ID", userUniqueId);
//                    intent.putExtra("APPOINTMENT_TYPE","Video Consult");
//
//
//                    startActivity(intent);
//                }
//            });
//
//
//            // online consultation


            final String hospitalContactNo = "" + doctorDetails.getDoctorphone();

            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + hospitalContactNo));
                    startActivity(intent);
                }
            });

//            phoneConsultCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + hospitalContactNo));
//                    startActivity(intent);
//                }
//            });


        }


    }

    public void postData(String doctorId) {

        GetDoctorSchedulePost getDoctorSchedulePost = new GetDoctorSchedulePost();
        getDoctorSchedulePost.setDoctorId(doctorId);

        //post


        String url = "http://35.163.147.45/api/HospitalMaster/GetDrSchedule";

        Call<GetDoctorScheduleResponse> postList = eHospitalApi.getService().getDocScheduleApi(url, getDoctorSchedulePost);


        postList.enqueue(new Callback<GetDoctorScheduleResponse>() {
            @Override
            public void onResponse(Call<GetDoctorScheduleResponse> call, Response<GetDoctorScheduleResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                GetDoctorScheduleResponse postList1 = response.body();
                Log.v("EdocTime", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());


                if (postList1 != null) {

                    Log.v("EdocTime", "postList1 != null");


                    if (postList1.getMessage().equals("success") && postList1.getError() == false) {

                        Log.v("EdocTime", "(post)schedule = success");


                        //doctorTiming

                        final ArrayList<arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.Datum> doctorstiming = (ArrayList<arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.Datum>) postList1.getData();
                        displayAllDoctorTimings(getCurrentDate(),postList1);

                        try {
                            //displayAllDoctorTimings(getCurrentDate());
                        }catch (Exception e){
                            Log.v("tryCatch","EdoctorDetails Exception e ="+e.getMessage());
                            Log.v("tryCatch","EdoctorDetails Exception e ="+e.getStackTrace());
                            Log.v("tryCatch","EdoctorDetails Exception e ="+e.getClass());
                            Toast.makeText(EdoctorDetails.this,"Doctor Not Available Today",Toast.LENGTH_SHORT).show();

                        }

                        //doctorTiming


                        // online consultation


                        BookWalkInAppointment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(EdoctorDetails.this, EbookDoctorAppointment.class);
                                intent.putExtra("DOCTOR_ID", doctorDetails.getDoctorid());
                                intent.putExtra("PATIENT_ID", userUniqueId);
                                intent.putExtra("APPOINTMENT_TYPE", "Walk-In");
                                intent.putExtra("DOCTORS_TIMING", doctorstiming);

                                startActivity(intent);
                            }
                        });


                        textConsultCardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(EdoctorDetails.this, EbookDoctorAppointment.class);
                                intent.putExtra("DOCTOR_ID", doctorDetails.getDoctorid());
                                intent.putExtra("PATIENT_ID", userUniqueId);
                                intent.putExtra("APPOINTMENT_TYPE", "Text Consult");
                                intent.putExtra("DOCTORS_TIMING", doctorstiming);


                                startActivity(intent);
                            }
                        });

                        phoneConsultCardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(EdoctorDetails.this, EbookDoctorAppointment.class);
                                intent.putExtra("DOCTOR_ID", doctorDetails.getDoctorid());
                                intent.putExtra("PATIENT_ID", userUniqueId);
                                intent.putExtra("APPOINTMENT_TYPE", "Phone Consult");
                                intent.putExtra("DOCTORS_TIMING", doctorstiming);


                                startActivity(intent);
                            }
                        });

                        videoConsultCardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(EdoctorDetails.this, EbookDoctorAppointment.class);
                                intent.putExtra("DOCTOR_ID", doctorDetails.getDoctorid());
                                intent.putExtra("PATIENT_ID", userUniqueId);
                                intent.putExtra("APPOINTMENT_TYPE", "Video Consult");
                                intent.putExtra("DOCTORS_TIMING", doctorstiming);


                                startActivity(intent);
                            }
                        });


                        // online consultation


                    }


                }


            }

            @Override
            public void onFailure(Call<GetDoctorScheduleResponse> call, Throwable t) {

                Toast.makeText(EdoctorDetails.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


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

            Log.v("docScheduleTimeSlot", "getDayValue() (try) dayValue = " + dayValue);
            return dayValue;

        } catch (Exception e) {
            Log.v("tryCatch", "getDayValue e = " + e.getMessage());
        }

        Log.v("docScheduleTimeSlot", "getDayValue() (Error) dayValue = " + 0);

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
            Log.v("docScheduleTimeSlot", "getDayName() (try) dayName = " + dayNameUpperCase);


            return dayNameUpperCase;


        } catch (Exception e) {
            Log.v("tryCatch", "getDayValue e = " + e.getMessage());
        }

        Log.v("docScheduleTimeSlot", "getDayName() (Error) dayName = " + null);


        return null;

    }

    public int getDayPosition(int dayValue) {
        if (dayValue != 1) {
            Log.v("docScheduleTimeSlot", "getDayPosition() (if) dayValue = " + (dayValue - 2));

            return dayValue - 2;
        }
        Log.v("docScheduleTimeSlot", "getDayPosition () (else) dayValue = " + 6);

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

    public List<String> displayAlltimeSlots(int position, String dayName, GetDoctorScheduleResponse getDoctorScheduleResponse) {

        int counter = 0;

        List<String> timeSlotArrayList = new ArrayList<>();
       // GetDoctorScheduleResponse getDoctorScheduleResponse = new GetDoctorScheduleResponse();
        long ct = getFormattedCurrentDateTime();

        //Time1

        if (!((getDoctorScheduleResponse.getData().get(position).getTime1()).equals(""))) {

            timeSlotArrayList.add(getDoctorScheduleResponse.getData().get(position).getTime1());
            counter++;

        } else {
            timeSlotArrayList.add("");
        }

        //Time1


        //Time2

        if (!((getDoctorScheduleResponse.getData().get(position).getTime2()).equals(""))) {

            timeSlotArrayList.add(getDoctorScheduleResponse.getData().get(position).getTime2());
            counter++;

        } else {
            timeSlotArrayList.add("");
        }

        //Time2

        //Time3

        if (!((getDoctorScheduleResponse.getData().get(position).getTime3()).equals(""))) {

            timeSlotArrayList.add(getDoctorScheduleResponse.getData().get(position).getTime3());
            counter++;

        } else {
            timeSlotArrayList.add("");
        }

        //Time3

        //Time4

        if (!((getDoctorScheduleResponse.getData().get(position).getTime4()).equals(""))) {

            timeSlotArrayList.add(getDoctorScheduleResponse.getData().get(position).getTime4());
            counter++;

        } else {
            timeSlotArrayList.add("");
        }

        //Time4

        //Time5

        if (!((getDoctorScheduleResponse.getData().get(position).getTime5()).equals(""))) {

            timeSlotArrayList.add(getDoctorScheduleResponse.getData().get(position).getTime5());
            counter++;

        } else {
            timeSlotArrayList.add("");
        }

        //Time5

        //Time6

        if (!((getDoctorScheduleResponse.getData().get(position).getTime6()).equals(""))) {

            timeSlotArrayList.add(getDoctorScheduleResponse.getData().get(position).getTime6());
            counter++;

        } else {
            timeSlotArrayList.add("");
        }

        //Time6

        //Time7

        if (!((getDoctorScheduleResponse.getData().get(position).getTime7()).equals(""))) {

            timeSlotArrayList.add(getDoctorScheduleResponse.getData().get(position).getTime7());
            counter++;

        } else {
            timeSlotArrayList.add("");
        }

        //Time7

        if (counter == 0) {
            return new ArrayList<String>();
        }

        return timeSlotArrayList;
    }

    public void displayAllDoctorTimings(String date,GetDoctorScheduleResponse getDoctorScheduleResponse) {

        List<String> timeSlotsArrayList = new ArrayList<>();

        int dayValue = getDayValue(date);
        String dayName = getDayName(date);

        if (dayValue != 0 && dayName != null) {


            int dayPostion = getDayPosition(dayValue);


            timeSlotsArrayList = displayAlltimeSlots(dayPostion, dayName, getDoctorScheduleResponse);

            if (timeSlotsArrayList.size() == 0) {
//                Toast.makeText(EdoctorDetails.this, "Doctor is not available on this date.", Toast.LENGTH_SHORT).show();
                hospitalTimMonTextView.setText("Doctor Not Available Today.");
                hospitalTimtuesTextView.setVisibility(View.GONE);
                hospitalTimWedTextView.setVisibility(View.GONE);
                hospitalTimThrusTextView.setVisibility(View.GONE);
                hospitalTimFriTextView.setVisibility(View.GONE);
                hospitalTimSatTextView.setVisibility(View.GONE);
                hospitalTimSunTextView.setVisibility(View.GONE);


            } else {

                // display timing

                if (timeSlotsArrayList.get(0).equals("")) {
                    hospitalTimMonTextView.setVisibility(View.GONE);
                } else {
                    hospitalTimMonTextView.setText("" + timeSlotsArrayList.get(0));
                }

                if (timeSlotsArrayList.get(1).equals("")) {
                    hospitalTimtuesTextView.setVisibility(View.GONE);
                } else {
                    hospitalTimtuesTextView.setText("" + timeSlotsArrayList.get(1));
                }



                if (timeSlotsArrayList.get(2).equals("")) {
                    hospitalTimWedTextView.setVisibility(View.GONE);
                } else {
                    hospitalTimWedTextView.setText("" + timeSlotsArrayList.get(2));
                }

                if (timeSlotsArrayList.get(3).equals("")) {
                    hospitalTimThrusTextView.setVisibility(View.GONE);
                } else {
                    hospitalTimThrusTextView.setText("" + timeSlotsArrayList.get(3));
                }

                if (timeSlotsArrayList.get(4).equals("")) {
                    hospitalTimFriTextView.setVisibility(View.GONE);
                } else {
                    hospitalTimFriTextView.setText("" + timeSlotsArrayList.get(4));
                }

                if (timeSlotsArrayList.get(5).equals("")) {
                    hospitalTimSatTextView.setVisibility(View.GONE);
                } else {
                    hospitalTimSatTextView.setText("" + timeSlotsArrayList.get(5));
                }

                if (timeSlotsArrayList.get(6).equals("")) {
                    hospitalTimSunTextView.setVisibility(View.GONE);
                } else {
                    hospitalTimSunTextView.setText("" + timeSlotsArrayList.get(6));
                }



                //display timings
            }

        } else {
            Toast.makeText(EdoctorDetails.this, "Doctor not available", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


    //E-hospital


}
