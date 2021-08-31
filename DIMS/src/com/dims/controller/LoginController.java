package com.dims.controller;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
public static String  getName(JTextField nametext) {
	
	return nametext.getText().trim();
}
@SuppressWarnings("deprecation")
public static String  getPwd(JPasswordField pwdtext) {
	
	return pwdtext.getText().trim();
}
public static boolean namePwdIsEmpty(String name,String pwd) {
if(pwd.equals("")||name.equals("")) {
	return true;
}
return false;
}
}