-- Add/modify columns 
alter table B_DESIGN_CODE add device_no NVARCHAR2(100);
-- Add comments to the columns 
comment on column B_DESIGN_CODE.device_no
  is '位号';


-- Add/modify columns 
alter table B_DESIGN_CODE add design_count NUMBER(20,4);
-- Add comments to the columns 
comment on column B_DESIGN_CODE.design_count
  is '数量';

 -- 2017/4/24
 -- Add/modify columns
alter table OUT_DEMAND_DETAIL add drawing_detailed_id NVARCHAR2(32);
-- Add comments to the columns
comment on column OUT_DEMAND_DETAIL.drawing_detailed_id
  is '需用明细id';

  -------------------------------------------------------------上面sql脚本已在客户和本地执行 4.25 11：00

insert into SYS_DICTIONARY (DICTIONARY_ID, DICTIONARY_CODE, DICTIONARY_NAME, DICTIONARY_TYPE, DICTIONARY_ORDER, PARENT_ID, IS_DEL)
values ('b2925e385ca14b508e01c9ed9ba731d8', 'stock', '库存台账', '0', null, '0', '1');

insert into SYS_DICTIONARY (DICTIONARY_ID, DICTIONARY_CODE, DICTIONARY_NAME, DICTIONARY_TYPE, DICTIONARY_ORDER, PARENT_ID, IS_DEL)
values ('22e876f795624aabbd99ff40e10a9bb4', 'stock_state', '库存状态', '0', null, 'b2925e385ca14b508e01c9ed9ba731d8', '1');

insert into SYS_DICTIONARY (DICTIONARY_ID, DICTIONARY_CODE, DICTIONARY_NAME, DICTIONARY_TYPE, DICTIONARY_ORDER, PARENT_ID, IS_DEL)
values ('5f5863ead6e243a78cf7ce0de3823574', 'stockUse', '可用', '0', null, '22e876f795624aabbd99ff40e10a9bb4', '1');

insert into SYS_DICTIONARY (DICTIONARY_ID, DICTIONARY_CODE, DICTIONARY_NAME, DICTIONARY_TYPE, DICTIONARY_ORDER, PARENT_ID, IS_DEL)
values ('e1dfd059bd964a8687d3726e272e819d', 'outStockFinish', '出库完成', '0', null, '22e876f795624aabbd99ff40e10a9bb4', '1');

commit;

  -------------------------------------------------------------上面sql脚本已在客户和本地执行 4.28 10：00