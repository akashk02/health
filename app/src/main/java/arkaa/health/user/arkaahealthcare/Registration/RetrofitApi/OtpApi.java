package arkaa.health.user.arkaahealthcare.Registration.RetrofitApi;

import arkaa.health.user.arkaahealthcare.Registration.ModalClasses.OtpPost;
import arkaa.health.user.arkaahealthcare.Registration.ModalClasses.OtpResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class OtpApi {


    // api


    public static final String url = "https://arkaahealthapp.com/api/v1/verifyOtp/";


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
        Call<OtpResponse> getPostList(@Url String url);

        @POST
        Call<OtpResponse> reqRescreateUser(@Url String url,@Body OtpPost user);

    }




    //api




}
