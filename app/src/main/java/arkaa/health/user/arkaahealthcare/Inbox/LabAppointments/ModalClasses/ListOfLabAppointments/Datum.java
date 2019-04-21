
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments;

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
    @SerializedName("lab_uid")
    @Expose
    private String labUid;
    @SerializedName("lab_name")
    @Expose
    private String labName;
    @SerializedName("lab_address")
    @Expose
    private String labAddress;
    @SerializedName("lab_city")
    @Expose
    private String labCity;
    @SerializedName("lab_state")
    @Expose
    private String labState;
    @SerializedName("lab_location")
    @Expose
    private String labLocation;
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
    @SerializedName("appointment_taken_date")
    @Expose
    private String appointmentTakenDate;
    @SerializedName("consultation_type_id")
    @Expose
    private Integer consultationTypeId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("booking_type_id")
    @Expose
    private Integer bookingTypeId;
    @SerializedName("lab_booking_type")
    @Expose
    private String labBookingType;
    @SerializedName("total_cost")
    @Expose
    private Double totalCost;
    @SerializedName("prescription_id")
    @Expose
    private Integer prescriptionId;
    @SerializedName("prescription_path")
    @Expose
    private String prescriptionPath;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("appointment_status_id")
    @Expose
    private Integer appointmentStatusId;
    @SerializedName("appointment_status")
    @Expose
    private String appointmentStatus;
    @SerializedName("payment_status_id")
    @Expose
    private Integer paymentStatusId;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("payment_type_id")
    @Expose
    private Integer paymentTypeId;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("tests")
    @Expose
    private List<Test> tests = null;
    @SerializedName("patient_details")
    @Expose
    private List<PatientDetail> patientDetails = null;
    @SerializedName("patient_address")
    @Expose
    private List<PatientAddress> patientAddress = null;
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
        this.labUid = ((String) in.readValue((String.class.getClassLoader())));
        this.labName = ((String) in.readValue((String.class.getClassLoader())));
        this.labAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.labCity = ((String) in.readValue((String.class.getClassLoader())));
        this.labState = ((String) in.readValue((String.class.getClassLoader())));
        this.labLocation = ((String) in.readValue((String.class.getClassLoader())));
        this.userUid = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentDate = ((String) in.readValue((String.class.getClassLoader())));
        this.probableStartTime = ((String) in.readValue((String.class.getClassLoader())));
        this.actualEndTime = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentTakenDate = ((String) in.readValue((String.class.getClassLoader())));
        this.consultationTypeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.bookingTypeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.labBookingType = ((String) in.readValue((String.class.getClassLoader())));
        this.totalCost = ((Double) in.readValue((Double.class.getClassLoader())));
        this.prescriptionId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.prescriptionPath = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentStatusId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.appointmentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentStatusId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.paymentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentTypeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.paymentType = ((String) in.readValue((String.class.getClassLoader())));

//        in.readList(this.tests, (arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.Test.class.getClassLoader()));
//        in.readList(this.patientDetails, (arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.PatientDetail.class.getClassLoader()));
//        in.readList(this.patientAddress, (arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments.PatientAddress.class.getClassLoader()));

        this.tests = ( (List<Test>) in.readArrayList(Test.class.getClassLoader()) );
        this.patientDetails = ( (List<PatientDetail>) in.readArrayList(PatientDetail.class.getClassLoader()) );
        this.patientAddress = ( (List<PatientAddress>) in.readArrayList(PatientAddress.class.getClassLoader()) );
    }

    public Datum() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabUid() {
        return labUid;
    }

    public void setLabUid(String labUid) {
        this.labUid = labUid;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabAddress() {
        return labAddress;
    }

    public void setLabAddress(String labAddress) {
        this.labAddress = labAddress;
    }

    public String getLabCity() {
        return labCity;
    }

    public void setLabCity(String labCity) {
        this.labCity = labCity;
    }

    public String getLabState() {
        return labState;
    }

    public void setLabState(String labState) {
        this.labState = labState;
    }

    public String getLabLocation() {
        return labLocation;
    }

    public void setLabLocation(String labLocation) {
        this.labLocation = labLocation;
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

    public String getAppointmentTakenDate() {
        return appointmentTakenDate;
    }

    public void setAppointmentTakenDate(String appointmentTakenDate) {
        this.appointmentTakenDate = appointmentTakenDate;
    }

    public Integer getConsultationTypeId() {
        return consultationTypeId;
    }

    public void setConsultationTypeId(Integer consultationTypeId) {
        this.consultationTypeId = consultationTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBookingTypeId() {
        return bookingTypeId;
    }

    public void setBookingTypeId(Integer bookingTypeId) {
        this.bookingTypeId = bookingTypeId;
    }

    public String getLabBookingType() {
        return labBookingType;
    }

    public void setLabBookingType(String labBookingType) {
        this.labBookingType = labBookingType;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(Integer paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<PatientDetail> getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(List<PatientDetail> patientDetails) {
        this.patientDetails = patientDetails;
    }

    public List<PatientAddress> getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(List<PatientAddress> patientAddress) {
        this.patientAddress = patientAddress;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(labUid);
        dest.writeValue(labName);
        dest.writeValue(labAddress);
        dest.writeValue(labCity);
        dest.writeValue(labState);
        dest.writeValue(labLocation);
        dest.writeValue(userUid);
        dest.writeValue(appointmentDate);
        dest.writeValue(probableStartTime);
        dest.writeValue(actualEndTime);
        dest.writeValue(appointmentTakenDate);
        dest.writeValue(consultationTypeId);
        dest.writeValue(type);
        dest.writeValue(bookingTypeId);
        dest.writeValue(labBookingType);
        dest.writeValue(totalCost);
        dest.writeValue(prescriptionId);
        dest.writeValue(prescriptionPath);
        dest.writeValue(description);
        dest.writeValue(appointmentStatusId);
        dest.writeValue(appointmentStatus);
        dest.writeValue(paymentStatusId);
        dest.writeValue(paymentStatus);
        dest.writeValue(paymentTypeId);
        dest.writeValue(paymentType);
        dest.writeList(tests);
        dest.writeList(patientDetails);
        dest.writeList(patientAddress);
    }

    public int describeContents() {
        return  0;
    }

}
