import java.util.ArrayList;
import java.util.Comparator;

public class CatalogMgr {
    ArrayList<String> cat = new ArrayList<>();
    FileMgr file;

    public CatalogMgr(){
        file = new FileMgr("catalog.txt");
        cat = file.getData();
    }

    public String getCat(int index){
        if(index >= cat.size()){
            return null;
        }
        return cat.get(index);
    }

    public int getCatSize(){
        return cat.size();
    }

    public void showCatChoose(){
        for(int i = 0; i < cat.size(); i++){
            System.out.print("[" + (i + 1) + "]." + cat.get(i) + " ");
        }
    }

    public void showCat(){
        System.out.println("[Catalog]");
        for(int i = 0; i < cat.size(); i++){
            System.out.print(cat.get(i));
            System.out.println();
        }
    }

    public boolean checkCat(String s){
        if(cat.contains(s)){
            return true;
        }
        return false;
    }

    public void addCat(String s){
        cat.add(s);
        cat.sort(Comparator.naturalOrder());
        save();
    }

    public void save(){
        file.setData(cat);
    }
}
