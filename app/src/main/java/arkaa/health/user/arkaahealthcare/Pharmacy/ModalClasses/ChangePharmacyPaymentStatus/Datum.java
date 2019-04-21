
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ChangePharmacyPaymentStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("change_pharmacy_order_status_userwise")
    @Expose
    private Integer changePharmacyOrderStatusUserwise;

    public Integer getChangePharmacyOrderStatusUserwise() {
        return changePharmacyOrderStatusUserwise;
    }

    public void setChangePharmacyOrderStatusUserwise(Integer changePharmacyOrderStatusUserwise) {
        this.changePharmacyOrderStatusUserwise = changePharmacyOrderStatusUserwise;
    }

}
