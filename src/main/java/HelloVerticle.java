import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class HelloVerticle extends AbstractVerticle {
    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        // routing by exact path
        Route handler1 = router
                .get("/r1")
                .handler(routingContext -> {
                    HttpServerResponse phrase = routingContext.response();
                    phrase.putHeader("content-type", "text/plain");
                    phrase.end("Hello World!");
                });
        //Capture path parameter and print
        Route handler2 = router
                .get("/r2/:mirror")
                .handler(routingContext -> {
                    String mirror = routingContext.request().getParam("mirror");
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("content-type", "text/plain");
                    response.end(mirror);
                });
        server.requestHandler(router).listen(8080);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
    }
}
