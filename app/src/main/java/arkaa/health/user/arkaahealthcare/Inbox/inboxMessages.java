package arkaa.health.user.arkaahealthcare.Inbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Adapters.InboxMessagesAdapter;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Adapters.InboxMessagesAdapter;

public class inboxMessages extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_messages);
        myDataset = new ArrayList<>();
        myDataset.add("E-Hospital");
        myDataset.add("Prescriptions");
        myDataset.add("Lab Appointments");
        myDataset.add("Pharmacy Orders");
        myDataset.add("Lab Reports");


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new InboxMessagesAdapter(myDataset,1);
        mRecyclerView.setAdapter(mAdapter);





    }
}
