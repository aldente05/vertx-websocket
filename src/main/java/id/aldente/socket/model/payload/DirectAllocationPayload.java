package id.aldente.socket.model.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by f.putra on 10/25/20.
 */
public class DirectAllocationPayload implements Serializable {

    private static final long serialVersionUID = 7000414211314873466L;

    private Integer id;
    private String kode;
    private String departement;
    private Long account;
    private String description;
    private String sun_ac_description;
    private String expense_type;
    private Double attributablilty_attributable;
    private Double attributablilty_non_attributable;
    private Double attributablilty_total;
    private Double benefit_for_acquisition;
    private Double benefit_for_maintenance;
    private Double benefit_for_total;
    private Double acquisition_non_pre_coverage;
    private Double acquisition_pre_coverage;
    private Double acquisition_total;
    private Double maintenance_premium_related;
    private Double maintenance_non_premium_related;
    private Double maintenance_total;
    private String allocation_dirver;
    private String remarks;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("updated_by")
    private String updatedBy;
    @JsonProperty("updated_time")
    private String updatedTime = null;
    @JsonProperty("approve_by")
    private String approveBy;
    @JsonProperty("approve_time")
    private String approveTime = null;
    @JsonProperty("is_approve")
    private boolean isApprove;

    public DirectAllocationPayload() {
        this.id = id;
        this.kode = kode;
        this.departement = departement;
        this.account = account;
        this.description = description;
        this.sun_ac_description = sun_ac_description;
        this.expense_type = expense_type;
        this.attributablilty_attributable = attributablilty_attributable;
        this.attributablilty_non_attributable = attributablilty_non_attributable;
        this.attributablilty_total = attributablilty_total;
        this.benefit_for_acquisition = benefit_for_acquisition;
        this.benefit_for_maintenance = benefit_for_maintenance;
        this.benefit_for_total = benefit_for_total;
        this.acquisition_non_pre_coverage = acquisition_non_pre_coverage;
        this.acquisition_pre_coverage = acquisition_pre_coverage;
        this.acquisition_total = acquisition_total;
        this.maintenance_premium_related = maintenance_premium_related;
        this.maintenance_non_premium_related = maintenance_non_premium_related;
        this.maintenance_total = maintenance_total;
        this.allocation_dirver = allocation_dirver;
        this.remarks = remarks;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.approveBy = approveBy;
        this.approveTime = approveTime;
        this.isApprove = isApprove;
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

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
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

    public Double getAttributablilty_attributable() {
        return attributablilty_attributable;
    }

    public void setAttributablilty_attributable(Double attributablilty_attributable) {
        this.attributablilty_attributable = attributablilty_attributable;
    }

    public Double getAttributablilty_non_attributable() {
        return attributablilty_non_attributable;
    }

    public void setAttributablilty_non_attributable(Double attributablilty_non_attributable) {
        this.attributablilty_non_attributable = attributablilty_non_attributable;
    }

    public Double getAttributablilty_total() {
        return attributablilty_total;
    }

    public void setAttributablilty_total(Double attributablilty_total) {
        this.attributablilty_total = attributablilty_total;
    }

    public Double getBenefit_for_acquisition() {
        return benefit_for_acquisition;
    }

    public void setBenefit_for_acquisition(Double benefit_for_acquisition) {
        this.benefit_for_acquisition = benefit_for_acquisition;
    }

    public Double getBenefit_for_maintenance() {
        return benefit_for_maintenance;
    }

    public void setBenefit_for_maintenance(Double benefit_for_maintenance) {
        this.benefit_for_maintenance = benefit_for_maintenance;
    }

    public Double getBenefit_for_total() {
        return benefit_for_total;
    }

    public void setBenefit_for_total(Double benefit_for_total) {
        this.benefit_for_total = benefit_for_total;
    }

    public Double getAcquisition_non_pre_coverage() {
        return acquisition_non_pre_coverage;
    }

    public void setAcquisition_non_pre_coverage(Double acquisition_non_pre_coverage) {
        this.acquisition_non_pre_coverage = acquisition_non_pre_coverage;
    }

    public Double getAcquisition_pre_coverage() {
        return acquisition_pre_coverage;
    }

    public void setAcquisition_pre_coverage(Double acquisition_pre_coverage) {
        this.acquisition_pre_coverage = acquisition_pre_coverage;
    }

    public Double getAcquisition_total() {
        return acquisition_total;
    }

    public void setAcquisition_total(Double acquisition_total) {
        this.acquisition_total = acquisition_total;
    }

    public Double getMaintenance_premium_related() {
        return maintenance_premium_related;
    }

    public void setMaintenance_premium_related(Double maintenance_premium_related) {
        this.maintenance_premium_related = maintenance_premium_related;
    }

    public Double getMaintenance_non_premium_related() {
        return maintenance_non_premium_related;
    }

    public void setMaintenance_non_premium_related(Double maintenance_non_premium_related) {
        this.maintenance_non_premium_related = maintenance_non_premium_related;
    }

    public Double getMaintenance_total() {
        return maintenance_total;
    }

    public void setMaintenance_total(Double maintenance_total) {
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    @Override
    public String toString() {
        return "DirectAllocationPayload{" +
                "id=" + id +
                ", kode='" + kode + '\'' +
                ", departement='" + departement + '\'' +
                ", account=" + account +
                ", description='" + description + '\'' +
                ", sun_ac_description='" + sun_ac_description + '\'' +
                ", expense_type='" + expense_type + '\'' +
                ", attributablilty_attributable=" + attributablilty_attributable +
                ", attributablilty_non_attributable=" + attributablilty_non_attributable +
                ", attributablilty_total=" + attributablilty_total +
                ", benefit_for_acquisition=" + benefit_for_acquisition +
                ", benefit_for_maintenance=" + benefit_for_maintenance +
                ", benefit_for_total=" + benefit_for_total +
                ", acquisition_non_pre_coverage=" + acquisition_non_pre_coverage +
                ", acquisition_pre_coverage=" + acquisition_pre_coverage +
                ", acquisition_total=" + acquisition_total +
                ", maintenance_premium_related=" + maintenance_premium_related +
                ", maintenance_non_premium_related=" + maintenance_non_premium_related +
                ", maintenance_total=" + maintenance_total +
                ", allocation_dirver='" + allocation_dirver + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", approveBy='" + approveBy + '\'' +
                ", approveTime='" + approveTime + '\'' +
                ", isApprove=" + isApprove +
                '}';
    }
}
