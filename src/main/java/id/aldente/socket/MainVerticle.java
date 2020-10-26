package id.aldente.socket;

import id.aldente.socket.config.PostgreSQLVerticle;
import id.aldente.socket.server.ServerSocketEventBusVerticle;
import io.vertx.core.Future;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.Vertx;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
  public static Logger getLogger(Object o) {
    return LoggerFactory.getLogger(o.getClass());
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start(Future<Void> startFuture) {
    // Create an HTTP server which simply returns "Hello World!" to each request.
    // If a configuration is set it get the specified name
    String name = config().getString("name");
    MainVerticle.getLogger(this).info("Deployed main module " + startFuture + Thread.currentThread().getName());

    //Deploy the server verticle that listens to socket reqs with unique id
    vertx.deployVerticle(new ServerSocketEventBusVerticle());
  }

  @Override
  public void stop() throws Exception {
    MainVerticle.getLogger(this).info("Successfully stopping verticle.");
  }
}

