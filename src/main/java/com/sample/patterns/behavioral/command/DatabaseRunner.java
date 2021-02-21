package com.sample.patterns.behavioral.command;

public class DatabaseRunner {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        Developer developer = new Developer(
                new InsertCommand(dataBase),
                new UpdateCommand(dataBase),
                new SelectCommand(dataBase),
                new DeleteCommand(dataBase)
        );

        developer.insertRecord();
        developer.updateRecord();
        developer.selectRecord();
        developer.deleteRecord();
    }
}
