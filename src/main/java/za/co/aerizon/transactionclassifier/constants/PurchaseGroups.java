/* Copyright (C) 2019 Tshiamo Motaung  - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'README.md', which is part of this source code package.
 */

package za.co.aerizon.transactionclassifier.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static za.co.aerizon.transactionclassifier.constants.ExpenseTypes.EXPENSETYPES.*;

public class PurchaseGroups {

    private static final List<String> CLOTHING = Arrays.asList (
            "ALDO",
            "CLAIRE'S",
            "COTON ON",
            "FACTORY",
            "FACTORIE",
            "FOREVER NEW",
            "FOSCHINI",
            "FOSSIL",
            "GUESS",
            "H&M",
            "LGT",
            "LOVISA",
            "MR PRICE",
            "MRP",
            "MRPRICE",
            "PNP CLT",
            "RAGE",
            "SOLO",
            "SUPERGA",
            "THE FIX",
            "TRUWORTHS",
            "YDE"
    );

    private static final List<String> COMMUTE = Arrays.asList(
            "1 STOP",
            "BOKSRUIN MOTORS CONVINIBOSKR",
            "BP",
            "CALTEX",
            "ENGEN",
            "FIRST CAR LAN",
            "FOUNDERS SERVIC",
            "HTTP://WWW",
            "NOORDHEUWEL SER",
            "OXFORD FILLING",
            "PETROPORT",
            "RANT-EN-DAL M",
            "ROBERT BROOM 4548",
            "ROSEBANK CAR SERVICES",
            "SANDHURST FILLI",
            "SANDOWN GARDENS",
            "SASOL",
            "SHELL",
            "SHIELD",
            "TAXIFY",
            "TOTAL",
            "UBER",
            "WESTBRIDGE S/ST"
    );

    private static final List<String> DEPARTMENTS = Arrays.asList (
            "CHECKERS",
            "GAME",
            "PNP",
            "WOOLWORTHS"
    );

    private static final List<String> FOOD = Arrays.asList (
            "BILTONG 4 U",
            "FLM",
            "FVC",
            "MONTAGU DRIED FRUITS",
            "SPAR "
    );

    private static final List<String> HOME = Arrays.asList (
            "BB GLAMS",
            "C*HAIR",
            "C*ZENZELE FITNES",
            "CARDIES",
            "CELLINI",
            "CLICKS",
            "CRAZY STORE",
            "CNA",
            "DIS-CHEM",
            "EXCLUSIVE IMAGE SALON",
            "GOOGLE",
            "HAIR CITY",
            "JJ CALE",
            "LEVINGER",
            "MAKRO",
            "MICABELLA",
            "MR. PRICE HOM",
            "MRP H",
            "NETFLIX.COM",
            "SAMSONITE",
            "SHOWMAX",
            "SORBET",
            "THE LAUNDRY"
    );

    private static final List<String> LIQUOR = Arrays.asList (
            "LIQUOR SHOP",
            "LIQUOR CITY",
            "LIQUORSHOP",
            "THE FRIDGE",
            "TOPS"
    );

    private static final List<String> TAKEOUTS = Arrays.asList (
            "169 ON LONG",
            "ANAT",
            "BOOTLEGGER",
            "CAFFE FINETSA",
            "CHICKEN LICKE",
            "COMPASS GROUP",
            "CRAFT",
            "CROFT AND CO",
            "KFC",
            "CAFE",
            "EUROPA",
            "FEDICS",
            "GOOD CHEF",
            "KAUAI",
            "MCD",
            "MILKY LANE",
            "MILKYLANE",
            "MR DELIVERY",
            "MUGG AND BEAN",
            "MUGG & BEAN",
            "OCEAN BASKET",
            "PUBLICITY",
            "ROCKETS",
            "REEF HOTEL",
            "SAINT CHAMPAGNE BAR",
            "SHISANYAMA",
            "SHIMMY BEACH CLUB",
            "SIMPLY ASIA",
            "STARBUCKS",
            "SEATTLE",
            "STEERS",
            "STER KINEKOR",
            "THE BIG MOUTH",
            "THE GRILLHOUSE",
            "VIDA",
            "WAKABERRY",
            "WASABI"
    );

    public static final Map<ExpenseTypes.EXPENSETYPES, List<String>> PURCHASES_MAP =
            new HashMap<ExpenseTypes.EXPENSETYPES, List<String>>() {{
                put(Clothing, CLOTHING);
                put(Commute, COMMUTE);
                put(Departments, DEPARTMENTS);
                put(Food, FOOD);
                put(Home, HOME);
                put(Liquor, LIQUOR);
                put(Takeouts, TAKEOUTS);
            }};
}
