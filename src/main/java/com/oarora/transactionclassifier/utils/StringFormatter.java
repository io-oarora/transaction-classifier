/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package com.oarora.transactionclassifier.utils;

import static com.oarora.transactionclassifier.constants.InputOutput.SPACE;

public class StringFormatter {

    public static String rightPadString(final char character, final String src, final int length) {

        StringBuilder paddedString = new StringBuilder(src + SPACE);

        while (paddedString.length() < length) {
            paddedString.append(character);
        }

        return paddedString.toString();
    }

    public static String leftPadString(final char character, final String src, final int length) {

        StringBuilder paddedString = new StringBuilder(SPACE + src);

        while (paddedString.length() < length) {
            paddedString.insert(0, character);
        }

        return paddedString.toString();
    }

}
