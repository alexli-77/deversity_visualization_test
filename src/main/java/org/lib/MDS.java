package org.lib;

import lombok.NoArgsConstructor;
import org.common.Data;

@NoArgsConstructor
public class MDS {
    public static double[][] fullmds(double[][] d, int dim) {
        double[][] result = new double[dim][d.length];
        Data.randomize(result);
        double[] evals = new double[result.length];
        Data.squareEntries(d);
        Data.doubleCenter(d);
        Data.multiply(d, -0.5D);
        Data.eigen(d, result, evals);
        for (int i = 0; i < result.length; i++) {
            evals[i] = Math.sqrt(evals[i]);
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] *= evals[i];
            }
        }
        return result;
    }

    public static double[][] classicalScaling(double [][] distances, int dim) {
        double[][] d = Data.copyMatrix(distances);
        return fullmds(d, dim);
    }
}
