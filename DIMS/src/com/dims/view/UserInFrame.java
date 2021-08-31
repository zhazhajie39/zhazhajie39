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
 * ���˵��ڲ�����
 * @author 86157
 *
 */
public class UserInFrame  extends  JInternalFrame{
	
	
	/**
	 * ҩƷ��Ϣ��
	 */
	private static final long serialVersionUID = 1L;
	private final static int cnoteditcol[]= {0};//���ɱ༭����
	private JPopupMenu pm;
	private JMenuItem cutItem,addItem;
	private DefaultTableModel tm;//������ģ�Ͷ���
	private JTable tb;//������
	private final String[] colums= {"ҩƷID","ҩƷ����","���","����","������","����","���","����","��������","������","��Ӧ��","��ע","��λ"};//������
    private  int shownum=3;//ÿҳ��ʾ������
    private String[][] pagedata;//��ǰҳ��Ҫ��ʾ������
    private int totalnum;//��������
    private int page;//=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;//��ҳ��  ��������=[(curpage-1)*shownum+currow+1];
	private JButton pre,next,searchbtn;
	private JTextField searchtext;//������
	private JComboBox<String>  sbox;//��������
	private final static String[] stype= {"������","�����","����Ӧ��"};//�������
	private JLabel showpage,totalpage;
	private JPanel p1,pn;
	private int curpage;//��ǰҳ��
	private boolean isfinding;
    private TableModelListener ml;
	public UserInFrame() {
		// TODO Auto-generated constructor stub
		this.title="����";
	}
	public UserInFrame(String title) {
		// TODO Auto-generated constructor stub
		userInFrameInit(title);
		
		userInFramePanelInit();
		addListener();
		System.out.println("�ڲ������ʼ�����");
	}
	private void userInFrameInit(String title) {
		this.title=title;
		this.setResizable(true);//���������С
	
		//this.setIconifiable(true);//����ʹ�ùرհ�ťͼ��
		this.setMaximizable(true);//������󻯰�ť
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setClosable(true);//�����ṩ�رհ�ť
		this.setBounds(2, 2, 1000, 500);
		//this.setVisible(true);Ĭ�Ϲر�
	}
	private void tbValueInit() throws SQLException {
		totalnum=Dao.getInfoNum("tb_mm");
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
	private void tbValueInitBykey(String key, int type) throws SQLException {
		totalnum=MmDbOprator.getInfoNumByKey("tb_mm",key,type);
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
	 *�������ݵ�ʵ�ʳ���
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
				
	              for(int i=0;i<cnoteditcol.length;i++) {
	            	  if(column==cnoteditcol[i]) {
	            		  System.out.println("���ɱ༭");
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
		showpage=new JLabel("��"+curpage+"ҳ");
		totalpage=new JLabel("��"+page+"ҳ");
		p1.add(pre);p1.add(showpage);p1.add(next);p1.add(totalpage);
		this.getContentPane().add(jp,BorderLayout.CENTER);
		this.getContentPane().add(p1,BorderLayout.SOUTH);
	}
	private void popMenuInit()
	{
		pm=new JPopupMenu() ;
		addItem=new JMenuItem("���");
		cutItem=new JMenuItem("ɾ��");
		pm.add(addItem);
		pm.add(cutItem);
	}
	private void btnInit()
	{
		searchbtn=new JButton("����");
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
				int type=e.getType();//��ȡ�¼�����
				int row=e.getFirstRow();//�������¼��ı��������
				int col=e.getColumn();//��ô������¼��ı��������
				if(type==TableModelEvent.INSERT) {//����
					
				}
				else if(type==TableModelEvent.UPDATE) {//����
					
					String oldval=pagedata[row][col];
					System.out.println(oldval);
					String newval=(String) tm.getValueAt(row, col);
					System.out.println(newval);
					if(newval.equals(oldval)) {
						System.out.println("δ����");
					}else {
						TbMminfo info=rowValueToInfo(row);
						System.out.println(info.toString());
						
							try {
								if(MmDbOprator.updateMm(info))
								{
									//JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
								}
								else
								{
									JOptionPane.showMessageDialog(null,"�޸�ʧ��");
								}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					System.out.println(row+","+col+"����");
					
					}
				}
				else if(type==TableModelEvent.DELETE) {//ɾ��
					
				}
				else {//����
					
				}
			}
			
		};
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
		tm.addTableModelListener(ml);
		cutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ɾ������
				int sr=tb.getSelectedRow();//��ȡ��ǰ�е�����
				int type=sbox.getSelectedIndex();
				System.out.println(sr);
				if(sr!=-1) {
					tm.removeRow(sr);
					//ɾ�����ݿ��еļ�¼
					try {
						MmDbOprator.delMmById(pagedata[sr][0]);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					totalnum--;
					tm.removeTableModelListener(ml);//��ֹ�������¼�����
					if(isfinding) {
						refreshTb(searchtext.getText().trim(),type,curpage);
					}
					else {
						refreshTb(curpage);
					}
					tm.addTableModelListener(ml);
					page=(totalnum%shownum==0)?totalnum/shownum:totalnum/shownum+1;
					totalpage.setText("��"+page+"ҳ");
					if(page==0) {
						showpage.setText("��"+page+"ҳ");
					}
					else {
					showpage.setText("��"+curpage+"ҳ");
					}
					System.out.println("ɾ��");
				}
			}
			
		});
		addItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��Ӳ���
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
				showpage.setText("��"+curpage+"ҳ");
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
				tm.removeTableModelListener(ml);//��ֹ�������¼�����
				if(isfinding) {
					refreshTb(searchtext.getText().trim(),type,curpage);
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
				tm.removeTableModelListener(ml);//��ֹ�������¼�����
				refreshTb(key,type,curpage);//�ᴥ�������¼�
				tm.addTableModelListener(ml);
				//����key�����ݿ�����������
				System.out.println(key);
				
			}
			
		});
		
	
	}
	
	
	private void refreshTb(int curpage) {//ˢ�±��
		try {
			getPageData(curpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��ȡ��ǰҳ�������
		tm.setDataVector(pagedata, colums);//ˢ��
		System.out.println("ҳ�������������");
	}
	private void refreshTb(String key,int type,int curpage) {//ˢ�±��
		try {
			getPageDataBykey(key,type,curpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��ȡ��ǰҳ�������
		tm.setDataVector(pagedata, colums);//ˢ��
		System.out.println("ҳ�������������");
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
