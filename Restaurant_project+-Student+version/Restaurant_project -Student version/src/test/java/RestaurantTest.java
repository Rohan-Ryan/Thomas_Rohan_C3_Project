import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
//import sun.jvm.hotspot.utilities.Assert;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    Restaurant A1 = Mockito.spy(new Restaurant("A1","Chennai",LocalTime.of(9,0,0),LocalTime.of(22,0,0)));


    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Mockito.when(A1.getCurrentTime()).thenReturn(LocalTime.of(9,0,1));// 1 sec after 9
        assertTrue(A1.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Mockito.when(A1.getCurrentTime()).thenReturn(LocalTime.of(22,0,0));
        assertFalse(A1.isRestaurantOpen());

    }
    // The amount of selected items from the menu should give the total payable amount.
    // Two items to be added and checked
    // scenario1 2 item bought
    // scenario2 1 items bought
    // None bought

    @Test
    public void totalAmount_gives_the_Amount_Totaled_2(){
        A1.addToMenu("Gratin",520);
        A1.addToMenu("Spaghetti Bolognese",800);
        ArrayList<String> order=new ArrayList<>(Arrays.asList("Gratin","Spaghetti Bolognese"));
        assertEquals(A1.totalAmount(order),520+800);
    }
    @Test
    public void totalAmount_gives_the_Amount_Totaled_1(){
        A1.addToMenu("Gratin",520);
        A1.addToMenu("Spaghetti Bolognese",800);
        ArrayList<String> order=new ArrayList<>(Arrays.asList("Gratin"));
        assertEquals(A1.totalAmount(order),520);
    }
    @Test
    public void totalAmount_gives_the_Amount_Totaled_0(){
        A1.addToMenu("Gratin",520);
        A1.addToMenu("Spaghetti Bolognese",800);
        ArrayList<String> order=new ArrayList<>(Arrays.asList());
        assertEquals(A1.totalAmount(order),0);
    }
    //@Test
    //public void totalAmount_gives_the_gives_more_or_less_than_actual(){
        //A1.addToMenu("Gratin",520);
        //A1.addToMenu("Spaghetti Bolognese",800);
        //ArrayList<String> order=new ArrayList<>(Arrays.asList("Gratin","Spaghetti Bolognese"));
        //assertTrue(A1.totalAmount(order)==520+800);}


    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}