
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClinicDetail implements Parcelable
{

    @SerializedName("clinic_id")
    @Expose
    private Integer clinicId;
    @SerializedName("clinic_name")
    @Expose
    private String clinicName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("telephone_no")
    @Expose
    private String telephoneNo;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("location_lat")
    @Expose
    private String locationLat;
    @SerializedName("location_long")
    @Expose
    private String locationLong;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("clinic_timings")
    @Expose
    private List<ClinicTiming> clinicTimings = null;
    @SerializedName("affiliation_details")
    @Expose
    private List<AffiliationDetail> affiliationDetails = null;
    public final static Creator<ClinicDetail> CREATOR = new Creator<ClinicDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ClinicDetail createFromParcel(Parcel in) {
            return new ClinicDetail(in);
        }

        public ClinicDetail[] newArray(int size) {
            return (new ClinicDetail[size]);
        }

    }
    ;

    protected ClinicDetail(Parcel in) {
        this.clinicId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.clinicName = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.telephoneNo = ((String) in.readValue((String.class.getClassLoader())));
//        in.readList(this.photos, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Photo.class.getClassLoader()));
        this.photos = ( (List<Photo>) in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.Photo.class.getClassLoader()));
        this.locationLat = ((String) in.readValue((String.class.getClassLoader())));
        this.locationLong = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
//        in.readList(this.clinicTimings, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicTiming.class.getClassLoader()));
        this.clinicTimings = (List<ClinicTiming>) in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.ClinicTiming.class.getClassLoader());
//        in.readList(this.affiliationDetails, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.AffiliationDetail.class.getClassLoader()));
        this.affiliationDetails = ( (List<AffiliationDetail>) in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.AffiliationDetail.class.getClassLoader())  );
    }

    public ClinicDetail() {
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<ClinicTiming> getClinicTimings() {
        return clinicTimings;
    }

    public void setClinicTimings(List<ClinicTiming> clinicTimings) {
        this.clinicTimings = clinicTimings;
    }

    public List<AffiliationDetail> getAffiliationDetails() {
        return affiliationDetails;
    }

    public void setAffiliationDetails(List<AffiliationDetail> affiliationDetails) {
        this.affiliationDetails = affiliationDetails;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(clinicId);
        dest.writeValue(clinicName);
        dest.writeValue(address);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(pincode);
        dest.writeValue(mobile);
        dest.writeValue(telephoneNo);
        dest.writeList(photos);
        dest.writeValue(locationLat);
        dest.writeValue(locationLong);
        dest.writeValue(location);
        dest.writeList(clinicTimings);
        dest.writeList(affiliationDetails);
    }

    public int describeContents() {
        return  0;
    }

}
