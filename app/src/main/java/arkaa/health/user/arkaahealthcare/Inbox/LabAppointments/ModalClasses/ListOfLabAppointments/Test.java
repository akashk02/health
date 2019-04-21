
package arkaa.health.user.arkaahealthcare.Inbox.LabAppointments.ModalClasses.ListOfLabAppointments;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("test_id")
    @Expose
    private Integer testId;
    @SerializedName("test_name")
    @Expose
    private String testName;
    @SerializedName("price")
    @Expose
    private Integer price;
    public final static Creator<Test> CREATOR = new Creator<Test>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Test createFromParcel(Parcel in) {
            return new Test(in);
        }

        public Test[] newArray(int size) {
            return (new Test[size]);
        }

    }
    ;

    protected Test(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.testId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.testName = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Test() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(testId);
        dest.writeValue(testName);
        dest.writeValue(price);
    }

    public int describeContents() {
        return  0;
    }

}
