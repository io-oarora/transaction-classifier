/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package com.oarora.transactionclassifier.constants.sb;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oarora.transactionclassifier.constants.ExpenseTypes;

import static com.oarora.transactionclassifier.constants.ExpenseTypes.EXPENSETYPES.*;

public class TransactionCodes {

    private static final List<Integer> BANK_FEES = Arrays.asList (
            234,
            311,
            1112,
            1306,
            1340,
            1421,
            1422,
            1427,
            1565,
            1644,
            1662
    );

    private static final List<Integer> DEBITS = Arrays.asList (
            865,
            1624,
            6021,
            6023,
            6032,
            6036,
            6044,
            6055
    );

    private static final List<Integer> DEPOSITS = Arrays.asList (
            134,
            621,
            786,
            1000,
            1111,
            1529,
            1581,
            6088
    );

    private static final List<Integer> PAYMENTS = Arrays.asList (
            377,
            760,
            1714
    );

    private static final List<Integer> PREPAID = Collections.singletonList(
            222
    );

    private static final List<Integer> PURCHASES = Arrays.asList (
            348,
            6076,
            6099
    );

    private static final List<Integer> TRANSFERS = Arrays.asList (
            120,
            378,
            380,
            1224,
            1710
    );

    private static final List<Integer> WITHDRAWALS = Arrays.asList (
            551,
            600
    );

    public static final Map<ExpenseTypes.EXPENSETYPES, List<Integer>> TRANSACTIONS_MAP =
            new HashMap<ExpenseTypes.EXPENSETYPES, List<Integer>>() {{
                put(Bank_Fees, BANK_FEES);
                put(Debits, DEBITS);
                put(Deposits, DEPOSITS);
                put(Payments, PAYMENTS);
                put(Prepaid, PREPAID);
                put(Miscellaneous, PURCHASES);
                put(Transfers, TRANSFERS);
                put(Withdrawals, WITHDRAWALS);
            }};
}
