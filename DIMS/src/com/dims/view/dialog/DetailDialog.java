package com.dims.view.dialog;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dims.dao.model.TbYhinfo;

public class DetailDialog extends JDialog {
	/**
	 * �˺�����
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p; 
	public DetailDialog() {
		// TODO Auto-generated constructor stub
	}
	public DetailDialog(JFrame father,TbYhinfo userinfo) {
		//super(father,"�˺�����",true);
		this.setTitle("�˺�����");
		detailDialogInit(userinfo); 
		this.setResizable(false);//��ֹ����
		this.setVisible(true);
	}
   public void detailDialogInit(TbYhinfo userinfo) {
	Container cont=this.getContentPane();
	cont.setLayout(null);
	p=new JPanel();
	p.setLayout(new GridLayout(8,2));
	p.setBounds(20, 0, 300, 400);
	cont.add(p);
	p.add(new JLabel("�û�ID :"));p.add(new JLabel(userinfo.getId()));
	p.add(new JLabel("�û��� :"));p.add(new JLabel(userinfo.getName()));
	p.add(new JLabel("�Ա�  :"));p.add(new JLabel(userinfo.getXb()));
	p.add(new JLabel("ע������:"));p.add(new JLabel(userinfo.getSub_date()));
	p.add(new JLabel("��ϵ�绰:"));p.add(new JLabel(userinfo.getTel()));
	p.add(new JLabel("����:"));p.add(new JLabel(userinfo.getMail()));
	p.add(new JLabel("�������� :"));p.add(new JLabel(userinfo.getKhyh()));
	if(userinfo.getIsm().equals("0")) {
		p.add(new JLabel("����Ա��� : "));p.add(new JLabel("��"));
	}
	else {
		p.add(new JLabel("����Ա��� : "));p.add(new JLabel("��"));
	}
	cont.add(p);
	this.setBounds(500, 100, 330, 440);
 }
   public  void refreshDetail(TbYhinfo userinfo)
   {
	    this.getContentPane().remove(p);
	    p.removeAll();
	    p.add(new JLabel("�û�ID :"));p.add(new JLabel(userinfo.getId()));
		p.add(new JLabel("�û��� :"));p.add(new JLabel(userinfo.getName()));
		p.add(new JLabel("�Ա�  :"));p.add(new JLabel(userinfo.getXb()));
		p.add(new JLabel("ע������:"));p.add(new JLabel(userinfo.getSub_date()));
		p.add(new JLabel("��ϵ�绰:"));p.add(new JLabel(userinfo.getTel()));
		p.add(new JLabel("����:"));p.add(new JLabel(userinfo.getMail()));
		p.add(new JLabel("�������� :"));p.add(new JLabel(userinfo.getKhyh()));
		if(userinfo.getIsm().equals("0")) {
			p.add(new JLabel("����Ա��� : "));p.add(new JLabel("��"));
		}
		else {
			p.add(new JLabel("����Ա��� : "));p.add(new JLabel("��"));
		}
		this.getContentPane().add(p);
	   
   }
}
