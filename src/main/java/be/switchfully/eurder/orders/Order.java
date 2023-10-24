package be.switchfully.eurder.orders;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Order {
    private Set<ItemGroup> itemGroups;
    private final String customerId;
    private final UUID orderId;

    public Order(Set<ItemGroup> itemGroups, String customerId) {
        this.itemGroups = itemGroups;
        this.customerId = customerId;
        this.orderId = UUID.randomUUID();
    }

    public Set<ItemGroup> getItemGroups() {
        return itemGroups;
    }

}
