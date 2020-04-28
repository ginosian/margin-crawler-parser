package com.margin.config;

import io.netty.util.ThreadDeathWatcher;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.network.NetworkModule;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.reindex.ReindexPlugin;
import org.elasticsearch.percolator.PercolatorPlugin;
import org.elasticsearch.script.mustache.MustachePlugin;
import org.elasticsearch.transport.Netty4Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.margin.repository")
@ComponentScan(basePackages = { "com.margin" })
@PropertySource("classpath:database.properties")
public class ElasticsearchConfig {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfig.class);

    @Value("${elasticsearch.clusterName:elasticsearch_martaginosyan}")
    private String clusterName;

    @Value("${elasticsearch.clusterNode.address:127.0.0.1}")
    private String nodeAddress;

    @Value("${elasticsearch.clusterNode.port:9300}")
    private String nodePort;

    @Value("${elasticsearch.pingTimeout:15000}")
    private String pingTimeout;

    @Value("${elasticsearch.nodeNamePrefix:target/elastic}")
    private String nodeNamePrefix;


    @Value("${elasticsearch.home:/usr/local/Cellar/elasticsearch/6.2.4}")
    private String elasticsearchHome;

    @Value("${elasticsearch.path.home}")
    private String pathHome;

    private volatile Client esClient;

    @Lazy
    @Bean(destroyMethod = "close")
    public Client elasticsearchClient() throws UnknownHostException {
        if (esClient == null) {
            synchronized (this) {
                if (esClient == null) {
                    final Settings settings = Settings.builder()
                            .put("cluster.name", clusterName)
                            .put("node.name", nodeNamePrefix)
                            .put("client.transport.sniff", true)
//                            .put("client.transport.ping_timeout", pingTimeout)
                            .build();

                    esClient = new PluginClient(settings)
                            .addTransportAddress(new TransportAddress(InetAddress.getByName(nodeAddress), Integer.valueOf(nodePort)));

                    logger.info("Elasticsearch initialized on {}:{}.", nodeAddress, nodePort);
                }
            }
        }

        return esClient;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        try {
            return new ElasticsearchTemplate(elasticsearchClient());
        } catch (UnknownHostException e) {
            logger.error("Elasticsearch initialized on {}:{}.", nodeAddress, nodePort);
            e.printStackTrace();
            return null;
        }
    }

    private static class PluginClient extends TransportClient {
        PluginClient(Settings settings) {
            super(settings, Collections.unmodifiableList(Arrays.asList(Netty4Plugin.class, ReindexPlugin.class, PercolatorPlugin.class, MustachePlugin.class)));
        }

        @Override
        public void close() {
            super.close();

            if (!NetworkModule.TRANSPORT_TYPE_SETTING.exists(this.settings) || "netty4".equals(NetworkModule.TRANSPORT_TYPE_SETTING.get(this.settings))) {
                try {
                    GlobalEventExecutor.INSTANCE.awaitInactivity(5L, TimeUnit.SECONDS);
                } catch (InterruptedException var3) {
                    Thread.currentThread().interrupt();
                }

                try {
                    ThreadDeathWatcher.awaitInactivity(5L, TimeUnit.SECONDS);
                } catch (InterruptedException var2) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
