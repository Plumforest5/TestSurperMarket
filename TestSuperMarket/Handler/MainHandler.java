package TestSuperMarket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 主界面的按钮事件监听
public class MainHandler implements ActionListener {
        private MainView mainView;

        public MainHandler(MainView mainView) {
            this.mainView = mainView;
        }

    @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String text = jButton.getText();
            if ("增加".equals(text)) {
                new AddGoodsView(mainView);
            } else if ("修改".equals(text)) {
                int[] selectGoodsIds = mainView.getSelectGoodsIds();
                if (selectGoodsIds.length != 1){
                    JOptionPane.showMessageDialog(mainView,"对不起，一次只能修改一条数据");
                    return;
                }
                new UpdateGoodsView(mainView,selectGoodsIds[0]);
            } else if ("删除".equals(text)){
                int[] selectGoodsIds = mainView.getSelectGoodsIds();
                if (selectGoodsIds.length == 0) {
                    JOptionPane.showMessageDialog(mainView, "请选择需要删除的记录");
                    return;
                }
                int option = JOptionPane.showConfirmDialog(mainView, "是否确认删除选中的" +
                                selectGoodsIds.length + "条记录吗？", "确认删除",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_NO_OPTION){
                    GoodsService goodsService = new GoodsServiceImpl();
                    boolean deleteRs = goodsService.delete(selectGoodsIds);
                    if (deleteRs){
                        mainView.reloadTable();  //重新加载表格
                    }else {
                        JOptionPane.showMessageDialog(mainView,"删除失败");
                    }
                }
            } else if ("查找".equals(text)){
                mainView.setPageNow(1);
                // 引入重新加载方法
                mainView.reloadTable();
            } else if ("上一页".equals(text)){
                mainView.setPageNow(mainView.getPageNow() - 1);
                mainView.reloadTable();
            } else if ("下一页".equals(text)){
                mainView.setPageNow(mainView.getPageNow() + 1);
                mainView.reloadTable();
            }
        }



}





