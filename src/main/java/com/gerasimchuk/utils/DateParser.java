package com.gerasimchuk.utils;

/**
 * The type Date parser.
 */
public class DateParser {

    // Tue Sep 18 02:35:39 MSK 2018
    // Ddd MMM dd hh:mm:ss z yyyy


    /**
     * Parse year int.
     *
     * @param s the s
     * @return the int
     */
    public static int parseYear(String s) {
        String sYear = s.substring(24,28);
        int year = 0;
        try{
            year = Integer.parseInt(sYear);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return year;
    }

    /**
     * Parse month int.
     *
     * @param s the s
     * @return the int
     */
    public static int parseMonth(String s){
        String month =  s.substring(4,7);
        if (month.equals("Jan")) return 0;
        if (month.equals("Feb")) return 1;
        if (month.equals("Mar")) return 2;
        if (month.equals("Apr")) return 3;
        if (month.equals("May")) return 4;
        if (month.equals("Jun")) return 5;
        if (month.equals("Jul")) return 6;
        if (month.equals("Aug")) return 7;
        if (month.equals("Sep")) return 8;
        if (month.equals("Oct")) return 9;
        if (month.equals("Nov")) return 10;
        if (month.equals("Dec")) return 11;
        return 0;
    }


    /**
     * Parse day int.
     *
     * @param s the s
     * @return the int
     */
    public static int parseDay(String s){
        String sDay =  s.substring(8,10);
        int day = 0;
        try{
            day = Integer.parseInt(sDay);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        if (day == 0) return 0;
        return day;
    }


    /**
     * Parse hour int.
     *
     * @param s the s
     * @return the int
     */
    public static int parseHour(String s) {
        String sHour = s.substring(11,13);
        int hour = 0;
        try {
            hour = Integer.parseInt(sHour);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return hour;
    }


    /**
     * Parse minutes int.
     *
     * @param s the s
     * @return the int
     */
    public static int parseMinutes(String s) {
        String sMinutes = s.substring(14,16);
        int minutes = 0;
        try {
            minutes = Integer.parseInt(sMinutes);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return minutes;
    }

    /**
     * Parse seconds int.
     *
     * @param s the s
     * @return the int
     */
    public static int parseSeconds(String s){
        String sSeconds = s.substring(17,19);
        int seconds = 0;
        try{
            seconds = Integer.parseInt(sSeconds);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return seconds;
    }
}
