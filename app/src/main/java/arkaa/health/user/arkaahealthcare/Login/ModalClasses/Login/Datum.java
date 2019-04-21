
package arkaa.health.user.arkaahealthcare.Login.ModalClasses.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("authenticate_user")
    @Expose
    private Boolean authenticateUser;

    public Boolean getAuthenticateUser() {
        return authenticateUser;
    }

    public void setAuthenticateUser(Boolean authenticateUser) {
        this.authenticateUser = authenticateUser;
    }

}
