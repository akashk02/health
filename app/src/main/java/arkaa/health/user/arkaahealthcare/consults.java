package arkaa.health.user.arkaahealthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Doctor.Adapters.ListOfDoctorsAdapter;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Datum;

public class consults extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];

    //forcedchange

    private List<Datum> listOfDoctorsArray ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consults);

        listOfDoctorsArray = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListOfDoctorsAdapter(myDataset,2,listOfDoctorsArray,1,consults.this);
        mRecyclerView.setAdapter(mAdapter);



    }
}
