
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacyOrderMedicinePost implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_uid")
    @Expose
    private String userUid;
    @SerializedName("pharmacy_uid")
    @Expose
    private String pharmacyUid;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("order_taken_date")
    @Expose
    private String orderTakenDate;
    @SerializedName("consultation_type")
    @Expose
    private String consultationType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("prescription_id")
    @Expose
    private String prescriptionId;
    @SerializedName("payment_status_id")
    @Expose
    private String paymentStatusId;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("bookingType")
    @Expose
    private String bookingType;
    @SerializedName("medicineDetailId")
    @Expose
    private String medicineDetailId;
    @SerializedName("pharmacy_order_id_par")
    @Expose
    private String pharmacyOrderIdPar;
    @SerializedName("medicines")
    @Expose
    private List<Medicine> medicines = null;
    @SerializedName("patientId")
    @Expose
    private String patientId;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    public final static Creator<PharmacyOrderMedicinePost> CREATOR = new Creator<PharmacyOrderMedicinePost>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PharmacyOrderMedicinePost createFromParcel(Parcel in) {
            return new PharmacyOrderMedicinePost(in);
        }

        public PharmacyOrderMedicinePost[] newArray(int size) {
            return (new PharmacyOrderMedicinePost[size]);
        }

    }
    ;

    protected PharmacyOrderMedicinePost(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.userUid = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyUid = ((String) in.readValue((String.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        this.orderTakenDate = ((String) in.readValue((String.class.getClassLoader())));
        this.consultationType = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.cost = ((String) in.readValue((String.class.getClassLoader())));
        this.prescriptionId = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentStatusId = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTypeId = ((String) in.readValue((String.class.getClassLoader())));
        this.bookingType = ((String) in.readValue((String.class.getClassLoader())));
        this.medicineDetailId = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyOrderIdPar = ((String) in.readValue((String.class.getClassLoader())));
//        in.readList(this.medicines, (arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.PharmacyOrderMedicine.Medicine.class.getClassLoader()));
        this.medicines = ( (ArrayList<Medicine>) in.readArrayList(Medicine.class.getClassLoader()) );
        this.patientId = ((String) in.readValue((String.class.getClassLoader())));
        this.locality = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.landmark = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PharmacyOrderMedicinePost() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getPharmacyUid() {
        return pharmacyUid;
    }

    public void setPharmacyUid(String pharmacyUid) {
        this.pharmacyUid = pharmacyUid;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTakenDate() {
        return orderTakenDate;
    }

    public void setOrderTakenDate(String orderTakenDate) {
        this.orderTakenDate = orderTakenDate;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(String paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getMedicineDetailId() {
        return medicineDetailId;
    }

    public void setMedicineDetailId(String medicineDetailId) {
        this.medicineDetailId = medicineDetailId;
    }

    public String getPharmacyOrderIdPar() {
        return pharmacyOrderIdPar;
    }

    public void setPharmacyOrderIdPar(String pharmacyOrderIdPar) {
        this.pharmacyOrderIdPar = pharmacyOrderIdPar;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userUid);
        dest.writeValue(pharmacyUid);
        dest.writeValue(orderDate);
        dest.writeValue(orderTakenDate);
        dest.writeValue(consultationType);
        dest.writeValue(name);
        dest.writeValue(mobile);
        dest.writeValue(email);
        dest.writeValue(description);
        dest.writeValue(cost);
        dest.writeValue(prescriptionId);
        dest.writeValue(paymentStatusId);
        dest.writeValue(paymentTypeId);
        dest.writeValue(bookingType);
        dest.writeValue(medicineDetailId);
        dest.writeValue(pharmacyOrderIdPar);
        dest.writeList(medicines);
        dest.writeValue(patientId);
        dest.writeValue(locality);
        dest.writeValue(address);
        dest.writeValue(landmark);
        dest.writeValue(pincode);
    }

    public int describeContents() {
        return  0;
    }

}
