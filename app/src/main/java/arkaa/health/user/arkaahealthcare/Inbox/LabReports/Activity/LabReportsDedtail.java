package arkaa.health.user.arkaahealthcare.Inbox.LabReports.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.DisplayPrescription;
import arkaa.health.user.arkaahealthcare.R;

import arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.DisplayPrescription;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LabReportsDedtail extends AppCompatActivity {

    private Datum patientReportDetail ;
    private String patientAppointmentDate ;
    private String labAppointmentTime ;


    @BindView(R.id.patient_id)
    TextView patientIdTextView;

    @BindView(R.id.patient_name)
    TextView patientNameTextView;

    @BindView(R.id.patient_mobile)
    TextView patientMobNoTextView;

    @BindView(R.id.patient_email)
    TextView patientEmailTextView;

    @BindView(R.id.patient_date)
    TextView patientdateTextView;

    @BindView(R.id.patient_time)
    TextView patientTimeTextView;

    @BindView(R.id.patient_consultation_type)
    TextView patientConsultationTyperTextView;

    @BindView(R.id.patient_cost)
    TextView patientCostTextView;

    @BindView(R.id.patient_report_name)
    TextView patientReportNameTextView;

    @BindView(R.id.view_report)
    Button viewReportButton;

    @BindView(R.id.view_prescription)
    Button viewPresButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_reports_dedtail);


        ButterKnife.bind(this);

        patientReportDetail = getIntent().getParcelableExtra("PATIENT_REPORT_DETAILS");
        patientAppointmentDate =""+ getIntent().getStringExtra("PATIENT_APPOINTMENT_DATE");
        labAppointmentTime = ""+getIntent().getStringExtra("LAB_APPOINTMENT_TIME");

        if(patientReportDetail != null){
            String patientId = ""+ patientReportDetail.getLabUid();
            String patientName = ""+patientReportDetail.getLabName();
           // String patientMobileNo = ""+patientReportDetail.getMobileNo();
          //  String patientEmailId = ""+patientReportDetail.getEmail();
          //  String time = ""+patientReportDetail.getLastUpdatedOn();
            String consultationType = ""+patientReportDetail.getConsultationType();
            String cost = ""+patientReportDetail.getTotalCost();
            String reportName = ""+patientReportDetail.getReportName();
            final String reportPath = patientReportDetail.getReportPath();
            final String prescriptionPath  = patientReportDetail.getPrescriptionPath();

            patientIdTextView.setText(patientId);
            patientNameTextView.setText(patientName);
         //   patientMobNoTextView.setText(patientMobileNo);
          //  patientEmailTextView.setText(patientEmailId);
            patientdateTextView.setText(patientAppointmentDate);
            patientTimeTextView.setText(labAppointmentTime);
            patientConsultationTyperTextView.setText(consultationType);
            patientCostTextView.setText(cost);
            patientReportNameTextView.setText(reportName);



            viewReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DisplayPrescription.class);
                    intent.putExtra("PRESCRIPTION_URL",reportPath);
                    v.getContext().startActivity(intent);

                }
            });

            viewPresButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DisplayPrescription.class);
                    intent.putExtra("PRESCRIPTION_URL",prescriptionPath);
                    v.getContext().startActivity(intent);

                }
            });



        }



    }
}
