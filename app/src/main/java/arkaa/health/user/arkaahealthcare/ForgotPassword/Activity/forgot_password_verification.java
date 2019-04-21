package arkaa.health.user.arkaahealthcare.ForgotPassword.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.SendOtpPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.SendOtpResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.RestApi.ForgotPasswordApi;
import arkaa.health.user.arkaahealthcare.R;

import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class forgot_password_verification extends AppCompatActivity {

    @BindView(R.id.otp)
    EditText otpNoEditText;

    String mobileNo;
    String otpNo;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        ButterKnife.bind(this);
        mobileNo = getIntent().getStringExtra("MOBILE_NO");

        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpNo = otpNoEditText.getText().toString();
                if(!otpNo.equals("")) {
                    otpNoEditText.setError(null);
                    postData();


                } else {
                    otpNoEditText.setError("Please Enter Otp No");
                }
            }
        });

    }

    public void postData() {


        String url = "https://arkaahealthapp.com/api/v1/verifyOtpforgotpassword";

        VerifyOtpPost VerifyOtpPost = new VerifyOtpPost();
        VerifyOtpPost.setMobile(mobileNo);
        VerifyOtpPost.setOtp(otpNo);
        VerifyOtpPost.setType("mobile");


        final Call<VerifyOtpResponse> postList = ForgotPasswordApi.getService().verifyOtpApi(url, VerifyOtpPost);

        postList.enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {

                final VerifyOtpResponse postList1 = response.body();

                if (postList1.getSuccess() == true && postList1.getToken() !=null) {
                    token = postList1.getToken();
                    Intent intent = new Intent(forgot_password_verification.this, changePassword.class);
                    intent.putExtra("TOKEN",token);
                    intent.putExtra("MOBILE_NO", mobileNo);
                    startActivity(intent);

                } else {
                    Toast.makeText(forgot_password_verification.this, "Otp No Does Not Match", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {

                Toast.makeText(forgot_password_verification.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }



}
