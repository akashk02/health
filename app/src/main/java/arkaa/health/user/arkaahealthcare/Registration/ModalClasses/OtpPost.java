
package arkaa.health.user.arkaahealthcare.Registration.ModalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpPost {

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("otp")
    @Expose
    private String otp;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}
