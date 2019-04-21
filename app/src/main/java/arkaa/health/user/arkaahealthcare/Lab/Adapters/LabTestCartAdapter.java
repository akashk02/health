package arkaa.health.user.arkaahealthcare.Lab.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.R;

import java.util.List;

public class LabTestCartAdapter extends RecyclerView.Adapter<LabTestCartAdapter.ViewHolder> {
    private List<String> labTestsArrayList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView labTestNameTextView;



        public ViewHolder(View v) {
            super(v);
            mTextView = v;


            labTestNameTextView = v.findViewById(R.id.lab_test_name);




        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LabTestCartAdapter(List<String> labTests) {
        this.labTestsArrayList = labTests;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LabTestCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView ;



            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lab_tests_cart_recyclerview, parent, false);



        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




        if(labTestsArrayList != null){

            holder.labTestNameTextView.setText(""+labTestsArrayList.get(position));



        }







    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return labTestsArrayList.size();
    }
}
