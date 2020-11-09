package id.aldente.socket.services;

import id.aldente.socket.config.DBConnectionService;
import id.aldente.socket.util.BaseResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.prudential.platform.stream.compute.NonBlocking;

import java.sql.*;

/**
 * Created by f.putra on 11/10/20.
 */
public class ApproveDirectAllocation {

    private static final Logger logger = LoggerFactory.getLogger(DepartementListServices.class);


    @NonBlocking
    public static BaseResponse<JsonArray> approve(JSONObject jsonRequest, String dbeaqUrl, String dbeaqUser, String dbeaqPassword) {
        BaseResponse<JsonArray> result = new BaseResponse<JsonArray>();
        try {
            Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
            String queryUpdate = "UPDATE transaction_direct_allocation SET is_approve = ? WHERE kode = ?";

            try {
                PreparedStatement psmt = conn.prepareStatement(queryUpdate);
                psmt.setBoolean(1, jsonRequest.getBoolean("approve"));
                psmt.setString(2, jsonRequest.getString("kode"));
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
