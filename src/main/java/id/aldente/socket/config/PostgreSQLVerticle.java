package id.aldente.socket.config;

import id.aldente.socket.MainVerticle;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;

/**
 * Created by f.putra on 10/25/20.
 */
public class PostgreSQLVerticle {

    PgConnectOptions connectOptions = new PgConnectOptions()
            .setPort(5432)
            .setHost("127.0.0.1")
            .setDatabase("eaq_dev")
            .setUser("root")
            .setPassword("root");

    // Pool options
    PoolOptions poolOptions = new PoolOptions()
            .setMaxSize(5);

    public PgPool instance(Vertx vertx) {
        // Create the client pool
        return PgPool.pool(vertx, connectOptions, poolOptions);
    }
}
