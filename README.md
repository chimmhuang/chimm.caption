# Chimm Caption

一个简单易用的字幕格式转换工具，支持多种字幕格式之间的相互转换。

<div align="center">
<img src="./images/icon.jpeg" width="600px" height="150px" align="center"/>
<p>
  <img src='https://img.shields.io/badge/License-MIT-AA0010' alt='license'/>
  <img src="https://img.shields.io/badge/version-v1.0-orange" alt='version'/>
  <img src="https://img.shields.io/badge/JDK-21-9cf" alt='jdk'/>
  <img src="https://img.shields.io/badge/windows-0078D7?logo=data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNDAwIiBoZWlnaHQ9IjQwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB2aWV3Qm94PSIwIDAgNDAwIDQwMCI+CiAgPCEtLSDpgI/mmI7og4zmma8gLS0+CiAgCiAgPCEtLSDnu5jliLblm5vkuKrmnInpl7TpmpnnmoTnmb3oibLmraPmlrnlvaLvvIznsbvkvLxXaW5kb3dz5Zu+5qCHIC0tPgogIDxnIHRyYW5zZm9ybT0idHJhbnNsYXRlKDUwLCA1MCkiPiA8IS0tIOWwhuaVtOS4que7hOeojeW+ruenu+WKqOS7peWxheS4rSAtLT4KICAgIDxyZWN0IHdpZHRoPSIxNTAiIGhlaWdodD0iMTUwIiBmaWxsPSJ3aGl0ZSIgLz4KICAgIDxyZWN0IHg9IjE3MCIgd2lkdGg9IjE1MCIgaGVpZ2h0PSIxNTAiIGZpbGw9IndoaXRlIiAvPiA8IS0tIHjlnZDmoIflop7liqDpl7TpmpkgLS0+CiAgICA8cmVjdCB5PSIxNzAiIHdpZHRoPSIxNTAiIGhlaWdodD0iMTUwIiBmaWxsPSJ3aGl0ZSIgLz4gPCEtLSB55Z2Q5qCH5aKe5Yqg6Ze06ZqZIC0tPgogICAgPHJlY3QgeD0iMTcwIiB5PSIxNzAiIHdpZHRoPSIxNTAiIGhlaWdodD0iMTUwIiBmaWxsPSJ3aGl0ZSIgLz4gPCEtLSB45ZKMeeWdkOagh+mDveWinuWKoOmXtOmamSAtLT4KICA8L2c+Cjwvc3ZnPgoK" alt='windows'/>
  <img src="https://img.shields.io/badge/macos-000000?logo=apple" alt='macos'/>
  <img src="https://img.shields.io/badge/linux-000000?logo=Linux" alt='linux'/>
</p>
</div>

<div align="center">
<a href="https://gitee.com/chimmhuang/chimm.caption"><img src="https://img.shields.io/badge/Gitee地址-C71D23?logo=gitee" alt='Gitee地址'/></a>   <a href="https://github.com/chimmhuang/chimm.caption"><img src="https://img.shields.io/badge/Github地址-181717?logo=github" alt='Github地址'/></a> 
</div>

---------

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
- jpackage (多平台打包)

## 系统要求

- Windows/macOS/Linux
- 无需安装 Java 运行环境（已内置）

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/github/chimmhuang/caption/
│   │       ├── ChimmCaption.java          # 主入口类
│   │       ├── ui/
│   │       │   └── MainWindow.java        # 主窗口界面
│   │       ├── model/                     # 字幕数据模型
│   │       │   ├── CommonCaption.java     # 通用字幕基类
│   │       │   ├── BccCaption.java        # BCC格式字幕
│   │       │   ├── SrtCaption.java        # SRT格式字幕
│   │       │   └── SbvCaption.java        # SBV格式字幕
│   │       ├── handler/                   # 字幕处理器
│   │       │   ├── CaptionHandler.java    # 处理器接口
│   │       │   └── impl/
│   │       │       ├── BccHandler.java    # BCC格式处理器
│   │       │       ├── SrtHandler.java    # SRT格式处理器
│   │       │       └── SbvHandler.java    # SBV格式处理器
│   │       ├── factory/
│   │       │   └── CaptionFactory.java    # 工厂类
│   │       └── util/
│   │           └── TimeUtil.java          # 时间工具类
│   └── resources/
│       ├── icon.ico                       # Windows图标
│       ├── icon.icns                      # macOS图标
│       ├── icon.png                       # Linux图标
│       └── i18n/                          # 国际化资源
│           ├── messages_en.properties     # 英文翻译
│           └── messages_zh_CN.properties  # 中文翻译
```

## 使用说明

1. 下载对应系统的安装包
2. 安装并运行程序
3. 点击"上传"按钮选择源字幕文件
4. 在下拉菜单中选择目标格式
5. 点击"转换"按钮开始转换
6. 转换完成后，新文件将保存在与源文件相同的目录中

## 开发环境设置

### 环境要求
- JDK 21
- Maven 3.6+
- Git

### 开发步骤

1. 克隆项目：
```bash
git clone https://github.com/chimmhuang/chimm.caption.git
```

2. 进入项目目录：
```bash
cd chimm.caption
```

3. 编译项目：
```bash
mvn clean compile
```

4. 运行测试：
```bash
mvn test
```

## 打包说明

### 多平台打包

项目使用 Maven profiles 实现多平台打包，会根据当前操作系统自动选择对应的打包配置：

```bash
# 清理并打包（自动选择当前平台）
mvn clean package
```

打包完成后，在 `target/dist` 目录下会生成对应平台的文件：
- **Windows**: `target/dist/win/Chimm.Caption.exe`
- **macOS**: `target/dist/mac/Chimm.Caption.app`
- **Linux**: `target/dist/linux/chimm-caption.deb`

### 手动指定平台打包

如果需要为特定平台打包，可以使用以下命令：

```bash
# Windows 平台打包
mvn clean package -P windows

# macOS 平台打包
mvn clean package -P mac

# Linux 平台打包
mvn clean package -P linux

# macOS DMG 安装包打包（需要先有 .app 文件）
mvn package -P mac-dmg
```

### 打包配置说明

- 使用 `jpackage-maven-plugin` 进行多平台打包
- 自动处理依赖打包和可执行权限设置
- 支持自定义图标和元数据信息
- 生成的安装包无需额外安装 Java 运行环境

## 国际化支持

支持以下语言：
- 简体中文 (zh_CN)
- 英文 (en)

可以通过界面右下角的语言选择下拉框切换语言。

翻译文件位置：`src/main/resources/i18n/`

## 代码规范

- 遵循 Java 命名规范
- 使用 Lombok 简化代码
- 支持 Sonar 代码质量检查
- 国际化资源统一管理

## 许可证

[MIT License](LICENSE)

## 作者

Chimm Huang

## 更新日志

### v1.0.0 (2025-06-29)
- 初始版本发布
- 支持 BCC、SRT、SBV 格式转换
- 多平台打包支持
- 中英文界面支持 