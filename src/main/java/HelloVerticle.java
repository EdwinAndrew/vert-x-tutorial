import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class HelloVerticle extends AbstractVerticle {
    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {

            // This handler will be called for every request
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // Write to the response and end it
            response.end("Hello World from Vert.x-Web!");
        });

        server.requestHandler(router).listen(8080);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
    }
}
