package be.switchfully.eurder.orders;

import java.time.LocalDate;

public class ItemGroup {
    private String selectedItem;
    private int amount;
    private LocalDate shippingDate;

    public ItemGroup(String selectedItem, int amount) {
        this.selectedItem = selectedItem;
        this.amount = amount;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
