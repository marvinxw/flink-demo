package org.example.task;

import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.example.utils.FlinkEnv;

public class Sql2Mysql {

    public static void main(String[] args) {

        StreamExecutionEnvironment env = FlinkEnv.initEnv(60L);

        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
        tableEnv.getConfig().setIdleStateRetentionTime(Time.minutes(30), Time.minutes(60));

    }
}

