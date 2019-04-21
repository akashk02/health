
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorTiming implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("day_of_week")
    @Expose
    private String dayOfWeek;
    @SerializedName("is_available")
    @Expose
    private Boolean isAvailable;
    @SerializedName("reason_of_unavailability")
    @Expose
    private String reasonOfUnavailability;
    @SerializedName("timings")
    @Expose
    private List<Timing_> timings = null;
    public final static Creator<DoctorTiming> CREATOR = new Creator<DoctorTiming>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DoctorTiming createFromParcel(Parcel in) {
            return new DoctorTiming(in);
        }

        public DoctorTiming[] newArray(int size) {
            return (new DoctorTiming[size]);
        }

    }
    ;

    protected DoctorTiming(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dayOfWeek = ((String) in.readValue((String.class.getClassLoader())));
        this.isAvailable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.reasonOfUnavailability = ((String) in.readValue((String.class.getClassLoader())));
//        in.readList(this.timings, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Timing_.class.getClassLoader()));
        this.timings = ( (List<Timing_>) in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Timing_.class.getClassLoader())  );
    }

    public DoctorTiming() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getReasonOfUnavailability() {
        return reasonOfUnavailability;
    }

    public void setReasonOfUnavailability(String reasonOfUnavailability) {
        this.reasonOfUnavailability = reasonOfUnavailability;
    }

    public List<Timing_> getTimings() {
        return timings;
    }

    public void setTimings(List<Timing_> timings) {
        this.timings = timings;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(dayOfWeek);
        dest.writeValue(isAvailable);
        dest.writeValue(reasonOfUnavailability);
        dest.writeList(timings);
    }

    public int describeContents() {
        return  0;
    }

}
