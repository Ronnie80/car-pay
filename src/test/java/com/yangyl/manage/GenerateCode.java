package com.yangyl.manage;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class GenerateCode {

    private final String url = "jdbc:mysql://39.98.58.176:3306/car-db?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT%2B8";
    private final String username = "root";
    private final String password = "123456";
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String author = "yangyl";
    private final String outputDir = "E:/yyl-code";
    private final String packageName = "com.yangyl.manage";


    @Test
    public void getCode() {
        generateCode(
//                "vehicle_source",
//                "vehicle_info",
//                "user_menu",
//                "user_info",
//                "repayment_records",
//                "menu_info",
//                "image_info",
                "role_info"
        );
    }

    private void generateCode(String... table) {

        // 数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig() {{
            setDbType(DbType.MYSQL);
            setUrl(url);
            setUsername(username);
            setPassword(password);
            setDriverName(driverName);
        }};
        // 策略配置项
        StrategyConfig strategyConfig = new StrategyConfig() {{
            setRestControllerStyle(true);
            setCapitalMode(true);
            setEntityLombokModel(true);
            setControllerMappingHyphenStyle(true);
            setNaming(NamingStrategy.underline_to_camel);
            setInclude(table);

        }};
        // 全局配置
        GlobalConfig config = new GlobalConfig() {{
            setActiveRecord(false);
            setSwagger2(true);
            setAuthor(author);
            setIdType(IdType.AUTO);
            setEnableCache(false);
            setBaseResultMap(true);
            setXmlName("%sMapper");
            setMapperName("%sMapper");
            setOutputDir(outputDir);
            setServiceName("%sService");
        }};
        //包信息
        PackageConfig packageInfo = new PackageConfig() {{
            setParent(packageName);
            setEntity("entity");
            setMapper("mapper");
            setXml("mapper");
            setService("service");
            setServiceImpl("service.impl");
            setController("controller");
        }};
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageInfo)
                .execute();
    }

}
