package praktikum;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final String name;
    private final float price;
    private final IngredientType ingredientType;
    private AutoCloseable autoCloseable;
    @Mock
    private Database database;

    public IngredientTest(String name, float price, IngredientType ingredientType) {
        this.name = name;
        this.price = price;
        this.ingredientType = ingredientType;
    }

    @Parameterized.Parameters
    public static Object[][] params() {
        return new Object[][]{
                {"Котлета", 15.505f, IngredientType.FILLING},
                {"Помидор", 5.123f, IngredientType.FILLING},
                {"Сырный", 0.321f, IngredientType.SAUCE},
        };
    }

    @Before
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        when(database.availableIngredients()).thenReturn(Arrays.asList(new Ingredient(ingredientType, name, price)));
    }

    @Test
    public void getPrice() {
        Assert.assertEquals(price, database.availableIngredients().get(0).getPrice(), 0);
    }

    @Test
    public void getName() {
        Assert.assertEquals(name, database.availableIngredients().get(0).getName());
    }

    @Test
    public void getType() {
        Assert.assertEquals(ingredientType, database.availableIngredients().get(0).getType());
    }

    @After
    public void after() throws Exception {
        autoCloseable.close();
    }
}