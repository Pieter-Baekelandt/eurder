package be.switchfully.eurder.customers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerMapper {
    public CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmailAddress(customer.getEmailAddress());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        return customerDTO;
    }

    public Collection<CustomerDTO> mapToDTO(Collection<Customer> customers) {
        return customers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
