package arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.Griddatum;
import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Test;
import arkaa.health.user.arkaahealthcare.R;

import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass.Griddatum;

public class ElabOrdersAdapter extends RecyclerView.Adapter<ElabOrdersAdapter.ViewHolder> {

    private List<Griddatum> listOfTests;




    public ElabOrdersAdapter(List<Griddatum> listOfTests) {
        this.listOfTests = listOfTests;
    }





    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView reviewTestNameTextView;
        public TextView reviewTestDescriptionTextView;
        public TextView reviewTestPriceTextView;






        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            reviewTestNameTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.review_test_name);
            reviewTestDescriptionTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.review_test_description);
            reviewTestPriceTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.review_test_price);







        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)


    // Create new views (invoked by the layout manager)
    @Override
    public ElabOrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView ;



        itemView = LayoutInflater.from(parent.getContext())
                .inflate(arkaa.health.user.arkaahealthcare.R.layout.review_test_recyclerview, parent, false);



        return new ElabOrdersAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ElabOrdersAdapter.ViewHolder holder, int position) {


        if(listOfTests != null){

            String reviewTestName = listOfTests.get(position).getPtsLabChTestname();
            String reviewTestPrice = listOfTests.get(position).getPtsLabChFinalPrice().toString();
           // String testDescription = listOfTests.get(position).gettest

            holder.reviewTestNameTextView.setText(""+reviewTestName);
            holder.reviewTestPriceTextView.setText(""+reviewTestPrice);
          //  holder.reviewTestDescriptionTextView.setText();

        }







    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfTests.size();
    }
}


