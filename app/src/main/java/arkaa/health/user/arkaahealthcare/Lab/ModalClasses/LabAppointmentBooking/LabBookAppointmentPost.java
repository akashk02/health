
package arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabBookAppointmentPost implements Parcelable
{

    @SerializedName("lab_uid")
    @Expose
    private String labUid;
    @SerializedName("user_uid")
    @Expose
    private String userUid;
    @SerializedName("appointment_date")
    @Expose
    private String appointmentDate;
    @SerializedName("probable_start_time")
    @Expose
    private String probableStartTime;
    @SerializedName("actual_end_time")
    @Expose
    private String actualEndTime;
    @SerializedName("consultation_type_id")
    @Expose
    private String consultationTypeId;
    @SerializedName("total_cost")
    @Expose
    private String totalCost;
    @SerializedName("prescription_id")
    @Expose
    private String prescriptionId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("tests")
    @Expose
    private List<Integer> tests = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("email")
    @Expose
    private String email;
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
    @SerializedName("booking_type")
    @Expose
    private Integer bookingType;
    public final static Creator<LabBookAppointmentPost> CREATOR = new Creator<LabBookAppointmentPost>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LabBookAppointmentPost createFromParcel(Parcel in) {
            return new LabBookAppointmentPost(in);
        }

        public LabBookAppointmentPost[] newArray(int size) {
            return (new LabBookAppointmentPost[size]);
        }

    }
    ;

    protected LabBookAppointmentPost(Parcel in) {
        this.labUid = ((String) in.readValue((String.class.getClassLoader())));
        this.userUid = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentDate = ((String) in.readValue((String.class.getClassLoader())));
        this.probableStartTime = ((String) in.readValue((String.class.getClassLoader())));
        this.actualEndTime = ((String) in.readValue((String.class.getClassLoader())));
        this.consultationTypeId = ((String) in.readValue((String.class.getClassLoader())));
        this.totalCost = ((String) in.readValue((String.class.getClassLoader())));
        this.prescriptionId = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTypeId = ((String) in.readValue((String.class.getClassLoader())));
//        in.readList(this.tests, (Integer.class.getClassLoader()));
        this.tests = in.readArrayList(Integer.class.getClassLoader());
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.age = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.locality = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.landmark = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
        this.bookingType = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public LabBookAppointmentPost() {
    }

    public String getLabUid() {
        return labUid;
    }

    public void setLabUid(String labUid) {
        this.labUid = labUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getProbableStartTime() {
        return probableStartTime;
    }

    public void setProbableStartTime(String probableStartTime) {
        this.probableStartTime = probableStartTime;
    }

    public String getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public String getConsultationTypeId() {
        return consultationTypeId;
    }

    public void setConsultationTypeId(String consultationTypeId) {
        this.consultationTypeId = consultationTypeId;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public List<Integer> getTests() {
        return tests;
    }

    public void setTests(List<Integer> tests) {
        this.tests = tests;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getBookingType() {
        return bookingType;
    }

    public void setBookingType(Integer bookingType) {
        this.bookingType = bookingType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(labUid);
        dest.writeValue(userUid);
        dest.writeValue(appointmentDate);
        dest.writeValue(probableStartTime);
        dest.writeValue(actualEndTime);
        dest.writeValue(consultationTypeId);
        dest.writeValue(totalCost);
        dest.writeValue(prescriptionId);
        dest.writeValue(description);
        dest.writeValue(paymentTypeId);
        dest.writeList(tests);
        dest.writeValue(name);
        dest.writeValue(mobile);
        dest.writeValue(age);
        dest.writeValue(gender);
        dest.writeValue(email);
        dest.writeValue(locality);
        dest.writeValue(address);
        dest.writeValue(landmark);
        dest.writeValue(pincode);
        dest.writeValue(bookingType);
    }

    public int describeContents() {
        return  0;
    }

}
