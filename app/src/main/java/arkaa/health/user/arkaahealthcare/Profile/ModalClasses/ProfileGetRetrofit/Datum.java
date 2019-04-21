
package arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileGetRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("unique_id")
    @Expose
    private String uniqueId;
    @SerializedName("email_status")
    @Expose
    private Boolean emailStatus;
    @SerializedName("mobile_status")
    @Expose
    private Boolean mobileStatus;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Boolean getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Boolean emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Boolean getMobileStatus() {
        return mobileStatus;
    }

    public void setMobileStatus(Boolean mobileStatus) {
        this.mobileStatus = mobileStatus;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
