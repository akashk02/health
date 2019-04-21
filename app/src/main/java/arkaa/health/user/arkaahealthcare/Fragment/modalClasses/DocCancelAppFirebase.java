
package arkaa.health.user.arkaahealthcare.Fragment.modalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocCancelAppFirebase {

    @SerializedName("doctor_Name")
    @Expose
    private String doctorName;
    @SerializedName("doctor_Status")
    @Expose
    private Boolean doctorStatus;
    @SerializedName("doctor_UniqueID")
    @Expose
    private String doctorUniqueID;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Boolean getDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(Boolean doctorStatus) {
        this.doctorStatus = doctorStatus;
    }

    public String getDoctorUniqueID() {
        return doctorUniqueID;
    }

    public void setDoctorUniqueID(String doctorUniqueID) {
        this.doctorUniqueID = doctorUniqueID;
    }

}
