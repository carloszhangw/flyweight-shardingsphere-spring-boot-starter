package com.flyweight.platform.tools.shardingsphere.algorithm.modulo;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * Created by carloszhang
 */
public class ModuloDatabaseRangeShardingAlgorithm extends AbstractModuloShardingAlgorithm implements RangeShardingAlgorithm<Comparable<? extends Number>> {
    /**
     * select * from t_order from t_order where order_id between 10 and 20
     * ├── SELECT *  FROM db0.t_order_0 WHERE order_id BETWEEN 10 AND 20
     * └── SELECT *  FROM db1.t_order_0 WHERE order_id BETWEEN 10 AND 20
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Comparable<? extends Number>> shardingValue) {
        return doRangeSharding(availableTargetNames, shardingValue);
    }
}
