
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int b = 1, k = 1;

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator;
        System.out.println("Current working directory in Java : " + path);

        File file[] = new File(path).listFiles();
        new Main().listfiles(file, "\t");
    }

    public void listfiles(File[] f, String sprtr) {
        File file[] = f;
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) {
                System.out.println(sprtr + "-> " + file[i].getName() + " (folder)"+ k);
                k++;
                new Main().listfiles(file[i].listFiles(), sprtr + "\t");
            }
            else {
                System.out.println(sprtr + file[i].getName()+" (file)"+b);
                b++;
            }
        }
    }
}

