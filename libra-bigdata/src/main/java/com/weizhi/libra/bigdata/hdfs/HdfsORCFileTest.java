package com.weizhi.libra.bigdata.hdfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.AcidInputFormat;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.OrcSplit;
import org.apache.hadoop.hive.ql.io.orc.Reader;
import org.apache.hadoop.hive.ql.io.orc.RecordReader;
import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;

/**
 * @author Linchong
 * @version Id: HdfsTest.java, v 0.1 2017/4/25 15:48 Linchong Exp $$
 * @Description TODO
 */
public class HdfsORCFileTest {
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

        Path hdfsPath = new Path("/user/hive/warehouse/test_cxb.db/orcdata/");

        System.out.println(hdfsPath.toString());
        System.out.println(hdfsPath.getName());

        FileSystem fs = FileSystem.get(hdfsPath.toUri(), conf);

        System.out.println(fs.getUri());

        FileStatus fileList[] = fs.listStatus(hdfsPath);
        List<Path> hdfsPathList = new ArrayList<Path>();
        for (FileStatus fils : fileList) {
            //            System.out.println(fils.getPath().toUri().getPath());
            hdfsPathList.add(fils.getPath());
        }
        //        FileStatus fileList[] = fileSystem.listStatus(hdfsPath);
        //        int fileLen = fs.getChildFileSystems().length;
        //        System.out.println(fileLen);

        //        FileSplit split = new FileSplit(hdfsPath, 0, fileLen, new JobConf(conf));
        for (Path path : hdfsPathList) {
            Long fileLen = fs.getFileStatus(path).getLen();

            System.out.println(fileLen);
            //
            //            FileSplit split = new FileSplit(path, 0, fileLen, new JobConf(conf));
            //            path = getSplitPath(split, new JobConf(conf));
            Reader orcFileReader = OrcFile.createReader(path, OrcFile.readerOptions(conf));
            //            ObjectInspector inspector = orcFileReader.getObjectInspector();
            StructObjectInspector soi = (StructObjectInspector) orcFileReader.getObjectInspector();

            RecordReader recordReader = orcFileReader.rows();
            Object record = null;
            while (recordReader.hasNext()) {
                record = recordReader.next(record);
                //                System.out.println(record.toString());

                List<?> recs = soi.getStructFieldsDataAsList(record);
                for (Object obj : recs) {
                    System.out.print(obj.toString());
                }
                System.out.println();
            }
        }

        //        if (isAtomic(orcFileReader)) {
        //            LOG.warn("Split is atomic yet schema typeInfo was not provided via {}."
        //                    + " This is not recommended and will fail if you only have deltas!", SCHEMA_TYPE_INFO);
        //            typeInfo = extractRowStruct(typeInfo);
        //        }

        //        RCFileRecordReader<?, ?> rcFileRecordReader = new RCFileRecordReader<LongWritable, BytesRefArrayWritable>(conf, split);
        //
        //        while (rcFileRecordReader.next(key, value)) {
        //
        //            Text text = new Text();
        //
        //            for (int i = 0; i < value.size(); i++) {
        //                BytesRefWritable data = value.get(i);
        //                text.set(data.getData(), data.getStart(), data.getLength());
        //                System.out.println(text.toString());
        //            }
        //        }

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
