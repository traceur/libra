
package com.weizhi.libra.bigdata.hbase;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import com.google.common.collect.Lists;

/**
 * @author linchong
 * @version Id: HBaseManager.java, v 0.1 2017/3/13 15:10 linchong Exp $$
 * @Description TODO
 */
public class HBaseManager {

    private Connection connection;
    private BufferedMutator mutator;
    private Table htable;
    private Configuration conf;
    private Admin hBaseAdmin;

    private static HBaseManager instance;


    /**
     * Instance init
     * 
     * @param conf
     * @return
     */
    public static HBaseManager getInstance(Configuration conf) {
        if(instance == null) {
            synchronized (HBaseManager.class) {
                if(instance == null){
                    instance = new HBaseManager(conf);
                }
            }
        }
        return instance;
    }


    /**
     * constructor
     * 
     * @param conf
     */
    public HBaseManager(Configuration conf) {
        this.conf = conf;
    }


    /**
     * 库表管理
     * 
     * @return
     * @throws IOException
     */
    public Admin adminOperation() throws IOException {
        if (hBaseAdmin == null) {
            hBaseAdmin = getConnection(this.conf).getAdmin();
        }
        return hBaseAdmin;
    }


    /**
     * 数据操作
     * 
     * @return
     * @throws IOException
     */
    public HBaseManager openOperation(TableName tableName) throws IOException {
        if (this.mutator == null) {
            this.mutator = getConnection(this.conf).getBufferedMutator(tableName);
        }
        if (this.htable == null) {
            this.htable = getConnection(this.conf).getTable(tableName);
        }
        return this;
    }


    /**
     * 批量put
     * 
     * @param puts
     * @return
     * @throws Exception
     */
    public int put(List<Put> puts) throws Exception {
        if (CollectionUtils.isEmpty(puts)) {
            return 0;
        }
        if (mutator != null) {
            for (Put p : puts) {
                mutator.mutate(p);
            }
            mutator.flush();
        } else {
            htable.put(puts);
        }
        return puts.size();
    }


    /**
     * 遍历
     * 
     * @param scan
     * @return
     * @throws IOException
     */
    public ResultScanner getScaner(Scan scan) throws IOException {
        return htable.getScanner(scan);
    }


    public Result getRow(Get get) throws IOException {
        return htable.get(get);
    }


    /**
     * 批量put
     * 
     * @param put
     * @return
     * @throws Exception
     */
    public boolean put(Put... put) throws Exception {
        if (put == null || put.length == 0) {
            return false;
        }
        int len = put.length;
        if (len == 1) {
            htable.put(put[0]);
        }
        if (mutator != null) {
            for (Put p : put) {
                mutator.mutate(p);
            }
            mutator.flush();
            return true;
        }
        List<Put> puts = Lists.newArrayList(put);
        htable.put(puts);
        return true;
    }


    /**
     * Conn
     * 
     * @param config
     * @return
     */
    private Connection getConnection(Configuration config) {
        if (connection == null) {
            initHConnection(config);
        }
        return connection;
    }


    /**
     * init conn
     * 
     * @param config
     */
    synchronized void initHConnection(Configuration config) {
        if (connection == null) {
            try {
                connection = ConnectionFactory.createConnection(config);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void closeTable(Table table) throws IOException {
        table.close();
    }


    public void close() {
        if (connection != null) {
            try {
                this.connection.close();
            } catch (IOException e) {
                //log here.
                e.printStackTrace();
            }
        }
    }

}
