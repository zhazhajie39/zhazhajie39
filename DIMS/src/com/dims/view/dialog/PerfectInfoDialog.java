package com.dims.view.dialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dims.controller.SubController;
import com.dims.dao.model.TbYhinfo;
import com.dims.view.MainMenu;

public class PerfectInfoDialog extends JDialog{

	
	
	/**
	 * 完善信息
	 */
	private static final long serialVersionUID = 1L;
	//需要修改的信息
    private JTextField nametext,teltext,mailtext,khyhtext;
    private JComboBox<String> xbBox;//性别
	private JPanel p;//总面板
	private JButton ok,exit;
	public PerfectInfoDialog(JFrame father,TbYhinfo curuser) {
		// TODO Auto-generated constructor stub
		pIDialogInit();
		Container con=this.getContentPane();//得到容器
		con.setLayout(null);
		p=pIDPanelInit(curuser);
		pIDEventInit(this,curuser);
		p.setBounds(20, 10, 320,500);
		con.add(p);
	}
//	public PerfectInfoDialog() {
//		// TODO Auto-generated constructor stub
//		pIDialogInit();
//		Container con=this.getContentPane();//得到容器
//		//p=pIDPanelInit();
//		con.add(p);
//	}
    protected void pIDialogInit() {
    	this.setTitle("完善信息");
    	this.setBounds(420, 80, 380, 560);
		this.setResizable(true);//禁止缩放
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    //初始化面板
    protected JPanel pIDPanelInit(TbYhinfo curuser) {
    	
    	JPanel p=new JPanel();
    	//网格布局
    	p.setLayout(new GridLayout(13,1,0,5));
    	p.add(new JLabel("用户ID:      "+curuser.getId()));
    	p.add(new JLabel("用户名:"));nametext=new JTextField(20);nametext.setText(curuser.getName());p.add(nametext);
    	p.add(new JLabel("性别:"));
    	xbBox=new JComboBox<String>();
    	xbBox.addItem(curuser.getXb());
    	xbBox.addItem((curuser.getXb().equals("男")?"女":"男"));
    	p.add(xbBox);
    	p.add(new JLabel("电话:"));teltext=new JTextField(15);teltext.setText(curuser.getTel());p.add(teltext);
    	p.add(new JLabel("邮箱:"));mailtext=new JTextField(20);mailtext.setText(curuser.getMail());p.add(mailtext);
    	p.add(new JLabel("开户银行:"));khyhtext=new JTextField(40);khyhtext.setText(curuser.getKhyh());p.add(khyhtext);
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
    protected void pIDEventInit(JDialog jd,TbYhinfo curuser) {
    	ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TbYhinfo info=new TbYhinfo();
				info=SubController.getInput(nametext,xbBox,teltext,mailtext,khyhtext);
				if(SubController.checkInput2(info)){
				info=SubController.getInput(nametext,xbBox,teltext,mailtext,khyhtext);
				updatecurUserInfo(curuser,info);
				try {
					if(TbYhinfo.updateUser(curuser)) {
						MainMenu.setCurUser(curuser);
					    MainMenu.getDd().refreshDetail(curuser);
						JOptionPane.showMessageDialog(null,"修改成功");
					}
					else {
						JOptionPane.showMessageDialog(null,"修改失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
    		
    	}});
    	exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd.setVisible(false);
			}
   
    	});
    }

    public static  void updatecurUserInfo(TbYhinfo curuser,TbYhinfo newinfo) {
    		curuser.setName(newinfo.getName());
    		curuser.setXb(newinfo.getXb());
    		curuser.setTel(newinfo.getTel());
    		curuser.setMail(newinfo.getMail());
    		curuser.setKhyh(newinfo.getKhyh());
    }
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		TbYhinfo curUser=new TbYhinfo();
//     	curUser=Dao.getTbYhInfo("21314001");
//		PerfectInfoDialog pid=new PerfectInfoDialog(new JFrame(),curUser);
//		pid.setVisible(true);
//	}

}
