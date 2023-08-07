package com.csir.vehiclerequisition.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

//org.hibernate.dialect.MySQL5Dialect
public class CustomMysqlDialect extends MySQL8Dialect {

public CustomMysqlDialect() {
    super();
    // register custom/inner function here
    this.registerFunction("group_concat", new SQLFunctionTemplate(StandardBasicTypes.STRING, "group_concat(?1,\"###\")"));
    this.registerFunction("count", new SQLFunctionTemplate(StandardBasicTypes.STRING, "count(?1)"));
    this.registerFunction("sum", new SQLFunctionTemplate(StandardBasicTypes.STRING, "sum(?1)"));
    this.registerKeyword("union all");
    this.registerKeyword("interval");
    this.registerKeyword("day");
    this.registerKeyword("In");
    this.registerKeyword("cast");
    this.registerKeyword("date");
    this.registerKeyword("datetime");
    this.registerKeyword("limit");

}
} 