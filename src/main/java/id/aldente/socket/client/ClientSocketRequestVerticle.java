package id.aldente.socket.client;

import id.aldente.socket.MainVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

public class ClientSocketRequestVerticle extends AbstractVerticle {

	private HttpClient httpClient;

	public ClientSocketRequestVerticle() {
		this.httpClient = null;
	}

	@Override
	public void start(Future<Void> future) throws Exception {
		// A simple query
//		client.query("SELECT * FROM users WHERE id='julien'")
//				.execute(ar -> {
//					if (ar.succeeded()) {
//						RowSet<Row> result = ar.result();
//						System.out.println("Got " + result.size() + " rows ");
//					} else {
//						System.out.println("Failure: " + ar.cause().getMessage());
//					}
//
//					 Now close the pool
//					client.close();
//				});
		MainVerticle.getLogger(this).info("Deployed verticle [" + this.getClass().getName());

		HttpClientOptions options = new HttpClientOptions()
				.setSsl(false)
				.setTrustAll(true);

		this.httpClient = vertx.createHttpClient(options).websocket(9443, "127.0.0.1", "/wsapi/register",
				webSocket -> {
			// Set the handler for processing server response if any
			webSocket.handler(dataBuffer -> {
				MainVerticle.getLogger(this).info("Received response from server " + dataBuffer);
			});

			// Emulate client side register request
//			RegisterPayload registerRequest = createClientRegisterPayload();
//			Utils.getJsonStringFromObject(registerRequest)
//					.ifPresent(webSocket::writeTextMessage);

		});
	}

	@Override
	public void stop(Future<Void> future) throws Exception {
		if (null != this.httpClient) {
			this.httpClient.close();
		}
	}
}
