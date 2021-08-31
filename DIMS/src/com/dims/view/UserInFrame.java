package com.dims.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.dims.dao.Dao;
import com.dims.dao.MmDbOprator;
import com.dims.dao.model.TbMminfo;

/**
 * 主菜单内部窗体
 * @author 86157
 *
 */
public class UserInFrame  extends  JInternalFrame{
	
	
	/**
	 * 药品信息表
	 */
	private static final long serialVersionUID = 1L;
	private final static int cnoteditcol[]= {0};//不可编辑的列
	private JPopupMenu pm;
	private JMenuItem cutItem,addItem;
	private DefaultTableModel tm;//定义表格模型对象
	private JTable tb;//表格对象
	private final String[] colums= {"药品ID","药品名称","类别","单件","进货价","产地","规格","数量","生产日期","过期日","供应商","备注","单位"};//列属性
    private  int shownum=3;//每页显示的数量
    private String[][] pagedata;//当前页面要显示的数据
    private int totalnum;//数据总数
    private int page;//=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;//总页数  数据索引=[(curpage-1)*shownum+currow+1];
	private JButton pre,next,searchbtn;
	private JTextField searchtext;//搜索框
	private JComboBox<String>  sbox;//搜索类型
	private final static String[] stype= {"按名称","按类别","按供应商"};//搜索类别
	private JLabel showpage,totalpage;
	private JPanel p1,pn;
	private int curpage;//当前页数
	private boolean isfinding;
    private TableModelListener ml;
	public UserInFrame() {
		// TODO Auto-generated constructor stub
		this.title="测试";
	}
	public UserInFrame(String title) {
		// TODO Auto-generated constructor stub
		userInFrameInit(title);
		
		userInFramePanelInit();
		addListener();
		System.out.println("内部窗体初始化完成");
	}
	private void userInFrameInit(String title) {
		this.title=title;
		this.setResizable(true);//允许调整大小
	
		//this.setIconifiable(true);//允许使用关闭按钮图标
		this.setMaximizable(true);//设置最大化按钮
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setClosable(true);//允许提供关闭按钮
		this.setBounds(2, 2, 1000, 500);
		//this.setVisible(true);默认关闭
	}
	private void tbValueInit() throws SQLException {
		totalnum=Dao.getInfoNum("tb_mm");
		System.out.println("总数："+totalnum);
		if(totalnum==0)
		{
			next.setForeground(Color.gray);
			pre.setForeground(Color.gray);
			totalpage.setText("共0页");
			showpage.setText("第0页");
			curpage=1;
			page=1;
			return;
		}
		page=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;
		if(page==1)
		{
			next.setForeground(Color.gray);
			pre.setForeground(Color.gray);
		}
		curpage=1;
		getPageData(curpage);
	}
	//根据用户输入的关键字初始化表格的值
	private void tbValueInitBykey(String key, int type) throws SQLException {
		totalnum=MmDbOprator.getInfoNumByKey("tb_mm",key,type);
		System.out.println("总数："+totalnum);
		if(totalnum==0)
		{
			next.setForeground(Color.gray);
			totalpage.setText("共0页");
			showpage.setText("第0页");
			pre.setForeground(Color.gray);
			curpage=1;
			page=1;
			return;
		}
		page=(totalnum%shownum==0)?totalnum/shownum:(totalnum/shownum)+1;
		totalpage.setText("共"+page+"页");
		System.out.println("页数: "+page);
		if(page==1)
		{
			next.setForeground(Color.gray);
			pre.setForeground(Color.gray);
		}
		curpage=1;
		showpage.setText("第"+curpage+"页");
		getPageDataBykey(key,type,curpage);
	}
	private  void getPageData(int curpage) throws SQLException {
		TbMminfo[] infos=MmDbOprator.getMminfoByPage(curpage,shownum);
		int row=getTrueLength(infos);
		pagedata=new String[row][colums.length];
		for(int i=0;i<row;i++) {
				pagedata[i][0]=infos[i].getId().trim();
				pagedata[i][1]=infos[i].getName().trim();
				pagedata[i][2]=infos[i].getCate();
				pagedata[i][3]=Float.toString(infos[i].getUpri());
				pagedata[i][4]=Float.toString(infos[i].getBid());
				pagedata[i][5]=infos[i].getPaddr();
				pagedata[i][6]=infos[i].getSpacs();
				pagedata[i][7]=Integer.toString(infos[i].getNum());
				pagedata[i][8]=infos[i].getPdate();
				pagedata[i][9]=infos[i].getExpirydate();
				pagedata[i][10]=infos[i].getSup();
				pagedata[i][11]=infos[i].getRemark();
				pagedata[i][12]=infos[i].getUnit();
			}
	}
	private  void getPageDataBykey(String key,int type,int curpage) throws SQLException {
		TbMminfo[] infos=MmDbOprator.getSupInfoByKey(key,type,curpage,shownum);
		int row=getTrueLength(infos);
		pagedata=new String[row][colums.length];
		for(int i=0;i<row;i++) {
			pagedata[i][0]=infos[i].getId().trim();
			pagedata[i][1]=infos[i].getName().trim();
			pagedata[i][2]=infos[i].getCate();
			pagedata[i][3]=Float.toString(infos[i].getUpri());
			pagedata[i][4]=Float.toString(infos[i].getBid());
			pagedata[i][5]=infos[i].getPaddr();
			pagedata[i][6]=infos[i].getSpacs();
			pagedata[i][7]=Integer.toString(infos[i].getNum());
			pagedata[i][8]=infos[i].getPdate();
			pagedata[i][9]=infos[i].getExpirydate();
			pagedata[i][10]=infos[i].getSup();
			pagedata[i][11]=infos[i].getRemark();
			pagedata[i][12]=infos[i].getUnit();
			}
	}
	/**
	 *存在内容的实际长度
	 */
	private int getTrueLength(TbMminfo[]  infos) {
		int length=0;
	for(int i=0;i<infos.length;i++) {
		if(infos[i]==null)
		{
			return length;
		}
		length++;
	}
	return length;
	}
	private void userInFramePanelInit() {
		isfinding=false;
		pre=new JButton("上一页");
		next=new JButton("下一页");
		try {
			tbValueInit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tm= new DefaultTableModel(pagedata,colums) {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override  
	            public boolean isCellEditable(int row,int column){ 
				
	              for(int i=0;i<cnoteditcol.length;i++) {
	            	  if(column==cnoteditcol[i]) {
	            		  System.out.println("不可编辑");
	            		  return false;
	            	  }
	              }
	            	  return true;
	            }  
		};
		popMenuInit();
		tb=new JTable(tm);
		tb.setRowHeight(30);
		
		JScrollPane jp=new JScrollPane(tb);
		pn=new JPanel();
		searchtext=new JTextField(12);pn.add(searchtext);
		btnInit();pn.add(searchbtn);
		jcomboboxInit();pn.add(sbox);
		this.getContentPane().add(pn,BorderLayout.NORTH);
		p1=new JPanel();
		showpage=new JLabel("第"+curpage+"页");
		totalpage=new JLabel("共"+page+"页");
		p1.add(pre);p1.add(showpage);p1.add(next);p1.add(totalpage);
		this.getContentPane().add(jp,BorderLayout.CENTER);
		this.getContentPane().add(p1,BorderLayout.SOUTH);
	}
	private void popMenuInit()
	{
		pm=new JPopupMenu() ;
		addItem=new JMenuItem("添加");
		cutItem=new JMenuItem("删除");
		pm.add(addItem);
		pm.add(cutItem);
	}
	private void btnInit()
	{
		searchbtn=new JButton("搜索");
		searchbtn.setBackground(Color.white);
	}
	private void jcomboboxInit() {
		sbox=new JComboBox<String>();
		for(int i=0;i<stype.length;i++) {
			sbox.addItem(stype[i]);
		}
		sbox.setBackground(Color.white);
	}
	private void addListener() {
		ml=new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int type=e.getType();//获取事件类型
				int row=e.getFirstRow();//触发此事件的表格行索引
				int col=e.getColumn();//获得触发此事件的表格列索引
				if(type==TableModelEvent.INSERT) {//插入
					
				}
				else if(type==TableModelEvent.UPDATE) {//更新
					
					String oldval=pagedata[row][col];
					System.out.println(oldval);
					String newval=(String) tm.getValueAt(row, col);
					System.out.println(newval);
					if(newval.equals(oldval)) {
						System.out.println("未更新");
					}else {
						TbMminfo info=rowValueToInfo(row);
						System.out.println(info.toString());
						
							try {
								if(MmDbOprator.updateMm(info))
								{
									//JOptionPane.showMessageDialog(null,"修改成功");
								}
								else
								{
									JOptionPane.showMessageDialog(null,"修改失败");
								}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					System.out.println(row+","+col+"更新");
					
					}
				}
				else if(type==TableModelEvent.DELETE) {//删除
					
				}
				else {//其他
					
				}
			}
			
		};
		tb.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {//单击
				// TODO Auto-generated method stub
				int type=e.getButton();
				if(type==MouseEvent.BUTTON3)//右键
				{
					//弹出菜单
					if(tb.getSelectedRowCount()>0)
					{
					pm.show(e.getComponent(), e.getX(), e.getY());
					}
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			
		});
		tm.addTableModelListener(ml);
		cutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//删除操作
				int sr=tb.getSelectedRow();//获取当前行的索引
				int type=sbox.getSelectedIndex();
				System.out.println(sr);
				if(sr!=-1) {
					tm.removeRow(sr);
					//删除数据库中的记录
					try {
						MmDbOprator.delMmById(pagedata[sr][0]);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					totalnum--;
					tm.removeTableModelListener(ml);//防止表格更新事件触发
					if(isfinding) {
						refreshTb(searchtext.getText().trim(),type,curpage);
					}
					else {
						refreshTb(curpage);
					}
					tm.addTableModelListener(ml);
					page=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;
					totalpage.setText("共"+page+"页");
					if(page==0) {
						showpage.setText("第"+page+"页");
					}
					else {
					showpage.setText("第"+curpage+"页");
					}
					System.out.println("删除");
				}
			}
			
		});
		addItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//添加操作
				new YpjhFrame().setVisible(true);;
			}
			
		});
		pre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int type=sbox.getSelectedIndex();
				if(curpage==0||curpage==1) {
					pre.setForeground(Color.gray);
					return;
				}
				else {
					pre.setForeground(Color.black);
				}
				curpage--;
				
				tm.removeTableModelListener(ml);
				if(isfinding) {
					refreshTb(searchtext.getText().trim(),type,curpage);
				}
				else {
					refreshTb(curpage);
				}
				tm.addTableModelListener(ml);
				System.out.println(curpage);
				showpage.setText("第"+curpage+"页");
			}
			
		});
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int type=sbox.getSelectedIndex();
				if(curpage==page||curpage==0) {
					next.setForeground(Color.gray);
					return;
				}
				else {
					next.setForeground(Color.black);
				}
				curpage++;
				tm.removeTableModelListener(ml);//防止表格更新事件触发
				if(isfinding) {
					refreshTb(searchtext.getText().trim(),type,curpage);
				}
				else {
					refreshTb(curpage);
				}
				tm.addTableModelListener(ml);
				System.out.println(curpage);
				showpage.setText("第"+curpage+"页");
			}
			
		});
		//搜索
		searchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String key=searchtext.getText().trim();
				int type=sbox.getSelectedIndex();
				if(key.isEmpty()||key.isBlank()) {
					isfinding=false;
				}
				else {
					isfinding=true;
					
				}
				System.out.println(isfinding);
				try {
					tbValueInitBykey(key,type);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tm.removeTableModelListener(ml);//防止表格更新事件触发
				refreshTb(key,type,curpage);//会触发更新事件
				tm.addTableModelListener(ml);
				//根据key从数据库查找相关数据
				System.out.println(key);
				
			}
			
		});
		
	
	}
	
	
	private void refreshTb(int curpage) {//刷新表格
		try {
			getPageData(curpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取当前页面的数据
		tm.setDataVector(pagedata, colums);//刷新
		System.out.println("页面数据载入完毕");
	}
	private void refreshTb(String key,int type,int curpage) {//刷新表格
		try {
			getPageDataBykey(key,type,curpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取当前页面的数据
		tm.setDataVector(pagedata, colums);//刷新
		System.out.println("页面数据载入完毕");
	}
	private TbMminfo  rowValueToInfo(int row) {
		TbMminfo info=new TbMminfo();
		int index=0;
		info.setId((String)tm.getValueAt(row, index++));
		info.setName((String)tm.getValueAt(row, index++));
		info.setCate((String)tm.getValueAt(row, index++));
		info.setUpri(Float.parseFloat((String)tm.getValueAt(row, index++)));
		info.setBid(Float.parseFloat((String)tm.getValueAt(row, index++)));
		info.setPaddr((String)tm.getValueAt(row, index++));
		info.setSpacs((String)tm.getValueAt(row, index++));
		info.setNum(Integer.parseInt((String)tm.getValueAt(row, index++)));
		info.setPdate((String)tm.getValueAt(row, index++));
		info.setExpirydate((String)tm.getValueAt(row, index++));
		info.setSup((String)tm.getValueAt(row, index++));
		info.setRemark((String)tm.getValueAt(row, index++));
		info.setUnit((String)tm.getValueAt(row, index++));
		return info;
	}
	public JInternalFrame getUserIF() {
		return this;
	}
}
