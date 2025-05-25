import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        // TC 1, [allItems = null, cardNumber = "anything"]
        RuntimeException exOne = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "anything"));
        assertEquals("allItems list can't be null!", exOne.getMessage());

        // TC 2, [allItems = {Item(name = null, quantity = 1, price = 1, discount = 1)}, cardNumber = "anything"]
        RuntimeException exTwo = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item(null, 1, 1, 1)), "anything"));
        assertEquals("Invalid item!", exTwo.getMessage());

        // TC 3, [allItems = {Item(name = "Computer", quantity = 1, price = 1, discount = 1)}, cardNumber = null]
        RuntimeException exThree = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item("Computer", 1, 1, 1)), null));
        assertEquals("Invalid card number!", exThree.getMessage());

        // TC 4, [allItems={Item(name = "Computer", quantity = 1, price = 301, discount = 0)}, cardNumber = "ABCDEFGHIJKLMNOP"]
        RuntimeException exFour = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item("Computer", 1, 301, 0)), "ABCDEFGHIJKLMNOP"));
        assertEquals("Invalid character in card number!", exFour.getMessage());

        // TC 5, [allItems={Item(name = "Computer", quantity = 1, price = 301, discount = 0)}, cardNumber = "1234123412341234"]
        double result = SILab2.checkCart(
                List.of(new Item("Computer", 1, 301, 0)),
                "1234123412341234"
        );
        assertEquals(271, result);
    }

    @Test
    public void testMultipleCondition() { // if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
        //  TC 1, T X X
        RuntimeException ex1 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item("Computer", 1, 301, 1)), null));
        assertEquals("Invalid card number!", ex1.getMessage());

        //  TC 2, F T X
        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item("Computer", 1, 1, 1)), null));
        assertEquals("Invalid card number!", ex2.getMessage());

        //  TC 3, F F T
        RuntimeException ex3 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item("Computer", 12, 1, 0)), null));
        assertEquals("Invalid card number!", ex3.getMessage());

        //  TC 4, F F F
        RuntimeException ex4 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item("Computer", 1, 1, 0)), null));
        assertEquals("Invalid card number!", ex4.getMessage());
    }

}
