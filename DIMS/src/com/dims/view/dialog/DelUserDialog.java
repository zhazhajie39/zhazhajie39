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
	 * �û�ɾ������������ӵ�й���Ա���
	 */
	 // ArrayList<String> sites = new ArrayList<String>();
	private ArrayList<String> names;//���������û����û���
	private int num;//�û�����
	private static final long serialVersionUID = 1L;
	private JComboBox<String> usersBox;
	private JPanel p1;
	private JButton del;
	public DelUserDialog(JFrame father,TbYhinfo userinfo) throws SQLException  {
		// TODO Auto-generated constructor stub
		super(father,"�û�ɾ��",true);
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
			if(name.equals(curuserinfo.getName())){//����ǵ�ǰ��½�û�
				this.num--;
				continue;//��������ѭ��
			}
			else {
			if(users[i].getIsm().equals("1")) {
				name=name+"(����Ա)";
			}
			this.names.add(name);
			}
		}
		}
	}
	
	protected void dUDialogInit() {
		this.setBounds(420, 80, 360, 140);
		this.setResizable(false);//��ֹ����
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
		del =new JButton("ɾ��");
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
				dUDPanelRefresh(delname);//�Ƚ����Ƴ�
				int index=delname.indexOf("(����Ա)");
				if(index>1) {
					delname=delname.replaceFirst("\\(����Ա\\)","");
				}
				if(TbYhinfo.delUser(delname)){
					String mass="�û�"+delname+"ɾ���ɹ�";
					delUserDialog.names.remove(delname);
					delUserDialog.num--;
					JOptionPane.showMessageDialog(null,mass);
				}else {
					JOptionPane.showMessageDialog(null,"�û�ɾ������ʧ��");
				}
			}
			
		});
		}
		
	
}
