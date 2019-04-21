
package arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabAppointmentBooking;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("insert_or_update_lab_appointment")
    @Expose
    private Integer insertOrUpdateLabAppointment;
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
        this.insertOrUpdateLabAppointment = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getInsertOrUpdateLabAppointment() {
        return insertOrUpdateLabAppointment;
    }

    public void setInsertOrUpdateLabAppointment(Integer insertOrUpdateLabAppointment) {
        this.insertOrUpdateLabAppointment = insertOrUpdateLabAppointment;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(insertOrUpdateLabAppointment);
    }

    public int describeContents() {
        return  0;
    }

}
