
package arkaa.health.user.arkaahealthcare.Registration.ModalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.Error;

public class SignUpResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private java.lang.Error error;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
