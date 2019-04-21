
package arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("pts_lab_id")
    @Expose
    private Integer ptsLabId;
    @SerializedName("pts_id")
    @Expose
    private String ptsId;
    @SerializedName("pts_lab_date")
    @Expose
    private String ptsLabDate;
    @SerializedName("pts_lab_deliveryTime")
    @Expose
    private String ptsLabDeliveryTime;
    @SerializedName("pts_lab_patientName")
    @Expose
    private String ptsLabPatientName;
    @SerializedName("pts_lab_mobile")
    @Expose
    private String ptsLabMobile;
    @SerializedName("pts_lab_email")
    @Expose
    private String ptsLabEmail;
    @SerializedName("pts_lab_landline")
    @Expose
    private String ptsLabLandline;
    @SerializedName("pts_lab_address")
    @Expose
    private String ptsLabAddress;
    @SerializedName("pts_lab_altMobile")
    @Expose
    private String ptsLabAltMobile;
    @SerializedName("pts_lab_altEmail")
    @Expose
    private String ptsLabAltEmail;
    @SerializedName("pts_lab_altlandline")
    @Expose
    private String ptsLabAltlandline;
    @SerializedName("labotomistName")
    @Expose
    private String labotomistName;
    @SerializedName("labotomistMobile")
    @Expose
    private String labotomistMobile;
    @SerializedName("labotomistEmail")
    @Expose
    private String labotomistEmail;
    @SerializedName("paymentId")
    @Expose
    private String paymentId;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("paymentconfirmation")
    @Expose
    private Boolean paymentconfirmation;

    public Integer getPtsLabId() {
        return ptsLabId;
    }

    public void setPtsLabId(Integer ptsLabId) {
        this.ptsLabId = ptsLabId;
    }

    public String getPtsId() {
        return ptsId;
    }

    public void setPtsId(String ptsId) {
        this.ptsId = ptsId;
    }

    public String getPtsLabDate() {
        return ptsLabDate;
    }

    public void setPtsLabDate(String ptsLabDate) {
        this.ptsLabDate = ptsLabDate;
    }

    public String getPtsLabDeliveryTime() {
        return ptsLabDeliveryTime;
    }

    public void setPtsLabDeliveryTime(String ptsLabDeliveryTime) {
        this.ptsLabDeliveryTime = ptsLabDeliveryTime;
    }

    public String getPtsLabPatientName() {
        return ptsLabPatientName;
    }

    public void setPtsLabPatientName(String ptsLabPatientName) {
        this.ptsLabPatientName = ptsLabPatientName;
    }

    public String getPtsLabMobile() {
        return ptsLabMobile;
    }

    public void setPtsLabMobile(String ptsLabMobile) {
        this.ptsLabMobile = ptsLabMobile;
    }

    public String getPtsLabEmail() {
        return ptsLabEmail;
    }

    public void setPtsLabEmail(String ptsLabEmail) {
        this.ptsLabEmail = ptsLabEmail;
    }

    public String getPtsLabLandline() {
        return ptsLabLandline;
    }

    public void setPtsLabLandline(String ptsLabLandline) {
        this.ptsLabLandline = ptsLabLandline;
    }

    public String getPtsLabAddress() {
        return ptsLabAddress;
    }

    public void setPtsLabAddress(String ptsLabAddress) {
        this.ptsLabAddress = ptsLabAddress;
    }

    public String getPtsLabAltMobile() {
        return ptsLabAltMobile;
    }

    public void setPtsLabAltMobile(String ptsLabAltMobile) {
        this.ptsLabAltMobile = ptsLabAltMobile;
    }

    public String getPtsLabAltEmail() {
        return ptsLabAltEmail;
    }

    public void setPtsLabAltEmail(String ptsLabAltEmail) {
        this.ptsLabAltEmail = ptsLabAltEmail;
    }

    public String getPtsLabAltlandline() {
        return ptsLabAltlandline;
    }

    public void setPtsLabAltlandline(String ptsLabAltlandline) {
        this.ptsLabAltlandline = ptsLabAltlandline;
    }

    public String getLabotomistName() {
        return labotomistName;
    }

    public void setLabotomistName(String labotomistName) {
        this.labotomistName = labotomistName;
    }

    public String getLabotomistMobile() {
        return labotomistMobile;
    }

    public void setLabotomistMobile(String labotomistMobile) {
        this.labotomistMobile = labotomistMobile;
    }

    public String getLabotomistEmail() {
        return labotomistEmail;
    }

    public void setLabotomistEmail(String labotomistEmail) {
        this.labotomistEmail = labotomistEmail;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Boolean getPaymentconfirmation() {
        return paymentconfirmation;
    }

    public void setPaymentconfirmation(Boolean paymentconfirmation) {
        this.paymentconfirmation = paymentconfirmation;
    }

}
