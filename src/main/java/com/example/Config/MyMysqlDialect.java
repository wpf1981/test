package com.example.Config;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class MyMysqlDialect extends MySQLDialect {

    public MyMysqlDialect() {
        super();
        registerFunction("GROUP_CONCAT", new StandardSQLFunction("GROUP_CONCAT"));
    }
    //此处是解决创建表报错问题
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";

    }
}
