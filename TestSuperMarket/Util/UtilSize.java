package TestSuperMarket;

import javax.swing.*;
import java.awt.*;
/*
自动获取屏幕大小的工具类
 */
public class UtilSize {
    public static Rectangle getBounds(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit()
                .getScreenInsets(new JFrame().getGraphicsConfiguration());
        Rectangle rectangle = new Rectangle(screenInsets.left, screenInsets.top,
                screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
        return rectangle;
    }

}
