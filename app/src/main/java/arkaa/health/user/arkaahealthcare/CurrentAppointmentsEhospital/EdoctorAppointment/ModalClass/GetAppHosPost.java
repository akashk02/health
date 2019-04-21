package arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAppHosPost {

    @SerializedName("patientId")
    @Expose
    private String patientId;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
