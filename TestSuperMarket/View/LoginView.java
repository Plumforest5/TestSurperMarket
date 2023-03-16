package TestSuperMarket;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("Java超市管理系统",JLabel.CENTER);

    //使用弹簧布局
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel userPwdLabel = new JLabel("密码:");
    JPasswordField pwdTxt = new JPasswordField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");
    LoginHandler loginHandler;

    public LoginView(){
        super("Java超市管理系统");

        loginHandler = new LoginHandler(this);
        Container container = getContentPane();

        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));
        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,30));
        userPwdLabel.setFont(centerFont);
        pwdTxt.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);

        //把组件加入到面板中
        centerPanel.add(userNameLabel);
        centerPanel.add(userPwdLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdTxt);
        loginBtn.addActionListener(loginHandler); //添加点击事件
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler); //添加点击事件
        centerPanel.add(resetBtn);

        //弹簧布局
        // userNameLabel
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userTxt)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;

        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,
                SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        // userTxt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.SOUTH,userTxt,25,SpringLayout.NORTH,userNameLabel);
        // userPwdLabel
        springLayout.putConstraint(SpringLayout.EAST,userPwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.SOUTH,userPwdLabel,40,SpringLayout.SOUTH,userNameLabel);
        // pwdTxt
        springLayout.putConstraint(SpringLayout.EAST,pwdTxt,0,SpringLayout.EAST,userTxt);
        springLayout.putConstraint(SpringLayout.SOUTH,pwdTxt,40,SpringLayout.SOUTH,userTxt);
        // loginBtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,50,SpringLayout.WEST,userPwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,60,SpringLayout.NORTH,pwdTxt);
        // resetBtn
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,120,SpringLayout.WEST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,60,SpringLayout.NORTH,pwdTxt);
        //添加至容器中
        container.add(nameLabel,BorderLayout.NORTH);
        container.add(centerPanel,BorderLayout.CENTER);


        Toolkit kit =Toolkit.getDefaultToolkit();   //创建一个更改图标对象
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\带泪的鱼\\Desktop\\我的文件\\个人资料\\图标库\\Java超市管理系统2.png"));
            Image img=kit.createImage("C:\\Users\\带泪的鱼\\Desktop\\我的文件\\个人资料\\图标库\\Java超市管理系统2.png");
            this.setIconImage(img);
            }
        catch (IOException e) {
            e.printStackTrace();
        }

        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //主方法
    public static void main(String[] args) {
        new LoginView();
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPwdTxt() {
        return pwdTxt;
    }

}
