
package arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.AppointmentPayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDocAppPaymentPost {

    @SerializedName("AppointmentId")
    @Expose
    private String appointmentId;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("paymentconfirmation")
    @Expose
    private String paymentconfirmation;
    @SerializedName("payment_id")
    @Expose
    private String paymentId;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentconfirmation() {
        return paymentconfirmation;
    }

    public void setPaymentconfirmation(String paymentconfirmation) {
        this.paymentconfirmation = paymentconfirmation;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

}
