package com.github.chimmhuang.caption.model;

import lombok.Data;

@Data
public class SbvCaption {
    private String startTime;  // 格式：0:00:00.000
    private String endTime;    // 格式：0:00:00.000
    private String content;
} 