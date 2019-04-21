package arkaa.health.user.arkaahealthcare.EhospitalInbox.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.Activities.ElistOfPrescription;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.Activity.Elaborders;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Activity.ListOfDocAppointments;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.Activities.EdoctorAppointmentListPts;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.Activities.EpharmacyQuote;

import java.util.List;

public class EinboxMessageAdapter extends RecyclerView.Adapter<EinboxMessageAdapter.ViewHolder> {
    private List<String> mDataset;
    private int option;

    private String hospitalId;
    private String transactionId;
    private String ptsId ;

    // Provide a suitable constructor (depends on the kind of dataset)

    public EinboxMessageAdapter(){};

    public EinboxMessageAdapter(List<String> myDataset, int option,String hospitalId,String transactionId,String ptsId) {
        mDataset = myDataset;
        this.option = option;
        this.ptsId = ptsId;
        this.transactionId = transactionId;
        this.hospitalId = hospitalId;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EinboxMessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        if (option == 1) {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(arkaa.health.user.arkaahealthcare.R.layout.inbox_messages_recyclerview, parent, false);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(arkaa.health.user.arkaahealthcare.R.layout.inbox_messages_recyclerview, parent, false);

        }

        return new EinboxMessageAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EinboxMessageAdapter.ViewHolder holder, int position) {


        holder.inboxMessagesTextView.setText(mDataset.get(position));

        if(ptsId != null && transactionId != null) {

            Log.v("EinboxMessageAdapterIDS","hospitalID"+hospitalId);
            Log.v("EinboxMessageAdapterIDS","transactionId"+transactionId);
            Log.v("EinboxMessageAdapterIDS","ptsId"+ptsId);


            if(position == 0){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("checkInbx", "0 EdoctorAppointmentListPts");
                        Intent intent = new Intent(v.getContext(), EdoctorAppointmentListPts.class);
                        intent.putExtra("HOSPITAL_ID", hospitalId);
                        intent.putExtra("TRANSACTION_ID", transactionId);
                        intent.putExtra("PTS_ID", ptsId);
                        v.getContext().startActivity(intent);
                    }
                });
            }else if (position == 1) {
                Log.v("checkInbx", "0(1) pres");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Elaborders.class);
                        intent.putExtra("HOSPITAL_ID", hospitalId);
                        intent.putExtra("TRANSACTION_ID", transactionId);
                        intent.putExtra("PTS_ID", ptsId);
                        Log.v("checkInbx", "(1) lab");

                        v.getContext().startActivity(intent);
                    }
                });

            } else if (position == 2) {

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), EpharmacyQuote.class);
                        intent.putExtra("HOSPITAL_ID", hospitalId);
                        intent.putExtra("TRANSACTION_ID", transactionId);
                        intent.putExtra("PTS_ID", ptsId);
                        Log.v("checkInbx", "(2) phar");
                        v.getContext().startActivity(intent);
                    }
                });

            }



        }




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;
        public TextView inboxMessagesTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = v;
            inboxMessagesTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.prescription);
        }
    }
}


