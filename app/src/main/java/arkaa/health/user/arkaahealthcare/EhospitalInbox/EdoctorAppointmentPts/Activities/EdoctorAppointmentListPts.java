package arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Adapters.EdocAppListPtsAdapter;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Datum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.ElistOfDocAppPtsPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.ElistOfDocAppPtsResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Griddatum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi.EhospitalInboxApi;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Datum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.ElistOfDocAppPtsPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.ElistOfDocAppPtsResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Griddatum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EdoctorAppointmentListPts extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Datum> HospitalDetails;
    List<Griddatum> listOfDocDetails;

    private String hospitalId;
    private String transactionId;
    private String ptsId ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(arkaa.health.user.arkaahealthcare.R.layout.activity_edoctor_appointment_list);

        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        transactionId = getIntent().getStringExtra("TRANSACTION_ID");
        ptsId = getIntent().getStringExtra("PTS_ID");

        HospitalDetails = new ArrayList<>();
        listOfDocDetails = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(arkaa.health.user.arkaahealthcare.R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EdocAppListPtsAdapter(HospitalDetails,listOfDocDetails,EdoctorAppointmentListPts.this);
        mRecyclerView.setAdapter(mAdapter);

        if ((ptsId != null) && (transactionId != null)) {
            postData();
        }

    }

    public void postData() {
        Log.v("EDocApp","postData");

        ElistOfDocAppPtsPost elistOfDocPost = new ElistOfDocAppPtsPost();
//        elistOfDocPost.setPtsId("000004");
//        elistOfDocPost.setTransactionId("00000001");

        elistOfDocPost.setPtsId(ptsId);
        elistOfDocPost.setTransactionId(transactionId);

        String url = "http://35.163.147.45/api/HospitalMaster/GetPtsDoctorConsult";

        Call<ElistOfDocAppPtsResponse> postList = EhospitalInboxApi.getService().listOfDocAppPtsApi(url,elistOfDocPost);


        postList.enqueue(new Callback<ElistOfDocAppPtsResponse>() {
            @Override
            public void onResponse(Call<ElistOfDocAppPtsResponse> call, Response<ElistOfDocAppPtsResponse> response) {


//                Toast.makeText(schedule.this, "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)schedule = success");

                ElistOfDocAppPtsResponse postList1 = response.body();
                Log.v("SignUp", "(post)schedule = status code = " + response.code());
                Log.v("SignUp", "(post)time  = " + response.code());



                if (postList1 != null) {


                    Log.v("SignUp", "(post)schedule get =" + postList1.getMessage());
                    HospitalDetails.addAll(postList1.getData());
                    mAdapter.notifyItemInserted(HospitalDetails.size() - 1);

                    listOfDocDetails.addAll(postList1.getGriddata());
                    mAdapter.notifyItemInserted(listOfDocDetails.size() - 1);





                }


            }

            @Override
            public void onFailure(Call<ElistOfDocAppPtsResponse> call, Throwable t) {

                Toast.makeText(EdoctorAppointmentListPts.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }





}
