
package arkaa.health.user.arkaahealthcare.Profile.ModalClasses.ProfileEditRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("insert_or_update_user_profile")
    @Expose
    private Integer insertOrUpdateUserProfile;

    public Integer getInsertOrUpdateUserProfile() {
        return insertOrUpdateUserProfile;
    }

    public void setInsertOrUpdateUserProfile(Integer insertOrUpdateUserProfile) {
        this.insertOrUpdateUserProfile = insertOrUpdateUserProfile;
    }

}
