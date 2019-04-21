package arkaa.health.user.arkaahealthcare.Login.ModalClasses.ResendOtpRetrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class resendOtpApi {


    // api


    public static final String url = "https://arkaahealthapp.com/api/v1/resendOtp/";


    public static resendOtpApi.PostService postService = null;

    public static resendOtpApi.PostService getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(resendOtpApi.PostService.class);

        }

        return postService;

    }



    public interface PostService{


        @POST
        Call<ResendOtpResponse> reqRescreateUser(@Url String url, @Body ResendOtpPost user);

    }




    //api



}
