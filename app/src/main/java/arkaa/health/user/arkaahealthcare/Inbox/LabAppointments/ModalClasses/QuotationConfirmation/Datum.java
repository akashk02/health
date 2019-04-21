
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("change_lab_appointment_status")
    @Expose
    private Integer changeLabAppointmentStatus;
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
        this.changeLabAppointmentStatus = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getChangeLabAppointmentStatus() {
        return changeLabAppointmentStatus;
    }

    public void setChangeLabAppointmentStatus(Integer changeLabAppointmentStatus) {
        this.changeLabAppointmentStatus = changeLabAppointmentStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(changeLabAppointmentStatus);
    }

    public int describeContents() {
        return  0;
    }

}
