package com.dims.login;
/**
 * ��¼����
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
//	//���ڻ�ȡ�������Ļ�ĳߴ���Ϣ
// public  static Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
 		
 	protected Container con;
 	protected Panel p1,p2;
 	protected JLabel welcome,usname,paswd;
 	protected JTextField text1;
 	protected JPasswordField text2;
 	protected JButton login,regist;
 	public static String cur_userID;//��ǰ�û�ID
 	public static TbYhinfo cur_user;//��¼�ɹ��󱣴���û���Ϣ
 	public SubFrame subframe;
 	private 	MainMenu mm;
 	//���þ��Բ���
	public LoginFrame() {//��¼�����ʼ��
		// TODO Auto-generated constructor stub
		

		this.setTitle("��ӭ��½");
		this.setBounds(200, 80, 1000, 600);
		this.setResizable(false);//��ֹ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ʵ���������
		con = this.getContentPane(); //����һ������
		p1=new Panel();
		p2=new Panel();
		//con.setBackground(Color.LIGHT_GRAY);
		con.setLayout(null);  //ȡ�����ֹ�����
	    welcome = new JLabel("ҩƷ��Ϣ����ϵͳ");
		
		usname = new JLabel();
	    paswd = new JLabel();
	    text1 = new JTextField(12);//�û�������
		text2 = new JPasswordField(12);//��������
		login = new JButton("��½");
		regist = new JButton("ע��");
		
		//����FocusListener��������ȡ��꽹�㣬ʵ����ʾ���ܡ�
		
		p1.setBounds(350, 80, 240, 40);//λ��
		//p1.setBackground(Color.blue);
		welcome.setFont(new Font("Dialog",0,30));//��������
		p1.add(welcome);
		con.add(p1); 
		
		//��������ʽ
		p2.setBounds(300,200,360,180);
		p2.setBackground(Color.green);
		p2.setLayout(null);
		
		usname.setText("�˺ţ�");
		paswd.setText("���룺");
		usname.setBounds(60, 20, 90, 30);text1.setBounds(100, 20, 200,30);
		paswd.setBounds(60, 60, 90, 30);text2.setBounds(100, 60, 200, 30);
		login.setBounds(160, 120,60 ,30);regist.setBounds(240, 120,60 ,30);
		text1.setText("����");
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

	//��Ӽ����¼�
	login.addActionListener(new ActionListener() {

		@Override
		//��¼
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			String name=LoginController.getName(text1);
			String pwd=LoginController.getPwd(text2);
			if(LoginController.namePwdIsEmpty(name, pwd)){
				JOptionPane.showMessageDialog(null,"�û��������벻��Ϊ��");
			}
			//��¼��Ϣ���
			//System.out.println(name+","+pwd);
			else {
			try {
				if(TbYhinfo.userIsLogin(name,pwd)) {
					//������ת��ϵͳ���˵�
					JOptionPane.showMessageDialog(null,"��¼�ɹ���");
					jf.setVisible(false);
					cur_user=Dao.getTbYhInfo(cur_userID);
					//System.out.println(cur_user);
					mm=new MainMenu(jf,cur_user);
					mm.setVisible(true);
					//this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null,"�û��������ڻ��������");
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
		//ע��
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//��ע�ᴰ��
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
		LoginFrame lg=new LoginFrame();//ʵ������¼����
		lg.setVisible(true);
			
	}

	
}
