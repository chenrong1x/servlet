package cn.kgc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//读取数据库属性文件，获取数据库连接信息
/*
* 单例模式
* 1.把构造方法私有化
* 2.程序提供给别人唯一的一个对象
* 3.
* */
public class ConfigManager {
    private Properties properties;
    private static ConfigManager configManager;

    private ConfigManager(){
        String configFile = "database.properties";
        InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
        properties = new Properties();
        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据属性文件中的键获得对应的值
    public String getSring(String key){
        return properties.getProperty(key);
    }

    public static synchronized ConfigManager getInstance(){
        if(configManager == null){
            configManager = new ConfigManager();
        }
        return configManager;
    }
}
