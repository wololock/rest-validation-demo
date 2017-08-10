package com.github.wololock.restvalidationdemo;

import org.springframework.http.ResponseEntity;

public interface EmployeeContract {
    ResponseEntity<Employee> getHistory(String emp);
}
