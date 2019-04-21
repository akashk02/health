
package arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

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
    @SerializedName("office_id")
    @Expose
    private Integer officeId;
    @SerializedName("doctor_id")
    @Expose
    private String doctorId;
    @SerializedName("doctor_name")
    @Expose
    private String doctorName;
    @SerializedName("degree")
    @Expose
    private List<Degree> degree = null;
    @SerializedName("specialization")
    @Expose
    private List<Specialization> specialization = null;
    @SerializedName("total_experience")
    @Expose
    private String totalExperience;
    @SerializedName("clinic_details")
    @Expose
    private List<ClinicDetail> clinicDetails = null;
    @SerializedName("appointment_date")
    @Expose
    private String appointmentDate;
    @SerializedName("probable_start_time")
    @Expose
    private String probableStartTime;
    @SerializedName("actual_end_time")
    @Expose
    private String actualEndTime;
    @SerializedName("appointment_status")
    @Expose
    private String appointmentStatus;
    @SerializedName("appointment_taken_date")
    @Expose
    private String appointmentTakenDate;
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

    @SerializedName("order_id")
    @Expose
    private String orderId;

    @SerializedName("payment_status_id")
    @Expose
    private int paymentStatusId;

    @SerializedName("payment_type_id")
    @Expose
    private int paymentTypeId;

    @SerializedName("total_fees")
    @Expose
    private Double totalFees;

    @SerializedName("profile_pic_path")
    @Expose
    private String profilePicPath;

    @SerializedName("DoctorAlternatePhone")
    @Expose
    private String doctorAlternatePhone;



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
        this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorAlternatePhone = ((String) in.readValue((String.class.getClassLoader())));

        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userUid = ((String) in.readValue((String.class.getClassLoader())));
        this.officeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.doctorId = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorName = ((String) in.readValue((String.class.getClassLoader())));
        this.degree = (List<Degree>) in.readArrayList (Degree.class.getClassLoader());
        this.specialization = (List<Specialization>) in.readArrayList(Specialization.class.getClassLoader());
        this.totalExperience = ((String) in.readValue((String.class.getClassLoader())));
        this.clinicDetails = (List<ClinicDetail>) in.readArrayList (ClinicDetail.class.getClassLoader());
        this.appointmentDate = ((String) in.readValue((String.class.getClassLoader())));
        this.probableStartTime = ((String) in.readValue((String.class.getClassLoader())));
        this.actualEndTime = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentTakenDate = ((String) in.readValue((String.class.getClassLoader())));
        this.consultationType = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
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

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public List<Degree> getDegree() {
        return degree;
    }

    public void setDegree(List<Degree> degree) {
        this.degree = degree;
    }

    public List<Specialization> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Specialization> specialization) {
        this.specialization = specialization;
    }

    public String getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        this.totalExperience = totalExperience;
    }

    public List<ClinicDetail> getClinicDetails() {
        return clinicDetails;
    }

    public void setClinicDetails(List<ClinicDetail> clinicDetails) {
        this.clinicDetails = clinicDetails;
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

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentTakenDate() {
        return appointmentTakenDate;
    }

    public void setAppointmentTakenDate(String appointmentTakenDate) {
        this.appointmentTakenDate = appointmentTakenDate;
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

    public Double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(Double totalFees) {
        this.totalFees = totalFees;
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

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public String getDoctorAlternatePhone() {
        return doctorAlternatePhone;
    }

    public void setDoctorAlternatePhone(String doctorAlternatePhone) {
        this.doctorAlternatePhone = doctorAlternatePhone;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(profilePicPath);
        dest.writeValue(doctorAlternatePhone);


        dest.writeValue(id);
        dest.writeValue(userUid);
        dest.writeValue(officeId);
        dest.writeValue(doctorId);
        dest.writeValue(doctorName);
        dest.writeList(degree);
        dest.writeList(specialization);
        dest.writeValue(totalExperience);
        dest.writeList(clinicDetails);
        dest.writeValue(appointmentDate);
        dest.writeValue(probableStartTime);
        dest.writeValue(actualEndTime);
        dest.writeValue(appointmentStatus);
        dest.writeValue(appointmentTakenDate);
        dest.writeValue(consultationType);
        dest.writeValue(name);
        dest.writeValue(mobile);
        dest.writeValue(email);
    }

    public int describeContents() {
        return  0;
    }

}
