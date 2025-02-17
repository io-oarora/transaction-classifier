/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package com.oarora.transactionclassifier.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.oarora.transactionclassifier.constants.ExpenseTypes;
import com.oarora.transactionclassifier.database.entity.TransactionRecord;
import com.oarora.transactionclassifier.database.repository.TransactionRepository;

import static com.oarora.transactionclassifier.constants.ExpenseTypes.EXPENSETYPES.Deposits;
import static com.oarora.transactionclassifier.constants.ExpenseTypes.EXPENSETYPES.Transfers;
import static com.oarora.transactionclassifier.constants.PayDays.TWENTY_NINETEEN;

@RestController
@RequestMapping("openbanking/transactionclassifier")
public class OpenBanking {

    private final TransactionRepository transactionRepository;


    private LocalDateTime queryStartDate;
    private LocalDateTime queryEndDate;


    @Autowired
    public OpenBanking(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

        queryEndDate  = LocalDateTime.now();
        queryStartDate = TWENTY_NINETEEN
                .stream()
                .filter(payday -> !payday.isAfter(LocalDate.now()))
                .max(Comparator.comparing(LocalDate::toEpochDay))
                .orElse(queryEndDate.toLocalDate().minusMonths(1))
                .atStartOfDay();
    }

    @RequestMapping(method =  RequestMethod.GET, value = "/deposits")
    LinkedHashMap<String, Double> getDeposits() {
        return getGroupTotals(Collections.singletonList(Deposits));
    }

    @RequestMapping(method =  RequestMethod.GET, value = "/spendings")
    LinkedHashMap<String, Double> getSpendings() {
        LinkedList spendings = new LinkedList(Arrays.asList(ExpenseTypes.EXPENSETYPES.values()));

        // Excludes
        spendings.remove(Deposits);
        spendings.remove(Transfers);

        return getGroupTotals(spendings);
}

    @RequestMapping(method =  RequestMethod.GET, value = "/transactions")
    Iterable<TransactionRecord> getTransactions() {
        return transactionRepository.findByDateBetweenOrderByDateAsc(
                queryStartDate, queryEndDate);
    }

    @RequestMapping(method =  RequestMethod.GET, value = "/transactions/grouped")
    LinkedHashMap<String,
            Pair<Iterable<TransactionRecord>, Double>> getGroupedTransactions() {

        return Arrays.stream(ExpenseTypes.EXPENSETYPES.values())
               .collect(Collectors
                       .toMap(ExpenseTypes.EXPENSETYPES::toString, expenseType -> new Pair<>(
                               transactionRepository.findByDateBetweenAndExpenseTypeOrderByDateAsc(
                                       queryStartDate, queryEndDate, expenseType),
                               calculateTotal(expenseType)),
                       (key, val) -> val, LinkedHashMap::new));

    }

    private LinkedHashMap<String, Double> getGroupTotals(final List<ExpenseTypes.EXPENSETYPES> expenseTypes) {

        return expenseTypes
                .stream()
                .collect(Collectors
                        .toMap(ExpenseTypes.EXPENSETYPES::toString, this::calculateTotal,
                                (key, val) -> val, LinkedHashMap::new));
    }

    private double calculateTotal(final ExpenseTypes.EXPENSETYPES expenseType) {
        final List<TransactionRecord> transactionRecords = transactionRepository.
                findByDateBetweenAndExpenseTypeOrderByDateAsc(queryStartDate, queryEndDate, expenseType);

      return transactionRecords
              .stream()
              .map(TransactionRecord::getAmount)
              .mapToDouble(Double::doubleValue)
              .sum();
    }
}
