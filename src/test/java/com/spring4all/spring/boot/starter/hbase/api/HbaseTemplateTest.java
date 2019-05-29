package com.spring4all.spring.boot.starter.hbase.api;

import com.spring4all.spring.boot.starter.hbase.TestBase;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = TestBase.class)
@RunWith(SpringRunner.class)
public class HbaseTemplateTest {

    @Resource
    private HbaseTemplate hbaseTemplate;

    @Test
    public void saveOrUpdate() {
        Put put = new Put(Bytes.toBytes("file0111111"));
        put.addColumn(Bytes.toBytes("INFO"), Bytes.toBytes("FILE"), Bytes.toBytes("valuexxxx"));
        hbaseTemplate.saveOrUpdate("WSG_UPLOAD_FILE_INFOxxx", put);
    }

    @Test
    public void find() {
        String fileValue = hbaseTemplate.get("WSG_UPLOAD_FILE_INFO", "file0111111", "INFO", "FILE", new FileRowMapper());
        System.out.println("fileValue:" + fileValue);
    }

}