package com.github.wololock.restvalidationdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.LinkedList;
import java.util.List;

@Validated
@RestController
public class EmployeeController {

    private final List<Employee> employees;

    public EmployeeController() {
        this.employees = new LinkedList<Employee>() {{
            add(new Employee("AZ000001A", "Joe Doe"));
            add(new Employee("AZ000002A", "Mark Spencer"));
        }};
    }

    @GetMapping("/history")
    public ResponseEntity<Employee> getHistory(
            @Valid
            @Pattern(regexp = "^[A-Z]{2}[0-9]{6}[A-Z]{0,1}$", message = "Invalid emp")
            @NotNull(message = "input cannot be null")
            @RequestParam(name = "emp") String emp) {

        return employees.stream()
                .filter(it -> emp.equals(it.getId()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
