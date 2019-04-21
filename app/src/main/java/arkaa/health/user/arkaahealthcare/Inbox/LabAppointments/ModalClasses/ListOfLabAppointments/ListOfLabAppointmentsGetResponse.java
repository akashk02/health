
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfLabAppointmentsGetResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<ListOfLabAppointmentsGetResponse> CREATOR = new Creator<ListOfLabAppointmentsGetResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListOfLabAppointmentsGetResponse createFromParcel(Parcel in) {
            return new ListOfLabAppointmentsGetResponse(in);
        }

        public ListOfLabAppointmentsGetResponse[] newArray(int size) {
            return (new ListOfLabAppointmentsGetResponse[size]);
        }

    }
    ;

    protected ListOfLabAppointmentsGetResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
    }

    public ListOfLabAppointmentsGetResponse() {
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
