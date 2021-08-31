package com.dims.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dims.dao.model.TbXsinfo;

public class SellDbOprator {

	private SellDbOprator() {
		// TODO Auto-generated constructor stub
	}
 public static void addSellInfo(TbXsinfo info) throws SQLException {
	 PreparedStatement ps=Dao.conn.prepareStatement("insert into tb_msell(selmid,selmname,selnum,totalpri,oprator,selldate,custom,customtel) values(?,?,?,?,?,?,?,?)");//ps���ظ�ʹ��
		//ps.clearBatch();//���Ԥ��������
		ps.setString(1,info.getMid().trim());
		ps.setString(2,info.getMname().trim());
		ps.setString(3,Integer.toString(info.getNum()));
		ps.setString(4,Float.toString(info.getTotal()));
		ps.setString(5,info.getOprator().trim());
		ps.setString(6,info.getSeldate().trim());
		ps.setString(7,info.getCustom().trim());
		ps.setString(8,info.getCustomtel());
		ps.executeUpdate(); //��ʼִ��
		ps.close();
 }
 public static void main(String args[])  {
//	 TbXsinfo info=new TbXsinfo();
//	 info.setMid("12211123");
//	 info.setMname("��ҩ");
//	 info.setNum(12);
//	 info.setOprator("����");
//	 info.setSeldate("2021-02-01");
//	 info.setTotal(125.8f);
//	 info.setCustom("����");
//	 info.setCustomtel("112321322");
//	 try {
//		SellDbOprator.addSellInfo(info);
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
 }
}
