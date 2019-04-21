package arkaa.health.user.arkaahealthcare.Login.RetrofitApi;

import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.PostLogin;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.ResponseLogin;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.LoginSession.LoginSessionGet;

import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.PostLogin;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login.ResponseLogin;
import arkaa.health.user.arkaahealthcare.Login.ModalClasses.LoginSession.LoginSessionGet;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class ArkaaLoginApi {

    public static final String url = "https://arkaahealthapp.com/api/v1/authenticate/test/test/";


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
        Call<ResponseLogin> getPostList(@Url String url);

        @POST
        Call<ResponseLogin> reqRescreateUser(@Url String url,@Body PostLogin user);

        @GET
        Call<LoginSessionGet> getLoginSessionApi(@Url String url);




    }


}
