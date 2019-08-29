package com.bytesvc.shardingjdbc.sample.config;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.apache.shardingsphere.transaction.xa.yaml.config.YamlShardingConfiguration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class ShardingConfiguration {
	static final String configFile = "/META-INF/sharding-databases-tables.yaml";

	@org.springframework.context.annotation.Bean("shardingDataSource")
	public DataSource shardingDataSource() throws SQLException, IOException {
		File file = new File(ShardingConfiguration.class.getResource(configFile).getFile());
		return YamlShardingDataSourceFactory.createDataSource(file, YamlShardingConfiguration.class);
	}

	@org.springframework.context.annotation.Bean
	public DataSourceTransactionManager transactionManager(DataSource shardingDataSource) {
		return new DataSourceTransactionManager(shardingDataSource);
	}

}
