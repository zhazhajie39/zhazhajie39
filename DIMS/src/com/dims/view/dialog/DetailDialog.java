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
	 * 账号详情
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p; 
	public DetailDialog() {
		// TODO Auto-generated constructor stub
	}
	public DetailDialog(JFrame father,TbYhinfo userinfo) {
		//super(father,"账号详情",true);
		this.setTitle("账号详情");
		detailDialogInit(userinfo); 
		this.setResizable(false);//禁止缩放
		this.setVisible(true);
	}
   public void detailDialogInit(TbYhinfo userinfo) {
	Container cont=this.getContentPane();
	cont.setLayout(null);
	p=new JPanel();
	p.setLayout(new GridLayout(8,2));
	p.setBounds(20, 0, 300, 400);
	cont.add(p);
	p.add(new JLabel("用户ID :"));p.add(new JLabel(userinfo.getId()));
	p.add(new JLabel("用户名 :"));p.add(new JLabel(userinfo.getName()));
	p.add(new JLabel("性别  :"));p.add(new JLabel(userinfo.getXb()));
	p.add(new JLabel("注册日期:"));p.add(new JLabel(userinfo.getSub_date()));
	p.add(new JLabel("联系电话:"));p.add(new JLabel(userinfo.getTel()));
	p.add(new JLabel("邮箱:"));p.add(new JLabel(userinfo.getMail()));
	p.add(new JLabel("开户银行 :"));p.add(new JLabel(userinfo.getKhyh()));
	if(userinfo.getIsm().equals("0")) {
		p.add(new JLabel("管理员身份 : "));p.add(new JLabel("否"));
	}
	else {
		p.add(new JLabel("管理员身份 : "));p.add(new JLabel("是"));
	}
	cont.add(p);
	this.setBounds(500, 100, 330, 440);
 }
   public  void refreshDetail(TbYhinfo userinfo)
   {
	    this.getContentPane().remove(p);
	    p.removeAll();
	    p.add(new JLabel("用户ID :"));p.add(new JLabel(userinfo.getId()));
		p.add(new JLabel("用户名 :"));p.add(new JLabel(userinfo.getName()));
		p.add(new JLabel("性别  :"));p.add(new JLabel(userinfo.getXb()));
		p.add(new JLabel("注册日期:"));p.add(new JLabel(userinfo.getSub_date()));
		p.add(new JLabel("联系电话:"));p.add(new JLabel(userinfo.getTel()));
		p.add(new JLabel("邮箱:"));p.add(new JLabel(userinfo.getMail()));
		p.add(new JLabel("开户银行 :"));p.add(new JLabel(userinfo.getKhyh()));
		if(userinfo.getIsm().equals("0")) {
			p.add(new JLabel("管理员身份 : "));p.add(new JLabel("否"));
		}
		else {
			p.add(new JLabel("管理员身份 : "));p.add(new JLabel("是"));
		}
		this.getContentPane().add(p);
	   
   }
}
