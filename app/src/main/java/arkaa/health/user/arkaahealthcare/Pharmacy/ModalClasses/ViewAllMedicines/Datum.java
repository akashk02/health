
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ViewAllMedicines;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("pharmacy_uid")
    @Expose
    private String pharmacyUid;
    @SerializedName("pharmacy_inv_id")
    @Expose
    private Integer pharmacyInvId;
    @SerializedName("med_name")
    @Expose
    private String medName;
    @SerializedName("med_type")
    @Expose
    private String medType;
    @SerializedName("med_desc")
    @Expose
    private String medDesc;
    @SerializedName("med_dose")
    @Expose
    private String medDose;

    @SerializedName("total_with_gst")
    @Expose
    private Double medCost;


    @SerializedName("med_quantity")
    @Expose
    private Integer medQuantity;

    @SerializedName("med_manufacturer_name")
    @Expose
    private String medManufacturerName;
    @SerializedName("med_manufacture_date")
    @Expose
    private String medManufactureDate;
    @SerializedName("med_expiry_date")
    @Expose
    private String medExpiryDate;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("sub_category_id")
    @Expose
    private Integer subCategoryId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("sub_category")
    @Expose
    private String subCategory;
    @SerializedName("created_on")
    @Expose
    private String createdOn;

    @SerializedName("order_id")
    @Expose
    private String orderId;






    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;

    protected Datum(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pharmacyUid = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyInvId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medName = ((String) in.readValue((String.class.getClassLoader())));
        this.medType = ((String) in.readValue((String.class.getClassLoader())));
        this.medDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.medDose = ((String) in.readValue((String.class.getClassLoader())));
        this.medCost = ((Double) in.readValue((Double.class.getClassLoader())));
        this.medQuantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medManufacturerName = ((String) in.readValue((String.class.getClassLoader())));
        this.medManufactureDate = ((String) in.readValue((String.class.getClassLoader())));
        this.medExpiryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.subCategoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPharmacyUid() {
        return pharmacyUid;
    }

    public void setPharmacyUid(String pharmacyUid) {
        this.pharmacyUid = pharmacyUid;
    }

    public Integer getPharmacyInvId() {
        return pharmacyInvId;
    }

    public void setPharmacyInvId(Integer pharmacyInvId) {
        this.pharmacyInvId = pharmacyInvId;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
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

    public Double getMedCost() {
        return medCost;
    }

    public void setMedCost(Double medCost) {
        this.medCost = medCost;
    }

    public Integer getMedQuantity() {
        return medQuantity;
    }

    public void setMedQuantity(Integer medQuantity) {
        this.medQuantity = medQuantity;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getOrderId() {
        return orderId;
    }



    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(pharmacyUid);
        dest.writeValue(pharmacyInvId);
        dest.writeValue(medName);
        dest.writeValue(medType);
        dest.writeValue(medDesc);
        dest.writeValue(medDose);
        dest.writeValue(medCost);
        dest.writeValue(medQuantity);
        dest.writeValue(medManufacturerName);
        dest.writeValue(medManufactureDate);
        dest.writeValue(medExpiryDate);
        dest.writeValue(categoryId);
        dest.writeValue(subCategoryId);
        dest.writeValue(category);
        dest.writeValue(subCategory);
        dest.writeValue(createdOn);
    }

    public int describeContents() {
        return  0;
    }

}
