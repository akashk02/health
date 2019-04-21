package arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Adapter;

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

import java.util.List;

import arkaa.health.user.arkaahealthcare.CurrentAppointments.Activities.ShareReports;
import arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.Activity.ListOfDocAppointments;
import arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.Activities.ElistOfPrescription;
import arkaa.health.user.arkaahealthcare.R;
import arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfHospitalModalClass.Datum;

public class ListofEhosAppAdapter extends RecyclerView.Adapter<ListofEhosAppAdapter.ViewHolder> {
    List<Datum> listOfHospitalsArrayList;
    Context context ;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListofEhosAppAdapter( List<Datum> listOfPharmacyArrayList,Context context) {

        this.listOfHospitalsArrayList = listOfPharmacyArrayList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListofEhosAppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                                     int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView;


        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elist_of_hos_app_recyclerview, parent, false);


        return new ListofEhosAppAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ListofEhosAppAdapter.ViewHolder holder, int position) {

        final String hospitalName = ""+listOfHospitalsArrayList.get(position).getHospitalname();
        String hospitalAddress = ""+listOfHospitalsArrayList.get(position).getAddress();
        String hospitalContactNo = ""+listOfHospitalsArrayList.get(position).getPhoneoffice();
        final String hospitalId = ""+listOfHospitalsArrayList.get(position).getHospitalIdAppointment();
        String hospitalImagePath = "http://35.163.147.45"+listOfHospitalsArrayList.get(position).getImagepath();

        holder.hospitalNameTextView.setText(hospitalName);
        holder.hospitalAddressTextView.setText(hospitalAddress);
        holder.hospitalContactNoTextView.setText(hospitalContactNo);
        holder.hospitalIdTextView.setText(hospitalId);

        try {
            Glide.with(context).load(hospitalImagePath).placeholder(R.drawable.lab).into(holder.hospitalImageView);
        } catch (Exception e){
            Log.v("tryCatch","ListOfDocAdapter glide Exeption e = "+e.getMessage());
        }


        holder.viewAppointmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ListOfDocAppointments.class);
                intent.putExtra("HOSPITAL_ID", hospitalId);
                intent.putExtra("HOSPITAL_NAME",hospitalName);
                Log.v("pharmacy", "pharmacy listofLabAdapter= " + hospitalId);
                (view.getContext()).startActivity(intent);

            }

        });

        holder.viewPrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ElistOfPrescription.class);
                intent.putExtra("HOSPITAL_ID",hospitalId);
                v.getContext().startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {


        return listOfHospitalsArrayList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView hospitalNameTextView;
        public TextView hospitalAddressTextView;
        public TextView hospitalContactNoTextView;
        public TextView hospitalIdTextView;
        public Button viewAppointmentsButton;
        public Button viewPrescriptionButton;
        public ImageView hospitalImageView;


        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            hospitalNameTextView = v.findViewById(R.id.hospital_name);
            hospitalAddressTextView = v.findViewById(R.id.hospital_address);
            hospitalContactNoTextView = v.findViewById(R.id.hospital_contact_no);
            hospitalIdTextView = v.findViewById(R.id.hospital_id);
            viewAppointmentsButton = v.findViewById(R.id.hospital_doctor_consultation);
            viewPrescriptionButton = v.findViewById(R.id.upload_prescription);
            hospitalImageView = v.findViewById(R.id.hospital_image);


        }
    }
}

