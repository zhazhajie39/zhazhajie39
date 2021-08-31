package com.dims.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *维护表格模型测试代码
 */
public class TbModelTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int cnoteditcol[]= {0};//不可编辑的列
	private JPopupMenu pm;
	private JMenuItem cutItem;
	private DefaultTableModel tm;//定义表格模型对象
	private JTable tb;//表格对象
	private final String[] colums= {"a","b"};//列属性
    private String[][] data= {{"a1","b1"},{"a2","b2"},{"a3","b3"}};//表格数据
    private int shownum=2;//每页显示的数量
    private String[][] pagedata;//当前页面要显示的数据
    private int totalnum=3;//数据总数
    private int page=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;//总页数  数据索引=[(curpage-1)*shownum+currow+1];
	private JButton pre,next;
	private JLabel showpage,totalpage;
	private JPanel p1;
	private int curpage;//当前页数
    public TbModelTest() {
		// TODO Auto-generated constructor stub
		 tbModelFrameInit();
		 tbModelPanelInit();
		 addListener();
		 this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TbModelTest();
	}
	
	private void tbModelFrameInit() {
		this.setTitle("维护表格模型测试");
		this.setBounds(300,40, 800, 600);
		this.setResizable(true);//禁止缩放
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void tbModelPanelInit() {
		if(totalnum==0) {
			curpage=0;
		}
		else {
			curpage=1;
		}
		getPageData(curpage);
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
		pm=new JPopupMenu() ;
		cutItem=new JMenuItem("删除");
		pm.add(cutItem);
		tb=new JTable(tm);
		tb.setRowHeight(30);
		
		JScrollPane jp=new JScrollPane(tb);
		p1=new JPanel();
		pre=new JButton("上一页");
		showpage=new JLabel("第"+curpage+"页");
		next=new JButton("下一个");
		totalpage=new JLabel("共"+page+"页");
		p1.add(pre);p1.add(showpage);p1.add(next);p1.add(totalpage);
		this.getContentPane().add(jp,BorderLayout.CENTER);
		this.getContentPane().add(p1,BorderLayout.SOUTH);
	}
	private void addListener() {
		
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
		tm.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int type=e.getType();//获取事件类型
				int row=e.getFirstRow();//触发此事件的表格行索引
				int col=e.getColumn();//获得触发此事件的表格列索引
				if(type==TableModelEvent.INSERT) {//插入
					
				}
				else if(type==TableModelEvent.UPDATE) {//更新
//					String oldval=data[row][col];
//					System.out.println(oldval);
//					String newval=(String) tm.getValueAt(row, col);
//					System.out.println(newval);
//					if(newval.equals(oldval)) {
//						System.out.println("未更新");
//					}else {
//					System.out.println(row+","+col+"更新");
//					}
				}
				else if(type==TableModelEvent.DELETE) {//删除
					
				}
				else {//其他
					
				}
			}
			
		});
		cutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//删除操作
				int sr=tb.getSelectedRow();//获取当前行的索引
				System.out.println(sr);
				if(sr!=-1) {
					tm.removeRow(sr);
					delData(sr);
					System.out.println("删除");
				}
			}
			
		});
		pre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(curpage==0||curpage==1) {
					return;
				}
				curpage--;
				refreshTb(curpage);
				System.out.println(curpage);
				showpage.setText("第"+curpage+"页");
			}
			
		});
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(curpage==page) {
					return;
				}
				curpage++;
				refreshTb(curpage);
				System.out.println(curpage);
				showpage.setText("第"+curpage+"页");
			}
			
		});
		
	}
	private void delData(int row) {
		data[row]=null;
		for(int i=row;i<data.length-1;i++) {
			data[row]=data[row+1];
		}
		data[data.length-1]=null;
		this.totalnum--;
		this.page=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;
	}
	private void getPageData(int curpage) {
		pagedata=null;
		if(curpage==page) {//是最后一页
		pagedata=new String[totalnum%shownum][colums.length];
		}
		else {//其他页
		pagedata=new String[shownum][colums.length];
		}
		//开始赋值
		for(int row=0;row<pagedata.length;row++)
		{
			pagedata[row]=data[(curpage-1)*shownum+row];
		}
	}
	private void refreshTb(int curpage) {
		getPageData(curpage);
		System.out.println();
		tm.setDataVector(pagedata, colums);
		System.out.println("页面数据载入完毕");
	}
}


