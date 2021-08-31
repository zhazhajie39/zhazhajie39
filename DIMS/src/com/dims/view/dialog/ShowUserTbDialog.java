package com.dims.view.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.dims.dao.model.TbYhinfo;

public class ShowUserTbDialog extends JDialog {
/**
	 * �û���ϸ��Ϣ�б�
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TbYhinfo> userlist;//�û�����
	public ArrayList<TbYhinfo> getUserlist() {
		return userlist;
	}
	public void setUserlist(ArrayList<TbYhinfo> userlist) {
		this.userlist = userlist;
	}
	private int usernum;//��ǰ�û���
	private JScrollPane sp;
	private JTable usertb;//�û�������
	private final String[] colname={"id","�û���","�Ա�","ע������","�绰","����","��������","����Ա���"};
	private String[][] tbVal;//�����Ҫ��ʾ������
	private JPanel btnp;
	private JButton refresh;//ˢ�°�ť
	public ShowUserTbDialog(JFrame father) {
		// TODO Auto-generated constructor stub
		try {
			getUsersList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//�Ȼ�ȡ�û�����
		sHUTbDualogInit();
		sHUTbPanelInit();
		sHUtbEventInit();
		this.setVisible(true);
	}
	//�б�ˢ���¼�
	private void sHUtbEventInit() {
		// TODO Auto-generated method stub
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					getUsersList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					tbVal= setTbVal();
					usertb= new JTable(tbVal,colname);
					usertb.setRowHeight(30);//���ø�Ϊ30
					sp.setViewportView(usertb);
			}});
		
	}
	//�������ʼ��
	protected void sHUTbDualogInit() {
		this.setTitle("�û��б�");
		this.setBounds(100, 100, 1000, 475);
		this.setResizable(true);//������
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	protected void sHUTbPanelInit() {
	      sp=new JScrollPane();//�����ؼ�
		  getContentPane().add(sp,BorderLayout.CENTER);
		    //��ʼ����
		    tbVal= setTbVal();
			usertb= new JTable(tbVal,colname);
			usertb.setRowHeight(30);//���ø�Ϊ30
			sp.setViewportView(usertb);
			refresh=new JButton("ˢ��");
			btnp=new JPanel();
			btnp.add(refresh);
			refresh.setBounds(40, 0, 80, 30);
			getContentPane().add(btnp,BorderLayout.SOUTH);
	}
	protected String[][] setTbVal(){
		 String[][] tb=new String[this.usernum][colname.length];//�����洢�������
		//��ʼ����������ݵ�ֵ
		for(int row=0;row<this.usernum;row++) {
			tb[row][0]=userlist.get(row).getId();
			tb[row][1]=userlist.get(row).getName();
			tb[row][2]=userlist.get(row).getXb();
			tb[row][3]=userlist.get(row).getSub_date();
			tb[row][4]=userlist.get(row).getTel();
			tb[row][5]=userlist.get(row).getMail();
			tb[row][6]=userlist.get(row).getKhyh();
			tb[row][7]=userlist.get(row).getIsm();
			}
		return tb;
	}
	//�õ��û��б�
	public   void getUsersList() throws SQLException {
		TbYhinfo[] info=TbYhinfo.getAllUserInfo();//��ȡ�����û�����Ϣ
		this.usernum=info.length;//�����û���
		//���û�������ӵ��û�����userlist
		if(userlist!=null)
		{
			userlist=null;
		}
		userlist=new ArrayList<TbYhinfo>();
		for(int i=0;i<this.usernum;i++) {
			userlist.add(info[i]);
		}
	}
	
	//����û���������̨
	public void printUserList(ArrayList<TbYhinfo> userlist) {
		if(userlist==null) {
			return;
		}
		for(int i=0;i<this.usernum;i++) {
			System.out.println(userlist.get(i).getId());
		}
		
	}
	//�������
//	public static void main(String [] args) {
//		JFrame jf=new JFrame();
//		ShowUserTbDialog sud=new ShowUserTbDialog(jf);
////		sud.setVisible(false);
////		sud.printUserList(sud.getUserlist());
//	}
}
