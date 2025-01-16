package com.github.chimmhuang.caption.factory;

import com.github.chimmhuang.caption.handler.CaptionHandler;
import com.github.chimmhuang.caption.handler.impl.BccHandler;
import com.github.chimmhuang.caption.handler.impl.SrtHandler;
import com.github.chimmhuang.caption.handler.impl.SbvHandler;

public class CaptionFactory {
    
    public static CaptionHandler getHandler(String format) {
        return switch (format.toLowerCase()) {
            case "bcc" -> new BccHandler();
            case "srt" -> new SrtHandler();
            case "sbv" -> new SbvHandler();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
} 