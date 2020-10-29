package org.example.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileContent {

    public static String getFileContent(String path) {
        InputStream is = FileContent.class.getClassLoader().getResourceAsStream(path);
        Preconditions.checkState(is != null);
        try {
            return IOUtils.toString(is, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        String c = getFileContent("sql/kafka/kafka_sink.sql");
        System.out.println("c = " + c);
    }
}
