package arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.Activities;

import android.app.Activity;
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
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.Activity.Elaborders;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Activities.EdocAppDetailsPts;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.Adapters.ElistOfMedQuoteAdapter;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.EgetPharmacyQuotationPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.EgetPharmacyQuotationPostResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.Griddatum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment.EhosChPayStatusPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment.EhosChPayStatusResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi.EhospitalInboxApi;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.Datum;
import arkaa.health.user.arkaahealthcare.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.EgetPharmacyQuotationPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.EgetPharmacyQuotationPostResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.Griddatum;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.Datum;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpharmacyQuote extends AppCompatActivity implements PaymentResultListener {

    private TextView totalPriceTextView;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View confirmAppointmentCardView;
    private Button checkOutButton;

    private List<Datum> pharmacyQuatationDetails ;
    private List<Griddatum> listOfQuatationMedicinesArrayList ;
    String token;
    String patientUniqueId;
    Integer orderId;

    private String hospitalId;
    private String transactionId;
    private String ptsId ;

    private Double razaorPayAmount ;
    private int ptsMedicineId;
    private String paymentConfirmation;
    private String paymentMode ;

    private  String TAG;

    //pharmacy details

    @BindView(R.id.pharmay_name)
    TextView pharmacyNameTv;

    @BindView(R.id.pharmacy_contact_no)
    TextView pharmacyContactNoTv;

    @BindView(R.id.pharmacy_address)
    TextView pharmacyAddressTv;

    @BindView(R.id.pharmacy_date)
    TextView pharmacyDateTv;

    @BindView(R.id.pharmacy_delivery_time)
    TextView pharmacyDeliveryTimeTv;

    @BindView(R.id.pay_on_visit)
    Button payOnVisitButton;





    //pharmacy details



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epharmacy_quote);

        ButterKnife.bind(this);
        payOnVisitButton.setVisibility(View.GONE);


        checkOutButton = findViewById(R.id.check_out);
        totalPriceTextView = findViewById(R.id.total_price);
        Checkout.preload(getApplicationContext());
        TAG = getLocalClassName();


        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        transactionId = getIntent().getStringExtra("TRANSACTION_ID");
        ptsId = getIntent().getStringExtra("PTS_ID");



        orderId = getIntent().getIntExtra("orderId",1234);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniqueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");

        pharmacyQuatationDetails = new ArrayList<>();
        listOfQuatationMedicinesArrayList = new ArrayList<>();


        mRecyclerView = (RecyclerView) findViewById(arkaa.health.user.arkaahealthcare.R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ElistOfMedQuoteAdapter(listOfQuatationMedicinesArrayList);
        mRecyclerView.setAdapter(mAdapter);



        if ((ptsId != null) && (transactionId != null)) {

            try {
                postData();
            }catch (Exception e){
                Toast.makeText(EpharmacyQuote.this,"Sorry! no data found",Toast.LENGTH_SHORT).show();
                Log.v("tryCatch","Elaborders Exception e ="+e.getMessage());
                Log.v("tryCatch","Elaborders Exception e ="+e.getStackTrace());
                Log.v("tryCatch","Elaborders Exception e ="+e.getLocalizedMessage());
            }
        }

        }

    public void postData() {


        //post
        Log.v("EDocApp","postData");




        String url = "http://35.163.147.45/api/HospitalMaster/GetPtsMedicine";

        EgetPharmacyQuotationPost eGetPharmacyQuotationPost = new EgetPharmacyQuotationPost();
        eGetPharmacyQuotationPost.setPtsId(ptsId);
        eGetPharmacyQuotationPost.setTransactionId(transactionId);

        Call<EgetPharmacyQuotationPostResponse> postList = EhospitalInboxApi.getService().ePharmacyQuotationApi(url, eGetPharmacyQuotationPost);


        postList.enqueue(new Callback<EgetPharmacyQuotationPostResponse>() {
            @Override
            public void onResponse(Call<EgetPharmacyQuotationPostResponse> call, Response<EgetPharmacyQuotationPostResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                EgetPharmacyQuotationPostResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());

                if(postList1.getData() != null && postList1.getMessage().equals("success")){

                    if(postList1.getData().size() != 0){
                        String pharmacyAddress = postList1.getData().get(0).getPtsMedicineAddress();
                        String pharmacyContactNo = postList1.getData().get(0).getPtsMedicineMobile();
                        String pharmacyDate = postList1.getData().get(0).getPtsMedicineDate();
                        String pharmacyDeliveryTime = postList1.getData().get(0).getPtsMedicineDeliveryTime();

                        pharmacyAddressTv.setText(pharmacyAddress);
                        pharmacyContactNoTv.setText(pharmacyContactNo);
                        pharmacyDateTv.setText(pharmacyDate);
                        pharmacyDeliveryTimeTv.setText(pharmacyDeliveryTime);


                    }

                }



                if (postList1.getGriddata() != null && postList1.getGriddata().size() != 0  && postList1.getData() != null && postList1.getData().size() !=  0) {

                    String pharmacyName = postList1.getGriddata().get(0).getPtsMedicineChPharmacyName();
                    pharmacyNameTv.setText(pharmacyName);

                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    listOfQuatationMedicinesArrayList.addAll(postList1.getGriddata());
                    mAdapter.notifyItemInserted(listOfQuatationMedicinesArrayList.size() - 1);
                    Log.v("listOfQuatation","listOfQuatationMedicinesArrayList size ="+listOfQuatationMedicinesArrayList.size());




                    try{
                        razaorPayAmount = postList1.getGriddata().get(0).getTotalFinalPrice()*100;
                    }catch (Exception e){
                        razaorPayAmount = null;
                    }

                    if(razaorPayAmount != null ){
                        checkOutButton.setEnabled(true);
                    }

                    totalPriceTextView.setText("RS "+postList1.getGriddata().get(0).getTotalFinalPrice()+("Incl GST"));






                    ptsMedicineId = postList1.getData().get(0).getPtsMedicineId();

                    final String ptsMedicineIdFinal  ;
                    if(ptsMedicineId == 0){
                        ptsMedicineIdFinal = "";
                    } else {
                        ptsMedicineIdFinal = ""+ptsMedicineId;
                    }


                    String paymentMode = ""+postList1.getData().get(0).getPaymentMode();
                    Boolean paymentConfirmation = postList1.getData().get(0).getPaymentconfirmation();
                    if(paymentConfirmation == null){
                        paymentConfirmation = false;
                    }



                    if(paymentConfirmation && paymentMode.equals("ONLINE")){
                        checkOutButton.setEnabled(false);
                        checkOutButton.setText("Already paid");
                    } else if(paymentMode.equals("OFFLINE")){

                        checkOutButton.setEnabled(false);
                        checkOutButton.setText("pay on visit");

                    } else {
                        checkOutButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!(ptsMedicineIdFinal.equals(""))) {
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

            @Override
            public void onFailure(Call<EgetPharmacyQuotationPostResponse> call, Throwable t) {

                Toast.makeText(EpharmacyQuote.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


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
      //  changePaymentStatus();
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
                        Intent i = new Intent(EpharmacyQuote.this, FragmentActivity.class);
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
        ehosChPayStatusPost.setPtsMedicineId(""+ptsMedicineId);

        ehosChPayStatusPost.setPtsLabId("");
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

                Toast.makeText(EpharmacyQuote.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");
                showSuccessDialog(" Sorry,Something went wrong.");



            }
        });


        //post


    }

    public void updatePaymentStatus(String razorpayId, final String paymentMode,String paymentConfirmation) {


        //post



        String url = "http://35.163.147.45/api/HospitalMaster/SetPaymentConfirmation";


        Call<EhosChPayStatusResponse> postList = EhospitalInboxApi.getService().eHosChangePaymentStatus(url,getPaymentObj(razorpayId,""+ptsMedicineId,paymentMode,paymentConfirmation));


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
                       // showSuccessDialog(" Thank You,Payment Successfull.");

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

                Toast.makeText(EpharmacyQuote.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }

    public EhosChPayStatusPost getPaymentObj(String razorpayID,String serviceId,String paymentMode,String paymentConfirmation){

        EhosChPayStatusPost ehosChPayStatusPost = new EhosChPayStatusPost();

        ehosChPayStatusPost.setPtsId(ptsId);
        ehosChPayStatusPost.setPtsMedicineId(serviceId);
        ehosChPayStatusPost.setPtsLabId("");
        ehosChPayStatusPost.setPtsVenderProductsId("");
        ehosChPayStatusPost.setPtsVenderserviceId("");
        ehosChPayStatusPost.setPtsDoctorconsultChId("");

        ehosChPayStatusPost.setPaymentMode(paymentMode);
        ehosChPayStatusPost.setPaymentconfirmation(paymentConfirmation);
        ehosChPayStatusPost.setPaymentId(razorpayID);

        return  ehosChPayStatusPost;


    }







}
