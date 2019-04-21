package arkaa.health.user.arkaahealthcare.eHospital.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import arkaa.health.user.arkaahealthcare.eHospital.Activities.EdoctorDetails;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.Datum;

import arkaa.health.user.arkaahealthcare.R;

import java.util.List;

public class eListOfDoctorsAdapter extends RecyclerView.Adapter<eListOfDoctorsAdapter.ViewHolder> {


    private List<Datum> listOfDoctorsArray;
    private String specialization;
    private String hospitalName;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public eListOfDoctorsAdapter(List<Datum> listOfDoctorsArray, String specialization, String hospitalName, Context context) {
        this.listOfDoctorsArray = listOfDoctorsArray;
        this.specialization = specialization;
        this.hospitalName = hospitalName;
        this.context = context;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public eListOfDoctorsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
        View itemView;


        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elist_of_doc_recyclerview, parent, false);


        return new eListOfDoctorsAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(eListOfDoctorsAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);

//        position = 0;

        if (listOfDoctorsArray != null) {


            String name = listOfDoctorsArray.get(position).getDoctorname();
            String experince = listOfDoctorsArray.get(position).getExperienceinyears();
            String education = listOfDoctorsArray.get(position).getQualification();

            String docSpecialization = specialization;
            String address = listOfDoctorsArray.get(position).getDoctoraddress();
            String DocprofilePic = "http://35.163.147.45" + listOfDoctorsArray.get(position).getDoctorimageurl();




            holder.nameTextView.setText("" + name);
            holder.experinceTextView.setText(experince + " yrs exp");
            holder.educationTextView.setText("" + education);
            holder.specialitiesTextView.setText("" + specialization);
//            holder.feesTextView.setText(fees);
            holder.addressTextView.setText("" + address);

            try {
                Glide.with(context).load(DocprofilePic).into(holder.doctorProfileIV);
            } catch (Exception e) {
                Log.v("tryCatch", "ListOfDocAdapter glide Exeption e = " + e.getMessage());
            }





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(view.getContext(), DoctorsDetail.class);
//                intent.putExtra("INDEX", position);
//
//                (view.getContext()).startActivity(intent);

            }

        });


        holder.consultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EdoctorDetails.class);
                intent.putExtra("DOCTOR_DETAIL", listOfDoctorsArray.get(position));
                intent.putExtra("HOSPITAL_NAME", hospitalName);
                intent.putExtra("DOCTOR_SPECIALIZATION", specialization);


                (view.getContext()).startActivity(intent);

            }
        });

    }
}


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfDoctorsArray.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
//        public View mTextView;
        public Button callButton;
        public Button consultButton;


        public TextView nameTextView;
        public TextView educationTextView;
        public TextView clinicNameTextView;
        public TextView specialitiesTextView;
        public TextView addressTextView;
        public TextView experinceTextView;
        public TextView feesTextView;
        public ImageView doctorProfileIV;


        public ViewHolder(View v) {
            super(v);
//            mTextView = v;
//            callButton = v.findViewById(R.id.call)
            consultButton = v.findViewById(R.id.view_profile);


            nameTextView = v.findViewById(R.id.name);
            educationTextView = v.findViewById(R.id.education);
            clinicNameTextView = v.findViewById(R.id.clinic_name);
            specialitiesTextView = v.findViewById(R.id.specialization);
            addressTextView = v.findViewById(R.id.location);
            experinceTextView = v.findViewById(R.id.experince);
            feesTextView = v.findViewById(R.id.fees);
            doctorProfileIV = v.findViewById(R.id.doctor_profile_pic);

        }
    }
}
