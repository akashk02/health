
package arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElistOfDocAppPtsPost {

    @SerializedName("ptsId")
    @Expose
    private String ptsId;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;

    public String getPtsId() {
        return ptsId;
    }

    public void setPtsId(String ptsId) {
        this.ptsId = ptsId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
