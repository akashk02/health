package arkaa.health.user.arkaahealthcare.eHospital.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.eHospital.Activities.eListOfDoctors;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDocCategoryModalClass.Datum;

import java.util.List;

public class eListOfDocSpecializationAdapter extends RecyclerView.Adapter<eListOfDocSpecializationAdapter.ViewHolder> {
    private List<Datum> listOfSpecialization ;
    private String hospitalId;
    private String hospitalName;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;
        public TextView specialization ;
        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            specialization = v.findViewById(R.id.specialization);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public eListOfDocSpecializationAdapter(List<Datum> specialities,String hospitalId,String hospitalName) {
        listOfSpecialization = specialities ;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public eListOfDocSpecializationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {


        View itemView ;


            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_of_doctors_recyclerview, parent, false);

            return new eListOfDocSpecializationAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(eListOfDocSpecializationAdapter.ViewHolder holder, final int position) {


        if(listOfSpecialization != null) {

            final String specialization = listOfSpecialization.get(position).getSpecialization();
            Log.v("specialization111","specialization = "+specialization);
            final int specializationID = listOfSpecialization.get(position).getId();

            holder.specialization.setText(""+specialization);
            Log.v("specialization111","specialization textview ="+holder.specialization.getText().toString());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//
                    Intent intent = new Intent(view.getContext(),eListOfDoctors.class);
                    intent.putExtra("SPECIALIZATION_ID",""+specializationID);
                    intent.putExtra("SPECIALIZATION_NAME",specialization);
                    intent.putExtra("HOSPITAL_ID",hospitalId);
                    intent.putExtra("HOSPITAL_NAME",hospitalName);
                    (view.getContext()).startActivity(intent);

                }

            });







        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfSpecialization.size();
    }
}

