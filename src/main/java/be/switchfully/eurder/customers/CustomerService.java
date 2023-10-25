package be.switchfully.eurder.customers;

import be.switchfully.eurder.security.users.Role;
import be.switchfully.eurder.security.users.User;
import be.switchfully.eurder.security.users.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final UserRepository userRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.userRepository = userRepository;
    }

    public void registerCustomer(CreateCustomerDTO createCustomerDTO) throws IllegalArgumentException {

        if (customerRepository.containsEmail(createCustomerDTO.getEmailAddress())) {
            throw new IllegalArgumentException("Email address already in use");
        } else {
            Customer savedCustomer = new Customer(createCustomerDTO.getFirstName(), createCustomerDTO.getLastName(), createCustomerDTO.getEmailAddress(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
            customerRepository.save(savedCustomer);
            userRepository.addUser(new User(createCustomerDTO.getUsername(), createCustomerDTO.getPassword(), Role.CUSTOMER, savedCustomer.getId()));

        }
    }

    public Collection<CustomerDTO> getAllCustomers() {
        return customerMapper.mapToDTO(customerRepository.getAllCustomers());
    }

    public CustomerDTO getCustomerById(String id) {
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.getCustomerById(id));
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException(String.format("No customer with id %s found.", id));
        }
        Customer foundCustomer = optionalCustomer.get();
        return customerMapper.mapToDTO(foundCustomer);
    }
}
