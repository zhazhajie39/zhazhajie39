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
	
	
	//private final static int cnoteditcol[]= {0};//���ɱ༭����
	//private final static String[] stype= {"������","�����","����Ӧ��"};//�������
	private JPopupMenu pm;
	private JMenuItem cutItem;
	private DefaultTableModel tm;//������ģ�Ͷ���
	private JTable tb;//������
	private final String[] colums= {"ҩƷID","ҩƷ����","ҩƷ����","ҩƷ����"};//������
    private  int shownum=5;//ÿҳ��ʾ������
    private  String[][] pagedata;//��ǰҳ��Ҫ��ʾ������
    private int totalnum;//��������
    private int page;//=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;//��ҳ��  ��������=[(curpage-1)*shownum+currow+1];
	private JButton pre,next,searchbtn;
	private JTextField searchtext;//������
	//private final JComboBox<String>  sbox;//��������
	private JLabel showpage,totalpage;
	private JPanel p1,pn;
	private  int curpage;//��ǰҳ��,���۽���Ҫ�õ�
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
		System.out.println("�����ʼ�����");
	}

	public void JJ() {
		
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//			new SupTbTest().setVisible(true);
//	}
	
	private void tbModelFrameInit() {
		this.title="����";
		this.setResizable(true);//���������С
	
		//this.setIconifiable(true);//����ʹ�ùرհ�ťͼ��
		this.setMaximizable(true);//������󻯰�ť
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setClosable(true);//�����ṩ�رհ�ť
		this.setBounds(4, 4, 400, 500);
		//this.setVisible(true);
	}
	private void tbValueInit() throws SQLException {
		totalnum=KcDbOprator.getKcNum();
		System.out.println("������"+totalnum);
		if(totalnum==0)
		{
			next.setForeground(Color.gray);
			pre.setForeground(Color.gray);
			totalpage.setText("��0ҳ");
			showpage.setText("��0ҳ");
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
	//�����û�����Ĺؼ��ֳ�ʼ������ֵ
	private void tbValueInitBykey(String key) throws SQLException {
		totalnum=KcDbOprator.getKcNumByKey(key);
		System.out.println("������"+totalnum);
		if(totalnum==0)
		{
			next.setForeground(Color.gray);
			totalpage.setText("��0ҳ");
			showpage.setText("��0ҳ");
			pre.setForeground(Color.gray);
			curpage=1;
			page=1;
			return;
		}
		page=(totalnum%shownum==0)?totalnum/shownum:(totalnum/shownum)+1;
		totalpage.setText("��"+page+"ҳ");
		System.out.println("ҳ��: "+page);
		if(page==1)
		{
			next.setForeground(Color.gray);
			pre.setForeground(Color.gray);
		}
		curpage=1;
		showpage.setText("��"+curpage+"ҳ");
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
	 *�������ݵ�ʵ�ʳ���
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
		pre=new JButton("��һҳ");
		next=new JButton("��һҳ");
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
				
	                System.out.println("�����޸�");
	            		  return false;
	            	  
	            }  
		};
		pm=new JPopupMenu() ;
		cutItem=new JMenuItem("ɾ��");
		pm.add(cutItem);
		tb=new JTable(tm);
		tb.setRowHeight(30);
		
		JScrollPane jp=new JScrollPane(tb);
		pn=new JPanel();
		searchtext=new JTextField(12);pn.add(searchtext);
		searchbtn=new JButton("����");pn.add(searchbtn);
		this.getContentPane().add(pn,BorderLayout.NORTH);
		p1=new JPanel();
		showpage=new JLabel("��"+curpage+"ҳ");
		totalpage=new JLabel("��"+page+"ҳ");
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

			public void mouseClicked(MouseEvent e) {//����
				// TODO Auto-generated method stub
				int type=e.getButton();
//				if(type==MouseEvent.BUTTON3)//�Ҽ�
//				{
//					//�����˵�
//					if(tb.getSelectedRowCount()>0)
//					{
//					pm.show(e.getComponent(), e.getX(), e.getY());
//					}
//				}
				if(type==MouseEvent.BUTTON1)//���
					{
					//������۴������
					if(MainMenu.sf.isShowing()) {
						System.out.println("���۴��ڴ���");
						System.out.println("���������");
						int row=tb.getSelectedRow();
						String id=pagedata[row][0];
						String name=pagedata[row][1];
						MainMenu.sf.setIdName(id, name);
						}
					else {
						System.out.println("���۴��ڹر���");
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
//				//ɾ������
//				int sr=tb.getSelectedRow();//��ȡ��ǰ�е�����
//				System.out.println(sr);
//				if(sr!=-1) {
//					tm.removeRow(sr);
//					//ɾ�����ݿ��еļ�¼
//					try {
//						TbMsupinfo.delSup(pagedata[sr][0]);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					totalnum--;
//					tm.removeTableModelListener(ml);//��ֹ�������¼�����
//					if(isfinding) {
//						refreshTb(searchtext.getText().trim(),curpage);
//					}
//					else {
//						refreshTb(curpage);
//					}
//					tm.addTableModelListener(ml);
//					page=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;
//					totalpage.setText("��"+page+"ҳ");
//					if(page==0) {
//						showpage.setText("��"+page+"ҳ");
//					}
//					else {
//					showpage.setText("��"+curpage+"ҳ");
//					}
//					System.out.println("ɾ��");
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
				showpage.setText("��"+curpage+"ҳ");
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
				tm.removeTableModelListener(ml);//��ֹ�������¼�����
				if(isfinding) {
					refreshTb(searchtext.getText().trim(),curpage);
				}
				else {
					refreshTb(curpage);
				}
				tm.addTableModelListener(ml);
				System.out.println(curpage);
				showpage.setText("��"+curpage+"ҳ");
			}
			
		});
		//����
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
				tm.removeTableModelListener(ml);//��ֹ�������¼�����
				refreshTb(key,curpage);//�ᴥ�������¼�
				tm.addTableModelListener(ml);
				//����key�����ݿ�����������
				System.out.println(key);
				
			}
			
		});
		
	}
	
	
	public void refreshTb(int curpage) {//ˢ�±��
		try {
			getPageData(curpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��ȡ��ǰҳ�������
		tm.setDataVector(pagedata, colums);//ˢ��
		System.out.println("ҳ�������������");
	}
	public  void refreshTb(String key,int curpage) {//ˢ�±��
		try {
			getPageDataBykey(key,curpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��ȡ��ǰҳ�������
		tm.setDataVector(pagedata, colums);//ˢ��
		System.out.println("keyҳ�������������");
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
