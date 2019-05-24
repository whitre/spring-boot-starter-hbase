package com.spring4all.spring.boot.starter.hbase.boot;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * JThink@JThink
 *
 * @author JThink
 * @version 0.0.1
 * desc： hbase auto configuration
 * date： 2016-11-16 11:11:27
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(HbaseProperties.class)
@ConditionalOnClass(HbaseTemplate.class)
public class HbaseAutoConfiguration {

    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";
    private static final String HBASE_PORT = "hbase.zookeeper.property.clientPort";
    private static final String HBASE_ROOTDIR = "hbase.rootdir";
    private static final String HBASE_ZNODE_PARENT = "zookeeper.znode.parent";


    @Autowired
    private HbaseProperties hbaseProperties;

    @Bean
    @ConditionalOnMissingBean(HbaseTemplate.class)
    public HbaseTemplate hbaseTemplate() {
        Configuration configuration = HBaseConfiguration.create();
        if (!CollectionUtils.isEmpty(hbaseProperties.getResources())) {
            for (String resource : hbaseProperties.getResources()) {
                configuration.addResource(resource);
            }
        } else {
            configuration.set(HBASE_QUORUM, hbaseProperties.getQuorum());
            configuration.set(HBASE_PORT, hbaseProperties.getPort());
            configuration.set(HBASE_ROOTDIR, hbaseProperties.getRootDir());
            if (StringUtils.hasText(hbaseProperties.getNodeParent())) {
                configuration.set(HBASE_ZNODE_PARENT, hbaseProperties.getNodeParent());
            }
        }
        return new HbaseTemplate(configuration);
    }
}
