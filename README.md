基于 [MyBatis Generator](https://mybatis.org/generator/) (MBG) 的代码生成工具

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

注意：

  - 如果不传 `–config` 参数，mbg-config.xml 文件需要在 `--dir` 参数指定的目录下；
  - 示例的 [mbg-config.xml](https://github.com/yulewei/mbg-ext/blob/master/mbg-config.xml) 配置文件内的配置项 `targetProject` 以 `--dir` 参数指定的目录为根目录

**内置提供的自定义插件和扩展：**

  - `BaseMapperPlugin`：生成的 Mapper 类将继承参数 `baseMapperType` 指定的基类（如 [Base](https://github.com/yulewei/mbg-ext/blob/master/src/main/java/org/mybatis/ext/base/Base.java)），生成的 model
 类将继承参数 `baseEntityType` 指定的基类（如 [BaseMapper](https://github.com/yulewei/mbg-ext/blob/master/src/main/java/org/mybatis/ext/base/BaseMapper.java)）
  - `LombokPlugin`：生成的 model 类使用 [Lombok](https://projectlombok.org/) 注解
  - `MySqlLimitPlugin`：在 Example 类和 Mapper.xml 文件中生成 MySQL 的 limit, offset 分页参数
  - `RemarksCommentGenerator`：提取数据库表字段的注释到 model 类的字段注释中
