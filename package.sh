#!/bin/bash

# 编译项目
mvn clean package

# 创建各平台安装包
jpackage --input target/ \
  --main-jar chimm-caption-1.0-SNAPSHOT-jar-with-dependencies.jar \
  --main-class com.github.chimmhuang.caption.ChimmCaption \
  --name ChimmCaption \
  --app-version 1.0.0 \
  --vendor "Chimm Huang" \
  --copyright "Copyright © 2024" \
  --description "A subtitle converter supporting multiple formats" \
  --icon src/main/resources/icon.png

# Windows 特定配置
if [[ "$OSTYPE" == "msys" || "$OSTYPE" == "win32" ]]; then
  jpackage --type msi
fi

# macOS 特定配置
if [[ "$OSTYPE" == "darwin"* ]]; then
  jpackage --type dmg
fi

# Linux 特定配置
if [[ "$OSTYPE" == "linux-gnu"* ]]; then
  jpackage --type deb
  jpackage --type rpm
fi 