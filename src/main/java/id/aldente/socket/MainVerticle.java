package id.aldente.socket;

import id.aldente.socket.config.PostgreSQLVerticle;
import id.aldente.socket.model.DirectAllocationParser;
import id.aldente.socket.model.MessageType;
import id.aldente.socket.model.payload.DepartementPayload;
import id.aldente.socket.model.payload.DirectAllocationPayload;
import id.aldente.socket.model.response.DepartementResponse;
import id.aldente.socket.model.response.DirectAllocationResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainVerticle extends AbstractVerticle {
    public static Logger getLogger(Object o) {
        return LoggerFactory.getLogger(o.getClass());
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        // Create a router object.
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route().handler(routingContext -> {
            MainVerticle.getLogger(this).info("http request is comming ...: " + routingContext.request().method() + " - " + routingContext.request().absoluteURI());
            routingContext.response().putHeader("Content-Type", "application/json;charset=UTF-8");
            routingContext.next();
        });
        // Bind "/" to our hello message - so we are still compatible.

        router.route(HttpMethod.GET, "/departement").handler(routingContext -> {
            try {
                PgPool postgreSQLVerticle = new PostgreSQLVerticle().instance(vertx);
                postgreSQLVerticle.query("SELECT DISTINCT kode, nama FROM master_direct_allocation ").execute(ar -> {
                    DepartementResponse departementResponse = new DepartementResponse();
                    List<DepartementPayload> departementPayload = new ArrayList<>();
                    if (ar.succeeded()) {
                        ar.result().forEach(v -> {
                            DepartementPayload payload = new DepartementPayload();
                            payload.setCode(v.getValue("kode").toString());
                            payload.setDepartement(v.getValue("nama").toString());
                            departementPayload.add(payload);
                        });
                        departementResponse.setData(departementPayload);
                        departementResponse.setSuccess(true);
                        departementResponse.setMessageType(MessageType.REST);
                        routingContext.response()
                                .setStatusCode(200)
                                .end(Json.encodePrettily(departementResponse));
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                });
                // Now close the pool
                postgreSQLVerticle.close();
            } catch (Exception e) {
                MainVerticle.getLogger(this).error("ERROR " + e.getMessage());
                routingContext.response().setStatusCode(400).end();
            }
        });

        router.route(HttpMethod.GET, "/direct-allocation-transaction/:idGOC").handler(routingContext -> {
            try {
                PgPool postgreSQLVerticle = new PostgreSQLVerticle().instance(vertx);
                postgreSQLVerticle.preparedQuery("SELECT * FROM transaction_direct_allocation WHERE kode = $1 ")
                        .execute(Tuple.of(routingContext.request().getParam("idGOC")), ar -> {
                            DirectAllocationResponse directAllocationResponse = new DirectAllocationResponse();
                            List<DirectAllocationPayload> directAllocationPayload = new ArrayList<>();
                            if (ar.succeeded()) {
                                ar.result().forEach(v -> {
                                    try {
                                        DirectAllocationPayload payload = new DirectAllocationPayload();
                                        payload.setId(v.getInteger("id"));
                                        payload.setKode(v.getValue("kode").toString());
                                        payload.setDepartement(v.getValue("departement").toString());
                                        payload.setAccount(v.getValue("account") == null ? null : (Long) v.getValue("account"));
                                        payload.setDescription(v.getValue("description") == null ? null : v.getValue("description").toString());
                                        payload.setSun_ac_description(v.getValue("sun_ac_description") == null ? null : v.getValue("sun_ac_description").toString());
                                        payload.setExpense_type(v.getValue("expense_type") == null ? null : v.getValue("expense_type").toString());
                                        payload.setAttributablilty_attributable(v.getDouble("attributablilty_attributable") == null ? null : v.getDouble("attributablilty_attributable"));
                                        payload.setAttributablilty_non_attributable(v.getDouble("attributablilty_non_attributable") == null ? null : v.getDouble("attributablilty_non_attributable"));
                                        payload.setAttributablilty_total(v.getDouble("attributablilty_total") == null ? null : v.getDouble("attributablilty_total"));
                                        payload.setBenefit_for_acquisition(v.getDouble("benefit_for_acquisition") == null ? null : v.getDouble("benefit_for_acquisition"));
                                        payload.setBenefit_for_maintenance(v.getDouble("benefit_for_maintenance") == null ? null : v.getDouble("benefit_for_maintenance"));
                                        payload.setBenefit_for_total(v.getDouble("benefit_for_total") == null ? null : v.getDouble("benefit_for_total"));
                                        payload.setAcquisition_non_pre_coverage(v.getDouble("acquisition_non_pre_coverage") == null ? null : v.getDouble("acquisition_non_pre_coverage"));
                                        payload.setAcquisition_pre_coverage(v.getDouble("acquisition_pre_coverage") == null ? null : v.getDouble("acquisition_pre_coverage"));
                                        payload.setAcquisition_total(v.getDouble("acquisition_total") == null ? null : v.getDouble("acquisition_total"));
                                        payload.setMaintenance_premium_related(v.getDouble("maintenance_premium_related") == null ? null : v.getDouble("maintenance_premium_related"));
                                        payload.setMaintenance_non_premium_related(v.getDouble("maintenance_non_premium_related") == null ? null : v.getDouble("maintenance_non_premium_related"));
                                        payload.setMaintenance_total(v.getDouble("maintenance_total") == null ? null : v.getDouble("maintenance_total"));
                                        payload.setAllocation_dirver(v.getString("allocation_dirver") == null ? null : v.getString("allocation_dirver"));
                                        payload.setRemarks(v.getString("remarks") == null ? null : v.getString("remarks"));
                                        payload.setCreatedBy(v.getString("created_by") == null ? null : v.getString("created_by"));
                                        payload.setCreatedTime(v.getValue("created_time") == null ? null : v.getValue("created_time").toString());
                                        payload.setUpdatedBy(v.getValue("updated_by") == null ? null : v.getValue("updated_by").toString());
                                        payload.setUpdatedTime(v.getValue("updated_time") == null ? null : v.getValue("updated_time").toString());
                                        payload.setApproveBy(v.getString("approve_by") == null ? null : v.getString("approve_by"));
                                        payload.setApproveTime(v.getValue("approve_time") == null ? null : v.getValue("approve_time").toString());
                                        payload.setApprove((Boolean) v.getValue("is_approve"));
                                        directAllocationPayload.add(payload);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                });
                                directAllocationResponse.setData(directAllocationPayload);
                                directAllocationResponse.setSuccess(true);
                                directAllocationResponse.setMessageType(MessageType.REST);
                                routingContext.response()
                                        .setStatusCode(200)
                                        .end(Json.encodePrettily(directAllocationResponse));
                            } else {
                                System.out.println("Failure: " + ar.cause().getMessage());
                            }
                        });
                // Now close the pool
                postgreSQLVerticle.close();
            } catch (Exception e) {
                MainVerticle.getLogger(this).error("ERROR " + e.getMessage());
                routingContext.response().setStatusCode(400).end();
            }
        });

        router.route(HttpMethod.GET, "/time-allocation-transaction").handler(routingContext -> {
            try {
                PgPool postgreSQLVerticle = new PostgreSQLVerticle().instance(vertx);
                postgreSQLVerticle.query("SELECT DISTINCT kode, nama FROM master_direct_allocation ").execute(ar -> {
                    DepartementResponse departementResponse = new DepartementResponse();
                    List<DepartementPayload> departementPayload = new ArrayList<>();
                    if (ar.succeeded()) {
                        ar.result().forEach(v -> {
                            DepartementPayload payload = new DepartementPayload();
                            payload.setCode(v.getValue("kode").toString());
                            payload.setDepartement(v.getValue("nama").toString());
                            departementPayload.add(payload);
                        });
                        departementResponse.setData(departementPayload);
                        departementResponse.setSuccess(true);
                        departementResponse.setMessageType(MessageType.REST);
                        routingContext.response()
                                .setStatusCode(200)
                                .end(Json.encodePrettily(departementResponse));
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                });
                // Now close the pool
                postgreSQLVerticle.close();
            } catch (Exception e) {
                MainVerticle.getLogger(this).error("ERROR " + e.getMessage());
                routingContext.response().setStatusCode(400).end();
            }
        });

        router.route(HttpMethod.POST, "/direct-allocation-transaction").handler(routingContext -> {
            try {
                PgPool postgreSQLVerticle = new PostgreSQLVerticle().instance(vertx);
                DirectAllocationParser datList = Json.decodeValue(routingContext.getBodyAsString(), DirectAllocationParser.class);
                DirectAllocationResponse directAllocationResponse = new DirectAllocationResponse();
                List<DirectAllocationPayload> directAllocationPayload = new ArrayList<>();
                datList.getData().forEach(v -> {
                    postgreSQLVerticle.begin(res -> {
                        if (res.succeeded()) {
                            // Get the transaction
                            Transaction tx = res.result();
                            // Various statements
                            tx.preparedQuery("UPDATE transaction_direct_allocation SET description = $1, sun_ac_description = $2, expense_type = $3, attributablilty_attributable = $4, " +
                                    "attributablilty_non_attributable =  $5, attributablilty_total =  $6, benefit_for_acquisition =  $7, benefit_for_maintenance =  $8, " +
                                    "benefit_for_total =  $9, acquisition_non_pre_coverage =  $10, acquisition_pre_coverage =  $11, acquisition_total =  $12, " +
                                    "maintenance_premium_related =  $13, maintenance_non_premium_related =  $14, maintenance_total =  $15,  allocation_dirver =  $16, " +
                                    "remarks =  $17, created_by =  $18, created_time =  $19, updated_by =  $20, " +
                                    "updated_time =  $21, approve_by =  $22, approve_time =  $23, is_approve =  $24 WHERE account = $25 ").execute(Tuple.of(
                                    v.getDescription(), v.getSun_ac_description(), v.getExpense_type(), v.getAttributablilty_attributable(),
                                    v.getAttributablilty_non_attributable(), v.getAttributablilty_total(), v.getBenefit_for_acquisition(), v.getBenefit_for_maintenance(),
                                    v.getBenefit_for_total(), v.getAcquisition_non_pre_coverage(), v.getAcquisition_pre_coverage(), v.getAcquisition_total(),
                                    v.getMaintenance_premium_related(), v.getMaintenance_non_premium_related(), v.getMaintenance_total(), v.getAllocation_dirver(),
                                    v.getRemarks(), v.getCreatedBy(), v.getCreatedTime(), v.getUpdatedBy(),
                                    v.getUpdatedTime(), v.getApproveBy(), v.getApproveTime(), v.isApprove(), v.getAccount()), resultSet -> {
                                // Commit the transaction
                                // the connection will automatically return to the pool
                                tx.commit(ar3 -> {
                                    if (ar3.succeeded()) {
                                        System.out.println("Transaction succeeded");
                                    } else {
                                        System.out.println("Transaction failed " + ar3.cause().getMessage());
                                    }
                                });
                            });
                        }
                    });
                });
                directAllocationResponse.setSuccess(true);
                directAllocationResponse.setMessageType(MessageType.REST);
                routingContext.response()
                        .setStatusCode(200)
                        .end(Json.encodePrettily(directAllocationResponse));
            } catch (Exception e) {
                MainVerticle.getLogger(this).error(e.getMessage());
                routingContext.response()
                        .setStatusCode(500)
                        .end();
            }

//            HttpServerResponse response = rc.response();
//            response.putHeader("content-type", "application/json");

        });
        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                startFuture.complete();
                            } else {
                                startFuture.fail(result.cause());
                            }
                        }
                );

    }

    @Override
    public void stop() throws Exception {
        MainVerticle.getLogger(this).info("Successfully stopping verticle.");
    }
}

