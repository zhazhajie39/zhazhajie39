package com.dims.login;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dims.controller.SubController;
import com.dims.dao.model.TbYhinfo;

public class SubFrame extends JFrame {

	/**
	 * 注册窗口
	 */
	private static final long serialVersionUID = 1L;
	protected Container ct;
	protected JPanel p1;
	protected JLabel name,xb,pwd1,pwd2,tel,mail,khyh;
 	protected JTextField nametext,teltext,mailtext,khyhtext;	
 	protected JPasswordField pwd1text,pwd2text;
 	protected JRadioButton rbxb1,rbxb2;//性别单选按钮
 	protected JButton conform,cansel;
 	public String xbstr;
	public SubFrame() {
		// TODO Auto-generated constructor stub
		subFrameInit();
		ct=this.getContentPane();
		subFrameJPanelInit();
		subFrameActionEventInit(this);
		ct.add(this.p1);
		this.setVisible(true);
	}
 //初始化注册窗口
 protected void  subFrameInit()
 {
	 	this.setTitle("用户注册");
		this.setBounds(420, 80, 380, 400);
		this.setResizable(false);//禁止缩放
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 }
 //初始化面板
 protected JPanel subFrameJPanelInit() {
	    p1=new JPanel();
		p1.setLayout(null);
		//用户名
		name=new JLabel("用户名：");
		nametext=new JTextField(10);
		name.setBounds(40, 20,120, 30);
		nametext.setBounds(120,20,200,30);
		p1.add(name);p1.add(nametext);
		//性别
		xb=new JLabel("性别：");
		rbxb1=new JRadioButton("男");
		rbxb2=new JRadioButton("女");
		rbxb1.setSelected(true);
		xbstr="男";//默认为男
		xb.setBounds(55, 60, 120, 30);
		rbxb1.setBounds(140, 60, 60, 30);
		rbxb2.setBounds(260, 60, 60, 30);
		p1.add(xb);p1.add(rbxb1);p1.add(rbxb2);
		
		//密码
		pwd1=new JLabel("设置密码：");
		pwd2=new JLabel("确认密码：");
		pwd1text=new JPasswordField(12);
		pwd2text=new JPasswordField(12);
		pwd1text.setEchoChar((char)0);pwd2text.setEchoChar((char)0);//设置明文可见
		pwd1.setBounds(30, 100, 150, 30);pwd1text.setBounds(120, 100, 200, 30);
		pwd2.setBounds(30, 140, 150, 30);pwd2text.setBounds(120, 140, 200, 30);
		p1.add(pwd1);p1.add(pwd1text);
		p1.add(pwd2);p1.add(pwd2text);
		//联系方式 邮箱 开户银行
		tel=new JLabel("联系方式：");teltext=new JTextField(20);
		mail=new JLabel("邮箱：");mailtext=new JTextField(20);
		khyh=new JLabel("开户银行：");khyhtext=new JTextField(30);
		tel.setBounds(30, 180, 150, 30);teltext.setBounds(120, 180, 200, 30);
		mail.setBounds(30, 220, 150, 30);mailtext.setBounds(120, 220, 200, 30);
		khyh.setBounds(30, 260, 150, 30);khyhtext.setBounds(120, 260, 200, 30);
		p1.add(tel);p1.add(teltext);
		p1.add(mail);p1.add(mailtext);
		p1.add(khyh);p1.add(khyhtext);
		//确认 取消
		conform=new JButton("确认");cansel=new JButton("取消");
		conform.setBounds(160, 300, 60, 30);cansel.setBounds(260, 300, 60, 30);
		p1.add(conform);p1.add(cansel);
	 return this.p1;
 }
 //初始化事件响应
 protected void subFrameActionEventInit(JFrame frame) {
	 //性别单选事件
	 rbxb1.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(rbxb1.isSelected()) {
				xbstr="男";
				rbxb2.setSelected(false);
			}
		} 
	 });
	 rbxb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rbxb2.isSelected()) {
					xbstr="女";
					rbxb1.setSelected(false);
				}
			} 
		 });
	 conform.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TbYhinfo info=new TbYhinfo();
				info=SubController.getInput(nametext,pwd1text,teltext,mailtext,khyhtext);
				if(SubController.checkInput(info,pwd1text,pwd2text)) {
				info=SubController.getInput(nametext,pwd1text,teltext,mailtext,khyhtext);
			    info.setSub_date(getNowTime());
			    info.setXb(xbstr);
			    info.setIsm("0");
			    if(TbYhinfo.addUser(info)) {
			    	JOptionPane.showMessageDialog(null,"注册成功");
			    }else {
			    	JOptionPane.showMessageDialog(null,"注册失败");
			    }
				//System.out.println(namestr+","+xbstr+","+pwdstr+","+telstr+","+mailstr+","+datestr+","+khyhstr);
				}
			} 
		 });
	 cansel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			} 
		 });
	
 }
 
 public static String getNowTime() {
	 Date dNow = new Date( );
	 SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	 return ft.format(dNow);
 }
}
