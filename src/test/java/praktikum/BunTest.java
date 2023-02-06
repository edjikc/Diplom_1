package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BunTest {

    private final String bunName = "TestBunName";
    private final float bunPrice = 2;
    @Mock
    private Database database;

    @Before
    public void setup(){
        when(database.availableBuns()).thenReturn(Arrays.asList(new Bun(bunName, bunPrice)));
    }

    @Test
    public void getName() {
        Assert.assertEquals(bunName, database.availableBuns().get(0).getName());
    }

    @Test
    public void getPrice() {
        Assert.assertEquals(bunPrice, database.availableBuns().get(0).getPrice(), 0);
    }
}