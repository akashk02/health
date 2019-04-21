package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Test;

import arkaa.health.user.arkaahealthcare.R;

import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Test;

public class ViewQuotationAdapter extends RecyclerView.Adapter<ViewQuotationAdapter.ViewHolder> {

    private List<Test> listOfTests;




    public ViewQuotationAdapter(List<Test> listOfTests) {

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

            reviewTestNameTextView = v.findViewById(R.id.review_test_name);
            reviewTestDescriptionTextView = v.findViewById(R.id.review_test_description);
            reviewTestPriceTextView = v.findViewById(R.id.review_test_price);







        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)


    // Create new views (invoked by the layout manager)
    @Override
    public ViewQuotationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView ;



        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_test_recyclerview, parent, false);



        return new ViewQuotationAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewQuotationAdapter.ViewHolder holder, int position) {


        if(listOfTests != null){

            String reviewTestName = listOfTests.get(position).getTestName();
            String reviewTestPrice = listOfTests.get(position).getPrice().toString();

            holder.reviewTestNameTextView.setText(""+reviewTestName);
            holder.reviewTestPriceTextView.setText(""+reviewTestPrice);

            }







    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfTests.size();
    }
}

