
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDocScheduleModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("DoctorUserId")
    @Expose
    private String doctorUserId;
    @SerializedName("Day")
    @Expose
    private String day;
    @SerializedName("Time1")
    @Expose
    private String time1;
    @SerializedName("Time2")
    @Expose
    private String time2;
    @SerializedName("Time3")
    @Expose
    private String time3;
    @SerializedName("Time4")
    @Expose
    private String time4;
    @SerializedName("Time5")
    @Expose
    private String time5;
    @SerializedName("Time6")
    @Expose
    private String time6;
    @SerializedName("Time7")
    @Expose
    private String time7;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorUserId() {
        return doctorUserId;
    }

    public void setDoctorUserId(String doctorUserId) {
        this.doctorUserId = doctorUserId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4;
    }

    public String getTime5() {
        return time5;
    }

    public void setTime5(String time5) {
        this.time5 = time5;
    }

    public String getTime6() {
        return time6;
    }

    public void setTime6(String time6) {
        this.time6 = time6;
    }

    public String getTime7() {
        return time7;
    }

    public void setTime7(String time7) {
        this.time7 = time7;
    }

}
