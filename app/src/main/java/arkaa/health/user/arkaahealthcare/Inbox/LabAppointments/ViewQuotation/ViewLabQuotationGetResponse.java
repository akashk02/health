
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewLabQuotationGetResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<ViewLabQuotationGetResponse> CREATOR = new Creator<ViewLabQuotationGetResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ViewLabQuotationGetResponse createFromParcel(Parcel in) {
            return new ViewLabQuotationGetResponse(in);
        }

        public ViewLabQuotationGetResponse[] newArray(int size) {
            return (new ViewLabQuotationGetResponse[size]);
        }

    }
    ;

    protected ViewLabQuotationGetResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
//        in.readList(this.data, (arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Datum.class.getClassLoader()));
        this.data = ( (List<Datum>) (Datum.class.getClassLoader())  );

    }

    public ViewLabQuotationGetResponse() {
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
