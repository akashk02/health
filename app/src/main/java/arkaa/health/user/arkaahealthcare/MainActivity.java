package arkaa.health.user.arkaahealthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Doctor.Activity.ListOfSpecialists;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.ListOfSpecializationApi;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.ListOfSpecializationGetResponse;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.LoginSession.LoginSessionGet;
import arkaa.health.user.arkaahealthcare.Login.RetrofitApi.ArkaaLoginApi;

import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String token;
    String userName;
    String isLogined;
    String userUniqueId;
    String patientUniqueId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", null);
        userName = preferences.getString("USERNAME", null);
        isLogined = preferences.getString("IS_LOGINED", null);
        userUniqueId = preferences.getString("USERUNIQUEID", null);
        patientUniqueId = preferences.getString("PATIENT_UNIQUE_ID", null);



        if(token !=null && userName !=null && isLogined != null && userUniqueId !=null && patientUniqueId != null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,FragmentActivity.class);
                    startActivity(intent);
                    finish();

                }
            },1000);
        }else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivityApp.class);
                    startActivity(intent);
                    finish();

                }
            }, 4000);

        }



//        Intent intent = new Intent(this, LoginActivityApp.class);
//        startActivity(intent);

    }






}
