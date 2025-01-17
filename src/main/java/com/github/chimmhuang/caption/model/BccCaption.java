/*
 * MIT License
 *
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

package com.github.chimmhuang.caption.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

/**
 * BCC Caption model class
 * 
 * @author Chimm Huang
 */
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
        private Integer location; // 位置信息（1-9，代表9个方位）
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