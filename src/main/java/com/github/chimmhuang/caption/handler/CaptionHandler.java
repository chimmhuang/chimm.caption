package com.github.chimmhuang.caption.handler;

import com.github.chimmhuang.caption.model.CommonCaption;
import java.io.File;
import java.util.List;

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
     * @return 字幕文件
     */
    File exportCaption(List<CommonCaption> captions);
} 