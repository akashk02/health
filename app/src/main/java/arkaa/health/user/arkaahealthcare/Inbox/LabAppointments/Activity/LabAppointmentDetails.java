package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.PatientAddress;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.PatientDetail;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Test;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.DisplayPrescription;
import arkaa.health.user.arkaahealthcare.R;

import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.PatientAddress;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.PatientDetail;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Test;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LabAppointmentDetails extends AppCompatActivity {

    @BindView(R.id.lab_name)
    TextView labNameEditText;
    @BindView(R.id.lab_address)
    TextView labAddressEditText;
    @BindView(R.id.lab_city)
    TextView labCityEditText;
    @BindView(R.id.lab_state)
    TextView labStateEditText;

    String labName;
    String labAddress;
    String labCity;
    String labState;

    @BindView(R.id.lab_appointment_status)
    TextView labAppointmentEditText;
    @BindView(R.id.lab_appointment_date)
    TextView labAppointmentDateEditText;
    @BindView(R.id.lab_appointment_time)
    TextView labTimeEditText;
    @BindView(R.id.lab_appointment_type)
    TextView labAppointmentTypeEditText;

    String labAppointmentStatus;
    String labAppointmentDate;
    String labAppointmentTime;
    String labAppointmentType;


    @BindView(R.id.patient_name)
    TextView patientNameEditText;
    @BindView(R.id.patient_age)
    TextView patientAgeEditText;
    @BindView(R.id.patient_mobile)
    TextView patientMobileEditText;
    @BindView(R.id.patient_email)
    TextView patientEmailEditText;
    @BindView(R.id.patient_gender)
    TextView patientGenderEditText;

    @BindView(R.id.patient_address) TextView patientAddressEditText;
    @BindView(R.id.patient_area) TextView patientAreaEditText;
    @BindView(R.id.patient_landmark) TextView patientLandmarkEditText;
    @BindView(R.id.patient_pincode) TextView patientPincodeEditText;



    String patientName;
    String patientAge;
    String patientMobile;
    String patientEmail;
    String patientGender;
    String patientAddress;
    String patientArea;
    String patientLandmark;
    String patientPincode;

    @BindView(R.id.patient_test1)
    TextView patientTest1TextView;
    @BindView(R.id.patient_test2)
    TextView patientTest2TextView;
    @BindView(R.id.patient_test3)
    TextView patientTest3TextView;
    @BindView(R.id.patient_test4)
    TextView patientTest4TextView;

    String patientTest1;
    String patientTest2;
    String patientTest3;
    String patientTest4;

    @BindView(R.id.tests_linear_layout)
    CardView testsLinearLayout;

    @BindView(R.id.tests_text_view)
    TextView testsTextView;

    @BindView(R.id.test2_LL)
    LinearLayout test2LinearLayout;

    @BindView(R.id.test3_LL)
    LinearLayout test3LinearLayout;

    @BindView(R.id.test4_LL)
    LinearLayout test4LinearLayout;

    @BindView(R.id.view_all_tests)
    Button viewAllTestButton;

    @BindView(R.id.view_prescription_button)
    Button viewPresButton;

    private Datum labAppointmentDetailObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_appointment_details);

        ButterKnife.bind(this);

        labAppointmentDetailObject = getIntent().getParcelableExtra("LAB_APPOINTMENT_DETAIL_OBJECT");

        viewAllTestButton.setVisibility(View.GONE);
        viewPresButton.setVisibility(View.GONE);

        if (labAppointmentDetailObject != null) {
            displayData();
        }



    }

    public void displayData() {

        labName = labAppointmentDetailObject.getLabName();
        labAddress = labAppointmentDetailObject.getLabAddress();
        labCity = labAppointmentDetailObject.getLabCity();
        labState = labAppointmentDetailObject.getLabState();

        labAppointmentStatus = labAppointmentDetailObject.getAppointmentStatus();
        labAppointmentDate = labAppointmentDetailObject.getAppointmentDate();
        labAppointmentTime = labAppointmentDetailObject.getProbableStartTime();
        labAppointmentType = labAppointmentDetailObject.getConsultationTypeId().toString();

        List<PatientDetail> patientDetails = labAppointmentDetailObject.getPatientDetails();
        if(patientDetails != null){

            PatientDetail patientDetail = patientDetails.get(0);

            patientName = patientDetail.getName();
            patientAge = patientDetail.getAge().toString();
            patientMobile = patientDetail.getMobile();
            patientEmail = patientDetail.getEmail();
            patientGender = patientDetail.getGender();

        }

        PatientAddress patientAddressObj = labAppointmentDetailObject.getPatientAddress().get(0);
        if(patientAddressObj != null){
            patientAddress  = patientAddressObj.getAddress();
            patientArea = patientAddressObj.getLocality();
            patientLandmark = patientAddressObj.getLandmark();
            patientPincode = patientAddressObj.getPincode();
        }

        if(labAppointmentDetailObject.getPrescriptionPath() != null){
            viewPresButton.setVisibility(View.VISIBLE);
            viewPresButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DisplayPrescription.class);
                    intent.putExtra("PRESCRIPTION_URL",labAppointmentDetailObject.getPrescriptionPath());
                    startActivity(intent);
                }
            });
        }

        List<Test> testsArrayList = labAppointmentDetailObject.getTests();

        if (testsArrayList == null) {
            testsLinearLayout.setVisibility(View.GONE);
//            testsTextView.setVisibility(View.GONE);
        } else {

            switch (testsArrayList.size()) {


                case 1:
                    patientTest1TextView.setText(""+testsArrayList.get(0).getTestName());
                    test2LinearLayout.setVisibility(View.GONE);
                    test3LinearLayout.setVisibility(View.GONE);
                    test4LinearLayout.setVisibility(View.GONE);
                    break;

                case 2:
                    patientTest1TextView.setText(""+testsArrayList.get(0).getTestName());
                    patientTest2TextView.setText(""+testsArrayList.get(1).getTestName());
                    test3LinearLayout.setVisibility(View.GONE);
                    test4LinearLayout.setVisibility(View.GONE);
                    break;

                case 3:
                    patientTest1TextView.setText(""+testsArrayList.get(0).getTestName());
                    patientTest2TextView.setText(""+testsArrayList.get(1).getTestName());
                    patientTest3TextView.setText(""+testsArrayList.get(2).getTestName());
                    test4LinearLayout.setVisibility(View.GONE);
                    break;

                case 4:
                    patientTest1TextView.setText(""+testsArrayList.get(0).getTestName());
                    patientTest2TextView.setText(""+testsArrayList.get(1).getTestName());
                    patientTest3TextView.setText(""+testsArrayList.get(2).getTestName());
                    patientTest4TextView.setText(""+testsArrayList.get(3).getTestName());
                    break;

                default:
                    viewAllTestButton.setVisibility(View.VISIBLE);
                    viewAllTestButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent intent = new Intent(ViewPatientDetails.this,ViewAllTests.class);
//                            intent.putParcelableArrayListExtra("LIST_OF_TESTS",(ArrayList<Test>) testsArrayList);
//                            startActivity(intent);
                        }
                    });

            }


        }







    }


}
