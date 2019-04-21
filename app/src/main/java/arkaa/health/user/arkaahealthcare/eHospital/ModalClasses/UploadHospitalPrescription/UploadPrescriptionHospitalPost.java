
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.UploadHospitalPrescription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadPrescriptionHospitalPost {

    @SerializedName("hospitalId")
    @Expose
    private String hospitalId;
    @SerializedName("patientId")
    @Expose
    private String patientId;
    @SerializedName("PrescriptionPath")
    @Expose
    private String prescriptionPath;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPrescriptionPath() {
        return prescriptionPath;
    }

    public void setPrescriptionPath(String prescriptionPath) {
        this.prescriptionPath = prescriptionPath;
    }

}
