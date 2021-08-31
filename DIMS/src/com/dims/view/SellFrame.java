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
	 * ���۴���
	 * �ṩҩƷ���ƣ�ҩƷ���������
	 * �˿����ƣ��˿���ϵ��ʽ��
	 * ��Ҫ�õ�������������
	 */
	private static final long serialVersionUID = 1L;
    private ArrayList<TbXsinfo> sellinfo;//��¼���д����۵�ҩƷ
	private JTextField id,name,num,cashier,customname,customtel;//ҩƷ��ţ�ҩƷ�����ɹ���������Ա,�ͻ���,�ͻ���ϵ�绰
	private JLabel show;//��ʾ�����ܽ��
	private float money;//�����ܽ��
	private DefaultTableModel tm;//������ģ�Ͷ���
	private final String[] colums= {"ҩƷ���","ҩƷ����","�ɹ���","������","�ͻ���","�ͻ���ϵ�绰","���"};//������
	private JTable buytb;//�ɹ���
	private String [][] tbVal;
	private String []  data;
	private JButton add,sellbtn,clear;//����۳���ť
	private String [] para;
	public SellFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		sellFrameInit();
		sellFramePanelInit();
		addEventListener();
	}
	//��ʼ�������
	private void sellFrameInit() {
		this.setTitle("���۴���");
		this.setResizable(true);//���������С
		
		//this.setIconifiable(true);//����ʹ�ùرհ�ťͼ��
		this.setMaximizable(true);//������󻯰�ť
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setClosable(true);//�����ṩ�رհ�ť
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
		p1.add(new JLabel("ҩƷ��ţ�"));id=new JTextField(15);p1.add(id);
		p1.add(new JLabel("ҩƷ���ƣ�"));name=new JTextField(15);p1.add(name);
		p1.add(new JLabel("\t�ɹ�����"));num=new JTextField(15);p1.add(num);num.setText("1");
		p2.add(new JLabel("   �����ˣ�"));cashier=new JTextField(15);p2.add(cashier);cashier.setText(MainMenu.curUser.getName());
		p2.add(new JLabel("  �ͻ�����"));customname=new JTextField(15);p2.add(customname);
		p2.add(new JLabel("�ͻ��绰��"));customtel=new JTextField(15);p2.add(customtel);
		add=new JButton("���");p3.add(add);
		n.add(p1);n.add(p2);n.add(p3);
		this.getContentPane().add(n,BorderLayout.NORTH);
		//�ɹ���
		tm= new DefaultTableModel(tbVal,colums) {
			private static final long serialVersionUID = 1L;
			@Override  
	            public boolean isCellEditable(int row,int column){ 
	              System.out.println("���ɱ༭");
	            		  return false;
	            }  
		};
		
		buytb=new JTable(tm);
		buytb.setRowHeight(30);
		final JScrollPane jp=new JScrollPane(buytb);
		this.getContentPane().add(jp,BorderLayout.CENTER);
		
		//�������
		final JPanel s=new JPanel();
		s.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		show=new JLabel(Float.toString(money));
		clear=new JButton("���");s.add(clear);
		s.add(new JLabel("�ܼƽ�"));s.add(show);
		sellbtn=new JButton("����");s.add(sellbtn);
		
		this.getContentPane().add(s,BorderLayout.SOUTH);
	}
	private void  addEventListener() {
		//���
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(SellController.checkInput(getPara())) {
						//System.out.println(SellController.getInput(para).toString());//����Ĳ�����װ����Ϣģ����
						//ͬ��ҩƷ����ӱ���С�ڻ���ڿ����
						//data��ʾ��ǰҪ���۵�ҩƷ��Ϣ
						if(isSpillover(data[0],Integer.parseInt(data[2]),sellinfo))
						{
							return ;
						}
						tm.addRow(data);
						//��ӵ��������sellinfo��
						sellinfo.add(SellController.getInput(para));
						System.out.println(sellinfo.size());
						//�ۼӽ��
						money=caculateCash(sellinfo);
						show.setText(Float.toString(money));//��ʾ���
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//���
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//sellinfo�Ƴ���������
				int len=sellinfo.size();
				if(len>0) {
				tm.setDataVector(tbVal, colums);//ˢ�����
				sellinfo.clear();;
				System.out.println(sellinfo.size());
				money=0;
				show.setText(Float.toString(money));//��ʾ���
				}
				//�Ƴ�����е���������
			}
		});
		//�۳�
		sellbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//sellinfo�Ƴ���������
				int len=sellinfo.size();
				//�������Ʒ
				if(len>0) {
				
					for(int i=0;i<len;i++)
					{
					try {
						//ҩƷ����ٶ�ӦҩƷ����
						MmDbOprator.updateNumByID(sellinfo.get(i).getMid(),sellinfo.get(i).getNum(), false);
						//������ٶ�ӦҩƷ�������
						KcDbOprator.updateNumByID(sellinfo.get(i).getMid(),sellinfo.get(i).getNum(), false);
						//��ӵ����۵�
						SellDbOprator.addSellInfo(sellinfo.get(i));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
			    //�������ˢ�¿���
					MainMenu.kcinframe.refreshTb(MainMenu.kcinframe.getCurkey(),MainMenu.kcinframe.getCurpage());
				//��մ����۱�
					tm.setDataVector(tbVal, colums);//ˢ�����
				//�������
					sellinfo.clear();
				//����
					money=0;
					show.setText(Float.toString(money));//��ʾ���
					System.out.println("���۳ɹ�");
				}
				else {
					System.out.println("������ҩƷ");
				}
				//������۱�ɼ���ˢ�����۱�
				//û�в�������
			}
		});
		
	
	}
	//�Ƿ����
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
	//������
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
