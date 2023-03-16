package TestSuperMarket;

import java.sql.*;
/*
获取MySQL连接的工具类
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/mytestdb";
    //JDBC4.0之后,每个驱动jar包中,在META-INF/services目录下提供了一个名为java.sql.Driver的文件;
    //private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PWD = "root";

//    static {
//        try {
//            Class.forName(DRIVER); //加载MySQL驱动
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConn(){   //获取连接
        try {
            return DriverManager.getConnection(URL,USER_NAME,PWD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void closeConn(Connection connection){   // 关闭连接
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closePS(PreparedStatement ps){
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeRS(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
