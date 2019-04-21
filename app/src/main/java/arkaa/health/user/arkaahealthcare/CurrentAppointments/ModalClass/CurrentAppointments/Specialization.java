
package arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specialization implements Parcelable
{

    @SerializedName("specialization_id")
    @Expose
    private Integer specializationId;
    @SerializedName("specialization_name")
    @Expose
    private String specializationName;
    public final static Creator<Specialization> CREATOR = new Creator<Specialization>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Specialization createFromParcel(Parcel in) {
            return new Specialization(in);
        }

        public Specialization[] newArray(int size) {
            return (new Specialization[size]);
        }

    }
    ;

    protected Specialization(Parcel in) {
        this.specializationId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.specializationName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Specialization() {
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
