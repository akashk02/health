package arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Activities.EdocAppDetailsPts;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Datum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Griddatum;
import arkaa.health.user.arkaahealthcare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Activities.EdocAppDetailsPts;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Datum;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Griddatum;

public class EdocAppListPtsAdapter extends RecyclerView.Adapter<EdocAppListPtsAdapter.ViewHolder> {

    List<Datum> hospitalDetailsArrayList;
    List<Griddatum> listOfDocArrayList;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView docNameTextView;
        public TextView docEduTextView;
        public TextView docAddTextView;
        public TextView docIdTextView;
        public TextView docTimeTextView;
        public TextView docDateTextView;
        public Button viewDetailButton;
        public ImageView DocProfilePicIV;


        public ViewHolder(View v) {
            super(v);

            docNameTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_name);
            docEduTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_education);
            docAddTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_address);
            docIdTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_id);
            docTimeTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_time);
            docDateTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_date);
            viewDetailButton = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_viewdetails_button);
            DocProfilePicIV = v.findViewById(R.id.doctor_profile_pic);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EdocAppListPtsAdapter(List<Datum> hospitalDetailsArrayList, List<Griddatum> listOfDocArrayList, Context context) {
        this.hospitalDetailsArrayList = hospitalDetailsArrayList ;
        this.listOfDocArrayList = listOfDocArrayList;
        context = this.context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EdocAppListPtsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView ;

        itemView = LayoutInflater.from(parent.getContext())
                    .inflate(arkaa.health.user.arkaahealthcare.R.layout.edoc_app_list_pts_recyclerview, parent, false);


            return new EdocAppListPtsAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EdocAppListPtsAdapter.ViewHolder holder, final int position) {

        if(listOfDocArrayList !=null){

            String docName = ""+listOfDocArrayList.get(position).getDoctorname();
            String docEdu = ""+listOfDocArrayList.get(position).getSpecialization();
            final String docId = ""+listOfDocArrayList.get(position).getDoctorId();
            String docAdd = "";
            final String docTime = ""+listOfDocArrayList.get(position).getPtsDoctorconsultChTimeSlots();
            final String docDate = ""+getDate(listOfDocArrayList.get(position).getPtsDoctorconsultChDate());
            final String ptsDoctorConsultChId = ""+listOfDocArrayList.get(position).getPtsDoctorconsultChId();
            final Boolean paymentConfirmation = listOfDocArrayList.get(position).getPaymentConfirmation();
            final String paymentMode = ""+listOfDocArrayList.get(position).getPaymentMode();
            final Double docFees = listOfDocArrayList.get(position).getDoctorFees();
            final String docFeesString = ""+docFees;
            final String appointmentType = ""+listOfDocArrayList.get(position).getPtsDoctorconsultChConsultMode();
            String ptsId = "";

            final String DocprofilePic = "http://35.163.147.45"+listOfDocArrayList.get(position).getDoctorProfilePath();


            if(hospitalDetailsArrayList != null){
                docAdd = ""+hospitalDetailsArrayList.get(0).getPtsDoctorconsultAddress();
                ptsId = hospitalDetailsArrayList.get(0).getPtsId();
                }

                final String ptsIdIntent = ptsId;

            holder.docNameTextView.setText(docName);
            holder.docEduTextView.setText(docEdu);
            holder.docIdTextView.setText(docId);
            holder.docAddTextView.setText(docAdd);
            holder.docTimeTextView.setText(docTime);
            holder.docDateTextView.setText(docDate);

            try {
                Glide.with(context).load(DocprofilePic).into(holder.DocProfilePicIV);
            } catch (Exception e){
                Log.v("tryCatch","ListOfDocAdapter glide Exeption e = "+e.getMessage());
            }

            holder.viewDetailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EdocAppDetailsPts.class);
                    intent.putExtra("APPOINTMENT_DATE",docDate);
                    intent.putExtra("APPOINTMENT_TIME",docTime);
                    intent.putExtra("INDEX",position);
                    intent.putExtra("PTS_DOC_CON_CH_ID",ptsDoctorConsultChId);
                    intent.putExtra("PAYMENT_CONFIRMATION",paymentConfirmation);
                    intent.putExtra("PAYMENT_MODE",paymentMode);
                    intent.putExtra("PTS_ID",ptsIdIntent);
                    intent.putExtra("DOCTOR_ID",docId);
                    intent.putExtra("DOC_FEES",docFees);
                    intent.putExtra("DOC_FEES_STRING",docFeesString);
                    intent.putExtra("APPOINTMENT_TYPE",appointmentType);
                    intent.putExtra("DOCTOR_IMAGE_URL",DocprofilePic);


                    v.getContext().startActivity(intent);
                }
            });








        }



        }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfDocArrayList.size();
    }



    public String getDate(String realTime) {

        String str ;
        try {

//            String time = realTime.substring(0, realTime.indexOf("."));
            String  time = realTime;

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


    }}



