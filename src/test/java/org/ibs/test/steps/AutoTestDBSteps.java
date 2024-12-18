package org.ibs.test.steps;

import driver.RemoteDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.И;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class AutoTestDBSteps extends RemoteDriver {

    private Statement statement;


    @Before
    public void link() throws MalformedURLException {
        super.link();
    }

    @After
    public void close() {
        super.close();
    }


   @И("подключение к БД")
    public void test() throws SQLException {
        Connection connection = getConnection(
                "jdbc:h2:tcp://qualit.applineselenoid.fvds.ru/mem:testdb",
                "user",
                "pass");
        Statement statement = connection.createStatement();

    }

@И("проверен список БД")
        public void исходная_БД () throws SQLException {

    Connection connection = getConnection(
            "jdbc:h2:tcp://qualit.applineselenoid.fvds.ru/mem:testdb",
            "user",
            "pass");
    Statement statement = connection.createStatement();


    String all = "Select * FROM FOOD";
    ResultSet rs = statement.executeQuery(all);
    while (rs.next()) {
        int FOOD_ID = rs.getInt("FOOD_ID");
        String FOOD_NAME = rs.getString("FOOD_NAME");
        String FOOD_TYPE = rs.getString("FOOD_TYPE");
        int FOOD_EXOTIC = rs.getInt("FOOD_EXOTIC");
        System.out.printf("%d, %s, %s, %d%n", FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC);

    }
}


    @И("добавление уже существующего товара в список БД")
    public void добавить_товар1 () throws SQLException {

        Connection connection = getConnection(
                "jdbc:h2:tcp://qualit.applineselenoid.fvds.ru/mem:testdb",
                "user",
                "pass");
        Statement statement = connection.createStatement();


        statement.executeUpdate("INSERT INTO FOOD " +
                "VALUES ('5', 'Апельсин', 'FRUIT', '1')");

        String all1 = "Select * FROM FOOD";
        ResultSet rs1 = statement.executeQuery(all1);
        while (rs1.next()) {
            int FOOD_ID1 = rs1.getInt("FOOD_ID");
            String FOOD_NAME1 = rs1.getString("FOOD_NAME");
            String FOOD_TYPE1 = rs1.getString("FOOD_TYPE");
            int FOOD_EXOTIC1 = rs1.getInt("FOOD_EXOTIC");
            System.out.printf("%d, %s, %s, %d%n", FOOD_ID1, FOOD_NAME1, FOOD_TYPE1, FOOD_EXOTIC1);
        }
    }


@И("удаление товара из списка в БД")
public void удалить_товар () throws SQLException {

    Connection connection = getConnection(
            "jdbc:h2:tcp://qualit.applineselenoid.fvds.ru/mem:testdb",
            "user",
            "pass");
    Statement statement = connection.createStatement();

        System.out.println("БД после удаления товара:");
        statement.executeUpdate("DELETE FROM FOOD WHERE FOOD_ID = '5'");

        String all2 = "Select * FROM FOOD";
        ResultSet rs2 = statement.executeQuery(all2);
        while (rs2.next()) {
            int FOOD_ID2 = rs2.getInt("FOOD_ID");
            String FOOD_NAME2 = rs2.getString("FOOD_NAME");
            String FOOD_TYPE2 = rs2.getString("FOOD_TYPE");
            int FOOD_EXOTIC2 = rs2.getInt("FOOD_EXOTIC");
            System.out.printf("%d, %s, %s, %d%n", FOOD_ID2, FOOD_NAME2, FOOD_TYPE2, FOOD_EXOTIC2);

        }
    }
}
