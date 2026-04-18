package Lab9;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveLoad {
    private final String filename;
    private final String filename2;

    public SaveLoad() {
        filename = "WorldData.txt";
        filename2 = "RandomStatus.txt";
    }

    public void save(int random) {
        Out out = new Out(filename2);
        out.println(random);
        out.close();
    }

    public void save(List<Character> data) {
        Out out = new Out(filename);
        for (char c : data) {
            out.println(c);
        }
        out.close();
    }

    public List<Character> load() {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        In in = new In(file);
        List<Character> result = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.readLine();
            if (!line.isEmpty()) {
                result.add(line.charAt(0));
            }
        }
        return result;
    }

}