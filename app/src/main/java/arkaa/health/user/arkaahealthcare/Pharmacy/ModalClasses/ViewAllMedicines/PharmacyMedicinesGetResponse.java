
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacyMedicinesGetResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<PharmacyMedicinesGetResponse> CREATOR = new Creator<PharmacyMedicinesGetResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PharmacyMedicinesGetResponse createFromParcel(Parcel in) {
            return new PharmacyMedicinesGetResponse(in);
        }

        public PharmacyMedicinesGetResponse[] newArray(int size) {
            return (new PharmacyMedicinesGetResponse[size]);
        }

    }
    ;

    protected PharmacyMedicinesGetResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
    }

    public PharmacyMedicinesGetResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
