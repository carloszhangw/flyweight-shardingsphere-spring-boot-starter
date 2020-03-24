package com.flyweight.platform.tools.shardingsphere.algorithm.modulo;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * 分表-取模方式算法(单分片键-Integer/Long/BigDecimal)
 * <p>
 * <p> between and 适用</p>
 * Created by carloszhang
 */
public class ModuloTableRangeShardingAlgorithm extends AbstractModuloShardingAlgorithm implements RangeShardingAlgorithm<Comparable<? extends Number>> {

    /**
     * select * from t_order from t_order where order_id between 10 and 20
     * ├── SELECT *  FROM t_order_0 WHERE order_id BETWEEN 10 AND 20
     * └── SELECT *  FROM t_order_1 WHERE order_id BETWEEN 10 AND 20
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Comparable<? extends Number>> shardingValue) {
        return doRangeSharding(availableTargetNames, shardingValue);
    }

}
