package com.github.chimmhuang.caption.util;

public class TimeUtil {
    
    /**
     * 将 SRT 格式时间(00:00:00,000)转换为 BCC 格式时间(秒)
     */
    public static double srtToBccTime(String srtTime) {
        String[] parts = srtTime.replace(",", ":").split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        int milliseconds = Integer.parseInt(parts[3]);
        
        return hours * 3600 + minutes * 60 + seconds + milliseconds / 1000.0;
    }
    
    /**
     * 将 BCC 格式时间(秒)转换为 SRT 格式时间(00:00:00,000)
     */
    public static String bccToSrtTime(double bccTime) {
        int hours = (int) (bccTime / 3600);
        int minutes = (int) ((bccTime % 3600) / 60);
        int seconds = (int) (bccTime % 60);
        int milliseconds = (int) ((bccTime % 1) * 1000);
        
        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, milliseconds);
    }
    
    /**
     * 将 SRT 格式时间(00:00:00,000)转换为 SBV 格式时间(0:00:00.000)
     */
    public static String srtToSbvTime(String srtTime) {
        return srtTime.replace(",", ".");
    }
    
    /**
     * 将 SBV 格式时间(0:00:00.000)转换为 SRT 格式时间(00:00:00,000)
     */
    public static String sbvToSrtTime(String sbvTime) {
        return sbvTime.replace(".", ",");
    }
} 