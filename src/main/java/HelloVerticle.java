import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import java.util.HashMap;
import java.util.Map;


public class HelloVerticle extends AbstractVerticle {

    Map<String,String> hashMap = new HashMap<>();

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

    private void handleR3(RoutingContext routingContext) {
        String key = routingContext.request().getParam("key");
        String value = routingContext.request().getParam("value");
        hashMap.put(key, value);
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "text/plain");
        response.end("");
    }

    private void handleR4(RoutingContext routingContext) {
        String text = routingContext.request().getParam("key");
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "text/plain");
        for (String key : hashMap.keySet()) {
            if (key.contains(text)) {
                response.end(hashMap.get(key));
            }
        }

    }

    private void setRoutes(Router router) {
        router.get("/r1")
                .handler(this::handleR1);

        router.get("/r2/:word")
                .handler(this::handleR2);

        router.get("/kv/set/:key/:value")
                .handler(this::handleR3);

        router.get("/kv/get/:key")
                .handler(this::handleR4);
    }


    public static int add(int num1, int num2) {
        return num1 + num2;
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

