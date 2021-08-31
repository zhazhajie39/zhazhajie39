package com.dims.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dims.controller.SellController;
import com.dims.dao.KcDbOprator;
import com.dims.dao.MmDbOprator;
import com.dims.dao.SellDbOprator;
import com.dims.dao.model.TbXsinfo;
import com.dims.log.SystemLog;

public class SellFrame  extends JInternalFrame {

	/**
	 * 销售窗口
	 * 提供药品名称，药品数完成销售
	 * 顾客名称，顾客联系方式‘
	 * 需要用到库存表，销售主表
	 */
	private static final long serialVersionUID = 1L;
    private ArrayList<TbXsinfo> sellinfo;//记录所有待销售的药品
	private JTextField id,name,num,cashier,customname,customtel;//药品编号，药品名，采购数，销售员,客户名,客户联系电话
	private JLabel show;//显示销售总金额
	private float money;//销售总金额
	private DefaultTableModel tm;//定义表格模型对象
	private final String[] colums= {"药品编号","药品名称","采购数","经手人","客户名","客户联系电话","金额"};//列属性
	private JTable buytb;//采购表
	private String [][] tbVal;
	private String []  data;
	private JButton add,sellbtn,clear;//添加售出按钮
	private String [] para;
	public SellFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		sellFrameInit();
		sellFramePanelInit();
		addEventListener();
	}
	//初始化库存表格
	private void sellFrameInit() {
		this.setTitle("销售窗口");
		this.setResizable(true);//允许调整大小
		
		//this.setIconifiable(true);//允许使用关闭按钮图标
		this.setMaximizable(true);//设置最大化按钮
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setClosable(true);//允许提供关闭按钮
		this.setBounds(404, 4, 600, 500);
	}
	public String[] getPara() throws SQLException {
//		info.setMid(para[0]);
//		info.setMname(para[1]);
//		info.setNum(Integer.parseInt(para[2]));
//		info.setOprator(para[3]);
//		info.setSeldate(para[4]);
//		info.setCustom(para[5]);
//		info.setCustomtel(para[6]);
//		info.setTotal(Float.parseFloat(para[7]));
		para[0]=id.getText().trim();data[0]=para[0];
		para[1]=name.getText().trim();data[1]=para[1];
		para[2]=num.getText().trim();data[2]=para[2];
		para[3]=cashier.getText().trim();data[3]=para[3];
		para[4]=SystemLog.getDate(3);
		para[5]=customname.getText().trim();data[4]=para[5];
		para[6]=customtel.getText().trim();data[5]=para[6];
		
		float upri=Float.parseFloat(MmDbOprator.getMmUpriByID(para[0]));
		int num=Integer.parseInt(para[2].equals("")?"0":para[2]);
		System.out.println(num);
		float total=num*upri;
		para[7]=Float.toString(total);data[6]=para[7];
		return para;
	}
	private void sellFramePanelInit() {
		sellinfo=new ArrayList<TbXsinfo>();
		para=new String[colums.length+1];
		data=new String[colums.length];
		final JPanel n=new JPanel();
		n.setLayout(new GridLayout(4,1));
		final JPanel p1=new JPanel();
		final JPanel p2=new JPanel();
		final JPanel p3=new JPanel();
		p1.add(new JLabel("药品编号："));id=new JTextField(15);p1.add(id);
		p1.add(new JLabel("药品名称："));name=new JTextField(15);p1.add(name);
		p1.add(new JLabel("\t采购数："));num=new JTextField(15);p1.add(num);num.setText("1");
		p2.add(new JLabel("   经手人："));cashier=new JTextField(15);p2.add(cashier);cashier.setText(MainMenu.curUser.getName());
		p2.add(new JLabel("  客户名："));customname=new JTextField(15);p2.add(customname);
		p2.add(new JLabel("客户电话："));customtel=new JTextField(15);p2.add(customtel);
		add=new JButton("添加");p3.add(add);
		n.add(p1);n.add(p2);n.add(p3);
		this.getContentPane().add(n,BorderLayout.NORTH);
		//采购表
		tm= new DefaultTableModel(tbVal,colums) {
			private static final long serialVersionUID = 1L;
			@Override  
	            public boolean isCellEditable(int row,int column){ 
	              System.out.println("不可编辑");
	            		  return false;
	            }  
		};
		
		buytb=new JTable(tm);
		buytb.setRowHeight(30);
		final JScrollPane jp=new JScrollPane(buytb);
		this.getContentPane().add(jp,BorderLayout.CENTER);
		
		//销售面板
		final JPanel s=new JPanel();
		s.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		show=new JLabel(Float.toString(money));
		clear=new JButton("清空");s.add(clear);
		s.add(new JLabel("总计金额："));s.add(show);
		sellbtn=new JButton("出售");s.add(sellbtn);
		
		this.getContentPane().add(s,BorderLayout.SOUTH);
	}
	private void  addEventListener() {
		//添加
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(SellController.checkInput(getPara())) {
						//System.out.println(SellController.getInput(para).toString());//输入的参数封装到信息模型中
						//同名药品数相加必须小于或等于库存数
						//data表示当前要销售的药品信息
						if(isSpillover(data[0],Integer.parseInt(data[2]),sellinfo))
						{
							return ;
						}
						tm.addRow(data);
						//添加到表格中与sellinfo中
						sellinfo.add(SellController.getInput(para));
						System.out.println(sellinfo.size());
						//累加金额
						money=caculateCash(sellinfo);
						show.setText(Float.toString(money));//显示金额
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//清空
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//sellinfo移除所有内容
				int len=sellinfo.size();
				if(len>0) {
				tm.setDataVector(tbVal, colums);//刷新清空
				sellinfo.clear();;
				System.out.println(sellinfo.size());
				money=0;
				show.setText(Float.toString(money));//显示金额
				}
				//移除表格中的所有内容
			}
		});
		//售出
		sellbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//sellinfo移除所有内容
				int len=sellinfo.size();
				//如果有商品
				if(len>0) {
				
					for(int i=0;i<len;i++)
					{
					try {
						//药品表减少对应药品数量
						MmDbOprator.updateNumByID(sellinfo.get(i).getMid(),sellinfo.get(i).getNum(), false);
						//库存表减少对应药品库存数量
						KcDbOprator.updateNumByID(sellinfo.get(i).getMid(),sellinfo.get(i).getNum(), false);
						//添加到销售单
						SellDbOprator.addSellInfo(sellinfo.get(i));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
			    //处理完后刷新库存表
					MainMenu.kcinframe.refreshTb(MainMenu.kcinframe.getCurkey(),MainMenu.kcinframe.getCurpage());
				//清空待销售表
					tm.setDataVector(tbVal, colums);//刷新清空
				//清空数组
					sellinfo.clear();
				//归零
					money=0;
					show.setText(Float.toString(money));//显示金额
					System.out.println("销售成功");
				}
				else {
					System.out.println("无销售药品");
				}
				//如果销售表可见，刷新销售表
				//没有不作处理
			}
		});
		
	
	}
	//是否溢出
	public boolean isSpillover(String mid,int inputnum,ArrayList<TbXsinfo> sellinfo) throws SQLException {
		int len =sellinfo.size();
		if(len==0) {
			return false;
		}
		int num=0;
		for(int i=0;i<len;i++) {
			if(mid.equals(sellinfo.get(i).getMid())) {
				num+=sellinfo.get(i).getNum();
				System.out.println("i:"+i+"num:"+num);
			}
		}
			num+=inputnum;
			System.out.println("num:"+num);
		return SellController.isSpillover(num, mid);
	}
	//计算金额
		private float caculateCash(ArrayList<TbXsinfo> sellinfo)
		{
			int len =sellinfo.size();
			float m=0f;
			for(int i=0;i<len;i++) {
				m+=sellinfo.get(i).getTotal();
				}
			return m;
		}
	public void setIdName(String id,String name) {
		this.id.setText(id);
		this.name.setText(name);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SellFrame sf=new SellFrame();
		sf.setVisible(true);
	}

}
