/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package com.oarora.transactionclassifier.batch.sb;

import java.util.List;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import com.oarora.transactionclassifier.constants.InputOutput;
import com.oarora.transactionclassifier.database.entity.TransactionRecord;
import com.oarora.transactionclassifier.database.repository.TransactionRepository;

@AllArgsConstructor
public class TransactionWriter implements ItemWriter<TransactionRecord> {

    private static final Logger logger = LoggerFactory.getLogger(TransactionWriter.class);
    private final TransactionRepository transactionRepository;

    @Override
    public void write(final List<? extends TransactionRecord> list) {
        for (TransactionRecord transaction: list) {
            logger.info(InputOutput.LOADING + transactionRepository.save(transaction));
        }
    }
}
