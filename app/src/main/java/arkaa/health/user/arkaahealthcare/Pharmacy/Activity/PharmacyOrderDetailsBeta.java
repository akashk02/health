package arkaa.health.user.arkaahealthcare.Pharmacy.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity.PharmacyOrederDetails;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.ListOfPrescription;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.Firebase.PharmacyOrderBookingFirebase;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.Medicine;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import arkaa.health.user.arkaahealthcare.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.Firebase.PharmacyOrderBookingFirebase;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.Medicine;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicinePost;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.PharmacyOrderMedicineResponse;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;
import arkaa.health.user.arkaahealthcare.Pharmacy.RetrofitApi.listOfPharmacyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PharmacyOrderDetailsBeta extends AppCompatActivity {

    private static final int PRESCRIPTION_RESULT = 3;
    private String token;
    private PharmacyOrderMedicinePost pharmacyOrderMedicinePost;
    private PharmacyOrderMedicinePost pharmacyPrescriptionOrderMedicinePost;
    private List<Datum> pharmacyAllMedicinesLocalStorage;
    private EditText patientNameEditText;
    private EditText patientMobileNoEditText;
    private EditText patientEmailEditText;
    private EditText patientLocalityEditText;
    private EditText patientAddressEditText;
    private EditText patientLandMarkEditText;
    private EditText patientPincodeEditText;
    private EditText patientAddNotesToDoctorEditText;
    private Button uploadPrescriptionButton;
    private Button continueButton;
    private String pharmacyUniqueid;
    private String userUniqueId;
    private String consultationTypeId; //1 & 4
    private String patientName;
    private String patientMobile;
    private String patientEmail;
    private String patientLocality;
    private String patientAddress;
    private String patientLandmark;
    private String patientPincode;
    private String prescriptionId;
    private String description;
    private String bookingIntentType;
    private final int  ADD_TO_CART = 1;
    private final int UPLOAD_PRESCRIPTION = 2;

    //firebase

    private FirebaseDatabase mFirebaseDataBase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListner;
    private PharmacyOrderBookingFirebase labAppointmentFirebase;


    //firebase
    private  String TAG ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_order_details);

        //firebase

        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDataBase.getReference().child("pharmacy_order");
        labAppointmentFirebase = new PharmacyOrderBookingFirebase();

        //firebase

        TAG = getLocalClassName();



        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);

        prescriptionId = "null";
        userUniqueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");
        pharmacyUniqueid = getIntent().getStringExtra("PHARMACY_UNIQUE_ID");
        Log.v("pod","pharmacyUniqueid = "+pharmacyUniqueid);
        token = preferences.getString("TOKEN", "123456789");

        patientNameEditText = findViewById(R.id.patient_name);
        patientMobileNoEditText = findViewById(R.id.patient_mobile_no);
        patientEmailEditText = findViewById(R.id.patient_email_id);

        patientLocalityEditText = findViewById(R.id.patient_locality);
        patientAddressEditText = findViewById(R.id.patient_address);
        patientLandMarkEditText = findViewById(R.id.patient_landmark);
        patientPincodeEditText = findViewById(R.id.patient_pincode);

        patientAddNotesToDoctorEditText = findViewById(R.id.add_notes_to_doc);

        uploadPrescriptionButton = findViewById(R.id.upload_prescription);
        continueButton = findViewById(R.id.continue_button);


        bookingIntentType = getIntent().getStringExtra("BOOKING_TYPE");
        if (bookingIntentType == null) {
            bookingIntentType = "";
        }

        if (!bookingIntentType.equals("uploadPrescription")) {

            pharmacyOrderMedicinePost = getIntent().getParcelableExtra("PHARMACY_ORDER_MEDICINE_POST");
            if (pharmacyOrderMedicinePost != null) {
                Log.v("parcelable", "parcelable = " + pharmacyOrderMedicinePost.getMedicines().get(0));
            }

            pharmacyAllMedicinesLocalStorage = getIntent().getParcelableArrayListExtra("LIST_OF_MEDICINES_LOCAL_STORAGE");
            if (pharmacyAllMedicinesLocalStorage != null) {
                Log.v("parcelableLocalStorage", "parcelable = " + pharmacyAllMedicinesLocalStorage.size());
            }

        }

        pharmacyPrescriptionOrderMedicinePost = new PharmacyOrderMedicinePost();

        uploadPrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PharmacyOrderDetailsBeta.this, ListOfPrescription.class);
                intent.putExtra("ACTIVITY_TYPE", "PHARMACY");
                startActivityForResult(intent, PRESCRIPTION_RESULT);
            }
        });


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!bookingIntentType.equals("uploadPrescription")) {

                    if(validate()) {
//                        assignLabAppointmentPostObjectValue();
//                        Intent intent = new Intent(PharmacyOrderDetailsBeta.this, PharmacyCart.class);
//                        intent.putExtra("PHARMACY_ORDER_MEDICINE_POST", pharmacyOrderMedicinePost);
//                        intent.putParcelableArrayListExtra("LIST_OF_MEDICINES_LOCAL_STORAGE", (ArrayList<? extends Parcelable>) pharmacyAllMedicinesLocalStorage);
//                        startActivity(intent);
                        postData(ADD_TO_CART);

                    }

                } else {

                    if(validate()) {
                        postData(UPLOAD_PRESCRIPTION);
                    }

                }


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.v("parcelable", "onActivityResult");

        if (requestCode == PRESCRIPTION_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                prescriptionId = Integer.toString(data.getIntExtra("PRESCRIPTION_ID", 0));
                Log.v("parcelable", "onActivityResult pres ID = " + prescriptionId);
                if(prescriptionId != null){
                    Toast.makeText(PharmacyOrderDetailsBeta.this,"Prescription Uploaded Successfully",Toast.LENGTH_SHORT).show();
                    } else {

                }


            }


        }


    }

    public PharmacyOrderMedicinePost assignLabAppointmentPostObjectValue() {


        patientName = patientNameEditText.getText().toString();
        patientMobile = patientMobileNoEditText.getText().toString();

        patientEmail = patientEmailEditText.getText().toString();
        patientLocality = patientLocalityEditText.getText().toString();
        patientAddress = patientAddressEditText.getText().toString();
        patientLandmark = patientLandMarkEditText.getText().toString();
        patientPincode = patientPincodeEditText.getText().toString();
        description = patientAddNotesToDoctorEditText.getText().toString();

        //hard coded values

        pharmacyOrderMedicinePost.setPaymentTypeId("2");
//        pharmacyOrderMedicinePost.setDescription("");
        pharmacyOrderMedicinePost.setDescription(description);


        //hard coded values

        pharmacyOrderMedicinePost.setPrescriptionId(prescriptionId);
        Log.v("parcelable", "pres Id = " + prescriptionId);

        pharmacyOrderMedicinePost.setPharmacyUid(pharmacyUniqueid);
        pharmacyOrderMedicinePost.setUserUid(userUniqueId);


        pharmacyOrderMedicinePost.setName(patientName);
        pharmacyOrderMedicinePost.setMobile(patientMobile);

        pharmacyOrderMedicinePost.setEmail(patientEmail);
        pharmacyOrderMedicinePost.setLocality(patientLocality);
        pharmacyOrderMedicinePost.setAddress(patientAddress);
        pharmacyOrderMedicinePost.setLandmark(patientLandmark);
        pharmacyOrderMedicinePost.setPincode(patientPincode);
        pharmacyOrderMedicinePost.setBookingType("1");

        pharmacyOrderMedicinePost.setId("0");
        pharmacyOrderMedicinePost.setOrderDate("06-08-2018");
        pharmacyOrderMedicinePost.setOrderTakenDate("06-08-2018");
//        pharmacyOrderMedicinePost.setMedicineDetailId("0");
//        pharmacyOrderMedicinePost.setPharmacyOrderIdPar("10");
//        pharmacyOrderMedicinePost.setPatientId("0");
//        pharmacyOrderMedicinePost.setPaymentStatusId("3");
        pharmacyOrderMedicinePost.setConsultationType("5");

        Log.v("medicineCheck", "" + pharmacyOrderMedicinePost.getMedicines().size());

        return pharmacyOrderMedicinePost;

    }

    public PharmacyOrderMedicinePost getPharmacyOrderPrescriptionObject(int orderType) {


        PharmacyOrderMedicinePost pharmacyOrderMedicinePost = new PharmacyOrderMedicinePost();

        if(orderType == UPLOAD_PRESCRIPTION) {

            List<Medicine> medicines = new ArrayList<>();


            patientName = patientNameEditText.getText().toString();
            patientMobile = patientMobileNoEditText.getText().toString();

            patientEmail = patientEmailEditText.getText().toString();
            patientLocality = patientLocalityEditText.getText().toString();
            patientAddress = patientAddressEditText.getText().toString();
            patientLandmark = patientLandMarkEditText.getText().toString();
            patientPincode = patientPincodeEditText.getText().toString();
            description = patientAddNotesToDoctorEditText.getText().toString();


            //hard coded values


            pharmacyPrescriptionOrderMedicinePost.setPaymentTypeId("2");
            pharmacyPrescriptionOrderMedicinePost.setDescription(description);
            pharmacyPrescriptionOrderMedicinePost.setCost("0");
            pharmacyPrescriptionOrderMedicinePost.setMedicines(medicines);

            //hard coded values

            pharmacyPrescriptionOrderMedicinePost.setPrescriptionId(prescriptionId);
            Log.v("parcelable", "pres Id = " + prescriptionId);

            pharmacyPrescriptionOrderMedicinePost.setPharmacyUid(pharmacyUniqueid);
            pharmacyPrescriptionOrderMedicinePost.setUserUid(userUniqueId);

            pharmacyPrescriptionOrderMedicinePost.setName(patientName);
            pharmacyPrescriptionOrderMedicinePost.setMobile(patientMobile);
            pharmacyPrescriptionOrderMedicinePost.setEmail(patientEmail);
            pharmacyPrescriptionOrderMedicinePost.setLocality(patientLocality);
            pharmacyPrescriptionOrderMedicinePost.setAddress(patientAddress);
            pharmacyPrescriptionOrderMedicinePost.setLandmark(patientLandmark);
            pharmacyPrescriptionOrderMedicinePost.setPincode(patientPincode);
            pharmacyPrescriptionOrderMedicinePost.setBookingType("2");

            pharmacyPrescriptionOrderMedicinePost.setId("0");
            pharmacyPrescriptionOrderMedicinePost.setOrderDate("06-08-2018");
            pharmacyPrescriptionOrderMedicinePost.setOrderTakenDate("06-08-2018");
//        pharmacyPrescriptionOrderMedicinePost.setMedicineDetailId("0");
//        pharmacyPrescriptionOrderMedicinePost.setPharmacyOrderIdPar("10");
//        pharmacyPrescriptionOrderMedicinePost.setPatientId("0");
//        pharmacyPrescriptionOrderMedicinePost.setPaymentStatusId("3");
            pharmacyPrescriptionOrderMedicinePost.setConsultationType("5");
            pharmacyOrderMedicinePost = pharmacyPrescriptionOrderMedicinePost;



        } else{
            pharmacyOrderMedicinePost =  assignLabAppointmentPostObjectValue();

        }

        return pharmacyOrderMedicinePost;


    }


    public void postData(final int orderType) {


        String url = "https://arkaahealthapp.com/api/v1/pharmacy/order";
//        String url = "http://13.127.59.252:3000/api/v1/pharmacy/order";

        Log.v("apiUrl",getLocalClassName()+" url ="+url);

//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

        final PharmacyOrderMedicinePost pharmacyOrderMedicinePost = getPharmacyOrderPrescriptionObject(orderType);


        final Call<PharmacyOrderMedicineResponse> postList = listOfPharmacyApi.getService().PharmacyOrderMedicineApi(token, url, pharmacyOrderMedicinePost);

        postList.enqueue(new Callback<PharmacyOrderMedicineResponse>() {
            @Override
            public void onResponse(Call<PharmacyOrderMedicineResponse> call, Response<PharmacyOrderMedicineResponse> response) {

                final PharmacyOrderMedicineResponse postList1 = response.body();

                if (postList1.getSuccess() == true) {

                    Log.v("confirmBooking", "confirmBooking = " + postList1.getSuccess());


                    String patientName = pharmacyOrderMedicinePost.getName();
                    String patientUniqueId = pharmacyOrderMedicinePost.getUserUid();
                    String pharmacyUniqueId = pharmacyOrderMedicinePost.getPharmacyUid();

                    Log.v("pod","patientName1 ="+patientName);
                    Log.v("pod","patientUniqueId1 ="+patientUniqueId);
                    Log.v("pod","pharmacyUniqueId1="+pharmacyUniqueId);


                    addDataToFireBase(patientName,patientUniqueId,pharmacyUniqueId);

                    if(orderType == UPLOAD_PRESCRIPTION) {
                        showSuccessDialog();

                    } else if(orderType == ADD_TO_CART) {
                        Intent intent = new Intent(PharmacyOrderDetailsBeta.this, PharmacyCartBeta.class);
                        intent.putExtra("ORDER_ID",postList1.getData().get(0).getInsertOrUpdatePharmacyOrder());
                        startActivity(intent);
                    } else {
                        Toast.makeText(PharmacyOrderDetailsBeta.this,"Something went wroung, Please try again later",Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(PharmacyOrderDetailsBeta.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<PharmacyOrderMedicineResponse> call, Throwable t) {

                Toast.makeText(PharmacyOrderDetailsBeta.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public boolean validate() {
        boolean valid = true;

        String name = patientNameEditText.getText().toString();
        String mobile = patientMobileNoEditText.getText().toString();
        String email = patientEmailEditText.getText().toString();

        String locality = patientLocalityEditText.getText().toString();
        String address = patientAddressEditText.getText().toString();
        String landmark = patientLandMarkEditText.getText().toString();
        String pincode = patientPincodeEditText.getText().toString();

        if (name.isEmpty()) {
            patientNameEditText.setError("Please enter patient's name.");
            valid = false;
        } else {
            patientNameEditText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            patientMobileNoEditText.setError("Please Enter valid 10 digit Mobile Number");
            valid = false;
        } else {
            patientMobileNoEditText.setError(null);
        }




        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            patientEmailEditText.setError("Please enter a valid email address");
            valid = false;
        } else {
            patientEmailEditText.setError(null);
        }



        //location details

        if (locality.isEmpty()) {
            patientLocalityEditText.setError("Please Enter Locality");
            valid = false;
        } else {
            patientLocalityEditText.setError(null);
        }

        Log.v("lengthVali","pharmacy address length = "+address.length()+" boolean length() <=10 = "+(address.length() <=10));
        if (address.isEmpty() || address.length() <=10) {
            patientAddressEditText.setError("Address should be more than 10 characters.");
            Log.v("lengthVali","pharmacy address length = "+address.length()+" boolean length() <=10 = "+(address.length() <=10));

            valid = false;
        } else {
            patientAddressEditText.setError(null);
        }

        if (landmark.isEmpty()) {
            patientLandMarkEditText.setError("Please Enter Landmark");
            valid = false;
        } else {
            patientLandMarkEditText.setError(null);
        }

        if (pincode.isEmpty()) {
            patientPincodeEditText.setError("Please Enter Pincode");
            valid = false;
        } else {
            patientPincodeEditText.setError(null);
        }

        if(prescriptionId.equals("null")){
            Toast.makeText(PharmacyOrderDetailsBeta.this,"Please Upload Prescription",Toast.LENGTH_SHORT).show();
            valid = false;

        }





        return valid;
    }

    public void showSuccessDialog(){

//        new AwesomeSuccessDialog(this)
//                .setTitle(R.string.app_name)
//                .setMessage(R.string.app_name)
//                .setColoredCircle(R.color.dialogSuccessBackgroundColor)
//                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
//                .setCancelable(true)
//                .setPositiveButtonText(getString(R.string.dialog_yes_button))
//                .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
//                .setPositiveButtonTextColor(R.color.white)
//                .setNegativeButtonText(getString(R.string.dialog_no_button))
//                .setNegativeButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
//                .setNegativeButtonTextColor(R.color.white)
//                .setPositiveButtonClick(new Closure() {
//                    @Override
//                    public void exec() {
//                        //click
//                    }
//                })
//                .setNegativeButtonClick(new Closure() {
//                    @Override
//                    public void exec() {
//                        //click
//                    }
//                })
//                .show();


        new AwesomeSuccessDialog(this)
                .setTitle(" Thank You,Please check Inbox for order status.")
                .setMessage("")
                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_done_black_24dp, R.color.white)
                .setCancelable(true)
                .setPositiveButtonText("OK")
                .setPositiveButtonbackgroundColor(R.color.dialogErrorBackgroundColor)
                .setPositiveButtonTextColor(R.color.white)
                .setPositiveButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        //click
                        Intent i = new Intent(PharmacyOrderDetailsBeta.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();




    }

    public void addDataToFireBase(String patientName , String patientUniqueId, String labUniqueid) {

        labAppointmentFirebase.setPatientName(patientName);
        labAppointmentFirebase.setPatientUniqueId(patientUniqueId);

        mMessagesDatabaseReference.child(labUniqueid).push().setValue(labAppointmentFirebase);


    }






}
