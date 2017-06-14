package com.weizhi.libra.bigdata.hdfs;

import org.apache.hadoop.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Linchong
 * @version Id: FileTest.java, v 0.1 2017/5/17 15:50 Linchong Exp $$
 * @Description TODO
 */
public class FileTest {

    public static void main(String args[]){
        String filePath = "D:\\data\\agreement";

        String tagetName = filePath + "\\data.out.gz";
        FileOutputStream out = null;
        ZipOutputStream zout = null;
        try {
            out = new FileOutputStream(tagetName);
            zout = new ZipOutputStream(out);
            File files = new File(filePath);
            BufferedInputStream in = null;
            for(File file:files.listFiles()){
                if(file.getName().contains("data")){
                    continue;
                }
                String[] fileNames = file.getName().split("/");
                zout.putNextEntry(new ZipEntry(fileNames[fileNames.length - 1]));
                try {
                    in = new BufferedInputStream(new FileInputStream(file));
                    int c;
                    while ((c=in.read())!=-1){
                        zout.write(c);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            IOUtils.closeStream(in);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeStream(zout);
        }

    }
}
