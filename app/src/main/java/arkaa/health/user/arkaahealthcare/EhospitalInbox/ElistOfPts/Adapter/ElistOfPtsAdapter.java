package arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.Activity.EinboxMessages;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.ModalClass.Datum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ElistOfPtsAdapter extends RecyclerView.Adapter<ElistOfPtsAdapter.ViewHolder> {


    private List<Datum> listOfPts;


    public ElistOfPtsAdapter(List<Datum> listOfDoctorsArray) {
        this.listOfPts = listOfDoctorsArray;

    }

    @Override
    public ElistOfPtsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(arkaa.health.user.arkaahealthcare.R.layout.list_of_pts_reccyclerview, parent, false);

        return new ElistOfPtsAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ElistOfPtsAdapter.ViewHolder holder, final int position) {


        if (listOfPts != null) {




            final String hospitalId = listOfPts.get(position).getHospitalId();
            final String transactionId = listOfPts.get(position).getPtsTransactionId();
            final String ptsId = listOfPts.get(position).getPtsId();
            String date = ": "+getDate(listOfPts.get(position).getDate());
            String time = ": "+getTime(listOfPts.get(position).getDate());
            String eHospitalName = ""+listOfPts.get(position).getHospitalName();




            holder.hospitalIdTv.setText(": "+hospitalId);
            holder.transactionIdTv.setText(": "+transactionId);
            holder.dateTv.setText(date);
            holder.timeTv.setText(time);
            holder.eHospitalNameTv.setText(": "+eHospitalName);




            if(ptsId != null && transactionId != null) {


                holder.viewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if ((!ptsId.equals("") && ptsId != null) && (!transactionId.equals("") && transactionId != null)) {

                            Log.v("ElistOfPtsAdapterIDS","hospitalID"+hospitalId);
                            Log.v("ElistOfPtsAdapterIDS","transactionId"+transactionId);
                            Log.v("ElistOfPtsAdapterIDS","ptsId"+ptsId);


                            Intent intent = new Intent(view.getContext(), EinboxMessages.class);
                            intent.putExtra("HOSPITAL_ID", hospitalId);
                            intent.putExtra("TRANSACTION_ID", transactionId);
                            intent.putExtra("PTS_ID", ptsId);
                            view.getContext().startActivity(intent);


                        }


                    }
                });
            }

        }


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfPts.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
//        public View mTextView;
        public Button viewButton;


        public TextView hospitalIdTv;
        public TextView transactionIdTv;
        public TextView dateTv;
        public TextView timeTv;
        public TextView eHospitalNameTv;



        public ViewHolder(View v) {
            super(v);

            viewButton = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.view_button);


            hospitalIdTv = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_hospital_id);
            transactionIdTv = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_transaction_id);
            dateTv = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_date);
            timeTv = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_time);
            eHospitalNameTv = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.e_hospital_name);


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

