/*
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

package com.github.chimmhuang.caption.ui;

import com.github.chimmhuang.caption.factory.CaptionFactory;
import com.github.chimmhuang.caption.handler.CaptionHandler;
import com.github.chimmhuang.caption.model.CommonCaption;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Objects;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;

/**
 * 字幕转换工具主窗口
 * @author Chimm Huang
 */
public class MainWindow extends JFrame {
    private static final String VERSION = readVersion();
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 300;
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 30;
    private static final String[] SUPPORTED_FORMATS = {"bcc", "srt", "sbv"};
    private static final String[] SUPPORTED_LANGUAGES = {"zh_CN", "en"};
    
    private ResourceBundle bundle;
    private File selectedFile;
    private JComboBox<String> targetFormatCombo;
    private JComboBox<String> languageCombo;
    private JProgressBar progressBar;
    private JLabel fileNameLabel;
    private JLabel formatLabel;
    private JButton selectButton;
    private JButton convertButton;
    
    private static final Color YOUTUBE_RED = new Color(255, 0, 0, 179);    // 70% 透明度的红色
    private static final Color BILIBILI_BLUE = new Color(0, 161, 214, 179); // 70% 透明度的蓝色

    /**
     * 创建主窗口
     */
    public MainWindow() {
        loadBundle(Locale.getDefault());
        initComponents();
    }
    
    private void loadBundle(Locale locale) {
        bundle = ResourceBundle.getBundle("i18n.messages", locale);
    }
    
    private void initComponents() {
        setTitle(bundle.getString("app.title"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        // 设置应用图标
        try {
            setIconImage(javax.imageio.ImageIO.read(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResourceAsStream("icon.png"),
                    "Icon resource not found"
                )
            ));
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to load application icon", e);
        }
        
        // 使用渐变背景面板
        JPanel mainPanel = createMainPanel();
        mainPanel.setOpaque(false);  // 使面板透明，以显示渐变背景
        
        // 创建渐变背景
        GradientPanel backgroundPanel = new GradientPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(mainPanel);
        
        // 使用内容面板
        setContentPane(backgroundPanel);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * 创建渐变背景面板
     */
    private static class GradientPanel extends JPanel {
        public GradientPanel() {
            setOpaque(true);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int w = getWidth();
            int h = getHeight();
            
            // 创建渐变色背景
            GradientPaint gradient = new GradientPaint(0, 0, YOUTUBE_RED, w, 0, BILIBILI_BLUE);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, w, h);
            
            // 增加白色背景的不透明度，使文字更清晰
            g2d.setColor(new Color(255, 255, 255, 204)); // 80% 不透明的白色
            g2d.fillRect(0, 0, w, h);
            
            g2d.dispose();
        }
    }
    
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setOpaque(false);
        
        JPanel progressPanel = createProgressPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel bottomPanel = createBottomPanel();  // 创建底部面板
        
        // 设置所有面板为透明
        progressPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        bottomPanel.setOpaque(false);
        
        mainPanel.add(progressPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        return mainPanel;
    }
    
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        
        // 版本号标签（左侧）
        JLabel versionLabel = new JLabel("v" + VERSION);
        versionLabel.setForeground(Color.BLACK);
        versionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        // 语言选择面板（右侧）
        JPanel languagePanel = createLanguagePanel();
        
        bottomPanel.add(versionLabel, BorderLayout.WEST);
        bottomPanel.add(languagePanel, BorderLayout.EAST);
        
        return bottomPanel;
    }
    
    private JPanel createLanguagePanel() {
        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        languagePanel.setOpaque(false);
        
        languageCombo = new JComboBox<>(SUPPORTED_LANGUAGES);
        languageCombo.setSelectedItem(Locale.getDefault().toString());
        languageCombo.addActionListener(e -> switchLanguage());
        languageCombo.setForeground(Color.BLACK);
        languageCombo.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        
        languagePanel.add(languageCombo);
        return languagePanel;
    }
    
    private void switchLanguage() {
        String selectedLanguage = (String) languageCombo.getSelectedItem();
        if (selectedLanguage != null) {
            LOGGER.log(Level.INFO, "Switching language to: {0}", selectedLanguage);
            loadBundle(new Locale(selectedLanguage.replace("_", "-")));
            updateComponentTexts();
        }
    }
    
    private void updateComponentTexts() {
        setTitle(bundle.getString("app.title"));
        selectButton.setText(bundle.getString("app.upload"));
        convertButton.setText(bundle.getString("app.convert"));
        formatLabel.setText(bundle.getString("app.format.target"));
        
        // 更新进度条状态文本
        if (selectedFile == null) {
            progressBar.setString("Ready");
        } else {
            progressBar.setString(progressBar.getString());
        }
    }
    
    private JPanel createProgressPanel() {
        JPanel progressPanel = new JPanel(new BorderLayout(5, 5));
        progressPanel.setOpaque(false);
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("Ready");
        progressBar.setForeground(BILIBILI_BLUE);
        progressBar.setBackground(new Color(255, 255, 255, 128));
        progressBar.setForeground(Color.BLACK);
        
        fileNameLabel = new JLabel(" ");
        fileNameLabel.setForeground(Color.BLACK);
        
        progressPanel.add(fileNameLabel, BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);
        return progressPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        
        selectButton = new JButton(bundle.getString("app.upload"));
        selectButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        styleButton(selectButton);
        selectButton.addActionListener(e -> {
            JFileChooser fileChooser = createFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                handleFileSelection(fileChooser.getSelectedFile());
            }
        });
        
        // 添加格式选择面板
        JPanel formatPanel = createFormatPanel();
        formatPanel.setOpaque(false);
        
        convertButton = new JButton(bundle.getString("app.convert"));
        convertButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        styleButton(convertButton);
        convertButton.setEnabled(false);
        convertButton.addActionListener(e -> startConversion());
        
        buttonPanel.add(selectButton);
        buttonPanel.add(formatPanel);  // 添加格式选择面板
        buttonPanel.add(convertButton);
        
        return buttonPanel;
    }
    
    private void styleButton(JButton button) {
        button.setForeground(Color.BLACK);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(false);
    }
    
    private JPanel createFormatPanel() {
        JPanel formatPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formatPanel.setOpaque(false);
        
        targetFormatCombo = new JComboBox<>(SUPPORTED_FORMATS);
        targetFormatCombo.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        targetFormatCombo.setForeground(Color.BLACK);
        
        formatLabel = new JLabel(bundle.getString("app.format.target"));
        formatLabel.setForeground(Color.BLACK);
        
        formatPanel.add(formatLabel);
        formatPanel.add(targetFormatCombo);
        
        return formatPanel;
    }
    
    private SwingWorker<File, Integer> createConversionWorker() {
        return new SwingWorker<>() {
            @Override
            protected File doInBackground() throws Exception {
                String sourceFormat = getSourceFormat(selectedFile);
                String targetFormat = Objects.requireNonNull(
                    (String) targetFormatCombo.getSelectedItem(),
                    "Target format must not be null"
                );
                
                // 获取输出文件路径
                File outputFile = getOutputFile(selectedFile, targetFormat);
                
                publish(20);
                CaptionHandler sourceHandler = CaptionFactory.getHandler(sourceFormat);
                
                publish(40);
                List<CommonCaption> captions = sourceHandler.importCaption(selectedFile);
                
                publish(60);
                CaptionHandler targetHandler = CaptionFactory.getHandler(targetFormat);
                
                publish(80);
                return targetHandler.exportCaption(captions, outputFile);
            }
            
            @Override
            protected void process(List<Integer> chunks) {
                if (!chunks.isEmpty()) {
                    int progress = chunks.get(chunks.size() - 1);
                    updateProgress(progress);
                }
            }
            
            @Override
            protected void done() {
                try {
                    File outputFile = get();
                    handleConversionSuccess(outputFile);
                } catch (Exception e) {
                    handleConversionError(e);
                } finally {
                    setComponentsEnabled(true);
                }
            }
        };
    }
    
    private void updateProgress(int progress) {
        progressBar.setValue(progress);
        progressBar.setString(progress + "%");
    }
    
    private void handleConversionSuccess(File outputFile) {
        progressBar.setValue(100);
        progressBar.setString("Completed");
        LOGGER.log(Level.INFO, "Conversion completed: {0}", outputFile.getAbsolutePath());
        JOptionPane.showMessageDialog(this, "Conversion completed: " + outputFile.getAbsolutePath());
    }
    
    private void handleConversionError(Exception e) {
        progressBar.setString("Error");
        LOGGER.log(Level.SEVERE, "Conversion failed", e);
        JOptionPane.showMessageDialog(this, 
                "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void setComponentsEnabled(boolean enabled) {
        for (Component component : getContentPane().getComponents()) {
            component.setEnabled(enabled);
        }
    }
    
    private String getSourceFormat(File file) {
        String name = Objects.requireNonNull(file, "File must not be null")
            .getName().toLowerCase();
        
        for (String format : SUPPORTED_FORMATS) {
            if (name.endsWith("." + format)) {
                return format;
            }
        }
        
        throw new IllegalArgumentException("Unsupported file format: " + name);
    }
    
    /**
     * 获取输出文件路径，与输入文件保持在同一目录
     * @param inputFile 输入文件
     * @param targetFormat 目标格式
     * @return 输出文件对象
     */
    private File getOutputFile(File inputFile, String targetFormat) {
        String originalName = inputFile.getName();
        String newName = originalName.substring(0, originalName.lastIndexOf('.')) + "." + targetFormat;
        return new File(inputFile.getParent(), newName);
    }
    
    /**
     * 更新目标格式下拉框选项
     * @param sourceFormat 源文件格式
     */
    private void updateTargetFormats(String sourceFormat) {
        targetFormatCombo.removeAllItems();
        for (String format : SUPPORTED_FORMATS) {
            if (!format.equals(sourceFormat)) {
                targetFormatCombo.addItem(format);
            }
        }
        if (targetFormatCombo.getItemCount() > 0) {
            targetFormatCombo.setSelectedIndex(0);
        }
    }
    
    private void handleFileSelection(File file) {
        selectedFile = file;
        fileNameLabel.setText(file.getName());
        String sourceFormat = getSourceFormat(file);
        updateTargetFormats(sourceFormat);
        convertButton.setEnabled(true);
    }
    
    private void startConversion() {
        convertButton.setEnabled(false);
        progressBar.setValue(0);
        progressBar.setString("Converting...");
        
        SwingWorker<File, Integer> worker = createConversionWorker();
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });
        worker.execute();
    }
    
    /**
     * 从 pom.xml 中读取版本号
     * @return 版本号，如果读取失败则返回 "unknown"
     */
    private static String readVersion() {
        try {
            // 获取 pom.properties 文件路径
            String pomPath = MainWindow.class.getPackage().getName().replace('.', '/');
            String resourcePath = "/META-INF/maven/com.github.chimmhuang/chimm-caption/pom.properties";
            
            // 读取版本号
            java.util.Properties properties = new java.util.Properties();
            try (java.io.InputStream inputStream = MainWindow.class.getResourceAsStream(resourcePath)) {
                if (inputStream != null) {
                    properties.load(inputStream);
                    return properties.getProperty("version", "unknown");
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to read version from pom.properties", e);
        }
        return "unknown";
    }
    
    /**
     * 创建文件选择器
     */
    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        // 添加文件过滤器
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String name = f.getName().toLowerCase();
                for (String format : SUPPORTED_FORMATS) {
                    if (name.endsWith("." + format)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return bundle.getString("file.filter.description");
            }
        });
        
        // 设置文件选择器的按钮文本
        UIManager.put("FileChooser.openButtonText", bundle.getString("file.chooser.open"));
        UIManager.put("FileChooser.cancelButtonText", bundle.getString("file.chooser.cancel"));
        UIManager.put("FileChooser.lookInLabelText", bundle.getString("file.chooser.look.in"));
        UIManager.put("FileChooser.fileNameLabelText", bundle.getString("file.chooser.file.name"));
        UIManager.put("FileChooser.filesOfTypeLabelText", bundle.getString("file.chooser.files.of.type"));
        
        return fileChooser;
    }
} 