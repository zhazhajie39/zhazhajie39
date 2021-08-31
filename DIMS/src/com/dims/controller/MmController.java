package com.dims.controller;

import javax.swing.JOptionPane;

import com.dims.check.*;
import com.dims.dao.model.TbMminfo;

public class MmController {

	private MmController() {
		// TODO Auto-generated constructor stub
	}
	public static TbMminfo getMmInfo(String Para[]) {
		TbMminfo info=new TbMminfo();
		info.setName(Para[0]);
		info.setCate(Para[1]);
		info.setUpri(Float.parseFloat(Para[2]));
		info.setBid(Float.parseFloat(Para[3]));
		info.setPaddr(Para[4]);
		info.setSpacs(Para[5]);
		info.setNum(Integer.parseInt(Para[6]));
		info.setPdate(getDate(Para[7],Para[8],Para[9]));
		info.setExpirydate(getDate(Para[10],Para[11],Para[12]));
		info.setSup(Para[13]);
		info.setRemark(Para[14]);
		info.setUnit(Para[15]);
		return info;
	}
	public static boolean checkInput(String Para[]){
		int rightNum=0;//输入正确的个数
		if(Para[0].isBlank()&&Para[0].isBlank()) { JOptionPane.showMessageDialog(null,"药品名称不为空");  return false;}else {rightNum++;}//药品名称不为空
		if(Checker.isNum(Para[2])&&rightNum==1) {rightNum++;}else{return false;}
		if(Checker.isNum(Para[3])&&rightNum==2) {rightNum++;}else{return false;}
		if(Checker.isZhe(Para[6])&&rightNum==3) {return true;}else{return false;}
	}
	public static String getDate(String year,String month,String day) {
		if(Integer.parseUnsignedInt(month)<10)
		{
			month='0'+month;
		}
		if(Integer.parseUnsignedInt(day)<10)
		{
			day='0'+day;
		}
		String date=year+"-"+month+"-"+day;
		return date;
	}
//判断是否是润年
	public static boolean isRen(int year) {
		if((year%4==0&&year%100!=0)||(year%400==0)) {
		   return true;
		} else {
		   return false;
		}
	}
	//得到当前天数
	public static int getDay(int year,int month) {
		int day=0;
		switch(month) {
		    case 1:
		    case 3:
		    case 5:
		    case 7:
		    case 8:
		    case 10:
		    case 12:
		        day=31;
		        break;
		    case 4:
		    case 6:
		    case 9:
		    case 11:
		        day=30;
		        break;
		    default:
		        if(isRen(year)) {
		            day=29;
		        } else {
		            day=28;
		        }
		        break;
		}
		return day;
	};
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
