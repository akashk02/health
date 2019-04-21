
package arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.ShareReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("insert_or_update_doctor_reports")
    @Expose
    private Integer insertOrUpdateDoctorReports;

    public Integer getInsertOrUpdateDoctorReports() {
        return insertOrUpdateDoctorReports;
    }

    public void setInsertOrUpdateDoctorReports(Integer insertOrUpdateDoctorReports) {
        this.insertOrUpdateDoctorReports = insertOrUpdateDoctorReports;
    }

}
