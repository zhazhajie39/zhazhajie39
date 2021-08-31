package com.dims.dao.model;

import java.sql.SQLException;

public class TbMminfo implements java.io.Serializable{

	/**
	 * ҩƷ��Ϣģ����
	 */
	private static final long serialVersionUID = 1L;

	private String id;//ҩƷID
	private String name;//ҩƷ����
	private String cate;//ҩƷ���
	private String unit;//ҩƷ��λ
	private float upri;//ҩƷ����
	private float bid;//ҩƷ����
	private String paddr;//ҩƷ����
	private String spacs;//ҩƷ���
	private int num;//ҩƷ����
	private String pdate;//ҩƷ��������
	private String expirydate;//ҩƷ��������
	private String sup;//ҩƷ��Ӧ����
	private String remark;//��ע
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(bid);
		result = prime * result + ((cate == null) ? 0 : cate.hashCode());
		result = prime * result + ((expirydate == null) ? 0 : expirydate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + num;
		result = prime * result + ((paddr == null) ? 0 : paddr.hashCode());
		result = prime * result + ((pdate == null) ? 0 : pdate.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((spacs == null) ? 0 : spacs.hashCode());
		result = prime * result + ((sup == null) ? 0 : sup.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		TbMminfo other = (TbMminfo) obj;
		if (Float.floatToIntBits(bid) != Float.floatToIntBits(other.bid))
			return false;
		if (cate == null) {
			if (other.cate != null)
				return false;
		} else if (!cate.equals(other.cate))
			return false;
		if (expirydate == null) {
			if (other.expirydate != null)
				return false;
		} else if (!expirydate.equals(other.expirydate))
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
		if (num != other.num)
			return false;
		if (paddr == null) {
			if (other.paddr != null)
				return false;
		} else if (!paddr.equals(other.paddr))
			return false;
		if (pdate == null) {
			if (other.pdate != null)
				return false;
		} else if (!pdate.equals(other.pdate))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (spacs == null) {
			if (other.spacs != null)
				return false;
		} else if (!spacs.equals(other.spacs))
			return false;
		if (sup == null) {
			if (other.sup != null)
				return false;
		} else if (!sup.equals(other.sup))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (Float.floatToIntBits(upri) != Float.floatToIntBits(other.upri))
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
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getUpri() {
		return upri;
	}
	public void setUpri(float upri) {
		if(upri>=0) {
		this.upri=upri;
		}
		else {
			this.upri=0;
		}
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		if(bid>=0) {
		this.bid = bid;
		}
		else {
			this.bid=0;
		}
	}
	public String getPaddr() {
		return paddr;
	}
	public void setPaddr(String paddr) {
		this.paddr = paddr;
	}
	public String getSpacs() {
		return spacs;
	}
	public void setSpacs(String spacs) {
		this.spacs = spacs;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		if(num>=0) {
		this.num = num;
		}
		else {
			this.num=0;
		}
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public String getSup() {
		return sup;
	}
	public void setSup(String sup) {
		this.sup = sup;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public TbMminfo() {
		// TODO Auto-generated constructor stub
		this.bid=0;
		this.cate="";
		this.expirydate="";
		this.id="";
		this.name="";
		this.paddr="";
		this.pdate="";
		this.remark="";
		this.spacs="";
		this.sup="";
		this.unit="";
		this.upri=0;
		this.num=0;
		
	}
@Override
	public String toString() {
		return "TbMminfo [id=" + id + ", name=" + name + ", cate=" + cate + ", unit=" + unit + ", upri=" + upri
				+ ", bid=" + bid + ", paddr=" + paddr + ", spacs=" + spacs + ", num=" + num + ", pdate=" + pdate
				+ ", expirydate=" + expirydate + ", sup=" + sup + ", remark=" + remark + "]";
	}

	

	//public static 
	//����
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
//		TbMminfo mm=new TbMminfo();
//		mm.setName("С��");
//		mm.setPdate("2020-2-11");
//		mm.setExpirydate("2022-2-11");
//		if(TbMminfo.addMm(mm)) {
//			System.out.print("��ӳɹ�");
//		}
//		else {
//			System.out.print("���ʧ��");
//		}
//		mm.setId("111111112");
//		
//		if(TbMminfo.delMmById(mm.getId())) {
//			System.out.print("ɾ���ɹ�");
//		}
//		else {
//			System.out.print("ɾ��ʧ��");
//		}

//		if(TbMminfo.delMmByName(mm.getName())) {
//			System.out.print("ɾ���ɹ�");
//		}
//		else {
//			System.out.print("ɾ��ʧ��");
//		}
//		if(TbMminfo.updateMm(mm)) {
//			System.out.print("�޸ĳɹ�");
//		}
//		else {
//			System.out.print("�޸�ʧ��");
//		}
//		if(TbMminfo.updateMmByUpri(mm.getId(),2)) {
//			System.out.print("�޸ĳɹ�");
//		}
//		else {
//			System.out.print("�޸�ʧ��");
//		}
//		if(TbMminfo.updateMmByNum(mm.getId(),100)) {
//			System.out.print("�޸ĳɹ�");
//		}
//		else {
//			System.out.print("�޸�ʧ��");
//		}
//		if(TbMminfo.updateMmByName(mm.getId(),"С�մ�")) {
//			System.out.print("�޸ĳɹ�");
//		}
//		else {
//			System.out.print("�޸�ʧ��");
//		}


	}
}
