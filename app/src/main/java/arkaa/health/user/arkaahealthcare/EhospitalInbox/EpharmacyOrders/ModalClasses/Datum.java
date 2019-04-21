
package arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("pts_medicine_id")
    @Expose
    private Integer ptsMedicineId;
    @SerializedName("pts_id")
    @Expose
    private String ptsId;
    @SerializedName("pts_medicine_date")
    @Expose
    private String ptsMedicineDate;
    @SerializedName("pts_medicine_deliveryTime")
    @Expose
    private String ptsMedicineDeliveryTime;
    @SerializedName("pts_medicine_patientName")
    @Expose
    private String ptsMedicinePatientName;
    @SerializedName("pts_medicine_mobile")
    @Expose
    private String ptsMedicineMobile;
    @SerializedName("pts_medicine_landline")
    @Expose
    private String ptsMedicineLandline;
    @SerializedName("pts_medicine_email")
    @Expose
    private String ptsMedicineEmail;
    @SerializedName("pts_medicine_address")
    @Expose
    private String ptsMedicineAddress;
    @SerializedName("pts_medicine_altMobile")
    @Expose
    private String ptsMedicineAltMobile;
    @SerializedName("pts_medicine_altEmail")
    @Expose
    private String ptsMedicineAltEmail;
    @SerializedName("pts_medicine_altlandline")
    @Expose
    private String ptsMedicineAltlandline;
    @SerializedName("paymentId")
    @Expose
    private String paymentId;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("paymentconfirmation")
    @Expose
    private Boolean paymentconfirmation;

    public Integer getPtsMedicineId() {
        return ptsMedicineId;
    }

    public void setPtsMedicineId(Integer ptsMedicineId) {
        this.ptsMedicineId = ptsMedicineId;
    }

    public String getPtsId() {
        return ptsId;
    }

    public void setPtsId(String ptsId) {
        this.ptsId = ptsId;
    }

    public String getPtsMedicineDate() {
        return ptsMedicineDate;
    }

    public void setPtsMedicineDate(String ptsMedicineDate) {
        this.ptsMedicineDate = ptsMedicineDate;
    }

    public String getPtsMedicineDeliveryTime() {
        return ptsMedicineDeliveryTime;
    }

    public void setPtsMedicineDeliveryTime(String ptsMedicineDeliveryTime) {
        this.ptsMedicineDeliveryTime = ptsMedicineDeliveryTime;
    }

    public String getPtsMedicinePatientName() {
        return ptsMedicinePatientName;
    }

    public void setPtsMedicinePatientName(String ptsMedicinePatientName) {
        this.ptsMedicinePatientName = ptsMedicinePatientName;
    }

    public String getPtsMedicineMobile() {
        return ptsMedicineMobile;
    }

    public void setPtsMedicineMobile(String ptsMedicineMobile) {
        this.ptsMedicineMobile = ptsMedicineMobile;
    }

    public String getPtsMedicineLandline() {
        return ptsMedicineLandline;
    }

    public void setPtsMedicineLandline(String ptsMedicineLandline) {
        this.ptsMedicineLandline = ptsMedicineLandline;
    }

    public String getPtsMedicineEmail() {
        return ptsMedicineEmail;
    }

    public void setPtsMedicineEmail(String ptsMedicineEmail) {
        this.ptsMedicineEmail = ptsMedicineEmail;
    }

    public String getPtsMedicineAddress() {
        return ptsMedicineAddress;
    }

    public void setPtsMedicineAddress(String ptsMedicineAddress) {
        this.ptsMedicineAddress = ptsMedicineAddress;
    }

    public String getPtsMedicineAltMobile() {
        return ptsMedicineAltMobile;
    }

    public void setPtsMedicineAltMobile(String ptsMedicineAltMobile) {
        this.ptsMedicineAltMobile = ptsMedicineAltMobile;
    }

    public String getPtsMedicineAltEmail() {
        return ptsMedicineAltEmail;
    }

    public void setPtsMedicineAltEmail(String ptsMedicineAltEmail) {
        this.ptsMedicineAltEmail = ptsMedicineAltEmail;
    }

    public String getPtsMedicineAltlandline() {
        return ptsMedicineAltlandline;
    }

    public void setPtsMedicineAltlandline(String ptsMedicineAltlandline) {
        this.ptsMedicineAltlandline = ptsMedicineAltlandline;
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
