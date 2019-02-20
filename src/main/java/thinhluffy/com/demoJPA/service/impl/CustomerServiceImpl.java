package thinhluffy.com.demoJPA.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import thinhluffy.com.demoJPA.model.Customer;
import thinhluffy.com.demoJPA.repository.CustomerRepository;
import thinhluffy.com.demoJPA.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.remvove(id);
    }
}
