package TestSuperMarket;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {
    public MainViewTable(){
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);
        // 设置表格体
        setFont(new Font(null,Font.PLAIN,14));
        setBackground(Color.black);     //单元格背景颜色
        setGridColor(Color.black);  //表格网格颜色
        setRowHeight(30);
        // 设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public void renderRule(){
        // 设置表格的渲染方式
        Vector<String> columns = MainViewTableModel.getColumns();
        MainViewCellRender render = new MainViewCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            if (i == 0){
                column.setPreferredWidth(50); // 设置列宽
                column.setResizable(false); // 设置第一列不可改变宽度
            }
        }
    }



}
