package id.aldente.socket.util;

import com.prudential.platform.data.EntityMessageSpec;
import com.prudential.platform.domain.Message;
import com.prudential.platform.stream.compute.NonBlocking;
import com.prudential.platform.stream.compute.StatelessFunction;
import com.prudential.platform.stream.compute.workflow.SimpleWorkflow;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.GregorianCalendar;

public class CommonData {
    private static final Logger logger = LoggerFactory.getLogger(CommonData.class);

    @NonBlocking
    public static Message<?> psql(StatelessFunction.Context state, String queryText, JsonArray params, String operation) throws Exception {
        return psql(state, queryText, params, false,operation);
    }

    @NonBlocking
    public static Message<?> psql(SimpleWorkflow.StateInstance state, String queryText, JsonArray params, String operation) throws Exception {
        return psql(state, queryText, params, false,operation);
    }

    @NonBlocking
    public static Message<?> psql(StatelessFunction.Context state, String queryText, JsonArray params, boolean isDml, String operation) throws Exception {
        JsonObject json = new JsonObject().put("query", queryText);

        if (params != null && params.size() > 0) {
            json.put("params", params);
        }

        if (isDml) {
            json.put("method", "dml");
        }

        Date tic = GregorianCalendar.getInstance().getTime();

        logger.info("query = {}", json);

        Message<?> query = state.newMessage(operation);
        query.setBodyAsString(Json.encode(json));

        Message<?> dbReply = state.invoke(query);
        if (dbReply == null) throw new Exception("dbReply is null");

        Date toc = GregorianCalendar.getInstance().getTime();
        logger.info("dbReply ({}ms) = {}, result = {}", toc.getTime() - tic.getTime(), dbReply, dbReply.getBodyAsString());

        return dbReply;
    }

    @NonBlocking
    public static Message<?> uploadDocToDb(StatelessFunction.Context state, String npaNumber, JsonArray documents, String operation) throws Exception {
    	JsonObject json = new JsonObject();
    	json.put("npaNo", npaNumber);
    	json.put("documents", documents);

    	Date tic = GregorianCalendar.getInstance().getTime();

    	Message<?> upload = state.newMessage(operation);
    	upload.setBodyAsString(Json.encode(json));

    	Message<?> dbReply = state.invoke(upload);
        if (dbReply == null) throw new Exception("dbReply is null");

        Date toc = GregorianCalendar.getInstance().getTime();
        logger.info("dbReply ({}ms) = {}, result = {}", toc.getTime() - tic.getTime(), dbReply, dbReply.getBodyAsString());

    	return dbReply;
    }

    @NonBlocking
    public static Message<?> psql(SimpleWorkflow.StateInstance state, String queryText, JsonArray params, boolean isDml, String operation) throws Exception {
        JsonObject json = new JsonObject().put("query", queryText);

        if (params != null && params.size() > 0) {
            json.put("params", params);
        }

        if (isDml) {
            json.put("method", "dml");
        }

        Date tic = GregorianCalendar.getInstance().getTime();

        logger.info("query = {}", json);

        Message<?> query = state.newMessage(operation);
        query.setBodyAsString(Json.encode(json));

        Message<?> dbReply = state.asyncInvoker().invoke(query);
        if (dbReply == null) throw new Exception("dbReply is null");

        Date toc = GregorianCalendar.getInstance().getTime();
        logger.info("dbReply ({}ms) = {}, result = {}", toc.getTime() - tic.getTime(), dbReply, dbReply.getBodyAsString());

        return dbReply;
    }

    @NonBlocking
    public static Message<?> queryEntity(StatelessFunction.Context state, String queryText, JsonObject params) throws Exception {
        JsonObject json = new JsonObject().put("query", queryText);

        if (params != null && !params.isEmpty()) {
            json.put("namedParams", params);
        }

        Date tic = GregorianCalendar.getInstance().getTime();

        if (logger.isDebugEnabled()) {
            logger.debug("query = {}", json);
        }

        Message<?> query = state.newMessage("queryEntity");
        query.setBodyAsString(Json.encode(json));

        Message<?> dbReply = state.invoke(query);
        if (dbReply == null) throw new Exception("dbReply is null");
        if (logger.isDebugEnabled()) {
            Date toc = GregorianCalendar.getInstance().getTime();
            logger.debug("dbReply ({}ms) = {}, result = {}", toc.getTime() - tic.getTime(), dbReply, dbReply.getBodyAsString());
        }

        return dbReply;
    }

    @NonBlocking
    public static Message<?> queryEntity(SimpleWorkflow.StateInstance state, String queryText, JsonObject params) throws Exception {
        JsonObject json = new JsonObject().put("query", queryText);

        if (params != null && !params.isEmpty()) {
            json.put("namedParams", params);
        }

        Date tic = GregorianCalendar.getInstance().getTime();

        if (logger.isDebugEnabled()) {
            logger.debug("query = {}", json);
        }

        Message<?> query = state.newMessage("queryEntity");
        query.setBodyAsString(Json.encode(json));

        Message<?> dbReply = state.asyncInvoker().invoke(query);
        if (dbReply == null) throw new Exception("dbReply is null");
        if (logger.isDebugEnabled()) {
            Date toc = GregorianCalendar.getInstance().getTime();
            logger.debug("dbReply ({}ms) = {}, result = {}", toc.getTime() - tic.getTime(), dbReply, dbReply.getBodyAsString());
        }

        return dbReply;
    }

    @NonBlocking
    public static Message<?> readEntity(StatelessFunction.Context state, String bucket, String entityType, String entityId) {
        Message<?> read = state.newMessage("readEntity");
        EntityMessageSpec.setBucketName(read, bucket);
        EntityMessageSpec.setEntityType(read, entityType);
        EntityMessageSpec.setEntityId(read, entityId);

        Message<?> dbReply = state.invoke(read);
        if (dbReply == null) throw new NullPointerException("dbReply is null");
        if (logger.isDebugEnabled()) {
            logger.debug("dbReply = {}", dbReply);
        }

        return dbReply;
    }

    @NonBlocking
    public static Message<?> readEntity(SimpleWorkflow.StateInstance state, String bucket, String entityType, String entityId) {
        Message<?> read = state.newMessage("readEntity");
        EntityMessageSpec.setBucketName(read, bucket);
        EntityMessageSpec.setEntityType(read, entityType);
        EntityMessageSpec.setEntityId(read, entityId);

        Message<?> dbReply = state.asyncInvoker().invoke(read);
        if (dbReply == null) throw new NullPointerException("dbReply is null");
        if (logger.isDebugEnabled()) {
            logger.debug("dbReply = {}", dbReply);
        }

        return dbReply;
    }

    @NonBlocking
    public static Message<?> createEntity(StatelessFunction.Context state, String bucket, String entityType, String entityId, Object obj) {
        Message<?> write = state.newMessage("createEntity");
        EntityMessageSpec.setBucketName(write, bucket);
        EntityMessageSpec.setEntityType(write, entityType);
        EntityMessageSpec.setEntityId(write, entityId);

        write.setBodyAsString(Json.encode(obj));

        Message<?> dbReply = state.invoke(write);
        if (dbReply == null) throw new NullPointerException("dbReply is null");
        if (logger.isDebugEnabled()) {
            logger.debug("dbReply = {}, result = {}", dbReply, dbReply.getBodyAsString());
        }

        return dbReply;
    }

    @NonBlocking
    public static Message<?> createEntity(SimpleWorkflow.StateInstance state, String bucket, String entityType, String entityId, Object obj) {
        Message<?> write = state.newMessage("createEntity");
        EntityMessageSpec.setBucketName(write, bucket);
        EntityMessageSpec.setEntityType(write, entityType);
        EntityMessageSpec.setEntityId(write, entityId);

        write.setBodyAsString(Json.encode(obj));

        Message<?> dbReply = state.asyncInvoker().invoke(write);
        if (dbReply == null) throw new NullPointerException("dbReply is null");
        if (logger.isDebugEnabled()) {
            logger.debug("dbReply = {}, result = {}", dbReply, dbReply.getBodyAsString());
        }

        return dbReply;
    }

    @NonBlocking
    public static Message<?> deleteEntity(StatelessFunction.Context state, String bucket, String entityType, String entityId) {
        Message<?> delete = state.newMessage("deleteEntity");
        EntityMessageSpec.setBucketName(delete, bucket);
        EntityMessageSpec.setEntityType(delete, entityType);
        EntityMessageSpec.setEntityId(delete, entityId);

        Message<?> dbReply = state.invoke(delete);
        if (dbReply == null) throw new NullPointerException("dbReply is null");
        if (logger.isDebugEnabled()) {
            logger.debug("dbReply = {}, result = {}", dbReply, dbReply.getBodyAsString());
        }

        return dbReply;
    }

    @NonBlocking
    public static Message<?> deleteEntity(SimpleWorkflow.StateInstance state, String bucket, String entityType, String entityId) {
        Message<?> delete = state.newMessage("deleteEntity");
        EntityMessageSpec.setBucketName(delete, bucket);
        EntityMessageSpec.setEntityType(delete, entityType);
        EntityMessageSpec.setEntityId(delete, entityId);

        Message<?> dbReply = state.asyncInvoker().invoke(delete);
        if (dbReply == null) throw new NullPointerException("dbReply is null");
        if (logger.isDebugEnabled()) {
            logger.debug("dbReply = {}, result = {}", dbReply, dbReply.getBodyAsString());
        }

        return dbReply;
    }


}
