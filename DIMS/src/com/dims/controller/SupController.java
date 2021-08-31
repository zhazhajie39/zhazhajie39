package com.dims.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.dims.dao.model.TbMsupinfo;

public class SupController  {

	private SupController() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean checkInput(String[] para)  {//para[0]=��Ӧ����;para[1]=��Ӧ�̵�ַ;para[2]=��Ӧ�̱�ע;
		if(para==null)
		{
			return false;
		}
		if(para[0].isBlank()||para[0].isEmpty()) {
			JOptionPane.showMessageDialog(null,"��Ӧ������Ϊ��");
			return false;
		}
		try {
			if(TbMsupinfo.haveName(para[0]))
			{
				JOptionPane.showMessageDialog(null,"��Ӧ�����Ѵ���");
				return false;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		// TODO Auto-generated method stub
	}
	public static boolean checkInput(String[] para,String lastname)  {//para[0]=��Ӧ����;para[1]=��Ӧ�̵�ַ;para[2]=��Ӧ�̱�ע;
		if(para==null)
		{
			return false;
		}
		if(para[0].isBlank()||para[0].isEmpty()) {
			JOptionPane.showMessageDialog(null,"��Ӧ������Ϊ��");
			return false;
		}
		if(para[0].equals(lastname)) {
			return true;
		}
		try {
			if(TbMsupinfo.haveName(para[0]))
			{
				JOptionPane.showMessageDialog(null,"��Ӧ�����Ѵ���");
				return false;
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		// TODO Auto-generated method stub
	}
	public static TbMsupinfo getInput(String[] para) {//para[0]=��Ӧ����;para[1]=��Ӧ�̵�ַ;para[2]=��Ӧ�̱�ע;
		// TODO Auto-generated method stub
		TbMsupinfo info=new TbMsupinfo();
		info.setName(para[0]);
		info.setAddr(para[1]);
		info.setRemark(para[2]);
		return info;
	}
	public static TbMsupinfo getInput1(String[] para) {//para[0]=��Ӧ����;para[1]=��Ӧ�̵�ַ;para[2]=��Ӧ�̱�ע;
		// TODO Auto-generated method stub
		TbMsupinfo info=new TbMsupinfo();
		info.setName(para[0]);
		info.setAddr(para[1]);
		info.setRemark(para[2]);
		info.setId(para[3]);//sid
		return info;
	}
}
