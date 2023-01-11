import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    private static final Scanner sc = new Scanner(System.in);
    private static final ConfigMgr configMgr = new ConfigMgr();
    private static final AccountMgr accountMgr = new AccountMgr(configMgr);
    private static final CatalogMgr catalogMgr = new CatalogMgr();
    private static final DataMgr dataMgr = new DataMgr(configMgr);

    public static void Show_a() {
        showDisplay(dataMgr.getData());

        subMenu();
    }

    public static void Show_p() {
        println("Choose_show_per_page:");
        String[] codes = { "3", "5", "10", "d", "0", "99" };
        String[] codesi = { "3", "5", "10" };
        String[] infoi = { "3_data_per_page", "5_data_per_page", "10_data_per_page" };
        showFormat(codesi, infoi);
        String[] codesii = { "d", "0", "99" };
        String[] infoii = { "default", "Go_back_to_main_menu", "Exit_system" };
        showFormat(codesii, infoii);

        String cmd = sc.next();
        while (!checkCmd(codes, cmd)) {
            println("Error_wrong_command");
            println("Please_enter_again:");
            cmd = sc.next();
        }
        switch (cmd) {
            case "0":
                return;
            case "99":
                System.exit(0);
            case "d":
                cmd = configMgr.getPerPage();
        }

        ArrayList<Data> data = dataMgr.getData();
        int numPage = Integer.parseInt(cmd);
        int page = (int) Math.ceil(data.size() / (numPage * 1.0));
        ArrayList<ArrayList<Data>> allPage = perPage(data, numPage, page);

        for (int i = 0; i < page;) {
            if (page == 1) {
                for (Data d : allPage.get(i)) {
                    d.printDisplay(configMgr.getShowName(), configMgr.getShowStart(), configMgr.getShowEnd(),
                            configMgr.getShowDegree(), configMgr.getShowState(), configMgr.getShowNumber(),
                            configMgr.getShowCatalog(), configMgr.getShowWork());
                }
                String[] codeStrings = { "0", "99" };
                String[] infoStrings = { "Go_back_to_main_menu", "Exit_system" };
                showFormat(codeStrings, infoStrings);
                cmd = sc.next();
                while (!checkCmd(codeStrings, cmd)) {
                    println("Error_wrong_command");
                    println("Please_enter_again:");
                    cmd = sc.next();
                }
                switch (cmd) {
                    case "0":
                        return;
                    case "99":
                        System.exit(0);
                }
            } else if (i == 0) {
                for (Data d : allPage.get(i)) {
                    d.printDisplay(configMgr.getShowName(), configMgr.getShowStart(), configMgr.getShowEnd(),
                            configMgr.getShowDegree(), configMgr.getShowState(), configMgr.getShowNumber(),
                            configMgr.getShowCatalog(), configMgr.getShowWork());
                }
                String[] codeStrings = { "2", "0", "99" };
                String[] infoStrings = { "Next_page", "Go_back_to_main_menu", "Exit_system" };
                showFormat(codeStrings, infoStrings);
                cmd = sc.next();
                while (!checkCmd(codeStrings, cmd)) {
                    println("Error_wrong_command");
                    println("Please_enter_again:");
                    cmd = sc.next();
                }
                switch (cmd) {
                    case "0":
                        return;
                    case "99":
                        System.exit(0);
                    case "2":
                        i++;
                        break;
                }
            } else if (i == (page - 1)) {
                for (Data d : allPage.get(i)) {
                    d.printDisplay(configMgr.getShowName(), configMgr.getShowStart(), configMgr.getShowEnd(),
                            configMgr.getShowDegree(), configMgr.getShowState(), configMgr.getShowNumber(),
                            configMgr.getShowCatalog(), configMgr.getShowWork());
                }
                String[] codeStrings = { "1", "0", "99" };
                String[] infoStrings = { "Last_page", "Go_back_to_main_menu", "Exit_system" };
                showFormat(codeStrings, infoStrings);
                cmd = sc.next();
                while (!checkCmd(codeStrings, cmd)) {
                    println("Error_wrong_command");
                    println("Please_enter_again:");
                    cmd = sc.next();
                }
                switch (cmd) {
                    case "0":
                        return;
                    case "99":
                        System.exit(0);
                    case "1":
                        i--;
                        break;
                }
            } else {
                for (Data d : allPage.get(i)) {
                    d.printDisplay(configMgr.getShowName(), configMgr.getShowStart(), configMgr.getShowEnd(),
                            configMgr.getShowDegree(), configMgr.getShowState(), configMgr.getShowNumber(),
                            configMgr.getShowCatalog(), configMgr.getShowWork());
                }
                String[] codeStrings = { "1", "2", "0", "99" };
                String[] infoStrings = { "Last_page", "Next_page", "Go_back_to_main_menu", "Exit_system" };
                showFormat(codeStrings, infoStrings);
                cmd = sc.next();
                while (!checkCmd(codeStrings, cmd)) {
                    println("Error_wrong_command");
                    println("Please_enter_again:");
                    cmd = sc.next();
                }
                switch (cmd) {
                    case "0":
                        return;
                    case "99":
                        System.exit(0);
                    case "1":
                        i--;
                        break;
                    case "2":
                        i++;
                        break;
                }
            }
        }
    }

    public static void Show_by_c() {
        println("Catalogs:");
        catalogMgr.showCatChoose();
        println("");
        String[] codeStrings = { "0", "99" };
        String[] infoStrings = { "Go_back_to_main_menu", "Exit_system" };
        showFormat(codeStrings, infoStrings);

        String[] codes = new String[catalogMgr.getCatSize() + 2];
        for (int i = 0; i <= catalogMgr.getCatSize(); i++) {
            if (i < catalogMgr.getCatSize()) {
                codes[i] = Integer.toString(i + 1);
            } else {
                codes[i] = "0";
                codes[i + 1] = "99";
            }
        }
        String cmd = sc.next();
        while (!checkCmd(codes, cmd)) {
            println("Error_wrong_data");
            println("Please_input_again:");
            cmd = sc.next();
        }
        switch (cmd) {
            case "0":
                return;
            case "99":
                System.exit(0);
        }

        println("Input_catalog_to_show:");
        showDisplay(dataMgr.getData(catalogMgr.getCat(Integer.parseInt(cmd) - 1)));

        showFormat(codeStrings, infoStrings);
        cmd = sc.next();
        while (!checkCmd(codes, cmd)) {
            println("Error_wrong_command");
            println("Please_enter_again:");
            cmd = sc.next();
        }
        switch (cmd) {
            case "0":
                return;
            case "99":
                System.exit(0);
        }
    }

    public static void Search() {
        while (true) {
            println("Search by:");
            String[] codes = { "1", "2", "3", "4", "5", "6", "7", "8" };
            String[] info = { "ID", "Name", "Start", "End", "Degree", "State", "Number", "Work" };
            showFormat(codes, info);
            String[] codeStrings = { "0", "99" };
            String[] infoStrings = { "Go_back_to_main_menu", "Exit_system" };
            showFormat(codeStrings, infoStrings);
            String[] code = { "1", "2", "3", "4", "5", "6", "7", "8", "0", "99" };

            String cmd = sc.next();
            while (!checkCmd(code, cmd)) {
                println("Error_wrong_command");
                println("Please_enter_again:");
                cmd = sc.next();
            }
            switch (cmd) {
                case "0":
                    return;
                case "99":
                    System.exit(0);
            }

            println("Input_target:");
            String contain = sc.next();
            while (!isRight(info[Integer.parseInt(cmd) - 1], contain)) {
                println("Error_wrong_data");
                println("Please_input_again:");
                contain = sc.next();
            }

            ArrayList<Data> data = dataMgr.getData(Integer.parseInt(cmd), contain);
            if (data.isEmpty()) {
                println("Error_no_result");
            } else {
                println("Search_result:");
                showDisplay(data);
            }

            String[] c = { "1", "0", "99" };
            String[] i = { "Restart_search", "Go_back_to_main_menu", "Exit_system" };
            showFormat(c, i);
            cmd = sc.next();
            while (!checkCmd(c, cmd)) {
                println("Error_wrong_command");
                println("Please_enter_again:");
                cmd = sc.next();
            }
            switch (cmd) {
                case "1":
                    break;
                case "0":
                    return;
                case "99":
                    System.exit(0);
            }
        }
    }

    public static void Mod() {
        println("Input_ID_to_be_modified:");
        String id = sc.next();
        int index = dataMgr.find(Integer.parseInt(id));
        if (index == -1) {
            println("Error_no_such_data");
        } else {
            println("Search_result:");
            showDisplay(dataMgr.getData(1, id));
        }
        sc.nextLine();

        println("New_name:");
        String n = sc.nextLine();
        if (n.equals("")) {
            n = dataMgr.getName(index);
        } else {
            while (!isRight("Name", n)) {
                println("Error_wrong_data");
                println("Please_input_again:");
                n = sc.nextLine();
                if (n.equals("")) {
                    n = dataMgr.getName(index);
                    break;
                }
            }
        }

        println("New_start:");
        String s = sc.nextLine();
        if (s.equals("")) {
            s = dataMgr.getStart(index);
        } else {
            while (!isRight("Start", s)) {
                println("Error_wrong_data");
                println("Please_input_again:");
                s = sc.nextLine();
                if (s.equals("")) {
                    s = dataMgr.getStart(index);
                    break;
                }
            }
        }

        println("New_end:");
        String e = sc.nextLine();
        if (e.equals("")) {
            e = dataMgr.getEnd(index);
        } else {
            while (!isRight("End", e)) {
                println("Error_wrong_data");
                println("Please_input_again:");
                e = sc.nextLine();
                if (e.equals("")) {
                    e = dataMgr.getEnd(index);
                    break;
                }
            }
        }

        println("New_degree:");
        String d = sc.nextLine();
        if (d.equals("")) {
            d = dataMgr.getDegree(index);
        } else {
            while (!isRight("Degree", d)) {
                println("Error_wrong_data");
                println("Please_input_again:");
                d = sc.nextLine();
                if (d.equals("")) {
                    d = dataMgr.getDegree(index);
                    break;
                }
            }
        }

        println("New_state:");
        String st = sc.nextLine();
        if (st.equals("")) {
            st = dataMgr.getState(index);
        } else {
            while (!isRight("State", st)) {
                println("Error_wrong_data");
                println("Please_input_again:");
                st = sc.nextLine();
                if (st.equals("")) {
                    st = dataMgr.getState(index);
                    break;
                }
            }
        }

        println("New_number:");
        String num = sc.nextLine();
        if (num.equals("")) {
            num = dataMgr.getNumber(index);
        } else {
            while (!isRight("Number", num)) {
                println("Error_wrong_data");
                println("Please_input_again:");
                num = sc.nextLine();
                if (num.equals("")) {
                    num = dataMgr.getNumber(index);
                    break;
                }
            }
        }

        print("Catalogs:");
        catalogMgr.showCatChoose();
        println("");
        println("New_catalog:");
        String cat = sc.nextLine();
        if (cat.equals("")) {
            cat = dataMgr.getCat(index);
        } else {
            int catindex = Integer.parseInt(cat);
            cat = catalogMgr.getCat(catindex - 1);
            while (catindex <= 0 && catindex > catalogMgr.getCatSize()) {
                println("Error_wrong_data");
                println("Please_input_again:");
                cat = sc.nextLine();
                if (cat.equals("")) {
                    cat = dataMgr.getCat(index);
                    break;
                }
                catindex = Integer.parseInt(cat);
                cat = catalogMgr.getCat(catindex - 1);
            }
        }

        println("New_work:");
        String w = sc.nextLine();
        if (w.equals("")) {
            w = dataMgr.getWork(index);
        } else {
            while (!isRight("Work", w)) {
                println("Error_wrong_data");
                println("Please_input_again:");
                w = sc.nextLine();
                if (w.equals("")) {
                    w = dataMgr.getWork(index);
                    break;
                }
            }
        }

        String f = String.format("%04d %s %s %s %s %s %s %s %s", Integer.parseInt(id), n, s, e, d, st, num,
                cat, w);
        Data data = new Data(f);
        dataMgr.mod(data, index);
        println("Modify_data_success");

        subMenu();
    }

    public static void Del() {
        println("Input_ID_to_be_deleted:");
        String id = sc.next();
        int index = dataMgr.find(Integer.parseInt(id));
        if (index == -1) {
            println("Error_no_such_data");
        } else {
            dataMgr.del(index);
            println("Delete_data_success");
        }

        subMenu();
    }

    public static void Add_job() {
        println("Name:");
        String n = sc.next();
        while (!isRight("Name", n)) {
            println("Error_wrong_data");
            println("Please_input_again:");
            n = sc.next();
        }

        println("Start:");
        String s = sc.next();
        while (!isRight("Start", s)) {
            println("Error_wrong_data");
            println("Please_input_again:");
            s = sc.next();
        }

        println("End:");
        String e = sc.next();
        while (!isRight("End", e)) {
            println("Error_wrong_data");
            println("Please_input_again:");
            e = sc.next();
        }

        println("Degree:");
        String d = sc.next();
        while (!isRight("Degree", d)) {
            println("Error_wrong_data");
            println("Please_input_again:");
            d = sc.next();
        }

        println("State:");
        String st = sc.next();
        while (!isRight("State", st)) {
            println("Error_wrong_data");
            println("Please_input_again:");
            st = sc.next();
        }

        println("Number:");
        String num = sc.next();
        while (!isRight("Number", num)) {
            println("Error_wrong_data");
            println("Please_input_again:");
            num = sc.next();
        }

        print("Catalogs:");
        catalogMgr.showCatChoose();
        println("");
        println("Catalog:");
        String cat = sc.next();
        int catindex = Integer.parseInt(cat);
        while (catindex <= 0 && catindex > catalogMgr.getCatSize()) {
            println("Error_wrong_data");
            println("Please_input_again:");
            cat = sc.next();
            catindex = Integer.parseInt(cat);
        }

        println("Work:");
        String w = sc.next();
        while (!isRight("Work", w)) {
            println("Error_wrong_data");
            println("Please_input_again:");
            w = sc.next();
        }

        configMgr.setLastId();
        String f = String.format("%s %s %s %s %s %s %s %s %s", configMgr.getLastId(), n, s, e, d, st, num,
                catalogMgr.getCat(catindex - 1), w);
        Data data = new Data(f);
        dataMgr.add(data);
        println("Add_contact_success");

        String[] codes = { "0", "99" };
        String[] info = { "Go_back_to_main_menu", "Exit_system" };
        showFormat(codes, info);
        String cmd = sc.next();
        while (!checkCmd(codes, cmd)) {
            println("Error_wrong_command");
            println("Please_enter_again:");
            cmd = sc.next();
        }
        switch (cmd) {
            case "0":
                return;
            case "99":
                System.exit(0);
        }
    }

    public static void Add_cat() {
        println("Please_input_new_catalog:");
        String cat = sc.next();
        String catSave = cat.substring(0, 1).toUpperCase() + cat.substring(1).toLowerCase();
        while (!isRight("CAT", catSave)) {
            println("Error_catalog_to_long");
            cat = sc.next();
            catSave = cat.substring(0, 1).toUpperCase() + cat.substring(1).toLowerCase();
        }
        while (catalogMgr.checkCat(catSave)) {
            println("Error_catalog_existed");
            cat = sc.next();
            catSave = cat.substring(0, 1).toUpperCase() + cat.substring(1).toLowerCase();
        }
        catalogMgr.addCat(catSave);
        println("Add_catalog_" + cat + "_success");

        subMenu();
    }

    public static void Show_cat() {
        catalogMgr.showCat();

        subMenu();
    }

    public static void Set_field() {
        configMgr.showConfig();

        println("New_show_name(0/1):");
        String cmd = sc.next();
        while (!cmd.equals("1") && !cmd.equals("0")) {
            println("Input_error_plaese_input_0_or_1:");
            cmd = sc.next();
        }
        if (cmd.equals("1")) {
            configMgr.setShowName("true");
        } else {
            configMgr.setShowName("false");
        }

        println("New_show_start(0/1):");
        cmd = sc.next();
        while (!cmd.equals("1") && !cmd.equals("0")) {
            println("Input_error_plaese_input_0_or_1:");
            cmd = sc.next();
        }
        if (cmd.equals("1")) {
            configMgr.setShowStart("true");
        } else {
            configMgr.setShowStart("false");
        }

        println("New_show_end(0/1):");
        cmd = sc.next();
        while (!cmd.equals("1") && !cmd.equals("0")) {
            println("Input_error_plaese_input_0_or_1:");
            cmd = sc.next();
        }
        if (cmd.equals("1")) {
            configMgr.setShowEnd("true");
        } else {
            configMgr.setShowEnd("false");
        }

        println("New_show_degree(0/1):");
        cmd = sc.next();
        while (!cmd.equals("1") && !cmd.equals("0")) {
            println("Input_error_plaese_input_0_or_1:");
            cmd = sc.next();
        }
        if (cmd.equals("1")) {
            configMgr.setShowDegree("true");
        } else {
            configMgr.setShowDegree("false");
        }

        println("New_show_state(0/1):");
        cmd = sc.next();
        while (!cmd.equals("1") && !cmd.equals("0")) {
            println("Input_error_plaese_input_0_or_1:");
            cmd = sc.next();
        }
        if (cmd.equals("1")) {
            configMgr.setShowState("true");
        } else {
            configMgr.setShowState("false");
        }

        println("New_show_number(0/1):");
        cmd = sc.next();
        while (!cmd.equals("1") && !cmd.equals("0")) {
            println("Input_error_plaese_input_0_or_1:");
            cmd = sc.next();
        }
        if (cmd.equals("1")) {
            configMgr.setShowNumber("true");
        } else {
            configMgr.setShowNumber("false");
        }

        println("New_show_catalog(0/1):");
        cmd = sc.next();
        while (!cmd.equals("1") && !cmd.equals("0")) {
            println("Input_error_plaese_input_0_or_1:");
            cmd = sc.next();
        }
        if (cmd.equals("1")) {
            configMgr.setShowCatalog("true");
        } else {
            configMgr.setShowCatalog("false");
        }

        println("New_show_work(0/1):");
        cmd = sc.next();
        while (!cmd.equals("1") && !cmd.equals("0")) {
            println("Input_error_plaese_input_0_or_1:");
        }
        if (cmd.equals("1")) {
            configMgr.setShowWork("true");
        } else {
            configMgr.setShowWork("false");
        }

        println("");
        configMgr.showConfig();

        subMenu();
    }

    public static void Set_page() {
        println("show_defalt_perpage:" + configMgr.getPerPage());
        println("new_show_defalt_perpage:");
        sc.nextLine();
        String s = sc.nextLine();
        if (s.equals("")) {
            s = configMgr.getPerPage();
        } else {
            while (!s.matches("[0-9]+")) {
                println("Error_wrong_data");
                println("Please_input_again:");
                s = sc.nextLine();
                if (s.equals("")) {
                    s = configMgr.getPerPage();
                    break;
                }
            }
        }
        configMgr.setPerPage(s);
        println("show_defalt_perpage:" + configMgr.getPerPage());

        String[] codes = { "0", "99" };
        String[] info = { "Go_back_to_main_menu", "Exit_system" };
        showFormat(codes, info);
        String cmd = sc.next();
        while (!checkCmd(codes, cmd)) {
            println("Error_wrong_command");
            println("Please_enter_again:");
            cmd = sc.next();
        }
        switch (cmd) {
            case "0":
                return;
            case "99":
                System.exit(0);
        }
    }

    public static void Set_order() {
        println("show_sort_order:" + configMgr.getOrder());

        println("Please_input_new_sort_order:");
        String cmd = sc.next();
        while (!cmd.equals("asc") && !cmd.equals("des")) {
            println("Input_error_plaese_input_asc_or_des:");
            cmd = sc.next();
        }
        if (cmd.equals("asc")) {
            configMgr.setOrder("asc");
        } else {
            configMgr.setOrder("des");
        }

        println("show_sort_order:" + configMgr.getOrder());

        subMenu();
    }

    public static void Set_sort() {
        String[] codes = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String[] info = { "ID", "Name", "Start", "End", "Degree", "State", "Number", "Catalog", "Work" };
        showFormat(codes, info);
        String[] codeStrings = { "0", "99" };
        String[] infoStrings = { "Go_back_to_main_menu", "Exit_system" };
        showFormat(codeStrings, infoStrings);
        String[] code = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "99" };

        String cmd = sc.next();
        while (!checkCmd(code, cmd)) {
            println("Error_wrong_command");
            println("Please_enter_again:");
            cmd = sc.next();
        }
        switch (cmd) {
            case "0":
                return;
            case "99":
                System.exit(0);
        }

        configMgr.setField(info[Integer.parseInt(cmd) - 1]);
        println("Sorted_by:" + configMgr.getField());

        subMenu();
    }

    public static void Show_r() {
        ArrayList<Data> datas = new ArrayList<>();
        for (String s : dataMgr.getRawData()) {
            datas.add(new Data(s));
        }

        showDisplay(datas);

        subMenu();
    }

    public static void Opt() {
        println("Please_confirm_data_optimize_y_or_n:");
        String cmd = sc.next();

        if (cmd.equals("y")) {
            dataMgr.opt(new ConfigMgr());
            println("Data_optimize_success");
        } else {
            println("Data_optimize_denied");
        }

        subMenu();
    }

    public static void Show_acc() {
        accountMgr.showAcc();

        subMenu();
    }

    public static void Add_acc() {
        println("New_account:");
        String acc = sc.next();
        println("New_password:");
        String pw = sc.next();

        accountMgr.addAcc(acc, pw);

        subMenu();
    }

    public static void Del_acc() {
        println("Delete_account:");
        String acc = sc.next();
        while(!accountMgr.checkAcc(acc)){
            println("No_account_please_try_again:");
            acc = sc.next();
        }
        accountMgr.delAcc(acc);
        println("Delete_account_success");

        subMenu();
    }

    public static void Mod_acc() {
        println("Modify_account:");
        String acc = sc.next();
        while(!accountMgr.checkAcc(acc)){
            println("No_account_please_try_again:");
            acc = sc.next();
        }

        println("New_account:");
        String newAcc = sc.next();

        println("New_password:");
        String newPw = sc.next();

        accountMgr.modAcc(acc, newAcc, newPw);
        println("Modify_account_success");

        subMenu();
    }

    public static void Logout() {
        println("Please_confirm_to_logout_y_or_n:");
        String cmd = sc.next();
        while (!cmd.equals("y") && !cmd.equals("n")){
            println("Error_input");
            println("Please_input_again:");
            cmd = sc.next();
        }
        if(cmd.equals("y")){
            login();
        } 
    }

    public static void main(String[] args) {
        login();
        boolean menu = true;
        while (true) {
            if (menu) {
                showMainMenu();
            }
            String cmd = null;
            cmd = sc.next();
            switch (cmd) {
                case "1":
                    Show_a();
                    break;
                case "2":
                    Show_p();
                    break;
                case "3":
                    Show_by_c();
                    break;
                case "4":
                    Search();
                    break;
                case "5":
                    Mod();
                    break;
                case "6":
                    Del();
                    break;
                case "7":
                    Add_job();
                    break;
                case "8":
                    Add_cat();
                    break;
                case "9":
                    Show_cat();
                    break;
                case "10":
                    Set_field();
                    break;
                case "11":
                    Set_page();
                    break;
                case "12":
                    Set_order();
                    break;
                case "13":
                    Set_sort();
                    break;
                case "14":
                    Show_r();
                    break;
                case "15":
                    Opt();
                    break;
                case "16":
                    Show_acc();
                    break;
                case "17":
                    Add_acc();
                    break;
                case "18":
                    Del_acc();
                    break;
                case "19":
                    Mod_acc();
                    break;
                case "20":
                    Logout();
                    break;
                case "99":
                    System.exit(0);
                default:
                    println("Error_wrong_command");
                    println("Please_enter_again:");
                    menu = false;
                    break;
            }
        }
    }

    public static void login() {
        int i;
        for (i = 0; i < 3; i++) {
            println("Account:");
            String acc = sc.next();
            println("Password:");
            String pw = sc.next();
            println("Verify_string:" + configMgr.getVerify());
            println("Input_Verify_string:");
            String vs = sc.next();
            if (accountMgr.login(acc, pw, vs)) {
                println("Login_success");
                break;
            } else {
                println("Error_wrong_account_password_or_verify_string");
            }
        }
        if (i == 3) {
            System.exit(0);
        }
    }

    public static void showMainMenu() {
        println("****************************************");
        println("1.Show_a 2.Show_p 3.Show_by_c 4.Search 5.Mod 6.Del 7.Add_job");
        println("8.Add_cat 9.Show_cat 10.Set_field 11.Set_page 12.Set_order 13.Set_sort");
        println("14.Show_r 15.Opt 16.Show_acc 17.Add_acc 18.Del_acc 19.Mod_acc 20.Logout 99.Exit");
        println("****************************************");
    }

    public static void showFormat(String[] codes, String[] info) {
        for (int i = 0; i < info.length; i++) {
            print("[" + codes[i] + "]." + info[i] + " ");
        }
        System.out.println();
    }

    public static void subMenu(){
        String[] codes = { "0", "99" };
        String[] info = { "Go_back_to_main_menu", "Exit_system" };
        showFormat(codes, info);
        String cmd = sc.next();
        while (!checkCmd(codes, cmd)) {
            println("Error_wrong_command");
            println("Please_enter_again:");
            cmd = sc.next();
        }
        switch (cmd) {
            case "0":
                return;
            case "99":
                System.exit(0);
        }
    }

    public static boolean checkCmd(String[] codes, String cmd) {
        for (int i = 0; i < codes.length; i++) {
            if (codes[i].equals(cmd)) {
                return true;
            }
        }
        return false;
    }

    public static void showDisplay(ArrayList<Data> d) {
        Data datas = new Data("[ID] [Name] [Start] [End] [Degree] [State] [Number] [Catalog] [Work]");
        datas.printDisplay(configMgr.getShowName(), configMgr.getShowStart(), configMgr.getShowEnd(),
                configMgr.getShowDegree(), configMgr.getShowState(), configMgr.getShowNumber(),
                configMgr.getShowCatalog(), configMgr.getShowWork());

        for (Data data : d) {
            data.printDisplay(configMgr.getShowName(), configMgr.getShowStart(), configMgr.getShowEnd(),
                    configMgr.getShowDegree(), configMgr.getShowState(), configMgr.getShowNumber(),
                    configMgr.getShowCatalog(), configMgr.getShowWork());
        }
    }

    public static ArrayList<ArrayList<Data>> perPage(ArrayList<Data> data, int numPage, int page) {
        ArrayList<ArrayList<Data>> res = new ArrayList<>();
        int l = 0;
        for (int i = 0; i < page; i++) {
            ArrayList<Data> temp = new ArrayList<>();
            for (int j = 0; j < numPage; j++) {
                temp.add(data.get(l++));
                if (l == data.size()) {
                    break;
                }
            }
            res.add(temp);
        }

        return res;
    }

    public static boolean isRight(String field, String val) {
        switch (field) {
            case "ID":
                return val.matches("[0-9]+") && val.length() <= 4;
            case "Name":
                return val.matches("[a-zA-Z]+") && val.length() <= 12;
            case "Start":
                return val.matches("([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]");
            case "End":
                return val.matches("([0-1][0-9]|[2][0-3]):[0-5][0-9]:[0-5][0-9]");
            case "Degree":
                return val.matches("[0-9]+") && val.length() <= 8;
            case "State":
                return val.matches("[^0-9]+") && val.length() <= 12;
            case "Number":
                return val.matches("[A-Z][0-9]{5}");
            case "Work":
                return val.matches("[^0-9]+") && val.length() <= 26;
        }
        return true;
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }
}
