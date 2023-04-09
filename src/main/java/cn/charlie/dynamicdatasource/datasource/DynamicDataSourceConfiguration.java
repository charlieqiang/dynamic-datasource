package cn.charlie.dynamicdatasource.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author charlie
 * @date 4/9/2023 4:00 PM
 **/
@Configuration
@EnableConfigurationProperties({DynamicDataSourceProperties.class, MybatisProperties.class})
@ConditionalOnProperty(
        prefix = "spring.datasource.dynamic",
        name = {"enable"},
        havingValue = "true"
)
@EnableTransactionManagement(
        proxyTargetClass = true,
        order = 1
)
public class DynamicDataSourceConfiguration {
    private DynamicDataSourceProperties dynamicDataSourceProperties;
    private MybatisProperties mybatisProperties;
    private String primary;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    Environment env;

    public DynamicDataSourceConfiguration(DynamicDataSourceProperties dynamicDataSourceProperties, MybatisProperties mybatisProperties) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
        this.mybatisProperties = mybatisProperties;
        this.primary = dynamicDataSourceProperties.getPrimary();
    }

    private DataSource dataSourceProxy(DataSource dataSource) {
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(
            prefix = "spring.datasource.druid"
    )
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean({"dataSource"})
    public DataSource dynamicDataSource(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
        Map<String, DynamicDataSourceProperties.DataSourceProperty> dynamicDataSourceMap = this.dynamicDataSourceProperties.getDatasource();
        Map<Object, Object> targetDataSources = new HashMap(dynamicDataSourceMap.size());
        Iterator<Map.Entry<String, DynamicDataSourceProperties.DataSourceProperty>> iterator = dynamicDataSourceMap.entrySet().iterator();

        DataSource defaultTargetDataSource;
        String key;
        DataSource dataSourceProxy;
        for(defaultTargetDataSource = primaryDataSource; iterator.hasNext(); targetDataSources.put(key, dataSourceProxy)) {
            DruidDataSource datasource = ((DruidDataSource)primaryDataSource).cloneDruidDataSource();
            Map.Entry<String, DynamicDataSourceProperties.DataSourceProperty> entry = (Map.Entry)iterator.next();
            key = (String)entry.getKey();
            DynamicDataSourceProperties.DataSourceProperty value = (DynamicDataSourceProperties.DataSourceProperty)entry.getValue();
            datasource.setDriverClassName(value.getDriverClassName());
            datasource.setUrl(value.getUrl());
            datasource.setUsername(value.getUsername());
            datasource.setPassword(value.getPassword());
            dataSourceProxy = this.dataSourceProxy(datasource);
            if (this.primary.equals(key)) {
                defaultTargetDataSource = dataSourceProxy;
            }
        }

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(defaultTargetDataSource);
        dataSource.setTargetDataSources(targetDataSources);
        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource primaryDataSource(@Qualifier("druidDataSource") DruidDataSource druidDataSource) {
        Map<String, DynamicDataSourceProperties.DataSourceProperty> dynamicDataSourceMap = this.dynamicDataSourceProperties.getDatasource();
        DynamicDataSourceProperties.DataSourceProperty dataSourceProperty = (DynamicDataSourceProperties.DataSourceProperty)dynamicDataSourceMap.get(this.primary);
        druidDataSource.setDriverClassName(dataSourceProperty.getDriverClassName());
        druidDataSource.setUrl(dataSourceProperty.getUrl());
        druidDataSource.setUsername(dataSourceProperty.getUsername());
        druidDataSource.setPassword(dataSourceProperty.getPassword());
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dynamicDataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.mybatisProperties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.mybatisProperties.getConfigLocation()));
        }

        this.applyConfiguration(factory);
        if (this.mybatisProperties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.mybatisProperties.getConfigurationProperties());
        }

        if (StringUtils.hasLength(this.mybatisProperties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.mybatisProperties.getTypeAliasesPackage());
        }

        if (this.mybatisProperties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(this.mybatisProperties.getTypeAliasesSuperType());
        }

        if (StringUtils.hasLength(this.mybatisProperties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.mybatisProperties.getTypeHandlersPackage());
        }

        if (!ObjectUtils.isEmpty(this.mybatisProperties.resolveMapperLocations())) {
            factory.setMapperLocations(this.mybatisProperties.resolveMapperLocations());
        }

        return factory.getObject();
    }

    private void applyConfiguration(SqlSessionFactoryBean factory) {
        org.apache.ibatis.session.Configuration configuration = this.mybatisProperties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(this.mybatisProperties.getConfigLocation())) {
            configuration = new org.apache.ibatis.session.Configuration();
        }

        factory.setConfiguration(configuration);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
