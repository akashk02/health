package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Adapter.ListOfQuatationMedicinesAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Adapter.PharmacyOrderDetailsAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.MedicineDetail;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.MedicineInfo;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyCartBeta;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyOrderDetails;
import arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.listOfMedicinesCartAdapter;
import arkaa.health.user.arkaahealthcare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Adapter.PharmacyOrderDetailsAdapter;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.MedicineDetail;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PharmacyOrederDetails extends AppCompatActivity {

    @BindView(R.id.pharmay_name)
    TextView pharNameTv;

    @BindView(R.id.pharmacy_unique_id)
    TextView pharUniqueTv;

    @BindView(R.id.pharmacy_address)
    TextView pharAddTv;

    @BindView(R.id.pharmacy_order_date)
    TextView pharOrderDateTv;

    @BindView(R.id.selected_tests)
    TextView pharTotalMedTv;

    @BindView(R.id.total_price)
    TextView pharTotalPriceTv;
//
//    @BindView(R.id.confirm_booking_card_view)
//    TextView confirmBookingCardView;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private Datum pharmacyOrderDetails;
    List<MedicineDetail> listOfQuatationMedicinesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_oreder_details);

        ButterKnife.bind(this);

        pharmacyOrderDetails = getIntent().getParcelableExtra("ORDER_DETAILS");

        listOfQuatationMedicinesArrayList = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.test_review_recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PharmacyOrderDetailsAdapter(listOfQuatationMedicinesArrayList, PharmacyOrederDetails.this, "");
        mRecyclerView.setAdapter(mAdapter);

        if(pharmacyOrderDetails != null){

            String pharmacyName = ""+pharmacyOrderDetails.getPharmacyName();
            String pharmacyUid = ""+pharmacyOrderDetails.getPharmacyUid();
            String pharmacyAddress = ""+pharmacyOrderDetails.getAddress();
            Log.v("urlOrder","address"+pharmacyOrderDetails.getAddress());
            String pharmacyOrderDate = ""+getDate(pharmacyOrderDetails.getCreatedOn());

            pharNameTv.setText(pharmacyName);
            pharUniqueTv.setText(pharmacyUid);
            pharAddTv.setText(pharmacyAddress);
            pharOrderDateTv.setText(pharmacyOrderDate);


            if(pharmacyOrderDetails.getMedicineDetail() != null){
                String totalMedicines = "Total Medicines = "+pharmacyOrderDetails.getMedicineDetail().size();
                String totalCost = "RS "+pharmacyOrderDetails.getCost();
                Log.v("urlOrder","Total Medicines"+pharmacyOrderDetails.getMedicineDetail().size());


                pharTotalPriceTv.setText(totalCost);
                pharTotalMedTv.setText(totalMedicines);



                listOfQuatationMedicinesArrayList.addAll(pharmacyOrderDetails.getMedicineDetail());
                mAdapter.notifyItemInserted(listOfQuatationMedicinesArrayList.size() - 1);


            }





        }





    }

    public String getDate(String realTime) {

        String str ;
        try {

            String time = realTime.substring(0, realTime.indexOf("."));

            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            //   String outputPattern = "dd-MMM-yyyy h:mm a";
            String outputPattern = "dd-MMM-yyyy";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
                Log.v("new_time", "date = " + date + "str = " + str);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("new_time", "exception " + e.getMessage());
            }

        }catch (Exception e){

            str = realTime;

        }



        Log.v("new_time","str = "+str);
        return str;


    }

}
