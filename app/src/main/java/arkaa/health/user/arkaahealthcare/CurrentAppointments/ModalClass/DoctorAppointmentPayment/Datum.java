
package arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.DoctorAppointmentPayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("update_payment_in_doctor_appointment")
    @Expose
    private Integer updatePaymentInDoctorAppointment;

    public Integer getUpdatePaymentInDoctorAppointment() {
        return updatePaymentInDoctorAppointment;
    }

    public void setUpdatePaymentInDoctorAppointment(Integer updatePaymentInDoctorAppointment) {
        this.updatePaymentInDoctorAppointment = updatePaymentInDoctorAppointment;
    }

}
