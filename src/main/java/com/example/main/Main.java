package com.example.main;

import com.example.config.SftpConfig;
import com.example.util.SFTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // 获取 args 参数  0: 列出某个目录下的文件  1: 下载某个目录下的文件  2: 上传某个文件到服务器
//        String runType = args[0];

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

            // 列出目录下的文件
            List<String> list = ftp.listFiles("/home/bigdata/bin/", sftpConfig);

            // 下载文件 测试通过
//            ftp.download(remoteDir, "xcall.sh", "D:\\temp\\", sftpConfig);

            // 上传文件 测试通过
//            ftp.upload(remoteDir, "D:\\temp\\1.png", sftpConfig);

            logger.info("{} 目录下的文件列表: \n{}", remoteDir, new Object[]{list});
        } catch (Exception e) {
            logger.error("文件上传下载异常:[{}]", e.getMessage());
        }
    }
}
