/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package com.oarora.transactionclassifier.batch.sb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemProcessor;
import com.oarora.transactionclassifier.database.entity.TransactionRecord;
import com.oarora.transactionclassifier.model.sb.TransactionRaw;

import static com.oarora.transactionclassifier.constants.ExpenseTypes.EXPENSETYPES;
import static com.oarora.transactionclassifier.constants.InputOutput.CARD_NO_REGEX;
import static com.oarora.transactionclassifier.constants.InputOutput.DATE_REGEX;
import static com.oarora.transactionclassifier.constants.InputOutput.YYYYMMDD;
import static com.oarora.transactionclassifier.constants.InputOutput.YYYYMMDDHHMMSS;
import static com.oarora.transactionclassifier.constants.PurchaseGroups.PURCHASES_MAP;
import static com.oarora.transactionclassifier.constants.sb.PreProcessor.PRE_PROCESSOR_MAP;
import static com.oarora.transactionclassifier.constants.sb.TransactionCodes.TRANSACTIONS_MAP;
import static com.oarora.transactionclassifier.utils.NumberFormatter.isNumeric;

public class TransactionProcessor implements ItemProcessor<TransactionRaw, TransactionRecord> {

    private TransactionRecord transactionRecord;

    @Override
    public TransactionRecord process(final TransactionRaw transactionRaw) {

        preProcess(transactionRaw);
        transactionRaw.setDetails(cleanDetails(transactionRaw.getDetails()));
        
        transactionRecord = TransactionRecord
                .builder()
                .date(LocalDate.parse(transactionRaw.getDate(),
                        DateTimeFormatter.ofPattern(YYYYMMDD)).atStartOfDay())
                .code(Integer.parseInt(transactionRaw.getCode()))
                .type(transactionRaw.getType())
                .details(transactionRaw.getDetails())
                .amount(Double.parseDouble(transactionRaw.getAmount()))
                .build();

        updateDate();
        classifyTransaction();

        return transactionRecord;
    }

    private void preProcess(final TransactionRaw transactionRaw) {

        if (!isNumeric(transactionRaw.getCode())) {
            transactionRaw.setDetails(transactionRaw.getType());
            transactionRaw.setType("");

            switch (transactionRaw.getCode()) {
                case "D":
                    String details = transactionRaw.getDetails().toUpperCase();
                    for (Map.Entry<List<String>, Integer> entry : PRE_PROCESSOR_MAP.entrySet()) {
                        for (String identifier: entry.getKey()) {
                            if (details.contains(identifier))
                            {
                                transactionRaw.setCode(entry.getValue().toString());
                                return;
                            }
                        }
                        transactionRaw.setCode("348");
                    }
                    break;
                case "C":
                    transactionRaw.setCode("380");
                    break;
                default:
                    // TODO: Cater for unhandled
                    break;
            }
        }
    }

    private String cleanDetails(final String details) {

        // Remove "C*" string
        String newDetails = details.replace("C*", "");

        // Remove card number string
        for (String cardNoRegex: CARD_NO_REGEX) {

            Matcher cardNoMatcher = Pattern.compile(cardNoRegex).matcher(newDetails);
            if (cardNoMatcher.find()) {
                String cardNo = cardNoMatcher.group(0);
                newDetails =  newDetails.replace(cardNo, "");
            }
        }

        return newDetails;
    }

    private void updateDate() {
        Matcher dateMatcher = Pattern.compile(DATE_REGEX).matcher(transactionRecord.getDetails());

        if (dateMatcher.find()) {
            String date = dateMatcher.group(1);
            String time = dateMatcher.group(2);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS);
            transactionRecord.setDate(LocalDateTime.parse(date + " " + time, formatter));
        }
    }

    private void classifyTransaction() {

        for (Map.Entry<EXPENSETYPES, List<Integer>> entry : TRANSACTIONS_MAP.entrySet()) {

            if (entry.getValue().contains(transactionRecord.getCode())) {
                if (entry.getKey().equals(EXPENSETYPES.Miscellaneous)) {
                    classifyPurchase();
                }
                else {
                    transactionRecord.setExpenseType(entry.getKey());
                }
                return;
            }
        }
    }

    private void classifyPurchase() {

        String details = transactionRecord.getDetails().toUpperCase();

        for (Map.Entry<EXPENSETYPES, List<String>> entry : PURCHASES_MAP.entrySet()) {
            for (String merchant: entry.getValue()
            ) {
                if (details.contains(merchant))
                {
                    transactionRecord.setExpenseType(entry.getKey());
                    return;
                }
            }
        }

        transactionRecord.setExpenseType(EXPENSETYPES.Miscellaneous);
    }
}
