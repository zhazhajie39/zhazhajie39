package com.dims.view.dialog;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import com.dims.check.Checker;
import com.dims.dao.model.TbYhinfo;

public class MotifiedPwdDialog extends JDialog{

	/**
	 * �����޸Ĺ��ܵĶԻ���
	 */
	private static final long serialVersionUID = 1L;
	protected JLabel name,showname,pwd1,pwd2;
	protected JPasswordField pwd1text,pwd2text;
	protected JButton ok,exit;
	public MotifiedPwdDialog(JFrame father,TbYhinfo userinfo) {
		// TODO Auto-generated constructor stub
		super(father,"�����޸�",true);
		mPDialogInit();
		mPDPanelInit(userinfo); 
		mPDEvenInit(this,userinfo);
		this.setVisible(true);
	}
	 protected void  mPDialogInit()
	 {
			this.setBounds(420, 80, 360, 300);
			this.setResizable(false);//��ֹ����
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 }
	//��ʼ�����
	protected void mPDPanelInit(TbYhinfo userinfo) {
		Container cont=this.getContentPane();
		cont.setLayout(null);
		JPanel p=new JPanel();
		JPanel p2=new JPanel();
		cont.add(p);
		p.setLayout(new GridLayout(7,1,10,5));//ʹ�����񲼾�
		p.setBounds(20, 10, 300, 210);//30*7
		//��ʾ��ǰ�û���
		p2.setLayout(new GridLayout(1,1,5,5));
		name=new JLabel("��ǰ�û���");showname=new JLabel(userinfo.getName());
		p.add(name);p.add(showname);
		pwd1=new JLabel("�����룺");pwd1text=new JPasswordField(12);
		p.add(pwd1);p.add(pwd1text);
		pwd2=new JLabel("ȷ�������룺");pwd2text=new JPasswordField(12);
		p.add(pwd2);p.add(pwd2text);
		ok=new JButton("ȷ��");exit=new JButton("ȡ��");
		p2.add(ok);p2.add(exit);
		p.add(p2);
		
	}
	//��ʼ����ؿؼ��¼�
	public void mPDEvenInit(JDialog jd,TbYhinfo userinfo) {
		ok.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Checker.isPwd(pwd1text.getText().trim(),pwd2text.getText().trim())) {
					if(userinfo.getPwd().equals(pwd1text.getText().trim())) {
						JOptionPane.showMessageDialog(null,"�����벻�����������ͬ");
					}
					else {
					userinfo.setPwd(pwd1text.getText());
					if(TbYhinfo.motifiedPwd(userinfo.getId(),userinfo.getPwd())) {
				    	JOptionPane.showMessageDialog(null,"�����޸ĳɹ�");
				      }else {
				    	JOptionPane.showMessageDialog(null,"�����޸�ʧ��");
				      }
					}
				}
				
			}
			
		});
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jd.dispose();
			}
		});
	}

}
