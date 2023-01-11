import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataMgr {
    ArrayList<Data> datas = new ArrayList<>();
    ArrayList<String> rawdata;
    Comparator<Data> comparator;
    ConfigMgr config;
    FileMgr file;

    public DataMgr(ConfigMgr config){
        file = new FileMgr("data.txt");
        rawdata = file.getData();

        for(String d : rawdata){
            datas.add(new Data(d));
        }

        this.config = config;
    }

    public ArrayList<String> toString(ArrayList<Data> data){
        ArrayList<String> res = new ArrayList<>();
        
        for(Data d : data){
            String r = d.toString();
            res.add(r);
        }

        return res;
    }

    public ArrayList<String> getRawData(){
        return rawdata;
    }

    public int find(int id){
        String Id = String.format("%04d", id);
        for(int i = 0; i < datas.size(); i++){
            if(datas.get(i).isTarget(1, Id)){
                return i;
            }
        }
        return -1;
    }

    public void del(int index){
        datas.remove(index);
        save();
    }

    public void mod(Data d, int index){
        datas.remove(index);
        datas.add(d);
        save();
    }

    public void add(Data d){
        datas.add(d);
        save();
    }

    public void opt(ConfigMgr config){
        String field = config.getField();
        String order = config.getOrder();
        Comparator<Data> compare = Data.getComparator(field, order);
        Collections.sort(datas, compare);
        save();
        rawdata = toString(datas);
    }

    public ArrayList<Data> getData(){
        ArrayList<Data> res = new ArrayList<>(datas);
        String field = config.getField();
        String order = config.getOrder();
        comparator = Data.getComparator(field, order);
        Collections.sort(res, comparator);
        return res;
    }
    public ArrayList<Data> getData(String cat){
        ArrayList<Data> res = new ArrayList<>();
        for(Data d : datas){
            if(d.isCat(cat)){
                res.add(d);
            }
        }
        return res;
    }
    public ArrayList<Data> getData(int field, String val){
        ArrayList<Data> res = new ArrayList<>();
        for(Data d : datas){
            if(d.isTarget(field, val)){
                res.add(d);
            }
        }
        return res;
    }

    public String getName(int index){
        Data d = datas.get(index);
        return d.name;
    }
    public String getStart(int index){
        Data d = datas.get(index);
        return d.start;
    }
    public String getEnd(int index){
        Data d = datas.get(index);
        return d.end;
    }
    public String getDegree(int index){
        Data d = datas.get(index);
        return d.degree;
    }
    public String getState(int index){
        Data d = datas.get(index);
        return d.state;
    }
    public String getNumber(int index){
        Data d = datas.get(index);
        return d.number;
    }
    public String getCat(int index){
        Data d = datas.get(index);
        return d.catalog;
    }
    public String getWork(int index){
        Data d = datas.get(index);
        return d.work;
    }

    public void save(){
        ArrayList<String> res = new ArrayList<>();
        
        for(Data d : datas){
            String r = d.toString();
            res.add(r);
        }

        file.setData(res);
    }
}
