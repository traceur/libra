
package com.weizhi.libra.common.utils;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Linchong
 * @version Id: FileUtil.java, v 0.1 2016/4/18 9:18 Linchong Exp $$
 * @Description TODO
 */
public class FileUtil {
    private static final Logger LOG          = LoggerFactory.getLogger(FileUtil.class);
    private static final String CHARSET_UTF8 = "UTF-8";


    private FileUtil() {
    }


    /**
     * 写文件
     * 
     * @param queryText
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean createNewFile(String queryText, String filePath, String fileName) {

        File file = null;
        FileOutputStream fos = null;
        try {
            file = new File(fileName);
            if (!checkAndmkDirs(file)) {
                return false;
            }

            if (!file.exists()) {
                file.createNewFile();
            }
            queryText = queryText.replace("\r\n", "\n");
            fos = new FileOutputStream(file);
            fos.write(queryText.getBytes(CHARSET_UTF8));
            fos.flush();
        } catch (Exception e) {
            LOG.error("Error When CreateNewFile:{},{}", fileName, e);
            return false;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception ex) {
                LOG.error("Error When Close File OutStream:{},{}", fileName, ex);
            }
        }
        return true;
    }


    /**
     * 创建目录
     * 
     * @param filePath
     * @return
     */
    public static boolean createDir(String filePath) {
        try {
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
        } catch (Exception e) {
            LOG.error("Error When Create Dir:{},{}", filePath, e);
            return false;
        }
        return true;
    }


    /**
     * 创建文件所在文件夹
     * 
     * @param file
     * @return
     */
    public static boolean checkAndmkDirs(File file) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        } catch (Exception e) {
            LOG.error("Error When Creating Dirs:{},", file.getAbsolutePath(), e);
            return false;
        }
        return true;
    }


    /**
     * 读取文件信息
     * 
     * @param fileName
     * @return
     */
    public static String readFromFile(String fileName) {
        File file = new File(fileName);
        FileInputStream in = null;
        BufferedReader br = null;
        StringBuffer fileText = new StringBuffer();
        try {
            in = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(in, CHARSET_UTF8));
            String line = null;
            while ((line = br.readLine()) != null) {
                fileText.append(line).append("\n");
            }
        } catch (Exception e) {
            LOG.error("Read file error:{}", fileName, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
                LOG.error("Close read file error:{}", fileName, ex);
            }
        }
        return fileText.toString();
    }
}
