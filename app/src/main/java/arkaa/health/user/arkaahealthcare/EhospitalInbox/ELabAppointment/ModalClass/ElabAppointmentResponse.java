
package arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElabAppointmentResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("griddata")
    @Expose
    private List<Griddatum> griddata = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Griddatum> getGriddata() {
        return griddata;
    }

    public void setGriddata(List<Griddatum> griddata) {
        this.griddata = griddata;
    }

}
