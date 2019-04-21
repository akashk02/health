
package arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails;

import java.util.List;
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
    @SerializedName("user_uid")
    @Expose
    private String userUid;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("appointment_status_id")
    @Expose
    private Integer appointmentStatusId;
    @SerializedName("appointment_status")
    @Expose
    private String appointmentStatus;
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
    private Double cost;

    @SerializedName("pharmacy_uid")
    @Expose
    private String pharmacyUid;
    @SerializedName("prescription_id")
    @Expose
    private Integer prescriptionId;
    @SerializedName("prescription_path")
    @Expose
    private String prescriptionPath;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("booking_type")
    @Expose
    private Integer bookingType;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
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
    @SerializedName("pharmacy_name")
    @Expose
    private String pharmacyName;
    @SerializedName("medicine_info")
    @Expose
    private List<MedicineDetail> medicineDetail = null;

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
        this.userUid = ((String) in.readValue((String.class.getClassLoader())));
        this.orderDate = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentStatusId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.appointmentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.orderTakenDate = ((String) in.readValue((String.class.getClassLoader())));
        this.consultationType = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.cost = ((Double) in.readValue((Double.class.getClassLoader())));
        this.pharmacyUid = ((String) in.readValue((String.class.getClassLoader())));
        this.prescriptionId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.prescriptionPath = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentType = ((String) in.readValue((String.class.getClassLoader())));
        this.bookingType = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
        this.locality = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.landmark = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyName = ((String) in.readValue((String.class.getClassLoader())));
      //  in.readList(this.medicineDetail, (arkaa.health.user.arkaahealthcare.Inbox.PharmacyOrders.ModalClasses.PharmacyOderDetails.MedicineDetail.class.getClassLoader()));
        this.medicineDetail = ( (List<MedicineDetail>) in.readArrayList(MedicineDetail.class.getClassLoader()) );

    }

    public Datum() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public void setAppointmentStatusId(Integer appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getPharmacyUid() {
        return pharmacyUid;
    }

    public void setPharmacyUid(String pharmacyUid) {
        this.pharmacyUid = pharmacyUid;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getBookingType() {
        return bookingType;
    }

    public void setBookingType(Integer bookingType) {
        this.bookingType = bookingType;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
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

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public List<MedicineDetail> getMedicineDetail() {
        return medicineDetail;
    }

    public void setMedicineDetail(List<MedicineDetail> medicineDetail) {
        this.medicineDetail = medicineDetail;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userUid);
        dest.writeValue(orderDate);
        dest.writeValue(appointmentStatusId);
        dest.writeValue(appointmentStatus);
        dest.writeValue(orderTakenDate);
        dest.writeValue(consultationType);
        dest.writeValue(name);
        dest.writeValue(mobile);
        dest.writeValue(email);
        dest.writeValue(description);
        dest.writeValue(cost);
        dest.writeValue(pharmacyUid);
        dest.writeValue(prescriptionId);
        dest.writeValue(prescriptionPath);
        dest.writeValue(paymentStatus);
        dest.writeValue(paymentType);
        dest.writeValue(bookingType);
        dest.writeValue(createdOn);
        dest.writeValue(locality);
        dest.writeValue(address);
        dest.writeValue(landmark);
        dest.writeValue(pincode);
        dest.writeValue(pharmacyName);
        dest.writeList(medicineDetail);
    }

    public int describeContents() {
        return  0;
    }

}
