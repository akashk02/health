package arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi;

import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentPost;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking.LabBookAppointmentResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus.ChangePharmacyPaymentStatusPost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus.ChangePharmacyPaymentStatusResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.ListOfPharmacyGetResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.PharmacyMedicinesGetResponse;

import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus.ChangePharmacyPaymentStatusPost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus.ChangePharmacyPaymentStatusResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.ListOfPharmacyGetResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment.UpdatePaymentPharmacy;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.PharmacyMedicinesGetResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class listOfPharmacyApi {

    public static final String url = "https://arkaahealthapp.com/api/v1/pharmacy/profile/45342454/";


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
        Call<ListOfPharmacyGetResponse> listOfPharmacyApi(@Header("x-access-token") String authorization, @Url String url);

        @GET
        Call<PharmacyMedicinesGetResponse> listofMedicinesApi(@Header("x-access-token") String authorization, @Url String url);

        @POST
        Call<PharmacyOrderMedicineResponse> PharmacyOrderMedicineApi(@Header("x-access-token") String authorization, @Url String url, @Body PharmacyOrderMedicinePost user);

        @POST
        Call<ChangePharmacyPaymentStatusResponse> changePharmacyPaymentStatusApi(@Header("x-access-token") String authorization, @Url String url, @Body ChangePharmacyPaymentStatusPost user);

        @GET
        Call<UpdatePaymentPharmacy> updatePaymentStatusApi(@Header("x-access-token") String authorization, @Url String url);

    }





    //api



}
