package TestSuperMarket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 主界面的按钮事件监听
public class UpdateViewHandler implements ActionListener {
        private UpdateGoodsView updateGoodsView;
        private MainView mainView;

        public UpdateViewHandler(UpdateGoodsView updateGoodsView, MainView mainView) {
            this.updateGoodsView = updateGoodsView;
            this.mainView = mainView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String text = jButton.getText();
            if ("修改".equals(text)) {
                GoodsService goodsService = new GoodsServiceImpl();
                GoodsDO goodsDO = updateGoodsView.buildUpdateGoodsDO();//获取到当前的学生对象
                boolean updateResult = goodsService.update(goodsDO);
                if (updateResult){
                    //重新加载table
                    mainView.reloadTable();
                    JOptionPane.showMessageDialog(updateGoodsView,"修改成功");
                    updateGoodsView.dispose();
                } else {
                    JOptionPane.showMessageDialog(updateGoodsView,"修改失败");
                }
            }
        }



}





