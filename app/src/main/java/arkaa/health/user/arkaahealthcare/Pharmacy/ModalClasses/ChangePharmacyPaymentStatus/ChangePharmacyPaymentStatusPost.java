
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePharmacyPaymentStatusPost {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

}
