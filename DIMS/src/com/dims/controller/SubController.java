package com.dims.controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dims.check.Checker;
import com.dims.dao.model.TbYhinfo;

//ÓÃ»§×¢²áÌí¼Ó
public class SubController {

	public SubController() {
		// TODO Auto-generated constructor stub
	}
	 @SuppressWarnings("deprecation")
	 public static TbYhinfo getInput(JTextField nametext,JPasswordField pwd1text,JTextField teltext,JTextField mailtext,JTextField khyhtext) {
		 TbYhinfo info=new TbYhinfo();
	 	    info.setName(nametext.getText().trim());
	 		info.setPwd(pwd1text.getText().trim());
	 		info.setTel(teltext.getText().trim());
	 		info.setMail(mailtext.getText().trim());
	 		info.setKhyh(khyhtext.getText().trim());
	 		return info;
	  }
	  @SuppressWarnings("deprecation")
	 public static  boolean checkInput(TbYhinfo info,JPasswordField pwd1text,JPasswordField pwd2text)  {

	 	int rightNum=0;
	 	try {
	 		if(Checker.isRightUserName(info.getName())) {rightNum++;}else{return false;}
	 	} catch (SQLException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	}
	 	if(Checker.isPwd(pwd1text.getText().trim(),pwd2text.getText().trim())&&rightNum==1) {rightNum++;}else {return false;}
	 	if(Checker.isTel(info.getTel())&&rightNum==2) {rightNum++;}else {return false;}
	 	if(Checker.isEmail(info.getMail())&&rightNum==3) {rightNum++;}else {return false;}
	 	if(Checker.isContainsSpecialChar(info.getKhyh())&&rightNum==4) {return true;}else {return false;}
	 	
	  }
	  public static TbYhinfo getInput(JTextField nametext,JTextField teltext,JTextField mailtext,JTextField khyhtext) {
			 TbYhinfo info=new TbYhinfo();
		 	    info.setName(nametext.getText().trim());
		 	    info.setTel(teltext.getText().trim());
		 		info.setMail(mailtext.getText().trim());
		 		info.setKhyh(khyhtext.getText().trim());
		 		return info;
		  }
	  public static TbYhinfo getInput(JTextField nametext,JComboBox<String> xbtext,JTextField teltext,JTextField mailtext,JTextField khyhtext) {
			 TbYhinfo info=new TbYhinfo();
		 	    info.setName(nametext.getText().trim());
		 	    info.setXb((String) xbtext.getSelectedItem());
		 	    info.setTel(teltext.getText().trim());
		 		info.setMail(mailtext.getText().trim());
		 		info.setKhyh(khyhtext.getText().trim());
		 		return info;
		  }
	  public static  boolean checkInput(TbYhinfo info)  {

		 	int rightNum=0;
		 	try {
		 		if(Checker.isRightUserName(info.getName())) {rightNum++;}else{return false;}
		 	} catch (SQLException e) {
		 		// TODO Auto-generated catch block
		 		e.printStackTrace();
		 	}
		 	if(Checker.isTel(info.getTel())&&rightNum==1) {rightNum++;}else {return false;}
		 	if(Checker.isEmail(info.getMail())&&rightNum==2) {rightNum++;}else {return false;}
		 	if(Checker.isContainsSpecialChar(info.getKhyh())&&rightNum==3) {return true;}else {return false;}
		 	
		  }
	  public static  boolean checkInput2(TbYhinfo info)  {

		 	int rightNum=0;
		 	if(Checker.isName(info.getName())&&rightNum==0){rightNum++;}else {return false;}
		 	if(Checker.isTel(info.getTel())&&rightNum==1) {rightNum++;}else {return false;}
		 	if(Checker.isEmail(info.getMail())&&rightNum==2) {rightNum++;}else {return false;}
		 	if(Checker.isContainsSpecialChar(info.getKhyh())&&rightNum==3) {return true;}else {return false;}
		 	
		  }
}
