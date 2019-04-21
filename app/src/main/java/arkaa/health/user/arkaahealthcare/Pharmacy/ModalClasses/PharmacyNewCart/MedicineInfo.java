
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyNewCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("medicine_id")
    @Expose
    private Integer medicineId;
    @SerializedName("medicine_quantity")
    @Expose
    private Integer medicineQuantity;
    @SerializedName("med_name")
    @Expose
    private String medName;
    @SerializedName("med_cost")
    @Expose
    private Integer medCost;
    @SerializedName("med_manufacturer_name")
    @Expose
    private String medManufacturerName;
    @SerializedName("med_expiry_date")
    @Expose
    private String medExpiryDate;
    @SerializedName("med_gst")
    @Expose
    private Integer medGst;
    @SerializedName("med_type")
    @Expose
    private String medType;
    @SerializedName("med_desc")
    @Expose
    private String medDesc;
    @SerializedName("med_quantity")
    @Expose
    private Integer medQuantity;
    @SerializedName("med_dose")
    @Expose
    private String medDose;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("total_with_gst")
    @Expose
    private Double totalWithGst;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(Integer medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public Integer getMedCost() {
        return medCost;
    }

    public void setMedCost(Integer medCost) {
        this.medCost = medCost;
    }

    public String getMedManufacturerName() {
        return medManufacturerName;
    }

    public void setMedManufacturerName(String medManufacturerName) {
        this.medManufacturerName = medManufacturerName;
    }

    public String getMedExpiryDate() {
        return medExpiryDate;
    }

    public void setMedExpiryDate(String medExpiryDate) {
        this.medExpiryDate = medExpiryDate;
    }

    public Integer getMedGst() {
        return medGst;
    }

    public void setMedGst(Integer medGst) {
        this.medGst = medGst;
    }

    public String getMedType() {
        return medType;
    }

    public void setMedType(String medType) {
        this.medType = medType;
    }

    public String getMedDesc() {
        return medDesc;
    }

    public void setMedDesc(String medDesc) {
        this.medDesc = medDesc;
    }

    public Integer getMedQuantity() {
        return medQuantity;
    }

    public void setMedQuantity(Integer medQuantity) {
        this.medQuantity = medQuantity;
    }

    public String getMedDose() {
        return medDose;
    }

    public void setMedDose(String medDose) {
        this.medDose = medDose;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getTotalWithGst() {
        return totalWithGst;
    }

    public void setTotalWithGst(Double totalWithGst) {
        this.totalWithGst = totalWithGst;
    }

}
