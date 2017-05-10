<%--
  Created by IntelliJ IDEA.
  User: WANGYING
  Date: 2017/3/27
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>列表页</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>

<div class="easyui-panel" title="首页->采购管理->框架协议管理->框架协议编辑" data-options="fit:true,border:false">
  <form id="edit" method="post">
    <input type="hidden" id="agreementId" name="AGREEMENT_ID" value="${agreementId}" />
    <div style="padding:10px">
      <input class="easyui-textbox" id="agreementCode" name="agreementCode" style="width:30%" data-options="readonly:true,label:'编号:'">
      <B>合同执行期限:</B>
      <input class="easyui-datebox " id="performStartDate" name="performStartDate" style="width:12%" >-
      <input class="easyui-datebox " id="performEndDate" name="performEndDate" style="width:12%" >
    </div>
  </form>
  <table id="table1" auto-resize="true" class="easyui-edatagrid" style="width:100%;height:200px" title="供应商列表">
  </table>
  <table id="table2" auto-resize="true" class="easyui-edatagrid" style="width:100%;height:200px" title="物资明细列表" >

  </table>
  <div style="text-align: center;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-back' onclick="exitLayout()">返回</a>
  </div>

</div>


<script type="text/javascript">
  var lastIndex;

  var lastIndex1;
  var supplierNo=[];

  $(function() {
    var agreementId = "${agreementId}";
    $('#edit').form('load', '${emms}/purchase/agreement.do?cmd=agreementDetail&agreementId=${agreementId}');
    $('#edit').form({
      onLoadSuccess: function (data) {
        if(data.agreementDetailList.length>0){
          $("#table2").edatagrid("loadData", loadFilter(data.agreementDetailList));
        }else{
          var agreementDetailList=[];
          $("#table2").edatagrid("loadData", loadFilter(agreementDetailList));
        }
        if(data.agreementSupplierList.length>0){
          $("#table1").edatagrid("loadData", loadFilter(data.agreementSupplierList));
        }else{
          var agreementSupplierList=[];
          $("#table1").edatagrid("loadData", loadFilter(agreementSupplierList));
        }
      }

    })
  })

  $(function(){  $('#table1').edatagrid({
            pagination:  false,
            fitColumns: true,
            rownumbers: true,
            showFooter: true,
            singleSelect:true,

            columns: [[
              {field: 'orgCode', sortable: true, title: '供应商编号', align: 'center', width: '25%'},
              {field: 'orgName', sortable: true, title: '供应商名称', align: 'center', width: '25%'},
              {field: 'supplierAgreementCode', sortable: true, title: '框架协议编码', align: 'center', width: '25%',editor:{type:'textbox',options:{required:true,precision:2,validType:'length[1,30]'}}},
              {field:'supplierOrgId',sortable:true,title:'供应商ID',align:'center',width:'1%',hidden:'true'},
              {field: 'aaa', title: '操作', sortable: true, align: 'center', width: '25%',
                formatter: function (value, row, index) {
                  show = "<a class='easyui-linkbutton' onclick='aaa()'"
                  + " target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
                  return show;
                }
              }
            ]],onDblClickRow:function(row){//运用双击事件实现对一行的编辑
              $('#table1').edatagrid('beginEdit', row);
            },
            onClickRow:function(row) {//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件
              $('#table1').edatagrid('endEdit', lastIndex1);
              $('#table1').edatagrid('endEdit', row);
              lastIndex1 = row;
            }
          });
          }
  )

  $(function (){ $('#table2').edatagrid({
    <%--url: '${emms}/purchase/agreement.do?cmd=loadAgreementDetailListData',--%>
    <%--method: 'POST',--%>
    pagination: false,
    fitColumns: true,
    rownumbers: true,
    showFooter: true,
    singleSelect:true,
    columns: [[
      {field:'materialsId',sortable:true,title:'物资Id',align:'center',hidden:'true'},
      {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'10%'},
      {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'10%'},
      {field:'additional1',sortable:true,title:'附加1',align:'center',width:'8%'},
      {field:'additional2',sortable:true,title:'附加2',align:'center',width:'8%'},
      {field:'additional3',sortable:true,title:'附加3',align:'center',width:'8%'},
      {field:'additional4',sortable:true,title:'附加4',align:'center',width:'8%'},
      {field:'materialsUnitMain',sortable:true,title:'采购计量单位',align:'center',width:'8%'},
      {field:'unitPrice',sortable:true,title:'单价',align:'center',width:'10%'},
      {field:'numberMain',sortable:true,title:'暂估数量',align:'center',width:'10%'},
      {field:'totalPrice',sortable:true,title:'暂估总价',align:'center',width:'10%'},

    ]]

  });

  })

  //后台List处理为
  function loadFilter(data) {//重新组织datagrid数据，把符合条件的内容加到定义的json字符串中。
    var value = {
      total: data.length,
      rows: data
    };
    return value;

  }
  //由编辑页面返回到查询页面
  function exitLayout(){
    window.location = "${emms}/purchase/agreement.do?cmd=query";
  }








</script>
</body>
</html>

