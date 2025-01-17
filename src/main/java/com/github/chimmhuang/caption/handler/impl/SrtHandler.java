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

package com.github.chimmhuang.caption.handler.impl;

import com.github.chimmhuang.caption.handler.CaptionHandler;
import com.github.chimmhuang.caption.model.CommonCaption;
import com.github.chimmhuang.caption.model.SrtCaption;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * SRT 格式字幕处理器
 */
public class SrtHandler implements CaptionHandler {
    private static final Logger LOGGER = Logger.getLogger(SrtHandler.class.getName());
    private static final String TIME_SEPARATOR = " --> ";
    
    @Override
    public List<CommonCaption> importCaption(File file) {
        Objects.requireNonNull(file, "Input file must not be null");
        LOGGER.log(Level.INFO, "Importing SRT file: {0}", file.getAbsolutePath());
        
        List<CommonCaption> commonCaptions = new ArrayList<>();
        try (BufferedReader reader = createReader(file)) {
            processFileContent(reader, commonCaptions);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read SRT file", e);
        }
        
        return commonCaptions;
    }
    
    private BufferedReader createReader(File file) throws IOException {
        return new BufferedReader(
            new InputStreamReader(
                new FileInputStream(file), 
                StandardCharsets.UTF_8
            )
        );
    }
    
    private void processFileContent(BufferedReader reader, List<CommonCaption> commonCaptions) 
            throws IOException {
        String line;
        SrtCaption srtCaption = null;
        StringBuilder content = new StringBuilder();
        
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                if (srtCaption != null) {
                    finalizeSrtCaption(srtCaption, content, commonCaptions);
                    content = new StringBuilder();
                }
                srtCaption = null;
                continue;
            }
            
            srtCaption = processLine(line, srtCaption, content);
        }
        
        // 处理最后一条字幕
        if (srtCaption != null) {
            finalizeSrtCaption(srtCaption, content, commonCaptions);
        }
    }
    
    private SrtCaption processLine(String line, SrtCaption srtCaption, StringBuilder content) {
        if (srtCaption == null) {
            srtCaption = new SrtCaption();
            try {
                srtCaption.setIndex(Integer.parseInt(line));
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid subtitle index: {0}", line);
                return null;
            }
        } else if (line.contains(TIME_SEPARATOR)) {
            processTimeLine(line, srtCaption);
        } else {
            content.append(line).append("\n");
        }
        return srtCaption;
    }
    
    private void processTimeLine(String line, SrtCaption srtCaption) {
        String[] times = line.split(TIME_SEPARATOR);
        if (times.length == 2) {
            srtCaption.setStartTime(times[0].trim());
            srtCaption.setEndTime(times[1].trim());
        } else {
            LOGGER.log(Level.WARNING, "Invalid time format: {0}", line);
        }
    }
    
    private void finalizeSrtCaption(SrtCaption srtCaption, StringBuilder content, 
            List<CommonCaption> commonCaptions) {
        srtCaption.setContent(content.toString().trim());
        commonCaptions.add(convertToCommonCaption(srtCaption));
    }

    @Override
    public File exportCaption(List<CommonCaption> captions, File outputFile) {
        Objects.requireNonNull(captions, "Captions list must not be null");
        Objects.requireNonNull(outputFile, "Output file must not be null");
        LOGGER.log(Level.INFO, "Exporting {0} captions to SRT format: {1}", 
            new Object[]{captions.size(), outputFile.getAbsolutePath()});
        
        try (BufferedWriter writer = createWriter(outputFile)) {
            writeSubtitles(writer, captions);
            LOGGER.log(Level.INFO, "Successfully exported to: {0}", outputFile.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write SRT file", e);
        }
        
        return outputFile;
    }
    
    private BufferedWriter createWriter(File file) throws IOException {
        return new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(file), 
                StandardCharsets.UTF_8
            )
        );
    }
    
    private void writeSubtitles(BufferedWriter writer, List<CommonCaption> captions) 
            throws IOException {
        int index = 1;
        for (CommonCaption caption : captions) {
            writeSubtitle(writer, caption, index++);
        }
    }
    
    private void writeSubtitle(BufferedWriter writer, CommonCaption caption, int index) 
            throws IOException {
        writer.write(String.valueOf(index));
        writer.newLine();
        writer.write(caption.getStartTime() + TIME_SEPARATOR + caption.getEndTime());
        writer.newLine();
        writer.write(caption.getContent());
        writer.newLine();
        writer.newLine();
    }
    
    private CommonCaption convertToCommonCaption(SrtCaption srtCaption) {
        CommonCaption commonCaption = new CommonCaption();
        commonCaption.setStartTime(srtCaption.getStartTime());
        commonCaption.setEndTime(srtCaption.getEndTime());
        commonCaption.setContent(srtCaption.getContent());
        return commonCaption;
    }
} 