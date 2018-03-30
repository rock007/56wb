package com.whfp.dxonline.pay.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088911856933751";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串
	public static String seller_id = partner;
	// 商户的私钥
	//public static String private_key ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDltNFz+q2Qa98C4gqmvJIXRTrNBhtqGpZloRkBr7wa8877jhwAUqPlvGYWo4/pLribeHV6UykNqttfxFxmUWF1RnCzUND2RO3TjUfKSys4I9A8efnwzSP/AYatzjWGpzoDOPRXO5PkjaMJCq+cpCf6rgvAlue8cR2xqK5RCNLvgQIDAQAB";
	public static String private_key = "MIIEqAIBADANBgkqhkiG9w0BAQEFAASCBJIwggSOAgEAAoH+AJpr97WUr2TiOLqTmf1jbQKIneRlhSsifeUWvS1d9No8fCHPDewLaFsRfavfW2eTTxfTrSQem9XAoaiPAFDjdQszzkqdIHVdrxyRFomjaMJ/veGVa0kW+OXX89gUEX5HWKLkVWgh0Px/mN68jhGLWgJ91bDaGu70huLm0TcnBUszQy2uRuG0MIIP8s3kZ6MkKf+3KZTFElp34rXJVdz6ZjAE6JfroJkdv00yiBTUJkxRcLeiS44IBzVqYG54/aTO4ctBOTD1v1koIiip0lMixL5JWz4aPgttRoVZJj1wf+VhBdlSPpa0J1qr56E+jG+L7WOQKPLCfKMbKV0JBj8CAwEAAQKB/Q7Ao9CYHM2BgzPn5/06I27riFIKlNdTGz3olzgfUsYKoz1Dn6veRyD0IdJqqzvQ6ex3z4G03uRj283To48Wu9bPIzUjn7c2tQUAo2uQzfBO7eAAO1inNi4XHHeXKziqLkCZmcMx/9ovgnY9A0GP05CLXv/fRlVvzPceX/xp4Og3TRjj2YLnp6QFvtmmW3DgFw41h4qWa/7kP2qzkTVPJSIMwBXNhtZob7c1zPu5nJQYUJtapVGtCpQFrBqEwB5iaVLnQttx2984Si+AYZI/FPq+jD701BT6Vpog5ooZ2ctOHREjyb0OuWdy8IET2UO1S0mHTM1fyzahzOm+7fkCfwyvQjD/8pivC0kL26mZkJfOWnyFuMFRak2RCyRv5Gt05K5hVkm1+YREAsYiqy7lGRPmegwgT6PWTjS7sW6ZXC5ATjwtXLztzwi7DRjxM+zIoBPa9Oya4kMQSWl41XYGVvUXiAy5BTWMDpRC94oEfugKaBLlCGPTJ3yO6q6nUAsCfwwsiBLDP1xP/TTe2Lr/RnRlT1ttENDrEQm+TzVPojpZXp7136SfMws7C38wupzNOY4VJtl/GCfBwsW2KTS/bP+NE1I2VuzI9Uyd7Ukvw3WojqeWokfN0jPMwL0zKxSDeVFw2fNTT4GaUa8hbTUZ+46EDlaSvpAjHR3WjphO/x0CfwZ/ZFW7Vmb2C77gTyaCnnnU3683xJtqfBy9tsp0crrExzu4ar4ZdHZXbGBYc/3ypzT1dAFwL7uKVMdYehxgfMLIeAsFZaL14n7jamMjc2jQoIdrOtlUwaB+4Ewh9J6fwr2Lz0pNIKj8VJ+H6kuN4AA3DHbZUcHo8Sox/292D18CfwTSl00nf7AoF9yb8yuQ+O4fPCKxwBmjJWrAs0whMdj/klZSfLgQQP5P86v9N8/qFMTOaEd866rEPU5pbUVyF9HcpyzITgMbUOF7Job5nuq7TVfieoMLbBwNHsVUwdosgyDaHd0gwbxLz9Qa39JLarX4Zl2xYEBlSKGLX43CrZkCfwdlVLpcIP8+nIOqTKKk+nX+XLSTp9u98/Rzpatt0zAwz0WNfk2AJvwMcktUVo9/3LjHivr7CMu9dRgFrYguLlg9JSzV2r3+kj8IClSlY4wJmoF2OUPFL8m5At0RdFGevQgrMpQCZaTDMyaGaxqZdTZeBtc2l0iMVL3yLu1DtZg=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/home/hello/workspace/logs";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

	//卖家支付宝帐户
	public static String seller_email="18086337555@189.cn";
}
