package arkaa.health.user.arkaahealthcare.Profile.RetrofitApi;

import android.content.SharedPreferences;

import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileGetRetrofit.ProfileGetReponse;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileRetrofit.ProfilePost;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileRetrofit.ProfileResponse;

import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileGetRetrofit.ProfileGetReponse;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileRetrofit.ProfilePost;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileRetrofit.ProfileResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class ProfileApi {

    //token

    private SharedPreferences sharedPreferences ;




    //token



    //api

    public  static final String url = "https://arkaahealthapp.com/api/v1/UserProfile/";


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

//        @Headers("Cache-Control: max-age=640000")
        @POST
        Call<ProfileResponse> reqRescreateUser(@Header("x-access-token") String authorization, @Url String url, @Body ProfilePost user);

        @GET
        Call<ProfileGetReponse> profileGetRequest(@Header("x-access-token") String authorization, @Url String url);



    }





    //api











}
