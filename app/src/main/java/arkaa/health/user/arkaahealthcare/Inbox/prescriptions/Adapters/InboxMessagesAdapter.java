package arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.Activity.ElistOfPts;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Activity.ListOfLabAppointments;
import arkaa.health.user.arkaahealthcare.Inbox.LabReports.Activity.ListOfLabReports;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity.ListOfPharmacyOrders;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Inbox.prescriptions.Activity.ListOfPrescription;

import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Activity.ListOfLabAppointments;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity.ListOfPharmacyOrders;

public class InboxMessagesAdapter extends RecyclerView.Adapter<InboxMessagesAdapter.ViewHolder> {
    private List<String> mDataset;
    private int option;

    // Provide a suitable constructor (depends on the kind of dataset)
    public InboxMessagesAdapter(List<String> myDataset, int option) {
        mDataset = myDataset;
        this.option = option;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public InboxMessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {


        View itemView;

        if (option == 1) {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.inbox_messages_recyclerview, parent, false);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.inbox_messages_recyclerview, parent, false);

        }

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.inboxMessagesTextView.setText(mDataset.get(position));

        if (position == 0) {
            Log.v("checkInbx","0(1) pres");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("checkInbx","0(2) pres");

//                    Intent intent = new Intent(v.getContext(), ElistOfPrescription.class);
//                    v.getContext().startActivity(intent);

                    Intent intent = new Intent(v.getContext(), ElistOfPts.class);
                    v.getContext().startActivity(intent);

                }
            });

        } else if (position == 1) {
            Log.v("checkInbx","1(1) pres");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ListOfPrescription.class);
                    Log.v("checkInbx","1(2) pres");
                    v.getContext().startActivity(intent);
                }
            });

        } else if (position == 2) {
            Log.v("checkInbx","2(1) lab");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ListOfLabAppointments.class);
                    Log.v("checkInbx","2(2) lab");

                    v.getContext().startActivity(intent);
                }
            });

        } else if (position == 3) {

            Log.v("checkInbx","3(1) phar");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ListOfPharmacyOrders.class);
                    Log.v("checkInbx","3(2) phar");
                    v.getContext().startActivity(intent);
                }
            });

        } else if (position == 4) {

            Log.v("checkInbx","3(1) phar");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ListOfLabReports.class);
                    Log.v("checkInbx","3(2) phar");
                    v.getContext().startActivity(intent);
                }
            });

        } else {


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
            inboxMessagesTextView = v.findViewById(R.id.prescription);
        }
    }
}

