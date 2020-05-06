import io.vertx.core.AbstractVerticle;

public class HelloVerticle extends AbstractVerticle {
    @Override
    public void start(){
        vertx.createHttpServer().requestHandler(req->{
            req.response().end("Hello Vert.x World!");
        }).listen(8080);
    }
}
