import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HelloVerticleTest {
    @Test
    public void additionTest(){
        int result = HelloVerticle.add(1,1);
        assertEquals(2,result);
    }
}
