package com.ll.standard.util;

public class Ut {
    public static class str {
        public static int parseInt(String value, int defaultValue) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }
}
