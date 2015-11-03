var host="http://www.hs-dx.com/backend";
//var host="http://www.hs-dx.com/backend-test";
//var host="http://192.168.0.104:7070/backend";

var workItems=["  ",
				{label:"退回上级",key:1,child:[	" ",{label:"派单错误",key:101},
													{label:"其他",key:102}
										]},
				{label:"需求确认",key:2,child:[	" ",{label:"可以继续",key:201},
													{label:"不能继续",key:202}
										]},
				{label:"实地勘察",key:3,child:[" ",	{label:"有资源继续安装",key:301},
												{label:"无资源待装",key:302},
												{label:"无资源退单",key:303},
												{label:"其他",key:304}
										]},
				{label:"上门施工",key:4,child:[" ",	{label:"放纤",key:401},
												{label:"调测",key:402}
										]}
				];

var areaItems=[
				"黄石港区" ,
				"西塞山区",
				"铁山区",
				"下陆区",
				"大冶市"
				];

var deviceForm=["局方赠送","租用局方","购买局方","用户自备"];

var deviceTypes=["无线光猫","有线光猫","无线猫","有线猫","利旧终端","其他"];

module.exports = {
    host: host,
    workItems:workItems,
    AreaItems:areaItems,
    DeviceForm:deviceForm,
	DeviceTypes:deviceTypes
};
