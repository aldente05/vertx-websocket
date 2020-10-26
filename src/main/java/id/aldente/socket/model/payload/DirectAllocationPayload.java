package id.aldente.socket.model.payload;

/**
 * Created by f.putra on 10/25/20.
 */
public class DirectAllocationPayload {

    private String senderId;
    private Integer id;
    private String kode;
    private String departement;
    private String account;
    private String description;
    private String sun_ac_description;
    private String expense_type;
    private String attributablilty_attributable;
    private String attributablilty_non_attributable;
    private String attributablilty_total;
    private String benefit_for_acquisition;
    private String benefit_for_maintenance;
    private String benefit_for_total;
    private String acquisition_non_pre_coverage;
    private String acquisition_pre_coverage;
    private String acquisition_total;
    private String maintenance_premium_related;
    private String maintenance_non_premium_related;
    private String maintenance_total;
    private String allocation_dirver;
    private String remarks;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSun_ac_description() {
        return sun_ac_description;
    }

    public void setSun_ac_description(String sun_ac_description) {
        this.sun_ac_description = sun_ac_description;
    }

    public String getExpense_type() {
        return expense_type;
    }

    public void setExpense_type(String expense_type) {
        this.expense_type = expense_type;
    }

    public String getAttributablilty_attributable() {
        return attributablilty_attributable;
    }

    public void setAttributablilty_attributable(String attributablilty_attributable) {
        this.attributablilty_attributable = attributablilty_attributable;
    }

    public String getAttributablilty_non_attributable() {
        return attributablilty_non_attributable;
    }

    public void setAttributablilty_non_attributable(String attributablilty_non_attributable) {
        this.attributablilty_non_attributable = attributablilty_non_attributable;
    }

    public String getAttributablilty_total() {
        return attributablilty_total;
    }

    public void setAttributablilty_total(String attributablilty_total) {
        this.attributablilty_total = attributablilty_total;
    }

    public String getBenefit_for_acquisition() {
        return benefit_for_acquisition;
    }

    public void setBenefit_for_acquisition(String benefit_for_acquisition) {
        this.benefit_for_acquisition = benefit_for_acquisition;
    }

    public String getBenefit_for_maintenance() {
        return benefit_for_maintenance;
    }

    public void setBenefit_for_maintenance(String benefit_for_maintenance) {
        this.benefit_for_maintenance = benefit_for_maintenance;
    }

    public String getBenefit_for_total() {
        return benefit_for_total;
    }

    public void setBenefit_for_total(String benefit_for_total) {
        this.benefit_for_total = benefit_for_total;
    }

    public String getAcquisition_non_pre_coverage() {
        return acquisition_non_pre_coverage;
    }

    public void setAcquisition_non_pre_coverage(String acquisition_non_pre_coverage) {
        this.acquisition_non_pre_coverage = acquisition_non_pre_coverage;
    }

    public String getAcquisition_pre_coverage() {
        return acquisition_pre_coverage;
    }

    public void setAcquisition_pre_coverage(String acquisition_pre_coverage) {
        this.acquisition_pre_coverage = acquisition_pre_coverage;
    }

    public String getAcquisition_total() {
        return acquisition_total;
    }

    public void setAcquisition_total(String acquisition_total) {
        this.acquisition_total = acquisition_total;
    }

    public String getMaintenance_premium_related() {
        return maintenance_premium_related;
    }

    public void setMaintenance_premium_related(String maintenance_premium_related) {
        this.maintenance_premium_related = maintenance_premium_related;
    }

    public String getMaintenance_non_premium_related() {
        return maintenance_non_premium_related;
    }

    public void setMaintenance_non_premium_related(String maintenance_non_premium_related) {
        this.maintenance_non_premium_related = maintenance_non_premium_related;
    }

    public String getMaintenance_total() {
        return maintenance_total;
    }

    public void setMaintenance_total(String maintenance_total) {
        this.maintenance_total = maintenance_total;
    }

    public String getAllocation_dirver() {
        return allocation_dirver;
    }

    public void setAllocation_dirver(String allocation_dirver) {
        this.allocation_dirver = allocation_dirver;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "DirectAllocationPayload{" +
                "senderId='" + senderId + '\'' +
                ", id=" + id +
                ", kode='" + kode + '\'' +
                ", departement='" + departement + '\'' +
                ", account='" + account + '\'' +
                ", description='" + description + '\'' +
                ", sun_ac_description='" + sun_ac_description + '\'' +
                ", expense_type='" + expense_type + '\'' +
                ", attributablilty_attributable='" + attributablilty_attributable + '\'' +
                ", attributablilty_non_attributable='" + attributablilty_non_attributable + '\'' +
                ", attributablilty_total='" + attributablilty_total + '\'' +
                ", benefit_for_acquisition='" + benefit_for_acquisition + '\'' +
                ", benefit_for_maintenance='" + benefit_for_maintenance + '\'' +
                ", benefit_for_total='" + benefit_for_total + '\'' +
                ", acquisition_non_pre_coverage='" + acquisition_non_pre_coverage + '\'' +
                ", acquisition_pre_coverage='" + acquisition_pre_coverage + '\'' +
                ", acquisition_total='" + acquisition_total + '\'' +
                ", maintenance_premium_related='" + maintenance_premium_related + '\'' +
                ", maintenance_non_premium_related='" + maintenance_non_premium_related + '\'' +
                ", maintenance_total='" + maintenance_total + '\'' +
                ", allocation_dirver='" + allocation_dirver + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
