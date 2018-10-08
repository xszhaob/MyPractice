package bo.zhao.practice.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.io.DefaultHttpResponseParserFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtils {
    private static PoolingHttpClientConnectionManager manager = null;

    private static CloseableHttpClient httpClient = null;


    public static synchronized CloseableHttpClient getHttpClient() {
        if (httpClient != null) {
            return httpClient;
        }

        // 注册访问协议相关的Socket工厂
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                .build();

        // HttpConnection工厂：配置写请求/解析相应器
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(
                DefaultHttpRequestWriterFactory.INSTANCE,
                DefaultHttpResponseParserFactory.INSTANCE);

        // DNS解析器
        DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;

        // 创建池化连接管理器
        manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connFactory, dnsResolver);

        // 默认为Socket配置
        SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        manager.setDefaultSocketConfig(socketConfig);

        // 最大连接数
        manager.setMaxTotal(300);
        // 每个路由的最大连接数，每个路由的实际最大连接数默认被DefaultMaxPerRoute控制，
        // 而MaxTotal是控制整个池子最大连接数；
        // 设置过小无法支持大并发（ConnectionPoolTimeoutException:Timeout waiting for connection from pool），
        // 路由是对MaxTotal的细分
        manager.setDefaultMaxPerRoute(200);
        // 在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
        manager.setValidateAfterInactivity(5 * 1000);

        RequestConfig requestConfig = RequestConfig.custom()
                // 设置连接超时时间
                .setConnectTimeout(2 * 1000)
                // 设置等待数据超时时间
                .setSocketTimeout(5 * 1000)
                // 设置从连接池获取连接的等待超时时间
                .setConnectionRequestTimeout(2 * 1000)
                .build();

        httpClient = HttpClients.custom()
                .setConnectionManager(manager)
                .setConnectionManagerShared(false)
                .evictIdleConnections(60, TimeUnit.SECONDS)
                .evictExpiredConnections()
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                .setDefaultRequestConfig(requestConfig)
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        return httpClient;
    }

}
