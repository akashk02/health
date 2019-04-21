
package arkaa.health.user.arkaahealthcare.EhospitalInbox.EDoctorPrescription.ModalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("PrescriptionId")
    @Expose
    private String prescriptionId;
    @SerializedName("DoctorId")
    @Expose
    private String doctorId;
    @SerializedName("AppointmentId")
    @Expose
    private String appointmentId;
    @SerializedName("PatientId")
    @Expose
    private String patientId;
    @SerializedName("PatientName")
    @Expose
    private String patientName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("BookingDetails")
    @Expose
    private String bookingDetails;
    @SerializedName("Diagnosis")
    @Expose
    private String diagnosis;
    @SerializedName("MedicalAllergies")
    @Expose
    private String medicalAllergies;
    @SerializedName("HealthCondition")
    @Expose
    private String healthCondition;
    @SerializedName("PrescriptionPath")
    @Expose
    private String prescriptionPath;
    @SerializedName("Date")
    @Expose
    private String date;

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(String bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedicalAllergies() {
        return medicalAllergies;
    }

    public void setMedicalAllergies(String medicalAllergies) {
        this.medicalAllergies = medicalAllergies;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getPrescriptionPath() {
        return prescriptionPath;
    }

    public void setPrescriptionPath(String prescriptionPath) {
        this.prescriptionPath = prescriptionPath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
