package com.github.chimmhuang.caption.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BccCaption {
    private List<BccBody> body;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BccBody {
        private Double from;    // 开始时间（秒）
        private Double to;      // 结束时间（秒）
        private String content; // 字幕内容
        private Location location; // 位置信息
        private String fontSize;   // 字体大小
        private String color;      // 字体颜色
        private String alpha;      // 透明度
        private String background; // 背景颜色
        private String stroke;     // 描边
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        private Integer x;  // x坐标
        private Integer y;  // y坐标
    }
} 