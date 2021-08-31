package com.dims.check;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.dims.dao.model.TbYhinfo;


public class Checker {
/**
 * 此类用于校验输入的合法性
 */
	private Checker() {
		// TODO Auto-generated constructor stub
	}
	 /**
     * 正则表达式匹配
     * 
     * @param text 待匹配的文本
     * @param reg 正则表达式
     * @return
     * @author GMJ
     */
    private final static boolean match(String text, String reg) {
        
        return Pattern.compile(reg).matcher(text).matches();
    }
	 /**
     * 匹配密码，长度在6-12之间，只能包含字符、数字和下划线。
     * 
     * @param str
     * @return
     * @author GMJ
     */
    public final static boolean isPwd(String str1,String str2) {
    	if(str1.isBlank()&&str1.isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"密码不为空");
    		return false;
    	}
    	if(str2.isBlank()&&str2.isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"密码不为空");
    		return false;
    	}
    	if(!str1.equals(str2))
    	{
    		JOptionPane.showMessageDialog(null,"两次密码输入不一致");
    		return false;
    	}
    	if(match(str1, "^[\\w_]{6,12}$"))
    	{
    		return true;
    	}
    	else 
    	{
    	JOptionPane.showMessageDialog(null,"密码长度为6-12，只能含有数字字符下划线");
    	return false;
    	}
    }
    public final static boolean isNum(String num) {//包括小数
    	if(match(num,"([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])"))
    	{
    		return true;
    	}
    	else {
    		JOptionPane.showMessageDialog(null,"单价进价输入包含非数字或为0");
    		return false;
    	}
    }
    public final static boolean isZhe(String num) {//包括小数
    	if(match(num,"^\\d+$"))
    	{
    		return true;
    	}
    	else {
    		JOptionPane.showMessageDialog(null,"采购数需要为正整数");
    		return false;
    	}
    }
    public final static boolean isPwd(String pwd) {
    	if(pwd.trim().isBlank()&&pwd.trim().isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"密码不为空");
    		return false;
    	}
    	if(match(pwd, "^[\\w_]{6,12}$"))
    	{
    		return true;
    	}
    	else 
    	{
    	JOptionPane.showMessageDialog(null,"密码长度为6-12，只能含有数字字符下划线");
    	return false;
    	}
    }
    public static boolean isRightUserName(String name) throws SQLException {
        if(name.trim().equals("")) {JOptionPane.showMessageDialog(null,"用户名不能为空");return false;}
    	if(TbYhinfo.findUser(name)) {
    		JOptionPane.showMessageDialog(null,"用户名已存在");
    		return false;
    	}
    	return isContainsSpecialChar(name);
    }
    public static boolean  isName(String name) {
    	  if(name.trim().equals("")) {JOptionPane.showMessageDialog(null,"用户名不能为空");return false;}
    	return isContainsSpecialChar(name);
    }
    public static boolean isRightUserName(String newname ,String oldname) throws SQLException {
        if(newname.trim().equals("")) {JOptionPane.showMessageDialog(null,"用户名不能为空");return false;}
    	if(newname.equals(oldname)) {return true;}
        if(TbYhinfo.findUser(newname)) {
    		JOptionPane.showMessageDialog(null,"用户名已使用");
    		return false;
    	}
    	return isContainsSpecialChar(newname);
    }
    /**
     * 是否包含中英文特殊字符，除英文"-_"字符外
     * 
     * @param str
     * @return
     */
    public static boolean isContainsSpecialChar(String text) {
        if(text.trim().equals("")) {JOptionPane.showMessageDialog(null,"用户名和开户银行不能为空");return false;}
        String[] chars={"[","`","~","!","@","#","$","%","^","&","*","(",")","+","=","|","{","}","'",
                ":",";","'",",","[","]",".","<",">","/","?","~","！","@","#","￥","%","…","&","*","（","）",
                "—","+","|","{","}","【","】","‘","；","：","”","“","’","。","，","、","？","]"};
        for(String ch : chars){
            if(text.contains(ch)) 
            	JOptionPane.showMessageDialog(null,"用户名和开户银行不能包含中英文特殊字符");
            	return true;
        }
        return false;
    }
  
    /**
     * 匹配Email地址
     * 
     * @param str
     * @return
     * @author GMJ
     */
    public final static boolean isEmail(String str) {
    	if(str.isBlank()&&str.isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"邮箱不为空");
    		return false;
    	}
    	if(match(str, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
    		return true;
    	}
    	JOptionPane.showMessageDialog(null,"邮箱地址输入错误");
        return false;
    }
    /**
     * 联系电话(手机/电话皆可)验证   
     * 
     * @param text
     * @return
     * @author GMJ
     */
    public final static boolean isTel(String text){
    	if(text.isBlank()&&text.isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"请填写联系方式");
    		return false;
    	}
        if(isMobile(text)||isPhone(text)) { return true;}
        JOptionPane.showMessageDialog(null,"联系电话输入错误");
        return false;
    }
    
    /**
     * 电话号码验证  
     * 
     * @param text
     * @return
     * @author GMJ
     */
    public final static boolean isPhone(String text){
        return match(text, "^(\\d{3,4}-?)?\\d{7,9}$");
    }
    
    /**
     * 手机号码验证   
     * 
     * @param text
     * @return
     * @author GMJ
     */
    public final static boolean isMobile(String text){
        if(text.length()!=11) return false;
        return match(text, "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$");
    }
//    public static void main(String args[]) {
//    	if(Checker.isZhe("32343 ")) {
//    		System.out.println("是");
//    	};
//    	
//    }
}
