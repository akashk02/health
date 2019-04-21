package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.RetrofitApi;


import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.ListOfMedicinesOrderGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.PharmacyQuatationGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationPost;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationResponse;

import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.ListOfMedicinesOrderGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.PharmacyQuatationGetResponse;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationPost;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation.QuatationConfirmatationResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public class PharmacyOrderApi {


    public static final String url = "https://arkaahealthapp.com/api/v1/pharmacy/orders/userview/test/";


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
        Call<ListOfMedicinesOrderGetResponse> ListOfPharmacyOrderApi(@Header("x-access-token") String authorization, @Url String url);

        @GET
        Call<PharmacyQuatationGetResponse> PharmacyQuatationApi(@Header("x-access-token") String authorization, @Url String url);

        @POST
        Call<QuatationConfirmatationResponse> QuatationConfirmationApi(@Header("x-access-token") String authorization, @Url String url, @Body QuatationConfirmatationPost obj );

    }





    //api





}
