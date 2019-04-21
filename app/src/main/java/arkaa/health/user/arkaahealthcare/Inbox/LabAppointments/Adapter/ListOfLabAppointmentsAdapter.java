package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Activity.LabAppointmentDetails;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Activity.ViewLabQuotation;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Test;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Datum;
import arkaa.health.user.arkaahealthcare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Activity.LabAppointmentDetails;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Test;

public class ListOfLabAppointmentsAdapter extends RecyclerView.Adapter<ListOfLabAppointmentsAdapter.ViewHolder> {
    private List<Datum> listOfLabAppointments;
    private Datum labAppointmentDetailObject;


    // Provide a suitable constructor (depends on the kind of dataset)
    public ListOfLabAppointmentsAdapter(List<Datum> listOfLabAppointments) {
        this.listOfLabAppointments = listOfLabAppointments;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListOfLabAppointmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lab_appointments_recyclerview, parent, false);

        return new ListOfLabAppointmentsAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ListOfLabAppointmentsAdapter.ViewHolder holder, final int position) {


        if(listOfLabAppointments != null) {
            String labName = listOfLabAppointments.get(position).getLabName();
            String consultationType = listOfLabAppointments.get(position).getLabBookingType();
            String appointmentStatus = listOfLabAppointments.get(position).getAppointmentStatus();
            String date = getDate(listOfLabAppointments.get(position).getAppointmentDate());
            String time = getTime(listOfLabAppointments.get(position).getProbableStartTime());

            int bookingTypeId = listOfLabAppointments.get(position).getBookingTypeId();
            int appointmentStatusId = listOfLabAppointments.get(position).getAppointmentStatusId();
            List<Test> listOfTestsArrayList  = listOfLabAppointments.get(position).getTests();
            final int orderId = listOfLabAppointments.get(position).getId();



            holder.labNameTextView.setText(""+labName);
            holder.labConsultationTypeTextView.setText(""+consultationType);
            holder.labAppointmentStatusTextView.setText(""+appointmentStatus);
            holder.labDateTextView.setText(""+date);
            holder.labTimeTextView.setText(""+time);


            holder.viewQuotationButton.setVisibility(View.GONE);
            holder.viewDetailsButton.setVisibility(View.GONE);

            if ( (appointmentStatusId == 0) && (bookingTypeId == 2) && (listOfTestsArrayList != null)) {
                holder.viewQuotationButton.setVisibility(View.VISIBLE);
                holder.viewDetailsButton.setVisibility(View.GONE);
            } else if((appointmentStatusId == 0) && (bookingTypeId == 2) && (listOfTestsArrayList == null)){

            } else {


                holder.viewQuotationButton.setVisibility(View.GONE);
                holder.viewDetailsButton.setVisibility(View.VISIBLE);
            }



            holder.viewDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), LabAppointmentDetails.class);
                    labAppointmentDetailObject = listOfLabAppointments.get(position);
                    intent.putExtra("LAB_APPOINTMENT_DETAIL_OBJECT",labAppointmentDetailObject);
                    (view.getContext()).startActivity(intent);

                }

            });

            holder.viewQuotationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), ViewLabQuotation.class);
                    labAppointmentDetailObject = listOfLabAppointments.get(position);
                    intent.putExtra("ORDER_ID",orderId);
                    (view.getContext()).startActivity(intent);

                }

            });










        }








    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfLabAppointments.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView labNameTextView;
        public TextView labConsultationTypeTextView;
        public TextView labAppointmentStatusTextView;
        public TextView labDateTextView;
        public TextView labTimeTextView;

        public Button viewDetailsButton;
        public Button viewQuotationButton;


        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            labNameTextView = v.findViewById(R.id.lab_name);
            labConsultationTypeTextView = v.findViewById(R.id.lab_consultation_type);
            labAppointmentStatusTextView = v.findViewById(R.id.lab_appointment_status);
            labDateTextView = v.findViewById(R.id.lab_date);
            labTimeTextView = v.findViewById(R.id.lab_time);

            viewDetailsButton = v.findViewById(R.id.lab_view_details);
            viewQuotationButton = v.findViewById(R.id.lab_view_quotation);


        }
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
