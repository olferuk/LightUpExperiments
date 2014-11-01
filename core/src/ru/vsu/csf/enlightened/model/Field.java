package ru.vsu.csf.enlightened.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicReference;

public class Field {

    FieldGenerator generator;
    FieldCell[][] data;

    public Field() {
        generator = new FieldGenerator();
    }

    public void init(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String[] sizes = reader.readLine().split(" ");
            data = new FieldCell[Integer.parseInt(sizes[0])][Integer.parseInt(sizes[1])];

            for (int j = 0; j < data[0].length; j++) {
                String[] chunks = reader.readLine().split(" ");

                for (int i = 0; i < data.length; i++) {
                    int code = Integer.parseInt(chunks[i]);
                    if (code == 0)
                        data[i][j] = new FieldCell(FieldCellType.REGULAR, -1);
                    else
                        data[i][j] = new FieldCell(FieldCellType.WALL, -1);
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FieldCell[][] getData() {
        return data;
    }

    public void analyze() {
        AtomicReference<FieldCell[][]> field = new AtomicReference<FieldCell[][]>(){{ set(data); }};

        generator.generateSolutions(field);
    }


    public void enlighten(int i, int j) {
        if (data[i][j].getType().equals(FieldCellType.WALL))
            return;

        data[i][j].setType(FieldCellType.BULB);

        if (i > 0) {
            boolean ok = true;
            for (int left = i-1; left >= 0; left--) {
                if (!ok)
                    break;

                switch (data[left][j].getType()) {
                    case REGULAR:
                        data[left][j].setType(FieldCellType.LIT);
                        break;
                    case WALL:
                        ok = false;
                        break;
                }
            }
        }

        if (i < data.length - 1) {
            boolean ok = true;
            for (int right = i+1; right < data.length; right++) {
                if (!ok)
                    break;

                switch (data[right][j].getType()) {
                    case REGULAR:
                        data[right][j].setType(FieldCellType.LIT);
                        break;
                    case WALL:
                        ok = false;
                        break;
                }
            }
        }

        if (j > 0) {
            boolean ok = true;
            for (int up = j-1; up >= 0; up--) {
                if (!ok)
                    break;

                switch (data[i][up].getType()) {
                    case REGULAR:
                        data[i][up].setType(FieldCellType.LIT);
                        break;
                    case WALL:
                        ok = false;
                        break;
                }
            }
        }

        if (j < data[0].length - 1) {
            boolean ok = true;
            for (int down = j+1; down < data[0].length; down++) {
                if (!ok)
                    break;

                switch (data[i][down].getType()) {
                    case REGULAR:
                        data[i][down].setType(FieldCellType.LIT);
                        break;
                    case WALL:
                        ok = false;
                        break;
                }
            }
        }
    }
}
