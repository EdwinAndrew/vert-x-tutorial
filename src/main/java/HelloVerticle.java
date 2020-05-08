import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class HelloVerticle extends AbstractVerticle {

    public void handler(RoutingContext routingContext, String resp){

        String respond = "hello world";
        if(resp != null){
            respond = resp;
        }

        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "text/plain");
        response.end(respond);

    }

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        Route handler1 = router
                .get("/r1")
                .handler(this::handler());

        Route handler2= router
                .get("/r2/:word")
                .handler(this::handler(routingContext.request().getParam("word"))); // this one

        server.requestHandler(router).listen(8080);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
    }
}


