
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.UpdatePharmacyPayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("update_payment_in_pharmacy_order")
    @Expose
    private Integer updatePaymentInPharmacyOrder;

    public Integer getUpdatePaymentInPharmacyOrder() {
        return updatePaymentInPharmacyOrder;
    }

    public void setUpdatePaymentInPharmacyOrder(Integer updatePaymentInPharmacyOrder) {
        this.updatePaymentInPharmacyOrder = updatePaymentInPharmacyOrder;
    }

}
