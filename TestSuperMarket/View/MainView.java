package TestSuperMarket;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    //使用的是流式布局管理器，方便我们在界面上将各个组件放置不同方位，可以让组件按照设置的对齐方式进行排列

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查找");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    MainViewTable mainViewTable = new MainViewTable();
    MainHandler mainHandler;

    private int pageNow = 1;    //当前页码
    private int pageSize = 0;  //一页显示的数据库记录

    public MainView(){
        super("Java超市管理主界面");
         /*
        Container容器分为两种类型，分别是 Window 和 Panel；
        JPanel:面板组件，非顶层容器。不能单独存在，只能存在于其他容器（Window 或其子类）中
         */
        Container container = getContentPane();
        Rectangle bounds = UtilSize.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);

        mainHandler = new MainHandler(this);

        //放置北面的组件
        layoutNorth(container);
        //放置中间的组件
        layoutCenter(container);
        //放置南面的组件
        layoutSouth(container);

        //设置窗口大小为充满屏幕

        setBounds(bounds);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true); // 设置可以改变窗体大小
        setVisible(true);
    }

    private void layoutCenter(Container container) {
        TableDTO dto = getTableDTO();

        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(dto.getData());
        // 将 jtable和 model关联
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);   //滚动面板
        container.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDTO() {
        GoodsService goodsService = new GoodsServiceImpl();  //接口等于实现类，从数据库里面读取数据
        GoodsRequest request = new GoodsRequest();   //获取页码和查询对象的setget方法
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = goodsService.referGoods(request);   //数据传输对象来接收获取到的数据
        return tableDTO;
    }

    private void layoutSouth(Container container) {
        preBtn.addActionListener(mainHandler);
        nextBtn.addActionListener(mainHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        container.add(southPanel,BorderLayout.SOUTH);
    }

    private void showPreNext(int totalCount) {
        int pageSum = 0;    //总页数
        if (pageNow == 1){
            preBtn.setVisible(false);
        } else {
            preBtn.setVisible(true);
        }
        if (totalCount % pageSize == 0){
            pageSum = totalCount / pageSize;
        } else {
            pageSum = totalCount / pageSize + 1;
        }
        if (pageNow == pageSum){
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
    }

    private void layoutNorth(Container container) {
        // 增加查询事件监听
        addBtn.addActionListener(mainHandler);
        delBtn.addActionListener(mainHandler);
        updateBtn.addActionListener(mainHandler);
        searchBtn.addActionListener(mainHandler);

        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        container.add(northPanel,BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new MainView();
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void reloadTable() {
        TableDTO dto = getTableDTO();

        MainViewTableModel.updateModel(dto.getData());   //把数据重新加载
        mainViewTable.renderRule();     //重新调用table里面的渲染表格方法
        showPreNext(dto.getTotalCount());
    }

    //获取选中的表中的条数
    public int[] getSelectGoodsIds() {
        int[] selectedRows = mainViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object idObj = mainViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }


}
