package org.example.task;

import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.example.utils.FlinkTblEnvAbstract;

import static org.example.utils.FileContent.getFileContent;


public class Sql2Kafka extends FlinkTblEnvAbstract {

    public static void main(String[] args) throws Exception {

        StreamTableEnvironment tbl = tblEnv();

        String filesysSource = getFileContent("sql/filesys/file_source.sql");
        System.out.println("filesysSource = " + filesysSource);

        String kafkaSinkSQL = getFileContent("sql/kafka/kafka_sink.sql");
        System.out.println("kafkaSinkSQL = " + kafkaSinkSQL);

        String filesys2Kafka = getFileContent("sql/action/filesys_to_kafka.sql");
        System.out.println("filesys2Kafka = " + filesys2Kafka);

        tbl.sqlUpdate(filesysSource);
        tbl.sqlUpdate(kafkaSinkSQL);
        tbl.sqlUpdate(filesys2Kafka);

        tbl.execute("local-Sql2Kafka");
    }
}
