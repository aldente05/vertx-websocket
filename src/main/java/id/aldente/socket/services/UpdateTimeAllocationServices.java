package id.aldente.socket.services;

import com.google.gson.Gson;
import id.aldente.socket.config.DBConnectionService;
import id.aldente.socket.model.DirectAllocationParser;
import id.aldente.socket.util.BaseResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by f.putra on 11/6/20.
 */
public class UpdateTimeAllocationServices {

    private static final Logger logger = LoggerFactory.getLogger(UpdateTimeAllocationServices.class);

    @NonBlocking
    public static BaseResponse<String> updateTimeAllocation(JSONObject jsonRequest, String dbeaqUrl, String dbeaqUser, String dbeaqPassword) {
        BaseResponse<String> result = new BaseResponse<String>();
        try {
            Gson gson = new Gson();
            DirectAllocationParser obj = gson.fromJson(jsonRequest.getString("data"), DirectAllocationParser.class);

            logger.info(obj.toString());
            Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
            String queryUpdate = "UPDATE eaq_dev.transaction_time_allocation " +
                    "SET team =?, " +
                    "    con_agen_acquisiton =?, " +
                    "    con_agen_maintenance =?, " +
                    "    con_bancassurance_acquisition =?, " +
                    "    con_bancassurance_maintenance =?, " +
                    "    con_dmtm_acquisition =?, " +
                    "    con_dmtm_maintenance =?, " +
                    "    con_total =?, " +
                    "    sharia_agen_acquisiton =?, " +
                    "    sharia_agen_maintenance =?, " +
                    "    sharia_bancassurance_acquisition =?, " +
                    "    sharia_bancassurance_maintenance =?, " +
                    "    sharia_dmtm_acquisition =?, " +
                    "    sharia_dmtm_maintenance =?, " +
                    "    sharia_total =?, " +
                    "    project_con_agen_acquisiton =?, " +
                    "    project_con_agen_maintenance =?, " +
                    "    project_con_bancassurance_acquisition =?, " +
                    "    project_con_bancassurance_maintenance =?, " +
                    "    project_con_dmtm_acquisition =?, " +
                    "    project_con_dmtm_maintenance =?, " +
                    "    project_con_total =?, " +
                    "    project_sharia_agen_acquisiton =?, " +
                    "    project_sharia_agen_maintenance =?, " +
                    "    project_sharia_bancassurance_acquisition =?, " +
                    "    project_sharia_bancassurance_maintenance =?, " +
                    "    project_sharia_dmtm_acquisition =?, " +
                    "    project_sharia_dmtm_maintenance =?, " +
                    "    project_sharia_total =?, " +
                    "    dpd_agen_acquisiton =?, " +
                    "    dpd_agen_maintenance =?, " +
                    "    dpd_bancassurance_acquisition =?, " +
                    "    dpd_bancassurance_maintenance =?, " +
                    "    dpd_dmtm_acquisition =?, " +
                    "    dpd_sharia_dmtm_maintenance =?, " +
                    "    dpd_total =?, " +
                    "    note =?, " +
                    "    created_by =?, " +
                    "    created_time =?, " +
                    "    updated_by =?, " +
                    "    updated_time =? WHERE account = ? AND kode = ?";

            try {
                PreparedStatement psmt = conn.prepareStatement(queryUpdate);
                psmt.setString(1, jsonRequest.getString("team"));
                psmt.setDouble(2, jsonRequest.getDouble("con_agen_acquisiton"));
                psmt.setDouble(3, jsonRequest.getDouble("con_agen_maintenance"));
                psmt.setDouble(4, jsonRequest.getDouble("con_bancassurance_acquisition"));
                psmt.setDouble(5, jsonRequest.getDouble("con_bancassurance_maintenance"));
                psmt.setDouble(6, jsonRequest.getDouble("con_dmtm_acquisition"));
                psmt.setDouble(7, jsonRequest.getDouble("con_dmtm_maintenance"));
                psmt.setDouble(8, jsonRequest.getDouble("con_total"));
                psmt.setDouble(9, jsonRequest.getDouble("sharia_agen_acquisiton"));
                psmt.setDouble(10, jsonRequest.getDouble("sharia_agen_maintenance"));
                psmt.setDouble(11, jsonRequest.getDouble("sharia_bancassurance_acquisition"));
                psmt.setDouble(12, jsonRequest.getDouble("sharia_bancassurance_maintenance"));
                psmt.setDouble(13, jsonRequest.getDouble("sharia_dmtm_acquisition"));
                psmt.setDouble(14, jsonRequest.getDouble("sharia_dmtm_maintenance"));
                psmt.setDouble(15, jsonRequest.getDouble("sharia_total"));
                psmt.setDouble(16, jsonRequest.getDouble("project_con_agen_acquisiton"));
                psmt.setDouble(17, jsonRequest.getDouble("project_con_agen_maintenance"));
                psmt.setDouble(18, jsonRequest.getDouble("project_con_bancassurance_acquisition"));
                psmt.setDouble(19, jsonRequest.getDouble("project_con_bancassurance_maintenance"));
                psmt.setDouble(20, jsonRequest.getDouble("project_con_dmtm_acquisition"));
                psmt.setDouble(21, jsonRequest.getDouble("project_con_dmtm_maintenance"));
                psmt.setDouble(22, jsonRequest.getDouble("project_con_total"));
                psmt.setDouble(23, jsonRequest.getDouble("project_sharia_agen_acquisiton"));
                psmt.setDouble(24, jsonRequest.getDouble("project_sharia_agen_maintenance"));
                psmt.setDouble(25, jsonRequest.getDouble("project_sharia_bancassurance_acquisition"));
                psmt.setDouble(26, jsonRequest.getDouble("project_sharia_bancassurance_maintenance"));
                psmt.setDouble(27, jsonRequest.getDouble("project_sharia_dmtm_acquisition"));
                psmt.setDouble(28, jsonRequest.getDouble("project_sharia_dmtm_maintenance"));
                psmt.setDouble(29, jsonRequest.getDouble("project_sharia_total"));
                psmt.setDouble(30, jsonRequest.getDouble("dpd_agen_acquisiton"));
                psmt.setDouble(31, jsonRequest.getDouble("dpd_agen_maintenance"));
                psmt.setDouble(32, jsonRequest.getDouble("dpd_bancassurance_acquisition"));
                psmt.setDouble(33, jsonRequest.getDouble("dpd_bancassurance_maintenance"));
                psmt.setDouble(34, jsonRequest.getDouble("dpd_dmtm_acquisition"));
                psmt.setDouble(35, jsonRequest.getDouble("dpd_sharia_dmtm_maintenance"));
                psmt.setDouble(36, jsonRequest.getDouble("dpd_total"));
                psmt.setString(37, jsonRequest.getString("note"));
                psmt.setString(38, jsonRequest.getString("created_by"));
                psmt.setString(39, jsonRequest.getString("created_time"));
                psmt.setString(40, jsonRequest.getString("updated_by"));
                psmt.setString(41, jsonRequest.getString("updated_time"));
                psmt.setString(42, jsonRequest.getString("account"));
                psmt.setString(43, jsonRequest.getString("kode"));
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
