package arkaa.health.user.arkaahealthcare.CurrentAppointments.RetrofitApi;


import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments.CurrentAppointmentsGetResponse;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.DoctorAppointmentPayment.DoctorAppointmentPaymentResponse;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.ShareReports.ShareReportsPost;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.ShareReports.ShareReportsResponse;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.appointment.BookAnAppointmentPost;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.appointment.BookAnAppointmentResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;
public class CurrentAppointmentApi {

    //api

    public static final String url = "https://arkaahealthapp.com/api/v1/users/test/appointments/0/";


    public static CurrentAppointmentApi.PostService postService = null;

    public static CurrentAppointmentApi.PostService getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(CurrentAppointmentApi.PostService.class);

        }

        return postService;

    }



    public interface PostService{


        @GET
        Call<CurrentAppointmentsGetResponse> getDocAppApi(@Header("x-access-token") String authorization, @Url String url);

        @POST
        Call<ShareReportsResponse> shareReportsApi(@Header("x-access-token") String authorization, @Url String url, @Body ShareReportsPost user);

        @GET
        Call<DoctorAppointmentPaymentResponse> updatePaymentStatusApi(@Header("x-access-token") String authorization, @Url String url);



    }





    //api



}

