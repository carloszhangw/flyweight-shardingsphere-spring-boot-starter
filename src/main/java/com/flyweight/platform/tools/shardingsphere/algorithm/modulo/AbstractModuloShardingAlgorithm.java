package com.flyweight.platform.tools.shardingsphere.algorithm.modulo;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by carloszhang
 * 取模算法基类
 */
public class AbstractModuloShardingAlgorithm {
    /**
     * 取模
     * @param shardingValue
     * @param availableTargetNamesSize
     * @return
     */
    protected String doModulo(Comparable<? extends Number> shardingValue, int availableTargetNamesSize){
        if(shardingValue instanceof Long){
            return Long.toString((Long)shardingValue % availableTargetNamesSize);
        } else if(shardingValue instanceof Integer){
            return Integer.toString((Integer)shardingValue % availableTargetNamesSize);
        } else if(shardingValue instanceof BigDecimal){
            return ((BigDecimal) shardingValue).divideAndRemainder(new BigDecimal(availableTargetNamesSize))[1].toString();
        } else{
            throw new IllegalArgumentException("Unsupported type: " + shardingValue);
        }
    }


    /**
     * 精确分片
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    protected String doPreciseSharding(Collection<String> availableTargetNames, PreciseShardingValue<Comparable<? extends Number>> shardingValue) {
        int size = availableTargetNames.size();
        for (String each : availableTargetNames) {
            if (each.endsWith(doModulo(shardingValue.getValue(), size))) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * 范围分片
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    protected Collection<String> doRangeSharding(Collection<String> availableTargetNames, RangeShardingValue<Comparable<? extends Number>> shardingValue) {
        int size = availableTargetNames.size();
        Collection<String> result = new LinkedHashSet<>(size);
        Range<Comparable<? extends Number>> range = shardingValue.getValueRange();
        if(range.lowerEndpoint() instanceof Long){
            for (Long i = (Long)range.lowerEndpoint(); i <= (Long)range.upperEndpoint(); i++) {
                for (String each : availableTargetNames) {
                    if (each.endsWith(Long.toString(i % size))) {
                        result.add(each);
                    }
                }
            }
        }else if (range.lowerEndpoint() instanceof Integer){
            for (Integer i = (Integer)range.lowerEndpoint(); i <= (Integer) range.upperEndpoint(); i++) {
                for (String each : availableTargetNames) {
                    if (each.endsWith(Integer.toString(i % size))) {
                        result.add(each);
                    }
                }
            }
        }else{
            throw new IllegalArgumentException("Unsupported type: " + range.lowerEndpoint());
        }
        return result;
    }
}
