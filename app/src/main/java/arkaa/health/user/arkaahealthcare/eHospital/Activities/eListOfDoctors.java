package arkaa.health.user.arkaahealthcare.eHospital.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.eHospital.Adapters.eListOfDoctorsAdapter;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.Datum;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.ElistOfDocPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.ElistOfDocPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.eHospital.Adapters.eListOfDoctorsAdapter;
import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class eListOfDoctors extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];
    private String specializationId;
    private String hospitalId;
    private String specializationName;
    private String hospitalName;

    private List<Datum> listOfDoctorsArray ;
    private ElistOfDocPost elistOfDocPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_list_of_doctors);

        listOfDoctorsArray = new ArrayList<>();
        specializationId = getIntent().getStringExtra("SPECIALIZATION_ID");
        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        specializationName = getIntent().getStringExtra("SPECIALIZATION_NAME");
        hospitalName = getIntent().getStringExtra("HOSPITAL_NAME");

        elistOfDocPost = new ElistOfDocPost();
        elistOfDocPost.setHospitalId(hospitalId);
        elistOfDocPost.setSpecializationId(specializationId);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new eListOfDoctorsAdapter(listOfDoctorsArray,specializationName,hospitalName,eListOfDoctors.this);
        mRecyclerView.setAdapter(mAdapter);

        postData();


        }

    public void postData() {


        //post



        String url = "http://35.163.147.45/api/HospitalMaster/Getdoctorprofile";



        Call<ElistOfDocPostResponse> postList = eHospitalApi.getService().listOfDocApi(url,elistOfDocPost);


        postList.enqueue(new Callback<ElistOfDocPostResponse>() {
            @Override
            public void onResponse(Call<ElistOfDocPostResponse> call, Response<ElistOfDocPostResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                ElistOfDocPostResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1 != null) {


                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    listOfDoctorsArray.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfDoctorsArray.size() - 1);





                }


            }

            @Override
            public void onFailure(Call<ElistOfDocPostResponse> call, Throwable t) {

                Toast.makeText(eListOfDoctors.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }




}
