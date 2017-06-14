package com.weizhi.libra.bigdata.hbase;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import com.google.common.collect.Maps;

/**
 * @author Linchong
 * @version Id: HbaseClient.java, v 0.1 2017/4/18 17:20 Linchong Exp $$
 * @Description TODO
 */
public class HbaseClient {
    private static final String CF = "info";


    public static void main(String args[]) throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "172.17.32.79,172.17.32.80,172.17.32.81");
        conf.set("dfs.socket.timeout", "600000");
        conf.set("base.client.pause", "100");
        conf.set("hbase.client.write.buffer", "10485760");
        conf.set("hbase.client.retries.number", "5");
        conf.set("zookeeper.znode.parent", "/hbase3");

        Admin hBaseAdmin = HBaseManager.getInstance(conf).adminOperation();

        TableName tableName = TableName.valueOf("test_cxb");

        for (TableName tab : hBaseAdmin.listTableNames()) {
            System.out.println(tab.getNameAsString());
        }
                if (hBaseAdmin.tableExists(tableName)) {
                    System.out.println(hBaseAdmin.tableExists(tableName));
                    hBaseAdmin.disableTable(tableName);
                    hBaseAdmin.deleteTable(tableName);
                }
//
//                HTableDescriptor desc = new HTableDescriptor(tableName);
//                desc.addFamily(new HColumnDescriptor(CF));
//                hBaseAdmin.createTable(desc);
////
//                String appid= RowKeyUtil.generatRan3();
//                Put put = new Put(Bytes.toBytes(RowKeyUtil.generatRan6() + ";" + appid));
//                put.addColumn(Bytes.toBytes(CF), Bytes.toBytes("a"), Bytes.toBytes("nihao2"));
//                put.addColumn(Bytes.toBytes(CF), Bytes.toBytes("b"), Bytes.toBytes("linchong2"));
//
//                HBaseManager.getInstance(conf).openOperation(tableName).put(Arrays.asList(put));

        Scan scan = new Scan();
        ResultScanner results = HBaseManager.getInstance(conf).openOperation(tableName)
                .getScaner(scan);
        Result result =null;
        while((result=results.next()) !=null){
            System.out.println(new String(result.getRow()));
            System.out.println(new String(result.getValue(Bytes.toBytes(CF),Bytes.toBytes("a"))));
            System.out.println(new String(result.getValue(Bytes.toBytes(CF),Bytes.toBytes("b"))));
//            Map<String, Map<Long, byte[]>> record = Maps.newLinkedHashMap();
//
//            NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> allMap = result
//                    .getMap();
//
//            if (allMap != null && allMap.size() >= 1) {
//                NavigableMap<byte[], NavigableMap<Long, byte[]>> value = allMap.firstEntry()
//                        .getValue();
//                for (Map.Entry<byte[], NavigableMap<Long, byte[]>> entry : value.entrySet()) {
//                    String qualifier = Bytes.toString(entry.getKey());
//                    record.put(qualifier, entry.getValue());
//                }
//                System.out.println(JSON.toJSONString(record));
//            }
            //            for(Cell cell:result.listCells()) {
            //                System.out.print( Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            //                System.out.print("-" + cell.getTimestamp());
            //
            //                System.out.print("-" + Bytes.toString(cell.getRowArray(),cell.getRowOffset(),cell.getRowLength()));
            //                System.out.println("-" + Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength()));
            //            }
        }
        //        Get get = new Get(Bytes.toBytes("221-8608748269841"));
        //        get.addFamily(Bytes.toBytes("info"));

        //        Result result = HBaseManager.getInstance(conf).openOperation(tableName).getRow(get);

        //        for(Cell cell:result.listCells()) {
        //            System.out.println("-" + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
        //        }

    }

}
