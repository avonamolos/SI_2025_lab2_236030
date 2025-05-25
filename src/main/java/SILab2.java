import java.util.List;

class Item {
    String name;
    int quantity; //numerical
    int price;
    double discount;

    public Item(String name, int quantity, int price, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static double checkCart(List<Item> allItems, String cardNumber){
        if (allItems == null){ // allItems da e null (prv test case), [allItems = null, cardNumber = "anything")
            throw new RuntimeException("allItems list can't be null!");
        }

        double sum = 0;

        for (int i = 0; i < allItems.size(); i++){
            Item item = allItems.get(i);
            if (item.getName() == null || item.getName().length() == 0){ // item.getName() da e null vtor test case, [allItems={Item(name=null, quantity = 1, price = 1, discount = 1)}, cardNumber = "anything"]
                throw new RuntimeException("Invalid item!");
            }

            if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10){ // 3 item.getDiscount da e pogolem od 0, [allItems={Item(name="Computer", quantity = 1, price = 1, discount = 1)}, cardNumber = null]
                sum -= 30;
            }

            if (item.getDiscount() > 0){ // 3 [allItems={Item(name="Computer", quantity = 1, price = 1, discount = 1)}, cardNumber = null]
                sum += item.getPrice()*(1-item.getDiscount())*item.getQuantity();
            }
            else { // 4 tuka da ne e pogolem [allItems={Item(name="Computer", quantity = 1, price = 301, discount = 0)}, cardNumber = "ABCDEFGHIJKLMNOP"]
                sum += item.getPrice()*item.getQuantity();
            }

        }
        if (cardNumber != null && cardNumber.length() == 16) { // 3 [allItems = {Item(name = "Computer", quantity = 1, price = 1, discount = 1)}, cardNumber = null], 4 [allItems={Item(name="Computer", quantity = 1, price = 1, discount = 0)}, cardNumber = "ABCDEFGHIJKLMNOP"]
            String allowed = "0123456789";
            char[] chars = cardNumber.toCharArray();
            for (int j = 0; j < cardNumber.length(); j++) {
                char c = cardNumber.charAt(j);
                if (allowed.indexOf(c) == -1) { // 4 да има буква во картичката, 5 да нема буква никаде [allItems={Item(name="Computer", quantity = 1, price = 1, discount = 0)}, cardNumber = "1234123412341234"]
                    throw new RuntimeException("Invalid character in card number!"); // 4
                }
            }
        }
        else{ // 3 [allItems={Item(name="Computer", quantity = 1, price = 1, discount = 0)}, cardNumber = null]
            throw new RuntimeException("Invalid card number!");
        }

        return sum; // 5, normal flow

    }
}