package com.flyweight.platform.tools.shardingsphere.algorithm.modulo;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 分库-取模方式算法(单分片键-Integer/Long/BigDecimal)
 * <p>
 * <p> = 和 in 适用</p>
 * <p>
 * Created by carloszhang
 */
public class ModuloDatabasePreciseShardingAlgorithm extends AbstractModuloShardingAlgorithm implements PreciseShardingAlgorithm<Comparable<? extends Number>> {
    /**
     * select * from t_order from t_order where order_id = 11
     * └── SELECT *  FROM db0.t_order_0 WHERE order_id = 11
     * select * from t_order from t_order where order_id = 44
     * └── SELECT *  FROM db1.t_order_0 WHERE order_id = 44
     * <p>
     * select * from t_order from t_order where order_id in (11,44)
     * ├── SELECT *  FROM db0.t_order_0 WHERE order_id IN (11,44)
     * └── SELECT *  FROM db1.t_order_0 WHERE order_id IN (11,44)
     * select * from t_order from t_order where order_id in (11,13,15)
     * └── SELECT *  FROM db0.t_order_0 WHERE order_id IN (11,13,15)
     * select * from t_order from t_order where order_id in (22,24,26)
     * └──SELECT *  FROM db1.t_order_0 WHERE order_id IN (22,24,26)
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Comparable<? extends Number>> shardingValue) {
        return doPreciseSharding(availableTargetNames, shardingValue);
    }
}
