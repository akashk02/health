
package arkaa.health.user.arkaahealthcare.EhospitalInbox.ELabAppointment.ModalClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Griddatum {

    @SerializedName("pts_lab_ch_id")
    @Expose
    private Integer ptsLabChId;
    @SerializedName("pts_transaction_id")
    @Expose
    private String ptsTransactionId;
    @SerializedName("pts_lab_id")
    @Expose
    private Integer ptsLabId;
    @SerializedName("pts_lab_labName")
    @Expose
    private String ptsLabLabName;
    @SerializedName("pts_lab_ch_testname")
    @Expose
    private String ptsLabChTestname;
    @SerializedName("pts_lab_ch_details")
    @Expose
    private String ptsLabChDetails;
    @SerializedName("pts_lab_ch_price")
    @Expose
    private Double ptsLabChPrice;
    @SerializedName("pts_lab_ch_GST")
    @Expose
    private Integer ptsLabChGST;
    @SerializedName("pts_lab_ch_discount")
    @Expose
    private Double ptsLabChDiscount;
    @SerializedName("pts_lab_ch_flag")
    @Expose
    private Boolean ptsLabChFlag;
    @SerializedName("pts_lab_ch_FinalPrice")
    @Expose
    private Double ptsLabChFinalPrice;
    @SerializedName("TotalFinalPrice")
    @Expose
    private Double totalFinalPrice;

    public Integer getPtsLabChId() {
        return ptsLabChId;
    }

    public void setPtsLabChId(Integer ptsLabChId) {
        this.ptsLabChId = ptsLabChId;
    }

    public String getPtsTransactionId() {
        return ptsTransactionId;
    }

    public void setPtsTransactionId(String ptsTransactionId) {
        this.ptsTransactionId = ptsTransactionId;
    }

    public Integer getPtsLabId() {
        return ptsLabId;
    }

    public void setPtsLabId(Integer ptsLabId) {
        this.ptsLabId = ptsLabId;
    }

    public String getPtsLabLabName() {
        return ptsLabLabName;
    }

    public void setPtsLabLabName(String ptsLabLabName) {
        this.ptsLabLabName = ptsLabLabName;
    }

    public String getPtsLabChTestname() {
        return ptsLabChTestname;
    }

    public void setPtsLabChTestname(String ptsLabChTestname) {
        this.ptsLabChTestname = ptsLabChTestname;
    }

    public String getPtsLabChDetails() {
        return ptsLabChDetails;
    }

    public void setPtsLabChDetails(String ptsLabChDetails) {
        this.ptsLabChDetails = ptsLabChDetails;
    }

    public Double getPtsLabChPrice() {
        return ptsLabChPrice;
    }

    public void setPtsLabChPrice(Double ptsLabChPrice) {
        this.ptsLabChPrice = ptsLabChPrice;
    }

    public Integer getPtsLabChGST() {
        return ptsLabChGST;
    }

    public void setPtsLabChGST(Integer ptsLabChGST) {
        this.ptsLabChGST = ptsLabChGST;
    }

    public Double getPtsLabChDiscount() {
        return ptsLabChDiscount;
    }

    public void setPtsLabChDiscount(Double ptsLabChDiscount) {
        this.ptsLabChDiscount = ptsLabChDiscount;
    }

    public Boolean getPtsLabChFlag() {
        return ptsLabChFlag;
    }

    public void setPtsLabChFlag(Boolean ptsLabChFlag) {
        this.ptsLabChFlag = ptsLabChFlag;
    }

    public Double getPtsLabChFinalPrice() {
        return ptsLabChFinalPrice;
    }

    public void setPtsLabChFinalPrice(Double ptsLabChFinalPrice) {
        this.ptsLabChFinalPrice = ptsLabChFinalPrice;
    }

    public Double getTotalFinalPrice() {
        return totalFinalPrice;
    }

    public void setTotalFinalPrice(Double totalFinalPrice) {
        this.totalFinalPrice = totalFinalPrice;
    }

}
