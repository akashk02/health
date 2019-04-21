
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabQuotationConfirmationPost implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    public final static Creator<LabQuotationConfirmationPost> CREATOR = new Creator<LabQuotationConfirmationPost>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LabQuotationConfirmationPost createFromParcel(Parcel in) {
            return new LabQuotationConfirmationPost(in);
        }

        public LabQuotationConfirmationPost[] newArray(int size) {
            return (new LabQuotationConfirmationPost[size]);
        }

    }
    ;

    protected LabQuotationConfirmationPost(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.statusId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public LabQuotationConfirmationPost() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(statusId);
    }

    public int describeContents() {
        return  0;
    }

}
