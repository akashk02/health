
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.appointment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("insert_or_update_appointment")
    @Expose
    private Integer insertOrUpdateAppointment;

    public Integer getInsertOrUpdateAppointment() {
        return insertOrUpdateAppointment;
    }

    public void setInsertOrUpdateAppointment(Integer insertOrUpdateAppointment) {
        this.insertOrUpdateAppointment = insertOrUpdateAppointment;
    }

}
