package app.service;

import app.entity.Employee;
import app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public Optional<Employee> save(Employee employee) {
        return Optional.of(repository.save(employee));
    }

    public Optional<List<Employee>> getAll() {
        return Optional.of(repository.findAll());
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee update(Long id, Employee employee) {
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String email = employee.getEmail();
        Optional<Employee> optional = repository.findById(id);
        if (optional.isPresent()) {
            Employee employeeUpdate = optional.get();
            if (firstName != null)
                employeeUpdate.setFirstName(firstName);
            if (lastName != null)
                employeeUpdate.setLastName(lastName);
            if (email != null)
                employeeUpdate.setEmail(email);
            repository.save(employeeUpdate);
        }
        return repository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else return false;
    }

    public List<Employee> getByFirstName(String firstName) {
        return repository.findByFirstName(firstName)
                .orElse(Collections.emptyList());
    }

    public List<Employee> getByLastName(String lastName) {
        return repository.findByLastName(lastName)
                .orElse(Collections.emptyList());
    }
}
