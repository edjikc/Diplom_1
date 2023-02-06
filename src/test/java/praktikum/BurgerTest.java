package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;
    private AutoCloseable autoCloseable;

    private Burger burger;

    private final float bunPrice;
    private final float firstIngredientPrice;
    private final float secondIngredientPrice;
    private final float finalPrice;

    public BurgerTest(float bunPrice, float firstIngredientPrice, float secondIngredientPrice) {
        this.bunPrice = bunPrice;
        this.firstIngredientPrice = firstIngredientPrice;
        this.secondIngredientPrice = secondIngredientPrice;
        finalPrice = (bunPrice * 2) + firstIngredientPrice + secondIngredientPrice;
    }


    @Before
    public void setup(){
        burger = new Burger();
        autoCloseable = MockitoAnnotations.openMocks(this);

    }

    @Parameterized.Parameters
    public static Object[][] params(){
        return new Object[][]{
                {10f, 15f, 25f},
                {20.123f, 30.321f, 45.322f},
                {220.12355f, 10.321211f, 65.323222f},
        };
    }

    @Test
    public void setBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredient() {
        assertTrue(burger.ingredients.isEmpty());
        burger.addIngredient(ingredient);
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient() {
        addIngredient();
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredient() {
        addIngredient();
        Ingredient newIngredient = addMockIngredient();
        burger.moveIngredient(1,0);
        assertEquals(burger.ingredients.get(0), newIngredient);

    }

    @Test
    public void getPrice() {
        setBuns();
        addIngredient();
        Ingredient newIngredient = addMockIngredient();
        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(firstIngredientPrice);
        when(newIngredient.getPrice()).thenReturn(secondIngredientPrice);

        assertEquals(burger.getPrice(), finalPrice, 0);
    }

    @Test
    public void getReceipt() {
        setBuns();
        addIngredient();
        Ingredient newIngredient = addMockIngredient();
        when(bun.getName()).thenReturn("Булочка с корицей");
        when(ingredient.getName()).thenReturn("Котлета");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(newIngredient.getName()).thenReturn("Сырный");
        when(newIngredient.getType()).thenReturn(IngredientType.SAUCE);

        String expectedReceipt = buildReceiptMessage(newIngredient);

        assertEquals(expectedReceipt, burger.getReceipt());
    }

    private Ingredient addMockIngredient(){
        Ingredient newIngredient = mock(Ingredient.class);
        burger.addIngredient(newIngredient);
        return newIngredient;
    }

    private String buildReceiptMessage(Ingredient newIngredient){
        return   String.format("(==== %s ====)%n", bun.getName())
                + String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                ingredient.getName())
                + String.format("= %s %s =%n", newIngredient.getType().toString().toLowerCase(),
                newIngredient.getName())
                + String.format("(==== %s ====)%n", bun.getName())
                + String.format("%nPrice: %f%n", burger.getPrice());
    }

    @After
    public void after() throws Exception {
        autoCloseable.close();
    }

}