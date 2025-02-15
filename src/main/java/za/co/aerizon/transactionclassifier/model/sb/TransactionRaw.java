/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package za.co.aerizon.transactionclassifier.model.sb;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRaw {

    private String hist;
    private String date;
    private String service;
    private String amount;
    private String type;
    private String details;
    private String code;
    private String balance;
}
