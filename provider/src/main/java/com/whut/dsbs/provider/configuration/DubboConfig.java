package com.whut.dsbs.provider.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * dubbo配置
 *
 * Created by zyb on 2017-04-29.
 */
@Configuration
@PropertySource("classpath:application.properties")
@ImportResource("classpath:dubbo-provider.xml")
public class DubboConfig {
}
