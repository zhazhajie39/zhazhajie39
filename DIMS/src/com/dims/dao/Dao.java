package com.dims.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import com.dims.dao.model.*;



/**
 * 数据库的相关操作类
 * @author GMJ
 *
 */
public  class Dao {

	//MySql数据库驱动类的名称
	protected final static String dbClassName="com.mysql.cj.jdbc.Driver";
	//所要连接的数据库的名称
	protected static String dbName="db_dims";
	//访问MySql数据库的路径
	protected static String dbUrl="jdbc:mysql://localhost:3306/"+dbName+"?useSSL=false&serverTimezone=UTC";
    //访问MySql数据库的用户名
	protected static String dbUser="root";
	//访问MySql数据库的密码
    protected static String dbPwd="123456";
    //默认数据库备份路径
    protected static String backupPath="D:\\EclipseProject\\DIMS\\backup";
	//封闭构造方法
	private Dao() {
		// TODO Auto-generated constructor stub
	}
	//数据库的连接套接字
	public static  Connection conn;
	static {//静态初始化Dao类
			try{
				if(conn==null) {
			//实例化数据库的驱动
				Class.forName(dbClassName);
			//连接数据库
				conn=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				System.out.println("数据库已连接");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"请将Mysql的JDBC驱动包复制到lib文件夹中。");
				System.exit(-1);//系统停止运行
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	public static ResultSet findForResultSet(String sql) throws SQLException {
		
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		//st.close();
		return rs;
	}

	//封装数据到数据模型中
	public static TbYhinfo getTbYhInfo(String id)//对应数据表muser
	{
		TbYhinfo info = new TbYhinfo();
		String where="'"+id+"'";
		if(id!=null) {
		String sql="select * from tb_muser where id="+where;
		try {
			ResultSet set=findForResultSet(sql);
			if(set.next())
			{
				info.setId(set.getString("id").trim());
				info.setName(set.getString("name").trim());
				info.setXb(set.getString("xb").trim());
				info.setPwd(set.getString("pwd").trim());
				info.setSub_date(set.getString("sub_date").trim());
				info.setTel(set.getString("tel").trim());
				info.setMail(set.getString("mail").trim());
				info.setKhyh(set.getString("khyh").trim());
				info.setIsm(set.getString("ism").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return info ;
		
	}
	//获得药品信息数据模型
	public static TbMminfo getTbMmInfo(String id)//对应数据表muser
	{
		TbMminfo info = new TbMminfo();
		String where="'"+id+"'";
		if(id!=null) {
		String sql="select * from tb_mm where mid="+where;
		try {
			ResultSet set=findForResultSet(sql);
			if(set.next())
			{
				info.setId(set.getString("mid").trim());
				info.setName(set.getString("mname").trim());
				info.setCate(set.getString("mcate").trim());
				info.setUpri(Float.parseFloat(set.getString("mupri").trim()));
				info.setBid(Float.parseFloat(set.getString("mbid").trim()));
				info.setPaddr(set.getString("mpaddr").trim());
				info.setSpacs(set.getString("mspacs").trim());
				info.setNum(Integer.parseInt(set.getString("mnum").trim()));
				info.setPdate(set.getString("mpdate").trim());
				info.setExpirydate(set.getString("mexpitydate").trim());
				info.setSup(set.getString("msup").trim());
				info.setRemark(set.getString("mremark").trim());
				info.setUnit(set.getString("munit").trim());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return info ;
		
	}
	//获得库存信息模型
	public static TbKcinfo getTbKcInfo(String id)//对应数据表tb_mstock
	{
		TbKcinfo info = new TbKcinfo();
		String where="'"+id+"'";
		if(id!=null) {
		String sql="select * from tb_mstock where kmid="+where;
		try {
			ResultSet set=findForResultSet(sql);
			if(set.next())
			{
				info.setMid(set.getString("kmid").trim());
				info.setMname(set.getString("kmname").trim());
				info.setNum(Integer.parseInt(set.getString("kmnum").trim()));
				info.setSid(set.getString("ksid").trim());
				info.setUpri(Float.parseFloat(set.getString("kmupri").trim()));
				info.setType(Integer.parseInt(set.getString("ktype").trim()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return info ;
		
	}
	//获取供应商信息模型
	public static TbMsupinfo getTbMsupInfo(String id)//对应数据表tb_msupplier
	{
		TbMsupinfo info = new TbMsupinfo();
		String where="'"+id+"'";
		if(id!=null) {
		String sql="select * from tb_msupplier where sid="+where;
		try {
			ResultSet set=findForResultSet(sql);
			if(set.next())
			{
				info.setId(set.getString("sid").trim());
				info.setName(set.getString("sname").trim());
				info.setAddr(set.getString("saddr").trim());
				info.setRemark(set.getString("sremark").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return info ;
		
	}
	//获得销售信息模型
	public static TbXsinfo getTbXsInfo(String id)//对应数据表tb_msupplier
	{
		TbXsinfo info = new TbXsinfo();
		String where="'"+id+"'";
		if(id!=null) {
		String sql="select * from tb_msell where selid="+where;
		try {
			ResultSet set=findForResultSet(sql);
			if(set.next())
			{
				info.setSelid(set.getString("selid").trim());
				info.setMid(set.getString("selmid").trim());
				info.setNum(Integer.parseInt(set.getString("selnum").trim()));
				info.setTotal(Float.parseFloat(set.getString("totalpri").trim()));
				info.setMname(set.getString("selmname").trim());
				info.setOprator(set.getString("oprator").trim());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return info ;
		
	}
	public static void close() throws SQLException {
		if(conn!=null) {
			conn.close();
		}
		System.out.print("数据库已关闭");
	}
	
	public static boolean executeUpdateSQL(String sql) throws SQLException {
		// TODO Auto-generated method stub
		Statement st=conn.createStatement();
		if(st.executeUpdate(sql)==1||st.executeUpdate(sql)==0)
		{
			return true;
		}
		return false;
	}
/**
 * 获取某张表的数据总数
 * @param table
 * @return
 * @throws SQLException
 */
	public static int getInfoNum(String table) throws SQLException
	{
		String sql="select count(*) from "+table;
		int num=0;//记录有多少条数据
		ResultSet set=Dao.findForResultSet(sql);
		if(set.next())
		{
			num=Integer.parseInt(set.getString("count(*)"));
		}
		return num;
	}
	/**
	 * 通过某字段的关键字获取某张表的数据总数
	 * @param table
	 * @param  
	 * @param Key
	 * @return
	 * @throws SQLException
	 */
	public static int getInfoNumByKey(String table,String key) throws SQLException
	{
		String like="'%"+key+"%'";
		String sql="SELECT count(*)  FROM "+table+" WHERE mname LIKE "+like;
		System.out.println(sql);
		// TODO Auto-generated method stub
		int num=0;//记录有多少条数据
		ResultSet set=Dao.findForResultSet(sql);
		if(set.next())
		{
			num=Integer.parseInt(set.getString("count(*)"));
		}
		set.close();
		return num;
	}
	/**
	 * 。。。。。。返回名称相同的字段值数
	 * @param table
	 * @param cloname
	 * @param cloname
	 * @return colvalue
	 * @throws SQLException
	 */
	public static int getInfoNum(String table,String colname,String colvalue) throws SQLException
	{
		String where=" where "+colname+"="+"'"+colvalue+"'";
		String sql="select count(*) from "+table+where;
		int num=0;//记录有多少条数据
		ResultSet set=Dao.findForResultSet(sql);
		if(set.next())
		{
			num=Integer.parseInt(set.getString("count(*)"));
		}
		return num;
	}
	
	//系统备份/备份数据库
	/**
	 * 备份数据库的操作(全部表)
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void backup() 
	{
		
	}
	
	
	/**
	 * 数据库恢复
	 *  恢复全部所用时间太长
	 * @param 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void restore(String sqlfilename) throws IOException, InterruptedException
	{
		
	 }
public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 TbMminfo mm=new TbMminfo();
//		 mm=Dao.getTbMmInfo("111111111");
//		 System.out.println(mm);
//		 TbKcinfo kc=new TbKcinfo();
//		 kc=Dao.getTbKcInfo("11111111");
//		 System.out.println(kc);
//		 TbMsupinfo ms=new TbMsupinfo();
//		 ms=Dao.getTbMsupInfo("111111");
//		 System.out.println(ms);
//		 TbXsinfo xs=new TbXsinfo();
//		 xs=Dao.getTbXsInfo("2222222222");
//		 System.out.println(xs);
		
		 
	}
}