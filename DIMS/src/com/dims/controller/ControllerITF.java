package com.dims.controller;

/**
 * �����������ӿ�
 */
public interface ControllerITF <T>{

	/**
	 * �������Ϸ���
	 * @param String Para[]
	 * @return ture/false
	 */
	public  boolean checkInput(String para[]) ;
	/**
	 * �õ�������͵�����ģ��
	 * @param Para
	 * @return T
	 */
	public   T getInput(String para[]);
		
}
