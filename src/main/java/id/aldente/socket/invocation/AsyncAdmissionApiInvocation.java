package id.aldente.socket.invocation;

import id.aldente.socket.helper.RequestHelperMethod;
import id.aldente.socket.model.payload.DepartementPayload;
import id.aldente.socket.model.response.DepartementResponse;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * Created by f.putra on 7/23/20.
 */
public class AsyncAdmissionApiInvocation extends RichAsyncFunction<DepartementPayload, DepartementResponse> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AsyncAdmissionApiInvocation.class);
    private final Integer apiTimeoutMs;

    /**
     * The Asynchronous client that can issue concurrent requests with callbacks
     */
    private transient AsyncHttpClient asyncHttpClient = null;

    public AsyncAdmissionApiInvocation(Integer apiTimeoutMs) {
        this.apiTimeoutMs = apiTimeoutMs;
    }

    @Override
    public void open(Configuration parameters) {
        logger.info("Opening connection " + parameters.toString());
        this.asyncHttpClient = new DefaultAsyncHttpClient();
    }

    @Override
    public void close() throws Exception {
        logger.info("Closing connection");
        super.close();
        asyncHttpClient.close();
    }

    @Override
    public void timeout(DepartementPayload admissionRequest, ResultFuture<DepartementResponse> resultFuture) throws Exception {
//    AdmissionResponse admissionResponse = new AdmissionResponse();
//    admissionResponse.setSuccess(false);
//    admissionResponse.setSenderId(admissionRequest.getSenderId());
//    admissionResponse.setError("[TimeoutException Api-Invocation]");
//    admissionResponse.setCause("Timeout occurred during registration");
//    resultFuture.complete(Collections.singletonList(admissionResponse));
    }

    @Override
    public void asyncInvoke(DepartementPayload admissionRequest, final ResultFuture<DepartementResponse> resultFuture) throws Exception {
        // issue the asynchronous request, receive a future for result
        DepartementResponse responseMessage = new DepartementResponse();
        System.out.println(admissionRequest.toString());
        try {
            switch (admissionRequest.getMethod()) {
                case RequestHelperMethod.CREATE:
//          bucket.defaultCollection().async().insert(admissionRequest.getPolicyNo(), admissionRequest);
//          responseMessage.setSenderId(admissionRequest.getSenderId());
//          responseMessage.setSuccess(true);
//          responseMessage.setData(admissionRequest);
//          responseMessage.setMessageType(MessageType.REST);
//          responseMessage.setMessageType(MessageType.REST);
//          resultFuture.complete(Collections.singletonList(responseMessage));
                    break;
                case RequestHelperMethod.UPDATE:
                    break;
                case RequestHelperMethod.DELETE:

                    break;
                case RequestHelperMethod.READ:
//          DepartementPayload resultAdmision = bucket.defaultCollection().get(admissionRequest.getPolicyNo()).contentAs(DepartementPayload.class);
//          responseMessage.setSenderId(admissionRequest.getSenderId());
//          responseMessage.setSuccess(true);
//          responseMessage.setData(resultAdmision);
//          responseMessage.setMessageType(MessageType.REST);
//          responseMessage.setMessageType(MessageType.REST);
//          resultFuture.complete(Collections.singletonList(responseMessage));
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            logger.error("Exception [HTTP] Client " + ex);
        }
    }
}
