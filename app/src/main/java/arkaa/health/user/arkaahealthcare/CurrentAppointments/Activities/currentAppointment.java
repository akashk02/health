package arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.CurrentAppointments.Adapter.currentAppointmentsAdapter;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments.CurrentAppointmentsGetResponse;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments.Datum;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.DoctorAppointmentPayment.DoctorAppointmentPaymentResponse;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.RetrofitApi.CurrentAppointmentApi;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class currentAppointment extends AppCompatActivity implements PaymentResultListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Datum> listOfDocArrayList;
    String token;
    String patientUniueId;

    private String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_appointment);

        Checkout.preload(getApplicationContext());
        TAG = getLocalClassName();


        listOfDocArrayList = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new currentAppointmentsAdapter(listOfDocArrayList,currentAppointment.this);
        mRecyclerView.setAdapter(mAdapter);

        getData();
    }


    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfSpecialists)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/users/"+patientUniueId+"/appointments/0";
        Log.v("apiUrl",getLocalClassName()+" url ="+url);


        Call<CurrentAppointmentsGetResponse> postList = CurrentAppointmentApi.getService().getDocAppApi(token, url);

        Log.v("SignUp", "(ListOfSpecialists)token for header userProfile get" + token);

        postList.enqueue(new Callback<CurrentAppointmentsGetResponse>() {
            @Override
            public void onResponse(Call<CurrentAppointmentsGetResponse> call, Response<CurrentAppointmentsGetResponse> response) {


//                Toast.makeText(ListOfSpecialists.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfSpecialists)profile get = success");

                CurrentAppointmentsGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfSpecialists)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfSpecialists)profile success get =" + postList1.getSuccess());

                    listOfDocArrayList.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfDocArrayList.size() - 1);

//                    listOfSpecializationArray = postList1.getData();
//                    listOfSpecializationArray.notifyAll();






                }






            }

            @Override
            public void onFailure(Call<CurrentAppointmentsGetResponse> call, Throwable t) {

                Toast.makeText(currentAppointment.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfSpecialists)error in link (get)");


            }
        });


        //post


    }

    public void startPayment(String orderId,String amount) {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.arkaa_new_logo);


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
            options.put("name", "Arkaa");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Arkaa");

            options.put("currency", "INR");
            options.put("order_id",orderId);
          //  options.put("color","#D50000");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            options.put("amount", amount);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("paymentGateway", "Error in starting Razorpay Checkout", e);
        }
    }

    public void paymentFromAdapter(Double price,int index,String orderId){
        Log.v("paymentFromAdapter", "price = "+price+"index = "+index);

        String razorpayAmount ;
        if(price == 0.0) {
            razorpayAmount = "" ;
        } else {
            razorpayAmount = ""+price;
        }

        Log.v("razorpayAmount","currentAppt = "+razorpayAmount);

        if(!razorpayAmount.equals("")){
            startPayment(orderId,razorpayAmount);
            }
            else {
            showSuccessDialog("Servers are down,Please try again later");
        }

    }


    @Override
    public void onPaymentSuccess(String s) {
        Log.v("currentAppointment123","payment key ="+s);
        updatePaymentStatus(s);
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
                        Intent i = new Intent(currentAppointment.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public void updatePaymentStatus(String paymentKey) {


        //post



        String url = "https://arkaahealthapp.com/api/v1/doctor-appointment/update-payment/"+paymentKey;

        Call<DoctorAppointmentPaymentResponse> postList = CurrentAppointmentApi.getService().updatePaymentStatusApi(token, url);


        postList.enqueue(new Callback<DoctorAppointmentPaymentResponse>() {
            @Override
            public void onResponse(Call<DoctorAppointmentPaymentResponse> call, Response<DoctorAppointmentPaymentResponse> response) {



                DoctorAppointmentPaymentResponse postList1 = response.body();


                if (postList1 != null) {

                    if(postList1.getSuccess() == true){

                        showSuccessDialog(" Thank You,Payment successfull.");
                        Log.v("currentAppointment123","payment success");


                    }




                    }

                    }

            @Override
            public void onFailure(Call<DoctorAppointmentPaymentResponse> call, Throwable t) {

                Toast.makeText(currentAppointment.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


        //post


    }


}
