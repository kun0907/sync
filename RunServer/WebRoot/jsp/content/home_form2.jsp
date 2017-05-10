<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
  <head>
	<link 	href="<%=request.getContextPath()%>/design/css/content/home_form2.css" rel="stylesheet" type="text/css"/>
	<script src="<%=request.getContextPath()%>/design/js/content/home_form2.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/design/js/config.js" type="text/javascript"></script>
  </head>
  	<div class="filelist" id="filelist">
		<div class="left">
		<form action="<%=request.getContextPath()%>/file/upload.do" enctype="multipart/form-data" method="post" onsubmit="return fileType()">
		<input class='fileName' type="file" name="file" title="APP名称_业务名称_操作名称_其他.Pcap"accept=".pcap" multiple>
		<input class="confirm"  type="submit" value="提交">
		<input type="text"  hidden="" id="view_base">
		<input type="text"  hidden=""  id="view_jingpin">
		
		<br/>
				<p class='format'>APP名称_业务名称_操作名称_其他.Pcap</p>
		</form>

			<div id="file" class="file"></div>
			<div style='font-size: 12px;margin-left: 12px;margin-top: 5px;'>
				<input type="radio" id="rad1" name="rad[]" onclick="DocumentType(1)"/>Txt文档
				<input type="radio" id="rad2" name="rad[]" checked="checked" onclick="DocumentType(2)"/>Pcap文档
				<input type="radio" id="rad3" name="rad[]" onclick="DocumentType(3)"/>查看全部
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div id='nums' style='float: right;margin-right: 55px;'>已选：0</div>
			 </div>
		</div>
		
		
		<div style="float: left; width: 100%;">
			<div class="root" id="root" onclick="root()">分析</div>
		</div>
	</div>
	<div class="norm" id="normid">
		<table>
			<tr>
				<td style="width: 15px;">
					<input type="checkbox"   id="checkAll"  onclick="checkAllCommBox(this.checked)" value="全选" checked='checked'/>
				</td>
				<td>
					<a style="display:inherit;text-decoration: none;"><strong>通用指标</strong></a>
				</td>
			</tr>
		</table>
		
		<div class='comm'  id="container_comm">
			<table>
				<tr><th colspan='6' style='background: #a7dff9;'>用户体验类</th></tr>
				<tr class="tr">
					<td class="index" style='border-right: solid 0px;' title="整个log文件内，所有服务器IP交互时间总和、每一个服务器IP的交互时延">
						<input type="checkbox" class="boxutil" name="a" id="a1" checked='checked'/>交互时间</td>
					<td class="index" style='border-right: solid 0px;' title="整个log文件内，所有服务器IP的总流量（包含IP包头）、每一个服务器IP的交互流量">
						<input type="checkbox" class="boxutil" name="a" id="a2" checked='checked'/>交互流量</td>
				</tr>
				<tr><th colspan='6' style='background: #a7dff9;'>辅助分析类</th></tr>
				<tr><th style='text-align: left;'>性能参数：</th></tr>     
				<tr class="tr">
					<td class="index" style='border-right: solid 0px;' title="整个log文件内，所有服务器IP的总包数、每一个服务器IP的交互数据包数">
						<input type="checkbox" class="boxutil" name="a" id="a3" checked='checked'/>包数</td>
					<td class="index" style='border-right: solid 0px;' title="整个log文件内，所有服务器IP的链路总数、每一个服务器IP的链路数">
						<input type="checkbox" class="boxutil" name="a" id="a4" checked='checked'/>链路数</td>
				</tr>
				<tr><th style='text-align: left;'>信息参数：</td></tr>
				<tr class="tr">
					<td class="index" title="IP归属地，用于判断是否存在调度问题">
						<input type="checkbox" class="boxutil" name="a" id="a5" checked='checked'/>服务器IP归属</td>
					<td class="index" title="HTTP请求方式">
						<input type="checkbox" class="boxutil" name="a" id="a6" checked='checked'/>请求类别</td>
					<td class="index" title="整个log文件内含有的除了服务器IP以外的所有IP地址">
						<input type="checkbox" class="boxutil" name="a" id="a7" checked='checked'/>无效IP</td>
				</tr>
				<tr><th style='text-align: left;'>时延类参数：</th></tr>
				<tr class="tr">
					<td class="index" title="客户端与服务器TCP建链完成后，传输第一个有效数据包之前，耗费的时间">
						<input type="checkbox" class="boxutil" name="a" id="a8" value="Time to First Byte" checked='checked'/>建链后首包时延</td>
					<td class="index" style='border-right: solid 0px;' title="最有一个报文至断链开始的间隔时间">
						<input type="checkbox" class="boxutil" name="a" id="a9" checked='checked'/>断链时延</td>
					<td class="index" style='border-right: solid 0px;' title="DNS服务器响应客户端请求的时间">
						<input type="checkbox" class="boxutil" name="a" id="a10" checked='checked'/>DNS时延</td>
					<td class="index" style='border-right: solid 0px;' title="数据交互时间的平均值">
						<input type="checkbox" class="boxutil" name="a" id="a11" checked='checked'/>平均RTT时间</td>
					<td class="index" style='border-right: solid 0px;' title="客户端与服务器建链时，TCP三次握手完成的时间">
						<input type="checkbox" class="boxutil" name="a" id="a12" checked='checked'/>TCP建链时延</td>
				</tr>
				<tr><th style='text-align: left;'>诊断类参数：</th></tr>
				<tr class="tr">
					<td class="index" style='border-right: solid 0px;' title="报文重传次数">
						<input type="checkbox" class="boxutil" name="a" id="a13" checked='checked'/>重传识别</td>
					<td class="index" title="当前链路与上一条链路的串行或并行关系">
						<input type="checkbox" class="boxutil" name="a" id="a14" checked='checked'/>与上条链路串并行关系</td>
					<td class="index" title="当前链路与上一条链路的间隔时间">
						<input type="checkbox" class="boxutil" name="a" id="a15" checked='checked'/>与上条链路间隔时延</td>
					<td class="index" title="连续三条串行链路且间隔时间小于一秒">
					<input type="checkbox" class="boxutil" name="a" id="a16" checked='checked'/>串行链路频繁拆建链判断</td>
					<td class="index" style='border-right: solid 0px;' title="频繁拆建链情况时，每一条链路的有效数据占比，即每一条链路的有效利用率">
						<input type="checkbox" class="boxutil" name="a" id="a17"checked='checked'/>链路时间有效率</td>
					<td class="index" title="链路时间有效率除以有效链路数的平局值">
						<input type="checkbox" class="boxutil" name="a" id="a18" checked='checked'/>平均链路时间有效率</td>
				</tr>
			</table>
		</div>
	   	<a style="text-decoration: none;"><strong>业务个性指标（勾选相关业务后对比结果将放在一个PDF中）</strong></a>
		<div class="kidney">
			<table>
				<tr class="tr">
					<!-- <td class="index" title="个性参数：
1 TtFS
2 TrFR
3 windows scale检查"><input type="checkbox" class="boxutil" name="a" id="a19"/>视频比较</td>
					<td class="index" title=""><input type="checkbox" class="boxutil" name="a" id="a20" checked="checked"/>基本信息对比</td> -->
					<td class="index" title=""><input type="checkbox" class="boxutil" name="a" id="checkbox_compareConclusion" checked="checked"/>基本信息对比</td>
				</tr>
			</table>
		</div>
		<a style='text-decoration: none;'><strong>图形展示</strong></a>
		<div class="charts">
			<table>
				<tr class="tr">
					<td class="index" title="整个log文件内，所有服务器IP、其他IP的交互流量分布图">
						<input type="checkbox" class="boxutil" name="a" id="a21" checked='checked'/>IP流量分布图</td>
					<td class="index" title="log每隔固定时间的流量曲线图">
						<input type="checkbox" class="boxutil" name="a" id="a22" value="吞吐曲线图" checked='checked'/>吞吐曲线图</td>
					<td class="index" title="三个包长区间的包长和，分别占总包长的百分比">
						<input type="checkbox" class="boxutil" name="a" id="a23" value="包长分布图" checked='checked'/>包长分布图</td>
					<td class="index" title="每条链路中dns、Interval、SYN、Time to First Byte、Interactive、Delay、FIN在时间轴的划分">
						<input type="checkbox" class="boxutil" name="a" id="a24" value="链路图" checked='checked'/>链路图</td>
				</tr>
			</table>
		</div>
		<span class='behaviour'>
			<input type="button" value="存储为"  onclick="backup()"/>
			<input type="button" value="导入" onclick="checkselect()"/>
			<input type="button" value="全选" onclick="allCheck()"/>	
			<input type="button" value="恢复默认" onclick="def()"/>	
			<input type="button" value="确定" onclick="root()" class='affirm'/>
		</span>
	</div>
	<div id="over" class="over"></div>
	<div id="layout" class="layout"><img src="<%=request.getContextPath() %>/design/img/2013112931.gif" alt="" /><br/>正在解析请稍后</div>
