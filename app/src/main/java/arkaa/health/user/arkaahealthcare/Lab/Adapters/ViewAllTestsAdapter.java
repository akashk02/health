package arkaa.health.user.arkaahealthcare.Lab.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Lab.Activity.viewAllTest;
import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;
import arkaa.health.user.arkaahealthcare.R;

import java.util.ArrayList;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests.Datum;

public class ViewAllTestsAdapter extends RecyclerView.Adapter<ViewAllTestsAdapter.ViewHolder> implements Filterable {
    private String[] mDataset;
    private int option;
    private List<Datum> listOfAllTests ;
    private List<Datum> listOfAllTestsFull ;
    private Context context;
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            Log.v("exampleFilter", "Perform Filtering  ");
            Log.v("exampleFilter1", "constraint " + constraint);
            Log.v("exampleFilter", "listOfAllTests" + listOfAllTests.size());


            List<Datum> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Log.v("exampleFilter1", "value null");
                Log.v("exampleFilter1", "list of specialization full = " + listOfAllTestsFull.size());


                //  filteredList.addAll(listOfAllTestsFull);
                filteredList = listOfAllTestsFull;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Datum item : listOfAllTestsFull) {
                    if (item.getTestName().toLowerCase().contains(filterPattern)) {
                        Log.v("exampleFilter1", "not null item = " + item.toString());
                        Log.v("exampleFilter1", "not null item  listOfAllTests = " + listOfAllTestsFull.size());

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
            // listOfAllTests.clear();
            listOfAllTests = ((List) results.values);
//            Log.v("exampleFilter","listOfAllTests size "+listOfAllTests.size());
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        Log.v("listSpecialization", "filter = " + exampleFilter.toString());

        return exampleFilter;    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;
//        public TextView testIDTextView ;
        public TextView testNameTextView ;
        public TextView testPriceTextView ;
//        public TextView testDescriptionTextView ;
//        public Button addToCartButton;
        ImageView addToCartImageView;
        LinearLayout addToCartLinearLayout;

        public ViewHolder(View v) {
            super(v);
            mTextView = v;

//            testIDTextView = v.findViewById(R.id.test_id);
            testNameTextView = v.findViewById(R.id.test_name);
            testPriceTextView = v.findViewById(R.id.medicine_price);
//            testDescriptionTextView = v.findViewById(R.id.test_description);
//            addToCartButton  = v.findViewById(R.id.add_to_cart);
             addToCartImageView = v.findViewById(R.id.add_to_cart);
             addToCartLinearLayout = v.findViewById(R.id.add_to_cart_linear_layout);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ViewAllTestsAdapter(String[] myDataset, int option, List<Datum> listOfAllTests, Context context) {
        mDataset = myDataset;
        this.option = option;
        this.listOfAllTests = listOfAllTests;
        listOfAllTestsFull=listOfAllTests;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewAllTestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_doctor, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView ;


        if(option == 1) {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.all_tests_recyclerview_beta, parent, false);

        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.all_tests_recyclerview_beta, parent, false);

        }

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(view.getContext(),DoctorsDetail.class);
//                (view.getContext()).startActivity(intent);
//
//                Toast.makeText(view.getContext(),"asasas0",Toast.LENGTH_SHORT).show();
//            }
//
//        });

        if(listOfAllTests != null){

            final String testId = listOfAllTests.get(position).getId().toString();
            final String testName = listOfAllTests.get(position).getTestName();
            String testDescription = listOfAllTests.get(position).getDescription();
            final int testPrice = listOfAllTests.get(position).getPrice();

            Log.v("ListOfallLabs","test price postion = "+position+" price = "+listOfAllTests.get(position).getPrice());


//            holder.testIDTextView.setText(testId);
            holder.testNameTextView.setText(testName);
            holder.testPriceTextView.setText("RS "+testPrice);
//            holder.testDescriptionTextView.setText(testDescription);

//            holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(context instanceof viewAllTest){
//                        ((viewAllTest)context).updateTestCartView(Integer.parseInt(testId),testName,testPrice,position);
//                    }
//
//
//
//                }
//            });

//            holder.addToCartImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    if(context instanceof viewAllTest){
//                        ((viewAllTest)context).updateTestCartView(Integer.parseInt(testId),testName,testPrice,position);
//                    }
//
//
//
//                }
//            });

            holder.addToCartLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(context instanceof viewAllTest){
                        ((viewAllTest)context).updateTestCartView(Integer.parseInt(testId),testName,testPrice,position);
                    }



                }
            });







        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfAllTests.size();
    }
}
