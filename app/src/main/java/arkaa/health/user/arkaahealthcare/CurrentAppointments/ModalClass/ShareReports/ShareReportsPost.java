
package arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.ShareReports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareReportsPost {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("appointment_id")
    @Expose
    private Integer appointmentId;
    @SerializedName("report_id")
    @Expose
    private Integer reportId;
    @SerializedName("report_path")
    @Expose
    private String reportPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

}
