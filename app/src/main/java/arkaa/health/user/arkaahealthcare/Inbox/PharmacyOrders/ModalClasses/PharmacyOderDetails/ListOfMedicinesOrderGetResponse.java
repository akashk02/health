
package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfMedicinesOrderGetResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<ListOfMedicinesOrderGetResponse> CREATOR = new Creator<ListOfMedicinesOrderGetResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListOfMedicinesOrderGetResponse createFromParcel(Parcel in) {
            return new ListOfMedicinesOrderGetResponse(in);
        }

        public ListOfMedicinesOrderGetResponse[] newArray(int size) {
            return (new ListOfMedicinesOrderGetResponse[size]);
        }

    }
    ;

    protected ListOfMedicinesOrderGetResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        //in.readList(this.data, (arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum.class.getClassLoader()));
        this.data = ( (List<Datum>) in.readArrayList(arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.Datum.class.getClassLoader()) );

    }

    public ListOfMedicinesOrderGetResponse() {
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
