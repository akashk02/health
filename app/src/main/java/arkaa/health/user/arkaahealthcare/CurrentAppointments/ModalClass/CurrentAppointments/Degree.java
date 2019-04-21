
package arkaa.health.user.arkaahealthcare.CurrentAppointments.ModalClass.CurrentAppointments;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Degree implements Parcelable
{

    @SerializedName("degree")
    @Expose
    private String degree;
    public final static Creator<Degree> CREATOR = new Creator<Degree>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Degree createFromParcel(Parcel in) {
            return new Degree(in);
        }

        public Degree[] newArray(int size) {
            return (new Degree[size]);
        }

    }
    ;

    protected Degree(Parcel in) {
        this.degree = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Degree() {
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(degree);
    }

    public int describeContents() {
        return  0;
    }

}
