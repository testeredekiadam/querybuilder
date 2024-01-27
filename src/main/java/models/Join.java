package models;

public class Join {

    private String Id;
    private String tableName;
    private String onJoin;

    public Join(){
        this.tableName = "";
        this.onJoin = "";
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOnJoin() {
        return onJoin;
    }

    public void setOnJoin(String onJoin) {
        this.onJoin = onJoin;
    }
}
