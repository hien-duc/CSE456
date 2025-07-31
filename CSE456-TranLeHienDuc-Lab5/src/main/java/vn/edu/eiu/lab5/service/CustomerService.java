package vn.edu.eiu.lab5.service;

import vn.edu.eiu.lab5.entity.Customer;
import vn.edu.eiu.lab5.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        return customerRepository.save(customer);
    }
}
