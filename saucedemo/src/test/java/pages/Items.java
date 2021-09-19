package pages;

public enum Items {

    SAUCE_LABS_BACKPACK("Sauce Labs Backpack", 29.99),
    SAUCE_LABS_BIKE_LIGHT("Sauce Labs Bike Light", 9.99),
    SAUCE_LABS_BOLT_TSHIRT("Sauce Labs Bolt T-Shirt", 15.99),
    SAUCE_LABS_FLEECE_JACKET("Sauce Labs Fleece Jacket", 49.99),
    SAUCE_LABS_ONESIE("Sauce Labs Onesie", 7.99),
    SAUCE_LABS_TSHIRT_RED("Test.allTheThings() T-Shirt (Red)", 15.99);

    String itemname;
    double price;

    Items(String itemname, double price) {
        this.itemname = itemname;
        this.price = price;
    }

    public String getName() {
        return itemname;
    }

    public double getPrice() {
        return price;
    }
}
