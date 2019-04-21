package arkaa.health.user.arkaahealthcare.Lab.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Lab.Adapters.LabTestCartAdapter;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.LabAllTestsResponse;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import arkaa.health.user.arkaahealthcare.Lab.Adapters.ViewAllTestsAdapter;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Lab.Adapters.LabTestCartAdapter;
import arkaa.health.user.arkaahealthcare.Lab.Adapters.ViewAllTestsAdapter;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.LabAllTestsResponse;
import arkaa.health.user.arkaahealthcare.Lab.RetrofitApi.LabApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewAllTest extends AppCompatActivity {

    private int count1;
    private List<Integer> counterList;

    private TextView selectedTestTextVIew;
    private TextView testPriceTextView;
    private View testCartView;
    private Button contineButton;


    private RecyclerView mRecyclerView;
    private ViewAllTestsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = new String[10];

    private String token;
    private List<Datum> labAllTests;
    private List<Datum> LabAllTestsLocalStorage;


    private String labUniqueIdIntent;

    private List<String> labTestsArrayList;
    private RecyclerView mLabTestCartRecyclerView;
    private RecyclerView.Adapter mLabTestCartAdapter;

    private LinearLayoutManager  mLabTestCartLayoutManager;

    private int testPrice;
    private int selectedTest;
    private int flag;
    private List<Integer> testIdArrayList;

    private LabBookAppointmentPost labBookAppointmentPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_tests);

        labBookAppointmentPost = new LabBookAppointmentPost();


        selectedTestTextVIew = findViewById(R.id.selected_tests);
        testPriceTextView = findViewById(R.id.medicine_price);
        testCartView = findViewById(R.id.test_cart);
        contineButton = findViewById(R.id.button_continue);

        testCartView.setVisibility(View.GONE);

        count1 = 0;
        testPrice = 0;
        selectedTest = 0;
        flag = 0;

        counterList = new ArrayList<>();
        testIdArrayList = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("TOKEN", "123456789");

        labUniqueIdIntent = getIntent().getStringExtra("LAB_ID");
        Log.v("labIdIntent", "LABID =" + labUniqueIdIntent);

        labAllTests = new ArrayList<>();
        LabAllTestsLocalStorage = new ArrayList<>();

        labTestsArrayList = new ArrayList<>();
        labTestsArrayList.add("");
//        labTestsArrayList.remove(0);
//        labTestsArrayList.add("test2");
//        labTestsArrayList.add("test3");


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ViewAllTestsAdapter(myDataset, 1, labAllTests, this);
        mRecyclerView.setAdapter(mAdapter);



        mLabTestCartRecyclerView = findViewById(R.id.order_recycler_view);
        mLabTestCartRecyclerView.setHasFixedSize(true);
        mLabTestCartLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLabTestCartRecyclerView.setLayoutManager(mLabTestCartLayoutManager);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mLabTestCartRecyclerView.getContext(), mLabTestCartLayoutManager.getOrientation());
//        mLabTestCartRecyclerView.addItemDecoration(dividerItemDecoration);

//        mLabTestCartRecyclerView.addItemDecoration(new DividerItemDecoration(viewAllTest.this,
//                DividerItemDecoration.VERTICAL));


        mLabTestCartAdapter = new LabTestCartAdapter(labTestsArrayList);
        mLabTestCartRecyclerView.setAdapter(mLabTestCartAdapter);

        contineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewAllTest.this, Lab_Appointment_Booking.class);
                intent.putIntegerArrayListExtra("TEST_ID", (ArrayList<Integer>) testIdArrayList);
                intent.putExtra("LAB_BOOK_APPOINTMENT_POST_OBJ",labBookAppointmentPost);
                intent.putParcelableArrayListExtra("LAB_ALL_TESTS_LOCAL_STORAGE",(ArrayList<? extends Parcelable>) LabAllTestsLocalStorage);
                intent.putExtra("LAB_UNIQUE_ID",labUniqueIdIntent);
                startActivity(intent);
            }
        });


        getData();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setQueryHint("Search for tests");
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


        String url = "https://arkaahealthapp.com/api/v1/labs/" + labUniqueIdIntent + "/tests";

        Call<LabAllTestsResponse> postList = LabApi.getService().LabAllTests(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<LabAllTestsResponse>() {
            @Override
            public void onResponse(Call<LabAllTestsResponse> call, Response<LabAllTestsResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                LabAllTestsResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfDoctors)profile success get =" + postList1.getSuccess());

                    labAllTests.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(labAllTests.size() - 1);


                }


            }

            @Override
            public void onFailure(Call<LabAllTestsResponse> call, Throwable t) {

                Toast.makeText(viewAllTest.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)error in link (get)");


            }
        });


        //post


    }

    public void count() {
        count1++;
        counterList.add(1);

        Log.v("count", "count  =" + count1 + " counterArrayList size = " + counterList.size());
    }

    public void updateTestCartView(int testId, String testName, int testPrice, int testPosition) {

        if (flag == 0) {

            testCartView.setVisibility(View.VISIBLE);

            Log.v("flag", "ArraySize = " + labTestsArrayList.size());
            labTestsArrayList.remove(labTestsArrayList.size() - 1);
            mLabTestCartAdapter.notifyDataSetChanged();

            flag = 5;
            Log.v("flag", "flag =  " + flag);


        }


        this.selectedTest = this.selectedTest + 1;
        this.testPrice = this.testPrice + testPrice;
        testIdArrayList.add(testId);
        Log.v("arraySizeLab", "price = " + testPrice);

        labTestsArrayList.add(testName);
        mLabTestCartAdapter.notifyItemInserted(labTestsArrayList.size() - 1);
        selectedTestTextVIew.setText("Selected Tests = " + selectedTest);
        testPriceTextView.setText("RS " + this.testPrice);
        Log.v("arraySizeLab", "array suzxe " + labTestsArrayList.size());

        labBookAppointmentPost.setTests(testIdArrayList);
        labBookAppointmentPost.setTotalCost(Integer.toString(this.testPrice));

        LabAllTestsLocalStorage.add(labAllTests.get(testPosition));


    }


}
