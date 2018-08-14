package com.ccm.bi.core.base.tips;

/**
 * @Auther: Cassidy dev
 * @Date: 2018/8/12 22:05
 * @Description: (* _ *)
 */
public class SuccessTip extends Tip {
	
	public SuccessTip(){

		super.code = 200;
		super.message = "操作成功";
	}
}