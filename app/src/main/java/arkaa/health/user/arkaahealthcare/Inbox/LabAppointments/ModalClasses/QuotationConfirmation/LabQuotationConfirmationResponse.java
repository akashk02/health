
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabQuotationConfirmationResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<LabQuotationConfirmationResponse> CREATOR = new Creator<LabQuotationConfirmationResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LabQuotationConfirmationResponse createFromParcel(Parcel in) {
            return new LabQuotationConfirmationResponse(in);
        }

        public LabQuotationConfirmationResponse[] newArray(int size) {
            return (new LabQuotationConfirmationResponse[size]);
        }

    }
    ;

    protected LabQuotationConfirmationResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
       // in.readList(this.data, (arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation.Datum.class.getClassLoader()));
        this.data = ( (List<Datum>) in.readArrayList(arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.QuotationConfirmation.Datum.class.getClassLoader())  );
    }

    public LabQuotationConfirmationResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
