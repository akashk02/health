package arkaa.health.user.arkaahealthcare.Doctor.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Doctor.Adapters.ViewAllClinicsAdapter;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicDetail;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorDetailsResponse;
import arkaa.health.user.arkaahealthcare.Doctor.RetrofitApi.DoctorListApi;
import arkaa.health.user.arkaahealthcare.Lab.Adapters.listOfLabAdapter;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Doctor.Adapters.ViewAllClinicsAdapter;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicDetail;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorDetailsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllClinics extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ViewAllClinicsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String token;
    private int index;
    private int specializationId;
    private String TAG;

    private int officeId;
    private String doctorUniqueId;
    private List<ClinicDetail> clinicDetailsArrayList;
    private String doctorUniqueIdIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_clinics);

        TAG = getLocalClassName();
        index = getIntent().getIntExtra("INDEX", 0);
        Log.v("index", "index = " + index);
        specializationId = getIntent().getIntExtra("SPECIALIZATION_ID", 1);
        doctorUniqueIdIntent = getIntent().getStringExtra("DOCTOR_UNIQUE_ID");

        Log.v("viewAllClinicData", getLocalClassName() + " index = " + index + " specializationId" + specializationId + " doctorUniqueIdIntent" + doctorUniqueIdIntent);


        clinicDetailsArrayList = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ViewAllClinicsAdapter(clinicDetailsArrayList, doctorUniqueIdIntent,index,specializationId,ViewAllClinics.this);
        mRecyclerView.setAdapter(mAdapter);

        getData();


    }

    public void getData() {


        //post

        Log.v("SignUp1", "(DoctorsDetails)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/doctors/specialities/" + specializationId;

        Call<DoctorDetailsResponse> postList = DoctorListApi.getService().DoctorListGetView(token, url);

        Log.v("SignUp", "(DoctorsDetails)token for header userProfile get" + token);
        Log.v("apiUrl", getLocalClassName() + " url =" + url);

        postList.enqueue(new Callback<DoctorDetailsResponse>() {
            @Override
            public void onResponse(Call<DoctorDetailsResponse> call, Response<DoctorDetailsResponse> response) {


//                    Toast.makeText(DoctorsDetail.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(DoctorsDetails)profile get = success");

                DoctorDetailsResponse postList1 = response.body();
                Log.v("SignUp", "(DoctorsDetails)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(DoctorsDetails)profile success get =" + postList1.getSuccess());


                    List<ClinicDetail> clinicDetailsArray = postList1.getData().get(index).getClinicDetails();

                    if(clinicDetailsArray != null && clinicDetailsArray.size()>0) {

                        clinicDetailsArrayList.addAll(clinicDetailsArray);
                        mAdapter.notifyItemInserted(clinicDetailsArrayList.size() - 1);

                    }


                }


            }


            @Override
            public void onFailure(Call<DoctorDetailsResponse> call, Throwable t) {

                Toast.makeText(ViewAllClinics.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(DoctorsDetails)error in link (get)");


            }

        });


        //post


    }


}



