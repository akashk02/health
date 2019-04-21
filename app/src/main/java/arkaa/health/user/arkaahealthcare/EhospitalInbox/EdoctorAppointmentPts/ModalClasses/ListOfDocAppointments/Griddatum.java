
package arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Griddatum implements Parcelable
{

    @SerializedName("pts_doctorconsult_ch_id")
    @Expose
    private Integer ptsDoctorconsultChId;
    @SerializedName("pts_transaction_id")
    @Expose
    private String ptsTransactionId;
    @SerializedName("pts_doctorconsult_id")
    @Expose
    private Integer ptsDoctorconsultId;
    @SerializedName("doctorId")
    @Expose
    private String doctorId;
    @SerializedName("doctorname")
    @Expose
    private String doctorname;
    @SerializedName("specialization")
    @Expose
    private String specialization;
    @SerializedName("pts_doctorconsult_ch_services")
    @Expose
    private String ptsDoctorconsultChServices;
    @SerializedName("pts_doctorconsult_ch_details")
    @Expose
    private String ptsDoctorconsultChDetails;
    @SerializedName("pts_doctorconsult_ch_date")
    @Expose
    private String ptsDoctorconsultChDate;
    @SerializedName("pts_doctorconsult_ch_scheduleWeekDay")
    @Expose
    private String ptsDoctorconsultChScheduleWeekDay;
    @SerializedName("pts_doctorconsult_ch_timeSlots")
    @Expose
    private String ptsDoctorconsultChTimeSlots;
    @SerializedName("pts_doctorconsult_ch_consultType")
    @Expose
    private String ptsDoctorconsultChConsultType;
    @SerializedName("pts_doctorconsult_ch_consultMode")
    @Expose
    private String ptsDoctorconsultChConsultMode;
    @SerializedName("pts_doctorconsult_ch_sessions")
    @Expose
    private String ptsDoctorconsultChSessions;
    @SerializedName("pts_doctorconsult_ch_cost")
    @Expose
    private Integer ptsDoctorconsultChCost;
    @SerializedName("pts_doctorconsult_ch_gstTax")
    @Expose
    private Integer ptsDoctorconsultChGstTax;
    @SerializedName("pts_doctorconsult_ch_discount")
    @Expose
    private Integer ptsDoctorconsultChDiscount;
    @SerializedName("pts_doctorconsult_ch_serviceHireType")
    @Expose
    private String ptsDoctorconsultChServiceHireType;
    @SerializedName("pts_doctorconsult_ch_homeservicePerson")
    @Expose
    private String ptsDoctorconsultChHomeservicePerson;
    @SerializedName("pts_doctorconsult_ch_homeservicePersonMobile")
    @Expose
    private String ptsDoctorconsultChHomeservicePersonMobile;

    @SerializedName("paymentId")
    @Expose
    private String paymentId;

    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;

    @SerializedName("paymentconfirmation")
    @Expose
    private Boolean paymentConfirmation;

    @SerializedName("pts_doctorconsult_ch_FinalPrice")
    @Expose
    private Double doctorFees;

    @SerializedName("ProfilePath")
    @Expose
    private String doctorProfilePath;






    public final static Creator<Griddatum> CREATOR = new Creator<Griddatum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Griddatum createFromParcel(Parcel in) {
            return new Griddatum(in);
        }

        public Griddatum[] newArray(int size) {
            return (new Griddatum[size]);
        }

    }
    ;

    protected Griddatum(Parcel in) {
        this.ptsDoctorconsultChId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ptsTransactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.doctorId = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorname = ((String) in.readValue((String.class.getClassLoader())));
        this.specialization = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChServices = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChDetails = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChDate = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChScheduleWeekDay = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChTimeSlots = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChConsultType = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChConsultMode = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChSessions = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChCost = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ptsDoctorconsultChGstTax = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ptsDoctorconsultChDiscount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ptsDoctorconsultChServiceHireType = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChHomeservicePerson = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultChHomeservicePersonMobile = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Griddatum() {
    }

    public Integer getPtsDoctorconsultChId() {
        return ptsDoctorconsultChId;
    }

    public void setPtsDoctorconsultChId(Integer ptsDoctorconsultChId) {
        this.ptsDoctorconsultChId = ptsDoctorconsultChId;
    }

    public String getPtsTransactionId() {
        return ptsTransactionId;
    }

    public void setPtsTransactionId(String ptsTransactionId) {
        this.ptsTransactionId = ptsTransactionId;
    }

    public Integer getPtsDoctorconsultId() {
        return ptsDoctorconsultId;
    }

    public void setPtsDoctorconsultId(Integer ptsDoctorconsultId) {
        this.ptsDoctorconsultId = ptsDoctorconsultId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPtsDoctorconsultChServices() {
        return ptsDoctorconsultChServices;
    }

    public void setPtsDoctorconsultChServices(String ptsDoctorconsultChServices) {
        this.ptsDoctorconsultChServices = ptsDoctorconsultChServices;
    }

    public String getPtsDoctorconsultChDetails() {
        return ptsDoctorconsultChDetails;
    }

    public void setPtsDoctorconsultChDetails(String ptsDoctorconsultChDetails) {
        this.ptsDoctorconsultChDetails = ptsDoctorconsultChDetails;
    }

    public String getPtsDoctorconsultChDate() {
        return ptsDoctorconsultChDate;
    }

    public void setPtsDoctorconsultChDate(String ptsDoctorconsultChDate) {
        this.ptsDoctorconsultChDate = ptsDoctorconsultChDate;
    }

    public String getPtsDoctorconsultChScheduleWeekDay() {
        return ptsDoctorconsultChScheduleWeekDay;
    }

    public void setPtsDoctorconsultChScheduleWeekDay(String ptsDoctorconsultChScheduleWeekDay) {
        this.ptsDoctorconsultChScheduleWeekDay = ptsDoctorconsultChScheduleWeekDay;
    }

    public String getPtsDoctorconsultChTimeSlots() {
        return ptsDoctorconsultChTimeSlots;
    }

    public void setPtsDoctorconsultChTimeSlots(String ptsDoctorconsultChTimeSlots) {
        this.ptsDoctorconsultChTimeSlots = ptsDoctorconsultChTimeSlots;
    }

    public String getPtsDoctorconsultChConsultType() {
        return ptsDoctorconsultChConsultType;
    }

    public void setPtsDoctorconsultChConsultType(String ptsDoctorconsultChConsultType) {
        this.ptsDoctorconsultChConsultType = ptsDoctorconsultChConsultType;
    }

    public String getPtsDoctorconsultChConsultMode() {
        return ptsDoctorconsultChConsultMode;
    }

    public void setPtsDoctorconsultChConsultMode(String ptsDoctorconsultChConsultMode) {
        this.ptsDoctorconsultChConsultMode = ptsDoctorconsultChConsultMode;
    }

    public String getPtsDoctorconsultChSessions() {
        return ptsDoctorconsultChSessions;
    }

    public void setPtsDoctorconsultChSessions(String ptsDoctorconsultChSessions) {
        this.ptsDoctorconsultChSessions = ptsDoctorconsultChSessions;
    }

    public Integer getPtsDoctorconsultChCost() {
        return ptsDoctorconsultChCost;
    }

    public void setPtsDoctorconsultChCost(Integer ptsDoctorconsultChCost) {
        this.ptsDoctorconsultChCost = ptsDoctorconsultChCost;
    }

    public Integer getPtsDoctorconsultChGstTax() {
        return ptsDoctorconsultChGstTax;
    }

    public void setPtsDoctorconsultChGstTax(Integer ptsDoctorconsultChGstTax) {
        this.ptsDoctorconsultChGstTax = ptsDoctorconsultChGstTax;
    }

    public Integer getPtsDoctorconsultChDiscount() {
        return ptsDoctorconsultChDiscount;
    }

    public void setPtsDoctorconsultChDiscount(Integer ptsDoctorconsultChDiscount) {
        this.ptsDoctorconsultChDiscount = ptsDoctorconsultChDiscount;
    }

    public String getPtsDoctorconsultChServiceHireType() {
        return ptsDoctorconsultChServiceHireType;
    }

    public void setPtsDoctorconsultChServiceHireType(String ptsDoctorconsultChServiceHireType) {
        this.ptsDoctorconsultChServiceHireType = ptsDoctorconsultChServiceHireType;
    }

    public String getPtsDoctorconsultChHomeservicePerson() {
        return ptsDoctorconsultChHomeservicePerson;
    }

    public void setPtsDoctorconsultChHomeservicePerson(String ptsDoctorconsultChHomeservicePerson) {
        this.ptsDoctorconsultChHomeservicePerson = ptsDoctorconsultChHomeservicePerson;
    }

    public String getPtsDoctorconsultChHomeservicePersonMobile() {
        return ptsDoctorconsultChHomeservicePersonMobile;
    }

    public void setPtsDoctorconsultChHomeservicePersonMobile(String ptsDoctorconsultChHomeservicePersonMobile) {
        this.ptsDoctorconsultChHomeservicePersonMobile = ptsDoctorconsultChHomeservicePersonMobile;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Boolean getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public void setPaymentConfirmation(Boolean paymentConfirmation) {
        this.paymentConfirmation = paymentConfirmation;
    }

    public Double getDoctorFees() {
        return doctorFees;
    }

    public void setDoctorFees(Double doctorFees) {
        this.doctorFees = doctorFees;
    }

    public String getDoctorProfilePath() {
        return doctorProfilePath;
    }

    public void setDoctorProfilePath(String doctorProfilePath) {
        this.doctorProfilePath = doctorProfilePath;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ptsDoctorconsultChId);
        dest.writeValue(ptsTransactionId);
        dest.writeValue(ptsDoctorconsultId);
        dest.writeValue(doctorId);
        dest.writeValue(doctorname);
        dest.writeValue(specialization);
        dest.writeValue(ptsDoctorconsultChServices);
        dest.writeValue(ptsDoctorconsultChDetails);
        dest.writeValue(ptsDoctorconsultChDate);
        dest.writeValue(ptsDoctorconsultChScheduleWeekDay);
        dest.writeValue(ptsDoctorconsultChTimeSlots);
        dest.writeValue(ptsDoctorconsultChConsultType);
        dest.writeValue(ptsDoctorconsultChConsultMode);
        dest.writeValue(ptsDoctorconsultChSessions);
        dest.writeValue(ptsDoctorconsultChCost);
        dest.writeValue(ptsDoctorconsultChGstTax);
        dest.writeValue(ptsDoctorconsultChDiscount);
        dest.writeValue(ptsDoctorconsultChServiceHireType);
        dest.writeValue(ptsDoctorconsultChHomeservicePerson);
        dest.writeValue(ptsDoctorconsultChHomeservicePersonMobile);
    }

    public int describeContents() {
        return  0;
    }

}
