package arkaa.health.user.arkaahealthcare.Pharmacy.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyDetails;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.Datum;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyDetails;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit.Datum;

public class listOfPharmacyAdapter extends RecyclerView.Adapter<listOfPharmacyAdapter.ViewHolder> implements Filterable {
    private String[] mDataset;
    private int option;
    List<Datum> listOfPharmacyArrayList;
    List<Datum> listOfPharmacyArrayListFull;

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            Log.v("exampleFilter", "Perform Filtering  ");
            Log.v("exampleFilter1", "constraint " + constraint);
            Log.v("exampleFilter", "listOfPharmacyArrayList" + listOfPharmacyArrayList.size());


            List<Datum> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Log.v("exampleFilter1", "value null");
                Log.v("exampleFilter1", "list of specialization full = " + listOfPharmacyArrayListFull.size());


                //  filteredList.addAll(listOfPharmacyArrayList);
                filteredList = listOfPharmacyArrayListFull;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Datum item : listOfPharmacyArrayListFull) {
                    if (item.getPharmacyName().toLowerCase().contains(filterPattern)) {
                        Log.v("exampleFilter1", "not null item = " + item.toString());
                        Log.v("exampleFilter1", "not null item  listOfPharmacyArrayList = " + listOfPharmacyArrayListFull.size());

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
            // listOfAllTests.clear();
            listOfPharmacyArrayList = ((List) results.values);
//            Log.v("exampleFilter","listOfPharmacyArrayList size "+listOfPharmacyArrayList.size());
            notifyDataSetChanged();
        }
    };




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

        public TextView pharmacyNameTextView ;
        public TextView pharmacyAddressTextView ;
        public TextView pharmacyContactNoTextView ;
        public TextView pharmacyIdTextView ;



        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            pharmacyNameTextView = v.findViewById(R.id.lab_name);
            pharmacyAddressTextView = v.findViewById(R.id.lab_address);
            pharmacyContactNoTextView = v.findViewById(R.id.lab_contact_no);
            pharmacyIdTextView = v.findViewById(R.id.lab_id);



        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public listOfPharmacyAdapter(String[] myDataset, int option, List<Datum> listOfPharmacyArrayList) {
        mDataset = myDataset;
        this.option = option;
        this.listOfPharmacyArrayList = listOfPharmacyArrayList ;
        listOfPharmacyArrayListFull = listOfPharmacyArrayList ;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public listOfPharmacyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView ;

        if(option == 1) {

//            itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.medicinesRecyclerView, parent, false);

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lab_items, parent, false);



        } else {
//            itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.order_history_recyclerview, parent, false);

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lab_items, parent, false);


        }

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String pharmacyName = listOfPharmacyArrayList.get(position).getPharmacyName();
        String pharmacyAddress = listOfPharmacyArrayList.get(position).getAddress();
        String pharmacyContactNo = listOfPharmacyArrayList.get(position).getContact();
        final String pharmacyuNIQUEId = listOfPharmacyArrayList.get(position).getUniqueId();

        holder.pharmacyNameTextView.setText(""+pharmacyName);
        holder.pharmacyAddressTextView.setText(""+pharmacyAddress);
        holder.pharmacyContactNoTextView.setText(""+pharmacyContactNo);
        holder.pharmacyIdTextView.setText(""+pharmacyuNIQUEId);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),PharmacyDetails.class);
                intent.putExtra("PHARMACY_ID",pharmacyuNIQUEId);
                Log.v("pharmacy","pharmacy listofLabAdapter= "+pharmacyuNIQUEId);
                (view.getContext()).startActivity(intent);

            }

        });





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {


        return listOfPharmacyArrayList.size();
    }
}
