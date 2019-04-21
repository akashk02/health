
package arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElistOfDocPostResponse implements Parcelable
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
    public final static Creator<ElistOfDocPostResponse> CREATOR = new Creator<ElistOfDocPostResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ElistOfDocPostResponse createFromParcel(Parcel in) {
            return new ElistOfDocPostResponse(in);
        }

        public ElistOfDocPostResponse[] newArray(int size) {
            return (new ElistOfDocPostResponse[size]);
        }

    }
    ;

    protected ElistOfDocPostResponse(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
      //  in.readList(this.data, (arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.Datum.class.getClassLoader()));
        this.data = ( (List<Datum>) in.readArrayList(arkaa.health.user.arkaahealthcare.eHospital.ModalClasses.ListOfDoctorsModalClass.Datum.class.getClassLoader())  );
    }

    public ElistOfDocPostResponse() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(error);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
