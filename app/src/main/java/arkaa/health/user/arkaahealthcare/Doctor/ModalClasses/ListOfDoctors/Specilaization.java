
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specilaization implements Parcelable
{

    @SerializedName("specialization_id")
    @Expose
    private Integer specializationId;
    @SerializedName("specialization_name")
    @Expose
    private String specializationName;
    public final static Creator<Specilaization> CREATOR = new Creator<Specilaization>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Specilaization createFromParcel(Parcel in) {
            return new Specilaization(in);
        }

        public Specilaization[] newArray(int size) {
            return (new Specilaization[size]);
        }

    }
    ;

    protected Specilaization(Parcel in) {
        this.specializationId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.specializationName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Specilaization() {
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(specializationId);
        dest.writeValue(specializationName);
    }

    public int describeContents() {
        return  0;
    }

}
