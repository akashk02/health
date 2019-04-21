package arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.Activities.ElistOfPrescription;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.Adapters.EprescriptionAdapter;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.GetDocPresPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.GetDocPresPostResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.Adapter.ElistOfPtsAdapter;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.ModalClass.Datum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.ModalClass.ListOfPtsPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.ModalClass.ListOfPtsResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi.EhospitalInboxApi;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi.EhospitalInboxApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElistOfPts extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Datum> listOfPtsArrayList ;
    private String patientUniueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_elist_of_pts);
        Log.v("checkInbx","ElistOfPts");


        listOfPtsArrayList = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(arkaa.health.user.arkaahealthcare.R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ElistOfPtsAdapter(listOfPtsArrayList);
        mRecyclerView.setAdapter(mAdapter);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");
        Log.v("checkInbx","patient unique id ="+patientUniueId);


        try {
            Log.v("checkInbx","try");


            if (patientUniueId != null) {
                Log.v("checkInbx","postdata()");

                postData();
            } else {
                Log.v("checkInbx","else cond");
            }

        } catch (Exception e){
            Toast.makeText(ElistOfPts.this,"Sorry, No data available",Toast.LENGTH_SHORT).show();
            Log.v("checkInbx","exception: "+e.getMessage());

        }

        }

    public void postData() {




        Log.v("checkInbx","postData");


        String url = "http://35.163.147.45/api/HospitalMaster/GetAllPtsList";
        ListOfPtsPost listOfPtsPost = new ListOfPtsPost();
        listOfPtsPost.setPatientId(patientUniueId);
       // listOfPtsPost.setPatientId("mohojitappointment4");
        //Log.v("elistOfpts","elistofpts postdata");




        Call<ListOfPtsResponse> postList = EhospitalInboxApi.getService().listOfPtsApi(url,listOfPtsPost);


        postList.enqueue(new Callback<ListOfPtsResponse>() {
            @Override
            public void onResponse(Call<ListOfPtsResponse> call, Response<ListOfPtsResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                ListOfPtsResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());




                if (postList1 != null) {

                    Log.v("checkInbx","postList1 != null");


                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    listOfPtsArrayList.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfPtsArrayList.size() - 1);

                 //   Log.v("elistOfpts","elistofpts postdata hospital id = "+listOfPtsArrayList.get(0).getHospitalId());



                }


            }

            @Override
            public void onFailure(Call<ListOfPtsResponse> call, Throwable t) {

                Toast.makeText(ElistOfPts.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }




}
