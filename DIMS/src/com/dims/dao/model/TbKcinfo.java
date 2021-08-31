package com.dims.dao.model;

public class TbKcinfo implements java.io.Serializable{

	/**
	 * 库存模型类
	 */
	private static final long serialVersionUID = 1L;

	private String mid;//药品ID
	private String sid;//供应商ID
	private String mname;//药品名称
	private int num ;//库存数
	private float upri;//药品单价
	private int type;//仓库类型
	

	@Override
	public String toString() {
		return "TbKcinfo [mid=" + mid + ", sid=" + sid + ", mname=" + mname + ", num=" + num + ", upri=" + upri
				+ ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((mname == null) ? 0 : mname.hashCode());
		result = prime * result + num;
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + type;
		result = prime * result + Float.floatToIntBits(upri);
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
		TbKcinfo other = (TbKcinfo) obj;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		if (mname == null) {
			if (other.mname != null)
				return false;
		} else if (!mname.equals(other.mname))
			return false;
		if (num != other.num)
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (type != other.type)
			return false;
		if (Float.floatToIntBits(upri) != Float.floatToIntBits(other.upri))
			return false;
		return true;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getUpri() {
		return upri;
	}

	public void setUpri(float upri) {
		this.upri = upri;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public TbKcinfo() {
		// TODO Auto-generated constructor stub
		this.mid="";
		this.mname="";
		this.sid="";
	}


}
