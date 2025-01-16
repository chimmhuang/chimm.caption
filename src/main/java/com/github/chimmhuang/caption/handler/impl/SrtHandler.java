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
    private static final String OUTPUT_FILENAME = "output.srt";
    private static final String TIME_SEPARATOR = " --> ";
    
    @Override
    public List<CommonCaption> importCaption(File file) {
        Objects.requireNonNull(file, "Input file must not be null");
        LOGGER.log(Level.INFO, "Importing SRT file: {0}", file.getAbsolutePath());
        
        List<CommonCaption> commonCaptions = new ArrayList<>();
        try (BufferedReader reader = createReader(file)) {
            processFileContent(reader, commonCaptions);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to read SRT file", e);
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
    public File exportCaption(List<CommonCaption> captions) {
        Objects.requireNonNull(captions, "Captions list must not be null");
        LOGGER.log(Level.INFO, "Exporting {0} captions to SRT format", captions.size());
        
        File outputFile = new File(OUTPUT_FILENAME);
        try (BufferedWriter writer = createWriter(outputFile)) {
            writeSubtitles(writer, captions);
            LOGGER.log(Level.INFO, "Successfully exported to: {0}", outputFile.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write SRT file", e);
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