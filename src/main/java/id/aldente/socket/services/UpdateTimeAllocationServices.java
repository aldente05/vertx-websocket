package id.aldente.socket.services;

import com.google.gson.Gson;
import id.aldente.socket.config.DBConnectionService;
import id.aldente.socket.model.DirectAllocationParser;
import id.aldente.socket.util.BaseResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by f.putra on 11/6/20.
 */
public class UpdateTimeAllocationServices {

    private static final Logger logger = LoggerFactory.getLogger(UpdateTimeAllocationServices.class);

    @NonBlocking
    public static BaseResponse<JsonArray> updateTimeAllocation(JSONObject jsonRequest, String dbeaqUrl, String dbeaqUser, String dbeaqPassword) {
        BaseResponse<JsonArray> result = new BaseResponse<JsonArray>();
        try {
            String team = jsonRequest.getString("team");
            double con_agen_acquisiton = jsonRequest.getDouble("con_agen_acquisiton");
            double con_agen_maintenance = jsonRequest.getDouble("con_agen_maintenance");
            double con_bancassurance_acquisition = jsonRequest.getDouble("con_bancassurance_acquisition");
            double con_bancassurance_maintenance = jsonRequest.getDouble("con_bancassurance_maintenance");
            double con_dmtm_acquisition = jsonRequest.getDouble("con_dmtm_acquisition");
            double con_dmtm_maintenance = jsonRequest.getDouble("con_dmtm_maintenance");
            double con_total = jsonRequest.getDouble("con_total");
            double sharia_agen_acquisiton = jsonRequest.getDouble("sharia_agen_acquisiton");
            double sharia_agen_maintenance = jsonRequest.getDouble("sharia_agen_maintenance");
            double sharia_bancassurance_acquisition = jsonRequest.getDouble("sharia_bancassurance_acquisition");
            double sharia_bancassurance_maintenance = jsonRequest.getDouble("sharia_bancassurance_maintenance");
            double sharia_dmtm_acquisition = jsonRequest.getDouble("sharia_dmtm_acquisition");
            double sharia_dmtm_maintenance = jsonRequest.getDouble("sharia_dmtm_maintenance");
            double sharia_total = jsonRequest.getDouble("sharia_total");
            double project_con_agen_acquisiton = jsonRequest.getDouble("project_con_agen_acquisiton");
            double project_con_agen_maintenance = jsonRequest.getDouble("project_con_agen_maintenance");
            double project_con_bancassurance_acquisition = jsonRequest.getDouble("project_con_bancassurance_acquisition");
            double project_con_bancassurance_maintenance = jsonRequest.getDouble("project_con_bancassurance_maintenance");
            double project_con_dmtm_acquisition = jsonRequest.getDouble("project_con_dmtm_acquisition");
            double project_con_dmtm_maintenance = jsonRequest.getDouble("project_con_dmtm_maintenance");
            double project_con_total = jsonRequest.getDouble("project_con_total");
            double project_sharia_agen_acquisiton = jsonRequest.getDouble("project_sharia_agen_acquisiton");
            double project_sharia_agen_maintenance = jsonRequest.getDouble("project_sharia_agen_maintenance");
            double project_sharia_bancassurance_acquisition = jsonRequest.getDouble("project_sharia_bancassurance_acquisition");
            double project_sharia_bancassurance_maintenance = jsonRequest.getDouble("project_sharia_bancassurance_maintenance");
            double project_sharia_dmtm_acquisition = jsonRequest.getDouble("project_sharia_dmtm_acquisition");
            double project_sharia_dmtm_maintenance = jsonRequest.getDouble("project_sharia_dmtm_maintenance");
            double project_sharia_total = jsonRequest.getDouble("project_sharia_total");
            double dpd_agen_acquisiton = jsonRequest.getDouble("dpd_agen_acquisiton");
            double dpd_agen_maintenance = jsonRequest.getDouble("dpd_agen_maintenance");
            double dpd_bancassurance_acquisition = jsonRequest.getDouble("dpd_bancassurance_acquisition");
            double dpd_bancassurance_maintenance = jsonRequest.getDouble("dpd_bancassurance_maintenance");
            double dpd_dmtm_acquisition = jsonRequest.getDouble("dpd_dmtm_acquisition");
            double dpd_sharia_dmtm_maintenance = jsonRequest.getDouble("dpd_sharia_dmtm_maintenance");
            double dpd_total = jsonRequest.getDouble("dpd_total");
            String note = jsonRequest.getString("note");
            String username = jsonRequest.getString("username");
            String updated_by = jsonRequest.getString("username");
            String id = jsonRequest.getString("id");

            if(id.equals("0")){
                Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
                String queryUpdate = "INSERT INTO eaq_dev.transaction_time_allocation  \r\n" +
                        " (team, \r\n" +
                        " con_agen_acquisiton, \r\n" +
                        " con_agen_maintenance, \r\n" +
                        " con_bancassurance_acquisition, \r\n" +
                        " con_bancassurance_maintenance, \r\n" +
                        " con_dmtm_acquisition, \r\n" +
                        " con_dmtm_maintenance, \r\n" +
                        " con_total, \r\n" +
                        " sharia_agen_acquisiton, \r\n" +
                        " sharia_agen_maintenance, \r\n" +
                        " sharia_bancassurance_acquisition, \r\n" +
                        " sharia_bancassurance_maintenance, \r\n" +
                        " sharia_dmtm_acquisition, \r\n" +
                        " sharia_dmtm_maintenance, \r\n" +
                        " sharia_total, \r\n" +
                        " project_con_agen_acquisiton, \r\n" +
                        " project_con_agen_maintenance, \r\n" +
                        " project_con_bancassurance_acquisition, \r\n" +
                        " project_con_bancassurance_maintenance, \r\n" +
                        " project_con_dmtm_acquisition, \r\n" +
                        " project_con_dmtm_maintenance, \r\n" +
                        " project_con_total, \r\n" +
                        " project_sharia_agen_acquisiton, \r\n" +
                        " project_sharia_agen_maintenance, \r\n" +
                        " project_sharia_bancassurance_acquisition, \r\n" +
                        " project_sharia_bancassurance_maintenance, \r\n" +
                        " project_sharia_dmtm_acquisition, \r\n" +
                        " project_sharia_dmtm_maintenance, \r\n" +
                        " project_sharia_total, \r\n" +
                        " dpd_agen_acquisiton, \r\n" +
                        " dpd_agen_maintenance, \r\n" +
                        " dpd_bancassurance_acquisition, \r\n" +
                        " dpd_bancassurance_maintenance, \r\n" +
                        " dpd_dmtm_acquisition, \r\n" +
                        " dpd_sharia_dmtm_maintenance, \r\n" +
                        " dpd_total, \r\n" +
                        " note, \r\n" +
                        " created_by, \r\n" +
                        " created_time)  \r\n" +
                        " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP)";

                PreparedStatement psmt = conn.prepareStatement(queryUpdate);
                psmt.setString(1, team);
                psmt.setDouble(2, con_agen_acquisiton);
                psmt.setDouble(3, con_agen_maintenance);
                psmt.setDouble(4, con_bancassurance_acquisition);
                psmt.setDouble(5, con_bancassurance_maintenance);
                psmt.setDouble(6, con_dmtm_acquisition);
                psmt.setDouble(7, con_dmtm_maintenance);
                psmt.setDouble(8, con_total);
                psmt.setDouble(9, sharia_agen_acquisiton);
                psmt.setDouble(10, sharia_agen_maintenance);
                psmt.setDouble(11, sharia_bancassurance_acquisition);
                psmt.setDouble(12, sharia_bancassurance_maintenance);
                psmt.setDouble(13, sharia_dmtm_acquisition);
                psmt.setDouble(14, sharia_dmtm_maintenance);
                psmt.setDouble(15, sharia_total);
                psmt.setDouble(16, project_con_agen_acquisiton);
                psmt.setDouble(17, project_con_agen_maintenance);
                psmt.setDouble(18, project_con_bancassurance_acquisition);
                psmt.setDouble(19, project_con_bancassurance_maintenance);
                psmt.setDouble(20, project_con_dmtm_acquisition);
                psmt.setDouble(21, project_con_dmtm_maintenance);
                psmt.setDouble(22, project_con_total);
                psmt.setDouble(23, project_sharia_agen_acquisiton);
                psmt.setDouble(24, project_sharia_agen_maintenance);
                psmt.setDouble(25, project_sharia_bancassurance_acquisition);
                psmt.setDouble(26, project_sharia_bancassurance_maintenance);
                psmt.setDouble(27, project_sharia_dmtm_acquisition);
                psmt.setDouble(28, project_sharia_dmtm_maintenance);
                psmt.setDouble(29, project_sharia_total);
                psmt.setDouble(30, dpd_agen_acquisiton);
                psmt.setDouble(31, dpd_agen_maintenance);
                psmt.setDouble(32, dpd_bancassurance_acquisition);
                psmt.setDouble(33, dpd_bancassurance_maintenance);
                psmt.setDouble(34, dpd_dmtm_acquisition);
                psmt.setDouble(35, dpd_sharia_dmtm_maintenance);
                psmt.setDouble(36, dpd_total);
                psmt.setString(37, note);
                psmt.setString(38, username);

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
            } else{
                Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
                String queryUpdate = "UPDATE eaq_dev.transaction_time_allocation  \r\n" +
                        "SET team =?,  \r\n" +
                        "    con_agen_acquisiton =?,  \r\n" +
                        "    con_agen_maintenance =?,  \r\n" +
                        "    con_bancassurance_acquisition =?,  \r\n" +
                        "    con_bancassurance_maintenance =?,  \r\n" +
                        "    con_dmtm_acquisition =?,  \r\n" +
                        "    con_dmtm_maintenance =?,  \r\n" +
                        "    con_total =?,  \r\n" +
                        "    sharia_agen_acquisiton =?,  \r\n" +
                        "    sharia_agen_maintenance =?,  \r\n" +
                        "    sharia_bancassurance_acquisition =?,  \r\n" +
                        "    sharia_bancassurance_maintenance =?,  \r\n" +
                        "    sharia_dmtm_acquisition =?,  \r\n" +
                        "    sharia_dmtm_maintenance =?,  \r\n" +
                        "    sharia_total =?,  \r\n" +
                        "    project_con_agen_acquisiton =?,  \r\n" +
                        "    project_con_agen_maintenance =?,  \r\n" +
                        "    project_con_bancassurance_acquisition =?,  \r\n" +
                        "    project_con_bancassurance_maintenance =?,  \r\n" +
                        "    project_con_dmtm_acquisition =?,  \r\n" +
                        "    project_con_dmtm_maintenance =?,  \r\n" +
                        "    project_con_total =?,  \r\n" +
                        "    project_sharia_agen_acquisiton =?,  \r\n" +
                        "    project_sharia_agen_maintenance =?,  \r\n" +
                        "    project_sharia_bancassurance_acquisition =?,  \r\n" +
                        "    project_sharia_bancassurance_maintenance =?,  \r\n" +
                        "    project_sharia_dmtm_acquisition =?,  \r\n" +
                        "    project_sharia_dmtm_maintenance =?,  \r\n" +
                        "    project_sharia_total =?,  \r\n" +
                        "    dpd_agen_acquisiton =?,  \r\n" +
                        "    dpd_agen_maintenance =?,  \r\n" +
                        "    dpd_bancassurance_acquisition =?,  \r\n" +
                        "    dpd_bancassurance_maintenance =?,  \r\n" +
                        "    dpd_dmtm_acquisition =?,  \r\n" +
                        "    dpd_sharia_dmtm_maintenance =?,  \r\n" +
                        "    dpd_total =?,  \r\n" +
                        "    note =?,  \r\n" +
                        "    created_by =?,  \r\n" +
                        "    created_time =?,  \r\n" +
                        "    updated_by =?,  \r\n" +
                        "    updated_time =? WHERE id = ?";

                PreparedStatement psmt = conn.prepareStatement(queryUpdate);
                psmt.setString(1, team);
                psmt.setDouble(2, con_agen_acquisiton);
                psmt.setDouble(3, con_agen_maintenance);
                psmt.setDouble(4, con_bancassurance_acquisition);
                psmt.setDouble(5, con_bancassurance_maintenance);
                psmt.setDouble(6, con_dmtm_acquisition);
                psmt.setDouble(7, con_dmtm_maintenance);
                psmt.setDouble(8, con_total);
                psmt.setDouble(9, sharia_agen_acquisiton);
                psmt.setDouble(10, sharia_agen_maintenance);
                psmt.setDouble(11, sharia_bancassurance_acquisition);
                psmt.setDouble(12, sharia_bancassurance_maintenance);
                psmt.setDouble(13, sharia_dmtm_acquisition);
                psmt.setDouble(14, sharia_dmtm_maintenance);
                psmt.setDouble(15, sharia_total);
                psmt.setDouble(16, project_con_agen_acquisiton);
                psmt.setDouble(17, project_con_agen_maintenance);
                psmt.setDouble(18, project_con_bancassurance_acquisition);
                psmt.setDouble(19, project_con_bancassurance_maintenance);
                psmt.setDouble(20, project_con_dmtm_acquisition);
                psmt.setDouble(21, project_con_dmtm_maintenance);
                psmt.setDouble(22, project_con_total);
                psmt.setDouble(23, project_sharia_agen_acquisiton);
                psmt.setDouble(24, project_sharia_agen_maintenance);
                psmt.setDouble(25, project_sharia_bancassurance_acquisition);
                psmt.setDouble(26, project_sharia_bancassurance_maintenance);
                psmt.setDouble(27, project_sharia_dmtm_acquisition);
                psmt.setDouble(28, project_sharia_dmtm_maintenance);
                psmt.setDouble(29, project_sharia_total);
                psmt.setDouble(30, dpd_agen_acquisiton);
                psmt.setDouble(31, dpd_agen_maintenance);
                psmt.setDouble(32, dpd_bancassurance_acquisition);
                psmt.setDouble(33, dpd_bancassurance_maintenance);
                psmt.setDouble(34, dpd_dmtm_acquisition);
                psmt.setDouble(35, dpd_sharia_dmtm_maintenance);
                psmt.setDouble(36, dpd_total);
                psmt.setString(37, note);
                psmt.setString(38, updated_by);
                psmt.setDate(39, new java.sql.Date(System.currentTimeMillis()));
                psmt.setString(40, id);

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
            }
        } catch (SQLException e) {
            logger.error("update time allocation : " + e.getMessage());
            result.setMessage("500");
            result.setSuccess(false);
            return result;
        } catch (Exception e) {
            logger.error("update time allocation : " + e.getMessage());
            result.setMessage("500");
            result.setSuccess(false);
            return result;
        }

    }
}
