@echo off
REM 后续命令使用的是：UTF-8编码

chcp 65001

echo ****************************************
echo 日报管理系统启动
echo ****************************************

set dataUrl=E:\data\worklog.db
set FolderUrl=E:\data
set FileName=worklog.db

set curUrl=%~dp0 

rem NOW SELECT DATABASE……

if not exist %dataUrl% (
    echo info:        database not exist
    if not exist %FolderUrl% (
        echo info:        E:\data FolderUrl not exist
        md %FolderUrl%
        echo info:        create E:/data FolderUrl success
    ) else (
        echo E:\data FolderUrl exist
    )

    echo info:        start create database file……
    copy %FileName% %dataUrl%
    echo info:        databased file create success
) else (
    echo info:        database exist
)

java -jar demo-0.0.1-SNAPSHOT.jar




