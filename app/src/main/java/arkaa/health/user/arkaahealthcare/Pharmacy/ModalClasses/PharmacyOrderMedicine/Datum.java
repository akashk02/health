
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("insert_or_update_pharmacy_order")
    @Expose
    private Integer insertOrUpdatePharmacyOrder;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;

    protected Datum(Parcel in) {
        this.insertOrUpdatePharmacyOrder = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getInsertOrUpdatePharmacyOrder() {
        return insertOrUpdatePharmacyOrder;
    }

    public void setInsertOrUpdatePharmacyOrder(Integer insertOrUpdatePharmacyOrder) {
        this.insertOrUpdatePharmacyOrder = insertOrUpdatePharmacyOrder;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(insertOrUpdatePharmacyOrder);
    }

    public int describeContents() {
        return  0;
    }

}
