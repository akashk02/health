package arkaa.health.user.arkaahealthcare.Inbox.LabReports.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Inbox.LabReports.Adapter.ListOfLabReportsAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses.LabReportsGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.RetrofitApi.LabReportsApi;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfLabReports extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Datum> listOflabReport ;
    private String token;
    private String patientUniueId;
    private String classType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_lab_reports);

        classType = getIntent().getStringExtra("CLASS_TYPE");
        if(classType == null){
            classType = "";
        }
        listOflabReport = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListOfLabReportsAdapter(listOflabReport,classType,ListOfLabReports.this);
        mRecyclerView.setAdapter(mAdapter);

        getData();

    }

    public void getData() {



        String url = "https://arkaahealthapp.com/api/v1/labs/reports/"+patientUniueId;


        Call<LabReportsGetResponse> postList = LabReportsApi.getService().listOfLabreportsApi(token, url);

        Log.v("SignUp", "(post)token for header userProfile get" + token);

        postList.enqueue(new Callback<LabReportsGetResponse>() {
            @Override
            public void onResponse(Call<LabReportsGetResponse> call, Response<LabReportsGetResponse> response) {


//                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)profile get = success");

                LabReportsGetResponse postList1 = response.body();
                Log.v("SignUp", "(post)profile get = status code = " + response.code());


                if (postList1 != null) {

                    listOflabReport.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOflabReport.size() - 1);


                }


            }

            @Override
            public void onFailure(Call<LabReportsGetResponse> call, Throwable t) {

                Toast.makeText(ListOfLabReports.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)error in link (get)"+t.getMessage());



            }
        });


        //post


    }



}
