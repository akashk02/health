package arkaa.health.user.arkaahealthcare.Doctor.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Doctor.Activity.ListOfDoctors;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.Datum;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfSpecialization.Datum;

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationAdapter.ViewHolder> implements Filterable {
    private String[] mDataset;
    private int option;
    private List<Datum> listOfSpecialization ;
    private List<Datum> listOfSpecializationFull ;




    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mTextView;
        public TextView specialization ;
        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            specialization = v.findViewById(R.id.specialization);

        }
    }

    public SpecializationAdapter(String[] myDataset, int option,List<Datum> specialities) {
        listOfSpecialization = specialities ;
        listOfSpecializationFull = specialities;
        mDataset = myDataset;
        this.option = option;
    }

    @Override
    public SpecializationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View itemView ;

        if(option == 1) {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_of_doctors_recyclerview, parent, false);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_of_doctors_recyclerview, parent, false);

        }

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(listOfSpecialization != null) {

            String specialization = listOfSpecialization.get(position).getSpecialization();
            Log.v("specialization111","specialization = "+specialization);
            final int specializationID = listOfSpecialization.get(position).getId();

            holder.specialization.setText(""+specialization);
            Log.v("specialization111","specialization textview ="+holder.specialization.getText().toString());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),ListOfDoctors.class);
                intent.putExtra("SPECIALIZATION_ID",specializationID);
                (view.getContext()).startActivity(intent);

//                Toast.makeText(view.getContext(),".",Toast.LENGTH_SHORT).show();
            }

        });

            }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfSpecialization.size();
    }

    @Override
    public Filter getFilter() {
        Log.v("listSpecialization","filter = "+exampleFilter.toString());

        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            Log.v("exampleFilter","Perform Filtering  ");
            Log.v("exampleFilter1","constraint "+constraint);
            Log.v("exampleFilter","listOfSpecialization"+listOfSpecialization.size());



            List<Datum> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0 ) {
                Log.v("exampleFilter1","value null");
                Log.v("exampleFilter1","list of specialization full = "+listOfSpecializationFull.size());


              //  filteredList.addAll(listOfSpecializationFull);
                filteredList = listOfSpecializationFull;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Datum item : listOfSpecializationFull) {
                    if (item.getSpecialization().toLowerCase().contains(filterPattern)) {
                        Log.v("exampleFilter1","not null item = "+item.toString());
                        Log.v("exampleFilter1","not null item  listOfSpecialization = "+listOfSpecializationFull.size());

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
           // listOfSpecialization.clear();
            listOfSpecialization =((List) results.values);
            Log.v("exampleFilter","listOfSpecialization size "+listOfSpecialization.size());
            notifyDataSetChanged();
        }
    };


}


