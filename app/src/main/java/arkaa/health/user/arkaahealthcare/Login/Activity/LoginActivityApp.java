package arkaa.health.user.arkaahealthcare.Login.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.ForgotPassword.Activity.activity_forgot;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Login.RetrofitApi.ArkaaLoginApi;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.PostLogin;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.ResponseLogin;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileGetRetrofit.ProfileGetReponse;
import arkaa.health.user.arkaahealthcare.Profile.RetrofitApi.ProfileApi;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Registration.Activity.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.PostLogin;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.ResponseLogin;
import arkaa.health.user.arkaahealthcare.Login.RetrofitApi.ArkaaLoginApi;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileGetRetrofit.ProfileGetReponse;
import arkaa.health.user.arkaahealthcare.Profile.RetrofitApi.ProfileApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityApp extends AppCompatActivity {


    //token

    public static String token ;


    //token

    private static final String TAG = "LoginActivityApp";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.sign)
    TextView _signupLink;

    @BindView(R.id.forgotPass)
    TextView _forgotPass;


    EditText edittext;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    private SharedPreferences sharedPreferences ;


    //Retrofit
    private String username;
    private String password;
    private ResponseLogin post;
    private PostLogin loginPost;
    private Boolean result;
    private EditText usernameEditText;
    private EditText passwordUserText;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mAuth = FirebaseAuth.getInstance();


        //token



        //token


        ButterKnife.bind(this);
        //orignal login validation
//        _loginButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                login();
//            }
//        });
        //orignal login validation


// Arkaa login


//        usernameEditText = findViewById(R.id.username);
//        passwordUserText = findViewById(R.id.password);

        loginPost = new PostLogin();


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!validate()) {
                    onLoginFailed();
                    return;
                }

                username = _emailText.getText().toString();
                password = _passwordText.getText().toString();

                loginPost.setUsername(username);
                loginPost.setPassword(password);

                postData();
                Log.v("firebaseToken","_loginButton.setOnClickListener");

//
//                Intent intent = new Intent(LoginActivityApp.this,FragmentActivity.class);
//                startActivity(intent);





            }
        });

        //Arkaa login


        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _forgotPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityApp.this, activity_forgot.class);
                startActivity(intent);
            }
        });


    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivityApp.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivitySinch
        moveTaskToBack(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.v("firebaseToken","onStart() currentUser = "+currentUser);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() ) {
            _emailText.setError("enter your UserName");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() ) {
            _passwordText.setError("Enter your password");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


    public void postData() {


        String url = "https://arkaahealthapp.com/api/v1/authenticate";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        final Call<ResponseLogin> postList = ArkaaLoginApi.getService().reqRescreateUser(url, loginPost);

        postList.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                final ResponseLogin postList1 = response.body();
                post = postList1;

                if(postList1 != null) {

//                Log.v("postData", "OnResponse");
//                Log.v("postData", "status code = " + response.code());
//                Log.v("postData", "postList1 =" + postList1);
//                Log.v("SignUp","token login = "+postList1.getToken());

                    try {
                        token = postList1.getToken();
                        Log.v("universalToken", token);

                    } catch (Exception e) {
                        Log.v("universalToken", "Exceptionn e =" + e.getMessage());
                        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MjUyNjE2Mjl9.qPwBhsyvFSsu7EuJq9DgD9sk4nFDgSeVVS8jV5DaSPo";
                    }


                    if (postList1.getSuccess() == true) {
                        sharedPreferences = getSharedPreferences("token",
                                Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("TOKEN", token);
                        editor.putString("USERNAME", username);
                        editor.putString("IS_LOGINED", "ture");

//                    editor.putString("USERNAME",)
                        editor.commit();


//
//                    Log.v("firebaseToken","token = "+postList1.getToken());
//                    Log.v("firebaseToken","Firebase token = "+postList1.getFireToken());

                        signInWithCustomToken(postList1.getFireToken());


                        //Firebase Authentication

//
//                    mAuth.signInWithCustomToken(postList1.getToken())
//                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        // Sign in success, update UI with the signed-in user's information
//                                        Log.d(TAG, "signInWithCustomToken:success");
//                                        FirebaseUser user = mAuth.getCurrentUser();
////                                        updateUI(user);
//                                    } else {
//                                        // If sign in fails, display a message to the user.
//                                        Log.w(TAG, "signInWithCustomToken:failure", task.getException());
////                                        Toast.makeText(CustomAuthActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
////                                        updateUI(null);
//                                    }
//                                }
//                            });


                        //Firebase Authentication


                        getUserUniqueId(username, token);


                    } else {
                        Toast.makeText(LoginActivityApp.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(LoginActivityApp.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                Toast.makeText(LoginActivityApp.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void signInWithCustomToken(String firebaseToken){

        Log.v("firebaseToken","signInWithCustomToken");

        mAuth.signInWithCustomToken(firebaseToken)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            Log.v("firebaseToken","signInWithCustomToken() addOnCompleteListener");
                            Log.v("firebaseToken","signInWithCustomToken() user = "+user+" user ID = "+user.getUid());


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
//                            Toast.makeText(LoginActivityApp.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                            Log.v("firebaseToken","signInWithCustomToken() addOnCompleteListener(Failure)");

                        }
                    }
                });


    }

    public void getUserUniqueId(String userName,String token) {


        //post

        Log.v("homescreenfragment", "GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/users/" + userName + "/";
    //    Log.v("homescreenfragment","url "+loginUserName);

        Call<ProfileGetReponse> postList = ProfileApi.getService().profileGetRequest(token, url);

        Log.v("homescreenfragment", "(post)token for header userProfile get" + token);

        postList.enqueue(new Callback<ProfileGetReponse>() {
            @Override
            public void onResponse(Call<ProfileGetReponse> call, Response<ProfileGetReponse> response) {


//                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                Log.v("homescreenfragment", "(post)profile get = success");

                ProfileGetReponse postList1 = response.body();
                Log.v("homescreenfragment", "(post)profile get = status code = " + response.code());


                if (postList1 != null) {

                    String uniqueId = postList1.getData().get(0).getUniqueId();
                    String userName = postList1.getData().get(0).getFirstName();

                    sharedPreferences = LoginActivityApp.this.getSharedPreferences("token",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("USERUNIQUEID",uniqueId );
                    editor.putString("PATIENT_UNIQUE_ID",uniqueId );
                    editor.putString("PATIENT_NAME",userName);
                    Log.v("homescreenfragment","unique id"+uniqueId+" username ="+userName);
                    editor.commit();


                    if(uniqueId != null && !uniqueId.equals("")) {
                        //Toast.makeText(LoginActivityApp.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivityApp.this, FragmentActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginActivityApp.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                    }






                } else {
                    Toast.makeText(LoginActivityApp.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                }







            }

            @Override
            public void onFailure(Call<ProfileGetReponse> call, Throwable t) {

                Toast.makeText(LoginActivityApp.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("homescreenfragment", "(post)error in link (get)");


            }
        });


        //post


    }


















}
