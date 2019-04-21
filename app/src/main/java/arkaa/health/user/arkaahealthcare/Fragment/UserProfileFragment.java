package arkaa.health.user.arkaahealthcare.Fragment;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Profile.RetrofitApi.FullProfileGetViewApi;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.FullProfileView.FullProfileGetViewResponse;
import arkaa.health.user.arkaahealthcare.Profile.RetrofitApi.ProfileEditApi;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileEditRetrofit.ProfileEditPut;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileEditRetrofit.ProfileEditResponse;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileGetRetrofit.Datum;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileGetRetrofit.ProfileGetReponse;
import arkaa.health.user.arkaahealthcare.Profile.RetrofitApi.ProfileApi;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileRetrofit.ProfilePost;
import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileRetrofit.ProfileResponse;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileEditRetrofit.ProfileEditPut;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    String[] languages1 = new String[]{"A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};

    //token
    //token
    private SharedPreferences sharedPreferences;
    private EditText edittext;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText userNameEditText;


    //server
    private ProfileEditPut profileEditPut;
    private ProfilePost profilePost;
    private Button submitButton;
    //    private List<MedicalRecord> medicalRecordsList ;
    private String token;


    private EditText firstNameEditText;
    private EditText LastNameEditText;
    private EditText addressEditText;
    private EditText emailIdEditText;
    private EditText mobileNoEditText;
    private EditText aadhaarNoEditText;
    private AutoCompleteTextView bloodGroupAutoCompleteTextView;
    private EditText heightEditText;
    private EditText weightEditText;
    private EditText ageEditText;
    private EditText dateOfBirthEditText;
    private AutoCompleteTextView genderAutoCompleteTextView;
    private AutoCompleteTextView accountTypeAutoCompleteTextView;
    private EditText ailmentsEditText;
    private EditText medicalHistoryEditText;
    private TextView idTextView;
    private TextView userNameTextView;

    private EditText cityEditText;
    private EditText stateEditText;
    private EditText pincodeEditText;
//profile picture
    private ImageView DoctorProfile;
    private static final int PICK_IMAGE_REQUEST = 234;

    //firebase PROFILE
    private FirebaseDatabase mFirebaseDataBase;
    private FirebaseStorage mFirebaseStorage;

    private DatabaseReference mDoctorProfileDatabaseRef;

    private StorageReference mDoctorProfileStorageRef;


    private ProgressDialog progressUpload;
    private UploadTask uploadTask;
    private String firebaseDoctorProfilePath;

    private Uri selectedImage;
    private String profilepicGet;





//profile picture






    private String userName;
    private String firstName;
    private String LastName;
    private String address;
    private String emailId;
    private String mobileNo;
    private String aadhaarNo;
    private String bloodGroup;
    private String height;
    private String weight;
    private String age;
    private String dateOfBirth;
    private String gender;
    private String accountType;
    private String ailments;
    private String medicalHistory;
    private String loginUserName;

    private String city;
    private String state;
    private String pincode;




    private String userNameGet;
    private String firstNameGet;
    private String LastNameGet;
    private String addressGet;
    private String emailIdGet;
    private String mobileNoGet;
    private String aadhaarNoGet;
    private String bloodGroupGet;
    private String heightGet;
    private String weightGet;
    private String ageGet;
    private String dateOfBirthGet;
    private String genderGet;
    private String accountTypeGet;
    private String ailmentsGet;
    private String medicalHistoryGet;

    private String cityGet;
    private String stateGet;
    private String pincodeGet;


    private int PROFILE_FLAG = 0;
    private String PROFILE_ID ;
    private String UNIQUE_ID1 ;



    public UserProfileFragment() {
        // Required empty public constructor
    }


    //server

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

//        View view = inflater.inflate(R.layout.fragment_user_profile_real, container, false);
        View view = inflater.inflate(R.layout.test, container, false);


        //token

        //hide
        Button uploadButton = view.findViewById(R.id.photo);
        uploadButton.setVisibility(View.GONE);

        Button uploadRecords = view.findViewById(R.id.records);
        uploadRecords.setVisibility(View.GONE);
        //hide


        SharedPreferences preferences = this.getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        token = preferences.getString("TOKEN", "123456789");
        loginUserName = preferences.getString("USERNAME", "test");
        Log.v("SignUp", "Token userProfile = " + token + "  loginUSerName = " + loginUserName);


        //token


        //

        getData();

        //


        //server

        profilePost = new ProfilePost();
        profileEditPut = new ProfileEditPut();

        if(token == null) {
          //  token = LoginActivityApp.TOKEN;
        }
        userNameEditText = view.findViewById(R.id.user_name);
        firstNameEditText = view.findViewById(R.id.first_name);
        LastNameEditText = view.findViewById(R.id.Last_name);
        Log.v("SignUp", "firstnameedittext  " + firstNameEditText);
        Log.v("SignUp", "lastnameedittext    " + LastNameEditText);


        addressEditText = view.findViewById(R.id.address);
        emailIdEditText = view.findViewById(R.id.email);
        mobileNoEditText = view.findViewById(R.id.mobile_number);
        aadhaarNoEditText = view.findViewById(R.id.aadhaar_no);
        bloodGroupAutoCompleteTextView = view.findViewById(R.id.blood_group);
        heightEditText = view.findViewById(R.id.height);
        weightEditText = view.findViewById(R.id.weight);
        ageEditText = view.findViewById(R.id.age);
        dateOfBirthEditText = view.findViewById(R.id.birth);
        genderAutoCompleteTextView = view.findViewById(R.id.gender);
        accountTypeAutoCompleteTextView = view.findViewById(R.id.account_type);
        ailmentsEditText = view.findViewById(R.id.ailments);
        medicalHistoryEditText = view.findViewById(R.id.family);
        idTextView = view.findViewById(R.id.user_ID);
        userNameTextView = view.findViewById(R.id.username_textview);

        cityEditText = view.findViewById(R.id.city);
        stateEditText = view.findViewById(R.id.state);
        pincodeEditText = view.findViewById(R.id.pincode);




        //server


//        String[] languages = { "A+","A-","AB+","AB-","B+","B-","O+","O-" };

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice, languages1);
        //Find TextView control
        final AutoCompleteTextView acTextView = (AutoCompleteTextView) view.findViewById(R.id.blood_group);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(0);
        //Set the adapter
        acTextView.setAdapter(adapter);

        acTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    acTextView.showDropDown();

            }
        });

        acTextView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                acTextView.showDropDown();
                return false;
            }
        });

        //profile picture

        DoctorProfile = view.findViewById(R.id.profile_image);
        DoctorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
            }
        });




        //firebase Prescription

        mFirebaseDataBase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();


        mDoctorProfileDatabaseRef = mFirebaseDataBase.getReference().child("patient_profile");

        Log.v("prescription123", "mDocProfileDatabaseRef " + mDoctorProfileDatabaseRef);


        mDoctorProfileStorageRef = mFirebaseStorage.getReference().child("patient_profile");

        Log.v("prescription123", "mDocProfileStorageRef " + mDoctorProfileStorageRef);

        //firebase Prescription








        //profile picture














        String[] languages1 = {"Male", "Female"};

        //Create Array Adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice, languages1);
        //Find TextView control
        final AutoCompleteTextView acTextView1 = (AutoCompleteTextView) view.findViewById(R.id.gender);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView1.setThreshold(0);
        //Set the adapter
        acTextView1.setAdapter(adapter1);

        acTextView1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    acTextView1.showDropDown();

            }
        });

        acTextView1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                acTextView1.showDropDown();
                return false;
            }
        });


        //account type

        String[] languages2 = {"private", "public"};

        //Create Array Adapter
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice, languages2);
        //Find TextView control
        final AutoCompleteTextView acTextView2 = (AutoCompleteTextView) view.findViewById(R.id.account_type);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView2.setThreshold(0);
        //Set the adapter
        acTextView2.setAdapter(adapter2);

        acTextView2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    acTextView2.showDropDown();

            }
        });

        acTextView2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                acTextView2.showDropDown();
                return false;
            }
        });


        //account type


        myCalendar = Calendar.getInstance();

        edittext = (EditText) view.findViewById(R.id.birth);
        edittext.setFocusable(false);

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });


        submitButton = view.findViewById(R.id.button);






//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                validate();
//
//
////                profilePost.setProfileId("0");
////                profilePost.setUniqueId("2223322");
////                profilePost.setBloodGroup("B+");
////                profilePost.setHeight("5.5");
////                profilePost.setWeight("67kg");
////                profilePost.setAge("21");
////                profilePost.setDob("25-05-1995");
////                profilePost.setGender("male");
////                profilePost.setState("Maharashtra");
////                profilePost.setCity("Mumbai");
////                profilePost.setAddress("mumbai");
////                profilePost.setPincode("400607");
////                profilePost.setAadhaarNo("11111111122222222");
//////                profilePost.setMedicalRecords(medicalRecordsList);
////                profilePost.setPreviousAilments("asasasas");
////                profilePost.setFamilyMedicalRecords("asasasas");
////                profilePost.setProfilePicturePath("asasas");
//
//
////                profilePost.setProfileId("0");
//                profilePost.setUniqueId("2223322");
//                profilePost.setBloodGroup(bloodGroup);
//                profilePost.setHeight(height);
//                profilePost.setWeight(weight);
//                profilePost.setAge(age);
//                profilePost.setDob(dateOfBirth);
//                profilePost.setGender(gender);
//                profilePost.setState("Maharashtra");
//                profilePost.setCity("Mumbai");
//                profilePost.setAddress(address);
//                profilePost.setPincode("400607");
//                profilePost.setAadhaarNo(aadhaarNo);
////                profilePost.setMedicalRecords(medicalRecordsList);
//                profilePost.setPreviousAilments(ailments);
//                profilePost.setFamilyMedicalRecords(medicalHistory);
//                profilePost.setProfilePicturePath("asasas");
//                profilePost.setAccountType(accountType);
//
//
////                postData();
//
//
//            }
//        });


        //testing
//        putData();

//        getFullProfileData();

        //testing


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!validate()) {
                    onSignupFailed();
                    return;
                }

//                submitButton.setEnabled(false);

                if (PROFILE_FLAG == 1) {

                    campare();

                } else {

                    postData();

                }

            }
        });


        return view;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


            selectedImage = data.getData();
            Log.v("data", "path = " + selectedImage);


            progressUpload = new ProgressDialog(getContext());
            progressUpload.setTitle("Uploading Profile");
            progressUpload.setMessage("Please Wait For a While...");
            progressUpload.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  // Progress Dialog Style Horizontal
            progressUpload.setIndeterminate(true);
            progressUpload.setCancelable(false);  //ProgressDialog cannot be cancelled until the work is done.
            progressUpload.setProgress(0);
            progressUpload.setMax(100);  //Progress Dialog Max Value
            progressUpload.show();


            final StorageReference photoRef = mDoctorProfileStorageRef.child(selectedImage.getLastPathSegment());


            uploadTask = photoRef.putFile(selectedImage);


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


                    progressUpload.dismiss();


                    Log.v("RC_PHOTO_PICKER", "isSuccessful=" + task.isSuccessful());

                    if (task.isSuccessful()) {

                        Uri downloadUri = task.getResult();
                        firebaseDoctorProfilePath = downloadUri.toString();
                        Log.v("urlTest", "document path =  " + firebaseDoctorProfilePath);

                        Log.v("urlTest", "downloadUri " + task.getResult());
                        Toast.makeText(getContext(), "Profile Successfully Uploaded ", Toast.LENGTH_SHORT).show();


                    } else {

                        Toast.makeText(getContext(), "Network Error, File Not Uploaded ", Toast.LENGTH_SHORT).show();

                    }
                }
            });


            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

//            ImageView imageView = (ImageView) findViewById(R.id.imgView);
//            DoctorProfile.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                DoctorProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }


    public boolean validate() {

        boolean valid = true;

        userName = userNameEditText.getText().toString();

        firstName = firstNameEditText.getText().toString();

        LastName = LastNameEditText.getText().toString();

        address = addressEditText.getText().toString();

        emailId = emailIdEditText.getText().toString();

        mobileNo = mobileNoEditText.getText().toString();

        aadhaarNo = aadhaarNoEditText.getText().toString();

        bloodGroup = bloodGroupAutoCompleteTextView.getText().toString();

        height = heightEditText.getText().toString();

        weight = weightEditText.getText().toString();

        age = ageEditText.getText().toString();

        dateOfBirth = dateOfBirthEditText.getText().toString();

        gender = genderAutoCompleteTextView.getText().toString();

        accountType = accountTypeAutoCompleteTextView.getText().toString();

        ailments = ailmentsEditText.getText().toString();

        medicalHistory = medicalHistoryEditText.getText().toString();

        city = cityEditText.getText().toString();
        state = stateEditText.getText().toString();
        pincode = pincodeEditText.getText().toString();


//        //validation
//
//        if (userName.isEmpty() || userName.length() < 3) {
//            userNameEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            userNameEditText.setError(null);
//        }
//
//        if (firstName.isEmpty() || userName.length() < 3) {
//            firstNameEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            firstNameEditText.setError(null);
//        }
//
//
//        if (LastName.isEmpty() || LastName.length() < 3) {
//            LastNameEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            LastNameEditText.setError(null);
//        }
//
//        if (address.isEmpty() || address.length() < 3) {
//            addressEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            addressEditText.setError(null);
//        }
//
//        if (emailId.isEmpty() || emailId.length() < 3) {
//            emailIdEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            emailIdEditText.setError(null);
//        }
//
//        if (mobileNo.isEmpty() || mobileNo.length() != 10) {
//            mobileNoEditText.setError("Enter Valid Mobile Number");
//            valid = false;
//        } else {
//            mobileNoEditText.setError(null);
//        }
//
//        if (aadhaarNo.isEmpty() || aadhaarNo.length() != 16) {
//            aadhaarNoEditText.setError("Enter Valid Aadhaar Number");
//            valid = false;
//        } else {
//            aadhaarNoEditText.setError(null);
//        }
//
//        if (bloodGroup.isEmpty()) {
//            bloodGroupAutoCompleteTextView.setError("Select your blood group");
//            valid = false;
//        } else {
//            bloodGroupAutoCompleteTextView.setError(null);
//        }
//
//        if (height.isEmpty()) {
//            heightEditText.setError("Enter your height");
//            valid = false;
//        } else {
//            heightEditText.setError(null);
//        }
//
//        if (weight.isEmpty()) {
//            weightEditText.setError("Enter your weight");
//            valid = false;
//        } else {
//            weightEditText.setError(null);
//        }
//
//        if (age.isEmpty()) {
//            ageEditText.setError("Enter your age");
//            valid = false;
//        } else {
//            ageEditText.setError(null);
//        }
//
//        if (dateOfBirth.isEmpty()) {
//            dateOfBirthEditText.setError("Enter your date of birth");
//            valid = false;
//        } else {
//            dateOfBirthEditText.setError(null);
//        }
//
//        if (gender.isEmpty()) {
//            genderAutoCompleteTextView.setError("Enter your gender");
//            valid = false;
//        } else {
//            genderAutoCompleteTextView.setError(null);
//        }
//
//
//        //validation

//
//        //validation new
//
//                //validation
//
//        if (!userName.isEmpty() && userName.length() < 3) {
//            userNameEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            userNameEditText.setError(null);
//        }
//
//        if (!firstName.isEmpty() && userName.length() < 3) {
//            firstNameEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            firstNameEditText.setError(null);
//        }
//
//
//        if (!LastName.isEmpty() && LastName.length() < 3) {
//            LastNameEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            LastNameEditText.setError(null);
//        }
//
//        if (!address.isEmpty() && address.length() < 3) {
//            addressEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            addressEditText.setError(null);
//        }
//
//        if (!emailId.isEmpty() && emailId.length() < 3) {
//            emailIdEditText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            emailIdEditText.setError(null);
//        }
//
//        if (!mobileNo.isEmpty() && mobileNo.length() != 10) {
//            mobileNoEditText.setError("Enter Valid Mobile Number");
//            valid = false;
//        } else {
//            mobileNoEditText.setError(null);
//        }
//
        if (!aadhaarNo.isEmpty() && aadhaarNo.length() != 12) {
            aadhaarNoEditText.setError("Enter Valid 12 digit Aadhaar Number");
            valid = false;
        } else {
            aadhaarNoEditText.setError(null);
        }

//        if(!pincode.equals("") && pincode.length() !=6){
//            Log.v("pincodeError","!pincode.equals = "+!pincode.equals("")+" pincode.length() !=6 = "+""+(pincode.length() != 6));
//            pincodeEditText.setError("Enter valid pincode");
//            valid = false;
//        } else {
//            pincodeEditText.setError("Enter valid pincode");
//
//        }

//
////
////
////        //validation
//
//
//
//        //validation new




        return valid;

    }

    public void postData() {

        Log.v("SignUp1", "POST DATA");


        String url = "https://arkaahealthapp.com/api/v1/users/profiles";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

//        Call<ProfileResponse> postList = ProfileApi.getService().reqRescreateUser(token,url, profilePost);

        Call<ProfileResponse> postList = ProfileApi.getService().reqRescreateUser(token, url, getProfilePostObj());

        Log.v("SignUp", "(Post)token for header userProfile" + token);

        postList.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {


//                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)profile = success");

                ProfileResponse postList1 = response.body();
                Log.v("SignUp", "(post)profile = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(post)profile success =" + postList1.getSuccess());

                    if(postList1.getSuccess() == true){
//                        Intent intent = new Intent(getContext(), FragmentActivity.class);
//                        startActivity(intent);
                        Toast.makeText(getContext(),"Your account has been successfully created",Toast.LENGTH_SHORT);

                        Intent intent = new Intent(getContext(), FragmentActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);

                    }



                }


//
//                if (postList1.getSuccess() == true) {
//
//                    Toast.makeText(LoginActivityApp.this, "Success", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivityApp.this, FragmentActivity.class);
//                    startActivity(intent);
//
//
//                } else {
//                    Toast.makeText(LoginActivityApp.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();
//
//                }


            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

                Toast.makeText(getContext(), "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)error in link");


            }
        });


    }

    public void getData() {


        //post

        Log.v("SignUp1", "GET DATA");


        String url = "https://arkaahealthapp.com/api/v1/users/" + loginUserName + "/";

        Call<ProfileGetReponse> postList = ProfileApi.getService().profileGetRequest(token, url);

        Log.v("SignUp", "(post)token for header userProfile get" + token);

        postList.enqueue(new Callback<ProfileGetReponse>() {
            @Override
            public void onResponse(Call<ProfileGetReponse> call, Response<ProfileGetReponse> response) {


//                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)profile get = success");

                ProfileGetReponse postList1 = response.body();
                Log.v("SignUp", "(post)profile get = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(post)profile success get =" + postList1.getSuccess());


                    List<Datum> data = postList1.getData();

                    Datum SignUpData = data.get(0);


                    userNameGet = SignUpData.getUsername();
                    firstNameGet = SignUpData.getFirstName();
                    LastNameGet = SignUpData.getLastName();
                    emailIdGet = SignUpData.getEmail();
                    mobileNoGet = SignUpData.getMobileNo();

                    userNameEditText.setText(""+SignUpData.getUsername());


                    firstNameEditText.setText(""+SignUpData.getFirstName());
                    LastNameEditText.setText(""+SignUpData.getLastName());
                    emailIdEditText.setText(""+SignUpData.getEmail());
                    mobileNoEditText.setText("+91 "+SignUpData.getMobileNo());

                    idTextView.setText("ID:"+SignUpData.getUniqueId());
                    userNameTextView.setText(""+SignUpData.getUsername());


                    mobileNoEditText.setEnabled(false);
                    mobileNoEditText.setFocusable(false);
                    mobileNoEditText.setTextColor(getResources().getColor(R.color.profileEditText));


                    userNameEditText.setEnabled(false);
                    userNameEditText.setFocusable(false);
                    userNameEditText.setTextColor(getResources().getColor(R.color.profileEditText));

                    firstNameEditText.setEnabled(false);
                    firstNameEditText.setFocusable(false);
                    firstNameEditText.setTextColor(getResources().getColor(R.color.profileEditText));


                    emailIdEditText.setEnabled(false);
                    emailIdEditText.setFocusable(false);
                    emailIdEditText.setTextColor(getResources().getColor(R.color.profileEditText));


                    LastNameEditText.setEnabled(false);
                    LastNameEditText.setFocusable(false);
                    LastNameEditText.setTextColor(getResources().getColor(R.color.profileEditText));






                    UNIQUE_ID1 = SignUpData.getUniqueId();
                    Log.v("SignUp2","getData(UNIQUE ID )"+UNIQUE_ID1);

                    sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

//                    editor.putString("PATIENT_UNIQUE_ID",UNIQUE_ID1 );
//                    editor.commit();
                    Log.v("UNIQUEid","patientUniqueId|(UserProfileFragment) = "+UNIQUE_ID1);


                    getFullProfileData();


                }


                //edit in layout

//
//                userNameEditText.setText(SignUpData.getUsername());
//                Log.v("SignUp", "(post)userName get  = " + data.get(0).getUsername());
//
//                firstNameEditText.setText(SignUpData.getFirstName());
//
//                LastNameEditText.setText(SignUpData.getLastName());
//
//                Log.v("SignUp", "(post)Last name get = " + SignUpData.getLastName());
//
//                emailIdEditText.setText(SignUpData.getEmail());
//
//                mobileNoEditText.setText(SignUpData.getMobileNo());
//
//                idTextView.setText(SignUpData.getUniqueId());
//
//                userNameTextView.setText(SignUpData.getUsername());


                //edit in layout


//
//                if (postList1.getSuccess() == true) {
//
//                    Toast.makeText(LoginActivityApp.this, "Success", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivityApp.this, FragmentActivity.class);
//                    startActivity(intent);
//
//
//                } else {
//                    Toast.makeText(LoginActivityApp.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();
//
//                }


            }

            @Override
            public void onFailure(Call<ProfileGetReponse> call, Throwable t) {

                Toast.makeText(getContext(), "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)error in link (get)");


            }
        });


        //post


    }

    public void putData() {

        Log.v("SignUp1", "PUT DATA");


        //put data feilds

//
//        profileEditPut.setProfileId("35");
//        profileEditPut.setUniqueId("123456");
//        profileEditPut.setBloodGroup("B+");
//        profileEditPut.setHeight("5.67");
//        profileEditPut.setWeight("72");
//        profileEditPut.setAge("23");
//        profileEditPut.setDob("12/02/1996");
//        profileEditPut.setGender("Male");
//        profileEditPut.setState("Maharashtra");
//        profileEditPut.setCity("Thane");
//        profileEditPut.setAddress("Thane");
//        profileEditPut.setPincode("400607");
//        profileEditPut.setAadhaarNo("5755555555555555");
//        profileEditPut.setPreviousAilments("aaaaaa");
//        profileEditPut.setFamilyMedicalRecords("adadad0");
//        profileEditPut.setProfilePicturePath("asas0");
//        profileEditPut.setAccountType("public");


        //put data feilds


        String url = "https://arkaahealthapp.com/api/v1/users/profiles";
//        Call<Post> postList = News_Api.getService().reqRescreateUser(url,loginPost);

//        Call<ProfileResponse> postList = ProfileApi.getService().reqRescreateUser(token,url, profilePost);

        Call<ProfileEditResponse> postList = ProfileEditApi.getService().reqRescreateUser(token, url, getProfilePutObj());

        Log.v("SignUp", "(Put)token for header userProfile" + token);

        postList.enqueue(new Callback<ProfileEditResponse>() {
            @Override
            public void onResponse(Call<ProfileEditResponse> call, Response<ProfileEditResponse> response) {


//                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(put)profile = success");

                ProfileEditResponse postList1 = response.body();
                Log.v("SignUp", "(put)profile = status code = " + response.code());


                if (postList1 != null) {


                    Log.v("SignUp", "(put)profile success =" + postList1.getSuccess());

                    if(postList1.getSuccess() == true){
                        Intent intent = new Intent(getContext(), FragmentActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);


                    }




                }


//
//                if (postList1.getSuccess() == true) {
//
//                    Toast.makeText(LoginActivityApp.this, "Success", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivityApp.this, FragmentActivity.class);
//                    startActivity(intent);
//
//
//                } else {
//                    Toast.makeText(LoginActivityApp.this, "Please enter correct username/password", Toast.LENGTH_SHORT).show();
//
//                }


            }

            @Override
            public void onFailure(Call<ProfileEditResponse> call, Throwable t) {

                Toast.makeText(getContext(), "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(put)error in link");


            }
        });


    }

    public void getFullProfileData() {

        Log.v("SignUp1", "GET FULL PROFILE DATA");


        String url = "https://arkaahealthapp.com/api/v1/users/profiles/"+UNIQUE_ID1;
        Log.v("SignUp2","UNIQUE ID GET FULL PROFILE DATA"+UNIQUE_ID1);
        Log.v("SignUp3","url link = "+url);


        Call<FullProfileGetViewResponse> postList = FullProfileGetViewApi.getService().FullProfileGetView(token, url);

        Log.v("SignUp", "(Full profile get)token for header userProfile get" + token);

        postList.enqueue(new Callback<FullProfileGetViewResponse>() {
            @Override
            public void onResponse(Call<FullProfileGetViewResponse> call, Response<FullProfileGetViewResponse> response) {


//                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(Full Profile Get)profile get = success");

                FullProfileGetViewResponse postList1 = response.body();
                Log.v("SignUp", "(Full Profile Get)profile get = status code = " + response.code());
                Log.v("SignUp", "(Full Profile Get)Response = " + response.code());
                Log.v("SignUp", "(Full Profile Get)postList1 = " + postList1);
//                Log.v("SignUp", "(Full Profile Get)postList1 = " + (   !postList1.getData().isEmpty()  ));
//                Log.v("SignUp", "(Full Profile Get)postList1 = " + (  postList1.getData() ));







                if (postList1 != null  && !postList1.getData().isEmpty()) {

                    PROFILE_FLAG = 1;

                    Log.v("SignUp","getprofiledata success = "+postList1.getSuccess());

                    Log.v("SignUp", "(Full Profile Get)profile success get =" + postList1.getSuccess());


                    //                List<Datum> data = postList1.getData();

                    List<arkaa.health.user.arkaahealthcare.Profile.ModalClasses.FullProfileView.Datum> data = postList1.getData();

                    arkaa.health.user.arkaahealthcare.Profile.ModalClasses.FullProfileView.Datum FullProfileGetdata = data.get(0);


                    Log.v("SignUp", "(Full Profile Get) Aadhar no =" + FullProfileGetdata.getAadhaarNo());

                    PROFILE_ID = FullProfileGetdata.getProfileId().toString();

                    bloodGroupGet = FullProfileGetdata.getBloodGroup();
                    heightGet = FullProfileGetdata.getHeight();
                    weightGet = FullProfileGetdata.getWeight();
                    ageGet = FullProfileGetdata.getAge();
                    dateOfBirthGet = FullProfileGetdata.getDob();
                    genderGet = FullProfileGetdata.getGender();
                    addressGet = FullProfileGetdata.getAddress();
                    aadhaarNoGet = FullProfileGetdata.getAadhaarNo();
                    ailmentsGet = FullProfileGetdata.getPreviousAilments();
                    medicalHistoryGet = FullProfileGetdata.getFamilyMedicalRecords();
                    accountTypeGet = FullProfileGetdata.getAccountType();

                    cityGet = FullProfileGetdata.getCity();
                    stateGet = FullProfileGetdata.getState();
                    pincodeGet = FullProfileGetdata.getPincode();


                    bloodGroupAutoCompleteTextView.setText(""+bloodGroupGet);
                    heightEditText.setText(""+heightGet);
                    weightEditText.setText(""+weightGet);
                    ageEditText.setText(""+ageGet);
                    dateOfBirthEditText.setText(""+dateOfBirthGet);
                    genderAutoCompleteTextView.setText(""+genderGet);
                    addressEditText.setText(""+addressGet);
                    aadhaarNoEditText.setText(""+aadhaarNoGet);
                    ailmentsEditText.setText(""+ailmentsGet);
                    medicalHistoryEditText.setText(""+medicalHistoryGet);
                    accountTypeAutoCompleteTextView.setText(""+accountTypeGet);

                    cityEditText.setText(""+cityGet);
                    stateEditText.setText(""+stateGet);
                    pincodeEditText.setText(""+pincodeGet);


                    //profile picture

                    if (FullProfileGetdata.getProfilePicturePath() != null) {
                        profilepicGet = FullProfileGetdata.getProfilePicturePath();
                        Log.v("post", "profilepic Get =" + profilepicGet);
                    } else {
//                        profilepicGet = String.valueOf(R.drawable.profile);
                        DoctorProfile.setImageResource(R.drawable.profile);
                        Log.v("post", "profilepic Get blank =" + profilepicGet);
                    }
// Load the image using Glide]

                    if (!FullProfileGetdata.getProfilePicturePath().equals("profile pic")) {
                        Glide.with(getContext())
//                            .using(new FirebaseImageLoader())
                                .load(profilepicGet)
                                .into(DoctorProfile);
                    }


                    //profile picture






                }


            }

            @Override
            public void onFailure(Call<FullProfileGetViewResponse> call, Throwable t) {

                Toast.makeText(getContext(), "No Network, Please check your internet connection", Toast.LENGTH_SHORT).show();
                Log.v("SignUp", "(post)error in link (get)");


            }
        });


    }

    public void campare() {


        if (campareCondition()) {

            Log.v("SignUp1", "campareCondition = exit");

            Intent intent = new Intent(getContext(), FragmentActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);



        } else {



            Log.v("SignUp1", "campareCondition = putData");

            putData();

        }


    }

    public boolean campareCondition() {

        validate();

        boolean result;

        boolean testing;
        testing = userNameGet.equals(userNameEditText.getText().toString());
        Log.v("SignUp1", "testing campare for username =" + testing + ".....");
        Log.v("SignUp1", "usernameget=" + userNameGet);
        Log.v("SignUp1", "userNameEditText=" + userNameEditText.getText().toString());
        Log.v("SignUp1","firstName = "+  (    firstNameGet.equals(firstNameEditText.getText().toString())   )  );
        Log.v("SignUp1","LastNameGet = "+  (  LastNameGet.equals(LastNameEditText.getText().toString())     )  );
        Log.v("SignUp1","addressGet = "+  (   addressGet.equals(addressEditText.getText().toString())    )  );
        Log.v("SignUp1","emailIdGet = "+  (   emailIdGet.equals(emailIdEditText.getText().toString())    )  );
        Log.v("SignUp1","mobileNoGet = "+  (     mobileNoGet.equals(mobileNoEditText.getText().toString())   )  );
        Log.v("SignUp1","aadhaarNoGet = "+  (    aadhaarNoGet.equals(aadhaarNoEditText.getText().toString())   )  );
        Log.v("SignUp1","bloodGroupGet = "+  (     bloodGroupGet.equals(bloodGroupAutoCompleteTextView.getText().toString())   )  );
        Log.v("SignUp1","heightGet = "+  (    heightGet.equals(heightEditText.getText().toString())   )  );
        Log.v("SignUp1","weightGet = "+  (     weightGet.equals(weightEditText.getText().toString())   )  );
        Log.v("SignUp1","ageGet = "+  (    ageGet.equals(ageEditText.getText().toString())   )  );
        Log.v("SignUp1","dateOfBirthGet = "+  (  dateOfBirthGet.equals(dateOfBirthEditText.getText().toString())     )  );
        Log.v("SignUp1","genderGet = "+  (     genderGet.equals(genderAutoCompleteTextView.getText().toString())   )  );
//        Log.v("SignUp1","accountTypeGet = "+  (    accountTypeGet.equals(accountTypeAutoCompleteTextView.getText().toString())    )  );
//        Log.v("SignUp1","ailmentsGet = "+  (   accountTypeGet.equals(accountTypeAutoCompleteTextView.getText().toString())     )  );
//        Log.v("SignUp1","medicalHistoryGet = "+  (    medicalHistoryGet.equals(medicalHistoryEditText.getText().toString()   )  ));



        result = userNameGet.equals(userNameEditText.getText().toString()) &&
                firstNameGet.equals(firstNameEditText.getText().toString()) &&
                LastNameGet.equals(LastNameEditText.getText().toString()) &&
                addressGet.equals(addressEditText.getText().toString()) &&
                emailIdGet.equals(emailIdEditText.getText().toString()) &&
                mobileNoGet.equals(mobileNoEditText.getText().toString()) &&
                aadhaarNoGet.equals(aadhaarNoEditText.getText().toString()) &&
                bloodGroupGet.equals(bloodGroupAutoCompleteTextView.getText().toString()) &&
                heightGet.equals(heightEditText.getText().toString()) &&
                weightGet.equals(weightEditText.getText().toString()) &&
                ageGet.equals(ageEditText.getText().toString()) &&
                dateOfBirthGet.equals(dateOfBirthEditText.getText().toString()) &&
                genderGet.equals(genderAutoCompleteTextView.getText().toString()) &&
                accountTypeGet.equals(accountTypeAutoCompleteTextView.getText().toString()) &&
                ailmentsGet.equals(ailmentsEditText.getText().toString()) &&
                medicalHistoryGet.equals(medicalHistoryEditText.getText().toString()) &&
                cityGet.equals(cityEditText.getText().toString()) &&
                stateGet.equals(cityEditText.getText().toString()) &&
                pincode.equals(pincodeEditText.getText().toString());

        Log.v("SignUp1", "campareCondition = " + result);
        return result;

    }

    public ProfilePost getProfilePostObj() {

        ProfilePost profilePost = new ProfilePost();
        validate();

        profilePost.setUniqueId(UNIQUE_ID1);
        profilePost.setBloodGroup(bloodGroup);
        profilePost.setHeight(height);
        profilePost.setWeight(weight);
        profilePost.setAge(age);
        profilePost.setDob(dateOfBirth);
        profilePost.setGender(gender);
        profilePost.setState(state);
        profilePost.setCity(city);
        profilePost.setAddress(address);
        profilePost.setPincode(pincode);
        profilePost.setAadhaarNo(aadhaarNo);
        profilePost.setPreviousAilments(ailments);
        profilePost.setFamilyMedicalRecords(medicalHistory);
        profilePost.setAccountType(accountType);
//        profilePost.setProfilePicturePath("asasas");

        if (firebaseDoctorProfilePath != null) {
            Log.v("post", "post function = " + firebaseDoctorProfilePath);
            profilePost.setProfilePicturePath(firebaseDoctorProfilePath);
        } else {
            profilePost.setProfilePicturePath("profile pic");
        }




        return profilePost;


    }

    public ProfileEditPut getProfilePutObj() {

        ProfileEditPut profileEditPut = new ProfileEditPut();
        validate();

        profileEditPut.setProfileId(PROFILE_ID);

        profileEditPut.setUniqueId(UNIQUE_ID1);
        profileEditPut.setBloodGroup(bloodGroup);
        profileEditPut.setHeight(height);
        profileEditPut.setWeight(weight);
        profileEditPut.setAge(age);
        profileEditPut.setDob(dateOfBirth);
        profileEditPut.setGender(gender);
        profileEditPut.setState(state);
        profileEditPut.setCity(city);
        profileEditPut.setAddress(address);
        profileEditPut.setPincode(pincode);
        profileEditPut.setAadhaarNo(aadhaarNo);
        profileEditPut.setPreviousAilments(ailments);
        profileEditPut.setFamilyMedicalRecords(medicalHistory);
        profileEditPut.setAccountType(accountType);
//        profileEditPut.setProfilePicturePath("asasas");


        if (firebaseDoctorProfilePath != null) {

            profileEditPut.setProfilePicturePath(firebaseDoctorProfilePath);
        } else {
            profileEditPut.setProfilePicturePath(profilepicGet);
        }



        return profileEditPut;


    }

    public void onSignupFailed() {
       // Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();

        submitButton.setEnabled(true);
    }


}
