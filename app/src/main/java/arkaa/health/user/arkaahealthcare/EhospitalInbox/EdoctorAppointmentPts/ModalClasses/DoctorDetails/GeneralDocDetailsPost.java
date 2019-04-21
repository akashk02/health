
package arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.DoctorDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralDocDetailsPost {

    @SerializedName("Userid")
    @Expose
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
