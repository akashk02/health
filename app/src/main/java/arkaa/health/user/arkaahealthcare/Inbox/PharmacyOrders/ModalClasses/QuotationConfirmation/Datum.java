
package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.QuotationConfirmation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("change_pharmacy_order_status_userwise")
    @Expose
    private Integer changePharmacyOrderStatusUserwise;
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
        this.changePharmacyOrderStatusUserwise = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getChangePharmacyOrderStatusUserwise() {
        return changePharmacyOrderStatusUserwise;
    }

    public void setChangePharmacyOrderStatusUserwise(Integer changePharmacyOrderStatusUserwise) {
        this.changePharmacyOrderStatusUserwise = changePharmacyOrderStatusUserwise;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(changePharmacyOrderStatusUserwise);
    }

    public int describeContents() {
        return  0;
    }

}
