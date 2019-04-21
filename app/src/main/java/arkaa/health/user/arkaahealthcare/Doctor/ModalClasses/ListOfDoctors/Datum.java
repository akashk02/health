
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("doctor_id")
    @Expose
    private Integer doctorId;
    @SerializedName("doctor_unique_id")
    @Expose
    private String doctorUniqueId;
    @SerializedName("doctor_name")
    @Expose
    private String doctorName;
    @SerializedName("total_experience")
    @Expose
    private String totalExperience;
    @SerializedName("specilaization")
    @Expose
    private List<Specilaization> specilaization = null;
    @SerializedName("degree")
    @Expose
    private List<Degree> degree = null;
    @SerializedName("clinic_details")
    @Expose
    private List<ClinicDetail> clinicDetails = null;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("profile_pic_path")
    @Expose
    private String profilePicPath;
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
        this.doctorId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.doctorUniqueId = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorName = ((String) in.readValue((String.class.getClassLoader())));
        this.totalExperience = ((String) in.readValue((String.class.getClassLoader())));
//        in.readList(this.specilaization, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Specilaization.class.getClassLoader()));
        this.specilaization = ((List<Specilaization>) in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Specilaization.class.getClassLoader()));
//        in.readList(this.degree, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Degree.class.getClassLoader()));
        this.degree = ( (List<Degree>) in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Degree.class.getClassLoader()) );
//        in.readList(this.clinicDetails, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicDetail.class.getClassLoader()));
        this.clinicDetails = ( (List<ClinicDetail> ) in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicDetail.class.getClassLoader()) );
        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorUniqueId() {
        return doctorUniqueId;
    }

    public void setDoctorUniqueId(String doctorUniqueId) {
        this.doctorUniqueId = doctorUniqueId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        this.totalExperience = totalExperience;
    }

    public List<Specilaization> getSpecilaization() {
        return specilaization;
    }

    public void setSpecilaization(List<Specilaization> specilaization) {
        this.specilaization = specilaization;
    }

    public List<Degree> getDegree() {
        return degree;
    }

    public void setDegree(List<Degree> degree) {
        this.degree = degree;
    }

    public List<ClinicDetail> getClinicDetails() {
        return clinicDetails;
    }

    public void setClinicDetails(List<ClinicDetail> clinicDetails) {
        this.clinicDetails = clinicDetails;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(doctorId);
        dest.writeValue(doctorUniqueId);
        dest.writeValue(doctorName);
        dest.writeValue(totalExperience);
        dest.writeList(specilaization);
        dest.writeList(degree);
        dest.writeList(clinicDetails);
        dest.writeValue(createdOn);
        dest.writeValue(status);
        dest.writeValue(profilePicPath);
    }

    public int describeContents() {
        return  0;
    }

}
