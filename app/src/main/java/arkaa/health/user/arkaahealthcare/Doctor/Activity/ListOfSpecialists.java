package arkaa.health.user.arkaahealthcare.Doctor.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.Datum;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.ListOfSpecializationApi;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.ListOfSpecializationGetResponse;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Doctor.Adapters.SpecializationAdapter;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Doctor.Adapters.SpecializationAdapter;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.Datum;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.ListOfSpecializationApi;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.ListOfSpecializationGetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfSpecialists extends AppCompatActivity  {


    private String token;

    private RecyclerView mRecyclerView;
    private SpecializationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];
    private List<Datum> listOfSpecializationArray ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_doctors1);

        listOfSpecializationArray = new ArrayList<>();




        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SpecializationAdapter(myDataset,1,listOfSpecializationArray);
        mRecyclerView.setAdapter(mAdapter);







        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);

        token = preferences.getString("TOKEN", "123456789");

        getData();






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setQueryHint("Speciallities,Doctors,Clinics,Symptoms");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                Log.v("listSpecialization","onQueryTextSubmit =");

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                Log.v("listSpecialization","onQueryTextChange =");

                return false;
            }
        });



        return true;
    }




    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfSpecialists)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/doctors/specialities";

        Call<ListOfSpecializationGetResponse> postList = ListOfSpecializationApi.getService().specialization(token, url);

        Log.v("SignUp", "(ListOfSpecialists)token for header userProfile get" + token);

        postList.enqueue(new Callback<ListOfSpecializationGetResponse>() {
            @Override
            public void onResponse(Call<ListOfSpecializationGetResponse> call, Response<ListOfSpecializationGetResponse> response) {


//                Toast.makeText(ListOfSpecialists.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfSpecialists)profile get = success");

                ListOfSpecializationGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfSpecialists)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfSpecialists)profile success get =" + postList1.getSuccess());

                    listOfSpecializationArray.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfSpecializationArray.size() - 1);

//                    listOfSpecializationArray = postList1.getData();
//                    listOfSpecializationArray.notifyAll();






                }






            }

            @Override
            public void onFailure(Call<ListOfSpecializationGetResponse> call, Throwable t) {

                Toast.makeText(ListOfSpecialists.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfSpecialists)error in link (get)");


            }
        });


        //post


    }




}
