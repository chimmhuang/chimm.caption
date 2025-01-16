package com.github.chimmhuang.caption.ui;

import com.github.chimmhuang.caption.factory.CaptionFactory;
import com.github.chimmhuang.caption.handler.CaptionHandler;
import com.github.chimmhuang.caption.model.CommonCaption;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.SwingWorker;
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

/**
 * 字幕转换工具主窗口
 */
public class MainWindow extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 200;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 30;
    private static final String[] SUPPORTED_FORMATS = {"srt", "sbv", "bcc"};
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        mainPanel.add(createProgressPanel(), BorderLayout.NORTH);
        mainPanel.add(createButtonPanel(), BorderLayout.CENTER);
        mainPanel.add(createLanguagePanel(), BorderLayout.SOUTH);
        
        return mainPanel;
    }
    
    private JPanel createLanguagePanel() {
        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        languageCombo = new JComboBox<>(SUPPORTED_LANGUAGES);
        languageCombo.setSelectedItem(Locale.getDefault().toString());
        languageCombo.addActionListener(e -> switchLanguage());
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
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("Ready");
        fileNameLabel = new JLabel(" ");
        progressPanel.add(fileNameLabel, BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);
        return progressPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        selectButton = createButton("app.upload", this::selectFile);
        JPanel formatPanel = createFormatPanel();
        convertButton = createButton("app.convert", this::convertFile);
        
        buttonPanel.add(selectButton);
        buttonPanel.add(formatPanel);
        buttonPanel.add(convertButton);
        
        return buttonPanel;
    }
    
    private JButton createButton(String key, Runnable action) {
        JButton button = new JButton(bundle.getString(key));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.addActionListener(e -> action.run());
        return button;
    }
    
    private JPanel createFormatPanel() {
        targetFormatCombo = new JComboBox<>(SUPPORTED_FORMATS);
        targetFormatCombo.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        
        formatLabel = new JLabel(bundle.getString("app.format.target"));
        JPanel formatPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formatPanel.add(formatLabel);
        formatPanel.add(targetFormatCombo);
        
        return formatPanel;
    }
    
    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            fileNameLabel.setText("Selected file: " + selectedFile.getName());
            progressBar.setValue(0);
            progressBar.setString("Ready to convert");
            LOGGER.log(Level.INFO, "Selected file: {0}", selectedFile.getAbsolutePath());
        }
    }
    
    private void convertFile() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select a file first");
            return;
        }
        
        setComponentsEnabled(false);
        createConversionWorker().execute();
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
                
                publish(20);
                CaptionHandler sourceHandler = CaptionFactory.getHandler(sourceFormat);
                
                publish(40);
                List<CommonCaption> captions = sourceHandler.importCaption(selectedFile);
                
                publish(60);
                CaptionHandler targetHandler = CaptionFactory.getHandler(targetFormat);
                
                publish(80);
                return targetHandler.exportCaption(captions);
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
} 