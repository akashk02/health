package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity.PharmacyOrederDetails;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.Activity.ViewQuotation;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.MedicineDetail;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.PharmacyOrderDetails;
import arkaa.health.user.arkaahealthcare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.MedicineDetail;

public class ListOfPharmacyOrderAdapter extends RecyclerView.Adapter<ListOfPharmacyOrderAdapter.ViewHolder> {
    private List<Datum> listOfPharmacyOrders;
    private Datum pharmacyOrderDetailObject;


    // Provide a suitable constructor (depends on the kind of dataset)
    public ListOfPharmacyOrderAdapter(List<Datum> listOfPharmacyOrders) {
        this.listOfPharmacyOrders = listOfPharmacyOrders;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListOfPharmacyOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pharmacy_order_recyclerview, parent, false);

        return new ListOfPharmacyOrderAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ListOfPharmacyOrderAdapter.ViewHolder holder, final int position) {


        if (listOfPharmacyOrders != null) {


            String pharmacyName = ""+listOfPharmacyOrders.get(position).getPharmacyName();
            String orderStatus = ""+listOfPharmacyOrders.get(position).getAppointmentStatus();
            String date = ""+getDate(listOfPharmacyOrders.get(position).getCreatedOn());
            String totalAmount = "RS "+listOfPharmacyOrders.get(position).getCost().toString();
            final Integer orderId = listOfPharmacyOrders.get(position).getId();

            holder.pharmacyNameTextView.setText(pharmacyName);
            holder.orderStatusTextView.setText(orderStatus);
            holder.totalAmountTextView.setText(totalAmount);
            holder.pharmacyDateTextView.setText(date);

            String bookingType = listOfPharmacyOrders.get(position).getBookingType().toString();
            List<MedicineDetail> medicineDetailArrayList = listOfPharmacyOrders.get(position).getMedicineDetail();

            holder.viewQuotationButton.setVisibility(View.GONE);
            holder.viewDetailsButton.setVisibility(View.GONE);
            holder.noQuoteReceivedTextView.setVisibility(View.GONE);

            holder.totalAmountLinearLayout.setVisibility(View.VISIBLE);


            if (orderStatus.equals("pending") && bookingType.equals("2") && (medicineDetailArrayList != null)) {
                Log.v("medicineDetailArrayList", " " + medicineDetailArrayList + "  boolean = " + (orderStatus.equals("pending") && bookingType.equals("2") && medicineDetailArrayList == null));
                holder.viewQuotationButton.setVisibility(View.VISIBLE);
                holder.viewDetailsButton.setVisibility(View.GONE);
            } else if(orderStatus.equals("pending") && bookingType.equals("2") && (medicineDetailArrayList == null)){
                holder.viewQuotationButton.setVisibility(View.GONE);
                holder.viewDetailsButton.setVisibility(View.GONE);
                holder.totalAmountLinearLayout.setVisibility(View.GONE);
                holder.noQuoteReceivedTextView.setVisibility(View.VISIBLE);
            }
            else if(medicineDetailArrayList != null){


                holder.viewQuotationButton.setVisibility(View.GONE);
                holder.viewDetailsButton.setVisibility(View.VISIBLE);
            }


            holder.viewQuotationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewQuotation.class);
                    intent.putExtra("orderId", orderId);
                    v.getContext().startActivity(intent);
                }
            });




            holder.viewDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PharmacyOrederDetails.class);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("ORDER_DETAILS",listOfPharmacyOrders.get(position));
                    Log.v("viewDetail","viewDetail");
                    v.getContext().startActivity(intent);

                }

            });

        }





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listOfPharmacyOrders.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView pharmacyNameTextView;
        public TextView orderStatusTextView;
        public TextView totalAmountTextView;
        public TextView pharmacyDateTextView;
        public TextView pharmacyTimeTextView;
        public TextView noQuoteReceivedTextView;

        public Button viewDetailsButton;
        public Button viewQuotationButton;
        public LinearLayout totalAmountLinearLayout;


        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            pharmacyNameTextView = v.findViewById(R.id.pharmacy_name);
            orderStatusTextView = v.findViewById(R.id.pharmacy_order_status);
            totalAmountTextView = v.findViewById(R.id.pharmacy_total_amount);
            totalAmountLinearLayout = v.findViewById(R.id.total_amount);
            pharmacyDateTextView = v.findViewById(R.id.pharmacy_date);
            pharmacyTimeTextView = v.findViewById(R.id.pharmacy_time);
            noQuoteReceivedTextView = v.findViewById(R.id.no_quote_received);
            viewDetailsButton = v.findViewById(R.id.pharmacy_view_details);
            viewQuotationButton = v.findViewById(R.id.pharmacy_view_quotation);


        }
    }

    public String getDate(String realTime) {

        String str ;
        try {

            String time = realTime.substring(0, realTime.indexOf("."));

            String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
            //   String outputPattern = "dd-MMM-yyyy h:mm a";
            String outputPattern = "dd-MMM-yyyy";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            str = null;

            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
                Log.v("new_time", "date = " + date + "str = " + str);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.v("new_time", "exception " + e.getMessage());
            }

        }catch (Exception e){

            str = realTime;

        }



        Log.v("new_time","str = "+str);
        return str;


    }



}

