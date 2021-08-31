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
	public static TbYhinfo curUser;//��ǰ�û���Ϣ
	private static final long serialVersionUID = 1L;
    public static DetailDialog getDd() {
		return dd;
	}
/**
 * ϵͳ���˵�
 */
 
	protected JMenuBar menuBar;
	protected JMenu userM,accountM,purchaseM,sellM;//�û����� �˺Ź�������������۹���
	protected JMenuItem userDetial,delUser,userTable,updatePwd,userLogout,perfectInfo,updateUser;
	protected JMenuItem ypjh,supinit,kcinit;  //ҩƷ����,��Ӧ�̳�ʼ��,����ʼ��
	protected JMenuItem sell,drawback;  //�������˿�
	private ShowUserTbDialog sutd;
	private static DetailDialog dd;
	private PerfectInfoDialog pid;
	private UpdateUserDialog   uu;
	protected static SellFrame sf;
	//������
	private MainMenuToolbar mmtb;
	//�ڲ�����
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
		accountM=new JMenu("�˺Ź���(A)");
		accountM.setMnemonic(KeyEvent.VK_A);
		userM=new JMenu("�û�����(U)");//�����û�����˵�
		userM.setMnemonic(KeyEvent.VK_U);//���ÿ�ݼ�
		purchaseM=new JMenu("��������(P)");
		purchaseM.setMnemonic(KeyEvent.VK_P);
		sellM=new JMenu("���۹���(S)");
		sellM.setMnemonic(KeyEvent.VK_S);
		menuBar.add(accountM);
		menuBar.add(userM);//��ӵ��˵���
		menuBar.add(purchaseM);
		menuBar.add(sellM);
		userDetial=new JMenuItem("�˺�����");
		
		JMenuItem addUser=new JMenuItem("�û����");
		//�û����
		addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(curuser.getIsm().equals("1"))//�ǹ���Ա������ǰ����
				// TODO Auto-generated method stub
				{
					new AddUserDialog();
				}
				else {
					JOptionPane.showMessageDialog(null,"��û�и�Ȩ��");
				}
				
			}
		});
		delUser=new JMenuItem("�û�ɾ��");
		
		userTable=new JMenuItem("�û��б�");
		updateUser=new JMenuItem("�û��޸�");
		updatePwd=new JMenuItem("�����޸�");
	
		perfectInfo=new JMenuItem("������Ϣ");
	    userLogout=new JMenuItem("ע���˺�");
	    ypjh=new JMenuItem("ҩƷ����");
	    supinit=new JMenuItem("��Ӧ�̳�ʼ��");
	    kcinit=new JMenuItem("����ʼ��");
		sell=new JMenuItem("ҩƷ����");
		drawback=new JMenuItem("�ۺ��˿�");
		accountM.add(userDetial);//��ӵ��û�����˵�
		
		userM.add(addUser);//��ӵ�"�û�����"�˵�
		userM.add(delUser);//ɾ���û�
		userM.add(userTable);
		userM.add(updateUser);//�޸��û���Ϣ
		
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
		System.out.println("dp��ʼ�����"+dp);
		mainMenuFrameEventInit(login,this,MainMenu.curUser);
		this.getContentPane().add(dp,BorderLayout.CENTER);
		
		//�ڲ�����
		
	}
	private void mainMenuFrameInit()
	{

		this.setTitle("ϵͳ���˵�");
		this.setBounds(20, 20, 1200, 700);
		this.setResizable(false);//��ֹ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void mainMenuFrameEventInit(JFrame login,JFrame jf, TbYhinfo curUser2) {	
		//�˻�����
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
		//�û�ɾ��
		delUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(curUser.getIsm().equals("1"))//�ǹ���Ա������ǰ����
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
						JOptionPane.showMessageDialog(null,"��û�и�Ȩ��");
					}
				
			}
		});
		//�û���ϸ��Ϣ�б�
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
					JOptionPane.showMessageDialog(null,"��û�и�Ȩ��");
				}
			}
		});
		//�����޸�
		updatePwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MotifiedPwdDialog(jf,curUser2);
			}
		});
		//������Ϣ
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
		//ע���˺�
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
			JOptionPane.showMessageDialog(null,"ע���ɹ���");
			login.setVisible(true);	
			}
		});
		//�޸��û���Ϣ
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
					JOptionPane.showMessageDialog(null,"��û�и�Ȩ��");
				}
			  
			}});
		//ҩƷ����
		ypjh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new YpjhFrame().setVisible(true);
			}});
		//��Ӧ�̳�ʼ��
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
					JOptionPane.showMessageDialog(null,"����ʼ����ɣ�");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					SystemLog.out("����ʼ�����		����Ա:"+LoginFrame.cur_user.getName());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		//����ڲ�����
		userinframe=new UserInFrame("ҩƷ��Ϣ��");
		kcinframe=new KcInframe();
		sf=new SellFrame();
		jf.addWindowListener(new DefinedListener(userinframe,dp));
		jf.addWindowListener(new DefinedListener(kcinframe,dp));
		jf.addWindowListener(new DefinedListener(sf,dp));
		sell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�򿪿���
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
//				System.out.println("���������");
//			}
		}
				//�����۲������
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
