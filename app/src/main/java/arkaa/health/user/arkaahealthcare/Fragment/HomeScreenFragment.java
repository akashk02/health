package arkaa.health.user.arkaahealthcare.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Activity.ListOfEHosApp;
import arkaa.health.user.arkaahealthcare.Doctor.Activity.ListOfSpecialists;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Activity.ListOfDocAppointments;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities.currentAppointment;
import arkaa.health.user.arkaahealthcare.Inbox.inboxMessages;
import arkaa.health.user.arkaahealthcare.Lab.Activity.lab;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.ListOfPharmacies;
import arkaa.health.user.arkaahealthcare.eHospital.Activities.eListOfHospitals;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileGetRetrofit.ProfileGetReponse;
import arkaa.health.user.arkaahealthcare.Profile.RetrofitApi.ProfileApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeScreenFragment extends Fragment {


    public HomeScreenFragment() {
        // Required empty public constructor
    }


    private listViewAdapter mAdapter;
    private ArrayList<healthInfo> data = new ArrayList<>();

    private ImageView imageView0;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;

    private String loginUserName;
    private String token;
    private SharedPreferences sharedPreferences ;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        View view = inflater.inflate(R.layout.fragment_home_screen_alpha, container, false);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE},100);
        }










        data.add(new healthInfo("Book an appointment","Find doctors,clinic,hospitals and more"));
        data.add(new healthInfo("order medicines","Get medicines deliver to your doorstep"));
        data.add(new healthInfo("Book tests and scans","Find trusted diagnostic labs near you"));
        data.add(new healthInfo("E-hospital","Find doctors,clinic,hospitals and more"));
       // data.add(new healthInfo("Allied Services","Personal care,mobility and more"));
       // data.add(new healthInfo("Set reminders","Get alert so you never miss an appointment"));
        data.add(new healthInfo("Current Appointments","List of all appointments"));
        data.add(new healthInfo("Current Appointments (E-Hospital)","List of all appointments"));
       // data.add(new healthInfo("Order History",""));
       // data.add(new healthInfo("Consults ",""));
        data.add(new healthInfo("Inbox ",""));






        ListView homeScreenListView = (ListView) view.findViewById(R.id.list);
        mAdapter = new listViewAdapter(getActivity(),data);
        homeScreenListView.setAdapter(mAdapter);

        AdapterView.OnClickListener onClickListener = new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),ListOfDoctors.class);
//                startActivity(intent);

                Intent intent = new Intent(getContext(),ListOfSpecialists.class);
                startActivity(intent);
            }
        };



        homeScreenListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if(i==0){
                    Intent intent = new Intent(getActivity(),ListOfSpecialists.class);
                    startActivity(intent);
                }

                if(i==1){
                    Intent intent = new Intent(getActivity(),ListOfPharmacies.class);
                    startActivity(intent);
                }

                if(i==2){
                    Intent intent = new Intent(getActivity(), lab.class);
                    startActivity(intent);
                }

                if(i==3){
                    Intent intent = new Intent(getActivity(), eListOfHospitals.class);
                    startActivity(intent);
                }



//                if(i==4){
//                    Intent intent = new Intent(getActivity(), alliedServices.class);
//                    startActivity(intent);
//                }

//                if(i==5){
//                    Intent intent = new Intent(getActivity(), reminder.class);
//                    startActivity(intent);
//                }

                if(i==4){
                    Intent intent = new Intent(getActivity(), currentAppointment.class);
                    startActivity(intent);
                }

//                if(i==7){
//                    Intent intent = new Intent(getActivity(), order_history.class);
//                    startActivity(intent);
//                }
//
//                if(i==8){
//                    Intent intent = new Intent(getActivity(), consults.class);
//                    startActivity(intent);
//                }
                if(i==5){
                    Intent intent = new Intent(getActivity(), ListOfEHosApp.class);
                    startActivity(intent);
                }

                if(i==6){
                    Intent intent = new Intent(getActivity(), inboxMessages.class);
                    startActivity(intent);
                }


//
//
//
//
//

            }
        });

        imageView0 = view.findViewById(R.id.imageview0);
        imageView1 = view.findViewById(R.id.imageview1);
        imageView2 = view.findViewById(R.id.imageview2);
        imageView3 = view.findViewById(R.id.imageview3);
        imageView4 = view.findViewById(R.id.imageview4);
        imageView5 = view.findViewById(R.id.imageview5);

        imageView0.setOnClickListener(onClickListener);
        imageView1.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        imageView3.setOnClickListener(onClickListener);
        imageView4.setOnClickListener(onClickListener);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ListOfSpecialists.class);
                startActivity(intent);
            }
        });







        SharedPreferences preferences = this.getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        token = preferences.getString("TOKEN", "123456789");
        loginUserName = preferences.getString("USERNAME", "");
        Log.v("SignUp", "Token userProfile = " + token + "  loginUSerName = " + loginUserName);

      //  getData();






        return view;






    }

    public void getData() {


        //post

        Log.v("homescreenfragment", "GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/users/" + loginUserName + "/";
        Log.v("homescreenfragment","url "+loginUserName);

        Call<ProfileGetReponse> postList = ProfileApi.getService().profileGetRequest(token, url);

        Log.v("homescreenfragment", "(post)token for header userProfile get" + token);

        postList.enqueue(new Callback<ProfileGetReponse>() {
            @Override
            public void onResponse(Call<ProfileGetReponse> call, Response<ProfileGetReponse> response) {


//                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                Log.v("homescreenfragment", "(post)profile get = success");

                ProfileGetReponse postList1 = response.body();
                Log.v("homescreenfragment", "(post)profile get = status code = " + response.code());


                if (postList1 != null) {

                    String uniqueId = postList1.getData().get(0).getUniqueId();

                    sharedPreferences = getContext().getSharedPreferences("token",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("USERUNIQUEID",uniqueId );
                    Log.v("homescreenfragment","unique id"+uniqueId);
                    editor.commit();

                    Log.v("uniqueIdSequence","HomeScreenFragment  ");





                }







            }

            @Override
            public void onFailure(Call<ProfileGetReponse> call, Throwable t) {

                Toast.makeText(getContext(), "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("homescreenfragment", "(post)error in link (get)");


            }
        });


        //post


    }






}
