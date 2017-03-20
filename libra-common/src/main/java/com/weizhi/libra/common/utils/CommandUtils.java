package com.weizhi.libra.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class CommandUtils {
    public static final String PTY_VT100 = "vt100";


    /**
     * @Description:执行linux命令，返回结果
     * @author yunyan
     * @param host 主机
     * @param port 端口号
     * @param user 用户名
     * @param password 密码
     * @param cmd 命令
     * @param @throws IOException 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String execCommand(String host, int port, String user, String password, String cmd)
            throws IOException {
        StringBuffer sb = new StringBuffer();
        Connection conn = null;
        Session session = null;
        conn = new Connection(host, port);
        conn.connect();
        boolean isAuthed = conn.authenticateWithPassword(user, password);
        if (!isAuthed) {
            throw new IOException();
        }
        session = conn.openSession();
        session.requestPTY(PTY_VT100, 80, 24, 640, 480, null);
        session.execCommand(cmd);
        InputStream is = new StreamGobbler(session.getStdout());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        session.close();
        conn.close();
        return sb.toString();

    }

}
