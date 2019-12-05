package com.spring4all.spring.boot.starter.hbase.boot;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.spring4all.spring.boot.starter.hbase.constant.HbaseConstants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

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
            configuration.set(HbaseConstants.HBASE_QUORUM, hbaseProperties.getQuorum());
            configuration.set(HbaseConstants.HBASE_PORT, hbaseProperties.getPort());
            configuration.set(HbaseConstants.HBASE_ROOTDIR, hbaseProperties.getRootDir());
            if (StringUtils.hasText(hbaseProperties.getNodeParent())) {
                configuration.set(HbaseConstants.HBASE_ZNODE_PARENT, hbaseProperties.getNodeParent());
            }

            if (!CollectionUtils.isEmpty(hbaseProperties.getConfig())) {
                for (Map.Entry<String, String> entry : hbaseProperties.getConfig().entrySet()) {
                    configuration.set(entry.getKey(), entry.getValue());
                }
            }
        }

        if (StringUtils.hasText(hbaseProperties.getNamespace())) {
            configuration.set(HbaseConstants.HBASE_NAMESPACE, hbaseProperties.getNamespace());
        }
        return new HbaseTemplate(configuration);
    }
}
