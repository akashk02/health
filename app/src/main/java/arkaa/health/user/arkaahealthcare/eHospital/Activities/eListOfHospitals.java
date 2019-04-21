package arkaa.health.user.arkaahealthcare.eHospital.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.eHospital.Adapters.listOfHospitalsAdapter;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfHospitalModalClass.Datum;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfHospitalModalClass.GetAllHospitalsPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfHospitalModalClass.ListOfHospitalsPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class eListOfHospitals extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];

    private List<Datum> listOfHospitalsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_hospitals);

        listOfHospitalsArrayList = new ArrayList<>();


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new listOfHospitalsAdapter(listOfHospitalsArrayList,eListOfHospitals.this);
        mRecyclerView.setAdapter(mAdapter);

        postData();

    }

    public void postData() {


        //post



        String url = "http://35.163.147.45/api/HospitalMaster/GetHospitalProfile";
        GetAllHospitalsPost getAllHospitalsPost = new GetAllHospitalsPost();
        getAllHospitalsPost.setApproval("1");

        Call<ListOfHospitalsPostResponse> postList = eHospitalApi.getService().listOfHospitalsApi(url,getAllHospitalsPost);


        postList.enqueue(new Callback<ListOfHospitalsPostResponse>() {
            @Override
            public void onResponse(Call<ListOfHospitalsPostResponse> call, Response<ListOfHospitalsPostResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                ListOfHospitalsPostResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1 != null) {


                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    listOfHospitalsArrayList.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfHospitalsArrayList.size() - 1);





                }


            }

            @Override
            public void onFailure(Call<ListOfHospitalsPostResponse> call, Throwable t) {

                Toast.makeText(eListOfHospitals.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }

    public void uploadPresPostData(){


    }







}
