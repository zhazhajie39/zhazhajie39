package com.dims.dao.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.dims.dao.Dao;

public class TbMsupinfo implements java.io.Serializable {

	/**
	 * ��Ӧ������ģ��
	 */
	private static final long serialVersionUID = 1L;

	private String id;//��Ӧ��ID
	private String name;//��Ӧ������
	private String addr;//��Ӧ�̵�ַ
	private String remark;//��ע
	public TbMsupinfo() {
		// TODO Auto-generated constructor stub
		this.id="";
		this.name="";
		this.addr="";
		this.remark="";
	}

	@Override
	public String toString() {
		return "TbMsupinfo [id=" + id + ", name=" + name + ", addr=" + addr + ", remark=" + remark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
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
		TbMsupinfo other = (TbMsupinfo) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ��ӹ�Ӧ��
	 * @param info
	 * @return
	 * @throws SQLException
	 */
	public static boolean addSup(TbMsupinfo info) throws SQLException
	{
		PreparedStatement ps=Dao.conn.prepareStatement("insert into tb_msupplier(sname,saddr,sremark) values(?,?,?)");//ps���ظ�ʹ��
		//ps.clearBatch();//���Ԥ��������
		ps.setString(1, info.getName().trim());
		ps.setString(2, info.getAddr().trim());
		ps.setString(3, info.getRemark().trim());
		if(ps.executeUpdate()>=0) { ps.close();return true;}//��ʼִ��
		return false;
	}
	/**
	 * ͨ��IDɾ����Ӧ��
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static boolean delSup(String id) throws SQLException {
		String where="'"+id+"'";
		String sql="delete from tb_msupplier where sid="+where;
		Statement st=Dao.conn.createStatement();
		if(st.executeUpdate(sql)>0) {//executeUpdate(sql)����ֵ����Ӱ�������
			st.close();
			return true;
		};
		return false;
	}
	/**
	 * ɾ�����й�Ӧ����Ϣ
	 * @return
	 * @throws SQLException
	 */
	public static int delAllSup() throws SQLException {
		
		String sql="truncate table tb_msupplier";
		Statement st=Dao.conn.createStatement();
		return st.executeUpdate(sql);
	}
	/**
	 * �޸Ĺ�Ӧ����Ϣ
	 * @param sup
	 * @return
	 * @throws SQLException 
	 */
	public static boolean updateSup(TbMsupinfo sup) throws SQLException
	{
		PreparedStatement ps=Dao.conn.prepareStatement("update tb_msupplier set sname= ? ,saddr= ? ,sremark = ?  where sid= ? ");//ps���ظ�ʹ��
		//ps.clearBatch();//���Ԥ��������
		ps.setString(1, sup.getName().trim());
		ps.setString(2, sup.getAddr().trim());
		ps.setString(3, sup.getRemark());
		ps.setString(4, sup.getId().trim());
		if(ps.executeUpdate()>=0) { System.out.println("update����");ps.close();return true;}//��ʼִ��
		return false;
	}
	/**
	 * ��ȡ��Ӧ�̵�������Ϣ
	 * @return
	 * @throws SQLException
	 */
	public static TbMsupinfo[] getAllSupInfo() throws SQLException {
		String sql="select * from tb_msupplier";
		int num=getSupNum();//��¼�ж���������
		int index=0;
		ResultSet set=Dao.findForResultSet(sql);
		TbMsupinfo[] users=new TbMsupinfo[num];
		//
		while(set.next()) {
			users[index]=new TbMsupinfo();
			users[index].setId(set.getString("sid").trim());
			users[index].setName(set.getString("sname").trim());
			users[index].setAddr(set.getString("saddr").trim());
			users[index].setRemark(set.getString("sremark").trim());
			index++;
		}
		return users;
	}
	/**
	 * ��ȡ��Ӧ������
	 * @return
	 * @throws SQLException
	 */
	public static int getSupNum() throws SQLException
	{
		return Dao.getInfoNum("tb_msupplier");
	}
	public static boolean haveName(String name) throws SQLException
	{
		String where="'"+name+"'";
		String sql="select * from tb_msupplier where sname="+where;
		ResultSet st=Dao.findForResultSet(sql);
		boolean b=st.next();//�Ƿ���ڽ�����������ڷ���false
		st.close();
		return b;
	}
	public static boolean haveId(String id) throws SQLException
	{
		String where="'"+id+"'";
		String sql="select * from tb_msupplier where sid="+where;
		ResultSet st=Dao.findForResultSet(sql);
		boolean b=st.next();//�Ƿ���ڽ�����������ڷ���false
		st.close();
		return b;
	}
	public static String getSidByName(String name) throws SQLException {
		String id="";
		if(haveName(name)) {
			String where="'"+name+"'";
			String sql="select sid from tb_msupplier where sname="+where;
			ResultSet st=Dao.findForResultSet(sql);
		if(st.next())
		{
			id=st.getString("sid");
		}
		}
		return id;
	}


	public static int updateIncreament() throws SQLException {
		// TODO Auto-generated method stub
		String sql="alter table tb_msupplier AUTO_INCREMENT=210000";
		Statement st=Dao.conn.createStatement();
		return  st.executeUpdate(sql);
	}
	//��ҳ��ѯ����
	public static TbMsupinfo[] getSupinfoByPage(int page,int num) throws SQLException { 
		PreparedStatement ps=Dao.conn.prepareStatement("select * from tb_msupplier limit ?,?");//ps���ظ�ʹ��
		//ps.clearBatch();//���Ԥ��������
		ps.setInt(1, (page-1)*num);
		ps.setInt(2, num);
		ResultSet set=ps.executeQuery();
		TbMsupinfo[] users=new TbMsupinfo[num];;
		int index=0;
		//
		while(set.next()) {
			users[index]=new TbMsupinfo();
			String id=set.getString("sid").trim();
			if(id==null) {
				break;
			}
			users[index].setId(id);
			users[index].setName(set.getString("sname").trim());
			users[index].setAddr(set.getString("saddr").trim());
			users[index].setRemark(set.getString("sremark").trim());
			index++;
		}
		ps.close();
		set.close();
		return users;
		
	}
	//ͨ�����Ʋ�ѯ��Ӧ����Ϣ
	
	public static TbMsupinfo[] getSupInfoByName(String name) throws SQLException {
		
			String where="'"+name+"'";
			int num=Dao.getInfoNum("tb_msupplier", "sname", name);
			if(num>0)
				{
				int index=0;
				String sql="select * from tb_msupplier where sname="+where;
			    TbMsupinfo[] infos=new TbMsupinfo[num];
			    ResultSet set=Dao.findForResultSet(sql);
			    while(set.next()) {
			    	infos[index]=new TbMsupinfo();
			    	infos[index].setId(set.getString("sid").trim());
			    	infos[index].setName(set.getString("sname").trim());
			    	infos[index].setAddr(set.getString("saddr").trim());
					infos[index].setRemark(set.getString("sremark").trim());
					index++;
				}
				return infos;
				};
				
				return null;//û�з��ؿ�ֵ
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//��ҳ����
//		try {
//			TbMsupinfo[] info=getSupinfoByPage(1,3);
//			for(int i=0;i<info.length;i++) {
//				if(info[i]!=null) {
//					System.out.println(info[i].getId());
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
/**
 * ͨ���ؼ�������ȡ��Ӧ�̵�����
 * @param key
 * @return int 
 * @throws SQLException
 */
	public static int getSupNumByKey(String key) throws SQLException {
		String like="'%"+key+"%'";
		String sql="SELECT count(*)  FROM tb_msupplier WHERE sname LIKE "+like;
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
	 * ͨ���ؼ�������ȡ��Ӧ�̵���Ϣ
	 * @param key
	 * @param page
	 * @param num
	 * @return TbMsupinfo[]
	 * @throws SQLException
	 */
	public static TbMsupinfo[] getSupInfoByKey(String key,int page,int num) throws SQLException {
		// TODO Auto-generated method stub
		String like="'%"+key+"%'";
		String sql="SELECT *  FROM tb_msupplier WHERE sname LIKE "+like+"limit ?,?";
		PreparedStatement ps=Dao.conn.prepareStatement(sql);//ps���ظ�ʹ��
		//ps.clearBatch();//���Ԥ��������
		ps.setInt(1, (page-1)*num);
		ps.setInt(2, num);
		ResultSet set=ps.executeQuery();
		TbMsupinfo[] users=new TbMsupinfo[num];;
		int index=0;
		//
		while(set.next()) {
			users[index]=new TbMsupinfo();
			String id=set.getString("sid").trim();
			if(id==null) {
				break;
			}
			users[index].setId(id);
			users[index].setName(set.getString("sname").trim());
			users[index].setAddr(set.getString("saddr").trim());
			users[index].setRemark(set.getString("sremark").trim());
			index++;
		}
		ps.close();
		set.close();
		return users;
	}
}
