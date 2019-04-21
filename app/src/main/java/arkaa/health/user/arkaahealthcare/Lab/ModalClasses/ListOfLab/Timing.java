
package arkaa.health.user.arkaahealthcare.Lab.ModalClasses.ListOfLab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timing {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("start_time")
    @Expose
    private String startTime;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

}
