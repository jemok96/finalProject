package com.wegoing;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
	private final static String KEY="wegoing1";
	private final static String ALGORITHM="PBEWithMD5AndDES";
	private final static String CNT="1000";
	private final static String POOL_SIZE="1";
	private final static String BASE64="=base64";

    @Bean(name = "jasyptStringEncryptor")
    StringEncryptor stringEncryptor() {
        
    	SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    	config.setPassword(KEY);
    	config.setAlgorithm(ALGORITHM);
    	config.setKeyObtentionIterations(CNT);
    	config.setPoolSize(POOL_SIZE);
    	config.setStringOutputType(BASE64);
    	PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    	encryptor.setConfig(config);
    	return encryptor;

    }
    public static void main(String[] args) {
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(KEY);
		config.setAlgorithm(ALGORITHM);
		config.setKeyObtentionIterations(CNT);
		config.setPoolSize(POOL_SIZE);
		config.setStringOutputType(BASE64);
		
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(config);
		System.out.println(encryptor.encrypt(KEY));
	}
}
