package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyGreenDaoClass {

    public static void main(String[] args){
        Schema schema = new Schema(1,"com.example.dllo.mirror.model.db");
        addNote(schema);


        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addNote(Schema schema){

        //添加表名
        Entity entity = schema.addEntity("Users");
        //加入id
        //并且id自增
        entity.addIdProperty().autoincrement().primaryKey();
        //添加类属性根据属性生成相应表中的字段
        entity.addStringProperty("uid");
        entity.addStringProperty("token");

    }




}


