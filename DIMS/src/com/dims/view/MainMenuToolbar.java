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
	 * inframe Ҫ��ʾ�Ĵ���
	 * @param inframe
	 */
	public MainMenuToolbar() {
		// TODO Auto-generated constructor stub
		this.setFloatable(true);//���������϶�
		btnInit();
		btnEventInit();
		toolBarPanel();
	}
	public void btnInit() {
		mmtbbtn=new JButton("ҩƷ��Ϣ��");
		kctbbtn=new JButton("����");
	}
	public void btnEventInit() {
		mmtbbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				MainMenu.userinframe=new  UserInFrame("����");
//				MainMenu.userinframe.setBounds(10, 10, 1000, 500);
				if(!MainMenu.userinframe.isShowing()) {
//					if(!MainMenu.userinframe.isMaximum())
//					{
				MainMenu.kcinframe.setVisible(false);
				MainMenu.sf.setVisible(false);
				MainMenu.dp.removeAll();//���ɼ����Ƴ�
				MainMenu.userinframe.setBounds(10, 10,1000, 500);
				MainMenu.dp.add(MainMenu.userinframe);
				MainMenu.userinframe.setVisible(true);
//				System.out.println("���"+MainMenu.userinframe.isClosed());
//				System.out.println("���"+MainMenu.userinframe.isShowing());
				}
			}
		});
		kctbbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				MainMenu.userinframe=new  UserInFrame("����");
//				MainMenu.userinframe.setBounds(10, 10, 1000, 500);
				if(!MainMenu.kcinframe.isShowing()) {
//					if(!MainMenu.userinframe.isMaximum())
//					{
				//MainMenu.dp.remove(MainMenu.userinframe);
				MainMenu.userinframe.setVisible(false);
				MainMenu.dp.removeAll();//���ɼ����Ƴ�
				MainMenu.kcinframe.setBounds(0, 0, 400, 500);
				MainMenu.dp.add(MainMenu.kcinframe);
				MainMenu.kcinframe.setVisible(true);
				
//					}
//					else {
//						System.out.println("���������");
//					}
				}
				else
				{
					System.out.println("δ��");
				}
//				System.out.println("���"+MainMenu.userinframe.isClosed());
//				System.out.println("���"+MainMenu.userinframe.isShowing());
				
			}
		});
	}
	public void toolBarPanel() {
		this.add(mmtbbtn);//��ӵ�������
		this.add(kctbbtn);
	}
	

}
