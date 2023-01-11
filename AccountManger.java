import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountMgr {
    Map<String, String> account = new HashMap<>();
    FileMgr file;
    String verify;

    public AccountMgr(ConfigMgr config){
        file = new FileMgr("account.txt");
        ArrayList<String> source = file.getData();

        for(String str : source){
            String[] s = str.split(" ");
            account.put(s[0], s[1]);
        }

        verify = config.getVerify();
    }

    public boolean login(String acc, String pw, String vs){
        if (!vs.equals(verify)){
            return false;
        }
        if(!checkAcc(acc)){
            return false;
        }
        return pw.equals(account.get(acc));
    }

    public boolean checkAcc(String acc){
        if(account.containsKey(acc)){
            return true;
        }
        return false;
    }

    public void delAcc(String acc){
        account.remove(acc);
        save();
    }

    public void addAcc(String acc, String pw){
        account.put(acc, pw);
        save();
    }

    public void modAcc(String acc, String newAcc, String newPw){
        account.remove(acc);
        account.put(newAcc, newPw);
        save();
    }

    public void showAcc(){
        System.out.printf("%-12s %-12s ", "[Account]", "[Password]");
        for(String key : account.keySet()){
            System.out.println();
            System.out.printf("%-12s %-12s ", key, account.get(key));
        }
        System.out.println();
    }

    public void save(){
        ArrayList<String> res = new ArrayList<>();

        for(String key : account.keySet()){
            String f = String.format("%s %s", key, account.get(key));
            res.add(f);
        }

        file.setData(res);
    }
}
