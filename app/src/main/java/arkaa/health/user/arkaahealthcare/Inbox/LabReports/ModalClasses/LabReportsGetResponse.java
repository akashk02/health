
package arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabReportsGetResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<LabReportsGetResponse> CREATOR = new Creator<LabReportsGetResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LabReportsGetResponse createFromParcel(Parcel in) {
            return new LabReportsGetResponse(in);
        }

        public LabReportsGetResponse[] newArray(int size) {
            return (new LabReportsGetResponse[size]);
        }

    }
    ;

    protected LabReportsGetResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data =((List<Datum>) in.readArrayList(Datum.class.getClassLoader())) ;

    }

    public LabReportsGetResponse() {
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
