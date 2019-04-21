package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Adapter.ListOfPharmacyOrderAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.ListOfMedicinesOrderGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.RetrofitApi.PharmacyOrderApi;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Adapter.ListOfPharmacyOrderAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.ListOfMedicinesOrderGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.RetrofitApi.PharmacyOrderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfPharmacyOrders extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Datum> listOfLabAppointmentsArrayList ;
    private String token;
    private String patientUniueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_pharmacy_orders);

        listOfLabAppointmentsArrayList = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListOfPharmacyOrderAdapter(listOfLabAppointmentsArrayList);
        mRecyclerView.setAdapter(mAdapter);

        getData();

    }

    public void getData() {


        //post

        Log.v("SignUp1", "(ListOfDoctors)GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/pharmacy/orders/userview/"+patientUniueId;
        Log.v("urlOrder","url = "+url);

        Call<ListOfMedicinesOrderGetResponse> postList = PharmacyOrderApi.getService().ListOfPharmacyOrderApi(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<ListOfMedicinesOrderGetResponse>() {
            @Override
            public void onResponse(Call<ListOfMedicinesOrderGetResponse> call, Response<ListOfMedicinesOrderGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                ListOfMedicinesOrderGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfDoctors)profile success get =" + postList1.getSuccess());

                    listOfLabAppointmentsArrayList.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfLabAppointmentsArrayList.size() - 1);


                }


            }

            @Override
            public void onFailure(Call<ListOfMedicinesOrderGetResponse> call, Throwable t) {

                Toast.makeText(ListOfPharmacyOrders.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)error in link (get)");


            }
        });


        //post


    }





}
