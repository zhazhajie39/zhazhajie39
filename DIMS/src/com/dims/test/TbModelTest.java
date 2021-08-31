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
 *ά�����ģ�Ͳ��Դ���
 */
public class TbModelTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int cnoteditcol[]= {0};//���ɱ༭����
	private JPopupMenu pm;
	private JMenuItem cutItem;
	private DefaultTableModel tm;//������ģ�Ͷ���
	private JTable tb;//������
	private final String[] colums= {"a","b"};//������
    private String[][] data= {{"a1","b1"},{"a2","b2"},{"a3","b3"}};//�������
    private int shownum=2;//ÿҳ��ʾ������
    private String[][] pagedata;//��ǰҳ��Ҫ��ʾ������
    private int totalnum=3;//��������
    private int page=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;//��ҳ��  ��������=[(curpage-1)*shownum+currow+1];
	private JButton pre,next;
	private JLabel showpage,totalpage;
	private JPanel p1;
	private int curpage;//��ǰҳ��
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
		this.setTitle("ά�����ģ�Ͳ���");
		this.setBounds(300,40, 800, 600);
		this.setResizable(true);//��ֹ����
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
	            		  System.out.println("���ɱ༭");
	            		  return false;
	            	  }
	              }
	            	  return true;
	            }  
		};
		pm=new JPopupMenu() ;
		cutItem=new JMenuItem("ɾ��");
		pm.add(cutItem);
		tb=new JTable(tm);
		tb.setRowHeight(30);
		
		JScrollPane jp=new JScrollPane(tb);
		p1=new JPanel();
		pre=new JButton("��һҳ");
		showpage=new JLabel("��"+curpage+"ҳ");
		next=new JButton("��һ��");
		totalpage=new JLabel("��"+page+"ҳ");
		p1.add(pre);p1.add(showpage);p1.add(next);p1.add(totalpage);
		this.getContentPane().add(jp,BorderLayout.CENTER);
		this.getContentPane().add(p1,BorderLayout.SOUTH);
	}
	private void addListener() {
		
		tb.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {//����
				// TODO Auto-generated method stub
				int type=e.getButton();
				if(type==MouseEvent.BUTTON3)//�Ҽ�
				{
					//�����˵�
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
				int type=e.getType();//��ȡ�¼�����
				int row=e.getFirstRow();//�������¼��ı��������
				int col=e.getColumn();//��ô������¼��ı��������
				if(type==TableModelEvent.INSERT) {//����
					
				}
				else if(type==TableModelEvent.UPDATE) {//����
//					String oldval=data[row][col];
//					System.out.println(oldval);
//					String newval=(String) tm.getValueAt(row, col);
//					System.out.println(newval);
//					if(newval.equals(oldval)) {
//						System.out.println("δ����");
//					}else {
//					System.out.println(row+","+col+"����");
//					}
				}
				else if(type==TableModelEvent.DELETE) {//ɾ��
					
				}
				else {//����
					
				}
			}
			
		});
		cutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ɾ������
				int sr=tb.getSelectedRow();//��ȡ��ǰ�е�����
				System.out.println(sr);
				if(sr!=-1) {
					tm.removeRow(sr);
					delData(sr);
					System.out.println("ɾ��");
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
				showpage.setText("��"+curpage+"ҳ");
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
				showpage.setText("��"+curpage+"ҳ");
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
		if(curpage==page) {//�����һҳ
		pagedata=new String[totalnum%shownum][colums.length];
		}
		else {//����ҳ
		pagedata=new String[shownum][colums.length];
		}
		//��ʼ��ֵ
		for(int row=0;row<pagedata.length;row++)
		{
			pagedata[row]=data[(curpage-1)*shownum+row];
		}
	}
	private void refreshTb(int curpage) {
		getPageData(curpage);
		System.out.println();
		tm.setDataVector(pagedata, colums);
		System.out.println("ҳ�������������");
	}
}


