
package arkaa.health.user.arkaahealthcare.Lab.ModalClasses.UpdateLabPayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("update_payment_in_lab_order")
    @Expose
    private Integer updatePaymentInLabOrder;

    public Integer getUpdatePaymentInLabOrder() {
        return updatePaymentInLabOrder;
    }

    public void setUpdatePaymentInLabOrder(Integer updatePaymentInLabOrder) {
        this.updatePaymentInLabOrder = updatePaymentInLabOrder;
    }

}
