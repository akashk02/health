package arkaa.health.user.arkaahealthcare.Pharmacy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.view_all_medicines;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines.Datum;

public class listOfMedicinesAdapter extends RecyclerView.Adapter<listOfMedicinesAdapter.ViewHolder> implements Filterable {
    private List<Datum> listOfMedicines;
    private List<Datum> listOfMedicineFull;

    private int noOfItemsGlobal = 0;
    private Context context;
    private String type;
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            Log.v("exampleFilter", "Perform Filtering  ");
            Log.v("exampleFilter1", "constraint " + constraint);
            Log.v("exampleFilter", "listOfMedicines" + listOfMedicines.size());


            List<Datum> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Log.v("exampleFilter1", "value null");
                Log.v("exampleFilter1", "list of specialization full = " + listOfMedicineFull.size());


                //  filteredList.addAll(listOfSpecializationFull);
                filteredList = listOfMedicineFull;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Datum item : listOfMedicineFull) {
                    if (item.getMedName().toLowerCase().contains(filterPattern)) {
                        Log.v("exampleFilter1", "not null item = " + item.toString());
                        Log.v("exampleFilter1", "not null item  listOfMedicines = " + listOfMedicineFull.size());

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
            // listOfMedicines.clear();
            listOfMedicines = ((List) results.values);
//            Log.v("exampleFilter","listOfMedicines size "+listOfMedicines.size());
            notifyDataSetChanged();
        }
    };

    // Provide a suitable constructor (depends on the kind of dataset)
    public listOfMedicinesAdapter(List<Datum> listOfMedicines, Context context, String type) {
        this.listOfMedicines = listOfMedicines;
        listOfMedicineFull = listOfMedicines;

        this.context = context;
        this.type = type;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public listOfMedicinesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {


        View itemView;


        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicines_recyclerview, parent, false);


        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (listOfMedicines != null) {


            final int medicineId = listOfMedicines.get(position).getId();
            final String medicineName = listOfMedicines.get(position).getMedName();
            String medicineDose = " " + listOfMedicines.get(position).getMedDose();
            if (TextUtils.isEmpty(medicineDose)) {
                medicineDose = "";
            }
            final String medicineQuantity = listOfMedicines.get(position).getMedQuantity().toString();
            final String medicineType = listOfMedicines.get(position).getMedType();
            String medicineManufacturerName = listOfMedicines.get(position).getMedManufacturerName();
            final Double medicinePrice = listOfMedicines.get(position).getMedCost();
            Log.v("viewAllMEdi","medicinePrice adapter = "+medicinePrice);
            int medicineCateoryId = listOfMedicines.get(position).getCategoryId();
            boolean isPrescriptionNeeded = (medicineCateoryId == 1);

            final String medicineFullName = medicineName + medicineDose + " " + medicineType + " " + medicineQuantity;

            holder.medicineNameTextView.setText("" + medicineName + "" + medicineDose + " " + medicineType + " " + medicineQuantity);
            holder.medicineManufacturerNameTextView.setText("" + medicineManufacturerName);
            holder.medicineQuantityTextView.setText("" + medicineQuantity + " " + medicineType);
            holder.medicinePriceTextView.setText("RS " + medicinePrice);

            holder.decrementButton.setVisibility(View.GONE);
            holder.incrementButton.setVisibility(View.GONE);
            holder.countTextView.setVisibility(View.GONE);

            if (type.equals("cart")) {
                holder.addToCartTextView.setVisibility(View.GONE);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                Intent intent = new Intent(view.getContext(),PharmacyDetails.class);
//                intent.putExtra("PHARMACY_ID",pharmacyId);
//                Log.v("pharmacy","pharmacy listofLabAdapter= "+pharmacyId);
//                (view.getContext()).startActivity(intent);

                }

            });

            holder.incrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  noOfItemsGlobal++;
                    holder.noOfItemsLocal++;
                    Log.v("increment", "increment global = " + noOfItemsGlobal);
                    Log.v("increment", "increment local = " + holder.noOfItemsLocal);
                    holder.countTextView.setText("" + Integer.toString(holder.noOfItemsLocal));


                }
            });

            holder.decrementButton.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
//                if(noOfItemsGlobal != 0){
//                    noOfItemsGlobal -- ;
//                }
                  holder.noOfItemsLocal--;
                  if (holder.noOfItemsLocal == 0) {
                      holder.addToCartTextView.setVisibility(View.VISIBLE);
                      holder.decrementButton.setVisibility(View.GONE);
                      holder.incrementButton.setVisibility(View.GONE);
                      holder.countTextView.setVisibility(View.GONE);
                  } else {

    //                if(holder.noOfItemsLocal != 0 ){
    //                    holder.noOfItemsLocal -- ;
    //                }

                      Log.v("increment", "decrement global = " + noOfItemsGlobal);
                      Log.v("increment", "decrement local = " + holder.noOfItemsLocal);
                      holder.countTextView.setText("" + Integer.toString(holder.noOfItemsLocal));
                  }

              }
          }


            );

            holder.addToCartTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                noOfItemsGlobal++;
//                holder.noOfItemsLocal ++;

//                holder.incrementButton.setVisibility(View.VISIBLE);
//                holder.decrementButton.setVisibility(View.VISIBLE);
//                holder.countTextView.setVisibility(View.VISIBLE);
//
//                holder.countTextView.setText(Integer.toString(holder.noOfItemsLocal));
//
//
//                holder.addToCartTextView.setVisibility(View.GONE);


                    if (context instanceof view_all_medicines) {
//                    ((view_all_medicines)context).updateTestCartView(Integer.parseInt(testId),testName,testPrice,position);
                        ((view_all_medicines) context).updateMedicineCartView(medicineId, medicineFullName, medicinePrice, position);

                    }

                }
            });


        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {


        return listOfMedicines.size();
    }

    @Override
    public Filter getFilter() {
        Log.v("listSpecialization", "filter = " + exampleFilter.toString());

        return exampleFilter;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView medicineNameTextView;
        public TextView medicineManufacturerNameTextView;
        public TextView medicineQuantityTextView;
        public TextView medicinePriceTextView;
        public TextView countTextView;
        public TextView addToCartTextView;
        public Button incrementButton;
        public Button decrementButton;


        public int noOfItemsLocal;


        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            medicineNameTextView = v.findViewById(R.id.medicine_name);
            medicineManufacturerNameTextView = v.findViewById(R.id.medicine_manufacturer_name);
            medicineQuantityTextView = v.findViewById(R.id.medicine_quantity);
            medicinePriceTextView = v.findViewById(R.id.medicine_price);
            countTextView = v.findViewById(R.id.count_textview);
            addToCartTextView = v.findViewById(R.id.add_to_cart);
            incrementButton = v.findViewById(R.id.increment_button);
            decrementButton = v.findViewById(R.id.decrement_button);

            noOfItemsLocal = 0;


        }
    }


}
