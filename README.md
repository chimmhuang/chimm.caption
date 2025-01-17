# Chimm Caption

一个简单易用的字幕格式转换工具，支持多种字幕格式之间的相互转换。

## 功能特点

- 支持多种字幕格式：
  - BCC (Bilibili Closed Caption)
  - SRT (SubRip Subtitle)
  - SBV (SubViewer Subtitle)
- 简洁直观的图形界面
- 支持中文和英文界面
- 自动过滤相同格式的转换选项
- 保留字幕的样式信息（如果目标格式支持）
- 实时转换进度显示
- 输出文件自动保存在源文件目录

## 技术栈

- Java 21
- Swing GUI
- Maven
- Jackson (JSON 处理)
- Lombok

## 系统要求

- Windows/macOS/Linux
- Java 21 或更高版本

## 使用说明

1. 启动应用程序
2. 点击"上传"按钮选择源字幕文件
3. 在下拉菜单中选择目标格式
4. 点击"转换"按钮开始转换
5. 转换完成后，新文件将保存在与源文件相同的目录中

## 开发环境设置

1. 克隆项目：
```bash
git clone https://github.com/chimmhuang/chimm.caption.git
```

2. 进入项目目录：
```bash
cd chimm.caption
```

3. 使用 Maven 编译：
```bash
mvn clean package
```

4. 运行应用：
```bash
java -jar target/chimm-caption-1.0-jar-with-dependencies.jar
```

## 打包说明

项目使用 Maven Assembly 插件进行打包，可以生成包含所有依赖的可执行 JAR 文件。

Windows/macOS/Linux 系统可以使用 `package.sh` 脚本生成对应的安装包：
```bash
./package.sh
```

## 国际化支持

支持以下语言：
- 简体中文 (zh_CN)
- 英文 (en)

可以通过界面右下角的语言选择下拉框切换语言。

## 许可证

[MIT License](LICENSE)

## 作者

Chimm Huang 