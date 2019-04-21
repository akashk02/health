package arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorPrescriptionFirebase {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("patient_unique_id")
    @Expose
    private String patientUniqueId;
    @SerializedName("prescription_path")
    @Expose
    private String prescriptionPath;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPatientUniqueId() {
        return patientUniqueId;
    }

    public void setPatientUniqueId(String patientUniqueId) {
        this.patientUniqueId = patientUniqueId;
    }

    public String getPrescriptionPath() {
        return prescriptionPath;
    }

    public void setPrescriptionPath(String prescriptionPath) {
        this.prescriptionPath = prescriptionPath;
    }

}
