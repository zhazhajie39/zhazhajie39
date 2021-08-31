package com.dims.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.dims.dao.KcDbOprator;
import com.dims.dao.model.TbYhinfo;
import com.dims.log.SystemLog;
import com.dims.login.LoginFrame;
import com.dims.view.dialog.*;

public class MainMenu extends JFrame{
/**
	 * 
	 */
	public static TbYhinfo curUser;//当前用户信息
	private static final long serialVersionUID = 1L;
    public static DetailDialog getDd() {
		return dd;
	}
/**
 * 系统主菜单
 */
 
	protected JMenuBar menuBar;
	protected JMenu userM,accountM,purchaseM,sellM;//用户管理 账号管理进货管理销售管理
	protected JMenuItem userDetial,delUser,userTable,updatePwd,userLogout,perfectInfo,updateUser;
	protected JMenuItem ypjh,supinit,kcinit;  //药品进货,供应商初始化,库存初始化
	protected JMenuItem sell,drawback;  //销售与退款
	private ShowUserTbDialog sutd;
	private static DetailDialog dd;
	private PerfectInfoDialog pid;
	private UpdateUserDialog   uu;
	protected static SellFrame sf;
	//工具栏
	private MainMenuToolbar mmtb;
	//内部窗体
	protected static JDesktopPane dp;
	protected static JInternalFrame userinframe;
    protected static KcInframe kcinframe;
	public MainMenu(JFrame login,TbYhinfo curuser) {
		// TODO Auto-generated constructor stub
		//super();
		curUser=setCurUser(curuser);
		mainMenuFrameInit();
		menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		accountM=new JMenu("账号管理(A)");
		accountM.setMnemonic(KeyEvent.VK_A);
		userM=new JMenu("用户管理(U)");//创建用户管理菜单
		userM.setMnemonic(KeyEvent.VK_U);//设置快捷键
		purchaseM=new JMenu("进货管理(P)");
		purchaseM.setMnemonic(KeyEvent.VK_P);
		sellM=new JMenu("销售管理(S)");
		sellM.setMnemonic(KeyEvent.VK_S);
		menuBar.add(accountM);
		menuBar.add(userM);//添加到菜单栏
		menuBar.add(purchaseM);
		menuBar.add(sellM);
		userDetial=new JMenuItem("账号详情");
		
		JMenuItem addUser=new JMenuItem("用户添加");
		//用户添加
		addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(curuser.getIsm().equals("1"))//是管理员就允许当前操作
				// TODO Auto-generated method stub
				{
					new AddUserDialog();
				}
				else {
					JOptionPane.showMessageDialog(null,"你没有该权限");
				}
				
			}
		});
		delUser=new JMenuItem("用户删除");
		
		userTable=new JMenuItem("用户列表");
		updateUser=new JMenuItem("用户修改");
		updatePwd=new JMenuItem("密码修改");
	
		perfectInfo=new JMenuItem("完善信息");
	    userLogout=new JMenuItem("注销账号");
	    ypjh=new JMenuItem("药品进货");
	    supinit=new JMenuItem("供应商初始化");
	    kcinit=new JMenuItem("库存初始化");
		sell=new JMenuItem("药品销售");
		drawback=new JMenuItem("售后退款");
		accountM.add(userDetial);//添加到用户管理菜单
		
		userM.add(addUser);//添加到"用户管理"菜单
		userM.add(delUser);//删除用户
		userM.add(userTable);
		userM.add(updateUser);//修改用户信息
		
		accountM.add(updatePwd);
		accountM.add(perfectInfo);
		accountM.add(userLogout);
		
		purchaseM.add(ypjh);
		purchaseM.add(supinit);
		purchaseM.add(kcinit);
		//System.out.println(this.curUser);
		sellM.add(sell);
		sellM.add(drawback);
		
		mmtb=new MainMenuToolbar();
		mmtb.setVisible(true);
		this.getContentPane().add(mmtb,BorderLayout.NORTH);
		dp=new JDesktopPane();
		System.out.println("dp初始化完成"+dp);
		mainMenuFrameEventInit(login,this,MainMenu.curUser);
		this.getContentPane().add(dp,BorderLayout.CENTER);
		
		//内部窗体
		
	}
	private void mainMenuFrameInit()
	{

		this.setTitle("系统主菜单");
		this.setBounds(20, 20, 1200, 700);
		this.setResizable(false);//禁止缩放
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void mainMenuFrameEventInit(JFrame login,JFrame jf, TbYhinfo curUser2) {	
		//账户详情
		userDetial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(dd==null)
				{
				 dd=new DetailDialog(jf,curUser2);
				}
				 dd.setVisible(true);
			}
		});
		//用户删除
		delUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(curUser.getIsm().equals("1"))//是管理员就允许当前操作
					// TODO Auto-generated method stub
					{
						try {
							new DelUserDialog(jf,curUser2);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"你没有该权限");
					}
				
			}
		});
		//用户详细信息列表
		userTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(curUser.getIsm().equals("1")) {
				if(sutd==null) {
				 sutd=new ShowUserTbDialog(jf);
				}
				sutd.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"你没有该权限");
				}
			}
		});
		//密码修改
		updatePwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MotifiedPwdDialog(jf,curUser2);
			}
		});
		//完善信息
		perfectInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(pid==null)
				{
				pid=new PerfectInfoDialog(jf,curUser2);
				}
				pid.setVisible(true);
			}
		});
		//注销账号
		userLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			jf.dispose();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,"注销成功！");
			login.setVisible(true);	
			}
		});
		//修改用户信息
		updateUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(curUser.getIsm().equals("1")) {
				if(uu==null)
				{
			     uu=new UpdateUserDialog();
				}
				uu.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"你没有该权限");
				}
			  
			}});
		//药品进货
		ypjh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new YpjhFrame().setVisible(true);
			}});
		//供应商初始化
		supinit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SupInitFrame().setVisible(true);
			}});
		kcinit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					KcDbOprator.kcInit();
					JOptionPane.showMessageDialog(null,"库存初始化完成！");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					SystemLog.out("库存初始化完成		操作员:"+LoginFrame.cur_user.getName());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		//添加内部窗体
		userinframe=new UserInFrame("药品信息表");
		kcinframe=new KcInframe();
		sf=new SellFrame();
		jf.addWindowListener(new DefinedListener(userinframe,dp));
		jf.addWindowListener(new DefinedListener(kcinframe,dp));
		jf.addWindowListener(new DefinedListener(sf,dp));
		sell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//打开库存表
				if(!kcinframe.isShowing()) {
//			if(!MainMenu.userinframe.isMaximum())
//			{
		userinframe.setVisible(true);
		dp.removeAll();
		kcinframe.setBounds(4, 0, 400, 500);
		dp.add(MainMenu.kcinframe);
		kcinframe.setVisible(true);
//			}
//			else {
//				System.out.println("窗口已最大化");
//			}
		}
				//打开销售操作面板
				//sf=new SellFrame();
				sf.setBounds(404, 0, 700, 500);
				dp.add(MainMenu.sf);
				sf.setVisible(true);
				
			}});
		
	}
	
	public TbYhinfo getCurUser() {
		return curUser;
	}
	public static TbYhinfo setCurUser(TbYhinfo curUser) {
		return MainMenu.curUser = curUser;
	}
	public static void main(String args[]) {
//		TbYhinfo curUser=new TbYhinfo();
//		curUser=Dao.getTbYhInfo("21314001");
//		new MainMenu(curUser).setVisible(true);;
	}
	
}
