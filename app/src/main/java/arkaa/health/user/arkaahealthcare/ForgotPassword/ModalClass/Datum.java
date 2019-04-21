
package arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("authenticate_mobile_forgotpassword")
    @Expose
    private Boolean authenticateMobileForgotpassword;

    public Boolean getAuthenticateMobileForgotpassword() {
        return authenticateMobileForgotpassword;
    }

    public void setAuthenticateMobileForgotpassword(Boolean authenticateMobileForgotpassword) {
        this.authenticateMobileForgotpassword = authenticateMobileForgotpassword;
    }

}
