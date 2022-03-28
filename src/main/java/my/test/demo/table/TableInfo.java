package my.test.demo.table;

public class TableInfo {
    String name;
    String position;
    String office;

    public TableInfo(String name, String position, String office) {
        this.name = name;
        this.position = position;
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getOffice() {
        return office;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableInfo tableInfo = (TableInfo) o;

        if (!name.equals(tableInfo.name)) return false;
        if (!position.equals(tableInfo.position)) return false;
        return office.equals(tableInfo.office);
    }
}
