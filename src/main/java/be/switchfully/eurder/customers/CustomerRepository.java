package be.switchfully.eurder.customers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;


@ApplicationScoped
public class CustomerRepository {
    private final ConcurrentHashMap<String, Customer> customers;

    public CustomerRepository() {
        this.customers = new ConcurrentHashMap<>();
    }

    public void save(Customer savedCustomer) {
        customers.put(savedCustomer.getId(), savedCustomer);
    }

    public boolean containsId(String id) {
        return customers.containsKey(id);
    }

    public boolean containsEmail(String email) {
        return customers.values().stream()
                .anyMatch(customer -> customer.getEmailAddress().equals(email));
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    public Customer getCustomerById(String id) { return customers.get(id);
    }
}

