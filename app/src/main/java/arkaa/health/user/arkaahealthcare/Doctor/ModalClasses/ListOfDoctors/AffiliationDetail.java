
package arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AffiliationDetail implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("time_slot_per_client_in_min")
    @Expose
    private Integer timeSlotPerClientInMin;
    @SerializedName("first_consultation_fee")
    @Expose
    private Integer firstConsultationFee;
    @SerializedName("followup_consultation_fee")
    @Expose
    private Integer followupConsultationFee;
    @SerializedName("doctor_timings")
    @Expose
    private List<DoctorTiming> doctorTimings = null;
    @SerializedName("video_fees")
    @Expose
    private Double videoFees;
    @SerializedName("text_fees")
    @Expose
    private Double textFees;
    @SerializedName("voice_fees")
    @Expose
    private Double voiceFees;
    @SerializedName("voice_consultation_mobile1")
    @Expose
    private Double voiceConsultationMobile1;
    public final static Creator<AffiliationDetail> CREATOR = new Creator<AffiliationDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AffiliationDetail createFromParcel(Parcel in) {
            return new AffiliationDetail(in);
        }

        public AffiliationDetail[] newArray(int size) {
            return (new AffiliationDetail[size]);
        }

    }
    ;

    protected AffiliationDetail(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.timeSlotPerClientInMin = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.firstConsultationFee = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.followupConsultationFee = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        in.readList(this.doctorTimings, (arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorTiming.class.getClassLoader()));
        this.doctorTimings = (  (List<DoctorTiming>)  in.readArrayList(arkaa.health.user.arkaahealthcare.Doctor.ModalClasses.ListOfDoctors.DoctorTiming.class.getClassLoader()) );
        this.videoFees = ((Double) in.readValue((Double.class.getClassLoader())));
        this.textFees = ((Double) in.readValue((Double.class.getClassLoader())));
        this.voiceFees = ((Double) in.readValue((Double.class.getClassLoader())));
        this.voiceConsultationMobile1 = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public AffiliationDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeSlotPerClientInMin() {
        return timeSlotPerClientInMin;
    }

    public void setTimeSlotPerClientInMin(Integer timeSlotPerClientInMin) {
        this.timeSlotPerClientInMin = timeSlotPerClientInMin;
    }

    public Integer getFirstConsultationFee() {
        return firstConsultationFee;
    }

    public void setFirstConsultationFee(Integer firstConsultationFee) {
        this.firstConsultationFee = firstConsultationFee;
    }

    public Integer getFollowupConsultationFee() {
        return followupConsultationFee;
    }

    public void setFollowupConsultationFee(Integer followupConsultationFee) {
        this.followupConsultationFee = followupConsultationFee;
    }

    public List<DoctorTiming> getDoctorTimings() {
        return doctorTimings;
    }

    public void setDoctorTimings(List<DoctorTiming> doctorTimings) {
        this.doctorTimings = doctorTimings;
    }

    public Double getVideoFees() {
        return videoFees;
    }

    public void setVideoFees(Double videoFees) {
        this.videoFees = videoFees;
    }

    public Double getTextFees() {
        return textFees;
    }

    public void setTextFees(Double textFees) {
        this.textFees = textFees;
    }

    public Double getVoiceFees() {
        return voiceFees;
    }

    public void setVoiceFees(Double voiceFees) {
        this.voiceFees = voiceFees;
    }

    public Double getVoiceConsultationMobile1() {
        return voiceConsultationMobile1;
    }

    public void setVoiceConsultationMobile1(Double voiceConsultationMobile1) {
        this.voiceConsultationMobile1 = voiceConsultationMobile1;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(timeSlotPerClientInMin);
        dest.writeValue(firstConsultationFee);
        dest.writeValue(followupConsultationFee);
        dest.writeList(doctorTimings);
        dest.writeValue(videoFees);
        dest.writeValue(textFees);
        dest.writeValue(voiceFees);
        dest.writeValue(voiceConsultationMobile1);
    }

    public int describeContents() {
        return  0;
    }

}
