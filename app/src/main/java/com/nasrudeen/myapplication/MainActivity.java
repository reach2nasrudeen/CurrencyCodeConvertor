package com.nasrudeen.myapplication;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
//    public String[] currencyCode = new String[]{
//       "ABW","AFG","AGO","AIA","ALA","ALB","AND","ANT","ARE","ARG","ARM","ASM","ATA","ATF",
//            "ATG","AUS","AUT","AZE"
//    };
public String[] currencyCode = new String[]{
//        "ALL","DZD","ARS","AWG","AUD","BSD"
        "AED",
        "AFN",
        "ALL",
        "AMD",
        "ANG",
        "AOA",
        "ARS",
        "AUD",
        "AWG",
        "AZN",
        "BAM",
        "BBD",
        "BDT",
        "BGN",
        "BHD",
        "BIF",
        "BMD",
        "BND",
        "BOB",
        "BRL",
        "BSD",
        "BTN",
        "BWP",
//        "BYN",
        "BZD",
        "CAD",
        "CDF",
        "CHF",
        "CLP",
        "CNY",
        "COP",
        "CRC",
//        "CUC",
        "CUP",
        "CVE",
        "CZK",
        "DJF",
        "DKK",
        "DOP",
        "DZD",
        "EGP",
        "ERN",
        "ETB",
        "EUR",
        "FJD",
        "FKP",
        "GBP",
        "GEL",
//        "GGP",
        "GHS",
        "GIP",
        "GMD",
        "GNF",
        "GTQ",
        "GYD",
        "HKD",
        "HNL",
        "HRK",
        "HTG",
        "HUF",
        "IDR",
        "ILS",
//        "IMP",
        "INR",
        "IQD",
        "IRR",
        "ISK",
//        "JEP",
        "JMD",
        "JOD",
        "JPY",
        "KES",
        "KGS",
        "KHR",
        "KMF",
        "KPW",
        "KRW",
        "KWD",
        "KYD",
        "KZT",
        "LAK",
        "LBP",
        "LKR",
        "LRD",
//        "LSL",
        "LYD",
        "MAD",
        "MDL",
        "MGA",
        "MKD",
        "MMK",
        "MNT",
        "MOP",
        "MRO",
        "MUR",
//        "MVR",
        "MWK",
        "MXN",
        "MYR",
        "MZN",
        "NAD",
        "NGN",
        "NIO",
        "NOK",
        "NPR",
        "NZD",
        "OMR",
        "PAB",
        "PEN",
        "PGK",
        "PHP",
        "PKR",
        "PLN",
        "PYG",
        "QAR",
        "RON",
        "RSD",
        "RUB",
        "RWF",
        "SAR",
        "SBD",
        "SCR",
        "SDG",
        "SEK",
        "SGD",
        "SHP",
        "SLL",
        "SOS",
//        "SPL",
        "SRD",
        "STD",
//        "SVC",
        "SYP",
        "SZL",
        "THB",
//        "TJS",
//        "TMT",
        "TND",
        "TOP",
        "TRY",
        "TTD",
//        "TVD",
        "TWD",
        "TZS",
        "UAH",
        "UGX",
        "USD",
        "UYU",
        "UZS",
        "VEF",
        "VND",
        "VUV",
        "WST",
        "XAF",
        "XCD",
//        "XDR",
        "XOF",
        "XPF",
        "YER",
        "ZAR",
        "ZMW"
//        "ZWD"
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView currencyText = (TextView) findViewById(R.id.currencyText);
        for(int i=0;i<currencyCode.length;i++){
            Currency currency = Currency.getInstance(currencyCode[i]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                currencyText.append("\nCode : "+currency.getDisplayName()+" ==> "+Utils.getCurrencySymbol(currencyCode[i]));
            }
        }
//        currencyText.setText("US Symbol ==> "+Utils.getCurrencySymbol(Currency.getInstance(Locale.US).getCurrencyCode()));
//        currencyText.append("\nJapan Symbol ==> "+Utils.getCurrencySymbol(Currency.getInstance(Locale.JAPAN).getCurrencyCode()));
//        currencyText.append("\nUK Symbol ==> "+Utils.getCurrencySymbol(Currency.getInstance(Locale.UK).getCurrencyCode()));
//
//        //for your case that you want to get Euro symbol because France are with Euro symnol
//        currencyText.append("\nFrance Symbol ==> "+Utils.getCurrencySymbol(Currency.getInstance(Locale.FRANCE).getCurrencyCode()));
//        //you can get symbol also if you write string of your desired currency
//        currencyText.append("\nIndian Symbol ==> "+Utils.getCurrencySymbol("INR"));
    }
    static class Utils {
        public static SortedMap<Currency, Locale> currencyLocaleMap;

        static {
            currencyLocaleMap = new TreeMap<Currency, Locale>(new Comparator<Currency>() {
                public int compare(Currency c1, Currency c2) {
                    return c1.getCurrencyCode().compareTo(c2.getCurrencyCode());
                }
            });
            for (Locale locale : Locale.getAvailableLocales()) {
                try {
                    Currency currency = Currency.getInstance(locale);
                    currencyLocaleMap.put(currency, locale);
                } catch (Exception e) {
                }
            }
        }


        public static String getCurrencySymbol(String currencyCode) {
            Currency currency = Currency.getInstance(currencyCode);
            System.out.println(currencyCode + ":-" + currency.getSymbol(currencyLocaleMap.get(currency)));
            return currency.getSymbol(currencyLocaleMap.get(currency));
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                    }

                    // Right to left swipe action
                    else
                    {
                        Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                    }

                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
