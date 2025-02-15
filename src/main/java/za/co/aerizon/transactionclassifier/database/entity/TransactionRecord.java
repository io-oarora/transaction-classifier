/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package za.co.aerizon.transactionclassifier.database.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.aerizon.transactionclassifier.constants.ExpenseTypes;

import static za.co.aerizon.transactionclassifier.constants.InputOutput.TRANSACTION_OUTPUT;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecord implements Serializable {

    private @Id
    @GeneratedValue
    Long id;

    private LocalDateTime date;

    private int code;

    private String type;

    private String details;

    private Double amount;

    private ExpenseTypes.EXPENSETYPES expenseType;

    @Override
    public String toString() {

        return String.format(TRANSACTION_OUTPUT,
                id, date, code, type, details, amount);
    }
}
