package paf.assessment.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Account {

    private String accountId;
    private String name;
    private Double balance;

    //Getters
    public String getAccountId() {
        return accountId;
    }
    public String getName() {
        return name;
    }
    public Double getBalance() {
        return balance;
    }
    
    //Setters
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    //Create Account object from RowSet Object
    public static Account createAccount(SqlRowSet rs) {
        Account a = new Account();
        a.setAccountId(rs.getString("account_id"));
        a.setName(rs.getString("name"));
        a.setBalance(rs.getDouble("balance"));
        return a;
    }
    
}