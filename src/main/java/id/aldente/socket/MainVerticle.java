package id.aldente.socket;

import com.google.gson.Gson;
import id.aldente.socket.services.*;
import id.aldente.socket.util.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;
import com.prudential.platform.domain.Endpoint;
import com.prudential.platform.domain.Message;
import com.prudential.platform.domain.Operation;
import com.prudential.platform.stream.compute.StatelessFunction;
import com.prudential.platform.stream.env.Env;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainVerticle {
    private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

    FileUtil fileUtil;

    static AppConfig config = null;

    public static void main(String[] args) {
        logger.info("Job " + MainVerticle.class.getName() + " Run ");

        // Read Properties File
        Map<String, String> properties =new HashMap<String,String>();

        if(args.length>0) {
            System.out.println(args);
            System.out.println(args[0]);
            String fileName = args[0];
            logger.info("properties file "+fileName);
            properties =  FileUtil.readProperties(fileName);
            config = FileUtil.getConfig(properties,"");
        }

        //  Get Properties as an Object
        if(config==null) {
            logger.error("Config Properties null");
            return;
        }

        //  final AppConfig cfg=config;
        System.out.println("EndpointName : " + config.getDataSourceName());
        Endpoint ep = Env.instance.createEndpoint(config.getDataSourceName(), true);
        ep.addOperation(new Operation(config.getDataSourceName()));
        StatelessFunction func = new StatelessFunction(ep);

        try {
            Map<String, String> finalMapProperties = properties;
            func.onMessage((m, state) -> {
                String jasypt = finalMapProperties.get("jasypt");
                StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
                ZeroSaltGenerator salt = new ZeroSaltGenerator();
                encryptor.setPassword(jasypt);
                encryptor.setSaltGenerator(salt);
                Gson g = new Gson();
                Message<?> reply = m.reply();
                JSONObject jsonRequest = new JSONObject(m.getBodyAsString());
                logger.info("jsonRequest : " + jsonRequest);
                if(!jsonRequest.isNull("accessToken") && !jsonRequest.isNull("operation")) {
                    String operation = jsonRequest.getString("operation");

                    // Check token is valid
                    String access_token = jsonRequest.getString("accessToken");
                    String URL = encryptor.decrypt(finalMapProperties.get("pruhubBaseValidateToken"));
                    logger.info("URL VALIDATE : "+URL);
                    logger.info("TOKEN :"+access_token);
                    JSONObject tokenValidateResponse = HttpUtility.post(URL, "Bearer-"+access_token, new JSONObject("{}"), false);
                    if(!tokenValidateResponse.isNull("success")) {
                        if(!tokenValidateResponse.getBoolean("success")) {
                            logger.info("accessToken Unauthorized");;
                            logger.info("reply.getStatus : " + reply.getStatus());
                            setResponse(reply, CommonMessage.INVALID_REQUEST,"accessToken Unauthorized");
                            return reply;
                        }
                    }

                    String result = null;
                    String dbeaqUrl = encryptor.decrypt(finalMapProperties.get("DBeaqUrl"));
                    String dbeaqUser = encryptor.decrypt(finalMapProperties.get("DBeaqUser"));
                    String dbeaqPassword = encryptor.decrypt(finalMapProperties.get("DBeaqPassword"));
//                    String dbEmailUrl = encryptor.decrypt(finalMapProperties.get("DBEmailUrl"));
//                    String dbEmailUser = encryptor.decrypt(finalMapProperties.get("DBEmailUser"));
//                    String dbEmailPassword = encryptor.decrypt(finalMapProperties.get("DBEmailPassword"));
//                    String edocUrl = encryptor.decrypt(finalMapProperties.get("EdocUrl"));
//                    String emailClaimNeedPaymentAdminApproval = encryptor.decrypt(finalMapProperties.get("EmailClaimNeedPaymentAdminApproval"));
//                    String emailClaimNeedPDRevision = encryptor.decrypt(finalMapProperties.get("EmailClaimNeedPDRevision"));
//                    String emailClaimNeedAmlApproval = encryptor.decrypt(finalMapProperties.get("EmailClaimNeedAmlApproval"));
//                    String baseOutputFilePath = encryptor.decrypt(finalMapProperties.get("BaseOutputFilePath"));
                    switch (operation) {

                        case "departementList":
                            logger.info("get Departement");
                            result = g.toJson(DepartementListServices.departementList(jsonRequest, dbeaqUrl, dbeaqUser, dbeaqPassword));
                            reply.setBodyAsString(result);
                            setResponse(reply, CommonMessage.SUCCESS);
                            break;

                        case "allocationDriver":
                            logger.info("get allocationDriver");
                            result = g.toJson(AllocationDriverListServices.getAllocationDriver(jsonRequest, dbeaqUrl, dbeaqUser, dbeaqPassword));
                            reply.setBodyAsString(result);
                            setResponse(reply, CommonMessage.SUCCESS);
                            break;

                        case "direct-allocation-list":
                            logger.info("get transaction");
                            result = g.toJson(DirectAllocationListServices.getDirectAllocationList(jsonRequest, dbeaqUrl, dbeaqUser, dbeaqPassword));
                            reply.setBodyAsString(result);
                            setResponse(reply, CommonMessage.SUCCESS);
                            break;

                        case "time-allocation-list":
                            logger.info("get transaction");
                            result = g.toJson(TimeAllocationListServices.getTimeAllocationList(jsonRequest, dbeaqUrl, dbeaqUser, dbeaqPassword));
                            reply.setBodyAsString(result);
                            setResponse(reply, CommonMessage.SUCCESS);
                            break;

                        case "direct-allocation-create":
                            logger.info("post direct allocation transaction");
                            result = g.toJson(UpdateDirectAllocationServices.updateDirectAllocation(jsonRequest, dbeaqUrl, dbeaqUser, dbeaqPassword));
                            reply.setBodyAsString(result);
                            setResponse(reply, CommonMessage.SUCCESS);
                            break;
                        case "direct-allocation-approve":
                            logger.info("post direct allocation transaction");
                            result = g.toJson(ApproveDirectAllocation.approve(jsonRequest, dbeaqUrl, dbeaqUser, dbeaqPassword));
                            reply.setBodyAsString(result);
                            setResponse(reply, CommonMessage.SUCCESS);
                            break;

                        case "time-allocation-create":
                            logger.info("post direct allocation transaction");
                            result = g.toJson(UpdateTimeAllocationServices.updateTimeAllocation(jsonRequest, dbeaqUrl, dbeaqUser, dbeaqPassword));
                            reply.setBodyAsString(result);
                            setResponse(reply, CommonMessage.SUCCESS);
                            break;
                        default:
                            setResponse(reply, CommonMessage.INVALID_REQUEST, "operation name undefined");
                            break;
                    }

                }else {
                    setResponse(reply, CommonMessage.INVALID_REQUEST,"accessToken & operation no request found");
                }
                return reply;

            }).execute();
            System.out.println("After StatelessFunction");
        } catch (Exception e) {
            logger.error("Job exit with error", e);
        }
    }

    private static void setResponse(Message<?> m, IMessage m2) {
        setResponse(m, m2, m2.getMessage());
    }

    private static void setResponse(Message<?> m, IMessage m2, String m3) {
        m.getStatus().setCode(m2.getCode());
        m.getStatus().setShortCode(m2.toString());
        m.getStatus().setMessage(m3);
    }
}

