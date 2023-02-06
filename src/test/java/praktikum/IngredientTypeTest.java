package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    private final String type;
    private final IngredientType[] availableTypes = new IngredientType[]{
            IngredientType.SAUCE,
            IngredientType.FILLING
    };

    public IngredientTypeTest(String type) {
        this.type = type;
    }

    @Parameterized.Parameters
    public static Object[] params(){
        return new Object[]{
                "SAUCE", "FILLING"
        };
    }

    @Test
    public void values() {
        Assert.assertArrayEquals(IngredientType.values(), availableTypes);
    }

    @Test
    public void valueOf() {
        IngredientType ingredientType = IngredientType.valueOf(type);
        Assert.assertEquals(ingredientType.name(), type);
    }
}