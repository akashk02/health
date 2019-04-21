
package arkaa.health.user.arkaahealthcare.EhospitalInbox.ElistOfPts.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("pts_id")
    @Expose
    private String ptsId;
    @SerializedName("pts_transaction_id")
    @Expose
    private String ptsTransactionId;
    @SerializedName("pts_patientUID")
    @Expose
    private String ptsPatientUID;
    @SerializedName("hospitalId")
    @Expose
    private String hospitalId;
    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("hospitalName")
    @Expose
    private String hospitalName;

    public String getPtsId() {
        return ptsId;
    }

    public void setPtsId(String ptsId) {
        this.ptsId = ptsId;
    }

    public String getPtsTransactionId() {
        return ptsTransactionId;
    }

    public void setPtsTransactionId(String ptsTransactionId) {
        this.ptsTransactionId = ptsTransactionId;
    }

    public String getPtsPatientUID() {
        return ptsPatientUID;
    }

    public void setPtsPatientUID(String ptsPatientUID) {
        this.ptsPatientUID = ptsPatientUID;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
