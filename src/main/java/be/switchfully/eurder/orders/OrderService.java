package be.switchfully.eurder.orders;

import be.switchfully.eurder.customers.CustomerService;
import be.switchfully.eurder.items.ItemService;
import jakarta.inject.Inject;

public class OrderService {
    @Inject
    CustomerService customerService;
    @Inject
    ItemService itemService;

//    Methode createOrder waarvoor we eerst bepaalde dingen moeten opzoeken
//    Shippingdate -> itemService: amount


}
