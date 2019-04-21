package arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Adapter;

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

import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Activity.DoctorDetail;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass.Datum;
import arkaa.health.user.arkaahealthcare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListOfDocAppAdapter extends RecyclerView.Adapter<ListOfDocAppAdapter.ViewHolder> {

    List<Datum> listOfDocArrayList;
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

            docNameTextView = v.findViewById(R.id.e_pts_doctor_name);
            docEduTextView = v.findViewById(R.id.e_pts_doctor_education);
            docAddTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_address);
            docIdTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_id);
            docTimeTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_time);
            docDateTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_doctor_date);
            viewDetailButton = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_pts_viewdetails_button);
            DocProfilePicIV = v.findViewById(R.id.doctor_profile_pic);


            }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListOfDocAppAdapter(List<Datum> listOfDocArrayList,Context context) {
        this.listOfDocArrayList = listOfDocArrayList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListOfDocAppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView ;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(arkaa.health.user.arkaahealthcare.R.layout.edoc_app_list_pts_recyclerview, parent, false);


        return new ListOfDocAppAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ListOfDocAppAdapter.ViewHolder holder, final int position) {

        if(listOfDocArrayList !=null){

            //button logic

            String appointmentConfirmation = ""+listOfDocArrayList.get(position).getIsConfirmed();
            Log.v("ehospatientuid","appointmentConfirmation "+position+" = "+listOfDocArrayList.get(position).getIsConfirmed());

            // holder.viewDetailButton.setVisibility(View.GONE);

            if(!appointmentConfirmation.equals("null")) {
                if (appointmentConfirmation.equals("reschedule")) {
                    holder.viewDetailButton.setText("Appointment Cancelled");
                    holder.viewDetailButton.setEnabled(false);
                    Log.v("ehospatientuid","listOfDocAdap "+position+" =  appointmentConfirmation !=null(appointment cancelled)");
                }else {
                    holder.viewDetailButton.setText("View Details");
                    Log.v("ehospatientuid","listOfDocAdap "+position+" =  else(view details)");
                    }
            } else {
                holder.viewDetailButton.setText("Appointment Pending");
                holder.viewDetailButton.setEnabled(false);
                Log.v("ehospatientuid","listOfDocAdap "+position+" =  Appointment Pending");
            }

            //button logic



            String docName = ""+listOfDocArrayList.get(position).getDoctorName();
            String docEdu = ""+listOfDocArrayList.get(position).getSpecialization();
            final String docId = ""+listOfDocArrayList.get(position).getDoctorId();
            final String docTime = ""+listOfDocArrayList.get(position).getAppointmentTime();
            final String docDate = ""+getDate(listOfDocArrayList.get(position).getAppointmentDate());
            final String appointmentId = ""+listOfDocArrayList.get(position).getAppointmentId();
            final String paymentMode = ""+listOfDocArrayList.get(position).getPaymentMode();
            final Boolean paymentConfirmatation = listOfDocArrayList.get(position).getPaymentconfirmation();
            final String hospitalName = listOfDocArrayList.get(position).getHospitalName();
            final String appointmentType = listOfDocArrayList.get(position).getAppointmentType();
            final String doctorPhoneConsultationNo = listOfDocArrayList.get(position).getDoctorAlternatePhone();
            final Boolean consultationStatus = listOfDocArrayList.get(position).isConsultationStatus();
            final String DocprofilePic = "http://35.163.147.45"+listOfDocArrayList.get(position).getDoctorImageURL();

            Double feesWrtAppointmentType = 0.0 ;
            String totalCostDisplaying = "";


            try {

                Double videoConsultFees = listOfDocArrayList.get(position).getVideoFeesWithGSt();
                Double phoneConsultFees = listOfDocArrayList.get(position).getVoiceFeesWithGSt();
                Double textConsultFees = listOfDocArrayList.get(position).getTextFeesWithGSt();
                Double walkInFees = listOfDocArrayList.get(position).getWalkInFeesWithGSt();


                if (appointmentType.equals("Walk-In")) {
                    feesWrtAppointmentType = walkInFees*100;
                    totalCostDisplaying = ""+walkInFees;
                } else if (appointmentType.equals("Video Consult")) {
                    feesWrtAppointmentType = videoConsultFees*100;
                    totalCostDisplaying = ""+videoConsultFees;

                } else if (appointmentType.equals("Text Consult")) {
                    feesWrtAppointmentType = textConsultFees*100;
                    totalCostDisplaying = ""+textConsultFees;

                } else if (appointmentType.equals("Phone Consult")) {
                    feesWrtAppointmentType = phoneConsultFees*100;
                    totalCostDisplaying = ""+phoneConsultFees;

                }

                //fees calculation

              //  Double TotalCost;
//                try{
//                    totalCostDisplaying = ""+Double.parseDouble(listOfDocArrayList.get(position).getFinalPrice());
//                    TotalCost = Double.parseDouble(listOfDocArrayList.get(position).getFinalPrice())*100;
//                }catch (Exception e){
//                    totalCostDisplaying = "0";
//                    TotalCost = 0.0;
//                }


                //fees calculation



            } catch (Exception e){
                totalCostDisplaying = "0";
                feesWrtAppointmentType = 0.0;
            }


//            Double TotalCost;
//            String totalCostDisplaying;
//            try{
//                totalCostDisplaying = ""+Double.parseDouble(listOfDocArrayList.get(position).getFinalPrice());
//                TotalCost = Double.parseDouble(listOfDocArrayList.get(position).getFinalPrice())*100;
//            }catch (Exception e){
//                totalCostDisplaying = "0";
//                TotalCost = 0.0;
//            }

            final Double doctorFees = feesWrtAppointmentType;
            final String totalCostDisplayIntent = totalCostDisplaying;


            holder.docNameTextView.setText(docName);
            holder.docEduTextView.setText(docEdu);
            holder.docIdTextView.setText(docId);
//            holder.docAddTextView.setText(docAdd);
            holder.docAddTextView.setVisibility(View.GONE);

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
                    Intent intent = new Intent(v.getContext(), DoctorDetail.class);
                    intent.putExtra("APPOINTMENT_DATE",docDate);
                    intent.putExtra("APPOINTMENT_TIME",docTime);
                    intent.putExtra("INDEX",position);
                    intent.putExtra("DOCTOR_ID",docId);
                    intent.putExtra("DOCTOR_FEES",doctorFees);
                    intent.putExtra("APPOINTMENT_ID",appointmentId);
                    intent.putExtra("PAYMENT_MODE",paymentMode);
                    intent.putExtra("PAYMENT_CONFIRMATION",paymentConfirmatation);
                    intent.putExtra("HOSPITAL_NAME",hospitalName);
                    intent.putExtra("TOTAL_COST_DISPLAYING_INTENT",totalCostDisplayIntent);
                    intent.putExtra("APPOINTMENT_TYPE",appointmentType);
                    intent.putExtra("DOCTOR_PHONE_CONSULTATION_NO",doctorPhoneConsultationNo);
                    intent.putExtra("CONSULTATION_STATUS",consultationStatus);
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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public Double getPayableFees(Double TotalCost,String appointmentType) {
        return 0.0;
    }






    public String getDate(String realTime) {

        String str ;
        try {

           // String time = realTime.substring(0, realTime.indexOf("."));

            String time = realTime;
            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            //   String outputPattern = "dd-MMM-yyyy h:mm a";
            String outputPattern = "dd/MM/yyyy";

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




