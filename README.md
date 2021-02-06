基于 MyBatis Generator 的代码生成工具

```
# 参数选项
java -jar mbg-ext.jar -h
usage: mgb-ext
 -c,--config <arg>      xml configuration file, <mbg-config.xml>
 -d,--dir <arg>         current working directory, <.>
 -e,--example <arg>     generate XxxByExample code, <true>
 -h,--help              print help
 -o,--overwrite <arg>   overwrite existing file, <true>

# 代码生成使用示例
java -jar mbg-ext.jar --dir /Users/yulewei/spring-demo --config mbg-config.xml
```