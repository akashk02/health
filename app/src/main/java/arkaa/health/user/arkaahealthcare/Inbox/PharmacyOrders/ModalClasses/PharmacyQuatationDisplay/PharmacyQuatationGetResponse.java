
package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacyQuatationGetResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<PharmacyQuatationGetResponse> CREATOR = new Creator<PharmacyQuatationGetResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PharmacyQuatationGetResponse createFromParcel(Parcel in) {
            return new PharmacyQuatationGetResponse(in);
        }

        public PharmacyQuatationGetResponse[] newArray(int size) {
            return (new PharmacyQuatationGetResponse[size]);
        }

    }
    ;

    protected PharmacyQuatationGetResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.data = ( (List<Datum>) in.readArrayList(Datum.class.getClassLoader())  );
    }

    public PharmacyQuatationGetResponse() {
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
