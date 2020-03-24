package com.flyweight.platform.tools.shardingsphere.boot;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by carloszhang
 */
@Slf4j
public class ShardingJdbcAutoConfigurationImportFilter  implements AutoConfigurationImportFilter {

    private static final Set<String> SHOULD_SKIP = new HashSet(){{
        add(SpringBootConfiguration.class.getName());
    }};


    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
        boolean[] matches = new boolean[autoConfigurationClasses.length];
        for(int i = 0; i< autoConfigurationClasses.length; i++) {
            matches[i] = !SHOULD_SKIP.contains(autoConfigurationClasses[i]);
            if(!matches[i]){
                log.info("Skipped auto configuration classes : {}", autoConfigurationClasses[i]);
            }
        }
        return matches;
    }
}
