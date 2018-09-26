package com.ancaiyun.util;

public abstract class Constants {
	
	/**
	 * 错误
	 */
	public static String ERROR = "-2";
	/**
	 * 失败
	 */
	public static String FAIL = "-1";
	/**
	 * 成功
	 */
	public static String SUCCESS = "0";
	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";
	
	/**
	 * fastdfs server ip
	 */
	public static final String FASTDFS_PATH = "fastdfsIp";
	/**
	 * fastdfs server ip address
	 */
//	public static final String FASTDFS_SERVER_IP = "http://118.31.46.156";
	public static final String FASTDFS_SERVER_IP = "http://192.168.13.249";
	/**
	 * elasticsearch server ip address
	 */
	public static final String ELASTICSEARCH_SERVER_IP = "http://47.96.1.66";
	
	public static final String ACTIVITY_IP = "http://47.96.1.66:8080";
    
	/**
	 * add增加操作
	 * delete删除
	 * update修改
	 * find查询
	 * @author Administrator
	 *
	 */
	public enum subUserOperation{
		order_address_add,//***功能添加
		order_save,//保存订单
		order_update,//修改订单
		delete,
		update,
		find,
		//自营
		
		//第三方商城
		
		//招投标发布第一步
		Purchaser_bid_add_one,		//招投标信息的添加第一步
		Purchaser_bid_update_one,	//招投标信息的修改第一步
		
		//招投标发布第二步
		Purchaser_bid_add_two,		//招投标信息的添加第二步
		Purchaser_bid_update_two,	//招投标信息的修改第二步
				
		//招投标发布第三步
		Purchaser_bid_add_three,		//招投标信息的添加第三步
		
		//招投标审核前修改
		Purchaser_bid_update_all,	//招投标信息的修改第三步
		
		//开标
		Purchaser_bid_open,
		
		//定标
		Purchaser_bid_decide,
		
		//供应商
		supplier_add,		//添加供应商
		supplier_update,	//修改供应商(审核和冻结)
		
		//供应商资质
		supplierInfo_add,	
		SupplierInfo_update,
		SupplierInfo_delete,
		
		//招投标报名信息
		supplier_bidder_add,
		supplier_bidder_delete,
		
		//项目
		project_add,
		project_update,
		
		
		
		//竞价
		purchaser_other_bid_add,      //竞价采购信息的添加
		purchaser_other_bid_delete,	  //竞价采购信息的删除
		purchaser_other_bid_update,   //竞价采购信息的修改
		
		//报价
		supplier_other_bid_add,     //报价信息的添加    
		supplier_other_bid_delete,     //报价信息的删除
		supplier_other_bid_update,     //报价信息的修改
		
		//招募
		purchaser_recurit_add,		//招募信息的添加
		purchaser_recurit_delete,	//招募信息的删除
		purchaser_recurit_update,	//招募信息的修改
		
		//合同
		contract_add,              //合同添加
		contract_delete,              //合同删除
		contract_update,              //合同修改
		
		//合同订单
		contract_order_add,              //合同订单添加
		contract_order_delete,              //合同订单删除
		contract_order_update,              //合同订单修改
		
		//发货单
		shipment_document_add,              //发货单添加
		shipment_document_delete,           //发货单删除
		shipment_document_update,              //发货单的修改
		
		//结算单
		statements_add,                  //结算单添加
		statements_delete,                  //结算单删除
		statements_update,                  //结算单修改
		//结算单
		statements_invoice_add,                  //发票单添加
		statements_invioce_delete,                  //发票删除
		statements_invoice_update,                  //发票修改
		
		contract_supplementary_agreement_add,      //合同协议添加
		contract_supplementary_agreement_delete,      //合同协议删除
		contract_supplementary_agreement_update,      //合同协议修改
		
		offlineRemittance_add,//增加线下充值
		order_receiveAddress_add,//添加收货地址
		order_receiveAddress_update,//修改收货地址
		order_receiveAddress_delete,//删除收货地址
		order_sendAddress_add,//添加发货地址
		order_sendAddress_update,//修改发货地址
		order_sendAddress_delete,//删除发货地址
		selfSupportOrder_save,//提交自营订单
		selfSupportOrder_update,//修改自营订单
		order_productInfo_add,//添加订单商品信息
		order_productInfo_update,//修改订单商品信息
		invoice_update,//修改发票
		invoiceMember_save,//保存用户发票
		withdraw_add,//添加提现信息
		withdraw_update,//修改提现信息
		shoppingCart_save,//保存购物车
		shoppingCart_update,//修改购物车
		message_save,//保存消息
		message_update,//修改消息
		memberBank_save,//添加个人银行
		memberBank_update,//修改个人银行
		quickPurchase_add,//添加快速购买
		store_update,//修改店铺信息
		store_save,//保存店铺信息
		StoreCollect_save,//保存店铺收藏
		StoreCollect_update,//修改店铺收藏
		productCollect_save,//保存商品收藏
		productCollect_update,//修改商品收藏
		product_save,//发布商品
		product_update,//修改商品状态
		incomeDetail_save,//保存收支详细
		incomeDetail_update,//修改收支详细
		member_update,//修改账号信息
		organization_add,//添加机构
		organization_delete,//删除机构
		childAccount_save,//新增子账号
		error;
	}
}
