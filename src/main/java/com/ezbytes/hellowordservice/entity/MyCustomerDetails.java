package com.ezbytes.hellowordservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MyCustomerDetails {

    Accounts accounts;

    List<CardEntity> cards;

    List<LoansEntity> loans;

}
