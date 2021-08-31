package com.dims.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dims.dao.model.TbKcinfo;
import com.dims.dao.model.TbMminfo;
import com.dims.dao.model.TbMsupinfo;

public class KcDbOprator {

	private KcDbOprator() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 获取所有库存信息
	 * @param args
	 * @return  TbKcinfo[]
	 * @throws SQLException 
	 */

	public static TbKcinfo[] getAllKcInfos() throws SQLException {
		String sql="select * from tb_mstock";
		int num=getKcNum();//记录有多少条数据
		int index=0;
		ResultSet set=Dao.findForResultSet(sql);
		TbKcinfo[] kc=new TbKcinfo[num];
		//
		while(set.next()) {
			kc[index]=new TbKcinfo();
			kc[index].setMid(set.getString("kmid").trim());
			kc[index].setSid(set.getString("ksid").trim());
			kc[index].setMname(set.getString("kmname").trim());
			kc[index].setNum(Integer.parseInt(set.getString("kmnum").trim()));
			kc[index].setUpri(Float.parseFloat(set.getString("kmupri").trim()));
			kc[index].setType(Integer.parseInt(set.getString("ktype").trim()));
			index++;
		}
		//开始去重
//		List<TbKcinfo> kclist=new ArrayList<TbKcinfo>();
//		List<String> list=new ArrayList<String>();
//		for (int i=0; i<num; i++) {
//			  if(!list.contains( kc[i].getMname())) {
//				  kclist.add(kc[i]);
//				  list.add(kc[i].getMname());
//			  }
//			  //有
//			  else {
//				  int j=getFirstIndex(kclist, kc[i].getMname(),num);
//				  int newnum= kclist.get(j).getNum()+kc[i].getNum();
//				  kclist.get(j).setNum(newnum);
//			  }
//			}
		return kc;
	}
	public static boolean addKc(TbKcinfo info) throws SQLException {
		PreparedStatement ps=Dao.conn.prepareStatement("insert into tb_mstock(kmid,ksid,kmname,kmnum,kmupri,ktype) values(?,?,?,?,?,?)");//ps可重复使用
		//ps.clearBatch();//清空预处理命令
		ps.setString(1, info.getMid().trim());
		ps.setString(2, info.getSid().trim());
		ps.setString(3, info.getMname().trim());
		ps.setString(4, Integer.toString(info.getNum()));
		ps.setString(5, Float.toString(info.getUpri()));
		ps.setString(6, Integer.toString(info.getType()));
		if(ps.executeUpdate()>=0) { ps.close();return true;}//开始执行
		return false;
	}
	public static boolean addKc(TbMminfo info) throws SQLException {
		PreparedStatement ps=Dao.conn.prepareStatement("insert into tb_mstock(kmid,ksid,kmname,kmnum,kmupri,ktype) values(?,?,?,?,?,?)");//ps可重复使用
		//ps.clearBatch();//清空预处理命令
		ps.setString(1, MmDbOprator.getMidByName(info.getName().trim()));
		ps.setString(2, TbMsupinfo.getSidByName(info.getSup()));
		ps.setString(3, info.getName().trim());
		ps.setString(4, Integer.toString(info.getNum()));
		ps.setString(5, Float.toString(info.getUpri()));
		ps.setString(6, "0");
		if(ps.executeUpdate()>=0) { ps.close();return true;}//开始执行
		return false;
	}
	/**
	 * 清空库存
	 * @return
	 * @throws SQLException
	 */
	public static int delAllKc() throws SQLException {
		
		String sql="truncate table tb_mstock";
		Statement st=Dao.conn.createStatement();
		return st.executeUpdate(sql);
	}
	/**
	 * 初始化库存  需要用到tb_mm tb_msel 两张表
	 * @throws SQLException
	 */
	public static void kcInit() throws SQLException {
		TbMminfo[] info=MmDbOprator.getAllMminfos();
		TbKcinfo kc=null;
		delAllKc();//先清空库存
		for(int i=0;i<info.length;i++) {
			kc=new TbKcinfo();
			kc.setMid(info[i].getId());
			kc.setSid(TbMsupinfo.getSidByName(info[i].getSup()));
			kc.setNum(info[i].getNum());
			kc.setMname(info[i].getName());
			kc.setUpri(info[i].getUpri());
			kc.setType(0);//默认0
			addKc(kc);
		}
		System.out.println("库存初始化完毕");
	}
	/**
	 * 
	 * 得到重复名称的第一个索引
	 */
	//private static int getFirstIndex(List<TbKcinfo>  kc,String name,int length) {
//		for(int i=0;i<length;i++)
//		{
//			if(kc.get(i).equals(name)) {
//				return i;
//			}
//		}
//		return -1;
	//}
	/**
	 * 获取总库存数
	 * @return int
	 * @throws SQLException
	 */
	public static int getKcNum() throws SQLException
	{
		return Dao.getInfoNum("tb_mstock");
	}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			try {
				kcInit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 通过关键字来获取供应商的数量
		 * @param key
		 * @return int 
		 * @throws SQLException
		 */
			public static int getKcNumByKey(String key) throws SQLException {
				String like="'%"+key+"%'";
				String sql="SELECT count(*)  FROM tb_mstock WHERE kmname LIKE "+like;
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
		public static TbKcinfo[] getKcinfoByPage(int page, int num) throws SQLException {
			PreparedStatement ps=Dao.conn.prepareStatement("select * from tb_mstock limit ?,?");//ps可重复使用
			//ps.clearBatch();//清空预处理命令
			ps.setInt(1, (page-1)*num);
			ps.setInt(2, num);
			ResultSet set=ps.executeQuery();
			TbKcinfo[] users=new TbKcinfo[num];;
			int index=0;
			//
			while(set.next()) {
				users[index]=new TbKcinfo();
				String id=set.getString("kmid").trim();
				if(id==null) {
					break;
				}
				users[index].setMid(id);
				users[index].setMname(set.getString("kmname").trim());
				users[index].setNum(Integer.parseInt(set.getString("kmnum").trim()));
				users[index].setUpri(Float.parseFloat(set.getString("kmupri").trim()));
				index++;
			}
			ps.close();
			set.close();
			return users;
			
		}
		public static TbKcinfo[] getKcInfoByKey(String key, int page, int num) throws SQLException {
			// TODO Auto-generated method stub
			String like="'%"+key+"%'";
			String sql="SELECT *  FROM tb_mstock WHERE kmname LIKE "+like+"limit ?,?";
			PreparedStatement ps=Dao.conn.prepareStatement(sql);//ps可重复使用
			//ps.clearBatch();//清空预处理命令
			ps.setInt(1, (page-1)*num);
			ps.setInt(2, num);
			ResultSet set=ps.executeQuery();
			TbKcinfo[] users=new TbKcinfo[num];;
			int index=0;
			//
			while(set.next()) {
				users[index]=new TbKcinfo();
				String id=set.getString("kmid").trim();
				if(id==null) {
					break;
				}
				users[index].setMid(id);
				users[index].setMname(set.getString("kmname").trim());
				users[index].setNum(Integer.parseInt(set.getString("kmnum").trim()));
				users[index].setUpri(Float.parseFloat(set.getString("kmupri").trim()));
				index++;
			}
			ps.close();
			set.close();
			return users;
		}
		/**
		 * 获取某药品的库存数
		 * @param id
		 * @return
		 * @throws SQLException 
		 */
		public static int getKcNumByID(String id) throws SQLException {
			int num=0;
			String like="'"+id+"'";
			String sql="SELECT kmnum  FROM tb_mstock WHERE kmid ="+like;
			System.out.println(sql);
			// TODO Auto-generated method stub
			ResultSet set=Dao.findForResultSet(sql);
			if(set.next())
			{
				num=Integer.parseInt(set.getString("kmnum"));
			}
			set.close();
			return num;
		}
		public static boolean haveId(String id) throws SQLException {
			// TODO Auto-generated method stub
			String sql="select * from tb_mstock where kmid="+"'"+id+"'";
			ResultSet set=Dao.findForResultSet(sql);
			try {
				if(set.next()) {
					if(set.getString("kmid").trim()!=null) {
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
		/**
		 * 销售管理模块用到的相关的操作
		 * 根据药品ID对其药品数量做减法或加法操作
		 * @param id  sellnum isadd(是加法还是减法)
		 *sql 例句：update money_user_bank set account_money=account_money-100 where id=1
		 * @throws SQLException 
		 */
		public static void updateNumByID(String id,float sellnum,boolean isadd) throws SQLException {
			String where="'"+id+"'";
			String sql=new String();
			if(isadd) {
			sql="update tb_mstock set kmnum=kmnum+"+sellnum+" where kmid="+where;
			}
			else {
			sql="update tb_mstock set kmnum=kmnum-"+sellnum+" where kmid="+where;	
			}
			System.out.println(sql);
			Statement st=Dao.conn.createStatement();
			st.executeUpdate(sql);
			//System.out.println(st.executeUpdate(sql));//执行sql
			//System.out.println("执行完毕");
			st.close();
		}
		
}




