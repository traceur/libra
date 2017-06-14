package com.weizhi.libra.bigdata.hdfs;

import java.io.*;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.AcidInputFormat;
import org.apache.hadoop.hive.ql.io.orc.OrcSplit;
import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * @author Linchong
 * @version Id: HdfsTest.java, v 0.1 2017/4/25 15:48 Linchong Exp $$
 * @Description TODO
 */
public class HdfsZipTest2 {
    private static LongWritable          key   = new LongWritable();
    private static BytesRefArrayWritable value = new BytesRefArrayWritable();


    public static void main(String args[]) throws IOException {

        Configuration conf = new Configuration(false);
        //        conf.addResource(new Path("D:\\hadoop-2.6.5\\etc\\hadoop\\hdfs-site.xml"));
        //        conf.addResource(new Path("D:\\hadoop-2.6.5\\etc\\hadoop\\core-site.xml"));
        conf.set("fs.defaultFS", "hdfs://root");
        conf.set("dfs.nameservices", "root");
        conf.set("dfs.ha.namenodes.root", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.root.nn1", "172.16.10.225:9000");
        conf.set("dfs.namenode.rpc-address.root.nn2", "172.16.10.222:9000");
        conf.set("dfs.client.failover.proxy.provider.root",
                "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");

        conf.set("dfs.namenode.servicerpc-address.root.nn1", "172.16.10.225:53310");
        conf.set("dfs.namenode.servicerpc-address.root.nn2", "172.16.10.222:53310");

        Path hdfsPath = new Path("/user/zhuweiping/10002/data.gz");
        String filePath = "D:\\data\\agreement";

        String tagetName = filePath + "\\data.gz";
        FileSystem fs = FileSystem.get(hdfsPath.toUri(), conf);

//        fs.exists(hdfsPath);
//        fs.create(hdfsPath);

        zipFiles(filePath, tagetName);


        //指定压缩文件路径
        FSDataOutputStream out = fs.create(hdfsPath);

        InputStream in = new FileInputStream(new File(tagetName));

        IOUtils.copyBytes(in, out, conf);

        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }

    private static void zipFiles(String filePath, String tagetName) {
        FileOutputStream out = null;
        ZipOutputStream zout = null;
        try {
            out = new FileOutputStream(tagetName);
            zout = new ZipOutputStream(out);
            File files = new File(filePath);
            BufferedInputStream in = null;
            for (File file : files.listFiles()) {
                if (file.getName().contains("data")) {
                    continue;
                }
                String[] fileNames = file.getName().split("/");
                zout.putNextEntry(new ZipEntry(fileNames[fileNames.length - 1]));
                try {
                    in = new BufferedInputStream(new FileInputStream(file));

                    int c;
                    while ((c = in.read()) != -1) {
                        zout.write(c);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            IOUtils.closeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(zout);
        }
    }


    /*
     * This is to work around an issue reading from ORC transactional data sets
     * that contain only deltas. These contain synthesised column names that are
     * not usable to us.
     */
    private static Path getSplitPath(FileSplit inputSplit, JobConf conf) throws IOException {
        Path path = inputSplit.getPath();
        if (inputSplit instanceof OrcSplit) {
            OrcSplit orcSplit = (OrcSplit) inputSplit;
            List<AcidInputFormat.DeltaMetaData> deltas = orcSplit.getDeltas();
            if (!orcSplit.hasBase() && deltas.size() >= 2) {
                throw new IOException("Cannot read valid StructTypeInfo from delta only file: "
                        + path);
            }
        }
        //        log.debug("Input split path: "+ path);
        return path;
    }
}
