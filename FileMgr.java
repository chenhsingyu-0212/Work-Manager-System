import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileMgr {
    File file;
    String path;
    
    public FileMgr(String path){
        this.file = new File(path);
        this.path = path;
    }

    public ArrayList<String> getData(){
        ArrayList<String> res = new ArrayList<>();
        
        try {
            FileReader reader = new FileReader(file);
            BufferedReader source = new BufferedReader(reader);
            for(String l = source.readLine(); l != null ; l = source.readLine()){
                if(l.length() != 0){
                    res.add(l);
                }
            }
            reader.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error");
        }

        return res;
    }

    public void setData(ArrayList<String> res){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            for(String l : res){
                writer.append(l + "\n");
            }
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error");
        }
    }
}
