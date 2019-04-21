package arkaa.health.user.arkaahealthcare.EhospitalInbox.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.Adapter.EinboxMessageAdapter;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

public class EinboxMessages extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> myDataset;

    private String hospitalId;
    private String transactionId;
    private String ptsId ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_einbox_messages);

        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        transactionId = getIntent().getStringExtra("TRANSACTION_ID");
        ptsId = getIntent().getStringExtra("PTS_ID");

//        hospitalId = "201809170001";
//        transactionId = "00000001";
//        ptsId = "000004";

        Log.v("EinboxMessagesIDS","hospitalID"+hospitalId);
        Log.v("EinboxMessagesIDS","transactionId"+transactionId);
        Log.v("EinboxMessagesIDS","ptsId"+ptsId);


        myDataset = new ArrayList<>();
      //  myDataset.add("Current Appointments");
        myDataset.add("Current Appointments (PTS)");
      //  myDataset.add("Prescriptions");
        myDataset.add("Lab Appointment");
        myDataset.add("Pharmacy Orders");

        mRecyclerView = (RecyclerView) findViewById(arkaa.health.user.arkaahealthcare.R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new EinboxMessageAdapter(myDataset,1,hospitalId,transactionId,ptsId);
        mRecyclerView.setAdapter(mAdapter);


    }
}
