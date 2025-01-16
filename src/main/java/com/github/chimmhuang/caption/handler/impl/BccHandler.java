package com.github.chimmhuang.caption.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chimmhuang.caption.handler.CaptionHandler;
import com.github.chimmhuang.caption.model.BccCaption;
import com.github.chimmhuang.caption.model.CommonCaption;
import com.github.chimmhuang.caption.util.TimeUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * BCC 格式字幕处理器
 */
public class BccHandler implements CaptionHandler {
    private static final Logger LOGGER = Logger.getLogger(BccHandler.class.getName());
    private static final String OUTPUT_FILENAME = "output.bcc";
    
    private final ObjectMapper objectMapper;
    
    public BccHandler() {
        this.objectMapper = new ObjectMapper();
    }
    
    @Override
    public List<CommonCaption> importCaption(File file) {
        try {
            LOGGER.log(Level.INFO, "Importing BCC file: {0}", file.getAbsolutePath());
            BccCaption bccCaption = objectMapper.readValue(
                Objects.requireNonNull(file, "Input file must not be null"), 
                BccCaption.class
            );
            
            return convertToCommonCaptions(bccCaption);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to read BCC file", e);
            throw new IllegalStateException("Failed to read BCC file", e);
        }
    }
    
    private List<CommonCaption> convertToCommonCaptions(BccCaption bccCaption) {
        List<CommonCaption> commonCaptions = new ArrayList<>();
        if (bccCaption.getBody() == null) {
            return commonCaptions;
        }
        
        for (BccCaption.BccBody body : bccCaption.getBody()) {
            commonCaptions.add(convertBodyToCommonCaption(body));
        }
        
        return commonCaptions;
    }
    
    private CommonCaption convertBodyToCommonCaption(BccCaption.BccBody body) {
        CommonCaption commonCaption = new CommonCaption();
        commonCaption.setStartTime(TimeUtil.bccToSrtTime(body.getFrom()));
        commonCaption.setEndTime(TimeUtil.bccToSrtTime(body.getTo()));
        commonCaption.setContent(body.getContent());
        
        setLocationIfPresent(body, commonCaption);
        setStyleProperties(body, commonCaption);
        
        return commonCaption;
    }
    
    private void setLocationIfPresent(BccCaption.BccBody body, CommonCaption commonCaption) {
        if (body.getLocation() != null) {
            commonCaption.setLocation(String.format("x=%d,y=%d",
                    body.getLocation().getX(),
                    body.getLocation().getY()));
        }
    }
    
    private void setStyleProperties(BccCaption.BccBody body, CommonCaption commonCaption) {
        commonCaption.setFontSize(body.getFontSize());
        commonCaption.setFontColor(body.getColor());
        commonCaption.setBackgroundAlpha(body.getAlpha());
        commonCaption.setBackgroundColor(body.getBackground());
        commonCaption.setStroke(body.getStroke());
    }

    @Override
    public File exportCaption(List<CommonCaption> captions) {
        try {
            Objects.requireNonNull(captions, "Captions list must not be null");
            LOGGER.log(Level.INFO, "Exporting {0} captions to BCC format", captions.size());
            
            BccCaption bccCaption = convertToBccCaption(captions);
            File outputFile = new File(OUTPUT_FILENAME);
            
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(outputFile, bccCaption);
            
            LOGGER.log(Level.INFO, "Successfully exported to: {0}", outputFile.getAbsolutePath());
            return outputFile;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write BCC file", e);
            throw new IllegalStateException("Failed to write BCC file", e);
        }
    }
    
    private BccCaption convertToBccCaption(List<CommonCaption> captions) {
        BccCaption bccCaption = new BccCaption();
        List<BccCaption.BccBody> bodyList = new ArrayList<>();
        
        for (CommonCaption caption : captions) {
            bodyList.add(convertCommonCaptionToBody(caption));
        }
        
        bccCaption.setBody(bodyList);
        return bccCaption;
    }
    
    private BccCaption.BccBody convertCommonCaptionToBody(CommonCaption caption) {
        BccCaption.BccBody body = new BccCaption.BccBody();
        body.setFrom(TimeUtil.srtToBccTime(caption.getStartTime()));
        body.setTo(TimeUtil.srtToBccTime(caption.getEndTime()));
        body.setContent(caption.getContent());
        
        setBodyLocationIfPresent(caption, body);
        setBodyStyleProperties(caption, body);
        
        return body;
    }
    
    private void setBodyLocationIfPresent(CommonCaption caption, BccCaption.BccBody body) {
        if (caption.getLocation() != null) {
            BccCaption.Location location = parseLocation(caption.getLocation());
            body.setLocation(location);
        }
    }
    
    private BccCaption.Location parseLocation(String locationStr) {
        BccCaption.Location location = new BccCaption.Location();
        String[] parts = locationStr.split(",");
        
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                setLocationCoordinate(location, keyValue[0], keyValue[1]);
            }
        }
        
        return location;
    }
    
    private void setLocationCoordinate(BccCaption.Location location, String key, String value) {
        try {
            int coordinate = Integer.parseInt(value);
            if ("x".equals(key)) {
                location.setX(coordinate);
            } else if ("y".equals(key)) {
                location.setY(coordinate);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Invalid coordinate value: {0}", value);
        }
    }
    
    private void setBodyStyleProperties(CommonCaption caption, BccCaption.BccBody body) {
        body.setFontSize(caption.getFontSize());
        body.setColor(caption.getFontColor());
        body.setAlpha(caption.getBackgroundAlpha());
        body.setBackground(caption.getBackgroundColor());
        body.setStroke(caption.getStroke());
    }
} 