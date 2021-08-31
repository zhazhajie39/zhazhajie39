package com.dims.dao.model;

public class TbXsinfo implements java.io.Serializable{

	/**
	 * 销售信息模型类
	 */
	private static final long serialVersionUID = 1L;
    private String selid;//销售单编号
    private String mid;//药品编号
    private String mname;//药品名称
    private String oprator;//操作员名称
    private  int num;//采购数量
    private float total;//总价格
    private String seldate;//销售日期
    private String custom;//顾客名称
    private String customtel;//顾客联系方式
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getSelid() {
		return selid;
	}

	public void setSelid(String selid) {
		this.selid = selid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getOprator() {
		return oprator;
	}

	public void setOprator(String oprator) {
		this.oprator = oprator;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getSeldate() {
		return seldate;
	}

	public void setSeldate(String seldate) {
		this.seldate = seldate;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getCustomtel() {
		return customtel;
	}

	public void setCustomtel(String customtel) {
		this.customtel = customtel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custom == null) ? 0 : custom.hashCode());
		result = prime * result + ((customtel == null) ? 0 : customtel.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((mname == null) ? 0 : mname.hashCode());
		result = prime * result + num;
		result = prime * result + ((oprator == null) ? 0 : oprator.hashCode());
		result = prime * result + ((seldate == null) ? 0 : seldate.hashCode());
		result = prime * result + ((selid == null) ? 0 : selid.hashCode());
		result = prime * result + Float.floatToIntBits(total);
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
		TbXsinfo other = (TbXsinfo) obj;
		if (custom == null) {
			if (other.custom != null)
				return false;
		} else if (!custom.equals(other.custom))
			return false;
		if (customtel == null) {
			if (other.customtel != null)
				return false;
		} else if (!customtel.equals(other.customtel))
			return false;
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
		if (oprator == null) {
			if (other.oprator != null)
				return false;
		} else if (!oprator.equals(other.oprator))
			return false;
		if (seldate == null) {
			if (other.seldate != null)
				return false;
		} else if (!seldate.equals(other.seldate))
			return false;
		if (selid == null) {
			if (other.selid != null)
				return false;
		} else if (!selid.equals(other.selid))
			return false;
		if (Float.floatToIntBits(total) != Float.floatToIntBits(other.total))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbXsinfo [selid=" + selid + ", mid=" + mid + ", mname=" + mname + ", oprator=" + oprator + ", num="
				+ num + ", total=" + total + ", seldate=" + seldate + ", custom=" + custom + ", customtel=" + customtel
				+ "]";
	}

}
