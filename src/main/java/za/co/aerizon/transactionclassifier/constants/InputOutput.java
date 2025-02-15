/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package za.co.aerizon.transactionclassifier.constants;

import java.util.List;

import static java.util.Arrays.asList;

public class InputOutput {

    public static final String UNIQUENESS = "uniqueness";
    public static final String LOADING = "Loading ";
    public static final String TRANSACTION_OUTPUT = "Transaction %-3s >>  %-19s, %-4s: %-30s - %-60s  R%f";
    public static final String DATE_REGEX = "(\\d{4}-\\d{2}-\\d{2})T(\\d{2}:\\d{2}:\\d{2})";
    public static final List<String> CARD_NO_REGEX =
            asList(
                    "(\\d{4}\\*\\d{4} )",
                    "(5\\d{15})"

            );

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final char SPACE = ' ';
}
