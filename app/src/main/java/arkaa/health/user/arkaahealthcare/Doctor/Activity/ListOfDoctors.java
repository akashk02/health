package arkaa.health.user.arkaahealthcare.Doctor.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Datum;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorDetailsResponse;
import arkaa.health.user.arkaahealthcare.Doctor.RetrofitApi.DoctorListApi;
import arkaa.health.user.arkaahealthcare.Doctor.Adapters.ListOfDoctorsAdapter;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Doctor.Adapters.ListOfDoctorsAdapter;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Datum;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorDetailsResponse;
import arkaa.health.user.arkaahealthcare.Doctor.RetrofitApi.DoctorListApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ListOfDoctors extends AppCompatActivity {

    private String token;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];
    private int specializationId;

    private List<Datum> listOfDoctorsArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctormain);

        listOfDoctorsArray = new ArrayList<>();
        specializationId = getIntent().getIntExtra("SPECIALIZATION_ID",1);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListOfDoctorsAdapter(myDataset,1,listOfDoctorsArray,specializationId,ListOfDoctors.this);
        mRecyclerView.setAdapter(mAdapter);


        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);

        token = preferences.getString("TOKEN", "123456789");

        getData();





    }

    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfDoctors)  GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/doctors/specialities/"+specializationId;
        Log.v("listofDo","url = "+url);


        Call<DoctorDetailsResponse> postList = DoctorListApi.getService().DoctorListGetView(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<DoctorDetailsResponse>() {
            @Override
            public void onResponse(Call<DoctorDetailsResponse> call, Response<DoctorDetailsResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                DoctorDetailsResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfDoctors)profile success get =" + postList1.getSuccess());

                    listOfDoctorsArray.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfDoctorsArray.size() - 1);







                }






            }

            @Override
            public void onFailure(Call<DoctorDetailsResponse> call, Throwable t) {

                Toast.makeText(ListOfDoctors.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)error in link (get)");
                Log.v("listofDo","error = "+t.getMessage());
                Log.v("listofDo","error = "+t.getStackTrace());
                Log.v("listofDo","error = "+t.getLocalizedMessage());




            }
        });


        //post


    }










}
