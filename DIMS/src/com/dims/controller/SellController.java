package com.dims.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.dims.check.Checker;
import com.dims.dao.KcDbOprator;
import com.dims.dao.model.TbXsinfo;

public class SellController {

	public SellController() {
		// TODO Auto-generated constructor stub
	}

	
	public static  boolean checkInput(String[] para) throws SQLException {
		// TODO Auto-generated method stub
		int rightNum=0;
		if(!para[0].isBlank()&&!para[0].isEmpty()&&KcDbOprator.haveId(para[0])) {
			rightNum++;
		}
		else {
			JOptionPane.showMessageDialog(null,"药品不存在");
			return false;
		}
		if(Checker.isName(para[1])&&rightNum==1) {
			
			rightNum++;
		}
		else {
			return false;
		}
		if(para[2].isBlank()||para[2].isEmpty()) {
				JOptionPane.showMessageDialog(null,"请输入采购数");
				return false;
			}
		if(Checker.isZhe(para[2])&&rightNum==2) {
			int total=KcDbOprator.getKcNumByID(para[0]);
			
			int sellnum=Integer.parseInt(para[2]);
			if(sellnum>total) {
				JOptionPane.showMessageDialog(null,"库存不足或输入的数量大于库存数");
				return false;
			}
			rightNum++;
		}
		else {
			return false;
		}
		if(Checker.isName(para[5])&&rightNum==3) {
			rightNum++;
		}
		else {
			return false;
		}
		if(Checker.isTel(para[6])&&rightNum==4) {
			return true;
		}
		else {
			return false;
		}
	}
	public static TbXsinfo getInput(String[] para) throws NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		TbXsinfo info=new TbXsinfo();
		info.setMid(para[0]);
		info.setMname(para[1]);
		info.setNum(Integer.parseInt(para[2]));
		info.setOprator(para[3]);
		info.setSeldate(para[4]);
		info.setCustom(para[5]);
		info.setCustomtel(para[6]);
		info.setTotal(Float.parseFloat(para[7]));
		return info;
	}
	public static   boolean isSpillover(int num,String id) throws SQLException {
		int total=KcDbOprator.getKcNumByID(id);
		if(num<=total) {
			return false;
		}
		else {
			JOptionPane.showMessageDialog(null,"超过库存");
			return true;
		}
	}
	

}
