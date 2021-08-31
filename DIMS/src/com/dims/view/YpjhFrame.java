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
	 * 药品进货窗口
	 */
	private static final long serialVersionUID = 1L;
    private JPanel  p;
    private final static String[] catearr= {"处方药","非处方药"};
    private final static String[] unitarr= {"粒","片","盒","克","毫升","丸","瓶","斤"};
    private ArrayList<String> suparr;//使用数组链表方便自动扩容
    private int supnum;//记录供应商数
    private JComboBox<String> catet,unit,supt;//类别与单位
    private JComboBox<String> pyy,pmm, pdd;//进货年月日
    private JComboBox<String> eyy,emm, edd;//过期年月日
    private JTextField namet,uprit,bidt,paddrt,spacst,numt,remarkt;
    private JButton ok,exit;
    public YpjhFrame() {
		// TODO Auto-generated constructor stub
		ypjhFrameInit();
	    Container con=this.getContentPane();//获得容器
	    con.setLayout(null);//绝对布局
	    try {
			supArrInit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//初始化供应商信息
	    p=ypjhPanelInit();//布局初始化
	    ypjhEventInit(this);//初始化事件
	    p.setBounds(0,0, 900, 120);
	    con.add(p);
	}
	//初始化窗口
	public void ypjhFrameInit() {
		this.setTitle("药品进货");
		this.setBounds(200, 200, 900, 200);
		this.setResizable(false);//禁止缩放
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	//布局初始化 
	public JPanel ypjhPanelInit() {
		
		    JPanel p=new JPanel(new GridLayout(3,1,0,5));           
	        JPanel p1=new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
	 //药品名
	        JLabel lb01 = new JLabel("药品名:");namet=new JTextField(12);
	        JLabel lb02 = new JLabel("种类:");
	        catet=new JComboBox<String>();
	        for(int i=0;i<catearr.length;i++) {
	        	catet.addItem(catearr[i]);
	        }
	        JLabel lb03 = new JLabel("单价:");uprit=new JTextField(5);
	        JLabel lb04 = new JLabel("进货价:");bidt=new JTextField(5);
	        JLabel lb05 = new JLabel("产地:");paddrt=new JTextField(18);
	        JLabel lb06 = new JLabel("规格:");spacst=new JTextField(8);
	        JLabel lb07 = new JLabel("数量:");numt=new JTextField(5);
	        JLabel lb08 = new JLabel("生产日期:");
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
	        JLabel lb09 = new JLabel("过期日期:");
	        
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
	        JLabel lb10 = new JLabel("供应商:");
	        supt=new JComboBox<String>();
	        for(int i=0;i<supnum;i++) {
	        	supt.addItem(suparr.get(i));
	        }
	
	        JLabel lb11 = new JLabel("备注:");remarkt=new JTextField(20);
	        JLabel lb12 = new JLabel("单位:");unit=new JComboBox<String>();
	      
	        for(int i=0;i<unitarr.length;i++) {
	        	unit.addItem(unitarr[i]);
	        } 
	        ok=new JButton("添加");exit=new JButton("取消");
	        ok.setForeground(Color.RED);  exit.setForeground(Color.gray);
	      //第一行
	        p1.add(lb01);p1.add(namet);
	        p1.add(lb02);p1.add(catet);
	        p1.add(lb03);p1.add(uprit);
	        p1.add(lb04);p1.add(bidt);
	        p1.add(lb05);p1.add(paddrt);
	        p.add(p1);
	      //第二行
	       JPanel p2=new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
	       p2.add(lb06);p2.add(spacst);
	       p2.add(lb07);p2.add(numt);
	       p2.add(lb08);p2.add(pyy);p2.add(pmm);p2.add(pdd);
	       p2.add(lb09);p2.add(eyy);p2.add(emm);p2.add(edd);
	       p.add(p2);
	       //第三行
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
		//确认提交
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] value=getInput2StringArr();
				if(MmController.checkInput(value)) {
				    System.out.println("开始添加用户");
				    TbMminfo info=MmController.getMmInfo(value);
				    try {
						if(MmDbOprator.addMm(info)) {
							KcDbOprator.addKc(info);
							JOptionPane.showMessageDialog(null,"添加入库成功");
						}
						else {
							JOptionPane.showMessageDialog(null,"添加失败");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					System.out.println("错误");
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
	 * 得到输入的参数数组
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
	//供应商初始化
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
