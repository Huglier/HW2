package sky.pro.HW2.service;

import org.springframework.stereotype.Service;
import sky.pro.HW2.exception.EmployeeAlreadyAddedException;
import sky.pro.HW2.exception.EmployeeNotFoundException;
import sky.pro.HW2.exception.EmployeeStorageIsFullException;
import sky.pro.HW2.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)){
            throw new EmployeeAlreadyAddedException("Этот работник уже есть");
        }
        employeeList.add(employee);
        if (employeeList.size()>10){
            throw new EmployeeStorageIsFullException("Мест нет");
        }
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Такого работника нет");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            return employee;

        }
        throw new EmployeeNotFoundException("Такого работника нет");
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableList(employeeList);
    }
}
