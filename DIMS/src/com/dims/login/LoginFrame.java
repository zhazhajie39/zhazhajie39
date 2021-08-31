package com.dims.login;
/**
 * 登录窗体
 * @author GMJ
 */

import java.awt.Color;
import java.awt.Container;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Panel;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.dims.controller.LoginController;
import com.dims.dao.Dao;
import com.dims.dao.model.TbYhinfo;
import com.dims.view.MainMenu;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	/**
	 * 
	 */
//	//用于获取计算机屏幕的尺寸信息
// public  static Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
 		
 	protected Container con;
 	protected Panel p1,p2;
 	protected JLabel welcome,usname,paswd;
 	protected JTextField text1;
 	protected JPasswordField text2;
 	protected JButton login,regist;
 	public static String cur_userID;//当前用户ID
 	public static TbYhinfo cur_user;//登录成功后保存该用户信息
 	public SubFrame subframe;
 	private 	MainMenu mm;
 	//采用绝对布局
	public LoginFrame() {//登录界面初始化
		// TODO Auto-generated constructor stub
		

		this.setTitle("欢迎登陆");
		this.setBounds(200, 80, 1000, 600);
		this.setResizable(false);//禁止缩放
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 实例化各组件
		con = this.getContentPane(); //生成一个容器
		p1=new Panel();
		p2=new Panel();
		//con.setBackground(Color.LIGHT_GRAY);
		con.setLayout(null);  //取消布局管理器
	    welcome = new JLabel("药品信息管理系统");
		
		usname = new JLabel();
	    paswd = new JLabel();
	    text1 = new JTextField(12);//用户名输入
		text2 = new JPasswordField(12);//密码输入
		login = new JButton("登陆");
		regist = new JButton("注册");
		
		//调用FocusListener方法，获取鼠标焦点，实现提示功能。
		
		p1.setBounds(350, 80, 240, 40);//位置
		//p1.setBackground(Color.blue);
		welcome.setFont(new Font("Dialog",0,30));//设置字体
		p1.add(welcome);
		con.add(p1); 
		
		//布局与样式
		p2.setBounds(300,200,360,180);
		p2.setBackground(Color.green);
		p2.setLayout(null);
		
		usname.setText("账号：");
		paswd.setText("密码：");
		usname.setBounds(60, 20, 90, 30);text1.setBounds(100, 20, 200,30);
		paswd.setBounds(60, 60, 90, 30);text2.setBounds(100, 60, 200, 30);
		login.setBounds(160, 120,60 ,30);regist.setBounds(240, 120,60 ,30);
		text1.setText("张三");
		text2.setText("abc123");
		p2.add(usname);p2.add(text1);
		p2.add(paswd);p2.add(text2);
		p2.add(login);p2.add(regist);
		loginEventInit(this);
		con.add(p2);
		JPanel panel = new ImagePanel();
		panel.setBounds(0, 0, 1000, 800);
		getContentPane().add(panel);
	}
public void loginEventInit(JFrame jf) {

	//添加监听事件
	login.addActionListener(new ActionListener() {

		@Override
		//登录
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			String name=LoginController.getName(text1);
			String pwd=LoginController.getPwd(text2);
			if(LoginController.namePwdIsEmpty(name, pwd)){
				JOptionPane.showMessageDialog(null,"用户名或密码不能为空");
			}
			//登录信息检测
			//System.out.println(name+","+pwd);
			else {
			try {
				if(TbYhinfo.userIsLogin(name,pwd)) {
					//界面跳转至系统主菜单
					JOptionPane.showMessageDialog(null,"登录成功！");
					jf.setVisible(false);
					cur_user=Dao.getTbYhInfo(cur_userID);
					//System.out.println(cur_user);
					mm=new MainMenu(jf,cur_user);
					mm.setVisible(true);
					//this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null,"用户名不存在或密码错误");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			{
			}
			}
			
		}
		
	});
	regist.addActionListener(new ActionListener() {

		@Override
		//注册
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//打开注册窗口
			if(subframe==null) {
			subframe=new SubFrame();
			}
			else {
			subframe.setVisible(true);
			}
		}
	});
}
class ImagePanel extends JPanel {
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("D:\\EclipseProject\\DIMS\\image\\dimsbg1.png");
		g.drawImage(icon.getImage(), 0, 0, null);
	}
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginFrame lg=new LoginFrame();//实例化登录窗口
		lg.setVisible(true);
			
	}

	
}
