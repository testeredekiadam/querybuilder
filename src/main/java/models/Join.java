package models;

public class Join {

    private String Id;
    private StringBuilder tableName;
    private StringBuilder onJoin;

    public Join(){
        this.tableName = new StringBuilder();
        this.onJoin = new StringBuilder();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public StringBuilder getTableName() {
        return tableName;
    }

    public void setTableName(StringBuilder tableName) {
        this.tableName = tableName;
    }

    public StringBuilder getOnJoin() {
        return onJoin;
    }

    public void setOnJoin(StringBuilder onJoin) {
        this.onJoin = onJoin;
    }
}
