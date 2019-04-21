
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{
    //doctor fees

    @SerializedName("WalkInFees")
    @Expose
    private Double WalkInFees;

    @SerializedName("VideoFees")
    @Expose
    private Double VideoFees;

    @SerializedName("VoiceFees")
    @Expose
    private Double VoiceFees;

    @SerializedName("TextFees")
    @Expose
    private Double TextFees;


    //doctor fees

    @SerializedName("doctorid")
    @Expose
    private String doctorid;
    @SerializedName("hospitalcode")
    @Expose
    private String hospitalcode;
    @SerializedName("doctorname")
    @Expose
    private String doctorname;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("doctorstatus")
    @Expose
    private String doctorstatus;
    @SerializedName("registrationnoboard")
    @Expose
    private String registrationnoboard;
    @SerializedName("associationdate")
    @Expose
    private String associationdate;
    @SerializedName("genralpracticeno")
    @Expose
    private String genralpracticeno;
    @SerializedName("experienceinyears")
    @Expose
    private String experienceinyears;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dateofbirth")
    @Expose
    private String dateofbirth;
    @SerializedName("doctoremail")
    @Expose
    private String doctoremail;
    @SerializedName("doctorphone")
    @Expose
    private String doctorphone;
    @SerializedName("doctoralternatephone")
    @Expose
    private String doctoralternatephone;
    @SerializedName("professionalexperience")
    @Expose
    private String professionalexperience;
    @SerializedName("awardsandrecognition")
    @Expose
    private String awardsandrecognition;
    @SerializedName("memberships")
    @Expose
    private String memberships;
    @SerializedName("doctoraddress")
    @Expose
    private String doctoraddress;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("doctorimageurl")
    @Expose
    private String doctorimageurl;
    @SerializedName("doctorwebsiteurl")
    @Expose
    private String doctorwebsiteurl;
    @SerializedName("aboutdoctor")
    @Expose
    private String aboutdoctor;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("isactive")
    @Expose
    private Boolean isactive;
    @SerializedName("qualification")
    @Expose
    private String qualification;
    @SerializedName("isbookingallow")
    @Expose
    private Boolean isbookingallow;
    @SerializedName("isdoctorathome")
    @Expose
    private Boolean isdoctorathome;
    @SerializedName("Walk_In_Consult")
    @Expose
    private Boolean walkInConsult;
    @SerializedName("Video_Consult")
    @Expose
    private Boolean videoConsult;
    @SerializedName("Text_Consult")
    @Expose
    private Boolean textConsult;

    @SerializedName("VoiceConsult")
    @Expose
    private Boolean VoiceConsult;

    public Double getWalkInFees() {
        return WalkInFees;
    }

    public void setWalkInFees(Double walkInFees) {
        WalkInFees = walkInFees;
    }

    public Double getVideoFees() {
        return VideoFees;
    }

    public void setVideoFees(Double videoFees) {
        VideoFees = videoFees;
    }

    public Double getVoiceFees() {
        return VoiceFees;
    }

    public void setVoiceFees(Double voiceFees) {
        VoiceFees = voiceFees;
    }

    public Double getTextFees() {
        return TextFees;
    }

    public void setTextFees(Double textFees) {
        TextFees = textFees;
    }

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
        this.WalkInFees = ((Double) in.readValue( (Double.class.getClassLoader())) );
        this.TextFees = ((Double) in.readValue( (Double.class.getClassLoader())) );
        this.VideoFees = ((Double) in.readValue( (Double.class.getClassLoader())) );
        this.VoiceFees = ((Double) in.readValue( (Double.class.getClassLoader())) );

        this.VoiceConsult = ((Boolean) in.readValue( (Boolean.class.getClassLoader())) );



        this.doctorid = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalcode = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorname = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorstatus = ((String) in.readValue((String.class.getClassLoader())));
        this.registrationnoboard = ((String) in.readValue((String.class.getClassLoader())));
        this.associationdate = ((String) in.readValue((String.class.getClassLoader())));
        this.genralpracticeno = ((String) in.readValue((String.class.getClassLoader())));
        this.experienceinyears = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.dateofbirth = ((String) in.readValue((String.class.getClassLoader())));
        this.doctoremail = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorphone = ((String) in.readValue((String.class.getClassLoader())));
        this.doctoralternatephone = ((String) in.readValue((String.class.getClassLoader())));
        this.professionalexperience = ((String) in.readValue((String.class.getClassLoader())));
        this.awardsandrecognition = ((String) in.readValue((String.class.getClassLoader())));
        this.memberships = ((String) in.readValue((String.class.getClassLoader())));
        this.doctoraddress = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorimageurl = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorwebsiteurl = ((String) in.readValue((String.class.getClassLoader())));
        this.aboutdoctor = ((String) in.readValue((String.class.getClassLoader())));
        this.facebook = ((String) in.readValue((String.class.getClassLoader())));
        this.twitter = ((String) in.readValue((String.class.getClassLoader())));
        this.isactive = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.qualification = ((String) in.readValue((String.class.getClassLoader())));
        this.isbookingallow = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.isdoctorathome = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.walkInConsult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.videoConsult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.textConsult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public Datum() {
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getHospitalcode() {
        return hospitalcode;
    }

    public void setHospitalcode(String hospitalcode) {
        this.hospitalcode = hospitalcode;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDoctorstatus() {
        return doctorstatus;
    }

    public void setDoctorstatus(String doctorstatus) {
        this.doctorstatus = doctorstatus;
    }

    public String getRegistrationnoboard() {
        return registrationnoboard;
    }

    public void setRegistrationnoboard(String registrationnoboard) {
        this.registrationnoboard = registrationnoboard;
    }

    public String getAssociationdate() {
        return associationdate;
    }

    public void setAssociationdate(String associationdate) {
        this.associationdate = associationdate;
    }

    public String getGenralpracticeno() {
        return genralpracticeno;
    }

    public void setGenralpracticeno(String genralpracticeno) {
        this.genralpracticeno = genralpracticeno;
    }

    public String getExperienceinyears() {
        return experienceinyears;
    }

    public void setExperienceinyears(String experienceinyears) {
        this.experienceinyears = experienceinyears;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getDoctoremail() {
        return doctoremail;
    }

    public void setDoctoremail(String doctoremail) {
        this.doctoremail = doctoremail;
    }

    public String getDoctorphone() {
        return doctorphone;
    }

    public void setDoctorphone(String doctorphone) {
        this.doctorphone = doctorphone;
    }

    public String getDoctoralternatephone() {
        return doctoralternatephone;
    }

    public void setDoctoralternatephone(String doctoralternatephone) {
        this.doctoralternatephone = doctoralternatephone;
    }

    public String getProfessionalexperience() {
        return professionalexperience;
    }

    public void setProfessionalexperience(String professionalexperience) {
        this.professionalexperience = professionalexperience;
    }

    public String getAwardsandrecognition() {
        return awardsandrecognition;
    }

    public void setAwardsandrecognition(String awardsandrecognition) {
        this.awardsandrecognition = awardsandrecognition;
    }

    public String getMemberships() {
        return memberships;
    }

    public void setMemberships(String memberships) {
        this.memberships = memberships;
    }

    public String getDoctoraddress() {
        return doctoraddress;
    }

    public void setDoctoraddress(String doctoraddress) {
        this.doctoraddress = doctoraddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDoctorimageurl() {
        return doctorimageurl;
    }

    public void setDoctorimageurl(String doctorimageurl) {
        this.doctorimageurl = doctorimageurl;
    }

    public String getDoctorwebsiteurl() {
        return doctorwebsiteurl;
    }

    public void setDoctorwebsiteurl(String doctorwebsiteurl) {
        this.doctorwebsiteurl = doctorwebsiteurl;
    }

    public String getAboutdoctor() {
        return aboutdoctor;
    }

    public void setAboutdoctor(String aboutdoctor) {
        this.aboutdoctor = aboutdoctor;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Boolean getIsbookingallow() {
        return isbookingallow;
    }

    public void setIsbookingallow(Boolean isbookingallow) {
        this.isbookingallow = isbookingallow;
    }

    public Boolean getIsdoctorathome() {
        return isdoctorathome;
    }

    public void setIsdoctorathome(Boolean isdoctorathome) {
        this.isdoctorathome = isdoctorathome;
    }

    public Boolean getWalkInConsult() {
        return walkInConsult;
    }

    public void setWalkInConsult(Boolean walkInConsult) {
        this.walkInConsult = walkInConsult;
    }

    public Boolean getVideoConsult() {
        return videoConsult;
    }

    public void setVideoConsult(Boolean videoConsult) {
        this.videoConsult = videoConsult;
    }

    public Boolean getTextConsult() {
        return textConsult;
    }

    public void setTextConsult(Boolean textConsult) {
        this.textConsult = textConsult;
    }

    public Boolean getVoiceConsult() {
        return VoiceConsult;
    }

    public void setVoiceConsult(Boolean voiceConsult) {
        VoiceConsult = voiceConsult;
    }

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeValue(VideoFees);
        dest.writeValue(VoiceFees);
        dest.writeValue(TextFees);
        dest.writeValue(WalkInFees);

        dest.writeValue(VoiceConsult);



        dest.writeValue(doctorid);
        dest.writeValue(hospitalcode);
        dest.writeValue(doctorname);
        dest.writeValue(rating);
        dest.writeValue(doctorstatus);
        dest.writeValue(registrationnoboard);
        dest.writeValue(associationdate);
        dest.writeValue(genralpracticeno);
        dest.writeValue(experienceinyears);
        dest.writeValue(gender);
        dest.writeValue(dateofbirth);
        dest.writeValue(doctoremail);
        dest.writeValue(doctorphone);
        dest.writeValue(doctoralternatephone);
        dest.writeValue(professionalexperience);
        dest.writeValue(awardsandrecognition);
        dest.writeValue(memberships);
        dest.writeValue(doctoraddress);
        dest.writeValue(pincode);
        dest.writeValue(doctorimageurl);
        dest.writeValue(doctorwebsiteurl);
        dest.writeValue(aboutdoctor);
        dest.writeValue(facebook);
        dest.writeValue(twitter);
        dest.writeValue(isactive);
        dest.writeValue(qualification);
        dest.writeValue(isbookingallow);
        dest.writeValue(isdoctorathome);
        dest.writeValue(walkInConsult);
        dest.writeValue(videoConsult);
        dest.writeValue(textConsult);
    }

    public int describeContents() {
        return  0;
    }

}
