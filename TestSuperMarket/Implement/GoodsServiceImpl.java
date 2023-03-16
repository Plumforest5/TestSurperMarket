package TestSuperMarket;

import java.sql.*;
import java.util.Vector;

public class GoodsServiceImpl implements GoodsService{
//实现类重写
    @Override
    public TableDTO referGoods(GoodsRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from goods ");
        if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
            sql.append("where gname like '%"+request.getSearchKey().trim()+"%'"); //按照gname来查找
        }
        sql.append("order by gno desc limit ").append(request.getStart()).append(",")
                .append(request.getPageSize());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();

        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //查询记录
            returnDTO.setData(fillData(rs)); //抽取了方法

            sql.setLength(0);
            sql.append("select count(*) from goods ");
            if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())){
                sql.append("where gname like '%"+request.getSearchKey().trim()+"%'");
            }

            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closeRS(rs);
            DBUtil.closePS(ps);
            DBUtil.closeConn(conn);
        }

        return null;
    }

    //实现添加商品--》数据库
    @Override
    public boolean add(GoodsDO goodsDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into goods(gname,gprice,gcont,gdays,gform,genterdate,ghuman) ");
        sql.append("values(?,?,?,?,?,?,?) ");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());

            ps.setString(1,goodsDO.getGoodsName());
            ps.setDouble(2,goodsDO.getPrice());
            ps.setInt(3,goodsDO.getKucun());
            ps.setInt(4,goodsDO.getDays());
            ps.setString(5,goodsDO.getForms());
            ps.setDate(6,goodsDO.getDate());
            ps.setString(7,goodsDO.getHuman());
            return ps.executeUpdate() == 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closePS(ps);
            DBUtil.closeConn(conn);
        }

        return false;
    }

    //重写查找记录方法以便于修改
    @Override
    public GoodsDO getById(int selectGoodsId) {
        StringBuilder sql = new StringBuilder("select * from goods where gno = ? ");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GoodsDO goodsDO = new GoodsDO();

        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,selectGoodsId);
            rs = ps.executeQuery();

            while (rs.next()){  //处理查出的每一条记录
                int id = rs.getInt("gno");  //商品编号
                String name = rs.getString("gname");    //商品名称
                double gprice = rs.getDouble("gprice");    //价格
                int gcont = rs.getInt("gcont");   //库存
                int gdays = rs.getInt("gdays");   //保质期
                String gform = rs.getString("gform");   //供应商
                Date date = rs.getDate("genterdate");   //进货日期
                String ghuman = rs.getString("ghuman");    //操作者
                goodsDO.setId(id);
                goodsDO.setGoodsName(name);
                goodsDO.setPrice(gprice);
                goodsDO.setKucun(gcont);
                goodsDO.setDays(gdays);
                goodsDO.setForms(gform);
                goodsDO.setDate(date);
                goodsDO.setHuman(ghuman);
            }
            return goodsDO;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closeRS(rs);
            DBUtil.closePS(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    //重写修改的实现方法
    @Override
    public boolean update(GoodsDO goodsDO) {
        StringBuilder sql = new StringBuilder();
        sql.append("update goods set gname=?,gprice=?,gcont=?,gdays=?,gform=?,genterdate=?,ghuman=? ");
        sql.append("where gno =? ");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());

            ps.setString(1,goodsDO.getGoodsName());
            ps.setDouble(2,goodsDO.getPrice());
            ps.setInt(3,goodsDO.getKucun());
            ps.setInt(4,goodsDO.getDays());
            ps.setString(5,goodsDO.getForms());
            ps.setDate(6,goodsDO.getDate());
            ps.setString(7,goodsDO.getHuman());
            ps.setInt(8,goodsDO.getId());
            return ps.executeUpdate() == 1;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closePS(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    //重写删除的方法
    @Override
    public boolean delete(int[] selectGoodsIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from goods where gno in ( ");
        int last = selectGoodsIds.length;
        for (int i = 0; i < last; i++) {
            if (i == (last-1)){
                sql.append(" ? ");
            }else {
                sql.append(" ?, ");
            }
        }
        sql.append(") ");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());

            for (int i = 0; i < last; i++) {
                ps.setInt(i + 1, selectGoodsIds[i]);
            }
            return ps.executeUpdate() == last;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closePS(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()){  //处理查出的每一条记录
            Vector<Object> record = new Vector<>();
            int id = rs.getInt("gno");  //商品编号
            String name = rs.getString("gname");    //商品名称
            double gprice = rs.getDouble("gprice");    //价格
            int gcont = rs.getInt("gcont");   //库存
            int gdays = rs.getInt("gdays");   //保质期
            String gform = rs.getString("gform");   //供应商
            Date date = rs.getDate("genterdate");   //进货日期
            String ghuman = rs.getString("ghuman");    //操作者

            record.addElement(id);
            record.addElement(name);
            record.addElement(gprice);
            record.addElement(gcont);
            record.addElement(gdays);
            record.addElement(gform);
            record.addElement(date);
            record.addElement(ghuman);

            data.addElement(record);
        }
        return data;
    }
}
