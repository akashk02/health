package arkaa.health.user.arkaahealthcare.ForgotPassword.RestApi;


import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.ResetPasswordPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.ResetPasswordResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.SendOtpPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.SendOtpResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpResponse;

import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.ResetPasswordResponse;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpPost;
import arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass.VerifyOtpResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class ForgotPasswordApi {

    public static final String url = "https://arkaahealthapp.com/api/v1/forgotpassword/";


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

        @POST
        Call<SendOtpResponse> sendOtpApi(@Url String url, @Body SendOtpPost user);

        @POST
        Call<VerifyOtpResponse> verifyOtpApi(@Url String url, @Body VerifyOtpPost user);

        @POST
        Call<ResetPasswordResponse> resetPassApi(@Header("x-access-token") String authorization, @Url String url, @Body ResetPasswordPost user);





    }


}
