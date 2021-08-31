package com.dims.controller;

/**
 * 控制器公共接口
 */
public interface ControllerITF <T>{

	/**
	 * 检查输入合法性
	 * @param String Para[]
	 * @return ture/false
	 */
	public  boolean checkInput(String para[]) ;
	/**
	 * 得到相关类型的数据模型
	 * @param Para
	 * @return T
	 */
	public   T getInput(String para[]);
		
}
