package com.tw.trains.utils;

/**
 * @author: yuanbo
 * @description: 时间格式化工具类
 * 
 */
public final class TimeUtils {
    private TimeUtils() {}

    private static final int MINUTES_PER_DAY = 1440;

    /**  
    * @Description: 根据相对于凌晨12点过去的分钟数计算并格式化成12小时制时间  
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    public static String format(final int passedMinutesFromMidNight) {
        if (passedMinutesFromMidNight < 0) {
            throw new IllegalArgumentException("passedMinutesFromMidNight must not be less than 0");
        }

        final int hours = passedMinutesFromMidNight >= MINUTES_PER_DAY
                ? (passedMinutesFromMidNight % MINUTES_PER_DAY) / 60 : passedMinutesFromMidNight / 60;
        final int minutes = passedMinutesFromMidNight >= MINUTES_PER_DAY
                ? (passedMinutesFromMidNight % MINUTES_PER_DAY) % 60 : passedMinutesFromMidNight % 60;

        return new StringBuffer(formatHours(hours)).append(":").append(formatMinutes(minutes)).append(getAMPM(hours))
                .toString();
    }

    /**  
    * @Description: 根据小时数判断是AM还是PM  
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    private static String getAMPM(final int hours) {
        return hours > 11 ? "PM" : "AM";
    }

    /**  
    * @Description: 格式化Hours     
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    private static String formatHours(final int hours) {
        // 如果为凌晨12点或正午12点，则为12：XXAM 和12：XXPM
        if (hours == 0 || hours == 12) {
            return "12";
        } else {
            return hours < 12 ? formatDigit(hours) : formatDigit(hours - 12);
        }
    }

    /**  
    * @Description: 格式化Minutes  
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    private static String formatMinutes(final int minutes) {
        return formatDigit(minutes);
    }

    /**  
    * @Description:  将数字格式化为字符串格式，小于10的数字需要格式化为0X 
    * @param:
    * @return:   
    * @throws: 
    * @TODO:
    */
    private static String formatDigit(final int digit) {
        return digit < 10 ? new StringBuffer("0").append(String.valueOf(digit)).toString() : String.valueOf(digit);
    }
}
