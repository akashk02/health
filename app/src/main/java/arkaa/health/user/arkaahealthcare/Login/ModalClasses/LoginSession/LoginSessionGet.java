
package arkaa.health.user.arkaahealthcare.Login.ModalClasses.LoginSession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginSessionGet {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
