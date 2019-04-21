package arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Adapter.ListOfDocAppAdapter;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass.Datum;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass.ListOfDocAppPost;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass.ListOfDocAppResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi.EhospitalInboxApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfDocAppointments extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Datum> lisoOfDocAppointments;

    private String hospitalId;
    private String userUniqueId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_list_of_doc_appointments);

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        userUniqueId = preferences.getString("USERUNIQUEID", null);
        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        Log.v("EDocApp","hospitalAppointment ="+hospitalId);

        lisoOfDocAppointments = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(arkaa.health.user.arkaahealthcare.R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListOfDocAppAdapter(lisoOfDocAppointments,ListOfDocAppointments.this);
        mRecyclerView.setAdapter(mAdapter);

        try {
            if ((userUniqueId != null) && (hospitalId != null)) {
                if(!hospitalId.equals(""))
                { postData();
                }else {
                    Toast.makeText(ListOfDocAppointments.this,"Sorry, No data available",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(ListOfDocAppointments.this,"Sorry, No data available",Toast.LENGTH_SHORT).show();
                }
        }catch (Exception e){
            Toast.makeText(ListOfDocAppointments.this,"Sorry, No data available",Toast.LENGTH_SHORT).show();
            Log.v("tryCatch","ListOfDocAppointments Exception = "+e.getMessage());
        }


        }




    public void postData() {
        Log.v("EDocApp","postData");

        ListOfDocAppPost listOfDocAppPost = new ListOfDocAppPost();
//        elistOfDocPost.setPtsId("000004");
//        elistOfDocPost.setTransactionId("00000001");

        listOfDocAppPost.setHospitalId(hospitalId);
//        listOfDocAppPost.setHospitalId("");
        listOfDocAppPost.setPatientId(userUniqueId);
        Log.v("ehospatientuid","uid = "+userUniqueId);


        String url = "http://35.163.147.45/api/HospitalMaster/GetPatientAppointments";



        Call<ListOfDocAppResponse> postList = EhospitalInboxApi.getService().listOfDocAppApi(url,listOfDocAppPost);


        postList.enqueue(new Callback<ListOfDocAppResponse>() {
            @Override
            public void onResponse(Call<ListOfDocAppResponse> call, Response<ListOfDocAppResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                ListOfDocAppResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1 != null) {

                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    lisoOfDocAppointments.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(lisoOfDocAppointments.size() - 1);

                    }


            }

            @Override
            public void onFailure(Call<ListOfDocAppResponse> call, Throwable t) {

                Toast.makeText(ListOfDocAppointments.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }

}
