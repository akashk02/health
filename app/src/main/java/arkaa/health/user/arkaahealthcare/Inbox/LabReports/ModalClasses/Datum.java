
package arkaa.health.user.arkaahealthcare.Inbox.LabReports.ModalClasses;

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
    @SerializedName("appointment_id")
    @Expose
    private Integer appointmentId;
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
    @SerializedName("appointment_taken_date")
    @Expose
    private String appointmentTakenDate;
    @SerializedName("consultation_type")
    @Expose
    private String consultationType;
    @SerializedName("total_cost")
    @Expose
    private Double totalCost;
    @SerializedName("prescription_path")
    @Expose
    private String prescriptionPath;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("lab_booking_type")
    @Expose
    private String labBookingType;
    @SerializedName("report_name")
    @Expose
    private String reportName;
    @SerializedName("report_path")
    @Expose
    private String reportPath;
    @SerializedName("last_updated_on")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("status")
    @Expose
    private Integer status;
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
        this.appointmentId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.labUid = ((String) in.readValue((String.class.getClassLoader())));
        this.labName = ((String) in.readValue((String.class.getClassLoader())));
        this.labAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.labCity = ((String) in.readValue((String.class.getClassLoader())));
        this.labState = ((String) in.readValue((String.class.getClassLoader())));
        this.labLocation = ((String) in.readValue((String.class.getClassLoader())));
        this.userUid = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentDate = ((String) in.readValue((String.class.getClassLoader())));
        this.probableStartTime = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentTakenDate = ((String) in.readValue((String.class.getClassLoader())));
        this.consultationType = ((String) in.readValue((String.class.getClassLoader())));
        this.totalCost = ((Double) in.readValue((Double.class.getClassLoader())));
        this.prescriptionPath = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.labBookingType = ((String) in.readValue((String.class.getClassLoader())));
        this.reportName = ((String) in.readValue((String.class.getClassLoader())));
        this.reportPath = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedOn = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
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

    public String getLabBookingType() {
        return labBookingType;
    }

    public void setLabBookingType(String labBookingType) {
        this.labBookingType = labBookingType;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(appointmentId);
        dest.writeValue(labUid);
        dest.writeValue(labName);
        dest.writeValue(labAddress);
        dest.writeValue(labCity);
        dest.writeValue(labState);
        dest.writeValue(labLocation);
        dest.writeValue(userUid);
        dest.writeValue(appointmentDate);
        dest.writeValue(probableStartTime);
        dest.writeValue(appointmentTakenDate);
        dest.writeValue(consultationType);
        dest.writeValue(totalCost);
        dest.writeValue(prescriptionPath);
        dest.writeValue(description);
        dest.writeValue(labBookingType);
        dest.writeValue(reportName);
        dest.writeValue(reportPath);
        dest.writeValue(lastUpdatedOn);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
