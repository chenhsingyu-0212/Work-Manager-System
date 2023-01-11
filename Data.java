import java.util.Comparator;

public class Data {
    String id, name, start, end, degree, state, number, catalog, work;

    public Data(String s) {
        String[] str = s.split(" ");
        try {
            id = String.format("%04d", Integer.parseInt(str[0]));
        } catch (Exception e) {
            // TODO: handle exception
            id = str[0];
        }
        name = str[1];
        start = str[2];
        end = str[3];
        degree = str[4];
        state = str[5];
        number = str[6];
        catalog = str[7];
        work = str[8];
    }

    public String toString() {
        String s = String.format("%d %s %s %s %s %s %s %s %s", Integer.parseInt(id), name, start, end, degree, state,
                number, catalog, work);
        return s;
    }

    public void printDisplay(String n, String s, String e, String d, String st, String num,
            String cat, String w) {
        System.out.printf("%-4s ", id);
        if (n.equals("true")) {
            System.out.printf("%-12s ", name);
        }
        if (s.equals("true")) {
            System.out.printf("%-8s ", start);
        }
        if (e.equals("true")) {
            System.out.printf("%-8s ", end);
        }
        if (d.equals("true")) {
            System.out.printf("%-8s ", degree);
        }
        if (st.equals("true")) {
            System.out.printf("%-12s ", state);
        }
        if (num.equals("true")) {
            System.out.printf("%-8s ", number);
        }
        if (cat.equals("true")) {
            System.out.printf("%-12s ", catalog);
        }
        if (w.equals("true")) {
            System.out.printf("%-26s ", work);
        }
        System.out.println();
    }

    public boolean isTarget(int field, String val) {
        switch (field) {
            case 1:
                val = String.format("%04d", Integer.parseInt(val));
                return id.equals(val);
            case 2:
                return name.equals(val);
            case 3:
                return start.equals(val);
            case 4:
                return end.equals(val);
            case 5:
                return degree.equals(val);
            case 6:
                return state.equals(val);
            case 7:
                return number.equals(val);
            case 8:
                return catalog.equals(val);
            case 9:
                return work.equals(val);
            default:
                return false;
        }
    }

    public boolean isCat(String val) {
        return catalog.equals(val);
    }

    public static Comparator<Data> getComparator(String field, String order) {
        field = field.toLowerCase();
        order = order.toLowerCase();

        if (field.equals("id")) {
            if (order.equals("asc")) {
                return ID_ASC;
            }
            return ID_DES;
        }

        if (field.equals("name")) {
            if (order.equals("asc")) {
                return Name_ASC;
            }
            return Name_DES;
        }

        if (field.equals("start")) {
            if (order.equals("asc")) {
                return Start_ASC;
            }
            return Start_DES;
        }

        if (field.equals("end")) {
            if (order.equals("asc")) {
                return End_ASC;
            }
            return End_DES;
        }

        if (field.equals("degree")) {
            if (order.equals("asc")) {
                return Degree_ASC;
            }
            return Degree_DES;
        }

        if (field.equals("state")) {
            if (order.equals("asc")) {
                return State_ASC;
            }
            return State_DES;
        }

        if (field.equals("number")) {
            if (order.equals("asc")) {
                return Num_ASC;
            }
            return Num_DES;
        }

        if (field.equals("catalog")) {
            if (order.equals("asc")) {
                return Cat_ASC;
            }
            return Cat_DES;
        }

        if (order.equals("asc")) {
            return Work_ASC;
        }
        return Work_DES;
    }

    public static Comparator<Data> ID_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.id.compareTo(c2.id);
        }
    };
    public static Comparator<Data> ID_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.id.compareTo(c1.id);
        }
    };

    public static Comparator<Data> Name_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.name.compareTo(c2.name);
        }
    };
    public static Comparator<Data> Name_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.name.compareTo(c1.name);
        }
    };

    public static Comparator<Data> Start_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.start.compareTo(c2.start);
        }
    };
    public static Comparator<Data> Start_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.start.compareTo(c1.start);
        }
    };

    public static Comparator<Data> End_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.end.compareTo(c2.end);
        }
    };
    public static Comparator<Data> End_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.end.compareTo(c1.end);
        }
    };

    public static Comparator<Data> Degree_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.degree.compareTo(c2.degree);
        }
    };
    public static Comparator<Data> Degree_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.degree.compareTo(c1.degree);
        }
    };

    public static Comparator<Data> State_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.state.compareTo(c2.state);
        }
    };
    public static Comparator<Data> State_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.state.compareTo(c1.state);
        }
    };

    public static Comparator<Data> Num_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.number.compareTo(c2.number);
        }
    };
    public static Comparator<Data> Num_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.number.compareTo(c1.number);
        }
    };

    public static Comparator<Data> Cat_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.catalog.compareTo(c2.catalog);
        }
    };
    public static Comparator<Data> Cat_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.catalog.compareTo(c1.catalog);
        }
    };

    public static Comparator<Data> Work_ASC = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c1.work.compareTo(c2.work);
        }
    };
    public static Comparator<Data> Work_DES = new Comparator<Data>() {
        public int compare(Data c1, Data c2) {
            return c2.work.compareTo(c1.work);
        }
    };
}
