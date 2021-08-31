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
 * ���ݿ����ز�����
 * @author GMJ
 *
 */
public  class Dao {

	//MySql���ݿ������������
	protected final static String dbClassName="com.mysql.cj.jdbc.Driver";
	//��Ҫ���ӵ����ݿ������
	protected static String dbName="db_dims";
	//����MySql���ݿ��·��
	protected static String dbUrl="jdbc:mysql://localhost:3306/"+dbName+"?useSSL=false&serverTimezone=UTC";
    //����MySql���ݿ���û���
	protected static String dbUser="root";
	//����MySql���ݿ������
    protected static String dbPwd="123456";
    //Ĭ�����ݿⱸ��·��
    protected static String backupPath="D:\\EclipseProject\\DIMS\\backup";
	//��չ��췽��
	private Dao() {
		// TODO Auto-generated constructor stub
	}
	//���ݿ�������׽���
	public static  Connection conn;
	static {//��̬��ʼ��Dao��
			try{
				if(conn==null) {
			//ʵ�������ݿ������
				Class.forName(dbClassName);
			//�������ݿ�
				conn=DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				System.out.println("���ݿ�������");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"�뽫Mysql��JDBC���������Ƶ�lib�ļ����С�");
				System.exit(-1);//ϵͳֹͣ����
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

	//��װ���ݵ�����ģ����
	public static TbYhinfo getTbYhInfo(String id)//��Ӧ���ݱ�muser
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
	//���ҩƷ��Ϣ����ģ��
	public static TbMminfo getTbMmInfo(String id)//��Ӧ���ݱ�muser
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
	//��ÿ����Ϣģ��
	public static TbKcinfo getTbKcInfo(String id)//��Ӧ���ݱ�tb_mstock
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
	//��ȡ��Ӧ����Ϣģ��
	public static TbMsupinfo getTbMsupInfo(String id)//��Ӧ���ݱ�tb_msupplier
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
	//���������Ϣģ��
	public static TbXsinfo getTbXsInfo(String id)//��Ӧ���ݱ�tb_msupplier
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
		System.out.print("���ݿ��ѹر�");
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
 * ��ȡĳ�ű����������
 * @param table
 * @return
 * @throws SQLException
 */
	public static int getInfoNum(String table) throws SQLException
	{
		String sql="select count(*) from "+table;
		int num=0;//��¼�ж���������
		ResultSet set=Dao.findForResultSet(sql);
		if(set.next())
		{
			num=Integer.parseInt(set.getString("count(*)"));
		}
		return num;
	}
	/**
	 * ͨ��ĳ�ֶεĹؼ��ֻ�ȡĳ�ű����������
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
		int num=0;//��¼�ж���������
		ResultSet set=Dao.findForResultSet(sql);
		if(set.next())
		{
			num=Integer.parseInt(set.getString("count(*)"));
		}
		set.close();
		return num;
	}
	/**
	 * ����������������������ͬ���ֶ�ֵ��
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
		int num=0;//��¼�ж���������
		ResultSet set=Dao.findForResultSet(sql);
		if(set.next())
		{
			num=Integer.parseInt(set.getString("count(*)"));
		}
		return num;
	}
	
	//ϵͳ����/�������ݿ�
	/**
	 * �������ݿ�Ĳ���(ȫ����)
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void backup() 
	{
		
	}
	
	
	/**
	 * ���ݿ�ָ�
	 *  �ָ�ȫ������ʱ��̫��
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