package com.example.snykbootweb.jdbc;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
@CrossOrigin("*")
public class CustomerRest {

    CustomerService customerService;

    public CustomerRest(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping ("/count")
    public int getCount() {
        return customerService.countCustomers();
    }

    @GetMapping(produces = "application/json", path = "/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping(produces = "application/json", path = "/all/{lastName}")
    public List<Customer> getAllCustomersByLastName(@PathVariable String lastName) {
        return customerService.getAllByLastName(lastName);
    }

    @GetMapping(produces = "application/json", path = "/all/{firstName}")
    public List<Customer> getAllCustomersByFirstName(@PathVariable String firstName) {
        return customerService.getAllByFirstName(firstName);
    }

    // Intentionally vulnerable endpoint for demo (SQL injection via nameTerm)
    @GetMapping(produces = "application/json", path = "/search/vulnerable/{nameTerm}")
    public List<Customer> searchCustomersVulnerable(@PathVariable String nameTerm) {
        return customerService.searchByNameVulnerable(nameTerm);
    }

    /*
    @GetMapping(produces = "application/json", path = "/all/withfix/{lastName}")
    public List<Customer> getAllCustomersByLastNameFixed(@PathVariable String lastName) {
        return customerService.getAllByLastNameFixed(lastName);
    }


    /* TO REMOVE
    @GetMapping(produces = "application/json", path = "/all/withfix/{lastName}")
    public List<Customer> getAllCustomersByLastNameFixed(@PathVariable String lastName) {
        return customerService.getAllByLastNameFixed(lastName);
    }
    */
}
