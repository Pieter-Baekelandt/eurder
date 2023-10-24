package be.switchfully.eurder.customers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;

@ApplicationScoped
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
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

    public Collection<CustomerDTO> getAllCustomers() {
            return customerMapper.mapToDTO(customerRepository.getAllCustomers());
    }
}
