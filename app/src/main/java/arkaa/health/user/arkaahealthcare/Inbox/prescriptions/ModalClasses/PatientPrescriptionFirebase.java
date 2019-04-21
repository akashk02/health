package arkaa.health.user.arkaahealthcare.Inbox.prescriptions.ModalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientPrescriptionFirebase {

    @SerializedName("count")
    @Expose
    private Boolean count;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("doctor_unique_id")
    @Expose
    private String doctorUniqueId;
    @SerializedName("prescription_path")
    @Expose
    private String prescriptionPath;

    public Boolean getCount() {
        return count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctorUniqueId() {
        return doctorUniqueId;
    }

    public void setDoctorUniqueId(String doctorUniqueId) {
        this.doctorUniqueId = doctorUniqueId;
    }

    public String getPrescriptionPath() {
        return prescriptionPath;
    }

    public void setPrescriptionPath(String prescriptionPath) {
        this.prescriptionPath = prescriptionPath;
    }

}
