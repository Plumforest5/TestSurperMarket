package TestSuperMarket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 接口的实现类-->重写校验方法
public class AdminImpl implements AdminService{
    @Override
    public boolean verifyAdmin(AdminDO adminDO) {
        String userName = adminDO.getUserName();
        String userPwd = adminDO.getUserPwd();

        String sql = "select upwd from t_usermess where uname = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtil.getConn();
            if (conn == null){
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                String pwd = resultSet.getString(1);
                if (userPwd.equals(pwd)){
                    return true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeRS(resultSet);
            DBUtil.closePS(ps);
            DBUtil.closeConn(conn);
        }

        return false;
    }
}
