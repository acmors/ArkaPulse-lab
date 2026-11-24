package arkapulse.core.services;

import arkapulse.core.dto.CreateCustomerDTO;
import arkapulse.core.dto.ResponseCustomer;
import arkapulse.core.entities.Customers;
import arkapulse.core.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public ResponseCustomer createCustomer(CreateCustomerDTO dto){
        Customers customers = new Customers();
        customers.setName(dto.name());
        customers.setEmail(dto.email());
        customers.setPassword(dto.password());
        customers.setRiskProfile(dto.riskProfile());
        customers.setVerified(false);
        customers.setCreatedAt(LocalDateTime.now());

        Customers saved = customerRepository.save(customers);

        return new ResponseCustomer(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.isVerified(),
                saved.getRiskProfile(),
                saved.getCreatedAt()
        );
    }
}
