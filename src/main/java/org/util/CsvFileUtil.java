package org.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
public class CsvFileUtil {
    public static void createCSVFile(List<Map.Entry<String, Integer>> invokedApiList, String projectName) throws IOException {
        List<String> paramList;
        try (FileWriter out = new FileWriter("./MDS_" + projectName + ".csv");
             CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT);
        ) {
            for(Map.Entry<String, Integer> map : invokedApiList) {
                csvPrinter.printRecord(
                        map.getKey(),
                        map.getValue());
            }
        }
    }
}
