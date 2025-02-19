/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package com.oarora.transactionclassifier.batch.sb;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import com.oarora.transactionclassifier.model.sb.TransactionRaw;

import static com.oarora.transactionclassifier.constants.sb.TransactionRawFields.*;

class TransactionCsvMapper extends DefaultLineMapper<TransactionRaw> {

    TransactionCsvMapper() {

        setLineTokenizer(new DelimitedLineTokenizer() {
            {
                setNames(HIST,
                        DATE,
                        SERVICE,
                        AMOUNT,
                        TYPE,
                        DETAILS,
                        CODE,
                        BALANCE);
            }
        });

        setFieldSetMapper(new BeanWrapperFieldSetMapper<TransactionRaw>() {
            {
                setTargetType(TransactionRaw.class);
            }
        });
    }
}
