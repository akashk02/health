package arkaa.health.user.arkaahealthcare.ForgotPassword.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.SendOtpPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.SendOtpResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.RestApi.ForgotPasswordApi;
import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.ResponseLogin;
import arkaa.health.user.arkaahealthcare.Login.RetrofitApi.ArkaaLoginApi;
import arkaa.health.user.arkaahealthcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_forgot extends AppCompatActivity {

    @BindView(R.id.mobile_no)
    EditText mobileNoEditText;

    String mobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        ButterKnife.bind(this);




        Button button = findViewById(R.id.btn_otp);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mobileNo = mobileNoEditText.getText().toString();

                    if(!mobileNo.equals("")) {
                        mobileNoEditText.setError(null);
                        postData();
                    }else {
                        mobileNoEditText.setError("Please Enter your Registered Mobile No");
                    }

                    }

            });

        }



    public void postData() {


        String url = "https://arkaahealthapp.com/api/v1/forgotpassword";

        SendOtpPost sendOtpPost = new SendOtpPost();
        sendOtpPost.setMobileNo(mobileNo);


        final Call<SendOtpResponse> postList = ForgotPasswordApi.getService().sendOtpApi(url, sendOtpPost);

        postList.enqueue(new Callback<SendOtpResponse>() {
            @Override
            public void onResponse(Call<SendOtpResponse> call, Response<SendOtpResponse> response) {

                final SendOtpResponse postList1 = response.body();

                if (postList1.getSuccess() == true) {
                    Intent intent = new Intent(activity_forgot.this, forgot_password_verification.class);
                    intent.putExtra("MOBILE_NO", mobileNo);
                    startActivity(intent);

                } else {
                    Toast.makeText(activity_forgot.this, "Please Enter Registered Mobile No", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<SendOtpResponse> call, Throwable t) {

                Toast.makeText(activity_forgot.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }



}
