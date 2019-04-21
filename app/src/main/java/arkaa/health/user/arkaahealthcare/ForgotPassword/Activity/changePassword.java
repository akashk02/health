package arkaa.health.user.arkaahealthcare.ForgotPassword.Activity;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.ResetPasswordPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.ResetPasswordResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.RestApi.ForgotPasswordApi;
import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.R;

import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.ResetPasswordPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.ResetPasswordResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.RestApi.ForgotPasswordApi;
import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changePassword extends AppCompatActivity {
    @BindView(arkaa.health.user.arkaahealthcare.R.id.new_password)
    EditText newPasswordET;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.confirm_password)
    EditText confirmPassET;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.submit_button)
    Button submitButton;

    String newPassword;
    String confirmPassword;
    String token;
    String mobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_change_password);

        ButterKnife.bind(this);

        mobileNo = getIntent().getStringExtra("MOBILE_NO");
        token = getIntent().getStringExtra("TOKEN");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newPassword = newPasswordET.getText().toString();
                confirmPassword = confirmPassET.getText().toString();

                newPasswordET.setError(null);
                confirmPassET.setError(null);

                if(newPassword.equals("") ||  newPassword.length() < 4 || newPassword.length() >10){
                    newPasswordET.setError("Between 4 and 10 characters");
                }else if(confirmPassword.equals("") ||  confirmPassword.length() < 4 || confirmPassword.length() >10){
                    confirmPassET.setError("Between 4 and 10 characters");

                } else if(!validate(newPassword,confirmPassword))
                {
                    Toast.makeText(changePassword.this,"Password Do Not Match",Toast.LENGTH_SHORT).show();
                }else if(token != null && mobileNo !=null){
                    postData();

                } else {
                    Toast.makeText(changePassword.this,"Servers Are Down,Please Try Again Later",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public Boolean validate(String newPassword,String confirmPassword){

        if(newPassword.equals(confirmPassword)){
            return true;
        }
        return false;

    }


    public void postData() {


        String url = "https://arkaahealthapp.com/api/v1/users/resetpassword";

        ResetPasswordPost resetPasswordPost = new ResetPasswordPost();
        resetPasswordPost.setMobile(mobileNo);
        resetPasswordPost.setPassword(newPassword);
        resetPasswordPost.setToken(token);


        final Call<ResetPasswordResponse> postList = ForgotPasswordApi.getService().resetPassApi(token,url, resetPasswordPost);

        postList.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {

                final ResetPasswordResponse postList1 = response.body();

                if (postList1.getSuccess() == true ) {
                    Toast.makeText(changePassword.this,"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(changePassword.this, LoginActivityApp.class);
                    intent.putExtra("TOKEN",token);
                    startActivity(intent);

                } else {
                    Toast.makeText(changePassword.this, "Servers Are Down,Try Again Later", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {

                Toast.makeText(changePassword.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }


}
