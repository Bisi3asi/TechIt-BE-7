package com.ll.domain.wise;

import java.sql.*;
import java.util.List;

public class WiseJDBC {
    public static Connection connection;

    public static void execute() {
        try {
            // JDBC 설정
            String url = "jdbc:mysql://localhost:3306/wisedb";
            String username = "root";
            String password = "lldj123414";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean saveWise(List<Wise> wiseList) {
        int rowsAffected = 0;

        try {
            for (Wise wise : wiseList) {
                int id = wise.getId();
                String content = wise.getContent();
                String author = wise.getAuthor();
                Statement statement = connection.createStatement();
                rowsAffected = statement.executeUpdate("INSERT INTO wise" +
                        "(id, createDate, content, author)" +
                        " VALUES (" + id + " , NOW(), '" + content + "' , '" + author + "')");
            }
            if (rowsAffected > 0) {
                System.out.println("데이터 저장에 성공했습니다");
                return true;
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean readWise(List<Wise> wiseList) {
        execute();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM wise");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                String author = resultSet.getString("author");
                wiseList.add(new Wise(id, content, author));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    void saveWise() throws Exception {
//        // sql 실행
//        String sql = "SELECT empno, ename, sal from emp";
//        //sql 문을 실행해줄 객체의 참조값 얻어오기
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        //query문 수행하고 결과셋 객체 얻어오기
//        ResultSet rs = pstmt.executeQuery();
//
//        while (rs.next()) {
//            //empno라는 칼럼을 정수로 얻어내기
//            int empno = rs.getInt("empno");
//            String ename = rs.getString("ename");
//            int sal = rs.getInt("sal");
//
//            System.out.println(+empno + "  |  " + ename + "  |  " + sal);
//        }
//
//    } catch(
//    Exception e)
//
//    {
//        e.printStackTrace();
//    }
//}
}
