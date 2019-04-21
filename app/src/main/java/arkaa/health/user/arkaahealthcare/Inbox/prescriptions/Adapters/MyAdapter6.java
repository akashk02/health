package arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Lab.Activity.lab_details;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.DisplayPrescription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses.Datum;

public class MyAdapter6 extends RecyclerView.Adapter<MyAdapter6.ViewHolder> {
    private String[] mDataset;
    private int option;
    private List<Datum> listOfPrescriptions ;
    private String activityType;
    private Activity context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView doctorNameTextView ;
        public TextView doctorDescriptionTextView ;
        public TextView dateTextView ;
        public TextView timeTextView ;
        public Button viewButton;
        public Button shareButton ;


        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            doctorNameTextView = v.findViewById(R.id.doctor_name);
            doctorDescriptionTextView = v.findViewById(R.id.doctor_description);
            dateTextView = v.findViewById(R.id.doctor_date);
            timeTextView = v.findViewById(R.id.doctor_time);
            viewButton = v.findViewById(R.id.view_button);
            shareButton = v.findViewById(R.id.share_button);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter6(String[] myDataset, int option,List<Datum> listOfPrescriptions, String activityType,Activity context) {
        mDataset = myDataset;
        this.option = option;
        this.listOfPrescriptions = listOfPrescriptions ;
        this.activityType = activityType;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter6.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView ;

        if(option == 1) {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.inbox_recyclerview, parent, false);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.inbox_recyclerview, parent, false);

        }

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        if(listOfPrescriptions != null){

            holder.doctorNameTextView.setText(listOfPrescriptions.get(position).getDoctorName());
            holder.doctorDescriptionTextView.setText(listOfPrescriptions.get(position).getDescription());

           String dateTime = listOfPrescriptions.get(position).getCreatedOn();
            holder.dateTextView.setText(getDate(dateTime));
            holder.timeTextView.setText(getTime(dateTime));


            holder.viewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.v("touchListner","setOnClickListener");
                    Intent intent = new Intent(v.getContext(),DisplayPrescription.class);
                    intent.putExtra("PRESCRIPTION_URL",listOfPrescriptions.get(position).getPrescriptionPath());
                    v.getContext().startActivity(intent);





                }
            });

            holder.shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),lab_details.class);
                    intent.putExtra("PRESCRIPTION_ID",listOfPrescriptions.get(position).getId());
                    Log.v("prescriptionId","prescriptionId adapter "+listOfPrescriptions.get(position).getId());
                    context.setResult(Activity.RESULT_OK,intent);
                    context.finish();



                }
            });


            if(activityType.equals("LAB")){
                holder.viewButton.setVisibility(View.GONE);
                Log.v("buttonView"," holder.viewButton.setVisibility(View.GONE)");
            } else {
                holder.shareButton.setVisibility(View.GONE);
                Log.v("buttonView"," holder.shareButton.setVisibility(View.GONE))");

            }

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

