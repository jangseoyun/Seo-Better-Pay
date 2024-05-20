package com.yun.utillity;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Random;

public class DummyMemberDataGenerator {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/seo-better-pay?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "mysqluser";
    private static final String DB_PW = "mysqlpw@!";

    private static final String[] ADDRESSES = {"강남구", "관악구", "서초구"};

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
                = "INSERT INTO t_membership (membership_id, membership_pw, membership_email, name, address, is_valid, is_wallet_available, created_at, modified_at, deleted_at) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Random random = new Random();

        //데이터 생성을 위한 preparedStatement 준비
        PreparedStatement pstmt = conn.prepareStatement(insertQuery);

        //데미 데이터 개수
        int numberOfDummyData = 1000;

        for (int i = 1; i <= numberOfDummyData; i++) {
            pstmt.setString(1, "id" + i);
            pstmt.setString(2, "pw@" + i);
            pstmt.setString(3, "email_" + i + "@email.com");
            pstmt.setString(4, "User " + i);
            pstmt.setString(5, ADDRESSES[random.nextInt(ADDRESSES.length)]);
            pstmt.setBoolean(6, random.nextBoolean());
            pstmt.setBoolean(7, random.nextBoolean());
            pstmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setTimestamp(10, null);

            pstmt.executeUpdate();
        }

        pstmt.close();

    }

}
