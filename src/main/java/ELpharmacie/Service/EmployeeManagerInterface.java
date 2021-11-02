package ELpharmacie.Service;

import ELpharmacie.entities.Employee;
import ELpharmacie.entities.Role;

import java.util.List;

public interface EmployeeManagerInterface {

    List<Employee> retrieveAllEmployee();
    void deleteEmployeeById(Long id);
    void deleteEmployeeById(String id);
    Employee updateEmployee(Employee A);
    Employee FindEmployeeById(Long id);
    List<Employee> FindEmployeeByRole(Role role);
    long getNbEmployeeByRole(Role role);
    long getNbEmployee();

    Employee AddEmployee(Employee user);
    void resetAbsence(int nb);
    int addAbsence(long id);
}
