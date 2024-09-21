package org.mybatis.ext;

import org.apache.commons.cli.*;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用 Java 运行 MyBatis Generator
 * http://www.mybatis.org/generator/running/runningWithJava.html
 *
 * @author yulewei on 2017/9/24
 */
public class MyBatisGen {
    private static final LibC libC = LibC.load();

    /**
     * 生成器的配置文件自行指定，从命令行参数传入
     */
    public static void main(String[] args) {
        Options options = new Options();

        Option helpOption = Option.builder("h")
                .longOpt("help")
                .desc("print help")
                .required(false)
                .hasArg(false)
                .build();
        options.addOption(helpOption);

        Option dirOption = Option.builder("d")
                .longOpt("dir")
                .hasArg()
                .desc("current working directory, <.>")
                .required(false)
                .build();
        options.addOption(dirOption);

        Option configOption = Option.builder("c")
                .longOpt("configfile")
                .hasArg()
                .desc("xml configuration file, <mbg-config.xml>")
                .required(false)
                .build();
        options.addOption(configOption);

        Option overwriteOption = Option.builder("o")
                .longOpt("overwrite")
                .hasArg()
                .required(false)
                .desc("overwrite existing file, <true>")
                .build();
        options.addOption(overwriteOption);

        Option exampleEnabledOption = Option.builder("e")
                .longOpt("example")
                .hasArg()
                .required(false)
                .desc("generate XxxByExample code, <true>")
                .build();
        options.addOption(exampleEnabledOption);

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(helpOption.getOpt())) {
                MyBatisGen.printHelp(options);
                return;
            }

            String dir = cmd.getOptionValue("dir", ".");
            String configFile = cmd.getOptionValue("configfile", "mbg-config.xml");
            boolean overwrite = Boolean.parseBoolean(cmd.getOptionValue("overwrite", "true"));
            boolean exampleEnabled = Boolean.parseBoolean(cmd.getOptionValue("example", "true"));
            if (!dir.equals(".")) {
                libC.chdir(dir);
                System.out.println("current working dir: " + libC.getcwd());
            }
            // 运行 MyBatis Generator
            MyBatisGen.runGenerator(configFile, overwrite, exampleEnabled);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            MyBatisGen.printHelp(options);
        }
    }

    public static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("mgb-ext", options);
    }

    public static void runGenerator(String configFileName, boolean overwrite, boolean exampleEnabled) throws Exception {
        File configFile = new File(configFileName);
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);

        // 重写配置文件的配置信息
        for (Context context : config.getContexts()) {
            for (TableConfiguration tableConfig : context.getTableConfigurations()) {
                // 不生成 xxxByExample 代码
                if (!exampleEnabled) {
                    tableConfig.setSelectByExampleStatementEnabled(false);
                    tableConfig.setCountByExampleStatementEnabled(false);
                    tableConfig.setUpdateByExampleStatementEnabled(false);
                    tableConfig.setDeleteByExampleStatementEnabled(false);
                }
            }
        }

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        for (String waring : warnings) {
            System.out.println(waring);
        }
    }
}
