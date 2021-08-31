package com.dims.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dims.controller.MmController;
import com.dims.dao.KcDbOprator;
import com.dims.dao.MmDbOprator;
import com.dims.dao.model.TbMminfo;
import com.dims.dao.model.TbMsupinfo;




public class YpjhFrame extends JFrame{

	/**
	 * ҩƷ��������
	 */
	private static final long serialVersionUID = 1L;
    private JPanel  p;
    private final static String[] catearr= {"����ҩ","�Ǵ���ҩ"};
    private final static String[] unitarr= {"��","Ƭ","��","��","����","��","ƿ","��"};
    private ArrayList<String> suparr;//ʹ�������������Զ�����
    private int supnum;//��¼��Ӧ����
    private JComboBox<String> catet,unit,supt;//����뵥λ
    private JComboBox<String> pyy,pmm, pdd;//����������
    private JComboBox<String> eyy,emm, edd;//����������
    private JTextField namet,uprit,bidt,paddrt,spacst,numt,remarkt;
    private JButton ok,exit;
    public YpjhFrame() {
		// TODO Auto-generated constructor stub
		ypjhFrameInit();
	    Container con=this.getContentPane();//�������
	    con.setLayout(null);//���Բ���
	    try {
			supArrInit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//��ʼ����Ӧ����Ϣ
	    p=ypjhPanelInit();//���ֳ�ʼ��
	    ypjhEventInit(this);//��ʼ���¼�
	    p.setBounds(0,0, 900, 120);
	    con.add(p);
	}
	//��ʼ������
	public void ypjhFrameInit() {
		this.setTitle("ҩƷ����");
		this.setBounds(200, 200, 900, 200);
		this.setResizable(false);//��ֹ����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	//���ֳ�ʼ�� 
	public JPanel ypjhPanelInit() {
		
		    JPanel p=new JPanel(new GridLayout(3,1,0,5));           
	        JPanel p1=new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
	 //ҩƷ��
	        JLabel lb01 = new JLabel("ҩƷ��:");namet=new JTextField(12);
	        JLabel lb02 = new JLabel("����:");
	        catet=new JComboBox<String>();
	        for(int i=0;i<catearr.length;i++) {
	        	catet.addItem(catearr[i]);
	        }
	        JLabel lb03 = new JLabel("����:");uprit=new JTextField(5);
	        JLabel lb04 = new JLabel("������:");bidt=new JTextField(5);
	        JLabel lb05 = new JLabel("����:");paddrt=new JTextField(18);
	        JLabel lb06 = new JLabel("���:");spacst=new JTextField(8);
	        JLabel lb07 = new JLabel("����:");numt=new JTextField(5);
	        JLabel lb08 = new JLabel("��������:");
	        pyy=new JComboBox<String>();  pmm=new JComboBox<String>();  pdd=new JComboBox<String>();
	        for(int i=2017;i<=2021;i++) {
	        	pyy.addItem(Integer.toString(i));
	        }
	        for(int i=1;i<=12;i++) {
	        	pmm.addItem(Integer.toString(i));
	        }
	        for(int i=1;i<=MmController.getDay(pyy.getSelectedIndex()+2017, pmm.getSelectedIndex()+1);i++)
			{
	             pdd.addItem(Integer.toString(i));
			};
	        JLabel lb09 = new JLabel("��������:");
	        
	        eyy=new JComboBox<String>();  emm=new JComboBox<String>();  edd=new JComboBox<String>();
	        for(int i=2017;i<=2021;i++) {
	        	eyy.addItem(Integer.toString(i));
	        }
	        for(int i=1;i<=12;i++) {
	        	emm.addItem(Integer.toString(i));
	        }
	        for(int i=1;i<=MmController.getDay(eyy.getSelectedIndex()+2017, emm.getSelectedIndex()+1);i++)
			{
	             edd.addItem(Integer.toString(i));
			};
	        JLabel lb10 = new JLabel("��Ӧ��:");
	        supt=new JComboBox<String>();
	        for(int i=0;i<supnum;i++) {
	        	supt.addItem(suparr.get(i));
	        }
	
	        JLabel lb11 = new JLabel("��ע:");remarkt=new JTextField(20);
	        JLabel lb12 = new JLabel("��λ:");unit=new JComboBox<String>();
	      
	        for(int i=0;i<unitarr.length;i++) {
	        	unit.addItem(unitarr[i]);
	        } 
	        ok=new JButton("���");exit=new JButton("ȡ��");
	        ok.setForeground(Color.RED);  exit.setForeground(Color.gray);
	      //��һ��
	        p1.add(lb01);p1.add(namet);
	        p1.add(lb02);p1.add(catet);
	        p1.add(lb03);p1.add(uprit);
	        p1.add(lb04);p1.add(bidt);
	        p1.add(lb05);p1.add(paddrt);
	        p.add(p1);
	      //�ڶ���
	       JPanel p2=new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
	       p2.add(lb06);p2.add(spacst);
	       p2.add(lb07);p2.add(numt);
	       p2.add(lb08);p2.add(pyy);p2.add(pmm);p2.add(pdd);
	       p2.add(lb09);p2.add(eyy);p2.add(emm);p2.add(edd);
	       p.add(p2);
	       //������
	       JPanel p3=new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
	       p3.add(lb10);p3.add(supt);
	       p3.add(lb11);p3.add(remarkt);
	       p3.add(lb12);p3.add(unit);
	       p3.add(ok);p3.add(exit);
	       p.add(p3);
	        
	        
	
		return p;
	}
	
	public void ypjhEventInit(JFrame jf) {
		pyy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pdd.removeAllItems();
				for(int i=1;i<=MmController.getDay(pyy.getSelectedIndex()+2017, pmm.getSelectedIndex()+1);i++)
				{
		             pdd.addItem(Integer.toString(i));
				};
			}
			
		});
		pmm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pdd.removeAllItems();
				for(int i=1;i<=MmController.getDay(pyy.getSelectedIndex()+2017, pmm.getSelectedIndex()+1);i++)
				{
		             pdd.addItem(Integer.toString(i));
				};
			}
			
		});
		eyy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				edd.removeAllItems();
				for(int i=1;i<=MmController.getDay(eyy.getSelectedIndex()+2017, emm.getSelectedIndex()+1);i++)
				{
		             edd.addItem(Integer.toString(i));
				};
			}
			
		});
		emm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				edd.removeAllItems();
				for(int i=1;i<=MmController.getDay(eyy.getSelectedIndex()+2017, emm.getSelectedIndex()+1);i++)
				{
		             edd.addItem(Integer.toString(i));
				};
			}
			
		});
		//ȷ���ύ
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] value=getInput2StringArr();
				if(MmController.checkInput(value)) {
				    System.out.println("��ʼ����û�");
				    TbMminfo info=MmController.getMmInfo(value);
				    try {
						if(MmDbOprator.addMm(info)) {
							KcDbOprator.addKc(info);
							JOptionPane.showMessageDialog(null,"������ɹ�");
						}
						else {
							JOptionPane.showMessageDialog(null,"���ʧ��");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					System.out.println("����");
				}
			}
			
		});
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			  jf.dispose();
			}
			
		});
	}
	/**
	 * �õ�����Ĳ�������
	 * @return String []
	 */
	public String[] getInput2StringArr()
	{
		String[] value=new String[16];
		value[0]=namet.getText().trim();
		value[1]=catearr[catet.getSelectedIndex()];
		value[2]=uprit.getText().trim();
		value[3]=bidt.getText().trim();
		
		value[4]=paddrt.getText().trim();
		value[5]=spacst.getText().trim();
		value[6]=numt.getText().trim();
		value[7]=(String)pyy.getSelectedItem();
		
		value[8]=(String)pmm.getSelectedItem();
		value[9]=(String)pdd.getSelectedItem();
		value[10]=(String)eyy.getSelectedItem();
		value[11]=(String)emm.getSelectedItem();
		
		value[12]=(String)edd.getSelectedItem();
		value[13]=suparr.get(supt.getSelectedIndex());
		value[14]=remarkt.getText();
		value[15]=unitarr[unit.getSelectedIndex()];
		return value;
		
	}
	//��Ӧ�̳�ʼ��
	public void supArrInit() throws SQLException {
		TbMsupinfo[] infos=TbMsupinfo.getAllSupInfo();
		this.supnum=TbMsupinfo.getSupNum();
		this.suparr=new ArrayList<String>();
		for(int i=0;i<this.supnum;i++) {
			this.suparr.add(infos[i].getName());
		}
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		YpjhFrame y=new YpjhFrame();
//		y.setVisible(true);
//	}

}
