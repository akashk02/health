package arkaa.health.user.arkaahealthcare.Lab.RetrofitApi;

import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabCart.LabCartGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.LabAllTestsResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.LabFullProfileGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.UpdateLabPayment.UpdatePaymentLab;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileRetrofit.ProfilePost;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileRetrofit.ProfileResponse;

import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabCart.LabCartGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.LabAllTestsResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.LabFullProfileGetResponse;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.UpdateLabPayment.UpdatePaymentLab;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class LabApi {

    public static final String url = "https://arkaahealthapp.com/api/v1/labs/test1/tests/";


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
        Call<LabFullProfileGetResponse> ListOfLabs(@Header("x-access-token") String authorization, @Url String url);

        @GET
        Call<LabAllTestsResponse> LabAllTests(@Header("x-access-token") String authorization, @Url String url);

        @POST
        Call<LabBookAppointmentResponse> LabBooking(@Header("x-access-token") String authorization, @Url String url, @Body LabBookAppointmentPost user);

        @GET
        Call<LabCartGetResponse> labCartApi(@Header("x-access-token") String authorization, @Url String url);

        @GET
        Call<UpdatePaymentLab> updatePaymentStatusApi(@Header("x-access-token") String authorization, @Url String url);

    }





    //api




}
