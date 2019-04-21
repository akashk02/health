package arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Adapters.MyAdapter6;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.RetrofitApi.DoctorPrescriptionApi;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.DoctorPrescriptionResponseAws;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Adapters.MyAdapter6;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.DoctorPrescriptionResponseAws;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.RetrofitApi.DoctorPrescriptionApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfPrescription extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];
    private List<Datum> listOfPrescription ;
    private String token;
    private String patientUniueId;
    private String activityType ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

//        activityType = getIntent().getStringExtra("ACTIVITY_TYPE");
//        if(activityType == null){
//
//        }

        if(getIntent().getStringExtra("ACTIVITY_TYPE") == null){
            activityType = "PRESCRIPTION";
            Log.v("buttonView","activityType = PRESCRIPTION ");

        }else {
            activityType = "LAB";
            Log.v("buttonView","activityType = LAB ");


        }



        listOfPrescription = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter6(myDataset,1,listOfPrescription,activityType,ListOfPrescription.this);
        mRecyclerView.setAdapter(mAdapter);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");


        getData();




    }

    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfSpecialists)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/users/"+patientUniueId+"/prescriptions/0";

        Call<DoctorPrescriptionResponseAws> postList = DoctorPrescriptionApi.getService().getAllPrescriptions(token, url);

        Log.v("SignUp", "(ListOfSpecialists)token for header userProfile get" + token);

        postList.enqueue(new Callback<DoctorPrescriptionResponseAws>() {
            @Override
            public void onResponse(Call<DoctorPrescriptionResponseAws> call, Response<DoctorPrescriptionResponseAws> response) {


//                Toast.makeText(ListOfSpecialists.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfSpecialists)profile get = success");

                DoctorPrescriptionResponseAws postList1 = response.body();
                Log.v("SignUp", "(ListOfSpecialists)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfSpecialists)profile success get =" + postList1.getSuccess());

                    listOfPrescription.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfPrescription.size() - 1);

//                    listOfSpecializationArray = postList1.getData();
//                    listOfSpecializationArray.notifyAll();






                }






            }

            @Override
            public void onFailure(Call<DoctorPrescriptionResponseAws> call, Throwable t) {

                Toast.makeText(ListOfPrescription.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfSpecialists)error in link (get)");


            }
        });


        //post


    }









}
