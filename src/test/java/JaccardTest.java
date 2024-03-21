import info.debatty.java.stringsimilarity.*;
import org.junit.Test;
import org.lib.MDS;
import org.util.FileUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JaccardTest {
    @Test
    public void testSimilarity(){
        System.out.println("similarity");
        //this param means how many words the jaccard takes each time.
        Jaccard instance = new Jaccard(1);

        // AB BC CD DE DF
        // 1  1  1  1  0
        // 1  1  1  0  1
        // => 3 / 5 = 0.6
        double result = instance.similarity("ABCDE", "ABCDF");
        assertEquals(0.6, result, 0.0);
    }

    @Test
    public void testMultiSimilarity(){
        System.out.println("Multi Similarity");
        FileUtil fileUtil = new FileUtil();
        fileUtil.reader(fileUtil.getResourceFilePath("/javaAnnotationsMap.txt"));
        int lines = fileUtil.getFileLines();
        List<String> list = fileUtil.getLineList();
        Jaccard instance = new Jaccard(2);
        double[][] matrix = new double[lines][lines];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < lines; j++){
                double result = instance.similarity(list.get(i), list.get(j));
                matrix[i][j] = result;
            }
        }
        System.out.println(matrix.length);
        System.out.println(matrix[1].length);
        print(matrix);
        FileUtil fileWriter = new FileUtil("./Jaccard_" + ".csv");

        for (int i = 0; i < matrix.length;i++){
            fileWriter.write(convert2String(matrix[i]));
        }
    }

    public void print(double[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println("\n");
        }
    }

    public String convert2String(double[] matrix){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            stringBuilder.append(matrix[i]+ ",");
            if (i == matrix.length - 1) {
                stringBuilder.append("Alex");
            }
        }
        return stringBuilder.toString();
    }

    @Test
    public void testJaccard_MDS(){
        System.out.println("Multi Similarity");
        FileUtil fileUtil = new FileUtil();
        fileUtil.reader(fileUtil.getResourceFilePath("/javaAnnotationsMap.txt"));
        int lines = fileUtil.getFileLines();
        List<String> list = fileUtil.getLineList();
        Jaccard instance = new Jaccard(2);
        double[][] matrix = new double[lines][lines];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < lines; j++){
                double result = instance.similarity(list.get(i), list.get(j));
                matrix[i][j] = result;
            }
        }

        double[][] mdsMatrix = MDS.fullmds(matrix,2);
        print(mdsMatrix);

        FileUtil fileWriter = new FileUtil("./MDS_" + ".csv");

        for (int i = 0; i < mdsMatrix.length;i++){
            fileWriter.write(convert2String(mdsMatrix[i]));
        }
    }
}
