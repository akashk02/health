
package arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabTests;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabAllTestsResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<LabAllTestsResponse> CREATOR = new Creator<LabAllTestsResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LabAllTestsResponse createFromParcel(Parcel in) {
            return new LabAllTestsResponse(in);
        }

        public LabAllTestsResponse[] newArray(int size) {
            return (new LabAllTestsResponse[size]);
        }

    }
    ;

    protected LabAllTestsResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
    }

    public LabAllTestsResponse() {
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
