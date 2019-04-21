
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timing implements Parcelable
{

    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    public final static Creator<Timing> CREATOR = new Creator<Timing>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Timing createFromParcel(Parcel in) {
            return new Timing(in);
        }

        public Timing[] newArray(int size) {
            return (new Timing[size]);
        }

    }
    ;

    protected Timing(Parcel in) {
        this.endTime = ((String) in.readValue((String.class.getClassLoader())));
        this.startTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Timing() {
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
