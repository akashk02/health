
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElistOfDocPost {

    @SerializedName("hospitalId")
    @Expose
    private String hospitalId;
    @SerializedName("SpecializationId")
    @Expose
    private String specializationId;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(String specializationId) {
        this.specializationId = specializationId;
    }

}
