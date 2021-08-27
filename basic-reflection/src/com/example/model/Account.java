package com.example.model;

import com.example.util.Documented;
import com.example.util.PersistentModel;

@PersistentModel(tableName = "TBL_ACCOUNT")
@Documented
public class Account extends BaseModel {

    public int accountId;

    private String accountName;

    private Account(int accountId, String accountName) {
        this();
        this.accountId = accountId;
        this.accountName = accountName;
    }

    public Account() {
        this.init();
    }

    public String stringifyNameAndId() {
        return this.accountId + ", " + this.accountName;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    private void init() {
        this.accountId = -1;
        this.accountName = "N/A";
    }
}
