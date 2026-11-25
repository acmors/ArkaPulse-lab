package arkapulse.core.controller;

import arkapulse.core.dto.customer.CreateCustomerDTO;
import arkapulse.core.dto.customer.ResponseCustomer;
import arkapulse.core.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseCustomer> createCustomer(@RequestBody CreateCustomerDTO create){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(create));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseCustomer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok().body(customerService.findById(id));
    }

    @GetMapping("/customer/email/{email}")
    public ResponseEntity<ResponseCustomer> getCustomerByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(customerService.findByEmail(email));
    }
}
