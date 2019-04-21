
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation;

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
    @SerializedName("appointment_id")
    @Expose
    private Integer appointmentId;
    @SerializedName("appointment_date")
    @Expose
    private String appointmentDate;
    @SerializedName("probable_start_time")
    @Expose
    private String probableStartTime;
    @SerializedName("appointment_taken_date")
    @Expose
    private String appointmentTakenDate;
    @SerializedName("consultation_type_id")
    @Expose
    private Integer consultationTypeId;
    @SerializedName("consultation_type")
    @Expose
    private String consultationType;
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
    @SerializedName("appointment_description")
    @Expose
    private String appointmentDescription;
    @SerializedName("patient_details")
    @Expose
    private List<PatientDetail> patientDetails = null;
    @SerializedName("patient_address")
    @Expose
    private List<PatientAddress> patientAddress = null;
    @SerializedName("tests")
    @Expose
    private List<Test> tests = null;

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
        this.labUid = ((String) in.readValue((String.class.getClassLoader())));
        this.labName = ((String) in.readValue((String.class.getClassLoader())));
        this.labAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.labCity = ((String) in.readValue((String.class.getClassLoader())));
        this.labState = ((String) in.readValue((String.class.getClassLoader())));
        this.labLocation = ((String) in.readValue((String.class.getClassLoader())));
        this.userUid = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.appointmentDate = ((String) in.readValue((String.class.getClassLoader())));
        this.probableStartTime = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentTakenDate = ((String) in.readValue((String.class.getClassLoader())));
        this.consultationTypeId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.consultationType = ((String) in.readValue((String.class.getClassLoader())));
        this.prescriptionId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.prescriptionPath = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorUid = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorName = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentDescription = ((String) in.readValue((String.class.getClassLoader())));

//        in.readList(this.patientDetails, (arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.PatientDetail.class.getClassLoader()));
//        in.readList(this.patientAddress, (arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.PatientAddress.class.getClassLoader()));
//        in.readList(this.tests, (arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation.Test.class.getClassLoader()));

        this.patientDetails = ( (List<PatientDetail>) in.readArrayList(PatientDetail.class.getClassLoader())  );
        this.patientAddress = ( (List<PatientAddress>) in.readArrayList(PatientAddress.class.getClassLoader())  );
        this.tests = ( (List<Test>) in.readArrayList(Test.class.getClassLoader()) );


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

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
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

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
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
        dest.writeValue(labUid);
        dest.writeValue(labName);
        dest.writeValue(labAddress);
        dest.writeValue(labCity);
        dest.writeValue(labState);
        dest.writeValue(labLocation);
        dest.writeValue(userUid);
        dest.writeValue(appointmentId);
        dest.writeValue(appointmentDate);
        dest.writeValue(probableStartTime);
        dest.writeValue(appointmentTakenDate);
        dest.writeValue(consultationTypeId);
        dest.writeValue(consultationType);
        dest.writeValue(prescriptionId);
        dest.writeValue(prescriptionPath);
        dest.writeValue(doctorUid);
        dest.writeValue(doctorName);
        dest.writeValue(appointmentDescription);
        dest.writeList(patientDetails);
        dest.writeList(patientAddress);
        dest.writeList(tests);
    }

    public int describeContents() {
        return  0;
    }

}
