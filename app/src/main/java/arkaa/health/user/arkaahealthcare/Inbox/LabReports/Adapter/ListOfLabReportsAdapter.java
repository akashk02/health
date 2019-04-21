package arkaa.health.user.arkaahealthcare.Inbox.LabReports.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities.ShareReports;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.Activity.LabReportsDedtail;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.Activity.ListOfLabReports;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.DisplayPrescription;
import arkaa.health.user.arkaahealthcare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.DisplayPrescription;

public class ListOfLabReportsAdapter extends RecyclerView.Adapter<ListOfLabReportsAdapter.ViewHolder> {
    private List<Datum> listOfLabReport;
    private String classType;
    private Activity context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView labIdTextview;
        private TextView labNameTextView;
        private TextView patientMobileNoTextView;
        private TextView patienstatusTextView;
        private TextView labdateTextView;
        private TextView labTimeTextView;

        private Button labViewDetailButton ;
        private Button lavViewReportButton;





        public ViewHolder(View v) {
            super(v);

            labIdTextview = v.findViewById(R.id.lab_id);
            labNameTextView = v.findViewById(R.id.lab_name);
            patientMobileNoTextView = v.findViewById(R.id.patient_mobile);
            labdateTextView = v.findViewById(R.id.lab_date);
            labTimeTextView = v.findViewById(R.id.lab_time);

            labViewDetailButton = v.findViewById(R.id.view_details_button);
            lavViewReportButton = v.findViewById(R.id.view_report_button);



        }
    }

    public ListOfLabReportsAdapter(List<Datum> listOfLabReport,String classType,Activity context) {
        this.listOfLabReport = listOfLabReport ;
        this.classType = classType;
        this.context = context;
    }

    @Override
    public ListOfLabReportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lab_reports_recyclerview, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(listOfLabReport != null){

            String labId = ": "+listOfLabReport.get(position).getLabUid();
            String labName = ": "+listOfLabReport.get(position).getLabName();
           // String mobileNumber = ": "+listOfLabReport.get(position).getMobileNo();
            final String time = ": "+getTime(listOfLabReport.get(position).getLastUpdatedOn());
            final String date = ": "+getDate(listOfLabReport.get(position).getLastUpdatedOn());
            final String reportPath = listOfLabReport.get(position).getReportPath();
            final int reportId = listOfLabReport.get(position).getId();


            holder.labIdTextview.setText(labId);
            holder.labNameTextView.setText(labName);
           // holder.patientMobileNoTextView.setText(mobileNumber);
            holder.labTimeTextView.setText(time);
            holder.labdateTextView.setText(date);

            if(!classType.equals("SHARE_REPORTS")) {

                holder.labViewDetailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), LabReportsDedtail.class);
                        intent.putExtra("PATIENT_REPORT_DETAILS", listOfLabReport.get(position));
                        intent.putExtra("PATIENT_APPOINTMENT_DATE", date);
                        intent.putExtra("LAB_APPOINTMENT_TIME", time);
                        v.getContext().startActivity(intent);

                    }
                });

            } else {
                holder.lavViewReportButton.setVisibility(View.INVISIBLE);
                holder.labViewDetailButton.setText("share");
                holder.labViewDetailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(),ShareReports.class);
                        intent.putExtra("REPORT_ID",reportId);
                        context.setResult(Activity.RESULT_OK,intent);
                        context.finish();

                    }
                });


            }

            holder.lavViewReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(reportPath != null && classType.equals("SHARE_REPORTS")) {
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(reportPath));
//                        v.getContext().startActivity(browserIntent);

                        Intent intent = new Intent(v.getContext(), DisplayPrescription.class);
                        intent.putExtra("REPORT_PATH",reportPath);
                        context.setResult(Activity.RESULT_OK,intent);
                        context.finish();


                    } else {
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(reportPath));
//                        v.getContext().startActivity(browserIntent);

                        Intent intent = new Intent(v.getContext(), DisplayPrescription.class);
                        intent.putExtra("PRESCRIPTION_URL",reportPath);
                        v.getContext().startActivity(intent);




                    }
                }
            });





        }





    }

    @Override
    public int getItemCount() {
        return listOfLabReport.size();
    }

    public String getDate(String realTime) {

        String str ;
        try {

            String time = realTime.substring(0, realTime.indexOf("."));

            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            //   String outputPattern = "dd-MMM-yyyy h:mm a";
            String outputPattern = "dd-MMM-yyyy";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
                Log.v("new_time", "date = " + date + "str = " + str);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("new_time", "exception " + e.getMessage());
            }

        }catch (Exception e){

            str = realTime;

        }



        Log.v("new_time","str = "+str);
        return str;


    }

    public String getTime(String realTime) {

        String str ;
        try {

            String time = realTime.substring(0, realTime.indexOf("."));

            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            //   String outputPattern = "dd-MMM-yyyy h:mm a";
            String outputPattern = "h:mm a";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
                Log.v("new_time", "date = " + date + "str = " + str);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("new_time", "exception " + e.getMessage());
            }

        }catch (Exception e){

            str = realTime;

        }



        Log.v("new_time","str = "+str);
        return str;


    }




}

