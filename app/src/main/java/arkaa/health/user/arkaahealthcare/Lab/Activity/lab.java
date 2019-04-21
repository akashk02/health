package arkaa.health.user.arkaahealthcare.Lab.Activity;

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

import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.Datum;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.LabFullProfileGetResponse;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Lab.Adapters.listOfLabAdapter;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.Datum;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.LabFullProfileGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lab extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private listOfLabAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
//    private String[] myDataset = new String[10];
    private List<Datum> listOfLabs;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        listOfLabs = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new listOfLabAdapter(listOfLabs);
        mRecyclerView.setAdapter(mAdapter);

        getData();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setQueryHint("Search For Labs");
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

        Log.v("SignUp1", "(ListOfDoctors)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/lab/profile/0";

        Call<LabFullProfileGetResponse> postList = LabApi.getService().ListOfLabs(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<LabFullProfileGetResponse>() {
            @Override
            public void onResponse(Call<LabFullProfileGetResponse> call, Response<LabFullProfileGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                LabFullProfileGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfDoctors)profile success get =" + postList1.getSuccess());

                    listOfLabs.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfLabs.size() - 1);


                }


            }

            @Override
            public void onFailure(Call<LabFullProfileGetResponse> call, Throwable t) {

                Toast.makeText(lab.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)error in link (get)");


            }
        });


        //post


    }


}
