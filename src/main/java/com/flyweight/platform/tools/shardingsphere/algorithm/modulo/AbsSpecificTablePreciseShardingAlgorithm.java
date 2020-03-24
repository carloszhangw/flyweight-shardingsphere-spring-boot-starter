package com.flyweight.platform.tools.shardingsphere.algorithm.modulo;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 这里特殊处理下,因为分库和分表的都是一个分片键,为了让数据均匀分配,所以使用一些特殊的手段
 * 因为属于自定义，在使用的过程中扩展
 */
public abstract class AbsSpecificTablePreciseShardingAlgorithm extends AbstractModuloShardingAlgorithm implements PreciseShardingAlgorithm<Comparable<? extends Number>> {

//

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Comparable<? extends Number>> shardingValue) {
        int size = availableTargetNames.size();
        for (String each : availableTargetNames) {
            if (each.endsWith(doSpecific(shardingValue.getValue(), size))) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 取模
     *
     * @param shardingValue
     * @param availableTargetNamesSize
     * @return
     */
    protected String doSpecific(Comparable<? extends Number> shardingValue, int availableTargetNamesSize) {
        if (shardingValue instanceof Long) {
            return Long.toString(((Long) shardingValue / buildDatabaseSiz()) % availableTargetNamesSize);
        } else if (shardingValue instanceof Integer) {
            return Integer.toString(((Integer) shardingValue / buildDatabaseSiz()) % availableTargetNamesSize);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + shardingValue);
        }
    }

    public abstract int buildDatabaseSiz();
}
