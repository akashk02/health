
package arkaa.health.user.arkaahealthcare.Pharmacy.ModalClasses.ListOfPharmacyRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentMethod {

    @SerializedName("path")
    @Expose
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
