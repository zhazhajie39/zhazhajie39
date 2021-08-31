package com.dims.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class MainMenuToolbar extends JToolBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  JButton mmtbbtn,kctbbtn;
	/**
	 * inframe 要显示的窗体
	 * @param inframe
	 */
	public MainMenuToolbar() {
		// TODO Auto-generated constructor stub
		this.setFloatable(true);//设置允许拖动
		btnInit();
		btnEventInit();
		toolBarPanel();
	}
	public void btnInit() {
		mmtbbtn=new JButton("药品信息表");
		kctbbtn=new JButton("库存表");
	}
	public void btnEventInit() {
		mmtbbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				MainMenu.userinframe=new  UserInFrame("测试");
//				MainMenu.userinframe.setBounds(10, 10, 1000, 500);
				if(!MainMenu.userinframe.isShowing()) {
//					if(!MainMenu.userinframe.isMaximum())
//					{
				MainMenu.kcinframe.setVisible(false);
				MainMenu.sf.setVisible(false);
				MainMenu.dp.removeAll();//不可见后移除
				MainMenu.userinframe.setBounds(10, 10,1000, 500);
				MainMenu.dp.add(MainMenu.userinframe);
				MainMenu.userinframe.setVisible(true);
//				System.out.println("点击"+MainMenu.userinframe.isClosed());
//				System.out.println("点击"+MainMenu.userinframe.isShowing());
				}
			}
		});
		kctbbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				MainMenu.userinframe=new  UserInFrame("测试");
//				MainMenu.userinframe.setBounds(10, 10, 1000, 500);
				if(!MainMenu.kcinframe.isShowing()) {
//					if(!MainMenu.userinframe.isMaximum())
//					{
				//MainMenu.dp.remove(MainMenu.userinframe);
				MainMenu.userinframe.setVisible(false);
				MainMenu.dp.removeAll();//不可见后移除
				MainMenu.kcinframe.setBounds(0, 0, 400, 500);
				MainMenu.dp.add(MainMenu.kcinframe);
				MainMenu.kcinframe.setVisible(true);
				
//					}
//					else {
//						System.out.println("窗口已最大化");
//					}
				}
				else
				{
					System.out.println("未关");
				}
//				System.out.println("点击"+MainMenu.userinframe.isClosed());
//				System.out.println("点击"+MainMenu.userinframe.isShowing());
				
			}
		});
	}
	public void toolBarPanel() {
		this.add(mmtbbtn);//添加到工具栏
		this.add(kctbbtn);
	}
	

}
