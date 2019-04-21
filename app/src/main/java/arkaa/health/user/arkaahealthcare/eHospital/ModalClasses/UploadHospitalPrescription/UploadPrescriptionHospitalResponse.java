
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.UploadHospitalPrescription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadPrescriptionHospitalResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;

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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
