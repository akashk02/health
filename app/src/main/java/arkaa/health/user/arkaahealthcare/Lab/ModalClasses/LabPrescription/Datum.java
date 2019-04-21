
package arkaa.health.user.arkaahealthcare.Lab.ModalClasses.LabPrescription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("insert_or_update_prescriptions_for_labs")
    @Expose
    private Integer insertOrUpdatePrescriptionsForLabs;

    public Integer getInsertOrUpdatePrescriptionsForLabs() {
        return insertOrUpdatePrescriptionsForLabs;
    }

    public void setInsertOrUpdatePrescriptionsForLabs(Integer insertOrUpdatePrescriptionsForLabs) {
        this.insertOrUpdatePrescriptionsForLabs = insertOrUpdatePrescriptionsForLabs;
    }

}
