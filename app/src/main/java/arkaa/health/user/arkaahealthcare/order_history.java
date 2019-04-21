package arkaa.health.user.arkaahealthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfPharmacyAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.Datum;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfPharmacyAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.Datum;

public class order_history extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];

    private List<Datum> listOfPharmacyArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        listOfPharmacyArrayList = new ArrayList<>();



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new listOfPharmacyAdapter(myDataset,2,listOfPharmacyArrayList);
        mRecyclerView.setAdapter(mAdapter);




    }
}
