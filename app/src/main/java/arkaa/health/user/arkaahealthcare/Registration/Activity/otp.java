package arkaa.health.user.arkaahealthcare.Registration.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.ResendOtpRetrofit .ResendOtpPost;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.ResendOtpRetrofit.ResendOtpResponse;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.ResendOtpRetrofit.resendOtpApi;
import arkaa.health.user.arkaahealthcare.Registration.RetrofitApi.OtpApi;
import arkaa.health.user.arkaahealthcare.Registration.ModalClasses.OtpPost;
import arkaa.health.user.arkaahealthcare.Registration.ModalClasses.OtpResponse;

import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.ResendOtpRetrofit.ResendOtpPost;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.ResendOtpRetrofit.ResendOtpResponse;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.ResendOtpRetrofit.resendOtpApi;
import arkaa.health.user.arkaahealthcare.Registration.RetrofitApi.OtpApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class otp extends AppCompatActivity {

    private OtpPost otpPost;
    private EditText otpEditText;
    private String mobileNo ;
    private ResendOtpPost resendOtpPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_otp);

         mobileNo = getIntent().getStringExtra("mobile_no");


        otpEditText = findViewById(arkaa.health.user.arkaahealthcare.R.id.otp);

        otpPost = new OtpPost();
        resendOtpPost = new ResendOtpPost();

        Button submitButton = findViewById(arkaa.health.user.arkaahealthcare.R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otpPost.setMobile(mobileNo);
                otpPost.setOtp(otpEditText.getText().toString());
                Log.v("mobile_no",mobileNo);

                postData();



            }
        });

        TextView sendAgainTextView = findViewById(arkaa.health.user.arkaahealthcare.R.id.send_again);
        sendAgainTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                resendOtpPost.setMobile(mobileNo);
                otpEditText.setText("");
                resendOtpPostData();



            }
        });





    }


    public void postData() {


        String url = "https://arkaahealthapp.com/api/v1/verifyOtp";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        Call<OtpResponse> postList = OtpApi.getService().reqRescreateUser(url, otpPost);

        postList.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {


//                Toast.makeText(otp.this,"success",Toast.LENGTH_SHORT).show();
                Log.v("SignUp","otp = success");

                OtpResponse postList1 = response.body();

                Log.v("SignUp", "otp = status code = " + response.code());



                Log.v("SignUp", "otp success =" + postList1.getSuccess());


                if(postList1.getSuccess()== true){

                    Intent intent = new Intent(otp.this,LoginActivityApp.class);
                    Toast.makeText(otp.this,"Your account has been created successfully",Toast.LENGTH_SHORT).show();
                    startActivity(intent);


                }




//
//                if (postList1.getSuccess() == true) {
//
//                    Toast.makeText(LoginActivityApp.this, "Success", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivityApp.this, FragmentActivity.class);
//                    startActivity(intent);
//
//
//                } else {
//                    Toast.makeText(LoginActivityApp.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();
//
//                }


            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {

                Toast.makeText(otp.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("retrofit","otp = success");


            }
        });


    }



    public void resendOtpPostData(){



        String url = "https://arkaahealthapp.com/api/v1/resendOtp/";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        Call<ResendOtpResponse> postList = resendOtpApi.getService().reqRescreateUser(url, resendOtpPost);

        postList.enqueue(new Callback<ResendOtpResponse>() {
            @Override
            public void onResponse(Call<ResendOtpResponse> call, Response<ResendOtpResponse> response) {


//                Toast.makeText(otp.this,"success",Toast.LENGTH_SHORT).show();
                Log.v("SignUp","otp resend= success");

                ResendOtpResponse postList1 = response.body();

                Log.v("SignUp", "otp resend = status code = " + response.code());



                Log.v("SignUp", "otp success resend=" + postList1.getSuccess());
                if(postList1.getSuccess() == true){
                    Toast.makeText(otp.this,"OTP sended successfully",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(otp.this,"Something went wrong, Please try again later",Toast.LENGTH_SHORT).show();
                }






            }

            @Override
            public void onFailure(Call<ResendOtpResponse> call, Throwable t) {

                Toast.makeText(otp.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });






    }








}
