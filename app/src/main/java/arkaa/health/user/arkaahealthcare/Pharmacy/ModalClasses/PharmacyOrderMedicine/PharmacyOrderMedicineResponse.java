
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacyOrderMedicineResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<PharmacyOrderMedicineResponse> CREATOR = new Creator<PharmacyOrderMedicineResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PharmacyOrderMedicineResponse createFromParcel(Parcel in) {
            return new PharmacyOrderMedicineResponse(in);
        }

        public PharmacyOrderMedicineResponse[] newArray(int size) {
            return (new PharmacyOrderMedicineResponse[size]);
        }

    }
    ;

    protected PharmacyOrderMedicineResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.Datum.class.getClassLoader()));
    }

    public PharmacyOrderMedicineResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
