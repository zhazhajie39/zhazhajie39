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
	 * ��Ӧ�̳�ʼ��
	 */
	private static final String[] dsupname= {"����ʡ������xxҩҵ���޹�˾","����ʡ������xxҩҵ���޹�˾","����ʡxx��xxҩҵ���޹�˾"};
	private static boolean isInit;//�Ƿ����Ĭ�ϳ�ʼ��
	private static final long serialVersionUID = 1L;
    private JPanel p,p1,p2,p3,p4,p5;
    private JTextField supnamet,supaddrt,supremarkt,supidt;
    private JTable tbsup;
    private final String[] colname={"id","����","��ַ","��ע"};
	private String[][] tbVal;//�����Ҫ��ʾ������
	private JScrollPane sp;
    private JButton dinitbtn,addsupbtn,delbtn,updatebtn,exitbtn;
    
    private JComboBox<String> supidBox;
    private JTextField supnamet1,supaddrt1,supremarkt1;
    private static int  supnum;
    
	public SupInitFrame()  {
		// TODO Auto-generated constructor stub
		isInit=false;
		supInitJframe();//��ʼ������
		Container conn=this.getContentPane();
		conn.setLayout(null);
		p=supPanelInit();//��ʼ������
		//p.setBackground(Color.gray);
		p.setBounds(10, 0, 765, 500);
		supEventInit(this);//��ʼ���¼�
		conn.add(p);
		
	} 
	/**
	 * ���ڳ�ʼ��
	 */
	private void supInitJframe() {
		this.setTitle("��Ӧ�̳�ʼ��");
		this.setBounds(300,40, 800, 600);
		this.setResizable(true);//��ֹ����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
/**
 * ���ֳ�ʼ��
 * @return
 */
	public JPanel supPanelInit() {
		JPanel p=new JPanel();//�����
				p1=new JPanel();//ɾ�����й�Ӧ����Ϣ
		        p2=new JPanel();//��Ӷ����Ӧ����Ϣ
		        p3=new JPanel();//��Ӧ����Ϣ�б�
		        p4=new JPanel();//�޸ĵ�����Ӧ����Ϣ
		        p5=new JPanel();//ɾ�������Ӧ����Ϣ
		p.setLayout(null);
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.setBounds(0, 0, 765, 40);
		dinitbtn=new MyButton("Ĭ�ϳ�ʼ��",Color.red);
		p1.add(dinitbtn);
		
		p2.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		p2.setBounds(0,40, 800, 40);
		p2.add(new JLabel("��Ӧ������:"));supnamet=new JTextField(12);p2.add(supnamet);
		p2.add(new JLabel("��ַ:"));supaddrt=new JTextField(12);p2.add(supaddrt);
		p2.add(new JLabel("��ע:"));supremarkt=new JTextField(30);p2.add(supremarkt);
		addsupbtn=new MyButton("���",Color.red);p2.add(addsupbtn);
		
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
		tbsup.setRowHeight(30);//���ø�Ϊ30
		tbsup.setEnabled(false);
		sp.setViewportView(tbsup);
		p3.add(sp,BorderLayout.CENTER);
		
		p4.setBounds(0,275, 765,40);
		p4.add(new JLabel("��Ӧ��ID:"));supidt=new JTextField(10);p4.add(supidt);
	    delbtn=new MyButton("ɾ��",Color.red);
		p4.add(delbtn);
		
		p5.setBounds(0, 325, 765, 120);
		p5.setLayout(new GridLayout(5,2,0,0));
		p5.add(new JLabel("ID:"));supidBox=new JComboBox<String>();
		for(int i=0;i<supnum;i++) {
			supidBox.addItem(tbVal[i][0]);	
		}
		p5.add(supidBox);
		p5.add(new JLabel("����:"));supnamet1=new JTextField(10);supnamet1.setText(tbVal[0][1]);p5.add(supnamet1);
		p5.add(new JLabel("��ַ:"));supaddrt1=new JTextField(10);supaddrt1.setText(tbVal[0][2]);p5.add(supaddrt1);
		p5.add(new JLabel("��ע:"));supremarkt1=new JTextField(10);supremarkt1.setText(tbVal[0][3]);p5.add(supremarkt1);
		exitbtn=new MyButton("",Color.gray);exitbtn.setVisible(false);;p5.add(exitbtn);
		updatebtn=new MyButton("�޸�",Color.red);p5.add(updatebtn);
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
		tbsup.setRowHeight(30);//���ø�Ϊ30
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
				 * ɾ�����й�Ӧ����Ϣ������Ĭ�ϳ�ʼ��
				 * 
				 */
				try {
					if(!isInit) {
					if(TbMsupinfo.delAllSup()>=0)
					{
						TbMsupinfo.updateIncreament();//��������ֵ
						TbMsupinfo[] infos=new TbMsupinfo[dsupname.length];
						for(int i=0;i<dsupname.length;i++) {
							infos[i]=new TbMsupinfo();
							infos[i].setName(dsupname[i]);
							infos[i].setAddr("��");
							infos[i].setRemark("��");
							TbMsupinfo.addSup(infos[i]);
						}
						refreshTb();
						JOptionPane.showMessageDialog(null,"Ĭ�ϳ�ʼ���ɹ�");
						isInit=true;
					}
					else{
						JOptionPane.showMessageDialog(null,"Ĭ�ϳ�ʼ��ʧ��");
					}
					}
					else {
						JOptionPane.showMessageDialog(null,"Ĭ�ϳ�ʼ������������ظ�ִ��");
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
							JOptionPane.showMessageDialog(null,"���ʧ��");
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
						JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
					}
					else {
						JOptionPane.showMessageDialog(null,"ɾ��ʧ��");
					}
					}
					else {
						JOptionPane.showMessageDialog(null,"δ�ҵ���Ӧ��");
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
							JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"�޸�ʧ��");
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
	 * �õ��û����벢����
	 * @param num ��Ҫ��ȡֵ�Ŀؼ��ĸ���
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
		 * @param content ��������
		 * @param color �ֵ���ɫ
		 */
		public MyButton(String content,Color color) {
			this.setText(content);
			this.setFocusPainted(false);
			this.setForeground(color);
		}
	}

}
