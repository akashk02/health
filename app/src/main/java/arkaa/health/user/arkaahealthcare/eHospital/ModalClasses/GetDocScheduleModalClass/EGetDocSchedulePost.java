
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDocScheduleModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EGetDocSchedulePost {

    @SerializedName("doctorId")
    @Expose
    private String doctorId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

}
