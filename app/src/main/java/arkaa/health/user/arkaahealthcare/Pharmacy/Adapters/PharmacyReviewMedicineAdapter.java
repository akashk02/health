package arkaa.health.user.arkaahealthcare.Pharmacy.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.R;

import java.util.List;

public class PharmacyReviewMedicineAdapter extends RecyclerView.Adapter<arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.PharmacyReviewMedicineAdapter.ViewHolder> {
//    private List<String> labTestsArrayList;

    private List<Datum> listOfTests;




    public PharmacyReviewMedicineAdapter(List<Datum> listOfTests) {

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
    public arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.PharmacyReviewMedicineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView ;



        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_test_recyclerview, parent, false);



        return new arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.PharmacyReviewMedicineAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(arkaa.health.user.arkaahealthcare.Pharmacy.Adapters.PharmacyReviewMedicineAdapter.ViewHolder holder, int position) {


//        if(listOfAllTests != null){
//
//            String testId = listOfAllTests.get(position).getId().toString();
//            String testName = listOfAllTests.get(position).getTestName();
//            String testDescription = listOfAllTests.get(position).getDescription();
////            String testPrice = listOfAllTests.get(position).getPrice().toString();
//
//            Log.v("ListOfallLabs","test price postion = "+position+" price = "+listOfAllTests.get(position).getPrice());
//
//
//            holder.testIDTextView.setText(testId);
//            holder.testNameTextView.setText(testName);
////            holder.testPriceTextView.setText(testPrice);
//            holder.testDescriptionTextView.setText(testDescription);
//
//            }

//        if(labTestsArrayList != null){
//
////            holder.labTestNameTextView.setText(labTestsArrayList.get(position));
//
//
//
//        }

        if(listOfTests != null){

            String reviewTestName = listOfTests.get(position).getTestName();
            String reviewTestDescription = listOfTests.get(position).getDescription();
            String reviewTestPrice = listOfTests.get(position).getPrice().toString();

            holder.reviewTestNameTextView.setText(reviewTestName);
            holder.reviewTestPriceTextView.setText(reviewTestDescription);
            holder.reviewTestPriceTextView.setText(reviewTestPrice);



        }







    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfTests.size();
    }
}
