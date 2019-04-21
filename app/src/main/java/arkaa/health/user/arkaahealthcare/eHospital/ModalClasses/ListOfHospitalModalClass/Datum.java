
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfHospitalModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("hospitalId")
    @Expose
    private String hospitalIdAppointment;

    @SerializedName("hospitalid")
    @Expose
    private String hospitalid;
    @SerializedName("hospitalname")
    @Expose
    private String hospitalname;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phoneoffice")
    @Expose
    private String phoneoffice;
    @SerializedName("emailoffice")
    @Expose
    private String emailoffice;
    @SerializedName("licenceno")
    @Expose
    private String licenceno;
    @SerializedName("dateofregistration")
    @Expose
    private String dateofregistration;
    @SerializedName("contactperson")
    @Expose
    private String contactperson;
    @SerializedName("imagepath")
    @Expose
    private String imagepath;
    @SerializedName("approved")
    @Expose
    private Boolean approved;

    public String getHospitalid() {
        return nullHandling(hospitalid);
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid;
    }

    public String getHospitalname() {
        return nullHandling(hospitalname);
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getAddress() {
        return nullHandling(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneoffice() {
        return nullHandling(phoneoffice);
    }

    public void setPhoneoffice(String phoneoffice) {
        this.phoneoffice = phoneoffice;
    }

    public String getEmailoffice() {
        return nullHandling(emailoffice);
    }

    public void setEmailoffice(String emailoffice) {
        this.emailoffice = emailoffice;
    }

    public String getLicenceno() {
        return nullHandling(licenceno);
    }

    public void setLicenceno(String licenceno) {
        this.licenceno = licenceno;
    }

    public String getDateofregistration() {
        return nullHandling(dateofregistration);
    }

    public void setDateofregistration(String dateofregistration) {
        this.dateofregistration = dateofregistration;
    }

    public String getContactperson() {
        return nullHandling(contactperson);
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getImagepath() {
        return nullHandling(imagepath);
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getHospitalIdAppointment() {
        return hospitalIdAppointment;
    }

    public void setHospitalIdAppointment(String hospitalIdAppointment) {
        this.hospitalIdAppointment = hospitalIdAppointment;
    }

    public String nullHandling(String value){
        if(value !=null){
            return value ;
        }
        return "";
    }

}
