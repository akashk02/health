
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfHospitalModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllHospitalsPost {

    @SerializedName("approval")
    @Expose
    private String approval;

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

}
