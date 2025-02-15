/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */
package za.co.aerizon.transactionclassifier;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class TransactionClassifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionClassifierApplication.class, args);
    }

}
