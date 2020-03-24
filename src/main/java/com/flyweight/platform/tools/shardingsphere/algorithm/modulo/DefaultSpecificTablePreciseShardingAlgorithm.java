package com.flyweight.platform.tools.shardingsphere.algorithm.modulo;

/**
 * Created by carloszhang
 */
public class DefaultSpecificTablePreciseShardingAlgorithm extends AbsSpecificTablePreciseShardingAlgorithm {

    private static final int DATABASE_SIZE = 2;

    @Override
    public int buildDatabaseSiz() {
        return DATABASE_SIZE;
    }
}
