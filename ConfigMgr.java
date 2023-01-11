import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfigMgr {
    Map<String, String> config = new HashMap<>();
    FileMgr file;

    public ConfigMgr(){
        file = new FileMgr("config.txt");
        ArrayList<String> source = file.getData();

        for(String str : source){
            String[] s = str.split(" *: *");
            config.put(s[0], s[1]);
        }
    }

    public String getVerify(){
        return config.get("verify_string");
    }
    public String getLastId(){
        return String.format("%04d", Integer.parseInt(config.get("used_last_id")));
    }
    public String getOrder(){
        return config.get("show_sort_order");
    }
    public String getField(){
        return config.get("show_sort_field");
    }
    public String getPerPage(){
        return config.get("show_defalt_perpage");
    }
    public String getShowName(){
        return config.get("show_name");
    }
    public String getShowStart(){
        return config.get("show_start");
    }
    public String getShowEnd(){
        return config.get("show_end");
    }
    public String getShowDegree(){
        return config.get("show_degree");
    }
    public String getShowState(){
        return config.get("show_state");
    }
    public String getShowNumber(){
        return config.get("show_number");
    }
    public String getShowCatalog(){
        return config.get("show_catalog");
    }
    public String getShowWork(){
        return config.get("show_work");
    }

    public void setVerify(String v){
        config.replace("verify_string", v);
        save();
    }
    public void setLastId(){
        int id = Integer.parseInt(getLastId()) + 1;
        config.replace("used_last_id", Integer.toString(id));
        save();
    }
    public void setOrder(String o){
        config.replace("show_sort_order", o);
        save();
    }
    public void setField(String f){
        config.replace("show_sort_field", f);
        save();
    }
    public void setPerPage(String p){
        config.replace("show_defalt_perpage", p);
        save();
    }
    public void setShowName(String n){
        config.replace("show_name", n);
        save();
    }
    public void setShowStart(String s){
        config.replace("show_start", s);
        save();
    }
    public void setShowEnd(String e){
        config.replace("show_end", e);
        save();
    }
    public void setShowDegree(String d){
        config.replace("show_degree", d);
        save();
    }
    public void setShowState(String s){
        config.replace("show_state", s);
        save();
    }
    public void setShowNumber(String n){
        config.replace("show_number", n);
        save();
    }
    public void setShowCatalog(String c){
        config.replace("show_catalog", c);
        save();
    }
    public void setShowWork(String w){
        config.replace("show_work", w);
        save();
    }

    public void showConfig(){
        System.out.print("[1].Show_name:" + (getShowName().equals("true") ? "1" : "0"));
        System.out.print(" [2].Show_start:" + (getShowStart().equals("true") ? "1" : "0"));
        System.out.print(" [3].Show_end:" + (getShowEnd().equals("true") ? "1" : "0"));
        System.out.print(" [4].Show_degree:" + (getShowDegree().equals("true") ? "1" : "0"));
        System.out.print(" [5].Show_state:" + (getShowState().equals("true") ? "1" : "0"));
        System.out.print(" [6].Show_number:" + (getShowNumber().equals("true") ? "1" : "0"));
        System.out.print(" [7].Show_catalog:" + (getShowCatalog().equals("true") ? "1" : "0"));
        System.out.println(" [8].Show_work:" + (getShowWork().equals("true") ? "1" : "0"));
        System.out.println("");
    }

    public void save(){
        ArrayList<String> res = new ArrayList<>();

        for(String key : config.keySet()){
            String f = String.format("%s: %s", key, config.get(key));
            res.add(f);
        }

        file.setData(res);
    }
}
