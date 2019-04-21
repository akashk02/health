
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timing_ implements Parcelable
{

    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    public final static Creator<Timing_> CREATOR = new Creator<Timing_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Timing_ createFromParcel(Parcel in) {
            return new Timing_(in);
        }

        public Timing_[] newArray(int size) {
            return (new Timing_[size]);
        }

    }
    ;

    protected Timing_(Parcel in) {
        this.endTime = ((String) in.readValue((String.class.getClassLoader())));
        this.startTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Timing_() {
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(endTime);
        dest.writeValue(startTime);
    }

    public int describeContents() {
        return  0;
    }

}
