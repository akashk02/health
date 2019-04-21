package arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi;

import android.content.SharedPreferences;

import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass.GetAppHosPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.BookDoctorAppointment.EbookDoctorAppointmentPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.BookDoctorAppointment.EbookDoctorAppointmentPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.GetDoctorSchedulePost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.GetDoctorScheduleResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDocCategoryModalClass.ListOfDocCategoriesPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.ElistOfDocPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.ElistOfDocPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfHospitalModalClass.GetAllHospitalsPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfHospitalModalClass.ListOfHospitalsPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.UploadHospitalPrescription.UploadPrescriptionHospitalPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.UploadHospitalPrescription.UploadPrescriptionHospitalResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class eHospitalApi {

    //token

    private SharedPreferences sharedPreferences ;




    //token



    //api

    public  static final String url = "https://arkaahealthapp.com/api/v1/UserProfile/";


    public static PostService postService = null;




    public static PostService getService(){

        if(postService == null){

            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();

            Gson gson = builder.create();



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            postService = retrofit.create(PostService.class);

        }

        return postService;

    }



    public interface PostService{

        @POST
        Call<ListOfHospitalsPostResponse> listOfHospitalsApi(@Url String url, @Body GetAllHospitalsPost obj);

        @POST
        Call<ListOfHospitalsPostResponse> listOfAppHospitalsApi(@Url String url, @Body GetAppHosPost obj);

        @POST
        Call<ListOfDocCategoriesPostResponse> listOfDocSpecApi(@Url String url);

        @POST
        Call<ElistOfDocPostResponse> listOfDocApi(@Url String url, @Body ElistOfDocPost obj);

        @POST
        Call<EbookDoctorAppointmentPostResponse> bookDocAppointmentApi(@Url String url, @Body EbookDoctorAppointmentPost obj);

        @POST
        Call<UploadPrescriptionHospitalResponse> uploadHosPresApi(@Url String url, @Body UploadPrescriptionHospitalPost obj);

        @POST
        Call<GetDoctorScheduleResponse> getDocScheduleApi(@Url String url, @Body GetDoctorSchedulePost obj);







    }








    //api




}
