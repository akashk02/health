package arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.DoctorDetails.Datum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.DoctorDetails.GeneralDocDetailsPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.DoctorDetails.GeneralDocDetailsResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment.EhosChPayStatusPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment.EhosChPayStatusResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi.EhospitalInboxApi;
import arkaa.health.user.arkaahealthcare.FirebaseChat.chatMainActivityBeta;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.R;

import com.bumptech.glide.Glide;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import arkaa.health.user.arkaahealthcare.SinchVideoCall.MainActivityVideo;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EdocAppDetailsPts extends AppCompatActivity implements PaymentResultListener {

    @BindView(arkaa.health.user.arkaahealthcare.R.id.name)
    TextView docNameTextView;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.education)
    TextView docEduTextView;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.specialization)
    TextView docSpecializationTextView;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.location)
    TextView docAddressTextView;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.patient_date)
    TextView docAppDateTextView;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.patient_time)
    TextView docAppTimeTextView;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.e_hospital_name)
    TextView docHospitalNameTextView;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.e_hospital_address)
    TextView docHospitalAddTextView;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.e_hospital_pincode)
    TextView docHospitalPincodeTextVIew;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.ehospital_doc_mob)
    TextView docHospitalContactTextView;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.education_line_1)
    TextView educationLine1TextView;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.education_line_2)
    TextView educationLine2TextView;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.education_line_3)
    TextView educationLine3TextView;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.book_now)
    Button confirmAndPay;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.consultation_fees)
    TextView doctorFeesTextView;

    @BindView(R.id.pay_on_visit)
    Button payOnVisitButton;

    @BindView(R.id.doctor_profile_pic)
    ImageView DocProfilePicIV;

    @BindView(R.id.patient_appointment_type)
    TextView appointmentTypeTv;

    String docAppointmentDateIntent;
    String docAppointmentTimeIntent ;
    String doctorId;


    private String ptsDoctorConsultChId ;
    private Boolean paymentConfirmation;
    private String paymentMode;
    private String ptsId ;

    private  String TAG ;

    private Double doctorFees;
    private String doctorFeesString;
    private String appointmentType;
    private String razorpayAmount;
    private String DocprofilePic;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_edoc_app_details_pts);

        ButterKnife.bind(this);
        Checkout.preload(getApplicationContext());
        TAG = getLocalClassName();
        payOnVisitButton.setVisibility(View.GONE);




        docAppointmentDateIntent  = getIntent().getStringExtra("APPOINTMENT_DATE");
        docAppointmentTimeIntent = getIntent().getStringExtra("APPOINTMENT_TIME");
        ptsDoctorConsultChId = getIntent().getStringExtra("PTS_DOC_CON_CH_ID");
        paymentConfirmation = getIntent().getBooleanExtra("PAYMENT_CONFIRMATION",false);
        paymentMode = getIntent().getStringExtra("PAYMENT_MODE");
        ptsId = getIntent().getStringExtra("PTS_ID");

        doctorFees = (getIntent().getDoubleExtra("DOC_FEES",0.0))*100;
        doctorFeesString = getIntent().getStringExtra("DOC_FEES_STRING");
        appointmentType = getIntent().getStringExtra("APPOINTMENT_TYPE");
        DocprofilePic = getIntent().getStringExtra("DOCTOR_IMAGE_URL");

        try {
            Glide.with(EdocAppDetailsPts.this).load(DocprofilePic).into(DocProfilePicIV);
        } catch (Exception e) {
            Log.v("tryCatch", "ListOfDocAdapter glide Exeption e = " + e.getMessage());
        }


        if (doctorFees == 0.0) {
            razorpayAmount = "";
        } else {
            razorpayAmount = "" + doctorFees;
        }


        if(paymentMode == null){
            paymentMode = "";
        }

//        if(ptsDoctorConsultChId == null){
//            ptsDoctorConsultChId = "";
//            }
        doctorId = getIntent().getStringExtra("DOCTOR_ID");


        //old payment


//        if(paymentConfirmation){
//            confirmAndPay.setEnabled(false);
//            confirmAndPay.setText("Already paid");
//        } else if(paymentMode.equals("COD")){
//            confirmAndPay.setEnabled(false);
//            confirmAndPay.setText("pay on visit");
//        } else {
//            confirmAndPay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(!ptsDoctorConsultChId.equals("")) {
//                        startPayment();
//                    } else {
//                        showSuccessDialog("Servers are down,Please try again later");
//                    }
//
//                }
//            });
//        }


        //old payment

        //new payment

        if (paymentMode.equals("OFFLINE")) {
            if(!paymentConfirmation) {
                confirmAndPay.setEnabled(false);
                confirmAndPay.setText("pay on visit");
            }else {
                confirmAndPay.setEnabled(false);
                confirmAndPay.setText("Already Paid");
            }
        } else if (paymentConfirmation) {
            //start

            if (appointmentType.equals("Video Consult")) {
                confirmAndPay.setText("Video consultation");
                confirmAndPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (EhosCheckAppTiming(docAppointmentDateIntent, docAppointmentTimeIntent)) {
                            Intent intent = new Intent(v.getContext(), MainActivityVideo.class);
                            intent.putExtra("DOCTOR_UNIQUE_ID", doctorId);
                            v.getContext().startActivity(intent);
                        } else {
                            Toast.makeText(EdocAppDetailsPts.this, "Video Consultation will be enabled on " + docAppointmentDateIntent + " at " + docAppointmentTimeIntent, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else if (appointmentType.equals("Text Consult")) {
                confirmAndPay.setText("Text Consultation");
                confirmAndPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (EhosCheckAppTiming(docAppointmentDateIntent, docAppointmentTimeIntent)) {
                            Intent intent = new Intent(v.getContext(), chatMainActivityBeta.class);
                            intent.putExtra("DOCTOR_UNIQUE_ID", doctorId);
                            intent.putExtra("FIREBASE_CHILD_NAME", "ehospital");
                            v.getContext().startActivity(intent);
                        } else {
                            Toast.makeText(EdocAppDetailsPts.this, "Text Consultation will be enabled on " + docAppointmentDateIntent + " at " + docAppointmentTimeIntent, Toast.LENGTH_LONG).show();

                        }
                    }
                });

            } else if (appointmentType.equals("Phone Consult")) {
                confirmAndPay.setText("Phone Consultation");
                confirmAndPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        if (EhosCheckAppTiming(docAppointmentDateIntent, docAppointmentTimeIntent)) {
//                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + doctorPhoneConsultationNo));
//                            startActivity(intent);
//                        } else {
//                            Toast.makeText(EdocAppDetailsPts.this, "Phone Consultation will be enabled on " + docAppointmentDateIntent + " at " + docAppointmentTimeIntent, Toast.LENGTH_LONG).show();
//
//                        }
                    }
                });


            } else {
                confirmAndPay.setEnabled(false);
                confirmAndPay.setText("Already paid");
            }

//end
        } else {
            confirmAndPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // startPayment();
                    if (!razorpayAmount.equals("")) {
                        startPayment(razorpayAmount);
                    } else {
                        showSuccessDialog(" Servers are down, please try again later.");

                    }

                }
            });

            payOnVisitButton.setVisibility(View.VISIBLE);
            payOnVisitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatePaymentStatus("","OFFLINE","false");

                }
            });
        }









        //new payment




        if(doctorId != null)
        postData();

        }

    public void postData() {


        //post



        String url = " http://35.163.147.45/api/HospitalMaster/GetIndividuaDoctorlProfile";

        GeneralDocDetailsPost generalDocDetailsPost = new GeneralDocDetailsPost();
        generalDocDetailsPost.setUserid(doctorId);



        Call<GeneralDocDetailsResponse> postList = EhospitalInboxApi.getService().GeneralDocDetailsApi(url,generalDocDetailsPost);


        postList.enqueue(new Callback<GeneralDocDetailsResponse>() {
            @Override
            public void onResponse(Call<GeneralDocDetailsResponse> call, Response<GeneralDocDetailsResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                GeneralDocDetailsResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1 != null) {


                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    Datum doctorDetail = postList1.getData().get(0);
                    if(doctorDetail != null) {
                        String docName = ""+ doctorDetail.getDoctorname();
                        String docEdu = ""+ doctorDetail.getQualification();
                        String docSpecialization = ""+ doctorDetail.getSpecialization();
                        String docAddress = ""+ doctorDetail.getDoctoraddress();

                        String docAppointmentDate = ""+docAppointmentDateIntent;
                        String docAppointmentTime = ""+docAppointmentTimeIntent;

                        String docHospitalName = "";
                        String docHospitalAdd = ""+doctorDetail.getDoctoraddress();
                        String docHospitalpincode = ""+doctorDetail.getPincode();
                        String docHospitalContactNo = ""+doctorDetail.getDoctorphone();

                        String educationLine1 = ""+doctorDetail.getQualification();
                        String educationLine2 = ""+doctorDetail.getSpecialization();
                        String educationLine3 = ""+doctorDetail.getExperienceinyears()+" years of experince";


                        docNameTextView.setText(docName);
                        docEduTextView.setText(docEdu);
                        docSpecializationTextView.setText(docSpecialization);
                        docAddressTextView.setText(docAddress);

                        docAppDateTextView.setText(docAppointmentDate);
                        docAppTimeTextView.setText(docAppointmentTime);

                        docHospitalAddTextView.setText(docAddress);
                        docHospitalNameTextView.setText(docHospitalName);
                        docHospitalPincodeTextVIew.setText(docHospitalpincode);
                        docHospitalContactTextView.setText(docHospitalContactNo);

                        educationLine1TextView.setText(educationLine1);
                        educationLine2TextView.setText(educationLine2);
                        educationLine3TextView.setText(educationLine3);

                        doctorFeesTextView.setText(""+doctorFeesString);
                        appointmentTypeTv.setText(appointmentType);



                    }


                    }


            }

            @Override
            public void onFailure(Call<GeneralDocDetailsResponse> call, Throwable t) {

                Toast.makeText(EdocAppDetailsPts.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }

    public void startPayment(String razorpayAmount) {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(arkaa.health.user.arkaahealthcare.R.drawable.arkaa_new_logo);


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
            options.put("amount", razorpayAmount);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("paymentGateway", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
       updatePaymentStatus(s,"ONLINE","true");
    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.v("razorpayErrorMessage",TAG+" ="+s);

        showSuccessDialog(" Payment unsuccessful card declined.");

    }

    public void showSuccessDialog(String text) {

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
                .setTitle(text)
                .setMessage("")
                .setColoredCircle(arkaa.health.user.arkaahealthcare.R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(arkaa.health.user.arkaahealthcare.R.drawable.ic_done_black_24dp, arkaa.health.user.arkaahealthcare.R.color.white)
                .setCancelable(true)
                .setPositiveButtonText("OK")
                .setPositiveButtonbackgroundColor(arkaa.health.user.arkaahealthcare.R.color.dialogErrorBackgroundColor)
                .setPositiveButtonTextColor(arkaa.health.user.arkaahealthcare.R.color.white)
                .setPositiveButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        //click
                        Intent i = new Intent(EdocAppDetailsPts.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public String getDate(String realTime) {

        String str ;
        try {

            String time = realTime.substring(0, realTime.indexOf("."));

            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            //   String outputPattern = "dd-MMM-yyyy h:mm a";
            String outputPattern = "dd-MMM-yyyy";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
                Log.v("new_time", "date = " + date + "str = " + str);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("new_time", "exception " + e.getMessage());
            }

        }catch (Exception e){

            str = realTime;

        }



        Log.v("new_time","str = "+str);
        return str;


    }

    public void updatePaymentStatus(String razorpayId, final String paymentMode,String paymentConfirmation) {


        //post



        String url = "http://35.163.147.45/api/HospitalMaster/SetPaymentConfirmation";


        Call<EhosChPayStatusResponse> postList = EhospitalInboxApi.getService().eHosChangePaymentStatus(url,getPaymentObj(razorpayId,ptsDoctorConsultChId,paymentMode,paymentConfirmation));


        postList.enqueue(new Callback<EhosChPayStatusResponse>() {
            @Override
            public void onResponse(Call<EhosChPayStatusResponse> call, Response<EhosChPayStatusResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                EhosChPayStatusResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1 != null) {

                    if(postList1.getMessage().equals("success") && !postList1.getError()){
                        //showSuccessDialog(" Thank You,Payment Successfull.");

                        if(paymentMode.equals("ONLINE")) {
                            showSuccessDialog(" Thank You,Payment Successfull.");
                        } else {
                            showSuccessDialog(" Your appointment has been booked, Please pay on visit");

                        }

                    }else {
                        showSuccessDialog(" Servers are down, Please try again later");
                    }


                    }


            }

            @Override
            public void onFailure(Call<EhosChPayStatusResponse> call, Throwable t) {

                Toast.makeText(EdocAppDetailsPts.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }

    public EhosChPayStatusPost getPaymentObj(String razorpayID,String serviceId,String paymentMode,String paymentConfirmatation){

        EhosChPayStatusPost ehosChPayStatusPost = new EhosChPayStatusPost();

        ehosChPayStatusPost.setPtsId(ptsId);
        ehosChPayStatusPost.setPtsMedicineId("");
        ehosChPayStatusPost.setPtsLabId("");
        ehosChPayStatusPost.setPtsVenderProductsId("");
        ehosChPayStatusPost.setPtsVenderserviceId("");
        ehosChPayStatusPost.setPtsDoctorconsultChId(serviceId);

        ehosChPayStatusPost.setPaymentMode(paymentMode);
        ehosChPayStatusPost.setPaymentconfirmation(paymentConfirmatation);
        ehosChPayStatusPost.setPaymentId(razorpayID);

        return  ehosChPayStatusPost;


    }

    public boolean EhosCheckAppTiming(String date, String timeSlot) {

        String appointmentTime;
        String formattedDateTime;
        long appDateTime;
        long currDateTime;

        if (timeSlot.length() > 8) {
            appointmentTime = timeSlot.substring(0, 8);
            formattedDateTime = date + " " + appointmentTime;

            appDateTime = getFormattedDateTime(formattedDateTime);
            currDateTime = getFormattedCurrentDateTime();

            Log.v("value123", "appointmentTime = " + appointmentTime);
            Log.v("value123", "formattedDateTime = " + formattedDateTime);
            Log.v("value123", "appDateTime = " + appDateTime);
            Log.v("value123", "currDateTime = " + currDateTime);


            if (currDateTime >= appDateTime) {
                Log.v("value123", "appDateTime >= currDateTime   value(true)");

                return true;
            } else if (currDateTime < appDateTime) {
                Log.v("value123", "appDateTime < currDateTime   value(false)");

                return false;
            } else {
                Log.v("value123", "else  value(true)");

                return true;
            }
        } else {
            Log.v("value123", "else parent  value(true)");

            return true;
        }

    }

    public long getFormattedCurrentDateTime() {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();

        long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String changedTime = dateFormat.format(currentTime);

        Log.v("getFormattedDateTime", "getFormattedDateTime =" + changedTime);

        return currentTimeConst;
    }

    public long getFormattedDateTime(String dateTime) {


        Date dateObj = null;
        SimpleDateFormat DATEformat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
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








}
