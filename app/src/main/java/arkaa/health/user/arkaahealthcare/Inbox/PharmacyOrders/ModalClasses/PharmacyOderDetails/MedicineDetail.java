
package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineDetail implements Parcelable
{

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
    @SerializedName("total_with_gst")
    @Expose
    private Double medCost;
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
    public final static Creator<MedicineDetail> CREATOR = new Creator<MedicineDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MedicineDetail createFromParcel(Parcel in) {
            return new MedicineDetail(in);
        }

        public MedicineDetail[] newArray(int size) {
            return (new MedicineDetail[size]);
        }

    }
    ;

    protected MedicineDetail(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medicineId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medicineQuantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medName = ((String) in.readValue((String.class.getClassLoader())));
        this.medCost = ((Double) in.readValue((Integer.class.getClassLoader())));
        this.medManufacturerName = ((String) in.readValue((String.class.getClassLoader())));
        this.medExpiryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.medGst = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medType = ((String) in.readValue((String.class.getClassLoader())));
        this.medDesc = ((String) in.readValue((String.class.getClassLoader())));
        this.medQuantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medDose = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MedicineDetail() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(medicineId);
        dest.writeValue(medicineQuantity);
        dest.writeValue(medName);
        dest.writeValue(medCost);
        dest.writeValue(medManufacturerName);
        dest.writeValue(medExpiryDate);
        dest.writeValue(medGst);
        dest.writeValue(medType);
        dest.writeValue(medDesc);
        dest.writeValue(medQuantity);
        dest.writeValue(medDose);
        dest.writeValue(categoryId);
    }

    public int describeContents() {
        return  0;
    }

}
