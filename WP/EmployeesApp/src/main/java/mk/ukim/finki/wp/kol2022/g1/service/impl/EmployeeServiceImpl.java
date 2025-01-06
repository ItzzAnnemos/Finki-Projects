package mk.ukim.finki.wp.kol2022.g1.service.impl;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidSkillIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.EmployeeRepository;
import mk.ukim.finki.wp.kol2022.g1.repository.SkillRepository;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import mk.ukim.finki.wp.kol2022.g1.service.SkillService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static mk.ukim.finki.wp.kol2022.g1.service.specifications.FieldFilterSpecification.filterContainsText;
import static mk.ukim.finki.wp.kol2022.g1.service.specifications.FieldFilterSpecification.filterEquals;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;
    private final PasswordEncoder passwordEncoder;
    private final SkillService skillService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillRepository skillRepository, PasswordEncoder passwordEncoder, SkillService skillService) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
        this.passwordEncoder = passwordEncoder;
        this.skillService = skillService;
    }

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        return employeeRepository.save(new Employee(name,
                email,
                passwordEncoder.encode(password),
                type,
                skillId.stream().map(this.skillService::findById).collect(Collectors.toList()),
                employmentDate));
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = this.findById(id);

        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(passwordEncoder.encode(password));
        employee.setType(type);
        employee.setSkills(skillId.stream().map(this.skillService::findById).collect(Collectors.toList()));
        employee.setEmploymentDate(employmentDate);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = this.findById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        List<Employee> employees;
        if (skillId != null && yearsOfService != null) {
            Skill skill = skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            LocalDate employmentBefore = LocalDate.now().minusYears(yearsOfService);
            employees = employeeRepository.findBySkillsContainingAndEmploymentDateBefore(skill, employmentBefore);
        } else if (skillId != null) {
            Skill skill = skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            employees = employeeRepository.findBySkillsContaining(skill);
        } else if (yearsOfService != null) {
            LocalDate employmentBefore = LocalDate.now().minusYears(yearsOfService);
            employees = employeeRepository.findByEmploymentDateBefore(employmentBefore);
        } else {
            employees = employeeRepository.findAll();
        }
        return employees;
    }
}
