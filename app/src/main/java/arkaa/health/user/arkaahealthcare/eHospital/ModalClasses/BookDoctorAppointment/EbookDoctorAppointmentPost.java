
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.BookDoctorAppointment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EbookDoctorAppointmentPost {

    @SerializedName("doctorId")
    @Expose
    private String doctorId;
    @SerializedName("patientId")
    @Expose
    private String patientId;
    @SerializedName("patientName")
    @Expose
    private String patientName;
    @SerializedName("AppointmentDate")
    @Expose
    private String appointmentDate;
    @SerializedName("AppointmentTime")
    @Expose
    private String appointmentTime;
    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("emailId")
    @Expose
    private String emailId;

    @SerializedName("AppointmentIdType")
    @Expose
    private String AppointmentIdType;




    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAppointmentIdType() {
        return AppointmentIdType;
    }

    public void setAppointmentIdType(String appointmentIdType) {
        AppointmentIdType = appointmentIdType;
    }
}
