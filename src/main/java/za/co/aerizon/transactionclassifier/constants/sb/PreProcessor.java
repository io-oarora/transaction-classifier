/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package za.co.aerizon.transactionclassifier.constants.sb;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreProcessor {

    private static final List<String> BANK_FEES = Arrays.asList (
            "!FEE - POS DECLINE",
            "!PREPAID FEE",
            "CASH FINANCE CHARGE",
            "SBSA ATM WITHDRAWAL FEE",
            "!INTL. TRANS FEE"
    );


    private static final List<String> PREPAID = Arrays.asList (
            "VOD PREPAID",
            "TELK MOBILE"
    );

    private static final List<String> WITHDRAWALS = Collections.singletonList(
            "ATM WDW"
    );

    public static final Map<List<String>, Integer> PRE_PROCESSOR_MAP =
            new HashMap<List<String>, Integer>() {{
                put(BANK_FEES, 1340);
                put(PREPAID, 222);
                put(WITHDRAWALS, 600);
            }};
}
