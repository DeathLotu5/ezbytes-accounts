package com.ezbytes.hellowordservice.service.client;

import com.ezbytes.hellowordservice.entity.Customer;
import com.ezbytes.hellowordservice.entity.LoansEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("loans") //Để xác định cái tên application name cái mà được đăng kí trong eureka server của chúng ta và định nghĩa trong loans application
public interface LoansFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "myLoans", consumes = "application/json")
    List<LoansEntity> getLoanDetails(@RequestBody Customer customer);

}
