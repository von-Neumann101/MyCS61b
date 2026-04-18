package Lab9;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SaveLoad {
    private final String filename;

    public SaveLoad() {
        filename = "WorldData.txt";
    }

    public void save(List<String> data) {
        Out out = new Out(filename);
        out.println(data);
        out.close();
    }

    public List<String> load() {
        File file = new File(filename);
        if (!file.exists()) {
            save(Collections.emptyList());
            return Collections.emptyList();
        }
        In in = new In(file);
        String line = in.readLine();
        line = line.substring(1, line.length() - 1);

        if (line.isEmpty()) {
            return List.of();
        }

        return List.of(line.split(", "));
    }

//    public static void main(String[] args) {
//        SaveLoad sl = new SaveLoad();
//        List<String> data = new ArrayList<>();
//        data.add("awa");
//        data.add("qwq");
//        data.add("ToT");
//        sl.save(data);
//
//        for(String i : sl.load()) {
//            System.out.println(i);
//        }
//    }
}
