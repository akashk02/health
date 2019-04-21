
package arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("pts_doctorconsult_id")
    @Expose
    private Integer ptsDoctorconsultId;
    @SerializedName("pts_id")
    @Expose
    private String ptsId;
    @SerializedName("pts_doctorconsult_patientName")
    @Expose
    private String ptsDoctorconsultPatientName;
    @SerializedName("pts_doctorconsult_address")
    @Expose
    private String ptsDoctorconsultAddress;
    @SerializedName("pts_doctorconsult_mobile")
    @Expose
    private String ptsDoctorconsultMobile;
    @SerializedName("pts_doctorconsult_email")
    @Expose
    private String ptsDoctorconsultEmail;
    @SerializedName("pts_doctorconsult_landline")
    @Expose
    private String ptsDoctorconsultLandline;
    @SerializedName("pts_doctorconsult_altMobile")
    @Expose
    private String ptsDoctorconsultAltMobile;
    @SerializedName("pts_doctorconsult_altEmail")
    @Expose
    private String ptsDoctorconsultAltEmail;
    @SerializedName("pts_doctorconsult_altLandline")
    @Expose
    private String ptsDoctorconsultAltLandline;
    @SerializedName("pts_doctorconsult_type")
    @Expose
    private String ptsDoctorconsultType;
    @SerializedName("pts_doctorconsult_conciergeName")
    @Expose
    private String ptsDoctorconsultConciergeName;
    @SerializedName("pts_doctorconsult_conciergeMobile")
    @Expose
    private String ptsDoctorconsultConciergeMobile;
    @SerializedName("pts_doctorconsult_conciergeEmail")
    @Expose
    private String ptsDoctorconsultConciergeEmail;
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
        this.ptsDoctorconsultId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ptsId = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultPatientName = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultLandline = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultAltMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultAltEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultAltLandline = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultType = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultConciergeName = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultConciergeMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.ptsDoctorconsultConciergeEmail = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getPtsDoctorconsultId() {
        return ptsDoctorconsultId;
    }

    public void setPtsDoctorconsultId(Integer ptsDoctorconsultId) {
        this.ptsDoctorconsultId = ptsDoctorconsultId;
    }

    public String getPtsId() {
        return ptsId;
    }

    public void setPtsId(String ptsId) {
        this.ptsId = ptsId;
    }

    public String getPtsDoctorconsultPatientName() {
        return ptsDoctorconsultPatientName;
    }

    public void setPtsDoctorconsultPatientName(String ptsDoctorconsultPatientName) {
        this.ptsDoctorconsultPatientName = ptsDoctorconsultPatientName;
    }

    public String getPtsDoctorconsultAddress() {
        return ptsDoctorconsultAddress;
    }

    public void setPtsDoctorconsultAddress(String ptsDoctorconsultAddress) {
        this.ptsDoctorconsultAddress = ptsDoctorconsultAddress;
    }

    public String getPtsDoctorconsultMobile() {
        return ptsDoctorconsultMobile;
    }

    public void setPtsDoctorconsultMobile(String ptsDoctorconsultMobile) {
        this.ptsDoctorconsultMobile = ptsDoctorconsultMobile;
    }

    public String getPtsDoctorconsultEmail() {
        return ptsDoctorconsultEmail;
    }

    public void setPtsDoctorconsultEmail(String ptsDoctorconsultEmail) {
        this.ptsDoctorconsultEmail = ptsDoctorconsultEmail;
    }

    public String getPtsDoctorconsultLandline() {
        return ptsDoctorconsultLandline;
    }

    public void setPtsDoctorconsultLandline(String ptsDoctorconsultLandline) {
        this.ptsDoctorconsultLandline = ptsDoctorconsultLandline;
    }

    public String getPtsDoctorconsultAltMobile() {
        return ptsDoctorconsultAltMobile;
    }

    public void setPtsDoctorconsultAltMobile(String ptsDoctorconsultAltMobile) {
        this.ptsDoctorconsultAltMobile = ptsDoctorconsultAltMobile;
    }

    public String getPtsDoctorconsultAltEmail() {
        return ptsDoctorconsultAltEmail;
    }

    public void setPtsDoctorconsultAltEmail(String ptsDoctorconsultAltEmail) {
        this.ptsDoctorconsultAltEmail = ptsDoctorconsultAltEmail;
    }

    public String getPtsDoctorconsultAltLandline() {
        return ptsDoctorconsultAltLandline;
    }

    public void setPtsDoctorconsultAltLandline(String ptsDoctorconsultAltLandline) {
        this.ptsDoctorconsultAltLandline = ptsDoctorconsultAltLandline;
    }

    public String getPtsDoctorconsultType() {
        return ptsDoctorconsultType;
    }

    public void setPtsDoctorconsultType(String ptsDoctorconsultType) {
        this.ptsDoctorconsultType = ptsDoctorconsultType;
    }

    public String getPtsDoctorconsultConciergeName() {
        return ptsDoctorconsultConciergeName;
    }

    public void setPtsDoctorconsultConciergeName(String ptsDoctorconsultConciergeName) {
        this.ptsDoctorconsultConciergeName = ptsDoctorconsultConciergeName;
    }

    public String getPtsDoctorconsultConciergeMobile() {
        return ptsDoctorconsultConciergeMobile;
    }

    public void setPtsDoctorconsultConciergeMobile(String ptsDoctorconsultConciergeMobile) {
        this.ptsDoctorconsultConciergeMobile = ptsDoctorconsultConciergeMobile;
    }

    public String getPtsDoctorconsultConciergeEmail() {
        return ptsDoctorconsultConciergeEmail;
    }

    public void setPtsDoctorconsultConciergeEmail(String ptsDoctorconsultConciergeEmail) {
        this.ptsDoctorconsultConciergeEmail = ptsDoctorconsultConciergeEmail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ptsDoctorconsultId);
        dest.writeValue(ptsId);
        dest.writeValue(ptsDoctorconsultPatientName);
        dest.writeValue(ptsDoctorconsultAddress);
        dest.writeValue(ptsDoctorconsultMobile);
        dest.writeValue(ptsDoctorconsultEmail);
        dest.writeValue(ptsDoctorconsultLandline);
        dest.writeValue(ptsDoctorconsultAltMobile);
        dest.writeValue(ptsDoctorconsultAltEmail);
        dest.writeValue(ptsDoctorconsultAltLandline);
        dest.writeValue(ptsDoctorconsultType);
        dest.writeValue(ptsDoctorconsultConciergeName);
        dest.writeValue(ptsDoctorconsultConciergeMobile);
        dest.writeValue(ptsDoctorconsultConciergeEmail);
    }

    public int describeContents() {
        return  0;
    }

}
