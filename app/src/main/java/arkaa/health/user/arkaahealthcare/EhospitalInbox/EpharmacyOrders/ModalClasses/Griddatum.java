
package arkaa.health.user.arkaahealthcare.EhospitalInbox.EpharmacyOrders.ModalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Griddatum {

    @SerializedName("pts_medicine_ch_id")
    @Expose
    private Integer ptsMedicineChId;
    @SerializedName("pts_transaction_id")
    @Expose
    private String ptsTransactionId;
    @SerializedName("pts_medicine_ch_PharmacyName")
    @Expose
    private String ptsMedicineChPharmacyName;
    @SerializedName("pts_medicine_id")
    @Expose
    private Integer ptsMedicineId;
    @SerializedName("medicine_name")
    @Expose
    private String medicineName;
    @SerializedName("pts_medicine_ch_brand")
    @Expose
    private String ptsMedicineChBrand;
    @SerializedName("pts_medicine_ch_strength")
    @Expose
    private String ptsMedicineChStrength;
    @SerializedName("pts_medicine_ch_dosage")
    @Expose
    private Integer ptsMedicineChDosage;
    @SerializedName("pts_medicine_ch_days")
    @Expose
    private Integer ptsMedicineChDays;
    @SerializedName("pts_medicine_ch_qty")
    @Expose
    private double ptsMedicineChQty;
    @SerializedName("pts_medicine_ch_price")
    @Expose
    private Double ptsMedicineChPrice;
    @SerializedName("pts_medicine_ch_gst")
    @Expose
    private Integer ptsMedicineChGst;
    @SerializedName("pts_medicine_ch_discount")
    @Expose
    private Integer ptsMedicineChDiscount;
    @SerializedName("pts_medicine_ch_FinalPrice")
    @Expose
    private Double ptsMedicineChFinalPrice;
    @SerializedName("TotalFinalPrice")
    @Expose
    private Double totalFinalPrice;

    public Integer getPtsMedicineChId() {
        return ptsMedicineChId;
    }

    public void setPtsMedicineChId(Integer ptsMedicineChId) {
        this.ptsMedicineChId = ptsMedicineChId;
    }

    public String getPtsTransactionId() {
        return ptsTransactionId;
    }

    public void setPtsTransactionId(String ptsTransactionId) {
        this.ptsTransactionId = ptsTransactionId;
    }

    public String getPtsMedicineChPharmacyName() {
        return ptsMedicineChPharmacyName;
    }

    public void setPtsMedicineChPharmacyName(String ptsMedicineChPharmacyName) {
        this.ptsMedicineChPharmacyName = ptsMedicineChPharmacyName;
    }

    public Integer getPtsMedicineId() {
        return ptsMedicineId;
    }

    public void setPtsMedicineId(Integer ptsMedicineId) {
        this.ptsMedicineId = ptsMedicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getPtsMedicineChBrand() {
        return ptsMedicineChBrand;
    }

    public void setPtsMedicineChBrand(String ptsMedicineChBrand) {
        this.ptsMedicineChBrand = ptsMedicineChBrand;
    }

    public String getPtsMedicineChStrength() {
        return ptsMedicineChStrength;
    }

    public void setPtsMedicineChStrength(String ptsMedicineChStrength) {
        this.ptsMedicineChStrength = ptsMedicineChStrength;
    }

    public Integer getPtsMedicineChDosage() {
        return ptsMedicineChDosage;
    }

    public void setPtsMedicineChDosage(Integer ptsMedicineChDosage) {
        this.ptsMedicineChDosage = ptsMedicineChDosage;
    }

    public Integer getPtsMedicineChDays() {
        return ptsMedicineChDays;
    }

    public void setPtsMedicineChDays(Integer ptsMedicineChDays) {
        this.ptsMedicineChDays = ptsMedicineChDays;
    }

    public double getPtsMedicineChQty() {
        return ptsMedicineChQty;
    }

    public void setPtsMedicineChQty(double ptsMedicineChQty) {
        this.ptsMedicineChQty = ptsMedicineChQty;
    }

    public Double getPtsMedicineChPrice() {
        return ptsMedicineChPrice;
    }

    public void setPtsMedicineChPrice(Double ptsMedicineChPrice) {
        this.ptsMedicineChPrice = ptsMedicineChPrice;
    }

    public Integer getPtsMedicineChGst() {
        return ptsMedicineChGst;
    }

    public void setPtsMedicineChGst(Integer ptsMedicineChGst) {
        this.ptsMedicineChGst = ptsMedicineChGst;
    }

    public Integer getPtsMedicineChDiscount() {
        return ptsMedicineChDiscount;
    }

    public void setPtsMedicineChDiscount(Integer ptsMedicineChDiscount) {
        this.ptsMedicineChDiscount = ptsMedicineChDiscount;
    }

    public Double getPtsMedicineChFinalPrice() {
        return ptsMedicineChFinalPrice;
    }

    public void setPtsMedicineChFinalPrice(Double ptsMedicineChFinalPrice) {
        this.ptsMedicineChFinalPrice = ptsMedicineChFinalPrice;
    }

    public Double getTotalFinalPrice() {
        return totalFinalPrice;
    }

    public void setTotalFinalPrice(Double totalFinalPrice) {
        this.totalFinalPrice = totalFinalPrice;
    }

}
