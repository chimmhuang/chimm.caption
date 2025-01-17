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
import com.github.chimmhuang.caption.model.SbvCaption;

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
 * SBV 格式字幕处理器
 */
public class SbvHandler implements CaptionHandler {
    private static final Logger LOGGER = Logger.getLogger(SbvHandler.class.getName());
    private static final String TIME_SEPARATOR = ",";
    
    @Override
    public List<CommonCaption> importCaption(File file) {
        Objects.requireNonNull(file, "Input file must not be null");
        LOGGER.log(Level.INFO, "Importing SBV file: {0}", file.getAbsolutePath());
        
        List<CommonCaption> commonCaptions = new ArrayList<>();
        try (BufferedReader reader = createReader(file)) {
            processFileContent(reader, commonCaptions);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read SBV file", e);
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
        SbvCaption sbvCaption = null;
        StringBuilder content = new StringBuilder();
        
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                if (sbvCaption != null) {
                    finalizeSbvCaption(sbvCaption, content, commonCaptions);
                    content = new StringBuilder();
                }
                sbvCaption = null;
                continue;
            }
            
            sbvCaption = processLine(line, sbvCaption, content);
        }
        
        // 处理最后一条字幕
        if (sbvCaption != null) {
            finalizeSbvCaption(sbvCaption, content, commonCaptions);
        }
    }
    
    private SbvCaption processLine(String line, SbvCaption sbvCaption, StringBuilder content) {
        if (line.contains(TIME_SEPARATOR)) {
            sbvCaption = new SbvCaption();
            processTimeLine(line, sbvCaption);
        } else if (sbvCaption != null) {
            content.append(line).append("\n");
        }
        return sbvCaption;
    }
    
    private void processTimeLine(String line, SbvCaption sbvCaption) {
        String[] times = line.split(TIME_SEPARATOR);
        if (times.length == 2) {
            sbvCaption.setStartTime(times[0].trim());
            sbvCaption.setEndTime(times[1].trim());
        } else {
            LOGGER.log(Level.WARNING, "Invalid time format: {0}", line);
        }
    }
    
    private void finalizeSbvCaption(SbvCaption sbvCaption, StringBuilder content, 
            List<CommonCaption> commonCaptions) {
        sbvCaption.setContent(content.toString().trim());
        commonCaptions.add(convertToCommonCaption(sbvCaption));
    }

    @Override
    public File exportCaption(List<CommonCaption> captions, File outputFile) {
        Objects.requireNonNull(captions, "Captions list must not be null");
        Objects.requireNonNull(outputFile, "Output file must not be null");
        LOGGER.log(Level.INFO, "Exporting {0} captions to SBV format: {1}", 
            new Object[]{captions.size(), outputFile.getAbsolutePath()});
        
        try (BufferedWriter writer = createWriter(outputFile)) {
            writeSubtitles(writer, captions);
            LOGGER.log(Level.INFO, "Successfully exported to: {0}", outputFile.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write SBV file", e);
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
        for (CommonCaption caption : captions) {
            writeSubtitle(writer, caption);
        }
    }
    
    private void writeSubtitle(BufferedWriter writer, CommonCaption caption) 
            throws IOException {
        writer.write(caption.getStartTime() + TIME_SEPARATOR + caption.getEndTime());
        writer.newLine();
        writer.write(caption.getContent());
        writer.newLine();
        writer.newLine();
    }
    
    private CommonCaption convertToCommonCaption(SbvCaption sbvCaption) {
        CommonCaption commonCaption = new CommonCaption();
        commonCaption.setStartTime(sbvCaption.getStartTime());
        commonCaption.setEndTime(sbvCaption.getEndTime());
        commonCaption.setContent(sbvCaption.getContent());
        return commonCaption;
    }
} 