package id.aldente.socket.services;

import id.aldente.socket.config.DBConnectionService;
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
public class TimeAllocationListServices {

    private static final Logger logger = LoggerFactory.getLogger(TimeAllocationListServices.class);

    @NonBlocking
    public static BaseResponse<JsonArray> getTimeAllocationList(JSONObject jsonRequest, String dbeaqUrl, String dbeaqUser, String dbeaqPassword) {
        BaseResponse<JsonArray> result = new BaseResponse<JsonArray>();
        try {
            String kode = jsonRequest.getString("kode");
            String SQLGetPdTaskListClaimSharia = "SELECT * FROM eaq_dev.transaction_time_allocation WHERE kode = ? ";
            Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
            PreparedStatement psmt = conn.prepareStatement(SQLGetPdTaskListClaimSharia);
            psmt.setString(1, "%" + kode + "%");
            ResultSet rs = psmt.executeQuery();
            JsonArray json = new JsonArray();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                int numColumns = rsmd.getColumnCount();
                JsonObject obj = new JsonObject();
                for (int i = 1; i <= numColumns; i++) {
                    String column_name = rsmd.getColumnName(i);
                    obj.put(column_name, String.valueOf(rs.getObject(column_name)));
                }
                json.add(obj);
            }
            result.setData(json);
            result.setMessage("success");
            result.setSuccess(true);
            return result;
        } catch (SQLException e) {
            logger.error("eaqDeparement SQLException : " + e.toString());
            logger.error("eaqDeparement : " + e.getMessage());
            result.setMessage("500");
            result.setSuccess(false);
            return result;
        } catch (Exception e) {
            logger.error("eaqDeparement Exception : " + e.toString());
            logger.error("eaqDeparement : " + e.getMessage());
            result.setMessage("500");
            result.setSuccess(false);
            return result;
        }
    }
}
