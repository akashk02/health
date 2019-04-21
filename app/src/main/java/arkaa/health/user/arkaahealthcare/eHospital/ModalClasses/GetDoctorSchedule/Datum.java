
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.GetDoctorSchedule;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

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
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.doctorUserId = ((String) in.readValue((String.class.getClassLoader())));
        this.day = ((String) in.readValue((String.class.getClassLoader())));
        this.time1 = ((String) in.readValue((String.class.getClassLoader())));
        this.time2 = ((String) in.readValue((String.class.getClassLoader())));
        this.time3 = ((String) in.readValue((String.class.getClassLoader())));
        this.time4 = ((String) in.readValue((String.class.getClassLoader())));
        this.time5 = ((String) in.readValue((String.class.getClassLoader())));
        this.time6 = ((String) in.readValue((String.class.getClassLoader())));
        this.time7 = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(doctorUserId);
        dest.writeValue(day);
        dest.writeValue(time1);
        dest.writeValue(time2);
        dest.writeValue(time3);
        dest.writeValue(time4);
        dest.writeValue(time5);
        dest.writeValue(time6);
        dest.writeValue(time7);
    }

    public int describeContents() {
        return  0;
    }

}
