package com.dims.check;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.dims.dao.model.TbYhinfo;


public class Checker {
/**
 * ��������У������ĺϷ���
 */
	private Checker() {
		// TODO Auto-generated constructor stub
	}
	 /**
     * ������ʽƥ��
     * 
     * @param text ��ƥ����ı�
     * @param reg ������ʽ
     * @return
     * @author GMJ
     */
    private final static boolean match(String text, String reg) {
        
        return Pattern.compile(reg).matcher(text).matches();
    }
	 /**
     * ƥ�����룬������6-12֮�䣬ֻ�ܰ����ַ������ֺ��»��ߡ�
     * 
     * @param str
     * @return
     * @author GMJ
     */
    public final static boolean isPwd(String str1,String str2) {
    	if(str1.isBlank()&&str1.isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"���벻Ϊ��");
    		return false;
    	}
    	if(str2.isBlank()&&str2.isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"���벻Ϊ��");
    		return false;
    	}
    	if(!str1.equals(str2))
    	{
    		JOptionPane.showMessageDialog(null,"�����������벻һ��");
    		return false;
    	}
    	if(match(str1, "^[\\w_]{6,12}$"))
    	{
    		return true;
    	}
    	else 
    	{
    	JOptionPane.showMessageDialog(null,"���볤��Ϊ6-12��ֻ�ܺ��������ַ��»���");
    	return false;
    	}
    }
    public final static boolean isNum(String num) {//����С��
    	if(match(num,"([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])"))
    	{
    		return true;
    	}
    	else {
    		JOptionPane.showMessageDialog(null,"���۽���������������ֻ�Ϊ0");
    		return false;
    	}
    }
    public final static boolean isZhe(String num) {//����С��
    	if(match(num,"^\\d+$"))
    	{
    		return true;
    	}
    	else {
    		JOptionPane.showMessageDialog(null,"�ɹ�����ҪΪ������");
    		return false;
    	}
    }
    public final static boolean isPwd(String pwd) {
    	if(pwd.trim().isBlank()&&pwd.trim().isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"���벻Ϊ��");
    		return false;
    	}
    	if(match(pwd, "^[\\w_]{6,12}$"))
    	{
    		return true;
    	}
    	else 
    	{
    	JOptionPane.showMessageDialog(null,"���볤��Ϊ6-12��ֻ�ܺ��������ַ��»���");
    	return false;
    	}
    }
    public static boolean isRightUserName(String name) throws SQLException {
        if(name.trim().equals("")) {JOptionPane.showMessageDialog(null,"�û�������Ϊ��");return false;}
    	if(TbYhinfo.findUser(name)) {
    		JOptionPane.showMessageDialog(null,"�û����Ѵ���");
    		return false;
    	}
    	return isContainsSpecialChar(name);
    }
    public static boolean  isName(String name) {
    	  if(name.trim().equals("")) {JOptionPane.showMessageDialog(null,"�û�������Ϊ��");return false;}
    	return isContainsSpecialChar(name);
    }
    public static boolean isRightUserName(String newname ,String oldname) throws SQLException {
        if(newname.trim().equals("")) {JOptionPane.showMessageDialog(null,"�û�������Ϊ��");return false;}
    	if(newname.equals(oldname)) {return true;}
        if(TbYhinfo.findUser(newname)) {
    		JOptionPane.showMessageDialog(null,"�û�����ʹ��");
    		return false;
    	}
    	return isContainsSpecialChar(newname);
    }
    /**
     * �Ƿ������Ӣ�������ַ�����Ӣ��"-_"�ַ���
     * 
     * @param str
     * @return
     */
    public static boolean isContainsSpecialChar(String text) {
        if(text.trim().equals("")) {JOptionPane.showMessageDialog(null,"�û����Ϳ������в���Ϊ��");return false;}
        String[] chars={"[","`","~","!","@","#","$","%","^","&","*","(",")","+","=","|","{","}","'",
                ":",";","'",",","[","]",".","<",">","/","?","~","��","@","#","��","%","��","&","*","��","��",
                "��","+","|","{","}","��","��","��","��","��","��","��","��","��","��","��","��","]"};
        for(String ch : chars){
            if(text.contains(ch)) 
            	JOptionPane.showMessageDialog(null,"�û����Ϳ������в��ܰ�����Ӣ�������ַ�");
            	return true;
        }
        return false;
    }
  
    /**
     * ƥ��Email��ַ
     * 
     * @param str
     * @return
     * @author GMJ
     */
    public final static boolean isEmail(String str) {
    	if(str.isBlank()&&str.isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"���䲻Ϊ��");
    		return false;
    	}
    	if(match(str, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
    		return true;
    	}
    	JOptionPane.showMessageDialog(null,"�����ַ�������");
        return false;
    }
    /**
     * ��ϵ�绰(�ֻ�/�绰�Կ�)��֤   
     * 
     * @param text
     * @return
     * @author GMJ
     */
    public final static boolean isTel(String text){
    	if(text.isBlank()&&text.isEmpty())
    	{
    		JOptionPane.showMessageDialog(null,"����д��ϵ��ʽ");
    		return false;
    	}
        if(isMobile(text)||isPhone(text)) { return true;}
        JOptionPane.showMessageDialog(null,"��ϵ�绰�������");
        return false;
    }
    
    /**
     * �绰������֤  
     * 
     * @param text
     * @return
     * @author GMJ
     */
    public final static boolean isPhone(String text){
        return match(text, "^(\\d{3,4}-?)?\\d{7,9}$");
    }
    
    /**
     * �ֻ�������֤   
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
//    		System.out.println("��");
//    	};
//    	
//    }
}
