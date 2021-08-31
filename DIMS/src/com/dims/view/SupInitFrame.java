package com.dims.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

import com.dims.controller.SupController;
import com.dims.dao.model.TbMsupinfo;



public class SupInitFrame extends JFrame {

	/**
	 * 供应商初始化
	 */
	private static final String[] dsupname= {"湖南省衡阳市xx药业有限公司","湖南省邵阳市xx药业有限公司","湖南省xx市xx药业有限公司"};
	private static boolean isInit;//是否进行默认初始化
	private static final long serialVersionUID = 1L;
    private JPanel p,p1,p2,p3,p4,p5;
    private JTextField supnamet,supaddrt,supremarkt,supidt;
    private JTable tbsup;
    private final String[] colname={"id","名称","地址","备注"};
	private String[][] tbVal;//表格中要显示的数据
	private JScrollPane sp;
    private JButton dinitbtn,addsupbtn,delbtn,updatebtn,exitbtn;
    
    private JComboBox<String> supidBox;
    private JTextField supnamet1,supaddrt1,supremarkt1;
    private static int  supnum;
    
	public SupInitFrame()  {
		// TODO Auto-generated constructor stub
		isInit=false;
		supInitJframe();//初始化窗口
		Container conn=this.getContentPane();
		conn.setLayout(null);
		p=supPanelInit();//初始化布局
		//p.setBackground(Color.gray);
		p.setBounds(10, 0, 765, 500);
		supEventInit(this);//初始化事件
		conn.add(p);
		
	} 
	/**
	 * 窗口初始化
	 */
	private void supInitJframe() {
		this.setTitle("供应商初始化");
		this.setBounds(300,40, 800, 600);
		this.setResizable(true);//禁止缩放
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
/**
 * 布局初始化
 * @return
 */
	public JPanel supPanelInit() {
		JPanel p=new JPanel();//总面板
				p1=new JPanel();//删除所有供应商信息
		        p2=new JPanel();//添加多个供应商信息
		        p3=new JPanel();//供应商信息列表
		        p4=new JPanel();//修改单个供应商信息
		        p5=new JPanel();//删除多个供应商信息
		p.setLayout(null);
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.setBounds(0, 0, 765, 40);
		dinitbtn=new MyButton("默认初始化",Color.red);
		p1.add(dinitbtn);
		
		p2.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		p2.setBounds(0,40, 800, 40);
		p2.add(new JLabel("供应商名称:"));supnamet=new JTextField(12);p2.add(supnamet);
		p2.add(new JLabel("地址:"));supaddrt=new JTextField(12);p2.add(supaddrt);
		p2.add(new JLabel("备注:"));supremarkt=new JTextField(30);p2.add(supremarkt);
		addsupbtn=new MyButton("添加",Color.red);p2.add(addsupbtn);
		
		p3.setBounds(0,80, 765,175);
		p3.setLayout(new BorderLayout());
		p3.setBackground(Color.blue);
		sp=new JScrollPane();
	
		try {
			tbVal= setTbVal();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tbsup= new JTable(tbVal,colname);
		tbsup.setRowHeight(30);//设置高为30
		tbsup.setEnabled(false);
		sp.setViewportView(tbsup);
		p3.add(sp,BorderLayout.CENTER);
		
		p4.setBounds(0,275, 765,40);
		p4.add(new JLabel("供应商ID:"));supidt=new JTextField(10);p4.add(supidt);
	    delbtn=new MyButton("删除",Color.red);
		p4.add(delbtn);
		
		p5.setBounds(0, 325, 765, 120);
		p5.setLayout(new GridLayout(5,2,0,0));
		p5.add(new JLabel("ID:"));supidBox=new JComboBox<String>();
		for(int i=0;i<supnum;i++) {
			supidBox.addItem(tbVal[i][0]);	
		}
		p5.add(supidBox);
		p5.add(new JLabel("名称:"));supnamet1=new JTextField(10);supnamet1.setText(tbVal[0][1]);p5.add(supnamet1);
		p5.add(new JLabel("地址:"));supaddrt1=new JTextField(10);supaddrt1.setText(tbVal[0][2]);p5.add(supaddrt1);
		p5.add(new JLabel("备注:"));supremarkt1=new JTextField(10);supremarkt1.setText(tbVal[0][3]);p5.add(supremarkt1);
		exitbtn=new MyButton("",Color.gray);exitbtn.setVisible(false);;p5.add(exitbtn);
		updatebtn=new MyButton("修改",Color.red);p5.add(updatebtn);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		return p;
	}
	private String[][]  setTbVal() throws SQLException{
		    supnum=TbMsupinfo.getSupNum();
		    if(supnum==0) {
		    	String[][] infostr=new String[1][4];
		    	infostr[0][0]="";
				infostr[0][1]="";
				infostr[0][2]="";
				infostr[0][3]="";
				return infostr;
		    }
			TbMsupinfo[] infos=TbMsupinfo.getAllSupInfo();
			String[][] infostr=new String[supnum][4];
		for(int i=0;i<supnum;i++)
		{
			infostr[i][0]=infos[i].getId().trim();
			infostr[i][1]=infos[i].getName().trim();
			infostr[i][2]=infos[i].getAddr().trim();
			infostr[i][3]=infos[i].getRemark().trim();
		}
		return infostr;
	}
	private void refreshTb() {
		try {
			tbVal= setTbVal();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tbsup= new JTable(tbVal,colname);
		tbsup.setRowHeight(30);//设置高为30
		tbsup.setEnabled(false);
		sp.setViewportView(tbsup);
		int num=supidBox.getItemCount();
		for(int i=0;i<supnum;i++)
		{
			supidBox.addItem(tbVal[i][0]);
		}
		for(int i=num-1;i>=0;i--)
		{
			supidBox.removeItem(supidBox.getItemAt(i));
		}
		supnamet1.setText(tbVal[0][1]);
		supaddrt1.setText(tbVal[0][2]);
		supremarkt1.setText(tbVal[0][3]);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supEventInit(JFrame jf)
	{
		dinitbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/**
				 * 删除所有供应商信息并进行默认初始化
				 * 
				 */
				try {
					if(!isInit) {
					if(TbMsupinfo.delAllSup()>=0)
					{
						TbMsupinfo.updateIncreament();//设置自增值
						TbMsupinfo[] infos=new TbMsupinfo[dsupname.length];
						for(int i=0;i<dsupname.length;i++) {
							infos[i]=new TbMsupinfo();
							infos[i].setName(dsupname[i]);
							infos[i].setAddr("无");
							infos[i].setRemark("无");
							TbMsupinfo.addSup(infos[i]);
						}
						refreshTb();
						JOptionPane.showMessageDialog(null,"默认初始化成功");
						isInit=true;
					}
					else{
						JOptionPane.showMessageDialog(null,"默认初始化失败");
					}
					}
					else {
						JOptionPane.showMessageDialog(null,"默认初始化已完成无需重复执行");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addsupbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String [] para=getInput(3);
				if(SupController.checkInput(para)) {
					TbMsupinfo info=SupController.getInput(para);
					try {
						if(TbMsupinfo.addSup(info))
						{
							supnum++;
							refreshTb();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"添加失败");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		delbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id=supidt.getText().trim();
				try {
					if(TbMsupinfo.haveId(id)) {
					if(TbMsupinfo.delSup(id)) {
						supnum--;
					
						refreshTb();
						supidt.setText("");
						JOptionPane.showMessageDialog(null,"删除成功");
					}
					else {
						JOptionPane.showMessageDialog(null,"删除失败");
					}
					}
					else {
						JOptionPane.showMessageDialog(null,"未找到供应商");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		supidBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int index=supidBox.getSelectedIndex();
				supnamet1.setText(tbVal[index][1]);
				supaddrt1.setText(tbVal[index][2]);
				supremarkt1.setText(tbVal[index][3]);
				
			}
		});
		updatebtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] para=getInput1(4);
				int index=supidBox.getSelectedIndex();
				String lastname=tbVal[index][1];
				if(SupController.checkInput(para,lastname)) {
					TbMsupinfo info=SupController.getInput1(para);
					try {
						if(TbMsupinfo.updateSup(info))
						{
							refreshTb();
							supidBox.setSelectedIndex(index);
							JOptionPane.showMessageDialog(null,"修改成功");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"修改失败");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
	}
	/**
	 * 得到用户输入并返回
	 * @param num 需要获取值的控件的个数
	 * @return String[]
	 */
	private String[] getInput(int num) {
		String [] para=new String[num];
		para[0]=supnamet.getText().trim();
		para[1]=supaddrt.getText().trim();
		para[2]=supremarkt.getText().trim();
		return para;
	}
	private String[] getInput1(int num) {
		String [] para=new String[num];
		para[0]=supnamet1.getText().trim();
		para[1]=supaddrt1.getText().trim();
		para[2]=supremarkt1.getText().trim();
		para[3]=(String)supidBox.getSelectedItem();
		return para;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new SupInitFrame().setVisible(true);
	}
	class MyButton extends JButton  {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 * @param content 标题内容
		 * @param color 字的颜色
		 */
		public MyButton(String content,Color color) {
			this.setText(content);
			this.setFocusPainted(false);
			this.setForeground(color);
		}
	}

}
