import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloVerticleTest {
    @Test
    public void additionTest(){
        int result = HelloVerticle.add(1,1);
        assertEquals(2,result);
    }
}
