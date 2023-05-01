package com.example.mystandardapp;

public class Enums {
    enum DataOptions {
        SMALL("GB_1", 1),
        MEDIUM("GB_2", 2),
        LARGE("GB_5", 3);

        private String stringValue;
        private int intValue;

        private DataOptions(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }

        public int getIntValue() {
            return intValue;
        }
    }
    enum SmsOptions {
        SMALL("SMS_100", 1),
        MEDIUM("SMS_500", 2),
        LARGE("SMS_INF", 3);

        private String stringValue;
        private int intValue;

        private SmsOptions(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }

        public int getIntValue() {
            return intValue;
        }
    }
}
