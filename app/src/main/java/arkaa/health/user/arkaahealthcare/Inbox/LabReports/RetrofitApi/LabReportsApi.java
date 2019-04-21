package arkaa.health.user.arkaahealthcare.Inbox.LabReports.RetrofitApi;


import arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses.LabReportsGetResponse;

import arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses.LabReportsGetResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public class LabReportsApi {

    //api

    public static final String url = "https://arkaahealthapp.com/api/v1/labs/reports/test/";


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
        Call<LabReportsGetResponse> listOfLabreportsApi(@Header("x-access-token") String authorization, @Url String url);

    }





    //api

}
