package com.dims.controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.dims.check.Checker;
import com.dims.dao.model.TbYhinfo;

public class UpdateUserController {

	public UpdateUserController() {
		// TODO Auto-generated constructor stub
	}
 public static boolean checkInput(String oldname,JTextField newname,JTextField pwd,JTextField tel,JTextField mail,JTextField khyh) {
	 int rightNum=0;
	 	try {
	 		if(Checker.isRightUserName(newname.getText(),oldname)) {rightNum++;}else{return false;}
	 	} catch (SQLException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 	}
	 	if(Checker.isPwd(pwd.getText())&&rightNum==1) {rightNum++;}else {return false;}
	 	if(Checker.isTel(tel.getText())&&rightNum==2) {rightNum++;}else {return false;}
	 	if(Checker.isEmail(mail.getText())&&rightNum==3) {rightNum++;}else {return false;}
	 	if(Checker.isContainsSpecialChar(khyh.getText())&&rightNum==4) {return true;}else {return false;}
 }
 public static TbYhinfo getInput(String id,JTextField newname,JTextField pwd,JTextField tel,JTextField mail,JTextField khyh,JComboBox<String> xb,JComboBox<String> ism) {
	 TbYhinfo info=new TbYhinfo();
	    info.setId(id);
	    info.setName(newname.getText().trim());
	    info.setXb((String) xb.getSelectedItem());
	    info.setTel(tel.getText().trim());
		info.setMail(mail.getText().trim());
		info.setKhyh(khyh.getText().trim());
		info.setPwd(pwd.getText());
		info.setIsm((String)ism.getSelectedItem());
		return info;
 }
}
