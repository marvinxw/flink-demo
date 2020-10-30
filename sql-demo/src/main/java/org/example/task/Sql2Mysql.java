package org.example.task;

import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.example.utils.FlinkTblEnvAbstract;

import static org.example.utils.FileContent.getFileContent;

public class Sql2Mysql extends FlinkTblEnvAbstract {

    public static void main(String[] args) throws Exception {

        StreamTableEnvironment tbl = tblEnv(3L);

        String kafkaSource = getFileContent("sql/kafka/kafka_source.sql");
        System.out.println("kafkaSource = " + kafkaSource);

        String mysqlSink = getFileContent("sql/mysql/mysql_sink.sql");
        System.out.println("mysqlSink = " + mysqlSink);

        String kafka2MySQL = getFileContent("sql/action/kafka_to_mysql.sql");
        System.out.println("kafka2MySQL = " + kafka2MySQL);

        tbl.sqlUpdate(kafkaSource);
        tbl.sqlUpdate(mysqlSink);
        tbl.sqlUpdate(kafka2MySQL);

        tbl.execute("local-Sql2Mysql");
    }
}

