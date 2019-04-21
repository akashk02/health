
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.HospitalDetailModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalDetailsPost {

    @SerializedName("hospitalid")
    @Expose
    private String hospitalid;

    public String getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid;
    }

}
