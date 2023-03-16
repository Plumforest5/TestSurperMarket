package TestSuperMarket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 主界面的按钮事件监听
public class AddViewHandler implements ActionListener {
        private AddGoodsView addGoodsView;
        private MainView mainView;

        public AddViewHandler(AddGoodsView addGoodsView, MainView mainView) {
            this.addGoodsView = addGoodsView;
            this.mainView = mainView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String text = jButton.getText();
            if ("添加".equals(text)) {
                GoodsService goodsService = new GoodsServiceImpl();
                GoodsDO goodsDO = addGoodsView.buildGoodsDO();//获取到当前的学生对象
                boolean addResult = goodsService.add(goodsDO);
                if (addResult){
                    //重新加载table
                    mainView.reloadTable();
                    JOptionPane.showMessageDialog(addGoodsView,"添加成功");
                    addGoodsView.dispose();
                } else {
                    JOptionPane.showMessageDialog(addGoodsView,"添加失败");
                }
            }
        }



}





