package arkaa.health.user.arkaahealthcare.Lab.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Lab.Activity.lab_details;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.Datum;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Lab.Activity.lab_details;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab.Datum;

public class listOfLabAdapter extends RecyclerView.Adapter<listOfLabAdapter.ViewHolder> implements Filterable {
    private String[] mDataset;
    private List<Datum> listofLabs ;
    private List<Datum> listofLabsFull ;

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            Log.v("exampleFilter", "Perform Filtering  ");
            Log.v("exampleFilter1", "constraint " + constraint);
            Log.v("exampleFilter", "listofLabs" + listofLabs.size());


            List<Datum> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Log.v("exampleFilter1", "value null");
                Log.v("exampleFilter1", "list of specialization full = " + listofLabsFull.size());


                //  filteredList.addAll(listofLabsFull);
                filteredList = listofLabsFull;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Datum item : listofLabsFull) {
                    if (item.getLabName().toLowerCase().contains(filterPattern)) {
                        Log.v("exampleFilter1", "not null item = " + item.toString());
                        Log.v("exampleFilter1", "not null item  listOfAllTests = " + listofLabsFull.size());

                        filteredList.add(item);
                    }

                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // listofLabs.clear();
            listofLabs = ((List) results.values);
//            Log.v("exampleFilter","listofLabs size "+listofLabs.size());
            notifyDataSetChanged();
        }
    };




    // Provide a suitable constructor (depends on the kind of dataset)
    public listOfLabAdapter(List<Datum> listofLabs) {
        this.listofLabs = listofLabs ;
        listofLabsFull = listofLabs;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView labNameTextView ;
        public TextView labAddressTextView ;
        public TextView labContactNoTextView ;
        public TextView labIdTextView ;
        public Button viewProfileButton;






        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            labNameTextView = v.findViewById(R.id.lab_name);
            labAddressTextView = v.findViewById(R.id.lab_address);
            labContactNoTextView = v.findViewById(R.id.lab_contact_no);
            labIdTextView = v.findViewById(R.id.lab_id);
            viewProfileButton = v.findViewById(R.id.view_profile_button);

            }
    }









    // Create new views (invoked by the layout manager)
    @Override
    public listOfLabAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lab_items_beta, parent, false);

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);

        String labName = listofLabs.get(position).getLabName();
        String labAddress = listofLabs.get(position).getAddress();
        String labContactNo = listofLabs.get(position).getContact();
        final String labId = listofLabs.get(position).getUniqueId();

        holder.labNameTextView.setText(""+labName);
        holder.labAddressTextView.setText(""+labAddress);
        holder.labContactNoTextView.setText(""+labContactNo);
        holder.labIdTextView.setText(""+labId);



//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(view.getContext(),lab_details.class);
//                intent.putExtra("LAB_ID",labId);
//                Log.v("lab_details","labId listofLabAdapter= "+labId);
//                (view.getContext()).startActivity(intent);
//
//            }
//
//        });

        holder.viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),lab_details.class);
                intent.putExtra("LAB_ID",labId);
                Log.v("lab_details","labId listofLabAdapter= "+labId);
                (view.getContext()).startActivity(intent);

            }

        });








    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listofLabs.size();
    }


}
