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
	 * 用户详细信息列表
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TbYhinfo> userlist;//用户链表
	public ArrayList<TbYhinfo> getUserlist() {
		return userlist;
	}
	public void setUserlist(ArrayList<TbYhinfo> userlist) {
		this.userlist = userlist;
	}
	private int usernum;//当前用户数
	private JScrollPane sp;
	private JTable usertb;//用户详情表格
	private final String[] colname={"id","用户名","性别","注册日期","电话","邮箱","开户银行","管理员身份"};
	private String[][] tbVal;//表格中要显示的数据
	private JPanel btnp;
	private JButton refresh;//刷新按钮
	public ShowUserTbDialog(JFrame father) {
		// TODO Auto-generated constructor stub
		try {
			getUsersList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//先获取用户链表
		sHUTbDualogInit();
		sHUTbPanelInit();
		sHUtbEventInit();
		this.setVisible(true);
	}
	//列表刷新事件
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
					usertb.setRowHeight(30);//设置高为30
					sp.setViewportView(usertb);
			}});
		
	}
	//主窗体初始化
	protected void sHUTbDualogInit() {
		this.setTitle("用户列表");
		this.setBounds(100, 100, 1000, 475);
		this.setResizable(true);//能缩放
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	protected void sHUTbPanelInit() {
	      sp=new JScrollPane();//滚动控件
		  getContentPane().add(sp,BorderLayout.CENTER);
		    //开始建表
		    tbVal= setTbVal();
			usertb= new JTable(tbVal,colname);
			usertb.setRowHeight(30);//设置高为30
			sp.setViewportView(usertb);
			refresh=new JButton("刷新");
			btnp=new JPanel();
			btnp.add(refresh);
			refresh.setBounds(40, 0, 80, 30);
			getContentPane().add(btnp,BorderLayout.SOUTH);
	}
	protected String[][] setTbVal(){
		 String[][] tb=new String[this.usernum][colname.length];//用来存储表格数据
		//初始化表格中数据的值
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
	//得到用户列表
	public   void getUsersList() throws SQLException {
		TbYhinfo[] info=TbYhinfo.getAllUserInfo();//获取所有用户的信息
		this.usernum=info.length;//保存用户数
		//将用户依次添加到用户链表userlist
		if(userlist!=null)
		{
			userlist=null;
		}
		userlist=new ArrayList<TbYhinfo>();
		for(int i=0;i<this.usernum;i++) {
			userlist.add(info[i]);
		}
	}
	
	//输出用户链表到控制台
	public void printUserList(ArrayList<TbYhinfo> userlist) {
		if(userlist==null) {
			return;
		}
		for(int i=0;i<this.usernum;i++) {
			System.out.println(userlist.get(i).getId());
		}
		
	}
	//程序测试
//	public static void main(String [] args) {
//		JFrame jf=new JFrame();
//		ShowUserTbDialog sud=new ShowUserTbDialog(jf);
////		sud.setVisible(false);
////		sud.printUserList(sud.getUserlist());
//	}
}
