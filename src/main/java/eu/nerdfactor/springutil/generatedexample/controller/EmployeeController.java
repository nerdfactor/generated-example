package eu.nerdfactor.springutil.generatedexample.controller;

import eu.nerdfactor.springutil.generatedexample.entity.Employee;
import eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestController;
import eu.nerdfactor.springutil.generatedrest.annotation.GeneratedRestSecurity;

/**
 * Base controller for Employees.<br>
 * Uses GeneratedRestController to configure a generated controller based on the controller.
 * Provides informationen about the entity and id to use in the generated controller.
 * <br>
 * Uses GeneratedRestSecurity to configure Spring security for the generated controller. Matches
 * automatically to the generated controller. Disables the inclusion of base security. Therefore
 * the methods only require access to the relation object and not to employee.
 */
@GeneratedRestController(value = "/api/employee", entity = Employee.class, id = Integer.class)
@GeneratedRestSecurity(inclusive = false)
public class EmployeeController {


}
