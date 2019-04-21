
package arkaa.health.user.arkaahealthcare.Login.ModalClasses.LoginSession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("logout_session")
    @Expose
    private String logoutSession;

    public String getLogoutSession() {
        return logoutSession;
    }

    public void setLogoutSession(String logoutSession) {
        this.logoutSession = logoutSession;
    }

}
