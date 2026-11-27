package arkapulse.core.services;

import arkapulse.core.dto.customer.CreateCustomerDTO;
import arkapulse.core.dto.customer.ResponseCustomer;
import arkapulse.core.dto.customer.SuitabilityAnswersDTO;
import arkapulse.core.dto.customer.VerifyCodeDTO;
import arkapulse.core.entities.Customer;
import arkapulse.core.entities.enums.RiskProfile;
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

    @Transactional
    public void verifyCustomer(VerifyCodeDTO dto){
        Customer customer = customerRepository.findCustomerByEmail(dto.email())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

        if(customer.isVerified()){
            throw new BusinessException("Cliente já verificado");
        }

        if(customer.getCode() == null || !customer.getCode().equals(dto.code())){
            throw new BusinessException("Código invalido");
        }

        customer.setVerified(true);
        customer.setCode(null);
        customerRepository.save(customer);
    }

    @Transactional
    public void calculateSuitability(Long id, SuitabilityAnswersDTO suitability){
        Customer customer = findByIdIntern(id);
        int total = suitability.ageScore() + suitability.experienceScore() + suitability.lossToToleranceScore();

        if(total <= 4){
            customer.setRiskProfile(RiskProfile.CONSERVATIVE);
        }else if(total <= 7){
            customer.setRiskProfile(RiskProfile.MODERATE);
        }else{
            customer.setRiskProfile(RiskProfile.AGGRESSIVE);
        }
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
