package be.switchfully.eurder.customers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;

@ApplicationScoped
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) throws IllegalArgumentException {
        if (customerRepository.containsId(customer.getId())) {
            throw new IllegalArgumentException("Customer with ID " + customer.getId() + " already exists.");
        }
        else if (customerRepository.containsEmail(customer.getEmailAddress())) {
            throw new IllegalArgumentException("Email address already in use");
            }
        else {
            customerRepository.save(customer);
        }
    }

    public Collection<Customer> getAllCustomers() {
            return customerRepository.getAllCustomers();
    }
}
