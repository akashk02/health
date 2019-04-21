package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.RetrofitApi;



import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.ListOfLabAppointmentsGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation.LabQuotationConfirmationPost;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation.LabQuotationConfirmationResponse;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.ViewLabQuotationGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.ViewLabQuotationGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationPost;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationResponse;

import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.ListOfLabAppointmentsGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation.LabQuotationConfirmationPost;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation.LabQuotationConfirmationResponse;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.ViewLabQuotationGetResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class LabAppointmentApi {

    public static final String url = "https://arkaahealthapp.com/api/v1/labs/appointments/test/0/";


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
        Call<ListOfLabAppointmentsGetResponse> ListOfLapAppointmentApi(@Header("x-access-token") String authorization, @Url String url);

        @GET
        Call<ViewLabQuotationGetResponse> viewLabQuotationApi(@Header("x-access-token") String authorization, @Url String url);

        @POST
        Call<LabQuotationConfirmationResponse> LabQuatationConfirmationApi(@Header("x-access-token") String authorization, @Url String url, @Body LabQuotationConfirmationPost obj );


    }





    //api







}
