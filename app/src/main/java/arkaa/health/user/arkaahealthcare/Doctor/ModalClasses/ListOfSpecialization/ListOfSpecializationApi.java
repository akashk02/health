package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public class ListOfSpecializationApi {

    //api

    public static final String url = "https://arkaahealthapp.com/api/v1/doctors/specialities/";


    public static ListOfSpecializationApi.PostService postService = null;

    public static ListOfSpecializationApi.PostService getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(ListOfSpecializationApi.PostService.class);

        }

        return postService;

    }



    public interface PostService{


        @GET
        Call<ListOfSpecializationGetResponse> specialization(@Header("x-access-token") String authorization, @Url String url);

    }





    //api



}
