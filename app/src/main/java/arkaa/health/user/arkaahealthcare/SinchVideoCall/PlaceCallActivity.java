package arkaa.health.user.arkaahealthcare.SinchVideoCall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.R;
import com.sinch.android.rtc.calling.Call;

public class PlaceCallActivity extends BaseActivity {

    private Button mCallButton;
    private EditText mCallName;

    //changes
    private String doctorUniqueId ;
    //changes

    private String doctorUniqueIdIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        doctorUniqueIdIntent = getIntent().getStringExtra("DOCTOR_UNIQUE_ID");

        //changes
        SharedPreferences preferences = getSharedPreferences("token", MODE_PRIVATE);
//        doctorUniqueId = "ARKAA"+preferences.getString("DOCTOR_UNIQUE_ID", "4414121");
          doctorUniqueId = "ARKAA"+doctorUniqueIdIntent;

//        doctorUniqueId = "test3";

        Log.v("UNIQUEid","doctorUniqueId = "+doctorUniqueId);
        //changes

        mCallName = (EditText) findViewById(R.id.callName);
        mCallButton = (Button) findViewById(R.id.callButton);
        mCallButton.setEnabled(false);
        mCallButton.setOnClickListener(buttonClickListener);

        Button stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onServiceConnected() {
        TextView userName = (TextView) findViewById(R.id.loggedInName);
        userName.setText(getSinchServiceInterface().getUserName());
        mCallButton.setEnabled(true);
    }

    private void stopButtonClicked() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        finish();
    }

    private void callButtonClicked() {
//        String userName = mCallName.getText().toString();
//        if (userName.isEmpty()) {
//            Toast.makeText(this, "Please enter a user to call", Toast.LENGTH_LONG).show();
//            return;
//        }

        String userName = doctorUniqueId;
        Call call = getSinchServiceInterface().callUserVideo(userName);
        String callId = call.getCallId();

        Intent callScreen = new Intent(this, CallScreenActivity.class);
        callScreen.putExtra(SinchService.CALL_ID, callId);
        startActivity(callScreen);
    }

    private OnClickListener buttonClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.callButton:
                    callButtonClicked();
                    break;

                case R.id.stopButton:
                    stopButtonClicked();
                    break;

            }
        }
    };
}
