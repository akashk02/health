
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ViewQuotation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientAddress implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    public final static Creator<PatientAddress> CREATOR = new Creator<PatientAddress>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PatientAddress createFromParcel(Parcel in) {
            return new PatientAddress(in);
        }

        public PatientAddress[] newArray(int size) {
            return (new PatientAddress[size]);
        }

    }
    ;

    protected PatientAddress(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.locality = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.landmark = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PatientAddress() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(locality);
        dest.writeValue(address);
        dest.writeValue(landmark);
        dest.writeValue(pincode);
    }

    public int describeContents() {
        return  0;
    }

}
