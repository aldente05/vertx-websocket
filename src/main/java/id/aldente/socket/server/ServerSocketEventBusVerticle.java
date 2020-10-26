package id.aldente.socket.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.aldente.socket.MainVerticle;
import id.aldente.socket.config.PostgreSQLVerticle;
import id.aldente.socket.model.codec.DepartementCodec;
import id.aldente.socket.model.payload.DepartementPayload;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;

import java.io.IOException;

import static id.aldente.socket.helper.RequestHelperMethod.*;
import static io.vertx.core.json.Json.mapper;

public class ServerSocketEventBusVerticle extends AbstractVerticle {

    private HttpServer httpServer;

    public ServerSocketEventBusVerticle() {
        this.httpServer = null;
    }

    @Override
    public void start(Future<Void> startFuture) {
        MainVerticle.getLogger(this).info("Deployed verticle [" + this.getClass().getName() + "]");
        this.httpServer = vertx.createHttpServer();

        httpServer.websocketHandler(webSocket -> {
            LocalMap<String, String> wsSessions = vertx.sharedData().getLocalMap("ws.sessions");

            //Filter socket base url
            pathResolve(webSocket.path(), webSocket, wsSessions);

            // Specify the close handler for the web socket connection
            webSocket.closeHandler(aVoid -> {
                MainVerticle.getLogger(this).info("Closing socket session : " + webSocket.textHandlerID());
                wsSessions.remove(webSocket.textHandlerID());
//        vertx.eventBus().unregisterDefaultCodec(RegisterPayload.class);
            });
        });

        httpServer.listen(httpServerAsyncResult -> {
            MainVerticle.getLogger(this).info("Http server up and running at port [" + httpServer.actualPort() + "]");
            //Deploy the client verticle that sends response to socket with unique id
//			vertx.deployVerticle(new ClientSocketRequestVerticle());
        });
    }

    @Override
    public void stop() throws Exception {
        if (null != this.httpServer) {
            this.httpServer.close(voidAsyncResult -> {
                if (voidAsyncResult.succeeded()) {
                    MainVerticle.getLogger(this).info("Server [" + httpServer.actualPort() + "] closed successfully");
                } else {
                    MainVerticle.getLogger(this).info("Server [" + httpServer.actualPort() + "] closed successfully");
                }
            });
        }
        super.stop();
    }

    public void pathResolve(String path, ServerWebSocket webSocket, LocalMap<String, String> wsSessions) {
        // Set delivery options to include a custom codec for sending the register request
        DeliveryOptions deliveryOptions = new DeliveryOptions();
        switch (path) {
            case "/departement":
                deliveryOptions.setCodecName(DepartementCodec.class.getName());
//                vertx.eventBus().registerDefaultCodec(DepartementPayload.class, new DepartementCodec());
                MainVerticle.getLogger(this).info("Request received departement at socket [" + webSocket.textHandlerID() + "]");
                wsSessions.put(webSocket.textHandlerID(), webSocket.textHandlerID());
                // Set handler for the incoming text data
                webSocket.textMessageHandler(data -> {
                    MainVerticle.getLogger(this).info("Received web socket " + path + "data [" + data + "]");
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        DepartementPayload registerRequest = mapper.readValue(data, DepartementPayload.class);
                        registerRequest.setSenderId(webSocket.textHandlerID());
                        MainVerticle.getLogger(this).info("Sending to kafka topic: data [" + registerRequest + "]");

                        PgPool postgresql = new PostgreSQLVerticle().instance(vertx);
                        switch (registerRequest.getMethod()){
                            case CREATE:

                                break;
                            case READ:
                                postgresql.query("SELECT * FROM master_direct_allocation")
                                        .execute(ar -> {
                                            MainVerticle.getLogger(this).info("Got " + ar + " rows ");
                                            if (ar.succeeded()) {
                                                RowSet<Row> result = ar.result();
                                                // Receive processed data from kafka consumer and write back to the socket
//                                                vertx.eventBus().consumer("ws-handler-" + webSocket.textHandlerID(), kafkaMessage -> {
//                                                    MainVerticle.getLogger(this).info("Received message from Kafka at Vertx: " + kafkaMessage.body());
                                                    webSocket.writeTextMessage("HELLO");
//                                                    kafkaMessage.reply("Writing the response to websocket");
//                                                });
                                                MainVerticle.getLogger(this).info("Got " + result.size() + " rows ");
                                            } else {
                                                MainVerticle.getLogger(this).info("Failure: " + ar.cause().getMessage());
                                            }

                                            // Now close the pool
                                            postgresql.close();
                                        });
                                break;
                            case UPDATE:
                                break;
                            case DELETE:
                                break;
                            default:
                                break;
                        }
                    } catch (IOException e) {
                        MainVerticle.getLogger(this).error("Error deserializing websocket data [" + data + "] id [" + webSocket.textHandlerID() + "]");
                        webSocket.writeTextMessage("Error deserializing websocket data [" + data + "] id [" + webSocket.textHandlerID() + "]");
                    }
                });
                break;
            case "/wsapi/auth":
                break;

            default:
                MainVerticle.getLogger(this).info("Websocket path [" + webSocket.path() + " is invalid");
                webSocket.reject();
                break;
        }
    }
}
