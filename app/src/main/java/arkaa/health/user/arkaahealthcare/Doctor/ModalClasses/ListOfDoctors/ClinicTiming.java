
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClinicTiming implements Parcelable
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
    @SerializedName("timings")
    @Expose
    private List<Timing> timings = null;
    public final static Creator<ClinicTiming> CREATOR = new Creator<ClinicTiming>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ClinicTiming createFromParcel(Parcel in) {
            return new ClinicTiming(in);
        }

        public ClinicTiming[] newArray(int size) {
            return (new ClinicTiming[size]);
        }

    }
    ;

    protected ClinicTiming(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dayOfWeek = ((String) in.readValue((String.class.getClassLoader())));
        this.isAvailable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
//        in.readList(this.timings, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Timing.class.getClassLoader()));
        this.timings = ( (List<Timing>) in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Timing.class.getClassLoader()));
    }

    public ClinicTiming() {
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

    public List<Timing> getTimings() {
        return timings;
    }

    public void setTimings(List<Timing> timings) {
        this.timings = timings;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(dayOfWeek);
        dest.writeValue(isAvailable);
        dest.writeList(timings);
    }

    public int describeContents() {
        return  0;
    }

}
