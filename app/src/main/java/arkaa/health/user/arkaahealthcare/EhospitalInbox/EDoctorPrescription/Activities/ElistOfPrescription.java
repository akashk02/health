package arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.Adapters.EprescriptionAdapter;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.GetDocPresPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.GetDocPresPostResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi.EhospitalInboxApi;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.R;


import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.GetDocPresPostResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElistOfPrescription extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];
    private List<Datum> listOfPrescription ;
    private String patientUniueId;

    private String hospitalId;
    private String transactionId;
    private String ptsId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_elist_of_prescription);

        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        transactionId = getIntent().getStringExtra("TRANSACTION_ID");
        ptsId = getIntent().getStringExtra("PTS_ID");



        listOfPrescription = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(arkaa.health.user.arkaahealthcare.R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EprescriptionAdapter(myDataset,listOfPrescription,ElistOfPrescription.this);
        mRecyclerView.setAdapter(mAdapter);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");

        if ((hospitalId != null)) {
            postData();
        }else {
            Toast.makeText(ElistOfPrescription.this,"sorry, Something went wrong, Please try again later.",Toast.LENGTH_SHORT).show();
        }


    }

    public void postData() {


        Log.v("EDocApp","postData");




        String url = "http://35.163.147.45/api/HospitalMaster/PrescriptionByDoctor";
        GetDocPresPost getDocPresPost = new GetDocPresPost();
        getDocPresPost.setPatientId(patientUniueId);
        getDocPresPost.setHospitalId(hospitalId);



        Call<GetDocPresPostResponse> postList = EhospitalInboxApi.getService().eListOfDocPres(url,getDocPresPost);


        postList.enqueue(new Callback<GetDocPresPostResponse>() {
            @Override
            public void onResponse(Call<GetDocPresPostResponse> call, Response<GetDocPresPostResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                GetDocPresPostResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1 != null) {


                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    listOfPrescription.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfPrescription.size() - 1);





                }


            }

            @Override
            public void onFailure(Call<GetDocPresPostResponse> call, Throwable t) {

                Toast.makeText(ElistOfPrescription.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }



}
