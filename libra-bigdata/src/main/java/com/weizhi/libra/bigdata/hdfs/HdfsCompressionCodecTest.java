package com.weizhi.libra.bigdata.hdfs;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * @author Linchong
 * @version Id: HdfsTest.java, v 0.1 2017/4/25 15:48 Linchong Exp $$
 * @Description TODO
 */
public class HdfsCompressionCodecTest {
    private static LongWritable          key   = new LongWritable();
    private static BytesRefArrayWritable value = new BytesRefArrayWritable();


    public static void main(String args[]) throws Exception {

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

//        String codecClass = "org.apache.hadoop.io.compress.GzipCodec";

        Path hdfsPath = new Path("/user/zhuweiping/10002/data1.gz");
        CompressionOutputStream cout = null;
        BufferedWriter writer = null;
        try {
            FileSystem fs = FileSystem.get(hdfsPath.toUri(), conf);
            CompressionCodec codec =  ReflectionUtils.newInstance(
                    GzipCodec.class, conf);
            FSDataOutputStream out = fs.create(hdfsPath, true);
            cout = codec.createOutputStream(out);
            writer = new BufferedWriter(new OutputStreamWriter(cout));
            writer.write("This is a test message.");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(cout);
            IOUtils.closeStream(writer);
        }

    }

}
