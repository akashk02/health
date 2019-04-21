package arkaa.health.user.arkaahealthcare.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import arkaa.health.user.arkaahealthcare.Fragment.modalClasses.DocCancelAppFirebase;
import arkaa.health.user.arkaahealthcare.Login.Activity.LoginActivityApp;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.DisplayPrescription;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.PatientPrescriptionFirebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Fragment.modalClasses.DocCancelAppFirebase;

public class FragmentActivity extends AppCompatActivity {

    //firebase prescription

    private ChildEventListener mChildEventListener;
    private ChildEventListener mDocCancelAppChildEventListener;

    private DatabaseReference mPatPrescriptionDatabaseRef ;
    private DatabaseReference mCancelAppointmentDatabaseRef;
    private FirebaseDatabase mFirebaseDataBase;
    private List<PatientPrescriptionFirebase> patientPrescriptionFirebasesArray;
    private int count ;
    private int count1;
    private PatientPrescriptionFirebase patient;

    private String patientUniueId;
    private String patientUniqueIdFromHSF;

    private DocCancelAppFirebase docCancelAppFirebase;
    private List<DocCancelAppFirebase> docCancelAppFirebasesArrayList;





    //firebase prescription




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // Set the content of the activity to use the activity_main.xml layout file

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


        Log.v("errorChecking","Fragment Activity");

        //firebase prescription


        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        patientUniueId = preferences.getString("PATIENT_UNIQUE_ID", "1234");
        String userUniqueId = preferences.getString("USERUNIQUEID", "1234");
        String username = preferences.getString("USERNAME", "1234");
        String token = preferences.getString("TOKEN", "1234");

        Log.v("fargActivity ","patientUniueId"+patientUniueId);

        Log.v("uniqueIdSequence1","Fragment Activity ");
        Log.v("uniqueIdSequence1","patientUniueId = "+patientUniueId);
        Log.v("uniqueIdSequence1","userUniqueId = "+userUniqueId);
        Log.v("uniqueIdSequence1","username = "+username);
        Log.v("uniqueIdSequence1","token = "+token);






        patientPrescriptionFirebasesArray = new ArrayList<>();
        docCancelAppFirebasesArrayList = new ArrayList<>();
        count = 0;
        count1= 0;

        mFirebaseDataBase = FirebaseDatabase.getInstance();

        mPatPrescriptionDatabaseRef = mFirebaseDataBase.getReference().child("patient_prescription").child(patientUniueId);
        Log.v("objectTesting1","mPatPrescriptionDatabaseRef "+mPatPrescriptionDatabaseRef);
        mCancelAppointmentDatabaseRef = mFirebaseDataBase.getReference().child("Appointment_Cancel_&_Confirm_Status").child(patientUniueId);




        //firebase prescription

        try {


            if (mChildEventListener == null) {
                Log.v("objectTesting1", "mChildEventListener == null value = " + mChildEventListener);

                mChildEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Log.v("objectTesting1", "counter = " + count);

                        PatientPrescriptionFirebase patientPrescriptionFirebase = dataSnapshot.getValue(PatientPrescriptionFirebase.class);
                        patientPrescriptionFirebasesArray.add(patientPrescriptionFirebase);
                        boolean recent = patientPrescriptionFirebase.getCount();

                        if (recent) {
                            showDialogBox(patientPrescriptionFirebase.getPrescriptionPath());
                        }


                        PatientPrescriptionFirebase patientPrescriptionFirebase1 = new PatientPrescriptionFirebase();
                        patientPrescriptionFirebase1.setCount(false);
                        patientPrescriptionFirebase1.setDescription(patientPrescriptionFirebase.getDescription());
                        patientPrescriptionFirebase1.setDoctorUniqueId(patientPrescriptionFirebase.getDoctorUniqueId());
                        patientPrescriptionFirebase1.setPrescriptionPath(patientPrescriptionFirebase.getPrescriptionPath());

                        mPatPrescriptionDatabaseRef.child(dataSnapshot.getKey()).setValue(patientPrescriptionFirebase1);


                        Log.v("objectTesting1", " patientPrescriptionFirebasesArray  " + patientPrescriptionFirebasesArray + "friendlyMessagesArrayList size =" + patientPrescriptionFirebasesArray.size());
                        Log.v("objectTesting1", " patientPrescriptionFirebasesArray  doctor unique id " + patientPrescriptionFirebasesArray.get(count).getDoctorUniqueId());
                        Log.v("objectTesting1", " patientPrescriptionFirebasesArray  description " + patientPrescriptionFirebasesArray.get(count).getDescription());
                        Log.v("objectTesting1", " patientPrescriptionFirebasesArray  setvalue " + patientPrescriptionFirebasesArray.get(count).getDescription());


                        Log.v("objectTesting1", "counter = " + count);


                        count++;


                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };

                mPatPrescriptionDatabaseRef.addChildEventListener(mChildEventListener);

            }

        }catch (Exception e){
            Log.v("tryCatch","FragmentActivity e="+e);
        }



        if(mDocCancelAppChildEventListener == null){
            Log.v("objectTesting1","mDocCancelAppChildEventListener == null value = "+mDocCancelAppChildEventListener);

            mDocCancelAppChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    DocCancelAppFirebase docCancelAppObj = dataSnapshot.getValue(DocCancelAppFirebase.class);
                  //  Toast.makeText(FragmentActivity.this,"Your Appointment has been canceled",Toast.LENGTH_SHORT).show();
                    Log.v("CancelAppointmnet","count = "+count++);
                    mCancelAppointmentDatabaseRef.removeValue();


                    count1++;


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mCancelAppointmentDatabaseRef.addChildEventListener(mDocCancelAppChildEventListener);







        }
















    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FragmentActivity.this);


        builder.setTitle("Are you sure you would like to close Arkaa App?");
//        Toast.makeText(Login.this,"Hello",Toast.LENGTH_SHORT).show();

        //Yes Button

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(),"Yes button Clicked",Toast.LENGTH_LONG).show();
                Log.i("Code2care ", "Yes button Clicked!");

                finish();
            }
        });

        //No Button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(),"No button Clicked",Toast.LENGTH_LONG).show();
                Log.i("Code2care ","No button Clicked!");
                dialog.dismiss();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


       /* // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.option);


        TextView data = (TextView) dialog.findViewById(R.id.new_appointment);
        data.setText("Are you sure you would like to close Arkaa App?");

        TextView view = (TextView) dialog.findViewById(R.id.no);
        // if button is clicked, close the custom dialog
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login.this,Appointments.class);
//                startActivity(intent);
                dialog.dismiss();
            }
        });


        TextView No = (TextView) dialog.findViewById(R.id.yes);
        // if button is clicked, close the custom dialog
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.show();
*/


    }

    public void showDialogBox(final String url){

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.appointment);


        TextView data = (TextView) dialog.findViewById(R.id.new_appointment);
        data.setText("You have a new Prescription");

        Button view = (Button) dialog.findViewById(R.id.view);
// if button is clicked, close the custom dialog
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentActivity.this,DisplayPrescription.class);
                intent.putExtra("PRESCRIPTION_URL",url);
                startActivity(intent);
                dialog.dismiss();
            }
        });


        Button Cancel = (Button) dialog.findViewById(R.id.later);
// if button is clicked, close the custom dialog
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            SharedPreferences sharedPreferences = getSharedPreferences("token",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("TOKEN",null);
            editor.putString("USERNAME",null);
            editor.putString("IS_LOGINED",null );
            editor.putString("USERUNIQUEID",null);
            editor.putString("PATIENT_UNIQUE_ID",null);
            editor.putString("PATIENT_NAME",null);

//                    editor.putString("USERNAME",)
            editor.commit();

            Intent signout = new Intent(FragmentActivity.this,LoginActivityApp.class);
            signout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signout);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);



    }


}
