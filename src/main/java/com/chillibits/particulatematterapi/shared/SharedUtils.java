/*
 * Copyright © Marc Auberer 2019 - 2020. All rights reserved
 */

package com.chillibits.particulatematterapi.shared;

public class SharedUtils {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        double newValue = value * factor;
        long tmp = Math.round(newValue);
        return (double) tmp / factor;
    }

    /*public static String hashMD5(String stringToHash) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(stringToHash.getBytes());
            byte[] digest = messageDigest.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (Exception ignored) {}
        return stringToHash;
    }*/
}