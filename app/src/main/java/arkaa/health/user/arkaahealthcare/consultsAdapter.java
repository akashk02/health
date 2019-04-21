package arkaa.health.user.arkaahealthcare;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import arkaa.health.user.arkaahealthcare.Doctor.Activity.DoctorsDetail;

public class consultsAdapter extends RecyclerView.Adapter<consultsAdapter.ViewHolder> {
    private String[] mDataset;
    private int option ;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;
        public Button callButton ;
        public Button consultButton ;




        public TextView nameTextView ;
        public TextView educationTextView ;
        public TextView clinicNameTextView ;
        public TextView specialitiesTextView ;
        public TextView addressTextView ;
        public TextView experinceTextView ;
        public TextView feesTextView ;



        public ViewHolder(View v) {
            super(v);
            mTextView = v;
//            callButton = v.findViewById(R.id.call)
            consultButton = v.findViewById(R.id.view_profile);


            nameTextView = v.findViewById(R.id.name);
            educationTextView = v.findViewById(R.id.education);
            clinicNameTextView = v.findViewById(R.id.clinic_name);
            specialitiesTextView = v.findViewById(R.id.specialization);
            addressTextView = v.findViewById(R.id.address);
            experinceTextView = v.findViewById(R.id.experince);
            feesTextView = v.findViewById(R.id.fees);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public consultsAdapter(String[] myDataset, int option) {
        mDataset = myDataset;
        this.option = option;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public consultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
        View itemView;

        if(option==1) {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_doctor, parent, false);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.consults_recyclerview, parent, false);

        }


        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),DoctorsDetail.class);
                (view.getContext()).startActivity(intent);

            }

        });

        if(option == 1) {

            holder.consultButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DoctorsDetail.class);
                    (view.getContext()).startActivity(intent);

                }
            });

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
