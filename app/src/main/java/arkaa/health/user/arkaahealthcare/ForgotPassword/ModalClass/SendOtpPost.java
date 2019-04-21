
package arkaa.health.user.arkaahealthcare.ForgotPassword.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendOtpPost {

    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
