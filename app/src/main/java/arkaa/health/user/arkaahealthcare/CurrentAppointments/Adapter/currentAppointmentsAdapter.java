package arkaa.health.user.arkaahealthcare.CurrentAppointments.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities.ShareReports;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities.currentAppointment;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments.Datum;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Activity.DoctorDetail;
import arkaa.health.user.arkaahealthcare.FirebaseChat.chatMainActivity;
import arkaa.health.user.arkaahealthcare.FirebaseChat.chatMainActivityBeta;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.SinchVideoCall.MainActivityVideo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities.ShareReports;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities.currentAppointment;
import arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments.Datum;
import arkaa.health.user.arkaahealthcare.FirebaseChat.chatMainActivityBeta;

public class currentAppointmentsAdapter extends RecyclerView.Adapter<currentAppointmentsAdapter.ViewHolder> {

    private List<Datum> listOfDocArrayList;
    private Activity context;
    private int REPORT_RESULT = 5;

    // Provide a suitable constructor (depends on the kind of dataset)
    public currentAppointmentsAdapter(List<Datum> listOfDocArrayList, Activity context ) {
        this.listOfDocArrayList = listOfDocArrayList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public currentAppointmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {

        View itemView;


        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_appointment_recyclerview, parent, false);


        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (listOfDocArrayList != null) {


            String doctorName = "" + listOfDocArrayList.get(position).getDoctorName();
            String doctorId = "ID :" + listOfDocArrayList.get(position).getDoctorId();
            String doctorEdu = "";
            if (listOfDocArrayList.get(position).getDegree() != null) {
                doctorEdu = "" + listOfDocArrayList.get(position).getDegree().get(0).getDegree();
            }
            String appointmentDate = "" + getDate(listOfDocArrayList.get(position).getAppointmentDate());
            String appointmentTime = "" + getTime(listOfDocArrayList.get(position).getProbableStartTime());
            String doctorAddress = "";
            final int appointmentId = listOfDocArrayList.get(position).getId();
            if (listOfDocArrayList.get(position).getClinicDetails() != null) {
                doctorAddress = "" + listOfDocArrayList.get(position).getClinicDetails().get(0).getAddress();

            }

            final String orderId = listOfDocArrayList.get(position).getOrderId();
            int payTypeId = listOfDocArrayList.get(position).getPaymentTypeId();
            //remove this value
//            int paymentStatusId = 1;
//            String appointmentStatus = "confirmed" ;

            //remove this value

            int paymentStatusId = listOfDocArrayList.get(position).getPaymentStatusId();
            String appointmentStatus = listOfDocArrayList.get(position).getAppointmentStatus();



            Log.v("paymentStatus", "position = " + position + " paymentTypeId = " + payTypeId + " payment status id = " + paymentStatusId + " appointmentStatus = " + appointmentStatus);
            String consultationType = listOfDocArrayList.get(position).getConsultationType();
            boolean videoConsultation = false ;
            boolean textConsultation = false;
            boolean phoneConsultation = false;
            if(consultationType.equals("Video")){
                videoConsultation = true;
            } else if(consultationType.equals("Phone")){
                phoneConsultation = true;
            } else if(consultationType.equals("Text")){
                textConsultation = true;
            }



            final String doctorUniqueId = listOfDocArrayList.get(position).getDoctorId();
            String DocprofilePic = listOfDocArrayList.get(position).getProfilePicPath();



            //razorpay

            Double TotalCost;
            try{
//                TotalCost = listOfDocArrayList.get(position).getClinicDetails().get(0).getFirstConsultationFee()*100;
                TotalCost = listOfDocArrayList.get(position).getTotalFees()*100;

            }catch (Exception e){
                TotalCost = 0.0;
            }

            final Double totalCost = TotalCost;
            Log.v("currenetApp","currentAppAdapter postion ="+position+" totalFees = "+totalCost);


            //razorpay

            holder.payNowButton.setVisibility(View.GONE);
            if (appointmentStatus.equals("confirmed")) {

                //if


                if (payTypeId == 1) {
                    holder.payNowButton.setVisibility(View.VISIBLE);
                    holder.payNowButton.setEnabled(false);
                    holder.payNowButton.setText("pay on visit");
                } else if (payTypeId == 2) {

                    if (paymentStatusId == 1) {
                        holder.payNowButton.setVisibility(View.VISIBLE);
                        holder.payNowButton.setEnabled(false);
                        holder.payNowButton.setText("Already Paid");
                        Log.v("alreadyPaid", "position = " + position + " paymentTypeId = " + payTypeId + " payment status id = " + paymentStatusId + " appointmentStatus = " + appointmentStatus);

                        if(videoConsultation){
                            holder.payNowButton.setVisibility(View.VISIBLE);
                            holder.payNowButton.setEnabled(true);
                            holder.payNowButton.setText("Video Consultation");

                            if(EhosCheckAppTimingGlobal(appointmentDate,appointmentTime)) {
                                holder.payNowButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(v.getContext(), MainActivityVideo.class);
                                        intent.putExtra("DOCTOR_UNIQUE_ID", doctorUniqueId);
                                        v.getContext().startActivity(intent);
                                    }
                                });
                            }else {
                                Toast.makeText(context, "Video Consultation will be enabled on " + appointmentDate + " at " + appointmentTime, Toast.LENGTH_LONG).show();

                            }

                        } else if(phoneConsultation){
                            holder.payNowButton.setVisibility(View.VISIBLE);
                            holder.payNowButton.setEnabled(true);
                            holder.payNowButton.setText("Phone Consultation");

                            if(EhosCheckAppTimingGlobal(appointmentDate,appointmentTime)) {
                                holder.payNowButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                            }else {
                                Toast.makeText(context, "Phone Consultation will be enabled on " + appointmentDate + " at " + appointmentTime, Toast.LENGTH_LONG).show();

                            }

                        } else if(textConsultation){
                            holder.payNowButton.setVisibility(View.VISIBLE);
                            holder.payNowButton.setEnabled(true);
                            holder.payNowButton.setText("Text Consultation");

                            if(EhosCheckAppTimingGlobal(appointmentDate,appointmentTime)) {
                                holder.payNowButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(v.getContext(), chatMainActivityBeta.class);
                                        intent.putExtra("DOCTOR_UNIQUE_ID", doctorUniqueId);
                                        intent.putExtra("FIREBASE_CHILD_NAME", "arkaaglobal");
                                        v.getContext().startActivity(intent);

                                    }
                                });
                            }else {
                                Toast.makeText(context, "Text Consultation will be enabled on " + appointmentDate + " at " + appointmentTime, Toast.LENGTH_LONG).show();

                            }

                        }else {
                            holder.payNowButton.setVisibility(View.VISIBLE);
                            holder.payNowButton.setEnabled(false);
                            holder.payNowButton.setText("Already Paid");
                        }


                    } else if (paymentStatusId == 2) {
                        holder.payNowButton.setVisibility(View.VISIBLE);
                        holder.payNowButton.setEnabled(false);
                        holder.payNowButton.setText("Cancelled");
                    } else if (paymentStatusId == 3) {
                        holder.payNowButton.setVisibility(View.VISIBLE);
                        holder.payNowButton.setText("online payment");
                        holder.payNowButton.setEnabled(true);
                        if(orderId != null) {
                            if (!orderId.equals("")){
                                holder.payNowButton.setOnClickListener(new View.OnClickListener() {


                                    @Override
                                    public void onClick(View v) {

                                        if (context instanceof currentAppointment) {
                                            ((currentAppointment) context).paymentFromAdapter(totalCost, position, orderId);
                                        }
                                    }
                                });
                        }
                        }
                    }

                }

                //else

            } else {
                holder.payNowButton.setVisibility(View.VISIBLE);
                holder.payNowButton.setText(appointmentStatus);
                holder.payNowButton.setEnabled(false);

            }


            holder.doctorNameTv.setText(doctorName);
            holder.doctorAddressTv.setText(doctorAddress);
            holder.doctorEduTv.setText(doctorEdu);
            holder.doctorIdTv.setText(doctorId);
            holder.doctorDateTv.setText(appointmentDate);
            holder.doctorTimeTv.setText(appointmentTime);

            try {
                Glide.with(context).load(DocprofilePic).into(holder.DocProfilePicIV);
            } catch (Exception e){
                Log.v("tryCatch","ListOfDocAdapter glide Exeption e = "+e.getMessage());
            }

            holder.shareReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), ShareReports.class);
                    intent.putExtra("APPOINTMENT_ID", appointmentId);
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

        String str;
        try {

            String time = realTime.substring(0, realTime.indexOf("."));

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

        } catch (Exception e) {

            str = realTime;

        }


        Log.v("new_time", "str = " + str);
        return str;


    }

    public String getTime(String realTime) {

        String str;
        try {

            String time = realTime;

//            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            String inputPattern = "kk:mm:ss";

            String outputPattern = "hh:mm a";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
                Log.v("new_time1", "try(time)date = " + date + "str = " + str);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("new_time1", "|(time)exception = " + e.getMessage());
                Log.v("new_time1", "|(time)exception =" + e.getStackTrace());
                Log.v("new_time1", "|(time)exception =" + e.getLocalizedMessage());


            }

        } catch (Exception e) {
            Log.v("new_time1", "|(time)exception main =" + e.getMessage());
            Log.v("new_time1", "|(time)exception main =" + e.getStackTrace());
            Log.v("new_time1", "|(time)exception main =" + e.getLocalizedMessage());



            str = realTime;

        }


        Log.v("new_time1", "(time)str = " + str);
        return str;


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView doctorNameTv;
        public TextView doctorEduTv;
        public TextView doctorIdTv;
        public TextView doctorAddressTv;
        public TextView doctorTimeTv;
        public TextView doctorDateTv;
        public Button shareReportButton;
        public Button payNowButton;
        public ImageView DocProfilePicIV;



        public ViewHolder(View v) {
            super(v);
            doctorNameTv = v.findViewById(R.id.doctor_name);
            doctorEduTv = v.findViewById(R.id.doctor_edu);
            doctorIdTv = v.findViewById(R.id.doctor_id);
            doctorAddressTv = v.findViewById(R.id.doctor_address);
            doctorTimeTv = v.findViewById(R.id.app_time);
            doctorDateTv = v.findViewById(R.id.app_date);
            shareReportButton = v.findViewById(R.id.share_reports_button);
            payNowButton = v.findViewById(R.id.pay_now);
            DocProfilePicIV = v.findViewById(R.id.doctor_profile_pic);



        }
    }

    public boolean EhosCheckAppTimingGlobal(String date, String time) {

        String appointmentTime;
        String formattedDateTime;
        long appDateTime;
        long currDateTime;

        try {

            appointmentTime = time;
            formattedDateTime = date + " " + appointmentTime;

            appDateTime = getFormattedDateTime(formattedDateTime);
            currDateTime = getFormattedCurrentDateTime();

            Log.v("value123", "appointmentTime = " + appointmentTime);
            Log.v("value123", "formattedDateTime = " + formattedDateTime);
            Log.v("value123", "appDateTime = " + appDateTime);
            Log.v("value123", "currDateTime = " + currDateTime);


            if (currDateTime >= appDateTime) {
                Log.v("value123ch", "currDateTime >= appDateTime   value(true)");

                return true;
            } else if (currDateTime < appDateTime) {
                Log.v("value123ch", "currDateTime < appDateTime   value(false)");

                return false;
            } else {
                Log.v("value123ch", "else  value(true)");

                return true;
            }

        } catch (Exception e){
            Log.v("tryCatch","EhosCheckAppTimingGlobal Exception e ="+e.getStackTrace());
            Log.v("tryCatch","EhosCheckAppTimingGlobal Exception e ="+e.getMessage());
            return  true;
        }


    }// for current appointments

    public long getFormattedDateTime(String dateTime) {


        Date dateObj = null;
        SimpleDateFormat DATEformat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        long timeInMs;


        try {
            dateObj = DATEformat.parse(dateTime);
            timeInMs = dateObj.getTime();
            Log.v("getFormattedDateTime", "getFormattedDateTime with argument =" + timeInMs);
            return timeInMs;
        } catch (Exception e) {
            Log.v("getFormattedDateTime", "getFormattedDateTime with argument Exception e =" + e.getMessage());

        }
        return 0;
    }

    public long getFormattedCurrentDateTime() {
        Calendar rightNow = Calendar.getInstance();
        Date currentTime = rightNow.getTime();

        long currentTimeConst = currentTime.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String changedTime = dateFormat.format(currentTime);

        Log.v("getFormattedDateTime", "getFormattedDateTime =" + changedTime);

        return currentTimeConst;
    }




}
