package id.aldente.socket.services;

import com.google.gson.Gson;
import id.aldente.socket.config.DBConnectionService;
import id.aldente.socket.model.DirectAllocationParser;
import id.aldente.socket.util.BaseResponse;

import java.sql.*;

import io.vertx.core.json.Json;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prudential.platform.stream.compute.NonBlocking;

import java.sql.SQLException;

/**
 * Created by f.putra on 11/4/20.
 */
public class UpdateDirectAllocationServices {
    private static final Logger logger = LoggerFactory.getLogger(UpdateDirectAllocationServices.class);

    @NonBlocking
    public static BaseResponse<String> updateDirectAllocation(JSONObject jsonRequest, String dbeaqUrl, String dbeaqUser, String dbeaqPassword) {
        BaseResponse<String> result = new BaseResponse<String>();
        try {
            Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
            String queryUpdate = "UPDATE eaq_dev.transaction_direct_allocation " +
                    "SET description = ?, sun_ac_description = ?, expense_type = ?, attributablilty_attributable = ?, " +
                    "attributablilty_non_attributable =  ?, attributablilty_total =  ?, benefit_for_acquisition =  ?, benefit_for_maintenance =  ?, " +
                    "benefit_for_total =  ?, acquisition_non_pre_coverage =  ?, acquisition_pre_coverage =  ?, acquisition_total =  ?, " +
                    "maintenance_premium_related =  ?, maintenance_non_premium_related =  ?, maintenance_total =  ?,  allocation_dirver =  ?, " +
                    "remarks =  ?, created_by =  ?, created_time =  ?, updated_by =  ?, " +
                    "updated_time =  ? WHERE account = ? AND kode = ?";

            try {
                PreparedStatement psmt = conn.prepareStatement(queryUpdate);
                psmt.setString(1, jsonRequest.getString("description"));
                psmt.setString(2, jsonRequest.getString("sun_ac_description"));
                psmt.setString(3, jsonRequest.getString("expense_type"));
                psmt.setDouble(4, jsonRequest.getDouble("attributability_attributable"));
                psmt.setDouble(5, jsonRequest.getDouble("attributability_non_attributable"));
                psmt.setDouble(6, jsonRequest.getDouble("attributability_total"));
                psmt.setDouble(7, jsonRequest.getDouble("benefit_for_acquisition"));
                psmt.setDouble(8, jsonRequest.getDouble("benefit_for_maintenance"));
                psmt.setDouble(9, jsonRequest.getDouble("benefit_for_total"));
                psmt.setDouble(10, jsonRequest.getDouble("acquisition_non_pre_coverage"));
                psmt.setDouble(11, jsonRequest.getDouble("acquisition_pre_coverage"));
                psmt.setDouble(12, jsonRequest.getDouble("acquisition_total"));
                psmt.setDouble(13, jsonRequest.getDouble("maintenance_premium_related"));
                psmt.setDouble(14, jsonRequest.getDouble("maintenance_non_premium_related"));
                psmt.setDouble(15, jsonRequest.getDouble("maintenance_total"));
                psmt.setString(16, jsonRequest.getString("allocation_dirver"));
                psmt.setString(17, jsonRequest.getString("remarks"));
                psmt.setString(18, jsonRequest.getString("created_by"));
                psmt.setDate(19, Date.valueOf(jsonRequest.getString("created_time")));
                psmt.setString(20, jsonRequest.getString("updated_by"));
                psmt.setDate(21, Date.valueOf(jsonRequest.getString("updated_time")));
                psmt.setString(22, jsonRequest.getString("account"));
                psmt.setString(23, jsonRequest.getString("kode"));
                psmt.executeUpdate();
                result.setMessage("Update Success");
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                logger.error("update direct allocation : " + e.getMessage());
                result.setMessage("500");
                result.setSuccess(false);
                return result;
            }

        }
    }
