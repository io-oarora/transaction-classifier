/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */
package za.co.aerizon.transactionclassifier.database.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import za.co.aerizon.transactionclassifier.constants.ExpenseTypes;
import za.co.aerizon.transactionclassifier.database.entity.TransactionRecord;

public interface TransactionRepository extends CrudRepository<TransactionRecord, Long> {

    List<TransactionRecord> findByDateBetweenOrderByDateAsc(final LocalDateTime start, final LocalDateTime end);

    List<TransactionRecord> findByDateBetweenAndExpenseTypeOrderByDateAsc(final LocalDateTime start,
                                                                          final LocalDateTime end,
                                                                          final ExpenseTypes.EXPENSETYPES expensetype);
}
