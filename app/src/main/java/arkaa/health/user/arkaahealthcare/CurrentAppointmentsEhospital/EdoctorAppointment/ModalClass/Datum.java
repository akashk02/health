
package arkaa.health.user.arkaahealthcare.CurrentAppointmentsEhospital.EdoctorAppointment.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("AppointmentId")
    @Expose
    private String appointmentId;
    @SerializedName("DoctorId")
    @Expose
    private String doctorId;
    @SerializedName("hospitalID")
    @Expose
    private String hospitalID;
    @SerializedName("HospitalName")
    @Expose
    private String hospitalName;
    @SerializedName("DoctorName")
    @Expose
    private String doctorName;
    @SerializedName("Specialization")
    @Expose
    private String specialization;
    @SerializedName("PatientId")
    @Expose
    private String patientId;
    @SerializedName("PatientName")
    @Expose
    private String patientName;
    @SerializedName("AppointmentDate")
    @Expose
    private String appointmentDate;
    @SerializedName("AppointmentTime")
    @Expose
    private String appointmentTime;
    @SerializedName("AppointmentType")
    @Expose
    private String appointmentType;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("isConfirmed")
    @Expose
    private String isConfirmed;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("paymentconfirmation")
    @Expose
    private Boolean paymentconfirmation;
    @SerializedName("FinalPrice")
    @Expose
    private String finalPrice;

    //online consultation fees

    @SerializedName("WalkInFeesWithGSt")
    @Expose
    private Double WalkInFeesWithGSt;

    @SerializedName("VideoFeesWithGSt")
    @Expose
    private Double VideoFeesWithGSt;

    @SerializedName("VoiceFeesWithGSt")
    @Expose
    private Double VoiceFeesWithGSt;

    @SerializedName("TextFeesWithGSt")
    @Expose
    private Double TextFeesWithGSt;


    //online consultation fees

    @SerializedName("DoctorImageURL")
    @Expose
    private String doctorImageURL;

    @SerializedName("doctorAlternatePhone")
    @Expose
    private String doctorAlternatePhone;

    @SerializedName("ConsultationStatus")
    @Expose
    private boolean ConsultationStatus;






    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(String isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Boolean getPaymentconfirmation() {
        return paymentconfirmation;
    }

    public void setPaymentconfirmation(Boolean paymentconfirmation) {
        this.paymentconfirmation = paymentconfirmation;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Double getWalkInFeesWithGSt() {
        return WalkInFeesWithGSt;
    }

    public void setWalkInFeesWithGSt(Double walkInFeesWithGSt) {
        WalkInFeesWithGSt = walkInFeesWithGSt;
    }

    public Double getVideoFeesWithGSt() {
        return VideoFeesWithGSt;
    }

    public void setVideoFeesWithGSt(Double videoFeesWithGSt) {
        VideoFeesWithGSt = videoFeesWithGSt;
    }

    public Double getVoiceFeesWithGSt() {
        return VoiceFeesWithGSt;
    }

    public void setVoiceFeesWithGSt(Double voiceFeesWithGSt) {
        VoiceFeesWithGSt = voiceFeesWithGSt;
    }

    public Double getTextFeesWithGSt() {
        return TextFeesWithGSt;
    }

    public void setTextFeesWithGSt(Double textFeesWithGSt) {
        TextFeesWithGSt = textFeesWithGSt;
    }

    public String getDoctorImageURL() {
        return doctorImageURL;
    }

    public void setDoctorImageURL(String doctorImageURL) {
        this.doctorImageURL = doctorImageURL;
    }

    public String getDoctorAlternatePhone() {
        return doctorAlternatePhone;
    }

    public void setDoctorAlternatePhone(String doctorAlternatePhone) {
        this.doctorAlternatePhone = doctorAlternatePhone;
    }

    public boolean isConsultationStatus() {
        return ConsultationStatus;
    }

    public void setConsultationStatus(boolean consultationStatus) {
        ConsultationStatus = consultationStatus;
    }
}


