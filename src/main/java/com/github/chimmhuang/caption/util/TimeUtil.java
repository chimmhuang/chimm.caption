/*
 * Copyright (c) 2025 Chimm Huang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.chimmhuang.caption.util;

/**
 * 时间格式转换工具类
 * @author Chimm Huang
 */
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