
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDoctorSchedulePost implements Parcelable
{

    @SerializedName("doctorId")
    @Expose
    private String doctorId;
    public final static Creator<GetDoctorSchedulePost> CREATOR = new Creator<GetDoctorSchedulePost>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetDoctorSchedulePost createFromParcel(Parcel in) {
            return new GetDoctorSchedulePost(in);
        }

        public GetDoctorSchedulePost[] newArray(int size) {
            return (new GetDoctorSchedulePost[size]);
        }

    }
    ;

    protected GetDoctorSchedulePost(Parcel in) {
        this.doctorId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GetDoctorSchedulePost() {
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(doctorId);
    }

    public int describeContents() {
        return  0;
    }

}
