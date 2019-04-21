
package arkaa.health.user.arkaahealthcare.EhospitalInbox.ModalClass.ptsPayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EhosChPayStatusPost {

    @SerializedName("PtsId")
    @Expose
    private String ptsId;
    @SerializedName("pts_medicine_id")
    @Expose
    private String ptsMedicineId;
    @SerializedName("pts_lab_id")
    @Expose
    private String ptsLabId;
    @SerializedName("pts_venderProducts_id")
    @Expose
    private String ptsVenderProductsId;
    @SerializedName("pts_venderservice_id")
    @Expose
    private String ptsVenderserviceId;
    @SerializedName("pts_doctorconsult_ch_id")
    @Expose
    private String ptsDoctorconsultChId;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("paymentconfirmation")
    @Expose
    private String paymentconfirmation;

    @SerializedName("payment_id")
    @Expose
    private String paymentId;

    public String getPtsId() {
        return ptsId;
    }

    public void setPtsId(String ptsId) {
        this.ptsId = ptsId;
    }

    public String getPtsMedicineId() {
        return ptsMedicineId;
    }

    public void setPtsMedicineId(String ptsMedicineId) {
        this.ptsMedicineId = ptsMedicineId;
    }

    public String getPtsLabId() {
        return ptsLabId;
    }

    public void setPtsLabId(String ptsLabId) {
        this.ptsLabId = ptsLabId;
    }

    public String getPtsVenderProductsId() {
        return ptsVenderProductsId;
    }

    public void setPtsVenderProductsId(String ptsVenderProductsId) {
        this.ptsVenderProductsId = ptsVenderProductsId;
    }

    public String getPtsVenderserviceId() {
        return ptsVenderserviceId;
    }

    public void setPtsVenderserviceId(String ptsVenderserviceId) {
        this.ptsVenderserviceId = ptsVenderserviceId;
    }

    public String getPtsDoctorconsultChId() {
        return ptsDoctorconsultChId;
    }

    public void setPtsDoctorconsultChId(String ptsDoctorconsultChId) {
        this.ptsDoctorconsultChId = ptsDoctorconsultChId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentconfirmation() {
        return paymentconfirmation;
    }

    public void setPaymentconfirmation(String paymentconfirmation) {
        this.paymentconfirmation = paymentconfirmation;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
