package arkaa.health.user.arkaahealthcare.SinchVideoCall;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.R;
import com.sinch.android.rtc.SinchError;

public class MainActivityVideo extends BaseActivity implements SinchService.StartFailedListener {

    private Button mLoginButton;
    private EditText mLoginName;
    private ProgressDialog mSpinner;

    //changes
    private String patientUniueId;
    private String token;
    //changes

    private String doctorUniqueIdIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video);

        doctorUniqueIdIntent = getIntent().getStringExtra("DOCTOR_UNIQUE_ID");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE},100);
        }

        //changes

         SharedPreferences preferences = getSharedPreferences("token", MODE_PRIVATE);
           patientUniueId = "ARKAA"+preferences.getString("PATIENT_UNIQUE_ID", "0101");
//        patientUniueId = "test2";

//        token = preferences.getString("TOKEN", "123456789");
//
//        Log.v("UNIQUEid","patientUniqueId = "+patientUniueId);
//        Log.v("UNIQUEid","MainActivityVideo(token) = "+token);

        //changes


        mLoginName = (EditText) findViewById(R.id.loginName);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setEnabled(false);
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });
    }

    @Override
    protected void onServiceConnected() {
        mLoginButton.setEnabled(true);
        getSinchServiceInterface().setStartListener(this);

        //changes
        loginClicked();
        //changes

    }


    @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }

    @Override
    public void onStartFailed(SinchError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
    }

    @Override
    public void onStarted() {
        openPlaceCallActivity();
    }

    private void loginClicked() {
//        String userName = mLoginName.getText().toString();
//
//        if (userName.isEmpty()) {
//            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
//            return;
//        }

        String userName = patientUniueId;
//        String userName = "akash";



        if (!userName.equals(getSinchServiceInterface().getUserName())) {
            getSinchServiceInterface().stopClient();
        }

        if (!getSinchServiceInterface().isStarted()) {
            getSinchServiceInterface().startClient(userName);
            showSpinner();
        } else {
            openPlaceCallActivity();
        }
    }

    private void openPlaceCallActivity() {
        Intent mainActivity = new Intent(this, PlaceCallActivity.class);
        mainActivity.putExtra("DOCTOR_UNIQUE_ID",doctorUniqueIdIntent);
        startActivity(mainActivity);
        //changes
        finish();
        //changes
    }

    private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Logging in");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }
}
