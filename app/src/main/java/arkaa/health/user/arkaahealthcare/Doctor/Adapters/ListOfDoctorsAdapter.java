package arkaa.health.user.arkaahealthcare.Doctor.Adapters;

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
import arkaa.health.user.arkaahealthcare.Doctor.Activity.DoctorsDetail;
import arkaa.health.user.arkaahealthcare.Doctor.Activity.ViewAllClinics;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicDetail;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Datum;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Degree;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Specilaization;
import arkaa.health.user.arkaahealthcare.R;

import java.util.List;

import arkaa.health.user.arkaahealthcare.Doctor.Activity.ViewAllClinics;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicDetail;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Datum;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Degree;
import arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Specilaization;

public class ListOfDoctorsAdapter extends RecyclerView.Adapter<ListOfDoctorsAdapter.ViewHolder> {
    private String[] mDataset;
    private int option;
    private int specializationId;
    private Context context;


    private List<Datum> listOfDoctorsArray;


    // Provide a suitable constructor (depends on the kind of dataset)
    public ListOfDoctorsAdapter(String[] myDataset, int option, List<Datum> listOfDoctorsArray,int specializationId, Context context) {
        mDataset = myDataset;
        this.option = option;
        this.listOfDoctorsArray = listOfDoctorsArray;
        this.specializationId = specializationId;
        this.context = context;
        }

    // Create new views (invoked by the layout manager)
    @Override
    public ListOfDoctorsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
        View itemView;

        if (option == 1) {

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);

//        position = 0;

        if (listOfDoctorsArray != null) {

            List<Degree> degreeArray = listOfDoctorsArray.get(position).getDegree();

            List<Specilaization> specializationArray = listOfDoctorsArray.get(position).getSpecilaization();

            List<ClinicDetail> clinicDetailsArray = listOfDoctorsArray.get(position).getClinicDetails();

            String name = listOfDoctorsArray.get(position).getDoctorName();
            String experince = listOfDoctorsArray.get(position).getTotalExperience();
            String education = degreeArray.get(0).getDegree();
            String specialization = specializationArray.get(0).getSpecializationName();
            String fees;
            String address;
            final String doctorUniqueId = "" + listOfDoctorsArray.get(position).getDoctorUniqueId();
            Log.v("viewAllClinicData", "ListOFDoctorAdapter doctorUniqueIdIntent " + doctorUniqueId);
            String DocprofilePic = listOfDoctorsArray.get(position).getProfilePicPath();


            if (clinicDetailsArray != null) {

//                  fees = clinicDetailsArray.get(0).getFirstConsultationFee().toString();

                if (clinicDetailsArray.get(0).getAffiliationDetails() != null) {
                    fees = "RS " + clinicDetailsArray.get(0).getAffiliationDetails().get(0).getFirstConsultationFee();
                } else {
                    fees = "";

                }

                address = clinicDetailsArray.get(0).getCity();
            } else {
                fees = "";
                address = "";
            }


            holder.nameTextView.setText("" + name);
            holder.experinceTextView.setText(experince + " yrs exp");
            holder.educationTextView.setText("" + education);
            holder.specialitiesTextView.setText("" + specialization);
            holder.feesTextView.setText("" + fees);
            holder.addressTextView.setText("" + address);

            try {
                Glide.with(context).load(DocprofilePic).into(holder.DocProfilePicIV);
            } catch (Exception e){
                Log.v("tryCatch","ListOfDocAdapter glide Exeption e = "+e.getMessage());
            }

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(view.getContext(), DoctorsDetail.class);
//                    intent.putExtra("INDEX", position);
//                    intent.putExtra("SPECIALIZATION_ID", specializationId);
//                    intent.putExtra("DOCTOR_UNIQUE_ID", doctorUniqueId);
//
//                    Log.v("viewAllClinicData", "ListOFDoctorAdapter() holder.itemView doctorUniqueIdIntent " + doctorUniqueId);
//
//
//                    (view.getContext()).startActivity(intent);
//
//                }
//
//            });

            if (option == 1) {

                holder.consultButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //OLD COFIG
//                        Intent intent = new Intent(view.getContext(), DoctorsDetail.class);
                        //OLD CONFIG

                        //NEW CHANGES
                        Intent intent = new Intent(view.getContext(), ViewAllClinics.class);

                        //NEW CHANGES


                        intent.putExtra("INDEX", position);
                        intent.putExtra("SPECIALIZATION_ID", specializationId);
                        intent.putExtra("DOCTOR_UNIQUE_ID", doctorUniqueId);
                        Log.v("viewAllClinicData", "ListOFDoctorAdapter() holder.consultButton doctorUniqueIdIntent " + doctorUniqueId);



                        (view.getContext()).startActivity(intent);

                    }
                });

            }

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
        public ImageView DocProfilePicIV;


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
            DocProfilePicIV = v.findViewById(R.id.doctor_profile_pic);

        }
    }
}
