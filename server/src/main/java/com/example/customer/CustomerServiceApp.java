package com.example.customer;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Raghavendra
 */

@Log
@SpringBootApplication
public class CustomerServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApp.class, args);
    }
}
