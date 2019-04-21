
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.HospitalDetailModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("hospitalid")
    @Expose
    private String hospitalid;
    @SerializedName("hospitalname")
    @Expose
    private String hospitalname;
    @SerializedName("gststatecode")
    @Expose
    private String gststatecode;
    @SerializedName("gstno")
    @Expose
    private String gstno;
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
    @SerializedName("approved")
    @Expose
    private Boolean approved;
    @SerializedName("imagepath")
    @Expose
    private String imagepath;

    public String getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getGststatecode() {
        return gststatecode;
    }

    public void setGststatecode(String gststatecode) {
        this.gststatecode = gststatecode;
    }

    public String getGstno() {
        return gstno;
    }

    public void setGstno(String gstno) {
        this.gstno = gstno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneoffice() {
        return phoneoffice;
    }

    public void setPhoneoffice(String phoneoffice) {
        this.phoneoffice = phoneoffice;
    }

    public String getEmailoffice() {
        return emailoffice;
    }

    public void setEmailoffice(String emailoffice) {
        this.emailoffice = emailoffice;
    }

    public String getLicenceno() {
        return licenceno;
    }

    public void setLicenceno(String licenceno) {
        this.licenceno = licenceno;
    }

    public String getDateofregistration() {
        return dateofregistration;
    }

    public void setDateofregistration(String dateofregistration) {
        this.dateofregistration = dateofregistration;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

}
