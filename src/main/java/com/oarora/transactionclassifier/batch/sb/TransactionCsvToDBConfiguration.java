/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package com.oarora.transactionclassifier.batch.sb;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import com.oarora.transactionclassifier.database.entity.TransactionRecord;
import com.oarora.transactionclassifier.database.repository.TransactionRepository;
import com.oarora.transactionclassifier.model.sb.TransactionRaw;

@PropertySource("classpath:batch.properties")
@Configuration
@AllArgsConstructor
public class TransactionCsvToDBConfiguration {

    private final JobBuilderFactory jobBuilders;
    private final StepBuilderFactory stepBuilders;
    private final TransactionRepository transactionRepository;

    @Bean(name = "transactionLoadJob")
    public Job transactionLoadJob() {
        return jobBuilders.get("TransactionToDBLoadJob")
                .start(transactionLoadTaskletStep())
                .next(chunkStep())
                .build();
    }

    @Bean
    public Step transactionLoadTaskletStep() {
        return stepBuilders.get("TransactionToDBLoadJobTaskLetStep")
                .tasklet(transactionLoadTasklet())
                .build();
    }

    @Bean
    public Tasklet transactionLoadTasklet() {
        return (contribution, chunkContext) -> RepeatStatus.FINISHED;
    }

    private Step chunkStep() {
        return stepBuilders.get("loadingTransactionChunkStep")
                .<TransactionRaw, TransactionRecord>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @StepScope
    private ItemReader<TransactionRaw> reader() {
        FlatFileItemReader<TransactionRaw> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("/All.csv"));

        reader.setLineMapper(new TransactionCsvMapper());

        return reader;
    }

    @StepScope
    private ItemProcessor<TransactionRaw, TransactionRecord> processor() {
        return new TransactionProcessor();
    }

    @StepScope
    private ItemWriter<TransactionRecord> writer() {
        return new TransactionWriter(transactionRepository);
    }

}
