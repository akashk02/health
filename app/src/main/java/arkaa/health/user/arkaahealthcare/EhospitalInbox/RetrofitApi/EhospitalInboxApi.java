package arkaa.health.user.arkaahealthcare.EhospitalInbox.RetrofitApi;

import android.content.SharedPreferences;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.GetDocPresPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.GetDocPresPostResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.ElabAppointmentPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.ElabAppointmentResponse;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass.ListOfDocAppPost;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass.ListOfDocAppResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.DoctorDetails.GeneralDocDetailsPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.DoctorDetails.GeneralDocDetailsResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.ElistOfDocAppPtsPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.ElistOfDocAppPtsResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.ModalClass.ListOfPtsPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.ModalClass.ListOfPtsResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.EgetPharmacyQuotationPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.EgetPharmacyQuotationPostResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.AppointmentPayment.GetDocAppPaymentPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.AppointmentPayment.GetDocAppPaymentResponse;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment.EhosChPayStatusPost;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment.EhosChPayStatusResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class EhospitalInboxApi {

    //token

    private SharedPreferences sharedPreferences ;




    //token



    //api

    public  static final String url = "https://arkaahealthapp.com/api/v1/UserProfile/";


    public static EhospitalInboxApi.PostService postService = null;




    public static EhospitalInboxApi.PostService getService(){

        if(postService == null){

            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();

            Gson gson = builder.create();



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            postService = retrofit.create(EhospitalInboxApi.PostService.class);

        }

        return postService;

    }



    public interface PostService{

        @POST
        Call<GetDocPresPostResponse> eListOfDocPres(@Url String url, @Body GetDocPresPost obj);

        @POST
        Call<EgetPharmacyQuotationPostResponse> ePharmacyQuotationApi(@Url String url, @Body EgetPharmacyQuotationPost obj);

        @POST
        Call<ElistOfDocAppPtsResponse> listOfDocAppPtsApi(@Url String url, @Body ElistOfDocAppPtsPost obj);

        @POST
        Call<ListOfDocAppResponse> listOfDocAppApi(@Url String url, @Body ListOfDocAppPost obj);

        @POST
        Call<GeneralDocDetailsResponse> GeneralDocDetailsApi(@Url String url, @Body GeneralDocDetailsPost obj);

        @POST
        Call<ListOfPtsResponse> listOfPtsApi(@Url String url, @Body ListOfPtsPost obj);

        @POST
        Call<ElabAppointmentResponse> eLabAppointmentApi(@Url String url, @Body ElabAppointmentPost obj);

        @POST
        Call<EhosChPayStatusResponse> eHosChangePaymentStatus(@Url String url, @Body EhosChPayStatusPost obj);

        @POST
        Call<GetDocAppPaymentResponse> eHosChangeDocAppPaymentStatus(@Url String url, @Body GetDocAppPaymentPost obj);



    }








    //api




}

