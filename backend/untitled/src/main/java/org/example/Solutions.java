package org.example;

public class Solutions {
    public boolean isEgoistic(String N) {
        String lastItem = N.substring(N.length()-1);
        return lastItem.equals(String.valueOf((int)Math.pow(Double.parseDouble(lastItem),2)).substring(1));

    }
}
