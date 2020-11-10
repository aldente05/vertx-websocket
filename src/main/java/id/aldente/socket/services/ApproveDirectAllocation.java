package id.aldente.socket.services;

import id.aldente.socket.config.DBConnectionService;
import id.aldente.socket.util.BaseResponse;
import io.vertx.core.json.JsonArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.prudential.platform.stream.compute.NonBlocking;

import java.sql.*;

/**
 * Created by f.putra on 11/10/20.
 */
public class ApproveDirectAllocation {

    private static final Logger logger = LoggerFactory.getLogger(ApproveDirectAllocation.class);

    @NonBlocking
    public static BaseResponse<JsonArray> approve(JSONObject jsonRequest, String dbeaqUrl, String dbeaqUser, String dbeaqPassword) {
        BaseResponse<JsonArray> result = new BaseResponse<JsonArray>();
        try {
            Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
            String queryUpdate = "UPDATE eaq_dev.transaction_direct_allocation SET approve_by = ?, approve_time = ?, is_approve = ? WHERE kode = ?";

            PreparedStatement psmt = conn.prepareStatement(queryUpdate);
            psmt.setString(1, jsonRequest.getString("username"));
            psmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            psmt.setBoolean(3, jsonRequest.getBoolean("approve"));
            psmt.setString(4, jsonRequest.getString("kode"));
            result.setMessage("Approve Success");
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
