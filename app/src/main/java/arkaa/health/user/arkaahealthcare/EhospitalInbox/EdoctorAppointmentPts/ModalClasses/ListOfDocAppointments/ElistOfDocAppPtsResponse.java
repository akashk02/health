
package arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElistOfDocAppPtsResponse implements Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("griddata")
    @Expose
    private List<Griddatum> griddata = null;
    public final static Creator<ElistOfDocAppPtsResponse> CREATOR = new Creator<ElistOfDocAppPtsResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ElistOfDocAppPtsResponse createFromParcel(Parcel in) {
            return new ElistOfDocAppPtsResponse(in);
        }

        public ElistOfDocAppPtsResponse[] newArray(int size) {
            return (new ElistOfDocAppPtsResponse[size]);
        }

    }
    ;

    protected ElistOfDocAppPtsResponse(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
//        in.readList(this.data, arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Datum.class.getClassLoader());
//        in.readList(this.griddata, (arkaa.health.user.arkaahealthcare.EhospitalInbox.EdoctorAppointmentPts.ModalClasses.ListOfDocAppointments.Griddatum.class.getClassLoader()));

        this.data = ( (List<Datum>) in.readArrayList(Datum.class.getClassLoader())  );
        this.griddata = ( (List<Griddatum>) in.readArrayList(Griddatum.class.getClassLoader())  );

    }

    public ElistOfDocAppPtsResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Griddatum> getGriddata() {
        return griddata;
    }

    public void setGriddata(List<Griddatum> griddata) {
        this.griddata = griddata;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(error);
        dest.writeList(data);
        dest.writeList(griddata);
    }

    public int describeContents() {
        return  0;
    }

}
