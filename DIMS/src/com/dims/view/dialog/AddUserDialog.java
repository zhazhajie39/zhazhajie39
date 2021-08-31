package com.dims.view.dialog;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dims.controller.SubController;
import com.dims.dao.model.TbYhinfo;
import com.dims.login.SubFrame;

public class AddUserDialog extends JDialog {

	/**
	 * ����û��ĶԻ��� Ĭ������123456
	 */
	private static final long serialVersionUID = 1L;
	protected Container ct;
	protected JPanel p1;
	protected JLabel name,xb,gly,tel,mail,khyh;
 	protected JTextField nametext,teltext,mailtext,khyhtext;	
 	protected JRadioButton rbxb1,rbxb2,glyyes,glyno;//�Ա�ѡ��ť
 	protected JButton conform,cansel;
 	public String xbstr;public boolean isM;
	public AddUserDialog() {
		// TODO Auto-generated constructor stub
		aUDialogInit();
		ct=this.getContentPane();
		aUDialogJPanelInit();
		aUDialogActionEventInit(this);
		ct.add(this.p1);
		this.setVisible(true);
	}
 //��ʼ��ע�ᴰ��
 protected void  aUDialogInit()
 {
	 	this.setTitle("�û����");
		this.setBounds(420, 80, 380, 360);
		this.setResizable(false);//��ֹ����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 }
 //��ʼ�����
 protected JPanel aUDialogJPanelInit() {
	    p1=new JPanel();
		p1.setLayout(null);
		//�û���
		name=new JLabel("�û�����");
		nametext=new JTextField(10);
		name.setBounds(30, 20,120, 30);
		nametext.setBounds(120,20,200,30);
		p1.add(name);p1.add(nametext);
		//�Ա�
		xb=new JLabel("�Ա�");
		rbxb1=new JRadioButton("��");
		rbxb2=new JRadioButton("Ů");
		rbxb1.setSelected(true);
		xbstr="��";//Ĭ��Ϊ��
		xb.setBounds(30, 60, 120, 30);
		rbxb1.setBounds(140, 60, 60, 30);
		rbxb2.setBounds(260, 60, 60, 30);
		p1.add(xb);p1.add(rbxb1);p1.add(rbxb2);
		//�Ա�
				gly=new JLabel("����Ա��");
				glyyes=new JRadioButton("��");
				glyno=new JRadioButton("��");
				glyno.setSelected(true);
				isM=false;
				gly.setBounds(30, 100, 120, 30);
				glyyes.setBounds(140, 100, 60, 30);
				glyno.setBounds(260, 100, 60, 30);
				p1.add(gly);p1.add(glyyes);p1.add(glyno);
		//��ϵ��ʽ ���� ��������
		tel=new JLabel("��ϵ��ʽ��");teltext=new JTextField(20);
		mail=new JLabel("���䣺");mailtext=new JTextField(20);
		khyh=new JLabel("�������У�");khyhtext=new JTextField(30);
		tel.setBounds(30, 140, 150, 30);teltext.setBounds(120, 140, 200, 30);
		mail.setBounds(30, 180, 150, 30);mailtext.setBounds(120, 180, 200, 30);
		khyh.setBounds(30, 220, 150, 30);khyhtext.setBounds(120, 220, 200, 30);
		p1.add(tel);p1.add(teltext);
		p1.add(mail);p1.add(mailtext);
		p1.add(khyh);p1.add(khyhtext);
		//ȷ�� ȡ��
		conform=new JButton("���");cansel=new JButton("ȡ��");
		conform.setBounds(160, 260, 60, 30);cansel.setBounds(260, 260, 60, 30);
		p1.add(conform);p1.add(cansel);
	 return this.p1;
 }
 //��ʼ���¼���Ӧ
 protected void aUDialogActionEventInit(JDialog dialog) {
	 //�Ա�ѡ�¼�
	 rbxb1.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(rbxb1.isSelected()) {
				xbstr="��";
				rbxb2.setSelected(false);
			}
		} 
	 });
	 rbxb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rbxb2.isSelected()) {
					xbstr="Ů";
					rbxb1.setSelected(false);
				}
			} 
		 });
	 //����Ա
	 glyyes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(glyyes.isSelected()) {
					isM=true;
					glyno.setSelected(false);
				}
			} 
		 });
	 glyno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(glyno.isSelected()) {
					isM=false;
					glyyes.setSelected(false);
				}
			} 
		 });
	 conform.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TbYhinfo info=new TbYhinfo();
				info=SubController.getInput(nametext,teltext,mailtext,khyhtext);
				if(SubController.checkInput(info)) {
				info=SubController.getInput(nametext,teltext,mailtext,khyhtext);
			    info.setSub_date(SubFrame.getNowTime());
			    info.setXb(xbstr);
			    info.setPwd("123456");//Ĭ������123456
			    if(isM) {
				  info.setIsm("1"); 
			    }
			    else {
			      info.setIsm("0");
			    }
			    if(TbYhinfo.addUser(info)) {
			    	try {
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	JOptionPane.showMessageDialog(null,"��ӳɹ�");
			    	dialog.setVisible(false);
			    }else {
			    	JOptionPane.showMessageDialog(null,"���ʧ��");
			    }
				//System.out.println(namestr+","+xbstr+","+pwdstr+","+telstr+","+mailstr+","+datestr+","+khyhstr);
				}
			} 
		 });
	 cansel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();
			} 
		 });
}
	
}
