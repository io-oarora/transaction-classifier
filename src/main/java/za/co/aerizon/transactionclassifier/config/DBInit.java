/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package za.co.aerizon.transactionclassifier.config;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.co.aerizon.transactionclassifier.batch.sb.TransactionCsvToDBConfiguration;
import za.co.aerizon.transactionclassifier.constants.InputOutput;

@Configuration
public class DBInit {

    @Bean
    CommandLineRunner initDatabase(final JobLauncher jobLauncher,
                                   final TransactionCsvToDBConfiguration transactionCsvToDBConfiguration) {
        return args -> jobLauncher.run(
                transactionCsvToDBConfiguration.transactionLoadJob(),
                new JobParametersBuilder().addLong(InputOutput.UNIQUENESS, System.nanoTime()).toJobParameters()
        );
    }
}
