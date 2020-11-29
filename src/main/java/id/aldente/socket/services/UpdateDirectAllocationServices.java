package id.aldente.socket.services;

import com.google.gson.Gson;
import id.aldente.socket.config.DBConnectionService;
import id.aldente.socket.model.DirectAllocationParser;
import id.aldente.socket.util.BaseResponse;

import java.sql.*;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.json.JSONArray;
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
    public static BaseResponse<JsonArray> updateDirectAllocation(JSONObject jsonRequest, String dbeaqUrl, String dbeaqUser, String dbeaqPassword) {
        BaseResponse<JsonArray> result = new BaseResponse<JsonArray>();

        String description = jsonRequest.getString("description");
        String sun_ac_description = jsonRequest.getString("sun_ac_description");
        String expense_type = jsonRequest.getString("expense_type");
        double attributability_attributable = jsonRequest.getInt("attributability_attributable");
        double attributability_non_attributable = jsonRequest.getDouble("attributability_non_attributable");
        double attributability_total = jsonRequest.getDouble("attributability_total");
        double benefit_for_acquisition = jsonRequest.getDouble("benefit_for_acquisition");
        double benefit_for_maintenance = jsonRequest.getDouble("benefit_for_maintenance");
        double benefit_for_total = jsonRequest.getDouble("benefit_for_total");
        double acquisition_non_pre_coverage = jsonRequest.getDouble("acquisition_non_pre_coverage");
        double acquisition_pre_coverage = jsonRequest.getDouble("acquisition_pre_coverage");
        double acquisition_total = jsonRequest.getDouble("acquisition_total");
        double maintenance_premium_related = jsonRequest.getDouble("maintenance_premium_related");
        double maintenance_non_premium_related = jsonRequest.getDouble("maintenance_non_premium_related");
        double maintenance_total = jsonRequest.getDouble("maintenance_total");
        String allocation_dirver = jsonRequest.getString("allocation_dirver");
        String remarks = jsonRequest.getString("remarks");
        String updated_by = jsonRequest.getString("username");
        long account = jsonRequest.getLong("account");
        String kode = jsonRequest.getString("kode");

        try {
            Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
            String queryUpdate = "UPDATE eaq_dev.transaction_direct_allocation " +
                    "SET description = ?, sun_ac_description = ?, expense_type = ?, attributablilty_attributable = ?, " +
                    "attributablilty_non_attributable =  ?, attributablilty_total =  ?, benefit_for_acquisition =  ?, benefit_for_maintenance =  ?, " +
                    "benefit_for_total =  ?, acquisition_non_pre_coverage =  ?, acquisition_pre_coverage =  ?, acquisition_total =  ?, " +
                    "maintenance_premium_related =  ?, maintenance_non_premium_related =  ?, maintenance_total =  ?,  allocation_dirver =  ?, " +
                    "remarks =  ?, created_by =  ?, created_time =  ?, updated_by =  ?, " +
                    "updated_time =  ? WHERE account = ? AND kode = ?";

            PreparedStatement psmt = conn.prepareStatement(queryUpdate);
            psmt.setString(1, description);
            psmt.setString(2, sun_ac_description);
            psmt.setString(3, expense_type);
            psmt.setDouble(4, attributability_attributable);
            psmt.setDouble(5, attributability_non_attributable);
            psmt.setDouble(6, attributability_total);
            psmt.setDouble(7, benefit_for_acquisition);
            psmt.setDouble(8, benefit_for_maintenance);
            psmt.setDouble(9, benefit_for_total);
            psmt.setDouble(10, acquisition_non_pre_coverage);
            psmt.setDouble(11, acquisition_pre_coverage);
            psmt.setDouble(12, acquisition_total);
            psmt.setDouble(13, maintenance_premium_related);
            psmt.setDouble(14, maintenance_non_premium_related);
            psmt.setDouble(15, maintenance_total);
            psmt.setString(16, allocation_dirver);
            psmt.setString(17, remarks);
            psmt.setString(18, updated_by);
            psmt.setDate(19, new java.sql.Date(System.currentTimeMillis()));
            psmt.setLong(20, Long.parseLong(account));
            psmt.setString(21, kode);

            ResultSet rs = psmt.executeQuery();
            JsonArray json = new JsonArray();
            ResultSetMetaData rsmd = rs.getMetaData();
            String columnName;
            while (rs.next()) {
                int numColumns = rsmd.getColumnCount();
                JsonObject obj = new JsonObject();
                for (int i = 1; i <= numColumns; i++) {
                    String column_name = rsmd.getColumnName(i);
                    columnName = rs.getString(column_name);
                    obj.put(column_name, columnName);
                }
                json.add(obj);
            }
            result.setData(json);
            result.setMessage("Update Success");
            result.setSuccess(true);
            return result;
        } catch (SQLException e) {
            logger.error("update direct allocation : " + e.getMessage());
            result.setMessage("500");
            result.setSuccess(false);
            return result;
        } catch (Exception e) {
            logger.error("update direct allocation : " + e.getMessage());
            result.setMessage("500");
            result.setSuccess(false);
            return result;
        }

    }
}
