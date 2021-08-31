package com.dims.view.dialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.dims.dao.model.TbYhinfo;

public class DelUserDialog extends JDialog{

	/**
	 * 用户删除操作，必须拥有管理员身份
	 */
	 // ArrayList<String> sites = new ArrayList<String>();
	private ArrayList<String> names;//保存所有用户的用户名
	private int num;//用户总数
	private static final long serialVersionUID = 1L;
	private JComboBox<String> usersBox;
	private JPanel p1;
	private JButton del;
	public DelUserDialog(JFrame father,TbYhinfo userinfo) throws SQLException  {
		// TODO Auto-generated constructor stub
		super(father,"用户删除",true);
		userNamesInit(userinfo);
		dUDialogInit();
		dUDPanelInit(userinfo); 
		dUDEvenInit(this,userinfo);
		this.setVisible(true);
	}
	private void userNamesInit(TbYhinfo curuserinfo) throws SQLException {
		if(this.names==null) {
		TbYhinfo [] users=TbYhinfo.getAllUserInfo();
		this.num=users.length;
		int len=num;
		this.names=new ArrayList<String>();
		for(int i=0;i<len;i++) {
			String name="";
			name=users[i].getName();
			if(name.equals(curuserinfo.getName())){//如果是当前登陆用户
				this.num--;
				continue;//跳出本次循环
			}
			else {
			if(users[i].getIsm().equals("1")) {
				name=name+"(管理员)";
			}
			this.names.add(name);
			}
		}
		}
	}
	
	protected void dUDialogInit() {
		this.setBounds(420, 80, 360, 140);
		this.setResizable(false);//禁止缩放
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	private void dUDPanelInit(TbYhinfo userinfo) {
		// TODO Auto-generated method stub
		Container cont=this.getContentPane();
		cont.setLayout(null);
		p1=new JPanel();
		p1.setBounds(20, 30, 300, 30);
		p1.setLayout(new GridLayout(1,2,5,5));
		usersBox=new JComboBox<String>();
		usersBox.setBackground(Color.white);
		for(int i=0;i<num;i++) {
			usersBox.addItem(names.get(i));
		}
		del =new JButton("删除");
		del.setForeground(Color.RED);
		p1.add(usersBox);p1.add(del);
		cont.add(p1);
	}
	private void dUDPanelRefresh(String name) {
		usersBox.removeItem(name);
		p1.add(usersBox);p1.add(del);
		this.getContentPane().add(p1);
	}
	private void dUDEvenInit(DelUserDialog delUserDialog, TbYhinfo userinfo) {
		// TODO Auto-generated method stub
		
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String delname=(String) usersBox.getSelectedItem();
				dUDPanelRefresh(delname);//先进行移除
				int index=delname.indexOf("(管理员)");
				if(index>1) {
					delname=delname.replaceFirst("\\(管理员\\)","");
				}
				if(TbYhinfo.delUser(delname)){
					String mass="用户"+delname+"删除成功";
					delUserDialog.names.remove(delname);
					delUserDialog.num--;
					JOptionPane.showMessageDialog(null,mass);
				}else {
					JOptionPane.showMessageDialog(null,"用户删除操作失败");
				}
			}
			
		});
		}
		
	
}
