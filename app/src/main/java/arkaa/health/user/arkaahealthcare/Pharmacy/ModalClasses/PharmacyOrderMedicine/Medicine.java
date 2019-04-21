
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medicine implements Parcelable
{

    @SerializedName("medi_id")
    @Expose
    private Integer mediId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    public final static Creator<Medicine> CREATOR = new Creator<Medicine>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Medicine createFromParcel(Parcel in) {
            return new Medicine(in);
        }

        public Medicine[] newArray(int size) {
            return (new Medicine[size]);
        }

    }
    ;

    protected Medicine(Parcel in) {
        this.mediId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.quantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Medicine() {
    }

    public Integer getMediId() {
        return mediId;
    }

    public void setMediId(Integer mediId) {
        this.mediId = mediId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mediId);
        dest.writeValue(quantity);
    }

    public int describeContents() {
        return  0;
    }

}
