
package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{


    @SerializedName("quote_id")
    @Expose
    private Integer quoteId;



    @SerializedName("quote_cost")

    @Expose
    private Double quoteCost;
    @SerializedName("quote_description")
    @Expose
    private String quoteDescription;
    @SerializedName("quote_status")
    @Expose
    private Integer quoteStatus;
    @SerializedName("pharmacy_uid")
    @Expose
    private String pharmacyUid;
    @SerializedName("pharmacy_name")
    @Expose
    private String pharmacyName;
    @SerializedName("user_uid")
    @Expose
    private String userUid;
    @SerializedName("pharmacy_order_id")
    @Expose
    private Integer pharmacyOrderId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("prescription_id")
    @Expose
    private Integer prescriptionId;
    @SerializedName("prescription_path")
    @Expose
    private String prescriptionPath;
    @SerializedName("doctor_uid")
    @Expose
    private String doctorUid;
    @SerializedName("doctor_name")
    @Expose
    private String doctorName;
    @SerializedName("order_description")
    @Expose
    private String orderDescription;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("last_updated_on")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("medicine_info")
    @Expose
    private List<MedicineInfo> medicineInfo = null;

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
        this.quoteId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.quoteCost = ((Double) in.readValue((Double.class.getClassLoader())));
        this.quoteDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.quoteStatus = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pharmacyUid = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyName = ((String) in.readValue((String.class.getClassLoader())));
        this.userUid = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyOrderId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        this.prescriptionId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.prescriptionPath = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorUid = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorName = ((String) in.readValue((String.class.getClassLoader())));
        this.orderDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedOn = ((String) in.readValue((String.class.getClassLoader())));
       // in.readList(this.medicineInfo, (arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.MedicineInfo.class.getClassLoader()));
        this.medicineInfo = ( (List<MedicineInfo>) in.readArrayList(arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyQuatationDisplay.MedicineInfo.class.getClassLoader()) );
    }

    public Datum() {
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public Double getQuoteCost() {
        return quoteCost;
    }

    public void setQuoteCost(Double quoteCost) {
        this.quoteCost = quoteCost;
    }

    public String getQuoteDescription() {
        return quoteDescription;
    }

    public void setQuoteDescription(String quoteDescription) {
        this.quoteDescription = quoteDescription;
    }

    public Integer getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(Integer quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public String getPharmacyUid() {
        return pharmacyUid;
    }

    public void setPharmacyUid(String pharmacyUid) {
        this.pharmacyUid = pharmacyUid;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public Integer getPharmacyOrderId() {
        return pharmacyOrderId;
    }

    public void setPharmacyOrderId(Integer pharmacyOrderId) {
        this.pharmacyOrderId = pharmacyOrderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionPath() {
        return prescriptionPath;
    }

    public void setPrescriptionPath(String prescriptionPath) {
        this.prescriptionPath = prescriptionPath;
    }

    public String getDoctorUid() {
        return doctorUid;
    }

    public void setDoctorUid(String doctorUid) {
        this.doctorUid = doctorUid;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<MedicineInfo> getMedicineInfo() {
        return medicineInfo;
    }

    public void setMedicineInfo(List<MedicineInfo> medicineInfo) {
        this.medicineInfo = medicineInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(quoteId);
        dest.writeValue(quoteCost);
        dest.writeValue(quoteDescription);
        dest.writeValue(quoteStatus);
        dest.writeValue(pharmacyUid);
        dest.writeValue(pharmacyName);
        dest.writeValue(userUid);
        dest.writeValue(pharmacyOrderId);
        dest.writeValue(orderDate);
        dest.writeValue(prescriptionId);
        dest.writeValue(prescriptionPath);
        dest.writeValue(doctorUid);
        dest.writeValue(doctorName);
        dest.writeValue(orderDescription);
        dest.writeValue(firstName);
        dest.writeValue(email);
        dest.writeValue(lastUpdatedOn);
        dest.writeList(medicineInfo);

    }

    public int describeContents() {
        return  0;
    }

}
