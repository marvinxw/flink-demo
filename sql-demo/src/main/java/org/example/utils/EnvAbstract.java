package org.example.utils;

import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.java.StreamTableEnvironment;

public abstract class EnvAbstract {

    public static StreamTableEnvironment tblEnv() {
        StreamExecutionEnvironment env = FlinkEnv.initEnv(60L);

        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
        tableEnv.getConfig().setIdleStateRetentionTime(Time.minutes(30), Time.minutes(60));

        return tableEnv;
    }
}
