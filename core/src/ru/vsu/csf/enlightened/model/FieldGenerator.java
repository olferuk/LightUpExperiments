package ru.vsu.csf.enlightened.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class FieldGenerator {

    FieldCell[][] data;

    public void generateSolutions(AtomicReference<FieldCell[][]> field) {
        data = field.get();

        for (int j = 0; j < data[0].length; j++) {
            for (int i = 0; i < data.length; i++) {

                if (ifIsBlocked(getNeighbours(i,j))) {
                    //enlighten(i, j);
                }

            }
        }

    }



    private boolean ifIsBlocked(ArrayList<Point> neighbours) {
        boolean ok = true;

        for (Point p : neighbours) {
            if (!data[p.x][p.y].getType().equals(FieldCellType.WALL)) {
                ok = false;
                break;
            }
        }

        return ok;
    }

    private ArrayList<Point> getNeighbours(int i, int j) {
        ArrayList<Point> results = new ArrayList<Point>();

        if (i-1 >= 0)
            results.add(new Point(i-1, j));
        if (i+1 < data.length)
            results.add(new Point(i+1, j));
        if (j-1 >= 0)
            results.add(new Point(i, j-1));
        if (j+1 < data[0].length)
            results.add(new Point(i, j+1));

        return results;
    }

}
