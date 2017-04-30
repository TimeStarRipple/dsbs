package com.whut.dsbs.customer.configuration;

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
@ImportResource(locations = {"classpath:dubbo-consumer.xml"})
public class DubboConfig {
}
