package arkaa.health.user.arkaahealthcare.Profile.RetrofitApi;

import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileEditRetrofit.ProfileEditPut;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileEditRetrofit.ProfileEditResponse;

import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileEditRetrofit.ProfileEditPut;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileEditRetrofit.ProfileEditResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public class ProfileEditApi {

    //api

    public static final String url = "https://arkaahealthapp.com/api/v1/Users/";


    public static ProfileEditApi.PostService postService = null;

    public static ProfileEditApi.PostService getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(ProfileEditApi.PostService.class);

        }

        return postService;

    }



    public interface PostService{


//        @POST
//        Call<SignUpResponse> reqRescreateUser(@Url String url, @Body SignUpPost user);

        @PUT
        Call<ProfileEditResponse> reqRescreateUser(@Header("x-access-token") String authorization, @Url String url, @Body ProfileEditPut user);


    }





    //api


}
