package arkaa.health.user.arkaahealthcare.Registration.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Registration.RetrofitApi.SignUpApi;
import arkaa.health.user.arkaahealthcare.Registration.ModalClasses.SignUpPost;
import arkaa.health.user.arkaahealthcare.Registration.ModalClasses.SignUpResponse;

import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.Registration.ModalClasses.SignUpPost;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {


    private static final String TAG = "SignupActivity";
    private String mobileNo ;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.input_name) EditText _nameText;



    @BindView(arkaa.health.user.arkaahealthcare.R.id.user_name) EditText _userName;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.last_name) EditText _lastName;

    //    @BindView(R.id.input_address) EditText _addressText;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.input_email) EditText _emailText;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.input_mobile) EditText _mobileText;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.input_password) EditText _passwordText;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.input_reEnterPassword) EditText _reEnterPasswordText;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.btn_signup) Button _signupButton;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.link_login) TextView _loginLink;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.terms_and_conditions) TextView termsAndConditionsTextView;

    @BindView(arkaa.health.user.arkaahealthcare.R.id.checkBox) CheckBox iAgreeCheckBox;
    @BindView(arkaa.health.user.arkaahealthcare.R.id.terms_validation) TextView termsValidationTextView;

    //SignUpArkaa

    private SignUpPost signUpPost;


    //SignUpArkaa



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_signup);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        ButterKnife.bind(this);

        signUpPost = new SignUpPost();


        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
//                Intent intent = new Intent(SignupActivity.this,otp.class);
//                startActivity(intent);
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivityApp.class);
                startActivity(intent);
                finish();
                overridePendingTransition(arkaa.health.user.arkaahealthcare.R.anim.push_left_in, arkaa.health.user.arkaahealthcare.R.anim.push_left_out);
            }
        });

        termsAndConditionsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdf_url = "http://13.127.59.252/T&C/index.pdf";

//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://13.127.59.252/T&C/index.pdf"));
//                startActivity(browserIntent);

                Intent intent = new Intent();
                intent.setDataAndType(Uri.parse(pdf_url), "application/pdf");
                startActivity(intent);


            }
        });

        iAgreeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iAgreeCheckBox.isChecked()){
                termsValidationTextView.setError(null);}
                }
        });









    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(true);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();

        String firstNamee = _nameText.getText().toString();
//        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        Log.v("mobile_no",mobile.toString());
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();
        String userName = _userName.getText().toString();
        String lastName = _lastName.getText().toString();

        mobileNo = mobile;



        // TODO: Implement your own signup logic here.

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);

        //SignUpArkaa


        signUpPost.setUsername(userName);
        signUpPost.setFirstName(firstNamee);
        signUpPost.setLastName(lastName);
        signUpPost.setEmail(email);
        signUpPost.setMobileNo(mobile);
        signUpPost.setPassword(password);

        postData();





        //SignUpArkaa







    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
       // Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
//        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        String userName = _userName.getText().toString();
        String lastName = _lastName.getText().toString();

        if (userName.isEmpty()) {
            _userName.setError("Please Enter Username");
            valid = false;
        } else {
            _userName.setError(null);
        }

        if (lastName.isEmpty()) {
            _lastName.setError("Please Enter Last Name");
            valid = false;
        } else {
            _lastName.setError(null);
        }




        if (name.isEmpty()) {
            _nameText.setError("Please Enter First Name");
            valid = false;
        } else {
            _nameText.setError(null);
        }

//        if (address.isEmpty()) {
//            _addressText.setError("Enter Valid Address");
//            valid = false;
//        } else {
//            _addressText.setError(null);
//        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Please Enter valid 10 digit Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        Boolean passValidation = false;
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Between 4 and 10 characters");
            passValidation = true;
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            if(passValidation){
                _reEnterPasswordText.setError("Between 4 and 10 characters");
                }else {
                _reEnterPasswordText.setError("Password Do not match");
            }

            Log.v("passTest","reEnterPassword.isEmpty() ="+reEnterPassword.isEmpty());
            Log.v("passTest","reEnterPassword.length() < 4 ="+(reEnterPassword.length() < 4)+"");
            Log.v("passTest","reEnterPassword.length() > 10 ="+(reEnterPassword.length() > 10));
            Log.v("passTest","!(reEnterPassword.equals(password))) ="+(!(reEnterPassword.equals(password))));
            Log.v("passTest","if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password)))  ="+( (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) ));





            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        if(!iAgreeCheckBox.isChecked()){
            termsValidationTextView.setError("Please agree to terms and conditions");
            valid = false;
        }else {
            termsValidationTextView.setError(null);
        }


        return valid;
    }

    public void postData() {


        String url = "https://arkaahealthapp.com/api/v1/Users";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        Call<SignUpResponse> postList = SignUpApi.getService().reqRescreateUser(url, signUpPost);

        postList.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {


//                Toast.makeText(SignupActivity.this,"success",Toast.LENGTH_SHORT).show();
//                Log.v("SignUp","success");

                SignUpResponse postList1 = response.body();

                Log.v("SignUp", "status code = " + response.code());



                Log.v("SignUp", "success =" + postList1.getSuccess());

                if(postList1.getSuccess()== true){

                    Intent intent = new Intent(SignupActivity.this,otp.class);
                    intent.putExtra("mobile_no",mobileNo);
                    startActivity(intent);


                } else {
//                    postList1.getMessage();
                    Toast.makeText(SignupActivity.this,postList1.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.v("registrationToast","registrationToast "+postList1.getMessage());
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
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Toast.makeText(SignupActivity.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("retrofit","success");


            }
        });


    }

    @Override
    public void onBackPressed() {


        Intent intent = new Intent(getApplicationContext(),LoginActivityApp.class);
        startActivity(intent);

        }
}
