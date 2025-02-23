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

package com.github.chimmhuang.caption.handler;

import com.github.chimmhuang.caption.model.CommonCaption;
import java.io.File;
import java.util.List;

/**
 * Caption file handler interface
 * 
 * @author Chimm Huang
 */
public interface CaptionHandler {
    /**
     * 导入字幕文件
     * @param file 字幕文件
     * @return 通用字幕对象列表
     */
    List<CommonCaption> importCaption(File file);

    /**
     * 导出字幕文件
     * @param captions 通用字幕对象列表
     * @param outputFile 输出文件
     * @return 字幕文件
     */
    File exportCaption(List<CommonCaption> captions, File outputFile);
} 