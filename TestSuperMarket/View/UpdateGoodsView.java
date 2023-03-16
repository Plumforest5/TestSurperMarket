package TestSuperMarket;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

//JDialog 窗体的功能是从一个窗体中弹出另一个窗体,消息对话框
public class UpdateGoodsView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel idLable = new JLabel("商品编号:",JLabel.RIGHT);
    JTextField idTxt = new JTextField();
    JLabel nameLable = new JLabel("商品名称",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel priceLable = new JLabel("商品价格:",JLabel.RIGHT);
    JTextField priceTxt = new JTextField();
    JLabel kucunLable = new JLabel("库存:",JLabel.RIGHT);
    JTextField kucunTxt = new JTextField();
    JLabel daysLable = new JLabel("保质期:",JLabel.RIGHT);
    JTextField daysTxt = new JTextField();
    JLabel formLable = new JLabel("供应商:",JLabel.RIGHT);
    JTextField formTxt = new JTextField();
    JLabel dateLable = new JLabel("进货日期:",JLabel.RIGHT);
    JTextField dateTxt = new JTextField();
    JLabel humanLable = new JLabel("操作者:",JLabel.RIGHT);
    JTextField humanTxt = new JTextField();

    UpdateViewHandler updateViewHandler;

    public UpdateGoodsView(MainView mainView, int selectGoodsId){ //依赖于MainView，从该窗体上弹出的
        super(mainView,"修改商品",true); //传入三个参数，依赖，标题，是否为模态

        updateViewHandler = new UpdateViewHandler(this,mainView);

        //查询selectGoodsId对应的记录
        GoodsService goodsService = new GoodsServiceImpl();
        GoodsDO selectGoods = goodsService.getById(selectGoodsId);

        idLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLable);
        idTxt.setPreferredSize(new Dimension(200,30));
        idTxt.setText(selectGoods.getId() + "");
        idTxt.setEnabled(false);  //设置商品id不可编辑
        jPanel.add(idTxt);

        nameLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLable);
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameTxt.setText(selectGoods.getGoodsName());
        jPanel.add(nameTxt);

        priceLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(priceLable);
        priceTxt.setPreferredSize(new Dimension(200,30));
        priceTxt.setText(String.valueOf(selectGoods.getPrice()));
        jPanel.add(priceTxt);

        kucunLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(kucunLable);
        kucunTxt.setPreferredSize(new Dimension(200,30));
        kucunTxt.setText(String.valueOf(selectGoods.getKucun()));
        jPanel.add(kucunTxt);

        daysLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(daysLable);
        daysTxt.setPreferredSize(new Dimension(200,30));
        daysTxt.setText(String.valueOf(selectGoods.getDays()));
        jPanel.add(daysTxt);

        formLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(formLable);
        formTxt.setPreferredSize(new Dimension(200,30));
        formTxt.setText(String.valueOf(selectGoods.getForms()));
        jPanel.add(formTxt);

        dateLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(dateLable);
        dateTxt.setPreferredSize(new Dimension(200,30));
        dateTxt.setText(String.valueOf(selectGoods.getDate()));
        jPanel.add(dateTxt);

        humanLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(humanLable);
        humanTxt.setPreferredSize(new Dimension(200,30));
        humanTxt.setText(String.valueOf(selectGoods.getHuman()));
        jPanel.add(humanTxt);

        JButton updateBtn = new JButton("修改");
        updateBtn.addActionListener(updateViewHandler);
        jPanel.add(updateBtn);

        //把jpanel加入到内容面板中，Container默认就是边界布局
        Container container = getContentPane();
        container.add(jPanel);

        setSize(350,500);
        setLocationRelativeTo(null);
        // DISPOSE_ON_CLOSE在关闭时只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }


    //获取修改以后的商品对象
    public GoodsDO buildUpdateGoodsDO() {
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setId(Integer.valueOf(idTxt.getText().trim()));
        goodsDO.setGoodsName(nameTxt.getText().trim());
        goodsDO.setPrice(Double.valueOf(priceTxt.getText().trim()));
        goodsDO.setKucun(Integer.parseInt(kucunTxt.getText().trim()));
        goodsDO.setDays(Integer.parseInt(daysTxt.getText().trim()));
        goodsDO.setForms(formTxt.getText().trim());
        goodsDO.setDate(Date.valueOf(dateTxt.getText().trim()));
        goodsDO.setHuman(humanTxt.getText().trim());

        return goodsDO;
    }
}
