package arkaa.health.user.arkaahealthcare.Pharmacy.Activity;

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
import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.Medicine;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.PharmacyMedicinesGetResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.Medicine;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.PharmacyMedicinesGetResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class view_all_medicines extends AppCompatActivity {


    private TextView selectedMedicineTextVIew;
    private TextView MedicinePriceTextView;
    private View medicineCartView;
    private Button contineButton;

    private RecyclerView mRecyclerView;
    private listOfMedicinesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //    private String[] myDataset = new String[10];
    private List<Datum> listOfMedicines ;
    private List<Datum> listOfMedicinesLocalStorage;
    private String token;
    private String pahrmacyUniqueIdIntent;


    private List<String> pharmacyMedicineArrayList;
    private RecyclerView mPharmacyMedicineRecyclerView;
    private RecyclerView.Adapter mPharmacyMedicineCartAdapter;

    private RecyclerView.LayoutManager mPharmacyMedicineCartLayoutManager;

    private Double medicinePrice;
    private int selectedMedicines;
    private int flag;
    private List<Medicine> medicineIdArrayList ;

    private PharmacyOrderMedicinePost pharmacyOrderMedicinePost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_medicines);

        pharmacyOrderMedicinePost = new PharmacyOrderMedicinePost();


        selectedMedicineTextVIew = findViewById(R.id.selected_tests);
        MedicinePriceTextView = findViewById(R.id.medicine_price);
        medicineCartView = findViewById(R.id.test_cart);
        contineButton = findViewById(R.id.button_continue);

        medicineCartView.setVisibility(View.GONE);

        medicinePrice = 0.0;
        selectedMedicines = 0;
        flag = 0;

        medicineIdArrayList = new ArrayList<>();

        pharmacyMedicineArrayList = new ArrayList<>();
        pharmacyMedicineArrayList.add("");


        pahrmacyUniqueIdIntent = getIntent().getStringExtra("PHARMACY_UNIQUE_ID");
        listOfMedicines = new ArrayList<>();
        listOfMedicinesLocalStorage = new ArrayList<>();

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
        mAdapter = new listOfMedicinesAdapter(listOfMedicines,view_all_medicines.this,"");
        mRecyclerView.setAdapter(mAdapter);


        mPharmacyMedicineRecyclerView = findViewById(R.id.order_recycler_view);
        mPharmacyMedicineRecyclerView.setHasFixedSize(true);

        mPharmacyMedicineCartLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mPharmacyMedicineRecyclerView.setLayoutManager(mPharmacyMedicineCartLayoutManager);

        mPharmacyMedicineCartAdapter = new LabTestCartAdapter(pharmacyMedicineArrayList);
        mPharmacyMedicineRecyclerView.setAdapter(mPharmacyMedicineCartAdapter);

        contineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_all_medicines.this,PharmacyOrderDetailsBeta.class);
                intent.putExtra("PHARMACY_UNIQUE_ID",pahrmacyUniqueIdIntent);
                intent.putExtra("PHARMACY_ORDER_MEDICINE_POST", pharmacyOrderMedicinePost);
                intent.putParcelableArrayListExtra("LIST_OF_MEDICINES_LOCAL_STORAGE", (ArrayList<? extends Parcelable>) listOfMedicinesLocalStorage);
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

        searchView.setQueryHint("What are you looking to buy?");
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

        Log.v("SignUp1", "(ListOfDoctors)  GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/pharmacy/"+pahrmacyUniqueIdIntent+"/medicine/0";
        Log.v("viewAllMEdi","url = "+url);


        Call<PharmacyMedicinesGetResponse> postList = listOfPharmacyApi.getService().listofMedicinesApi(token, url);

        Log.v("SignUp", "(ListOfDoctors)token for header userProfile get" + token);

        postList.enqueue(new Callback<PharmacyMedicinesGetResponse>() {
            @Override
            public void onResponse(Call<PharmacyMedicinesGetResponse> call, Response<PharmacyMedicinesGetResponse> response) {


//                Toast.makeText(ListOfDoctors.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)profile get = success");

                PharmacyMedicinesGetResponse postList1 = response.body();
                Log.v("SignUp", "(ListOfDoctors)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(ListOfDoctors)profile success get =" + postList1.getSuccess());

                    listOfMedicines.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(listOfMedicines.size() - 1);







                }






            }

            @Override
            public void onFailure(Call<PharmacyMedicinesGetResponse> call, Throwable t) {

                Toast.makeText(view_all_medicines.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(ListOfDoctors)error in link (get)");


            }
        });


        //post


    }


    public void updateMedicineCartView(int medicineId, String medicineName,Double medicinePrice, int medicinePosition) {

        if (flag == 0) {

            medicineCartView.setVisibility(View.VISIBLE);

            Log.v("flag", "ArraySize = " + pharmacyMedicineArrayList.size());
            pharmacyMedicineArrayList.remove(pharmacyMedicineArrayList.size() - 1);
            mPharmacyMedicineCartAdapter.notifyDataSetChanged();

            flag = 5;
            Log.v("flag", "flag =  " + flag);


        }


        this.selectedMedicines = this.selectedMedicines + 1;
        Log.v("viewAllMEdi","value of this.medicine price = "+this.medicinePrice+" value of medicinePrice ="+medicinePrice);
        this.medicinePrice = this.medicinePrice + medicinePrice;

        Medicine medicine  = new Medicine();
        medicine.setQuantity(1);
        medicine.setMediId(medicineId);
        medicineIdArrayList.add(medicine);


       // testIdArrayList.add(testId);
        Log.v("arraySizeLab", "price = " + medicinePrice);

        pharmacyMedicineArrayList.add(medicineName);
        mPharmacyMedicineCartAdapter.notifyItemInserted(pharmacyMedicineArrayList.size() - 1);
        selectedMedicineTextVIew.setText("Selected Tests = " + selectedMedicines);
        MedicinePriceTextView.setText("RS " + this.medicinePrice);
        Log.v("arraySizeLab", "array suzxe " + pharmacyMedicineArrayList.size());

      //  labBookAppointmentPost.setTests(testIdArrayList);
       // labBookAppointmentPost.setTotalCost(Integer.toString(this.testPrice));

        pharmacyOrderMedicinePost.setMedicines(medicineIdArrayList);
        pharmacyOrderMedicinePost.setCost(""+this.medicinePrice);
     //   pharmacyOrderMedicinePost.setCost("0");



        listOfMedicinesLocalStorage.add(listOfMedicines.get(medicinePosition));


    }





}
