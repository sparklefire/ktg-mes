##初始化产品物料分类的根节点
insert into md_item_type(item_type_code,item_type_name,parent_type_id,ancestors,item_or_product,order_num,enable_flag 
)values ('ITEM_TYPE_0000','物料产品分类',0,'0','PRODUCT',1,'Y');


insert into dv_machinery_type(machinery_type_code,machinery_type_name,parent_type_id,ancestors,enable_flag)
values ('M_TYPE_000','设备分类',0,'0','Y'); 

##初始化一个虚拟的线边库
insert into wm_warehouse (warehouse_code,warehouse_name,location,area,charge) values('XBK_VIRTUAL','线边库-虚拟',null,-1,null);

##初始化一个虚拟的线边库库区
insert into wm_storage_location (location_code,location_name,warehouse_id,area,area_flag) values('XBKKQ_VIRTUAL','线边库库区-虚拟',200,-1,'Y');


##初始化一个虚拟的线边库库位
insert into wm_storage_area (area_code,area_name,location_id,area,enable_flag) values('XBKKW_VIRTUAL','线边库库位-虚拟',200,-1,'Y');


