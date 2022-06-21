package com.example.main;

import com.example.config.SftpConfig;
import com.example.util.SFTPUtil;
import com.jcraft.jsch.SftpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SFTPUtil ftp = new SFTPUtil(3, 6000);
        try {
            // 加载配置文件信息
            InputStream is = Main.class.getClassLoader().getResourceAsStream("config.properties");
            // 构造 Properties
            Properties properties = new Properties();
            try {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 获取信息
            String ip = properties.getProperty("sftp.ip");
            String username = properties.getProperty("sftp.username");
            String password = properties.getProperty("sftp.password");
            String port = properties.getProperty("sftp.port");
            String timeout = properties.getProperty("sftp.timeout");

            // 服务端目录
            String remoteDir = "/home/bigdata/bin/";

            SftpConfig sftpConfig = new SftpConfig(ip, Integer.parseInt(port), username, password, Integer.parseInt(timeout), remoteDir);

            List<String> list = ftp.listFiles("/home/bigdata/bin/", sftpConfig);
            logger.info("{} 目录下的文件列表: {}", remoteDir, new Object[]{list});
            list.forEach(System.out::println);
        } catch (SftpException e) {
            logger.error("文件上传下载异常:[{}]", e.getMessage());
        }
    }
}
