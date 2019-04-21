package arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.Adapters;

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


import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.Datum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses.Datum;

public class EprescriptionAdapter extends RecyclerView.Adapter<EprescriptionAdapter.ViewHolder> {
    private String[] mDataset;
    private List<Datum> listOfPrescriptions ;
    private Activity context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView doctorNameTextView ;
        public TextView doctorDiagnosisTextView ;
        public TextView dateTextView ;
        public TextView timeTextView ;
        public Button viewButton;
        public Button shareButton ;


        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            doctorNameTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.doctor_name);
            doctorDiagnosisTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.doctor_description);
            dateTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.doctor_date);
            timeTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.doctor_time);
            viewButton = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.view_button);
            shareButton = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.share_button);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EprescriptionAdapter(String[] myDataset, List<Datum> listOfPrescriptions, Activity context) {
        mDataset = myDataset;
        this.listOfPrescriptions = listOfPrescriptions ;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EprescriptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView ;


            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(arkaa.health.user.arkaahealthcare.R.layout.e_prescription_recyclerview, parent, false);



        return new EprescriptionAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EprescriptionAdapter.ViewHolder holder, final int position) {


        if(listOfPrescriptions != null){

            holder.doctorNameTextView.setText(""+listOfPrescriptions.get(position).getDoctorId());
            holder.doctorDiagnosisTextView.setText(""+listOfPrescriptions.get(position).getDiagnosis());

            String dateTime = listOfPrescriptions.get(position).getDate();
            holder.dateTextView.setText(""+getDate(dateTime));
            holder.timeTextView.setText(""+getTime(dateTime));
            String presUrlVar = listOfPrescriptions.get(position).getPrescriptionPath();
            if(!presUrlVar.contains("https")){
                presUrlVar = "http://35.163.147.45"+presUrlVar;
            }
            final String prescriptionUrl = presUrlVar;


            holder.viewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Log.v("touchListner","setOnClickListener");
//                    Intent intent = new Intent(v.getContext(),DisplayPrescription.class);
//                    intent.putExtra("PRESCRIPTION_URL",listOfPrescriptions.get(position).getPrescriptionPath());
//                    v.getContext().startActivity(intent);

                    if(prescriptionUrl != null) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(prescriptionUrl));
                        v.getContext().startActivity(browserIntent);

                    }




                }
            });

            holder.shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(),lab_details.class);
//                    intent.putExtra("PRESCRIPTION_ID",listOfPrescriptions.get(position).getId());
//                    Log.v("prescriptionId","prescriptionId adapter "+listOfPrescriptions.get(position).getId());
//                    context.setResult(Activity.RESULT_OK,intent);
//                    context.finish();



                }
            });



                holder.shareButton.setVisibility(View.GONE);
                Log.v("buttonView"," holder.shareButton.setVisibility(View.GONE))");



//            holder.viewButton.setVisibility(View.GONE);
//            holder.shareButton.setVisibility(View.GONE);





        }




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfPrescriptions.size();
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

