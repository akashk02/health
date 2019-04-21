
package arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.AppointmentPayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDocAppPaymentResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
