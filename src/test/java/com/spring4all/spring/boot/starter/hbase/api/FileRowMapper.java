package com.spring4all.spring.boot.starter.hbase.api;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author whitr
 * @create 2019-05-22 18:53
 * @since 2019.05.22
 */
public class FileRowMapper implements RowMapper<String> {
    @Override
    public String mapRow(Result result, int rowNum) throws Exception {
        return new String(result.getValue(Bytes.toBytes("INFO"), Bytes.toBytes("FILE")));
    }
}
