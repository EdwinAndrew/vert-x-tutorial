import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class HelloVerticle extends AbstractVerticle {
    @Override
    public void start(){
        vertx.createHttpServer().requestHandler(req->{
            req.response().end("Hello Vert.x World!");
        }).listen(8080);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
    }
}
