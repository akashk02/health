
package arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable
{

    @SerializedName("path")
    @Expose
    private String path;
    public final static Creator<Photo> CREATOR = new Creator<Photo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        public Photo[] newArray(int size) {
            return (new Photo[size]);
        }

    }
    ;

    protected Photo(Parcel in) {
        this.path = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Photo() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(path);
    }

    public int describeContents() {
        return  0;
    }

}
