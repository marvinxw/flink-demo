package org.example.task;

import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.example.utils.FlinkEnv;


import static org.example.utils.FileContent.getFileContent;

public class Sql2Kafka {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = FlinkEnv.initEnv(60L);

        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
        tableEnv.getConfig().setIdleStateRetentionTime(Time.minutes(30), Time.minutes(60));

        String filesysSource = getFileContent("sql/filesys/file_source.sql");
        System.out.println("filesysSource = " + filesysSource);

        String kafkaSinkSQL = getFileContent("sql/kafka/kafka_sink.sql");
        System.out.println("kafkaSinkSQL = " + kafkaSinkSQL);

        String filesys2Kafka = getFileContent("sql/action/filesys_to_kafka.sql");
        System.out.println("filesys2Kafka = " + filesys2Kafka);

        tableEnv.sqlUpdate(filesysSource);
        tableEnv.sqlUpdate(kafkaSinkSQL);
        tableEnv.sqlUpdate(filesys2Kafka);

        env.execute("local-Sql2Kafka");
    }
}
