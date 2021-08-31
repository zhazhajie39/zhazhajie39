package com.dims.dao.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dims.dao.Dao;
import com.dims.login.LoginFrame;

public class TbYhinfo implements java.io.Serializable{

	/**
	 *系统用户模型类  对应数据库表tb_muser
	 *@author GMJ
	 */
	private static final long serialVersionUID = 1L;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSub_date() {
		return sub_date;
	}
	public void setSub_date(String sub_date) {
		this.sub_date = sub_date;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getKhyh() {
		return khyh;
	}
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	public String getIsm() {
		return ism;
	}
	public void setIsm(String ism) {
		this.ism = ism;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String id;//用户id
	private String name;//用户名
	private String xb;//性别
	private String pwd;//密码
	private String sub_date;//注册日期
	private String tel;//电话
	private String mail;//邮箱
	private String khyh;//开户银行
	private String ism;//是否是系统管理员
	public TbYhinfo() {
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ism == null) ? 0 : ism.hashCode());
		result = prime * result + ((khyh == null) ? 0 : khyh.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((sub_date == null) ? 0 : sub_date.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((xb == null) ? 0 : xb.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbYhinfo other = (TbYhinfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ism == null) {
			if (other.ism != null)
				return false;
		} else if (!ism.equals(other.ism))
			return false;
		if (khyh == null) {
			if (other.khyh != null)
				return false;
		} else if (!khyh.equals(other.khyh))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (sub_date == null) {
			if (other.sub_date != null)
				return false;
		} else if (!sub_date.equals(other.sub_date))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (xb == null) {
			if (other.xb != null)
				return false;
		} else if (!xb.equals(other.xb))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TbYhinfo [id=" + id + ", name=" + name + ", xb=" + xb + ", pwd=" + pwd + ", sub_date=" + sub_date
				+ ", tel=" + tel + ", mail=" + mail + ", khyh=" + khyh + ", ism=" + ism + "]";
	}
	//从数据库中查找通过用户名 登录验证
	public  final static boolean userIsLogin(String name,String pwd) throws SQLException  {
			String where="'"+name+"'";
			String sql ="select * from tb_muser where name="+where;
				ResultSet set=Dao.findForResultSet(sql);
				if(set==null)
				{
					return false;
				}
				if(set.next()) {
				//System.out.println(set.getString("pwd").trim());
					if(set.getString("pwd").trim().equals(pwd))
					{
						LoginFrame.cur_userID=set.getString("id").trim();//密码校验正确后保存用户ID
						return true;
					}
				}
						return false;
		}
	//添加用户
	public static boolean addUser(TbYhinfo info ) 
	{
	//INSERT INTO `db_dims`.`tb_muser`(`name`, `xb`, `pwd`, `sub_date`, `tel`, `mail`, `khyh`, `ism`) VALUES ('李芳', '女', '123456', '2021-03-01', '18848487749', '342312322@qq.com', '建设银行永州支行', 0)；
	String value="('"+info.getName()+"','"+info.getXb()+"','"+info.getPwd()+"','"+info.getSub_date()+"','"+info.getTel()+"','"+info.getMail()+"','"+info.getKhyh()+"','"+info.getIsm()+"')";
	String sql="INSERT INTO db_dims.tb_muser(name,xb,pwd,sub_date,tel,mail,khyh,ism) VALUES"+value;
	try {
		if(Dao.executeUpdateSQL(sql)) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	}
	//根据用户id修改密码
	public static boolean motifiedPwd(String id,String pwd) 
	{
	//INSERT INTO `db_dims`.`tb_muser`(`name`, `xb`, `pwd`, `sub_date`, `tel`, `mail`, `khyh`, `ism`) VALUES ('李芳', '女', '123456', '2021-03-01', '18848487749', '342312322@qq.com', '建设银行永州支行', 0)；
	String value="'"+id+"'";
	String sql="update db_dims.tb_muser set pwd="+"'"+pwd+"'"+"where id ="+value;
	System.out.println(sql);
	try {
		if(Dao.executeUpdateSQL(sql)) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	}
	public static boolean findUser(String name) throws SQLException {
		String sql="select * from tb_muser where name="+"'"+name+"'";
		ResultSet set=Dao.findForResultSet(sql);
		try {
			if(set.next()) {
				if(set.getString("name").trim()!=null) {
					set.close();
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		set.close();
		return false;
	}
	//用户删除操作
	public static boolean delUser(String name) {
		String sql="delete from tb_muser where name="+"'"+name+"'";
		try {
			if(Dao.executeUpdateSQL(sql)) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//获取表中有多少的数据并封装到数据模型中
	public static TbYhinfo [] getAllUserInfo() throws SQLException {
		String sql="select * from tb_muser";
		int num=Dao.getInfoNum("tb_muser");//记录有多少条数据
		int index=0;
		ResultSet set=Dao.findForResultSet(sql);
		TbYhinfo[] users=new TbYhinfo[num];
		//
		while(set.next()) {
			users[index]=new TbYhinfo();
			users[index].setId(set.getString("id").trim());
			users[index].setName(set.getString("name").trim());
			users[index].setXb(set.getString("xb").trim());
			users[index].setPwd(set.getString("pwd").trim());
			users[index].setSub_date(set.getString("sub_date").trim());
			users[index].setTel(set.getString("tel").trim());
			users[index].setMail(set.getString("mail").trim());
			users[index].setKhyh(set.getString("khyh").trim());
			users[index].setIsm(set.getString("ism").trim());
			index++;
		}
		return users;
	}
	//用户信息修改
	public static boolean updateUser(TbYhinfo curUser) throws SQLException {
		String where="'"+curUser.getId()+"'";
		String sql="update db_dims.tb_muser set name="+"'"+curUser.getName()+"' ,"+ "xb="+"'"+curUser.getXb()+"',"+ " tel="+"'"+
		curUser.getTel()+"' ,"+"mail="+"'"+curUser.getMail()+"' ,"+" khyh="+"'"+curUser.getKhyh()+"'"+" where id="+where;
		System.out.println(sql);
		if(Dao.executeUpdateSQL(sql)) {
			return true;
		}
		return false;
	}
//	public static void main(String args[]) {
//		try {
//			int num=Dao.getInfoNum("tb_muser");
//			System.out.println(num);
//			TbYhinfo [] tbs=TbYhinfo.getAllUserInfo();
//			System.out.print(tbs);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
