package com.github.chimmhuang.caption.model;

import lombok.Data;

@Data
public class SrtCaption {
    private Integer index;
    private String startTime;  // 格式：00:00:00,000
    private String endTime;    // 格式：00:00:00,000
    private String content;
} 