# Chimm Caption

一个简单易用的字幕格式转换工具，支持多种字幕格式之间的相互转换。

<div align="center">
<img src="./images/icon.jpeg" width="600px" height="150px" align="center"/>
<p>
  <img src='https://img.shields.io/badge/License-MIT-AA0010' alt='lisence'/>
  <img src="https://img.shields.io/badge/version-v1.0-orange" alt='version'/>
  <img src="https://img.shields.io/badge/JDK-21-9cf" alt='jdk'/>
  <img src="https://img.shields.io/badge/windows-0078D7?logo=data:image/x-icon;base64,AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAADoxWT/67Ao/+21Lv/ttC3/7bMu/+20Lf/tsy7/7bQt/+2zLv/ttC3/7bQt/+2zLv/ttS//7bEm/+u/VP/+//r//////3TW+P8kwvv/MMb7/y7F+/8uxfv/LsX7/y7F+/8uxfv/LsX7/y7F+/8uxfv/LsX7/y/F+/8qxPv/SMz6/+O3Pv/snAD/7qIB/+6hAP/uoQD/7qEA/+6hAP/uoQD/7qEA/+6hAP/uoQD/7qEA/+6iAv/unQD/6LAr//7/+P/+////U834/wCz//8DuP//ALf//wC3//8At///ALf//wC3//8At///ALf//wC3//8At///Abf//wC2//8ewPz/5LpD/+2gAP/vpgP/76UC/++lAv/vpQL/76UC/++lAv/vpQL/76UC/++lAv/vpQL/76YE/++iAP/pszD//v/4//////9Wzvj/ALX//wW6//8Cuf//Arn//wK5//8Cuf//Arn//wK5//8Cuf//Arn//wK5//8Duf//ALj//yLC/P/kuUL/7Z8A/++lAv/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpQP/76EA/+mzL//+//j//////1XO+P8Atf//BLr//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wK5//8AuP//IcL8/+S5Qv/tnwD/76UC/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++lA//voQD/6bMv//7/+P//////Vc74/wC1//8Euv//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Arn//wC4//8hwvz/5LlC/+2fAP/vpQL/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76UD/++hAP/psy///v/4//////9Vzvj/ALX//wS6//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Cuf//ALj//yHC/P/kuUL/7Z8A/++lAv/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpQP/76EA/+mzL//+//j//////1XO+P8Atf//BLr//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wK5//8AuP//IcL8/+S5Qv/tnwD/76UC/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++lA//voQD/6bMv//7/+P//////Vc74/wC1//8Euv//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Arn//wC4//8hwvz/5LlC/+2fAP/vpQL/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76UD/++hAP/psy///v/4//////9Vzvj/ALX//wS6//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Cuf//ALj//yHC/P/kuUL/7Z8A/++lAv/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpQP/76EA/+mzL//+//j//////1XO+P8Atf//BLr//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wK5//8AuP//IcL8/+S5Qv/tnwD/76UC/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++lA//voQD/6bMv//7/+P//////Vc74/wC1//8Euv//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Arn//wC4//8hwvz/5LlC/+2fAP/vpQL/76QB/++kAf/vpAH/76QB/++kAf/vpAH/76QB/++kAf/vpQD/76UD/++hAP/psy///v/4//////9Vzvj/ALX//wS6//8Buf//Abn//wG5//8Buf//Abn//wG5//8Buf//Abn//wG5//8Cuf//ALj//yHC/P/lukT/7KAA/++mBf/wpQT/76UE/++lBP/vpQT/76UE/++lBP/vpQT/76UE/++lA//vpgb/76IA/+qzMf/+//j//v///1fP+P8Atv//Brv//wO6//8Duv//A7r//wO6//8Duv//A7r//wO6//8Duv//A7r//wS6//8Auf//I8P8/+K2O//smwD/76EC/+6gAP/uoAD/7qAA/+6gAP/uoAD/7qAA/+6gAP/uoAD/76AA/++hAv/tnAD/6K8q//7/9//+////Ucz4/wCy//8Dtv//ALb//wC2//8Atv//ALb//wC2//8Atv//ALb//wC2//8Atv//Abb//wC1//8cv/z/5Mh4/+WzQ//nuEn/5rdI/+e3SP/nt0j/57dI/+e3R//nt0j/57dI/+e3SP/nt0j/57hJ/+a0Qf/lwmn//v/6//////+H2vb/Qsj3/0zM+P9Ky/j/Ssv4/0rL+P9Ky/j/Ssv4/0rL+P9Ky/j/Ssv4/0rL+P9Ly/j/R8r4/2LR+P////7//v/+//7//v/+//7//v/+//7//v/+//7//v/+//7//v/+//7//v/+//7//v/+//3//v/+/////v///////v////v////6//7/+f/+//n//v/5//7/+f/+//n//v/5//7/+f/+//n//v/5//7/+f/+//n//v/5//7/+v/+///////////////////////////////////////////////////////////////////////////////////////+////////////////////////////////////////////////////////////////////////////////////gpzo/1R26/9ae+7/WXrt/1l67f9Zeu3/WXrt/1l67f9Zeu3/WXrt/1l67f9Zeu3/Wnvt/1N27f93kuv/+/z+//////991rz/NsWZ/0HJn/9AyJ3/P8id/0DInv9AyJ7/P8id/z/Inf9AyJ3/QMie/0DInf9AyJ7/PMeb/1jOqv9Td+X/E0Lu/xpJ8f8ZSPH/GUjx/xlI8f8ZSPH/GUjx/xlI8f8ZSPH/GUjx/xlI8f8bSfH/EULw/0Jp6//6+/3//////07Jov8AsnL/A7h7/wC2ef8At3n/ALd5/wC3ef8At3n/ALd5/wC3ef8At3n/ALd5/wG3ev8AtXb/HL6K/1x95/8eS/L/JVPz/yRS8v8kUvL/JFLy/yRS8v8kUvL/JFLy/yRS8v8kUvL/JFLy/yZS8/8cTPL/S3Ds//r8/f//////VMyn/wC3ef8FvIL/A7uA/wK7gP8Cu4D/AruA/wK7gP8Cu4D/AruA/wK7gP8Cu4D/A7uB/wC5fv8jwpH/W3zn/x1J8v8jUfP/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPH/JFHy/xpK8v9Jb+z/+vz9//////9SzKf/ALZ5/wO6gf8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8BuoD/ALh9/yHBkP9afeb/HErw/yNR8v8iT/P/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8kUfH/Gkry/0lv7P/6/P3//////1LMp/8Atnj/A7uB/wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wG6gP8AuHz/IcGP/1p95v8cSvD/I1Hy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yRR8v8aSvL/SW/s//r8/f//////Usyn/wC2eP8Du4H/ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//AbqA/wC4fP8hwo//Wn3m/xxK8P8jUfL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/JFHy/xpK8v9Jb+z/+vz9//////9SzKf/ALZ4/wO7gf8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8BuoD/ALh8/yHCj/9afeb/HErw/yNR8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8kUfL/Gkry/0lv7P/6/P3//////1LMp/8Atnj/A7uB/wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wG6gP8AuHz/IcKP/1p95v8cSvD/I1Hy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yRR8v8aSvL/SW/s//r8/f//////Usyn/wC2eP8Du4H/ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//AbqA/wC4fP8hwo//Wn3m/xxK8P8jUfL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/JFHy/xpK8v9Jb+z/+vz9//////9SzKf/ALZ4/wO7gf8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8BuoD/ALh8/yHCj/9afef/HErw/yNR8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8kUfL/G0ny/0pv7P/6/P3//////1LMp/8Atnj/A7uB/wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wG6gP8AuHz/IcKP/1p95v8cSvD/I1Hz/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yJQ8v8iUPL/IlDy/yRR8/8aSfL/Sm/s//r8/f//////Usym/wC2eP8Du4H/ALp//wC6f/8Aun//ALp//wC6f/8Aun//ALp//wC6f/8Aun//AbqA/wC4fP8hwo//W33n/x1L8P8kUvP/I1Hy/yNR8v8jUfL/I1Hy/yNR8v8jUfL/I1Hy/yNR8v8jUfL/JVLz/xtL8v9KcO3/+vz9//////9TzKb/ALd5/wS8gv8Bu4D/AbuA/wG7gP8Bu4D/AbuA/wG7gP8Bu4D/AbuA/wG7gP8Cu4H/ALl9/yLDkP9XeeX/F0bu/x5N8P8dTPD/Hkvw/x1M8P8dTPD/HUvw/x1M8P8dTPD/HUzw/x5L8P8fTvD/FUbw/0Zs6//6+/3//////1HKo/8As3T/A7h9/wC3fP8At3z/ALd8/wC3fP8At3z/ALd8/wC3fP8At3z/ALd8/wG3ff8AtXn/H7+M/3uW5v9IbOf/TXLp/01w6P9Ocen/THHp/01x6f9Mcej/TXHo/0xx6f9Mcun/TXHo/05y6f9GbOj/bYro//r8/v//////ddW3/yfCkv80xpj/MsWX/zHGmP8yxZf/MsWY/zHGl/8xxpf/MsWY/zLFmP8yxpf/MsaY/y7Elf9MzaT/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=" alt='windows'/>
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