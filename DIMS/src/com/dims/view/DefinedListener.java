package com.dims.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 * 主菜单内部窗体监听事件
 * @author 86157
 *
 */
public class DefinedListener implements  WindowListener {

	JInternalFrame inframe;
	JDesktopPane dp;
	public DefinedListener() {
		// TODO Auto-generated constructor stub
		inframe=new JInternalFrame("测试");
	}
	public DefinedListener(JInternalFrame inframe,JDesktopPane dp) {
		// TODO Auto-generated constructor stub
		this.inframe=inframe;
		this.dp=dp;
		System.out.println(dp);
		this.dp.add(this.inframe);
	}

	public void windowActivated(WindowEvent e) {// 窗体被激活时触发
	//System.out.println("窗口被激活！");
	// TODO Auto-generated method stub
	/**
	 * 如果窗口被关闭或为空，当窗体进入活动状态时
	 * 重新加载内部窗体保证内部窗体不消失
	 */
	if(inframe==null||inframe.isClosed()) {
//		JInternalFrame[] allframe=dp.getAllFrames();
//		int count=allframe.length;
//		int tbh=30*count;//计算每个内部窗体的水平偏移量
//		int x=10+tbh,y=x;//窗体显示的位置
//		inframe=new UserInFrame("测试");
//		inframe.setBounds(x, y, 250, 180);
//		inframe.setVisible(true);
//		dp.add(inframe);
//		try {
//			inframe.setSelected(true);//选中窗体
//		} catch (PropertyVetoException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		System.out.println("已执行");
	}
	else {
	System.out.println("已有内部窗体");
	}
}
public void windowOpened(WindowEvent e) {// 窗体被打开时触发
	
}
public void windowIconified(WindowEvent e) {// 窗体被最小化时触发
	
}
public void windowDeiconified(WindowEvent e) {// 窗体被非最小化时触发
	
}
public void windowClosing(WindowEvent e) {// 窗体将要被关闭时触发
	
}
// 窗体不再处于激活状态时触发
public void windowDeactivated(WindowEvent e) {
	
	
}
public void windowClosed(WindowEvent e) {// 窗体已经被关闭时触发

}
	

}
