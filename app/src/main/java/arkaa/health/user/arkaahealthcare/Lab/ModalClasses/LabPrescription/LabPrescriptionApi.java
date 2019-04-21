package arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabPrescription;



import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class LabPrescriptionApi {




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
        Call<LabPrescriptionResponse> labPrescriptionShareApi(@Header("x-access-token") String authorization, @Url String url, @Body LabPrescriptionPost user);




    }








    //api




}
