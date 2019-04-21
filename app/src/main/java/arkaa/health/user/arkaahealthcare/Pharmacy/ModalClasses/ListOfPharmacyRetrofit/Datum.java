
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("unique_id")
    @Expose
    private String uniqueId;
    @SerializedName("pharmacy_name")
    @Expose
    private String pharmacyName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("location_lat")
    @Expose
    private String locationLat;
    @SerializedName("location_long")
    @Expose
    private String locationLong;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("license_no")
    @Expose
    private String licenseNo;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("home_service_charges")
    @Expose
    private String homeServiceCharges;
    @SerializedName("home_service_contact")
    @Expose
    private String homeServiceContact;
    @SerializedName("payment_method")
    @Expose
    private List<PaymentMethod> paymentMethod = null;
    @SerializedName("timings")
    @Expose
    private List<Timing> timings = null;
    @SerializedName("created_on")
    @Expose
    private String createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(String locationLong) {
        this.locationLong = locationLong;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getHomeServiceCharges() {
        return homeServiceCharges;
    }

    public void setHomeServiceCharges(String homeServiceCharges) {
        this.homeServiceCharges = homeServiceCharges;
    }

    public String getHomeServiceContact() {
        return homeServiceContact;
    }

    public void setHomeServiceContact(String homeServiceContact) {
        this.homeServiceContact = homeServiceContact;
    }

    public List<PaymentMethod> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(List<PaymentMethod> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Timing> getTimings() {
        return timings;
    }

    public void setTimings(List<Timing> timings) {
        this.timings = timings;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}
