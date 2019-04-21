
package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineInfo implements Parcelable
{

    @SerializedName("sub_category")
    @Expose
    private String subCategory;

    @SerializedName("med_quantity")
    @Expose
    private Integer medicineQuantity;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("medicine_id")
    @Expose
    private Integer medicineId;
    @SerializedName("med_name")
    @Expose
    private String medName;
    @SerializedName("total_with_gst")
    @Expose
    private Double medCost;
    @SerializedName("med_manufacturer_name")
    @Expose
    private String medManufacturerName;
    @SerializedName("med_manufacture_date")
    @Expose
    private String medManufactureDate;
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
    @SerializedName("med_dose")
    @Expose
    private String medDose;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("sub_category_id")
    @Expose
    private Integer subCategoryId;
    public final static Creator<MedicineInfo> CREATOR = new Creator<MedicineInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MedicineInfo createFromParcel(Parcel in) {
            return new MedicineInfo(in);
        }

        public MedicineInfo[] newArray(int size) {
            return (new MedicineInfo[size]);
        }

    }
    ;

    protected MedicineInfo(Parcel in) {
        this.subCategory = ( (String) in.readValue(String.class.getClassLoader())   );

        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medicineQuantity=( (Integer) in.readValue((Integer.class.getClassLoader())) );
        this.medicineId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medName = ((String) in.readValue((String.class.getClassLoader())));
        this.medCost = ((Double) in.readValue((Double.class.getClassLoader())));
        this.medManufacturerName = ((String) in.readValue((String.class.getClassLoader())));
        this.medManufactureDate = ((String) in.readValue((String.class.getClassLoader())));
        this.medExpiryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.medGst = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medType = ((String) in.readValue((String.class.getClassLoader())));
        this.medDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.medDose = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.subCategoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MedicineInfo() {
    }

    public Integer getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(Integer medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

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

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public Double getMedCost() {
        return medCost;
    }

    public void setMedCost(Double medCost) {
        this.medCost = medCost;
    }

    public String getMedManufacturerName() {
        return medManufacturerName;
    }

    public void setMedManufacturerName(String medManufacturerName) {
        this.medManufacturerName = medManufacturerName;
    }

    public String getMedManufactureDate() {
        return medManufactureDate;
    }

    public void setMedManufactureDate(String medManufactureDate) {
        this.medManufactureDate = medManufactureDate;
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

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(subCategory);

        dest.writeValue(id);
        dest.writeValue(medicineQuantity);
        dest.writeValue(medicineId);
        dest.writeValue(medName);
        dest.writeValue(medCost);
        dest.writeValue(medManufacturerName);
        dest.writeValue(medManufactureDate);
        dest.writeValue(medExpiryDate);
        dest.writeValue(medGst);
        dest.writeValue(medType);
        dest.writeValue(medDesc);
        dest.writeValue(medDose);
        dest.writeValue(categoryId);
        dest.writeValue(subCategoryId);
    }

    public int describeContents() {
        return  0;
    }

}
