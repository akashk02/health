package arkaa.health.user.arkaahealthcare.eHospital.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.eHospital.Adapters.eListOfDocSpecializationAdapter;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDocCategoryModalClass.Datum;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDocCategoryModalClass.ListOfDocCategoriesPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.eHospital.Adapters.eListOfDocSpecializationAdapter;
import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class eListOfDoctorCategories extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];
    private List<Datum> listOfSpecializationArray ;
    private String hospitalId;
    private String hospitalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_doctor_categories);

        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        if(getIntent().getStringExtra("HOSPITAL_NAME") != null) {
            hospitalName = getIntent().getStringExtra("HOSPITAL_NAME");
        }else {
            hospitalName = "";
        }
        listOfSpecializationArray = new ArrayList<>();




        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new eListOfDocSpecializationAdapter(listOfSpecializationArray,hospitalId,hospitalName);
        mRecyclerView.setAdapter(mAdapter);

        postData();


    }

    public void postData() {


        //post



        String url = "http://35.163.147.45/api/HospitalMaster/GetDrSpecialistzation";

        Call<ListOfDocCategoriesPostResponse> postList = eHospitalApi.getService().listOfDocSpecApi(url);


        postList.enqueue(new Callback<ListOfDocCategoriesPostResponse>() {
            @Override
            public void onResponse(Call<ListOfDocCategoriesPostResponse> call, Response<ListOfDocCategoriesPostResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                ListOfDocCategoriesPostResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1 != null) {


                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    listOfSpecializationArray.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfSpecializationArray.size() - 1);





                }


            }

            @Override
            public void onFailure(Call<ListOfDocCategoriesPostResponse> call, Throwable t) {

                Toast.makeText(eListOfDoctorCategories.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }






}
