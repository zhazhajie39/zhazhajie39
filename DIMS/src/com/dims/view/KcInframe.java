package com.dims.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.dims.dao.KcDbOprator;
import com.dims.dao.model.TbKcinfo;

public class KcInframe extends JInternalFrame {

	/**
	 * 
	 */
	
	
	//private final static int cnoteditcol[]= {0};//不可编辑的列
	//private final static String[] stype= {"按名称","按类别","按供应商"};//搜索类别
	private JPopupMenu pm;
	private JMenuItem cutItem;
	private DefaultTableModel tm;//定义表格模型对象
	private JTable tb;//表格对象
	private final String[] colums= {"药品ID","药品名称","药品总数","药品单价"};//列属性
    private  int shownum=5;//每页显示的数量
    private  String[][] pagedata;//当前页面要显示的数据
    private int totalnum;//数据总数
    private int page;//=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;//总页数  数据索引=[(curpage-1)*shownum+currow+1];
	private JButton pre,next,searchbtn;
	private JTextField searchtext;//搜索框
	//private final JComboBox<String>  sbox;//搜索类型
	private JLabel showpage,totalpage;
	private JPanel p1,pn;
	private  int curpage;//当前页数,销售界面要用到
	private String curkey="";
	private boolean isfinding;
    public int getCurpage() {
		return curpage;
	}
    public String getCurkey() {
    	return curkey;
    }
	private TableModelListener ml;
	public KcInframe() {
		// TODO Auto-generated constructor stub
		tbModelFrameInit();
		tbModelPanelInit();
		addListener();
		System.out.println("库存表初始化完成");
	}

	public void JJ() {
		
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//			new SupTbTest().setVisible(true);
//	}
	
	private void tbModelFrameInit() {
		this.title="库存表";
		this.setResizable(true);//允许调整大小
	
		//this.setIconifiable(true);//允许使用关闭按钮图标
		this.setMaximizable(true);//设置最大化按钮
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setClosable(true);//允许提供关闭按钮
		this.setBounds(4, 4, 400, 500);
		//this.setVisible(true);
	}
	private void tbValueInit() throws SQLException {
		totalnum=KcDbOprator.getKcNum();
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
	private void tbValueInitBykey(String key) throws SQLException {
		totalnum=KcDbOprator.getKcNumByKey(key);
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
		getPageDataBykey(key,curpage);
	}
	public  void getPageData(int curpage) throws SQLException {
		TbKcinfo[] infos=KcDbOprator.getKcinfoByPage(curpage,shownum);
		int row=getTrueLength(infos);
		pagedata=new String[row][colums.length];
		for(int i=0;i<row;i++) {
		
				pagedata[i][0]=infos[i].getMid();
				pagedata[i][1]=infos[i].getMname();
				pagedata[i][2]=Integer.toString(infos[i].getNum());
				pagedata[i][3]=Float.toString(infos[i].getUpri());
			}
	}
	private  void getPageDataBykey(String key,int curpage) throws SQLException {
		TbKcinfo[] infos=KcDbOprator.getKcInfoByKey(key,curpage,shownum);
		curkey=key;
		int row=getTrueLength(infos);
		pagedata=new String[row][colums.length];
		for(int i=0;i<row;i++) {
		
			pagedata[i][0]=infos[i].getMid();
			pagedata[i][1]=infos[i].getMname();
			pagedata[i][2]=Integer.toString(infos[i].getNum());
			pagedata[i][3]=Float.toString(infos[i].getUpri());
			}
	}
	/**
	 *存在内容的实际长度
	 */
	private static int getTrueLength(TbKcinfo[]  infos) {
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
	private void tbModelPanelInit() {
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
				
	                System.out.println("不可修改");
	            		  return false;
	            	  
	            }  
		};
		pm=new JPopupMenu() ;
		cutItem=new JMenuItem("删除");
		pm.add(cutItem);
		tb=new JTable(tm);
		tb.setRowHeight(30);
		
		JScrollPane jp=new JScrollPane(tb);
		pn=new JPanel();
		searchtext=new JTextField(12);pn.add(searchtext);
		searchbtn=new JButton("搜索");pn.add(searchbtn);
		this.getContentPane().add(pn,BorderLayout.NORTH);
		p1=new JPanel();
		showpage=new JLabel("第"+curpage+"页");
		totalpage=new JLabel("共"+page+"页");
		p1.add(pre);p1.add(showpage);p1.add(next);p1.add(totalpage);
		this.getContentPane().add(jp,BorderLayout.CENTER);
		this.getContentPane().add(p1,BorderLayout.SOUTH);
	}
	private void addListener() {
		ml=new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				
			}
			
		};
		tb.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {//单击
				// TODO Auto-generated method stub
				int type=e.getButton();
//				if(type==MouseEvent.BUTTON3)//右键
//				{
//					//弹出菜单
//					if(tb.getSelectedRowCount()>0)
//					{
//					pm.show(e.getComponent(), e.getX(), e.getY());
//					}
//				}
				if(type==MouseEvent.BUTTON1)//左键
					{
					//如果销售窗体打开了
					if(MainMenu.sf.isShowing()) {
						System.out.println("销售窗口打开了");
						System.out.println("你点击了左键");
						int row=tb.getSelectedRow();
						String id=pagedata[row][0];
						String name=pagedata[row][1];
						MainMenu.sf.setIdName(id, name);
						}
					else {
						System.out.println("销售窗口关闭了");
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

//	@Override
	public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				//删除操作
//				int sr=tb.getSelectedRow();//获取当前行的索引
//				System.out.println(sr);
//				if(sr!=-1) {
//					tm.removeRow(sr);
//					//删除数据库中的记录
//					try {
//						TbMsupinfo.delSup(pagedata[sr][0]);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					totalnum--;
//					tm.removeTableModelListener(ml);//防止表格更新事件触发
//					if(isfinding) {
//						refreshTb(searchtext.getText().trim(),curpage);
//					}
//					else {
//						refreshTb(curpage);
//					}
//					tm.addTableModelListener(ml);
//					page=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;
//					totalpage.setText("共"+page+"页");
//					if(page==0) {
//						showpage.setText("第"+page+"页");
//					}
//					else {
//					showpage.setText("第"+curpage+"页");
//					}
//					System.out.println("删除");
//				}
	}
			
		});
		pre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
					refreshTb(searchtext.getText().trim(),curpage);
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
					refreshTb(searchtext.getText().trim(),curpage);
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
				if(key.isEmpty()||key.isBlank()) {
					isfinding=false;
				}
				else {
					isfinding=true;
					
				}
				System.out.println(isfinding);
				try {
					tbValueInitBykey(key);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tm.removeTableModelListener(ml);//防止表格更新事件触发
				refreshTb(key,curpage);//会触发更新事件
				tm.addTableModelListener(ml);
				//根据key从数据库查找相关数据
				System.out.println(key);
				
			}
			
		});
		
	}
	
	
	public void refreshTb(int curpage) {//刷新表格
		try {
			getPageData(curpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取当前页面的数据
		tm.setDataVector(pagedata, colums);//刷新
		System.out.println("页面数据载入完毕");
	}
	public  void refreshTb(String key,int curpage) {//刷新表格
		try {
			getPageDataBykey(key,curpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取当前页面的数据
		tm.setDataVector(pagedata, colums);//刷新
		System.out.println("key页面数据载入完毕");
	}
	private static final long serialVersionUID = 1L;

	
	public KcInframe(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public KcInframe(String title, boolean resizable) {
		super(title, resizable);
		// TODO Auto-generated constructor stub
	}

	public KcInframe(String title, boolean resizable, boolean closable) {
		super(title, resizable, closable);
		// TODO Auto-generated constructor stub
	}

	public KcInframe(String title, boolean resizable, boolean closable, boolean maximizable) {
		super(title, resizable, closable, maximizable);
		// TODO Auto-generated constructor stub
	}

	public KcInframe(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		// TODO Auto-generated constructor stub
	}

}
