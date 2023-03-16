package TestSuperMarket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// 登录界面的按钮事件监听
public class LoginHandler implements ActionListener {
    private LoginView loginView;

    public LoginHandler(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton)e.getSource();
        String text = jButton.getText();
        if ("重置".equals(text)) {
            loginView.getUserTxt().setText("");
            loginView.getPwdTxt().setText("");
        }else if ("登录".equals(text)){
            String user = loginView.getUserTxt().getText();
            char[] chars = loginView.getPwdTxt().getPassword();
            if (user == null || "".equals(user.trim()) ||
                    chars == null){
                JOptionPane.showMessageDialog(loginView,"用户名和密码不能为空");
                return;
            }
            String pwd = new String(chars);

            //查询db
            AdminService adminIml = new AdminImpl();   //接口等于实现类，多态的体现
            AdminDO adminDO = new AdminDO();
            adminDO.setUserName(user);
            adminDO.setUserPwd(pwd);
            boolean flag = adminIml.verifyAdmin(adminDO);

            if (flag){
                // 跳转主界面
                new MainView();
                // 销毁登录界面
                loginView.dispose();
            }else {
                JOptionPane.showMessageDialog(loginView,"用户名或密码错误");
            }
        }
    }

}
