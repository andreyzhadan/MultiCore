package com.zhadan.test;

import java.io.IOException;
import java.util.List;

import static java.lang.System.out;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.FileSystems.getDefault;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.write;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;

/**
 * Created by andrewzhadan on 6/29/14.
 */
public class Lambda {
    public static void main(String[] args) throws IOException {
        List<String> collect = readAllLines(getDefault().getPath("q.txt"), UTF_8)
                .stream().filter(t -> t.length() != 0).sorted().collect(toList());
        collect.forEach(out::println);
        write(get("w.txt"), collect);
    }
}
