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
//            Connection conn = DBConnectionService.connect(dbeaqUrl, dbeaqUser, dbeaqPassword);
//            obj.getData().forEach(v -> {
//                String queryUpdate = "UPDATE eaq_dev.transaction_time_allocation " +
//                        "SET description = ?, sun_ac_description = ?, expense_type = ?, attributablilty_attributable = ?, " +
//                        "attributablilty_non_attributable =  ?, attributablilty_total =  ?, benefit_for_acquisition =  ?, benefit_for_maintenance =  ?, " +
//                        "benefit_for_total =  ?, acquisition_non_pre_coverage =  ?, acquisition_pre_coverage =  ?, acquisition_total =  ?, " +
//                        "maintenance_premium_related =  ?, maintenance_non_premium_related =  ?, maintenance_total =  ?,  allocation_dirver =  ?, " +
//                        "remarks =  ?, created_by =  ?, created_time =  ?, updated_by =  ?, " +
//                        "updated_time =  ?, approve_by =  ?, approve_time =  ?, is_approve =  ? WHERE account = ?";
//
//                try {
//                    PreparedStatement psmt = conn.prepareStatement(queryUpdate);
//                    psmt.setString(1, v.getDescription());
//                    psmt.setString(2, v.getSun_ac_description());
//                    psmt.setString(3, v.getExpense_type());
//                    psmt.setDouble(4, v.getAttributablilty_attributable());
//                    psmt.setDouble(5, v.getAttributablilty_non_attributable());
//                    psmt.setDouble(6, v.getAttributablilty_total());
//                    psmt.setDouble(7, v.getBenefit_for_acquisition());
//                    psmt.setDouble(8, v.getBenefit_for_maintenance());
//                    psmt.setDouble(9, v.getBenefit_for_total());
//                    psmt.setDouble(10, v.getAcquisition_non_pre_coverage());
//                    psmt.setDouble(11, v.getAcquisition_pre_coverage());
//                    psmt.setDouble(12, v.getAcquisition_total());
//                    psmt.setDouble(13, v.getMaintenance_premium_related());
//                    psmt.setDouble(14, v.getMaintenance_non_premium_related());
//                    psmt.setDouble(15, v.getMaintenance_total());
//                    psmt.setString(16, v.getAllocation_dirver());
//                    psmt.setString(17, v.getRemarks());
//                    psmt.setString(18, v.getCreatedBy());
//                    psmt.setString(19, v.getCreatedTime());
//                    psmt.setString(20, v.getUpdatedBy());
//                    psmt.setString(21, v.getUpdatedTime());
//                    psmt.setString(22, v.getApproveBy());
//                    psmt.setString(23, v.getApproveTime());
//                    psmt.setBoolean(24, v.isApprove());
//                    psmt.setLong(25, v.getAccount());
//                    psmt.executeUpdate();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            });
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
