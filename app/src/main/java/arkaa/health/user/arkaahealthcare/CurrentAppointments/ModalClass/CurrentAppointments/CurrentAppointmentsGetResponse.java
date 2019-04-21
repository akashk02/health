
package arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentAppointmentsGetResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<CurrentAppointmentsGetResponse> CREATOR = new Creator<CurrentAppointmentsGetResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentAppointmentsGetResponse createFromParcel(Parcel in) {
            return new CurrentAppointmentsGetResponse(in);
        }

        public CurrentAppointmentsGetResponse[] newArray(int size) {
            return (new CurrentAppointmentsGetResponse[size]);
        }

    };

    protected CurrentAppointmentsGetResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data =  (List<Datum>) in.readArrayList (Datum.class.getClassLoader());
    }

    public CurrentAppointmentsGetResponse() {
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
