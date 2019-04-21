package arkaa.health.user.arkaahealthcare.Profile.RetrofitApi;


import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.FullProfileView.FullProfileGetViewResponse;

import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.FullProfileView.FullProfileGetViewResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public class FullProfileGetViewApi {


    //api

    public static final String url = "https://arkaahealthapp.com/api/v1/users/profiles/135454331545/";


    public static FullProfileGetViewApi.PostService postService = null;

    public static FullProfileGetViewApi.PostService getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(FullProfileGetViewApi.PostService.class);

        }

        return postService;

    }



    public interface PostService{


        @GET
        Call<FullProfileGetViewResponse> FullProfileGetView(@Header("x-access-token") String authorization, @Url String url);

    }





    //api



}
