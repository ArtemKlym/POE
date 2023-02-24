
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int x = 1, b = 1;

    public static void main(String[] args) {

        String current = System.getProperty("user.dir");
        System.out.println("Current working directory in Java : " + current);

        String path = System.getProperty("user.dir") + File.separator;
        File file[] = new File(path).listFiles();


        for (int i = 0; i < file.length; i++) {
            if(file[i].isDirectory())
            {
                System.out.println("->" + file[i].getName() + " (folder)"+x);
                new Main().listfiles(file[i], "\t");
                x++;
            }
            else
            {
                System.out.println("-> " + file[i].getName()+ " (file)"+i);
            }
        }
    }

    public void listfiles(File f, String sprtr) {
        int k = 1;
        File file[] = f.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) {
                System.out.println(sprtr + "-> " + file[i].getName() + " (folder)"+ k);
                k++;
                new Main().listfiles(file[i], sprtr+sprtr);
            }
            else {
                System.out.println(sprtr + file[i].getName()+" (file)"+b);
                b++;
            }

        }
    }
}

