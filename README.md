# ctniubi
Nail internal application development framework out of the box based on secondary encapsulation of nail API

## 项目介绍
基于钉钉api二次封装的开箱即用的开发框架

## 企业内部应用
- [钉钉官方服务端API文档](https://ding-doc.dingtalk.com/doc#/serverapi2/gh60vz)
- [钉钉官方前端API文档](https://ding-doc.dingtalk.com/doc#/dev/ed25rr)

## maven引入
需要手动引入两个钉钉相关jar,位置在dingding-java-oapi根目录下。
- lippi-oapi-encrpt-dingtalk-2019.07.30.jar
- taobao-sdk-java-20200605.jar
- 打包到本地maven仓库命令,可参考https://blog.csdn.net/caitao123456789/article/details/105222721
``` 导入第三方jar的命令：
     
    mvn install:install-file -Dfile=D:\dingding\2020.3.31\dingtalk-sdk-javataobao-sdk-java-auto_1479188381469-20200330.jar -DgroupId=com.taobao.top -DartifactId=taobao-sdk-java -Dversion=20200330 -Dpackaging=jar
```
## 钉钉微应用配置参数
-  在项目配置文件dd.properties中设置相关参数

## demo
-  springmvc实例项目是dingding-web-springmvc，后期会增加springboot的demo版本