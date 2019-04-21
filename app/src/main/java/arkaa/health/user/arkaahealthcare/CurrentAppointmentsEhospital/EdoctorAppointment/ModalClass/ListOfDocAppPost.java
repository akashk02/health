
package arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfDocAppPost {

    @SerializedName("patientId")
    @Expose
    private String patientId;
    @SerializedName("hospitalId")
    @Expose
    private String hospitalId;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

}
