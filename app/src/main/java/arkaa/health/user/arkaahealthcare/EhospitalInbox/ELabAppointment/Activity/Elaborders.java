package arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.Activity;

import android.app.Activity;
import android.content.Intent;
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
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.Adapter.ElabOrdersAdapter;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.ElabAppointmentPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.ElabAppointmentResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.Griddatum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Activities.EdocAppDetailsPts;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment.EhosChPayStatusPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment.EhosChPayStatusResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi.EhospitalInboxApi;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Datum;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.ElabAppointmentResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.Griddatum;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Datum;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Elaborders extends AppCompatActivity implements PaymentResultListener {

    //lab details

    @BindView(R.id.lab_name)
    TextView labNameTv;

    @BindView(R.id.lab_contact_no)
    TextView labMobileNoTv;

    @BindView(R.id.land_line_no)
    TextView labLandlineNoTv;

    @BindView(R.id.lab_address)
    TextView labAddressTv;

    @BindView(R.id.lab_otomist_name)
    TextView labOtomistNameTv;

    @BindView(R.id.lab_otomist_mobile)
    TextView labOtomistMobileTv;

    @BindView(R.id.lab_otomist_Email)
    TextView labOtomistEmailTv;





    //lab details

    @BindView(arkaa.health.user.arkaahealthcare.R.id.e_date)
    TextView appointmentDateTv;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.e_time)
    TextView getAppointmentTimeTv;

    @BindView(R.id.pay_on_visit)
    Button payOnVisitButton;


    private TextView selectedTestsTextView;
    private TextView totalPriceTextView;
    private Button confirmBookingButton;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View confirmAppointmentCardView;

    private LabBookAppointmentPost labBookAppointmentPost;

    private List<Datum> quotationDetails;
    private List<Griddatum> quotationTestArrayList;
    private String token;
    private arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum listOfQuotationTest;
    private int orderId;
    private String labUniqueId;

    private String hospitalId;
    private String transactionId;
    private String ptsId ;
    private Double razaorPayAmount ;

    private int labPtsId;

    private String paymentConfirmation;
    private String paymentMode ;

    private  String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_elaborders);

        ButterKnife.bind(this);


        payOnVisitButton.setVisibility(View.GONE);


        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        transactionId = getIntent().getStringExtra("TRANSACTION_ID");
        ptsId = getIntent().getStringExtra("PTS_ID");

        Checkout.preload(getApplicationContext());
        TAG = getLocalClassName();



        ButterKnife.bind(this);

        quotationTestArrayList = new ArrayList<>();

        selectedTestsTextView = findViewById(arkaa.health.user.arkaahealthcare.R.id.selected_tests);
        totalPriceTextView = findViewById(arkaa.health.user.arkaahealthcare.R.id.total_price);
        confirmBookingButton = findViewById(arkaa.health.user.arkaahealthcare.R.id.confirm_booking_button);

        selectedTestsTextView.setText("PAYMENT DETAILS");

        confirmAppointmentCardView = findViewById(arkaa.health.user.arkaahealthcare.R.id.confirm_booking_card_view);
//        confirmAppointmentCardView.setVisibility(View.GONE);

        mRecyclerView = (RecyclerView) findViewById(arkaa.health.user.arkaahealthcare.R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ElabOrdersAdapter(quotationTestArrayList);
        mRecyclerView.setAdapter(mAdapter);

        if ((ptsId != null) && (transactionId != null)) {
            try {
                postData();
            } catch (Exception e){
                Toast.makeText(Elaborders.this,"Sorry! no data found",Toast.LENGTH_SHORT).show();
                Log.v("tryCatch","Elaborders Exception e ="+e.getMessage());
                Log.v("tryCatch","Elaborders Exception e ="+e.getStackTrace());
                Log.v("tryCatch","Elaborders Exception e ="+e.getLocalizedMessage());


            }
        }




        }

    public void postData() {

        Log.v("EDocApp","postData");


        ElabAppointmentPost elabAppointmentPost = new ElabAppointmentPost();
//        elabAppointmentPost.setPtsId("000004");
//        elabAppointmentPost.setTransactionId("00000001");

        elabAppointmentPost.setPtsId(ptsId);
        elabAppointmentPost.setTransactionId(transactionId);


        String url = "http://35.163.147.45/api/HospitalMaster/GetPtsLabServices";



        final Call<ElabAppointmentResponse> postList = EhospitalInboxApi.getService().eLabAppointmentApi(url,elabAppointmentPost);


        postList.enqueue(new Callback<ElabAppointmentResponse>() {
            @Override
            public void onResponse(Call<ElabAppointmentResponse> call, Response<ElabAppointmentResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("ElabOrder", "(post)schedule = success");

                ElabAppointmentResponse postList1 = response.body();
                Log.v("ElabOrder", "(post)schedule = status code = " + response.code());
                Log.v("ElabOrder", "(post)time  = " + response.code());



                if (postList1 != null) {


//                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
//                    quotationTestArrayList.addAll(postList1.getData());
//                    mAdapter.notifyItemInserted(quotationTestArrayList.size() - 1);


                        quotationTestArrayList.addAll(postList1.getGriddata());
                        mAdapter.notifyItemInserted(quotationTestArrayList.size() - 1);

                        if(postList1.getGriddata() != null && postList1.getGriddata().size() !=0 && postList1.getData() !=null && postList1.getData().size() != 0  ) {
                            Log.v("Elab","postList1.getGriddata()"+postList1.getGriddata().size());

                            //lab details
                            List<arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.Datum> labDetails = postList1.getData();

                            String labMobileNo = labDetails.get(0).getPtsLabMobile();
                            String labLandlineNo = labDetails.get(0).getPtsLabLandline();
                            String labAddress = labDetails.get(0).getPtsLabAddress();
                            String labOtomistName = labDetails.get(0).getLabotomistName();
                            String labOtomistMobile = labDetails.get(0).getLabotomistMobile();
                            String labOtomistEmail = labDetails.get(0).getLabotomistEmail();

                            labMobileNoTv.setText(""+labMobileNo);
                            labLandlineNoTv.setText(""+labLandlineNo);
                            labAddressTv.setText(""+labAddress);
                            labOtomistNameTv.setText(""+labOtomistName);
                            labOtomistMobileTv.setText(""+labOtomistMobile);
                            labOtomistEmailTv.setText(""+labOtomistEmail);

                            String labName = postList1.getGriddata().get(0).getPtsLabLabName();
                            labNameTv.setText(labName);




                            //lab details


                            String totalPrice = "RS " + postList1.getGriddata().get(0).getTotalFinalPrice()+ " Incl GST";
                            totalPriceTextView.setText(totalPrice);

                            String labDate = "" + postList1.getData().get(0).getPtsLabDate();
                            String labTime = "" + postList1.getData().get(0).getPtsLabDeliveryTime();

                            appointmentDateTv.setText(labDate);
                            getAppointmentTimeTv.setText(labTime);

                            try{
                                razaorPayAmount = postList1.getGriddata().get(0).getTotalFinalPrice()*100;
                            }catch (Exception e){
                                razaorPayAmount = null;
                            }

                            if(razaorPayAmount != null){
                                confirmBookingButton.setEnabled(true);
                            }

                            labPtsId = postList1.getData().get(0).getPtsLabId();
                            final String ptsLabId  ;
                            if(labPtsId == 0){
                                ptsLabId = "";
                            } else {
                                ptsLabId = ""+labPtsId;
                            }

                            final String paymentMode = ""+postList1.getData().get(0).getPaymentMode();
                            Boolean paymentConfirm = postList1.getData().get(0).getPaymentconfirmation();
                            Log.v("paymentConfirm","paymentConfirm = "+paymentConfirm);
                            if(paymentConfirm == null){
                                paymentConfirm = false;
                            }
                            final Boolean paymentConfirmation = paymentConfirm;

                          //  confirmBookingButton.setEnabled(false);
                            if(paymentConfirmation && paymentMode.equals("ONLINE")){
                                Log.v("paymentConfirm","Already paid ");
                                confirmBookingButton.setEnabled(false);
                                confirmBookingButton.setText("Already paid");
                            } else if(paymentMode.equals("OFFLINE")){

                                Log.v("paymentConfirm","OFFLINE");
                                if(!paymentConfirmation) {
                                    confirmBookingButton.setEnabled(false);
                                    confirmBookingButton.setText("pay on visit");
                                }else {
                                    confirmBookingButton.setEnabled(false);
                                    confirmBookingButton.setText("Already Paid");
                                }
                            } else {
                                confirmBookingButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(!(ptsLabId.equals(""))) {
                                            startPayment();
                                        } else {
                                            showSuccessDialog("Servers are down,Please try again later");
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







                        }






                }


            }

            @Override
            public void onFailure(Call<ElabAppointmentResponse> call, Throwable t) {

                Toast.makeText(Elaborders.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("ElabOrder", "(schedule)error in link (get)");
                Log.v("ElabOrder", "error = "+t.getMessage());



            }
        });


        //post


    }

    public void startPayment() {
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
            options.put("amount", ""+razaorPayAmount);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("paymentGateway", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
       // changePaymentStatus();
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
                        Intent i = new Intent(Elaborders.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public void changePaymentStatus() {


        //post
        Log.v("EDocApp","postData");




        String url = "http://35.163.147.45/api/HospitalMaster/SetPaymentConfirmation";

        EhosChPayStatusPost ehosChPayStatusPost = new EhosChPayStatusPost();
        ehosChPayStatusPost.setPaymentconfirmation("true");
        ehosChPayStatusPost.setPaymentMode("online");
        ehosChPayStatusPost.setPtsLabId(""+labPtsId);

        ehosChPayStatusPost.setPtsMedicineId("");
        ehosChPayStatusPost.setPtsVenderProductsId("");
        ehosChPayStatusPost.setPtsVenderserviceId("");


        Call<EhosChPayStatusResponse> postList = EhospitalInboxApi.getService().eHosChangePaymentStatus(url, ehosChPayStatusPost);


        postList.enqueue(new Callback<EhosChPayStatusResponse>() {
            @Override
            public void onResponse(Call<EhosChPayStatusResponse> call, Response<EhosChPayStatusResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                EhosChPayStatusResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1.getMessage().equals("success") && postList1.getError() == false) {


                    showSuccessDialog(" Thank You,Your appointment has been booked.");


                }


            }

            @Override
            public void onFailure(Call<EhosChPayStatusResponse> call, Throwable t) {

                Toast.makeText(Elaborders.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");
                showSuccessDialog(" Sorry,Something went wrong.");



            }
        });


        //post


    }

    public void updatePaymentStatus(String razorpayId, final String paymentMode,String paymentConfirmation) {


        //post



        String url = "http://35.163.147.45/api/HospitalMaster/SetPaymentConfirmation";


        Call<EhosChPayStatusResponse> postList = EhospitalInboxApi.getService().eHosChangePaymentStatus(url,getPaymentObj(razorpayId,""+labPtsId,paymentMode,paymentConfirmation));


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
                      //  showSuccessDialog(" Thank You,Payment Successfull.");

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

                Toast.makeText(Elaborders.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }

    public EhosChPayStatusPost getPaymentObj(String razorpayID,String serviceId,String paymentMode,String paymentConfirmation){

        EhosChPayStatusPost ehosChPayStatusPost = new EhosChPayStatusPost();

        ehosChPayStatusPost.setPtsId(ptsId);
        ehosChPayStatusPost.setPtsMedicineId("");
        ehosChPayStatusPost.setPtsLabId(serviceId);
        ehosChPayStatusPost.setPtsVenderProductsId("");
        ehosChPayStatusPost.setPtsVenderserviceId("");
        ehosChPayStatusPost.setPtsDoctorconsultChId("");

        ehosChPayStatusPost.setPaymentMode(paymentMode);
        ehosChPayStatusPost.setPaymentconfirmation(paymentConfirmation);
        ehosChPayStatusPost.setPaymentId(razorpayID);

        return  ehosChPayStatusPost;


    }







}
