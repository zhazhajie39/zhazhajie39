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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dims.controller.UpdateUserController;
import com.dims.dao.model.TbYhinfo;

public class UpdateUserDialog  extends JDialog{

	/**
	 * 修改用户信息
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TbYhinfo> userlist;//用户链表
	private int usernum;//用户数
	private int curindex;//
	private JTextField nametext,teltext,mailtext,khyhtext,pwdtext;
	private JComboBox<String> idBox;//ID复选框
	private JComboBox<String> xbBox;//性别
	private JComboBox<String> ismBox;//系统管理员身份
	private JPanel p;//总面板
	private JButton ok,exit;
	public UpdateUserDialog() {
		// TODO Auto-generated constructor stub
		uUDialogInit();
		try {
			userlist =new ArrayList<TbYhinfo>();
			userlist=getAllUserInfo();//获取所有的用户信息
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Container con=this.getContentPane();//得到容器
		con.setLayout(null);
		p=uUDPanelInit();
		uUDEventInit(this);
		p.setBounds(20, 0, 320,580);
		con.add(p);
	}
 public ArrayList<TbYhinfo> getAllUserInfo() throws SQLException
 {
	    ArrayList<TbYhinfo> list=new ArrayList<TbYhinfo>();
	    TbYhinfo[] info=TbYhinfo.getAllUserInfo();//获取所有用户的信息
		this.usernum=info.length;//保存用户数
		//将用户依次添加到用户链表userlist
		if(list!=null)
		{
			list=null;
		}
		list=new ArrayList<TbYhinfo>();
		for(int i=0;i<this.usernum;i++) {
			list.add(info[i]);
		}
	 return list;
 }
	 protected void uUDialogInit() {
	    	this.setTitle("用户修改");
	    	this.setBounds(420, 30, 380, 630);
			this.setResizable(false);//禁止缩放
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    }
	 protected JPanel uUDPanelInit() {
	    	
	    	JPanel p=new JPanel();
	    	//网格布局
	    	p.setLayout(new GridLayout(17,0,0,1));
	    	p.add(new JLabel("用户ID:"));
	    	idBox=new JComboBox<String>();
	    	for(int i=0;i<this.usernum;i++) {
	    		idBox.addItem(userlist.get(i).getId().trim());
	    	}
	    	p.add(idBox);
	    	p.add(new JLabel("用户名:"));nametext=new JTextField(20);nametext.setText(userlist.get(0).getName());p.add(nametext);
	    	p.add(new JLabel("密码:"));pwdtext=new JTextField(12);pwdtext.setText(userlist.get(0).getPwd());p.add(pwdtext);
	    	p.add(new JLabel("性别:"));
	    	xbBox=new JComboBox<String>();
	    	xbBox.addItem(userlist.get(0).getXb());
	    	xbBox.addItem((userlist.get(0).getXb().equals("男")?"女":"男"));
	    	p.add(xbBox);
	    	p.add(new JLabel("电话:"));teltext=new JTextField(15);teltext.setText(userlist.get(0).getTel());p.add(teltext);
	    	p.add(new JLabel("邮箱:"));mailtext=new JTextField(20);mailtext.setText(userlist.get(0).getMail());p.add(mailtext);
	    	p.add(new JLabel("开户银行:"));khyhtext=new JTextField(40);khyhtext.setText(userlist.get(0).getKhyh());p.add(khyhtext);
	    	p.add(new JLabel("管理员身份:"));
	    	ismBox=new JComboBox<String>();
	    	ismBox.addItem(userlist.get(0).getIsm());
	    	ismBox.addItem((userlist.get(0).getIsm().equals("1")?"0":"1"));
	    	p.add(ismBox);
	    	JPanel p2=new JPanel();
	    	ok=new JButton("确认修改");
	    	ok.setBackground(Color.white);
	    	ok.setForeground(Color.red);
	    	exit=new JButton("取消修改");
	    	exit.setForeground(Color.gray);
	    	exit.setBackground(Color.white);
	    	p2.add(ok);
	    	p2.add(exit);
	    	p.add(p2);
	    	return p;
	    }
	 protected void uUDEventInit(JDialog jd) {
	    	ok.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						TbYhinfo newinfo=UpdateUserController.getInput(userlist.get(curindex).getId(), nametext, pwdtext, teltext, mailtext,khyhtext, xbBox,ismBox);
						if(UpdateUserController.checkInput(userlist.get(curindex).getName(), nametext, pwdtext, teltext, mailtext, khyhtext))
						{
						newinfo=UpdateUserController.getInput(userlist.get(curindex).getId(), nametext, pwdtext, teltext, mailtext,khyhtext, xbBox,ismBox);
						userlist.set(curindex, newinfo);
						try {
						if(TbYhinfo.updateUser(userlist.get(curindex))&& TbYhinfo.motifiedPwd(userlist.get(curindex).getId(),userlist.get(curindex).getPwd())) {
							JOptionPane.showMessageDialog(null,"修改成功");
						}
						else {
							JOptionPane.showMessageDialog(null,"修改失败");
						}
						}
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					
				}
	    		
	    	});
	    	exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jd.setVisible(false);
				}
	   
	    	});
	    	idBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					curindex=idBox.getSelectedIndex();
					refreshInput(curindex);
				}
	   
	    	});
	    }
	/**
	 * 得到索引后刷新表单内容
	 * @param index
	 */
	 protected void refreshInput(int index) {
		 this.nametext.setText(userlist.get(index).getName());
		 this.mailtext.setText(userlist.get(index).getMail());
		 this.khyhtext.setText(userlist.get(index).getKhyh());
		 this.pwdtext.setText(userlist.get(index).getPwd());
		 xbBox.removeAllItems();
		 xbBox.addItem(userlist.get(index).getXb());
	     xbBox.addItem((userlist.get(index).getXb().equals("男")?"女":"男"));
	     ismBox.removeAllItems();;
	     ismBox.addItem(userlist.get(index).getIsm());
	     ismBox.addItem((userlist.get(index).getIsm().equals("1")?"0":"1"));
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UpdateUserDialog uu=new UpdateUserDialog();
//		uu.setVisible(true);
	}

}
