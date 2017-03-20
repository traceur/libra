
package com.weizhi.libra.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.*;

/**
 * @author Linchong
 * @version Id: CSVFileUtil.java, v 0.1 2017/2/20 17:15 Linchong Exp $$
 * @Description TODO
 */
public class CSVFileUtil {
    private static final char   CSV_SPACE            = ' ';
    private static final char   CSV_TAB              = '\t';
    private static final char   CSV_DELIMITER        = ',';
    private static final char   CSV_QUOTE            = '"';
    private static final String CSV_QUOTE_STR        = String.valueOf(CSV_QUOTE);
    private static final String CSV_QUOTE_STR_DOUBLE = CSV_QUOTE_STR + CSV_QUOTE_STR;
    private static final char[] CSV_SEARCH_CHARS     = new char[] { CSV_DELIMITER, CSV_QUOTE,
            CSV_SPACE, CSV_TAB, CharUtils.CR, CharUtils.LF };
    /**
     * 把CSV文件的一行转换成字符串数组
     */
    public static List<String> fromCSVLineToList(String source) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(source)) {
            return new ArrayList();
        }
        int currentPosition = 0;
        int maxPosition = source.length();
        int nextComma = 0;
        List<String> rtnArray = new ArrayList<String>();
        while (currentPosition < maxPosition) {
            nextComma = nextComma(source, currentPosition);
            rtnArray.add(nextToken(source, currentPosition, nextComma));
            currentPosition = nextComma + 1;
            if (currentPosition == maxPosition) {
                rtnArray.add("");
            }
        }
        return rtnArray;
    }

    /**
     * 查询下一个逗号的位置。
     *
     * @param source 文字列
     * @param st  检索开始位置
     * @return 下一个逗号的位置。
     */
    private static int nextComma(String source, int st) {
        int maxPosition = source.length();
        boolean inquote = false;
        while (st < maxPosition) {
            char ch = source.charAt(st);
            if (!inquote && ch == ',') {
                break;
            } else if ('"' == ch) {
                inquote = !inquote;
            }
            st++;
        }
        return st;
    }

    /**
     * 取得下一个字符串
     */
    private static String nextToken(String source, int st, int nextComma) {
        StringBuffer strb = new StringBuffer();
        int next = st;
        while (next < nextComma) {
            char ch = source.charAt(next++);
            if (ch == '"') {
                if ((st + 1 < next && next < nextComma) && (source.charAt(next) == '"')) {
                    strb.append(ch);
                    next++;
                }
            } else {
                strb.append(ch);
            }
        }
        return strb.toString();
    }

    /**
     * @param input
     * @return
     */
    public final static String escapeCsv(String input) {
        if (input == null) {
            return "\"\"";
        }
        if (org.apache.commons.lang3.StringUtils.containsNone(input.toString(), CSV_SEARCH_CHARS)) {
            return input;
        } else {
            return CSV_QUOTE
                    + org.apache.commons.lang3.StringUtils.replace(input.toString(), CSV_QUOTE_STR, CSV_QUOTE_STR_DOUBLE)
                    + CSV_QUOTE;
        }
    }

    public static void main(String args[]){
        String csvLine = "0,\"412,000\",15,2,8 \"\"9,1,0.5333333333333333";
        System.out.println(csvLine);
        List<String> strList = fromCSVLineToList(csvLine);
        strList.add("a,b");
        StringBuffer lineContent = new StringBuffer();
        for (int j = 0, msgContentSize = strList.size(); j < msgContentSize; j++) {
            Object writeContent = strList.get(j);
            if (writeContent != null) {
                lineContent.append(CSVFileUtil.escapeCsv(writeContent.toString()));
            }else{
                lineContent.append("\"\"");
            }
            if (j != msgContentSize - 1)
                lineContent.append(",");
        }
        System.out.println(lineContent);


    }
}
