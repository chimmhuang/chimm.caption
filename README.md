# Chimm Caption

一个简单易用的字幕格式转换工具，支持多种字幕格式之间的相互转换。

<div align="center">
<img src="./images/icon.jpeg" width="600px" height="150px" align="center"/>
<p>
  <img src='https://img.shields.io/badge/License-MIT-AA0010' alt='lisence'/>
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

## 系统要求

- Windows/macOS/Linux
- 无需安装 Java 运行环境（已内置）

## 使用说明

1. 下载对应系统的安装包
2. 安装并运行程序
3. 点击"上传"按钮选择源字幕文件
4. 在下拉菜单中选择目标格式
5. 点击"转换"按钮开始转换
6. 转换完成后，新文件将保存在与源文件相同的目录中

## 开发环境设置

1. 克隆项目：
```bash
git clone https://github.com/chimmhuang/chimm.caption.git
```

2. 进入项目目录：
```bash
cd chimm.caption
```

3. 使用 Maven 编译并打包：
```bash
mvn clean package
```

打包完成后，在 `target/dist` 目录下会生成三个子目录：
- `win`: Windows 可执行文件
- `mac`: macOS 应用程序
- `linux`: Linux 可执行文件

## 国际化支持

支持以下语言：
- 简体中文 (zh_CN)
- 英文 (en)

可以通过界面右下角的语言选择下拉框切换语言。

## 许可证

[MIT License](LICENSE)

## 作者

Chimm Huang 