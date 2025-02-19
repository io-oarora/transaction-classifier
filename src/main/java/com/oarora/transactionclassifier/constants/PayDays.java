/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package com.oarora.transactionclassifier.constants;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

public class PayDays {

    public static final String Feb = "20190222";
    public static final String Mar = "20190322";
    public static final String Apr = "20190423";
    public static final String May = "20190523";

    public static final List<LocalDate> TWENTY_NINETEEN = asList(
            LocalDate.of(2019, 1, 23),
            LocalDate.of(2019, 2, 22),
            LocalDate.of(2019, 3, 22),
            LocalDate.of(2019, 4, 23),
            LocalDate.of(2019, 5, 23)
    );

}
