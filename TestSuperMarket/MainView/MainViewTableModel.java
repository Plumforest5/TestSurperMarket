package TestSuperMarket;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("商品编号");
        columns.addElement("商品名称");
        columns.addElement("价格(元)");
        columns.addElement("库存");
        columns.addElement("保质期(天)");
        columns.addElement("供应商");
        columns.addElement("进货日期");
        columns.addElement("操作者");
    }

    private MainViewTableModel(){
        super(null,columns);
    }

    private static MainViewTableModel mainViewTableModel = new MainViewTableModel();

    public static MainViewTableModel assembleModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,columns);
        return mainViewTableModel;
    }

    // 更新数据方法
    public static void updateModel(Vector<Vector<Object>> data){
        mainViewTableModel.setDataVector(data,columns);
    }

    public static Vector<String> getColumns(){
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
