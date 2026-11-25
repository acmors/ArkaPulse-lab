package arkapulse.core.services;

import arkapulse.core.dto.customer.CreateCustomerDTO;
import arkapulse.core.dto.customer.ResponseCustomer;
import arkapulse.core.entities.Customer;
import arkapulse.core.exceptions.BusinessException;
import arkapulse.core.exceptions.ResourceNotFoundException;
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
        Customer customers = new Customer();
        validateCustomer(dto);

        customers.setName(dto.name());
        customers.setEmail(dto.email());
        customers.setPassword(dto.password());
        customers.setRiskProfile(dto.riskProfile());
        customers.setVerified(false);
        customers.setCreatedAt(LocalDateTime.now());

        Customer saved = customerRepository.save(customers);

        return new ResponseCustomer(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.isVerified(),
                saved.getRiskProfile(),
                saved.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public ResponseCustomer findById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found."));

        return new ResponseCustomer(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.isVerified(),
                customer.getRiskProfile(),
                customer.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public ResponseCustomer findByEmail(String email){
        Customer customer = customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found."));

        return new ResponseCustomer(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.isVerified(),
                customer.getRiskProfile(),
                customer.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public Customer findByIdIntern(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found."));
    }

    private void validateCustomer(CreateCustomerDTO create){
        if(     create.email() == null || create.email().isBlank() ||
                create.password() == null || create.password().isBlank() ||
                create.name() == null || create.name().isBlank() ){
            throw new BusinessException("Campos Nome, E-mail e Senha não podem ser vazios");
        }

        if(customerRepository.findCustomerByEmail(create.email()).isPresent()){
            throw new BusinessException("Já existe um cliente com esse email");
        }
    }

}
