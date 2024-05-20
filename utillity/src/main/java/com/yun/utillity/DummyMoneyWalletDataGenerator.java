package com.yun.utillity;

import java.sql.*;
import java.util.Random;
import java.util.UUID;

public class DummyMoneyWalletDataGenerator {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/seo-better-pay?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "mysqluser";
    private static final String DB_PW = "mysqlpw@!";
    private static Random RANDOM = new Random();

    public static void main(String[] args) {
        try {
            //JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            //데이터베이스 연결
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
            //더미 데이터 생성
            generateDummyData(connection);

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateDummyData(Connection conn) throws SQLException {
        String insertQuery
                = "insert into t_member_money_wallet (membership_id, money_aggregate_identifier, money_total_amount) values (?, ?, ?);";

        //데이터 생성을 위한 preparedStatement 준비
        PreparedStatement pstmt = conn.prepareStatement(insertQuery);

        //데미 데이터 개수
        int numberOfDummyData = 1000;
        for (int i = 1; i <= numberOfDummyData; i++) {
            pstmt.setString(1, "id" + i);
            pstmt.setString(2, UUID.randomUUID().toString());
            pstmt.setString(3, String.valueOf(RANDOM.nextInt(100, 500)));

            pstmt.executeUpdate();
        }

        pstmt.close();

    }

}
