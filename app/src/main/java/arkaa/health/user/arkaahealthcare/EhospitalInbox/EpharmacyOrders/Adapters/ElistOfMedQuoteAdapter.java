package arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.Griddatum;
import arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.MedicineInfo;
import arkaa.health.user.arkaahealthcare.Pharmacy.Activity.view_all_medicines;
import arkaa.health.user.arkaahealthcare.R;

import java.util.List;

import arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses.Griddatum;

public class ElistOfMedQuoteAdapter extends RecyclerView.Adapter<ElistOfMedQuoteAdapter.ViewHolder> {
    private List<Griddatum> listOfMedicines;
    private int noOfItemsGlobal =0 ;
    private Context context ;
    private String type ;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;

        public TextView medicineNameTextView ;
        public TextView brandNameTextView ;
        public TextView medicineQuantityTextView ;
        public TextView medicinePriceTextView ;
        public TextView medicineDiscountTextView;
        public TextView medicineGstTextView;





        public ViewHolder(View v) {
            super(v);
            mTextView = v;

            medicineNameTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.medicine_name);
            brandNameTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.medicine_manufacturer_name);
            medicineQuantityTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.medicine_quantity);
            medicinePriceTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.medicine_price);
            medicineDiscountTextView = v.findViewById(arkaa.health.user.arkaahealthcare.R.id.medicine_discount);
            medicineGstTextView = v.findViewById(R.id.medicine_gst);




        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ElistOfMedQuoteAdapter( List<Griddatum> listOfMedicines) {
        this.listOfMedicines = listOfMedicines ;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ElistOfMedQuoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView ;


        itemView = LayoutInflater.from(parent.getContext())
                .inflate(arkaa.health.user.arkaahealthcare.R.layout.e_phar_quote_recyclerview, parent, false);



        return new ElistOfMedQuoteAdapter.ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ElistOfMedQuoteAdapter.ViewHolder holder, final int position) {
        final String medicineName = listOfMedicines.get(position).getMedicineName();
        String medicineBrand = listOfMedicines.get(position).getPtsMedicineChBrand();
        Double medicinePrice = listOfMedicines.get(position).getPtsMedicineChFinalPrice();
        double medicineQuantity = listOfMedicines.get(position).getPtsMedicineChQty();
        int medicineDiscount = listOfMedicines.get(position).getPtsMedicineChDiscount();
        int medicineGst = listOfMedicines.get(position).getPtsMedicineChGst();








        holder.medicineNameTextView.setText(""+medicineName);
        holder.brandNameTextView.setText(""+medicineBrand);
        holder.medicineQuantityTextView.setText("Quantity "+medicineQuantity);
        holder.medicinePriceTextView.setText("RS "+medicinePrice);
        holder.medicineDiscountTextView.setText("Discount "+medicineDiscount+" %");
        holder.medicineGstTextView.setText("GST :"+medicineGst+"%");


        }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {


        return listOfMedicines.size();
    }
}





