package arkaa.health.user.arkaahealthcare.Inbox.prescriptions.RetrofitApi;


import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.DoctorPrescriptionResponseAws;

import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.DoctorPrescriptionResponseAws;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public class DoctorPrescriptionApi {

    //api

    public static final String url = "https://arkaahealthapp.com/api/v1/users/profiles/135454331545/";


    public static PostService postService = null;

    public static PostService getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostService.class);

        }

        return postService;

    }



    public interface PostService{


        @GET
        Call<DoctorPrescriptionResponseAws> getAllPrescriptions(@Header("x-access-token") String authorization, @Url String url);

    }





    //api



}
