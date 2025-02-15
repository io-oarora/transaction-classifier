/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package za.co.aerizon.transactionclassifier.utils;

public class NumberFormatter {

    public static boolean isNumeric(final String input) {
        try {
            Integer.parseInt(input);
            return true;

        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
