package com.dims.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dims.dao.model.TbMminfo;

/**
 * tb_mm��CRUD
 */
public class MmDbOprator {

	private final static String[] stype= {"mname","mcate","msup"};
	private MmDbOprator() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * ҩƷId�Ƿ����
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public static boolean haveId(String id) throws SQLException {
		String sql="select * from tb_mm where mid="+"'"+id+"'";
		ResultSet set=Dao.findForResultSet(sql);
		try {
			if(set.next()) {
				if(set.getString("mid").trim()!=null) {
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
	
	public static TbMminfo[] getMminfoByPage(int page,int num) throws SQLException { 
		PreparedStatement ps=Dao.conn.prepareStatement("select * from tb_mm limit ?,?");//ps���ظ�ʹ��
		//ps.clearBatch();//���Ԥ��������
		ps.setInt(1, (page-1)*num);
		ps.setInt(2, num);
		ResultSet set=ps.executeQuery();
		TbMminfo[] users=new TbMminfo[num];;
		int index=0;
		//
		while(set.next()) {
			users[index]=new TbMminfo();
			String id=set.getString("mid").trim();
			if(id==null) {
				break;
			}
			users[index].setId(id);
			users[index].setName(set.getString("mname").trim());
			users[index].setCate(set.getString("mcate").trim());
			users[index].setUpri(Float.parseFloat(set.getString("mupri").trim()));
			users[index].setBid(Float.parseFloat(set.getString("mbid").trim()));
			users[index].setPaddr(set.getString("mpaddr").trim());
			users[index].setSpacs(set.getString("mspacs").trim());
			users[index].setNum(Integer.parseInt(set.getString("mnum").trim()));
			users[index].setUnit(set.getString("munit").trim());
			users[index].setPdate(set.getString("mpdate").trim());
			users[index].setExpirydate(set.getString("mexpitydate").trim());
			users[index].setSup(set.getString("msup").trim());
			users[index].setRemark(set.getString("mremark").trim());
			index++;
		}
		ps.close();
		set.close();
		return users;
		
	}
	/**
	 * ͨ���ؼ�������ȡ��Ӧ�̵���Ϣ
	 * @param key
	 * @param page
	 * @param num
	 * @return TbMsupinfo[]
	 * @throws SQLException
	 */
	public static TbMminfo[] getSupInfoByKey(String key,int type,int page,int num) throws SQLException {
		// TODO Auto-generated method stub
		String like="'%"+key+"%'";
		String sql="SELECT *  FROM tb_mm WHERE "+stype[type]+" LIKE "+like+"limit ?,?";
		System.out.println(sql);
		PreparedStatement ps=Dao.conn.prepareStatement(sql);//ps���ظ�ʹ��
		//ps.clearBatch();//���Ԥ��������
		ps.setInt(1, (page-1)*num);
		ps.setInt(2, num);
		ResultSet set=ps.executeQuery();
		TbMminfo[] users=new TbMminfo[num];;
		int index=0;
		//
		while(set.next()) {
			users[index]=new TbMminfo();
			String id=set.getString("mid").trim();
			if(id==null) {
				break;
			}
			users[index].setId(id);
			users[index].setName(set.getString("mname").trim());
			users[index].setCate(set.getString("mcate").trim());
			users[index].setUpri(Float.parseFloat(set.getString("mupri").trim()));
			users[index].setBid(Float.parseFloat(set.getString("mbid").trim()));
			users[index].setPaddr(set.getString("mpaddr").trim());
			users[index].setSpacs(set.getString("mspacs").trim());
			users[index].setNum(Integer.parseInt(set.getString("mnum").trim()));
			users[index].setUnit(set.getString("munit").trim());
			users[index].setPdate(set.getString("mpdate").trim());
			users[index].setExpirydate(set.getString("mexpitydate").trim());
			users[index].setSup(set.getString("msup").trim());
			users[index].setRemark(set.getString("mremark").trim());
			index++;
		}
		ps.close();
		set.close();
		return users;
	}
	/**
	 * ����ҩƷID���ҩƷ����
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public static String getMmUpriByID(String id) throws SQLException {
		String where="'"+id+"'";
		String sql="SELECT mupri  FROM tb_mm WHERE mid="+where;
		System.out.println(sql);
		// TODO Auto-generated method stub
		String upri="0";//��¼�ж���������
		ResultSet set=Dao.findForResultSet(sql);
		if(set.next())
		{
			upri=set.getString("mupri");
		}
		set.close();
		return upri;
	}
	public static int getInfoNumByKey(String table,String key,int type) throws SQLException
	{
		String like="'%"+key+"%'";
		String sql="SELECT count(*)  FROM "+table+" WHERE "+stype[type]+" LIKE "+like;
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
	//����ҩƷ��Ϣ
		public static boolean updateMm(TbMminfo mm) throws SQLException
		{
			
			PreparedStatement ps=Dao.conn.prepareStatement("update tb_mm set mname= ? ,mcate= ? ,mupri = ? ,mbid = ? ,mpaddr= ? ,mspacs= ? ,mnum= ? ,munit= ? ,mpdate= ? ,mexpitydate= ? ,msup= ? ,mremark= ? where mid= ? ");//ps���ظ�ʹ��
			//ps.clearBatch();//���Ԥ��������
			ps.setString(1, mm.getName().trim());
			ps.setString(2, mm.getCate().trim());
			ps.setString(3, Float.toString(mm.getUpri()));
			ps.setString(4, Float.toString(mm.getBid()));
			ps.setString(5, mm.getPaddr().trim());
			ps.setString(6, mm.getSpacs().trim());
			ps.setString(7, Integer.toString(mm.getNum()).trim());
			ps.setString(8, mm.getUnit().trim());
			ps.setString(9, mm.getPdate().trim());
			ps.setString(10, mm.getExpirydate().trim());
			ps.setString(11, mm.getSup().trim());
			ps.setString(12, mm.getRemark());
			ps.setString(13, mm.getId());
			if(ps.executeUpdate()>0) { ps.close();return true;}//��ʼִ��
			return false;
			
		}
		/**
		 * 
		 * �����ݿ������ҩƷ��Ϣ
		 * @param TbMminfo
		 * @return boolean
		 * @throws SQLException 
		 */
		
		public static boolean addMm(TbMminfo mm) throws SQLException
		{
			
			PreparedStatement ps=Dao.conn.prepareStatement("insert into tb_mm(mname,mcate,mupri,mbid,mpaddr,mspacs,mnum,munit,mpdate,mexpitydate,msup,mremark) values(?,?,?,?,?,?,?,?,?,?,?,?)");//ps���ظ�ʹ��
			//ps.clearBatch();//���Ԥ��������
			ps.setString(1, mm.getName().trim());
			ps.setString(2, mm.getCate().trim());
			ps.setString(3, Float.toString(mm.getUpri()));
			ps.setString(4, Float.toString(mm.getBid()));
			ps.setString(5, mm.getPaddr().trim());
			ps.setString(6, mm.getSpacs().trim());
			ps.setString(7, Integer.toString(mm.getNum()).trim());
			ps.setString(8, mm.getUnit().trim());
			ps.setString(9, mm.getPdate().trim());
			ps.setString(10, mm.getExpirydate().trim());
			ps.setString(11, mm.getSup().trim());
			ps.setString(12, mm.getRemark());
			if(ps.executeUpdate()>=0) { ps.close();return true;}//��ʼִ��
			return false;
			
		}
		/**
		 * ͨ��ҩƷIDɾ��������¼
		 * @param String id
		 * @return boolean
		 * @throws SQLException
		 */
		public static boolean delMmById(String id) throws SQLException
		{
			String where="'"+id+"'";
			String sql="delete from tb_mm where mid="+where;
			Statement st=Dao.conn.createStatement();
			if(st.executeUpdate(sql)>0) {//executeUpdate(sql)����ֵ����Ӱ�������
				st.close();
				return true;
			};
			return false;
		}
		/**
		 * ͨ��ҩƷ����ɾ��������¼
		 * @param name
		 * @return boolean
		 * @throws SQLException
		 */
		public static boolean delMmByName(String name) throws SQLException
		{
			String where="'"+name+"'";
			String sql="delete from tb_mm where mname="+where;
			Statement st=Dao.conn.createStatement();
			if(st.executeUpdate(sql)>0) {//executeUpdate(sql)����ֵ����Ӱ�������
				st.close();
				return true;
			};
			return false;//û�и���Ϣ
		}
		/**
		 * �޸�ҩƷ������
		 * @param id,num
		 * @return boolean
		 * @throws SQLException 
		 */
		public static boolean updateMmByNum(String id, int num) throws SQLException {
			String where="'"+id+"'";
			String mnum="'"+num+"'";
			String sql="update tb_mm set mnum="+mnum+"where mid="+where;
			Statement st=Dao.conn.createStatement();
			if(st.executeUpdate(sql)>0) {//executeUpdate(sql)����ֵ����Ӱ�������
				st.close();
				return true;
			};
			return false;//û�и���Ϣ
		}
		/**
		 * �޸�ҩƷ����
		 * @param name
		 * @return boolean
		 * @throws SQLException 
		 */
		public static boolean updateMmByName(String id,String name) throws SQLException {
			String where="'"+id+"'";
			String mname="'"+name+"'";
			String sql="update tb_mm set mname="+mname+"where mid="+where;
			Statement st=Dao.conn.createStatement();
			if(st.executeUpdate(sql)>0) {//executeUpdate(sql)����ֵ����Ӱ�������
				st.close();
				return true;
			};
			return false;//û�и���Ϣ
		}
		/**
		 * �޸�ҩƷ����
		 * @param id
		 * @param upri
		 * @return boolean
		 * @throws SQLException
		 */
		public static boolean updateMmByUpri(String id,float upri) throws SQLException {
			String where="'"+id+"'";
			String mupri="'"+upri+"'";
			String sql="update tb_mm set mupri="+mupri+"where mid="+where;
			Statement st=Dao.conn.createStatement();
			if(st.executeUpdate(sql)>0) {//executeUpdate(sql)����ֵ����Ӱ�������
				st.close();
				return true;
			};
			return false;//û�и���Ϣ
		}
		
		/**
		*
		*
		*@return TbMminfo[] 
		 * @throws SQLException 
		*/
		public static TbMminfo[] getAllMminfos() throws SQLException {
			String sql="select * from tb_mm";
			int num=getMmNum();//��¼�ж���������
			int index=0;
			ResultSet set=Dao.findForResultSet(sql);
			TbMminfo[] users=new TbMminfo[num];
			//
			while(set.next()) {
				users[index]=new TbMminfo();
				users[index].setId(set.getString("mid").trim());
				users[index].setName(set.getString("mname").trim());
				users[index].setCate(set.getString("mcate").trim());
				users[index].setUpri(Float.parseFloat(set.getString("mupri").trim()));
				users[index].setBid(Float.parseFloat(set.getString("mbid").trim()));
				users[index].setPaddr(set.getString("mpaddr").trim());
				users[index].setSpacs(set.getString("mspacs").trim());
				users[index].setNum(Integer.parseInt(set.getString("mnum").trim()));
				users[index].setUnit(set.getString("munit").trim());
				users[index].setPdate(set.getString("mpdate").trim());
				users[index].setExpirydate(set.getString("mexpitydate").trim());
				users[index].setSup(set.getString("msup").trim());
				users[index].setRemark(set.getString("mremark").trim());
				index++;
			}
			return users;
		}
		public static int getMmNum() throws SQLException
		{
			return Dao.getInfoNum("tb_mm");
		}
		public static boolean haveName(String name) throws SQLException
		{
			String where="'"+name+"'";
			String sql="select * from tb_mm where mname="+where;
			ResultSet st=Dao.findForResultSet(sql);
			boolean b=st.next();//�Ƿ���ڽ�����������ڷ���false
			st.close();
			return b;
		}
		public static String getMidByName(String name) throws SQLException {
			String id="";
			if(haveName(name)) {
				String where="'"+name+"'";
				String sql="select mid from tb_mm where mname="+where;
				ResultSet st=Dao.findForResultSet(sql);
			while(st.next())
			{
				id=st.getString("mid");
			}
			}
			return id;
		}
		/**
		 * ���۹���ģ���õ�����صĲ���
		 * ����ҩƷID����ҩƷ������������ӷ�����
		 * @param id  sellnum isadd(�Ǽӷ����Ǽ���)
		 *sql ���䣺update money_user_bank set account_money=account_money-100 where id=1
		 * @throws SQLException 
		 */
		public static void updateNumByID(String id,float sellnum,boolean isadd) throws SQLException {
			String where="'"+id+"'";
			String sql=new String();
			if(isadd) {
			sql="update tb_mm set mnum=mnum+"+sellnum+" where mid="+where;
			}
			else {
			sql="update tb_mm set mnum=mnum-"+sellnum+" where mid="+where;	
			}
			System.out.println(sql);
			Statement st=Dao.conn.createStatement();
			st.executeUpdate(sql);
			//System.out.println(st.executeUpdate(sql));//ִ��sql
			//System.out.println("ִ�����");
			st.close();
		}
		public static void main (String[] args) {
//			try {
//				MmDbOprator.updateNumByID("111111122","2", false);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
}
