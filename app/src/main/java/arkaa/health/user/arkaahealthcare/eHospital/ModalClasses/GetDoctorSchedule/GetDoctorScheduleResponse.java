
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDoctorScheduleResponse implements Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<GetDoctorScheduleResponse> CREATOR = new Creator<GetDoctorScheduleResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetDoctorScheduleResponse createFromParcel(Parcel in) {
            return new GetDoctorScheduleResponse(in);
        }

        public GetDoctorScheduleResponse[] newArray(int size) {
            return (new GetDoctorScheduleResponse[size]);
        }

    }
    ;

    protected GetDoctorScheduleResponse(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule.Datum.class.getClassLoader()));
    }

    public GetDoctorScheduleResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(error);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
