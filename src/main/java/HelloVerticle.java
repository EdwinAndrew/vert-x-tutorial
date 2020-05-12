import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class HelloVerticle extends AbstractVerticle {
    private void handleR1(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "text/plain");
        response.end("hello world");
    }

    private void handleR2(RoutingContext routingContext) {
        String word = routingContext.request().getParam("word");
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "text/plain");
        response.end("hello " + word);
    }

    private void setRoutes(Router router) {
        router.get("/r1")
                .handler(this::handleR1);

        router.get("/r2/:word")
                .handler(this::handleR2);
    }
    public static int add(int num1, int num2){
        return num1+num2;
    }

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        setRoutes(router);
        server.requestHandler(router).listen(9999);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
    }
}


