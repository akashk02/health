package arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.ShareReports.ShareReportsPost;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.ShareReports.ShareReportsResponse;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.RetrofitApi.CurrentAppointmentApi;
import arkaa.health.user.arkaahealthcare.Doctor.Activity.schedule;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.Activity.ListOfLabReports;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.ListOfPrescription;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.DoctorPrescriptionFirebase;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.PatientPrescriptionFirebase;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyOrderDetails;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.eHospital.Activities.eListOfDoctors;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.ElistOfDocPostResponse;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.UploadHospitalPrescription.UploadPrescriptionHospitalPost;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.UploadHospitalPrescription.UploadPrescriptionHospitalResponse;
import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.ShareReports.ShareReportsPost;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.ShareReports.ShareReportsResponse;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.RetrofitApi.CurrentAppointmentApi;
import arkaa.health.user.arkaahealthcare.Fragment.FragmentActivity;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.Activity.ListOfLabReports;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.DoctorPrescriptionFirebase;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.PatientPrescriptionFirebase;
import arkaa.health.user.arkaahealthcare.eHospital.RetrofitApi.eHospitalApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareReports extends AppCompatActivity {

    @BindView(R.id.buttonChoose)
    CardView choosePresFromInbox ;

    @BindView(R.id.buttonUpload)
    Button shareReportsButton;

    @BindView(R.id.buttonCamera)
    CardView selectFile;

    @BindView(R.id.descriptionCardView)
    CardView descriptionCardView;

    @BindView(R.id.uploadFromGalleryTv)
    TextView uploadFromGalleryTv;

    private int REPORTS_RESULT = 6;
    private Integer reportId;
    private int appointmentId;
    private String token;

    //firebase
    FirebaseStorage storage; // used for uploading files...Ex.  : pdf
    FirebaseDatabase database;  // used to store URLs OF uploaded files.
    ProgressDialog progressDialog;
    private ProgressDialog progress;

    //firebase

    //upload reports
    //firebase Prescription
    private FirebaseDatabase mFirebaseDataBase;
    private FirebaseStorage mFirebaseStorage;


    private DatabaseReference mDocPrescriptionDatabaseRef;
    private DatabaseReference mPatPrescriptionDatabaseRef;

    private StorageReference mPrescriptionStorageRef;

    private DoctorPrescriptionFirebase doctor;
    private PatientPrescriptionFirebase patient;

    private static final int RC_PHOTO_PICKER = 2;
    private UploadTask uploadTask;
    private UploadTask cameraTask;
    private String firebasePrescriptionPath = "" ;
    private String firebaseCameraPath;
    private String pictureFilePath;

    private String classType;
    private String hospitalId ;
    private String userUniqueId;


    //upload reports


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_reports);

        ButterKnife.bind(this);

        //firebase

        //firebase Prescription

  //      mFirebaseDataBase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();


      //  mDocPrescriptionDatabaseRef = mFirebaseDataBase.getReference().child("patient_shared_report");
     //   mPatPrescriptionDatabaseRef = mFirebaseDataBase.getReference().child("patient_prescription");

//        Log.v("prescription123", "mDocPrescriptionDatabaseRef " + mDocPrescriptionDatabaseRef);
//        Log.v("prescription123", "mPatPrescriptionDatabaseRef " + mPatPrescriptionDatabaseRef);


        mPrescriptionStorageRef = mFirebaseStorage.getReference().child("patient_shared_report");

        Log.v("prescription123", "mDocPrescriptionStorageRef " + mPrescriptionStorageRef);

        storage = FirebaseStorage.getInstance();  //return an object of Firebase Storage
        database = FirebaseDatabase.getInstance();  //return an object of Firebase Database


        //firebase Prescription




        //firebase

        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);

        token = preferences.getString("TOKEN", "123456789");
        userUniqueId = preferences.getString("USERUNIQUEID", "123456789");

        classType = ""+getIntent().getStringExtra("CLASS_TYPE");
        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");



        if(classType.equals("eHospital")){

            choosePresFromInbox.setVisibility(View.GONE);
            descriptionCardView.setVisibility(View.GONE);
            shareReportsButton.setText("SEND PRESCRIPTION");
            uploadFromGalleryTv.setText("Choose Prescription From Gallery");
            shareReportsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (firebasePrescriptionPath!= null || reportId != null) {


                        uploadHospitalPresPost();

//                        mDocPrescriptionDatabaseRef.child(clinicDoctorUniqueId).child(PATIENT_UNIQUE_Id).push().setValue(getDoctorObject());
//                        mPatPrescriptionDatabaseRef.child(PATIENT_UNIQUE_Id).push().setValue(getPatientObject());

                    }

                    else {
                        Toast.makeText(ShareReports.this,"Upload Failed",Toast.LENGTH_SHORT).show();

                    }


                }
            });

        }




        appointmentId = getIntent().getIntExtra("APPOINTMENT_ID",1234);
        choosePresFromInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareReports.this, ListOfLabReports.class);
                intent.putExtra("CLASS_TYPE", "SHARE_REPORTS");
                startActivityForResult(intent, REPORTS_RESULT);            }
        });

        shareReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firebasePrescriptionPath!= null || reportId != null) {


                    postData();

//                        mDocPrescriptionDatabaseRef.child(clinicDoctorUniqueId).child(PATIENT_UNIQUE_Id).push().setValue(getDoctorObject());
//                        mPatPrescriptionDatabaseRef.child(PATIENT_UNIQUE_Id).push().setValue(getPatientObject());

                    }

                else {
                    Toast.makeText(ShareReports.this,"Please Select Any Prescription",Toast.LENGTH_SHORT).show();

                }





               // postData();
            }
        });

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
//                intent.setType("application/*");

                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
//                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 3);


            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REPORTS_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                reportId = data.getIntExtra("REPORT_ID",1234);
//                prescriptionId = Integer.toString(data.getIntExtra("PRESCRIPTION_ID", 0));
                Log.v("reportId", "Report Id = " + reportId);


            }


        }

        if(requestCode == 3){

            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 3 && resultCode == RESULT_OK) {
                Log.v("RC_PHOTO_PICKER", "rc photo picker =" + data.getData().toString());
                Uri file = data.getData();


                progress=new ProgressDialog(this);
                progress.setTitle("Uploading Prescripiton");
                progress.setMessage("Please Wait For a While...");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  // Progress Dialog Style Horizontal
                progress.setIndeterminate(true);
                progress.setCancelable(false);  //ProgressDialog cannot be cancelled until the work is done.
                progress.setProgress(0);
                progress.setMax(100);  //Progress Dialog Max Value
                progress.show();





                final StorageReference photoRef = mPrescriptionStorageRef.child(file.getLastPathSegment());

                uploadTask = photoRef.putFile(file);


                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        Log.v("urlTest", "photoRef.getDownloadUrl() " + photoRef.getDownloadUrl());
                        return photoRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        progress.dismiss();

                        Log.v("RC_PHOTO_PICKER", "isSuccessful=" + task.isSuccessful());

                        if (task.isSuccessful()) {

                            Uri downloadUri = task.getResult();
                            firebasePrescriptionPath = downloadUri.toString();
                            Log.v("urlTest", "downloadUri " + task.getResult());
                            Toast.makeText(ShareReports.this, "File Successfully Uploaded ", Toast.LENGTH_SHORT).show();

//                        FriendlyMessage friendlyMessage = new FriendlyMessage(null, mUsername, downloadUri.toString());
//                        mMessagesDatabaseReference.push().setValue(friendlyMessage);

                        } else {
                            // Handle failures
                            // ...
                            Toast.makeText(ShareReports.this, "Network Error, File Not Uploaded ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            //    fileUpload();

            }




        }


    }



    public void postData() {


        //post



        String url = "https://arkaahealthapp.com/api/v1/reports";
        ShareReportsPost shareReportsPost = new ShareReportsPost();
        shareReportsPost.setId(0);
        shareReportsPost.setAppointmentId(appointmentId);

        if(!firebasePrescriptionPath.equals("")){
            shareReportsPost.setReportId(0);
        }else {
            shareReportsPost.setReportId(reportId);
        }
        shareReportsPost.setReportPath(firebasePrescriptionPath);



        Call<ShareReportsResponse> postList = CurrentAppointmentApi.getService().shareReportsApi(token,url,shareReportsPost);


        postList.enqueue(new Callback<ShareReportsResponse>() {
            @Override
            public void onResponse(Call<ShareReportsResponse> call, Response<ShareReportsResponse> response) {



                ShareReportsResponse postList1 = response.body();
                Log.v("reportId", "(post)schedule = status code = " + response.code());
                Log.v("reportId", "(post)time  = " + response.code());



                if (postList1 != null) {


                    Log.v("reportId", "(post)schedule get =" + postList1.getMessage());
                    if(postList1.getSuccess() == true){
                        showSuccessDialog();
                    }





                }


            }

            @Override
            public void onFailure(Call<ShareReportsResponse> call, Throwable t) {

                Toast.makeText(ShareReports.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post


    }

    public void showSuccessDialog() {

        new AwesomeSuccessDialog(this)
                .setTitle(" Your Document is uploaded successfully.")
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
                        Intent i = new Intent(ShareReports.this, FragmentActivity.class);
// set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                })

                .show();


    }

    public void uploadHospitalPresPost(){
        //post



        String url = " http://35.163.147.45/api/HospitalMaster/SetPrescriptions";
        UploadPrescriptionHospitalPost uploadPrescriptionHospitalPost = new UploadPrescriptionHospitalPost();
        uploadPrescriptionHospitalPost.setHospitalId(hospitalId);
        uploadPrescriptionHospitalPost.setPrescriptionPath(firebasePrescriptionPath);
        uploadPrescriptionHospitalPost.setPatientId(userUniqueId);


        Call<UploadPrescriptionHospitalResponse> postList = eHospitalApi.getService().uploadHosPresApi(url,uploadPrescriptionHospitalPost);


        postList.enqueue(new Callback<UploadPrescriptionHospitalResponse>() {
            @Override
            public void onResponse(Call<UploadPrescriptionHospitalResponse> call, Response<UploadPrescriptionHospitalResponse> response) {



                UploadPrescriptionHospitalResponse postList1 = response.body();
                Log.v("reportId", "(post)schedule = status code = " + response.code());
                Log.v("reportId", "(post)time  = " + response.code());



                if (postList1 != null) {


                    Log.v("reportId", "(post)schedule get =" + postList1.getMessage());
                    if(postList1.getMessage().equals("success") && postList1.getError().equals("false")){
                        showSuccessDialog();
                    }





                }


            }

            @Override
            public void onFailure(Call<UploadPrescriptionHospitalResponse> call, Throwable t) {

                Toast.makeText(ShareReports.this, "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(schedule)error in link (get)");


            }
        });


        //post
    }








}
