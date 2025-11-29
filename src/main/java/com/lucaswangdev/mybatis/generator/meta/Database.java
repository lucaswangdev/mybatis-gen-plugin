package com.lucaswangdev.mybatis.generator.meta;

import java.util.ArrayList;
import java.util.List;


public class Database {
    private List<Table> tables = new ArrayList<>();

    public List<Table> getTables() {
        return tables;
    }

    public void addTable(Table table){
        tables.add(table);
    }
}
