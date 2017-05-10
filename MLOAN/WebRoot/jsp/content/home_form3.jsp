<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <head>
 	<script src="<%=request.getContextPath()%>/design/js/off/jquery-3.0.0.js" type="text/javascript"></script>
  	<script src="<%=request.getContextPath()%>/design/js/off/html2canvas_fixTheBugThatOnlyCutCurrScreen.js" type="text/javascript"></script>
  
    <script src="<%=request.getContextPath()%>/design/js/off/echarts.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/design/js/content/home_form3.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/design/js/content/template.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/design/js/off/highcharts1.js"></script>
    <script src="<%=request.getContextPath()%>/design/js/off/exporting.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/design/css/content/home_form3.css" />
    <style type="text/css">
    		#table_test{
    				width: 1300px;
    				table-layout:fixed;
    		}
    		
    		#table_test tr td{
    			word-break:break-all;
    		}
    		
    		
    </style>
  </head>
  <input type="text" hidden="" id="contextPath" value="${pageContext.request.contextPath}"/>
 
  <div class='norm' id='norm'>
  <div id="information" style="position: relative;" ></div>
  
  <!--系统信息 -->
  <!-- 	<span class="name"></span> -->
  <script id="information_system" type="text/html">
	<div class="sys">
			<span class="username" ><em>{{userName}}</em></span>
			<span class="startTime" ><em>{{startTime}}</em></span>
			<span class="businesType" ><i>业务类型：</i><em>{{businessType}}</em></span>
			<span class="version" ><i>版本号：</i><em>{{version}}</em></span>
			{{if userAgents.length==1}}
						<span class="version" ><i>测试终端：</i><em>{{userAgents[0]}}</em></span>
			{{else}}
						<br/>			
						<span class="version" style="margin-left:23px" ><i>测试终端：</i></span>
						<table style="margin-left:53px">
							{{each userAgents as userAgent i}}
								<tr>
									<td>
										<span class="version" ><em>{{userAgent}}</em></span>
									</td>
								</tr>
							{{/each}}
						</table>
			{{/if}}
	</div>
  </script>
  
  <!--报文处理概述(无论是否对比都要显示) -->
  <script id="summarize" type="text/html">
    <div class="messageSummarize" id="messageSummarize">
    	<span  style="font-weight:bold;font-size: 18px;">报文处理概述</span>
    	<table id="table_messageSummarize" cellpadding="0" cellspacing="0">
    		<tr class="tr_img">
    			<td valign="top" style="border-right:1px solid black;"></td>
    			<td valign="top" style="border-right:1px solid black;"><img src="${pageContext.request.contextPath}/design/img/pcapjiexi.jpg"></img></td>
    			<td valign="top" style="border-right:1px solid black;"><img src="${pageContext.request.contextPath}/design/img/pcapqingxi.jpg"></img></td>
    			<td valign="top" style="border-right:1px solid black;"><img src="${pageContext.request.contextPath}/design/img/canshujisuan.jpg"></img></td>
    			<td valign="top" ><img src="${pageContext.request.contextPath}/design/img/zhuanjiaxitong.jpg"></img></td>
    		</tr>
    		<tr class="tr_title">
    			<td valign="top" style="border-right:1px solid black;border-bottom:1px solid black;"></td>
    			<td valign="top" style="border-right:1px solid black;border-bottom:1px solid black;">报文解析</td>
    			<td valign="top" style="border-right:1px solid black;border-bottom:1px solid black;">报文清洗</td>
    			<td valign="top" style="border-right:1px solid black;border-bottom:1px solid black;">参数计算</td>
    			<td valign="top" style="border-bottom:1px solid black;">专家系统</td>
    		</tr>
    		{{each basicConclusions.data as value i}}
    			<tr style="font-size: 15px;">
    				<td valign="top" style="border-right:1px solid black;{{if i<(basicConclusions.data.length-1)}}border-bottom:1px solid black;{{/if}}">
						{{value.fileName}} 报文
					</td>
    				<td valign="top" style="border-right:1px solid black;{{if i<(basicConclusions.data.length-1)}}border-bottom:1px solid black;{{/if}}">
						<font class='font2'>◆   </font>
						<font class="font1">{{value.packetCount}}</font>条报文
					</td>
    				<td valign="top" style="border-right:1px solid black;{{if i<(basicConclusions.data.length-1)}}border-bottom:1px solid black;{{/if}}">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font class='font2'>◆   </font>
						<font class="font1">{{value.ipCount}}</font>条有效ip,已清洗
						<font class="font1">{{value.othersIpCount}}</font>条<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font class='font2'>◆   </font><font class="font1">{{value.tcpCount}}</font>条有效链路,已清洗
						<font class="font1">{{value.othersTcpCount}}</font>条<br/>
    				</td>
    				<td valign="top" style="border-right:1px solid black;{{if i<(basicConclusions.data.length-1)}}border-bottom:1px solid black;{{/if}}">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font class='font2'>◆   </font>可计算
						<font class="font1">{{value.permissableParameterCount}}</font>个参数<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					{{if value.permissableParameterCount!=18}}
    						<font class='font2'>◆   </font>{{value.noPermissablePara}}
							<font class="font1">{{value.noPermissableParaNum}}</font>个参数无法计算
    					{{/if}}
    				</td>
    				<td valign="top" style="{{if i<(basicConclusions.data.length-1)}}border-bottom:1px solid black;{{/if}}">
						<font class='font2'>◆   </font>
						<font class="font1">{{value.adviceNum}}</font>条建议
					</td>
    			</tr>
    		{{/each}}
    	</table>
    </div>
  </script>
  
  <!--基本结论(非比较时要显示上面的部分，但不显示对比部分) -->
  <script id="basicConclusion" type="text/html">
	<div class="bw"  id="div_basicConclusion">  
		<div class="bw_body">
			<div class="wenti5">
	
				<span style="font-weight:bold;font-size: 18px;">结论概述</span><br>
				<span style="font-weight:bold;font-size: 18px;margin-left: 50px;">{{basicConclusions.str1}}</span><br/>
  				<hr  style="border-color: whitesmoke;margin-left: 50px;" />
				
				<div style="margin-left:50px"  id="div_jibenjielun">
					{{each basicConclusions.data as value i}}
						<div class="div_each" style="width:1000px">
						{{if value.crossProviceNum>1||value.sumOftenOffVerdict!=0||value.downloadRate!='N/A'}}
							{{if i!=0}}<br/>{{/if}}
							<font style="font-weight:bold">{{value.fileName}}</font>报文涉及 <font style="font-weight:bold"> {{value.conclusionRes}}</font>  问题情况：<br/>
							 <div id="wenti_basicConclusion" style="margin-left:50px;width:1000px;word-wrap: break-word">
											{{if value.crossProviceNum >1}}
													<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>
													 服务器涉及{{value.crossProviceNum}}个地区：   {{value.crossProvice}}；<br/>
											{{/if }}
											{{if value.sumOftenOffVerdict>0}}
													<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>
													频繁拆建链共涉及{{value.sumOftenOffVerdict}}次；<br/>
											{{/if }}
											{{if value.downloadRate!='N/A'}}
													<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>
													链路不支持 TCP WINDOW SCALE OPTION参数 问题，涉及：{{value.downloadRate}}；<br/>
											{{/if }}
							 </div>
						{{/if}}
						</div>
					{{/each}}
				</div>
{{if basicConclusions.data[0].checkbox_compareConclusion}}
				</br><span style="font-weight:bold;font-size: 18px;margin-left: 50px;">对比结论</span><br/>
				<hr class="bw_xian" style="border-color: whitesmoke;margin-left: 50px;"></hr>
				{{if  basicConclusions.compareInfo&& basicConclusions.data[0].checkbox_compareConclusion&&
				  	(basicConclusions.compareInfo.timeEfficiencyAVG=='劣于'||
					   basicConclusions.compareInfo.avgExchangeTime=='劣于'||
					   basicConclusions.compareInfo.smallPacketCount=='劣于'||
					   basicConclusions.compareInfo.exchangeFlowCount=='劣于'||
					   basicConclusions.compareInfo.pcakageCompareMsg||
					   basicConclusions.compareInfo.tcpCountCompareMsg)}}
					<span  style="margin-left: 50px;font-size: 14px;">
						<font style="font-weight:bold">{{basicConclusions.data[0].fileName}}</font> 
						报文与竞品相比，在
						<font style="font-weight:bold">
							{{each basicConclusions.compareInfo.wholeRes as value i}}
								{{if i==0}} {{value}} {{else}} 、{{value}} {{/if}}
							{{/each}} 
						</font>
					 	方面可能存在问题。
					</span>	
					</br>
					<div style="margin-left:50px">
							<div style="margin-left:50px">
								{{if basicConclusions.compareInfo.timeEfficiencyAVG=='劣于'}}
										<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>
										<font style="font-weight:bold">{{basicConclusions.data[0].fileName}}</font> 报文在 <font style="font-weight:bold">平均链路效率</font>方面劣于竞品，低{{basicConclusions.compareInfo.timeEfficiencyAVGNum}}%，建议深度分析； <br/>
								<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;margin-left: 23px;" class="wenti3_t">
								<tr class="list1">
									<th>报文名</th>
									<th>平均链路效率</th>
								</tr>
								{{each basicConclusions.data as value i}}
										<tr>
											<td> <font style="font-weight:bold">{{value.fileName}}</font></td>
											<td>
												<font {{if basicConclusions.compareInfo.timeEfficiencyAVG=='劣于' && i==0}}color='red'{{/if}}>{{value.timeEfficiencyAVG}}%</font>
											</td>
										</tr>
								  {{/each}}
							</table>
								{{/if}}
					</div>	

				{{if basicConclusions.compareInfo.exchangeTimeCount=='劣于'}}
					<div style="margin-left: 50px;">
						<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>
						<font style="font-weight:bold">{{basicConclusions.data[0].fileName}}</font> 
						报文在
						<font style="font-weight:bold"> 时间消耗</font>
						方面劣于竞品，耗时多于竞品
						{{basicConclusions.compareInfo.exchangeTimeCountNum}}%，可能由于
						<font style="font-weight:bold"> {{basicConclusions.compareInfo.compareRes}}</font> 
						指标导致此问题，建议深度分析； 
						 {{if basicConclusions.compareInfo.avgDnsDelsyTs=='劣于'}}<br/>
						 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 平均DNS时延比竞品长{{basicConclusions.compareInfo.avgDnsDelsyTsNum}}%；
						 {{/if}}
						 {{if basicConclusions.compareInfo.avgTcpTimeDelayed=='劣于'}}<br/>
						 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 平均TCP建链时延比竞品长{{basicConclusions.compareInfo.avgTcpTimeDelayedNum}}%；
						 {{/if}}
						 {{if basicConclusions.compareInfo.avgTimeToFirstByte=='劣于'}}<br/>
						 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 建链后首包时延比竞品长{{basicConclusions.compareInfo.avgTimeToFirstByteNum}}%；
						 {{/if}}
						 {{if basicConclusions.compareInfo.avgExchangeTime=='劣于'}}<br/>
						 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 平均交互时间比竞品长{{basicConclusions.compareInfo.avgExchangeTimeNum}}%；
						 {{/if}}
						 {{if basicConclusions.compareInfo.avgOffTimeDelayed=='劣于'}}<br/>
						 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 断链时延比竞品长{{basicConclusions.compareInfo.avgOffTimeDelayedNum}}%；
						 {{/if}}
						 {{if basicConclusions.compareInfo.avgRttTime=='劣于'}}<br/>
						 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 平均RTT时间比竞品长{{basicConclusions.compareInfo.avgRttTimeNum}}%；
						 {{/if}}
					</div>
				{{else}}
					<div style="margin-left: 50px;"></br>
						<i class="flag" ><img src="../../design/img/home_form3/dui.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>
						<font style="font-weight:bold">{{basicConclusions.data[0].fileName}}</font> 报文在 <font style="font-weight:bold">时间消耗</font>方面优于竞品，耗时少于竞品{{basicConclusions.compareInfo.exchangeTimeCountNum}}%，但以下指标仍劣于竞品 ；
						{{if basicConclusions.compareInfo.avgDnsDelsyTs=='劣于'}}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 平均DNS时延比竞品长{{basicConclusions.compareInfo.avgDnsDelsyTsNum}}%；
						{{/if}}
						{{if basicConclusions.compareInfo.avgTcpTimeDelayed=='劣于'}}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 平均TCP建链时延比竞品长{{basicConclusions.compareInfo.avgTcpTimeDelayedNum}}%；
						{{/if}}
						{{if basicConclusions.compareInfo.avgTimeToFirstByte=='劣于'}}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 建链后首包时延比竞品长{{basicConclusions.compareInfo.avgTimeToFirstByteNum}}%；
						{{/if}}
						{{if basicConclusions.compareInfo.avgExchangeTime=='劣于'}}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 平均交互时间比竞品长{{basicConclusions.compareInfo.avgExchangeTimeNum}}%；
						{{/if}}
						{{if basicConclusions.compareInfo.avgOffTimeDelayed=='劣于'}}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 断链时延比竞品长{{basicConclusions.compareInfo.avgOffTimeDelayedNum}}%；
						{{/if}}
						{{if basicConclusions.compareInfo.avgRttTime=='劣于'}}<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;» 平均RTT时间比竞品长{{basicConclusions.compareInfo.avgRttTimeNum}}%；
						{{/if}}
					</div>
				{{/if }}
			</div>
			<div style="margin-left:123px" class="div_shijiaoxiaohao">
				<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;width:1100px" class="wenti3_t" >
					<tr class="list1">
						<th>报文名</th>
						<th>平均DNS时延(ms)</th>
						<th>平均TCP建链时延(ms)</th>
						<th>平均建链后首包时延(ms)</th>
						<th>平均断链时延(ms)</th>
						<th>平均RTT时间(ms)</th>
						<th>平均交互时间(ms)</th>
						<th>总消耗时间(ms)</th>
					</tr>
			
                  	{{each basicConclusions.data as value i}}
                  		<tr>
                  		{{if  i==0}}
                  			<td><font style="font-weight:bold">{{value.fileName}}</font></td>
                  			<td>
                  				<font {{if basicConclusions.compareInfo.avgDnsDelsyTs=='劣于'}}color='red'{{/if}}>{{value.avgDnsDelsyTs}}</font>
                  			</td><td>
                  				<font {{if basicConclusions.compareInfo.avgTcpTimeDelayed=='劣于'}}color='red'{{/if}}>{{value.avgTcpTimeDelayed}}</font>
                  			</td><td>
                  				<font {{if basicConclusions.compareInfo.avgTimeToFirstByte=='劣于'}}color='red'{{/if}}>{{value.avgTimeToFirstByte}}</font>
                  			</td><td>
                  				<font {{if basicConclusions.compareInfo.avgOffTimeDelayed=='劣于'}}color='red'{{/if}}>{{value.avgOffTimeDelayed}}</font>
                  			</td><td>
                  				<font {{if basicConclusions.compareInfo.avgRttTime=='劣于'}}color='red'{{/if}}>{{value.avgRttTime}}</font>
                  			</td><td>
                  				<font {{if basicConclusions.compareInfo.avgExchangeTime=='劣于'}}color='red'{{/if}}>{{value.avgExchangeTime}}</font>
                  			</td>
							<td>
								<font {{if basicConclusions.compareInfo.exchangeTimeCount=='劣于'}}color='red'{{/if}}>{{value.exchangeTimeCount}}</font>
							</td>
                  		{{else}}
                  			<td> <font style="font-weight:bold">{{value.fileName}}</font></td>
                  			<td>{{value.avgDnsDelsyTs}}</td>
                  			<td>{{value.avgTcpTimeDelayed}}</td>
                  			<td>{{value.avgTimeToFirstByte}}</td>
                  			<td>{{value.avgOffTimeDelayed}}</td>
                  			<td>{{value.avgRttTime}}</td>
                  			<td>{{value.avgExchangeTime}}</td>
							<td>{{value.exchangeTimeCount}}</td>
                  		{{/if}}
                  		</tr>
                  	{{/each}}
				</table>
			</div>

			<div class="wenti5" style="margin-left:100px">
									{{if basicConclusions.compareInfo.exchangeFlowCount=='劣于'}}</br>
									<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>
									<font style="font-weight:bold">{{basicConclusions.data[0].fileName}}</font>报文在<font style="font-weight:bold">流量消耗</font>方面劣于竞品，流量消耗多于竞品{{basicConclusions.compareInfo.exchangeFlowCountNum}}%，建议深度分析；<br/>
									<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;margin-left: 23px;" class="wenti3_t">
											<tr class="list1">
												<th>报文名</th>
												<th>交互流量(byte)</th>
											</tr>
											{{each basicConclusions.data as value i}}
												<tr>
													<td><font style="font-weight:bold">{{value.fileName}}</font></td>
													<td>
														{{if i==0}}
															<font {{if basicConclusions.compareInfo.exchangeFlowCount=='劣于'}}color='red'{{/if}}>{{value.exchangeFlowCount}}</font>
														{{else}}
															{{value.exchangeFlowCount}}
														{{/if}}
													</td>
												</tr>
											{{/each}}
									</table>
									{{/if}}
			</div>

			<div style="margin-left:100px" class="wenti5">

										{{if basicConclusions.compareInfo.pcakageCompareMsg != null}}</br>
											<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>
											<font style="font-weight:bold">{{basicConclusions.data[0].fileName}}</font> 报文在<font style="font-weight:bold">报文结构</font>方面劣于竞品，{{basicConclusions.compareInfo.pcakageCompareMsg}}，建议深度分析；
											<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;margin-left: 23px;" class="wenti3_t">
												<tr class="list1">
													<th>报文名</th>
													<th>小包</th>
													<th>中包</th>
													<th>大包</th>
													<th>包数</th>
												</tr>
											{{each basicConclusions.data as value i}}
													<tr>
														<td><font style="font-weight:bold">{{value.fileName}}</font></td>
														{{if  i==0}}
															<td><font {{if basicConclusions.compareInfo.smallPacketPercent=='劣于'}}color='red'{{/if}}>{{value.smallPacketPercent}}</font></td>	
															<td><font {{if basicConclusions.compareInfo.mediumPacketPercent=='劣于'}}color='red'{{/if}}>{{value.mediumPacketPercent}}</font></td>
															<td><font {{if basicConclusions.compareInfo.largePacketPercent=='劣于'}}color='red'{{/if}}>{{value.largePacketPercent}}</font></td>
															<td><font {{if basicConclusions.compareInfo.largePacketPercent=='劣于'}}color='red'{{/if}}>{{value.packetCount}}</font></td>
														{{else}}
															<td>{{value.smallPacketPercent}}</td>	
															<td>{{value.mediumPacketPercent}}</td>
															<td>{{value.largePacketPercent}}</td>
															<td>{{value.packetCount}}</td>
														{{/if}}
													</tr>
											{{/each}}	
											</table>
										{{/if}}
			</div>

			<div style="margin-left:100px" class="wenti5">
								{{if basicConclusions.compareInfo.tcpCountCompareMsg}}<br/>
									{{if basicConclusions.compareInfo.tcpCountRes=='劣于'}}
										<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>	
										<font style="font-weight:bold">{{basicConclusions.compareInfo.fileName}}</font> 报文在<font style="font-weight:bold">链路数</font>方面劣于竞品，链路数共{{basicConclusions.data[0].tcpCount}}条，多于竞品{{basicConclusions.compareInfo.tcpCountPercent}}%
										{{if basicConclusions.compareInfo.ipCountRes=='劣于'}}，IP数多于竞品 {{/if}}，建议深度分析；
									{{else}}
										<i class="flag" ><img src="../../design/img/home_form3/dui.jpg" style="width: 15px;height: 15px;border-radius: 10px;"/></i>	
										<font style="font-weight:bold">{{basicConclusions.compareInfo.fileName}}</font> 报文在<font style="font-weight:bold">链路数</font>方面优于竞品，链路数共{{basicConclusions.data[0].tcpCount}}条，少于竞品{{basicConclusions.compareInfo.tcpCountPercent}}%
										{{if basicConclusions.compareInfo.ipCountRes=='劣于'}}，但IP数仍多于竞品
										{{/if}}；
									{{/if}}
									<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;margin-left: 23px;" class="wenti3_t">
								        <tr class="list1">
								        	<th>报文名</th>
								        	<th>IP数(个)</th>
								        	<th>链路数(个)</th>
								        </tr>
								        {{each basicConclusions.data as value i}}
								        	<tr>
								        		<td> <font style="font-weight:bold">{{value.fileName}}</font></td>
								        		{{if i==0}}
								        				<td><font {{if basicConclusions.compareInfo.ipCountRes=='劣于'}}color='red'{{/if}}>{{value.ipCount}}</font></td>
								        				<td><font {{if basicConclusions.compareInfo.tcpCountRes=='劣于'}}color='red'{{/if}}>{{value.tcpCount}}</font></td>
								        		{{else}}
								        				<td>{{value.ipCount}}</td>
								        				<td>{{value.tcpCount}}</td>
								        		{{/if}}
								        	</tr>
								        {{/each}}
									</table>
								{{/if}}
				</div>

		{{else}}
			<span style="margin-left: 50px;font-size:14px"><font style="font-weight:bold">{{basicConclusions.data[0].fileName}}</font> 
				报文在  链路效率、时间消耗、流量消耗、报文结构、链路数  方面优于竞品。</span>	
		{{/if}}
{{/if}}
			 </div>

							
		</div>
			</div>
		</div>
	</div>
  </script>
  

 <!-- log间性能参数对比 
  <!--<script id="ratio_log" type="text/html">
  
	<div class="bw">  
		<div class="bw_body">
			<div class="wenti5">
				<span>视频业务对比</span>
				<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;" class="wenti3_t">
					<tr class="list1"><th>业务名</th><th>TtFS(ms)</th><th>TtFR(ms)</th></tr>
					<tr><td style="text-align: center;">{{list.file1}}</td><td>{{list.avgT1_1}}</td><td>{{list.avgT2_1}}</td></tr>
					<tr><td style="text-align: center;">{{list.file2}}</td><td>{{list.avgT1_2}}</td><td>{{list.avgT2_2}}</td></tr>
				</table>	
				<span class="list3">&nbsp;&nbsp;&nbsp;TtFS 相差百分比: {{list.avgT1_s}}</span>
				<span class="list4">&nbsp;&nbsp;&nbsp;TtFR 相差百分比: {{list.avgT2_s}}</span>
				<span class="list4">&nbsp;&nbsp;&nbsp;TtFS:建立连接后，客户端发送第一个请求的时间，时间越短越高效；</span>
				<span class="list4">&nbsp;&nbsp;&nbsp;TtFR:客户端发送请求后，服务器响应时间，时间越短越高效。</span>
				<hr class="wentihr"/>
				{{each list.baowen as value i}}
					<h3 class="bw_title">{{value.fileName}}</h3>
					<hr class="bw_xian" style="border-color: whitesmoke;"></hr>	   	
  					<div class="bw_body">
						<div class="wenti4">
						<i class="flag" ><img src="../../design/img/home_form3/cuo.jpg" style="width: 20px;height: 20px;border-radius: 10px;"/></i>服务器不支持TCP相关参数配置：</br><span style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="wentiIp" style="display: inline-block;">{{value.downloadRate}}</div></span>
						<hr class="wentihr"/>	
					</div>				
  					</div>
				{{/each}}
				<span style="color: brown;font-size: 12px;margin: 4px 0 0 34px">服务器不支持TCP WINDOW SCALE OPTION参数，会影响下载速率。</span>
			</div>
		</div>
	</div>
  </script>-->  
  
  
<!--   基本信息比较模板 -->
 <!--   <script id="template_baseInfoCompare" type="text/html">
	<div class="bw">  
		<div class="bw_body">
			<div class="wenti5">
				<span>基本信息比较</span>
				<h3>对比结果</h3>
					&nbsp;&nbsp;&nbsp;1) 交互时间<br/>
						&nbsp;&nbsp;&nbsp;{{if compareResult.commuTime=='N/A'}}
									N/A
						{{/if}}
						{{if compareResult.commuTime!='N/A'}}
									基础数据  {{compareResult.commuTime}} 竞品数据
						{{/if}}<br/>
					&nbsp;&nbsp;&nbsp;2) 交互流量<br/>
						&nbsp;&nbsp;&nbsp;{{if compareResult.commuFlow=='N/A'}}
									N/A
						{{/if}}
						{{if compareResult.commuFlow!='N/A'}}
									基础数据  {{compareResult.commuFlow}} 竞品数据
						{{/if}}<br/>
					&nbsp;&nbsp;&nbsp;3) ip数<br/>
						&nbsp;&nbsp;&nbsp;{{if compareResult.ipNum=='N/A'}}
									N/A
						{{/if}}
						{{if compareResult.ipNum!='N/A'}}
									基础数据  {{compareResult.ipNum}} 竞品数据
						{{/if}}<br/>
					&nbsp;&nbsp;&nbsp;4) 链路个数<br/>
						&nbsp;&nbsp;&nbsp;{{if compareResult.linkNum=='N/A'}}
									N/A
						{{/if}}
						{{if compareResult.linkNum!='N/A'}}
									基础数据  {{compareResult.linkNum}} 竞品数据
						{{/if}}<br/>
					&nbsp;&nbsp;&nbsp;5) 链路效率<br/>
						&nbsp;&nbsp;&nbsp;{{if compareResult.timeEfficiencyAVG=='N/A'}}
									N/A
						{{/if}}
						{{if compareResult.timeEfficiencyAVG!='N/A'}}
									基础数据  {{compareResult.timeEfficiencyAVG}} 竞品数据
						{{/if}}<br/>
				<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;" class="wenti3_t">
					<tr class="list1">
								<th>名称</th>
								<th>交互时间(ms)</th>
								<th>交互流量(byte)</th>
								<th>ip数(个)</th>
								<th>链路个数</th>
								<th>链路效率(%)</th>
								<th>是否频繁拆建链</th>
					</tr>
						<tr>
								<td style="text-align: center;">{{base.fileName}}(基础数据)</td>
								<td>{{base.commuTime}}</td>
								<td>{{base.commuFlow}}</td>
								<td>{{base.ipNum}}</td>
								<td>{{base.linkNum}}</td>
								<td>{{base.timeEfficiencyAVG}}</td>
								<td>{{base.frequRebuild}}</td>
						</tr>
						<tr>
								<td style="text-align: center;">{{jingpin.fileName}}(竞品数据)</td>
								<td>{{jingpin.commuTime}}</td>
								<td>{{jingpin.commuFlow}}</td>
								<td>{{jingpin.ipNum}}</td>
								<td>{{jingpin.linkNum}}</td>
								<td>{{jingpin.timeEfficiencyAVG}}</td>
								<td>{{jingpin.frequRebuild}}</td>
						</tr>
				</table>	
			</div>
		</div>
	</div>
  </script>-->
  
  
  
  <!--报文信息 -->
  <script id="information_baowen" type="text/html">

{{each lists as value i}}
  <div class="bw">  	 
	   <h3 class="bw_title" style="font-size:18px;">详细信息显示</h3>
	   <h3 style="font-size:17px;">{{value.fileName}}</h3>
	  <hr class="bw_xian" style="border-color: whitesmoke;"></hr>	   	
  	<div class="bw_body">
			<div class="base">
				<span class="title">基本信息</span>
		  		<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;border:1px solid #CCC;"  class="base_information">
					<tr class="name">
						<td style="width:30px;">持续时间(ms)</td>
						<td>数据传输时间(ms)</td>
						<td>传输总包数(个)</td>
						<td>传输量最大IP及端口</td>
						<td>传输总字节(byte)</td>
						<td>总链路数(个)</td>
						<td>链路最多IP</td>
						<td>链路最多IP(链路数)</td>
					</tr>
					<tr class="t1">
						<td style="min-width:100px">{{value.allKeepTimes}}</td>
						<td>{{value.allFileDataTimes}}</td>
						<td>{{value.packetCount}}</td>
						<td class="td4" title={{value.maxFlowIP}}{{value.maxFlowPort}}>{{value.maxFlowIP}}({{value.maxFlowPort}})</td>					
						<td>{{value.exchangeFlowCount}}</td>
						<td>{{value.tcpCount}}</td>
						<td>{{value.maxTCPip}}</td>
						<td>{{value.ipTCPCounts}}</td>
					</tr>
				</table>
			</div>			
				{{each value.rebuildInfo as value i}}				
			 	<div class="error">
					<span class="title"><i class="flag"><img src="../../design/img/home_form3/cuo.jpg"/></i>频繁拆建链</span>
					<ul class="lists">
						{{if value.serviceIp}}
						<li class="list"><i>IP:</i><em>{{value.serviceIp}}</em></li>
						{{/if}}
						{{if value.host}}
						<li class="list"><i>域名解析地址:</i><em>{{value.host}}</em></li>
						{{/if}}
						{{if value.repeatCount}}
						<li class="list"><i>拆建链的数目:</i><em>{{value.repeatCount}}个</em></li>
						{{/if}}
						{{if value.minLastTimeDelayed}}
						<li class="list"><i>最短键连间隔:</i><em>{{value.minLastTimeDelayed}}ms</em></li>
						{{/if}}
						{{if value.avgLastTimeDelayed}}
						<li class="list"><i>平均建立时间:</i><em>{{value.avgLastTimeDelayed}}ms</em></li>
						{{/if}}
					</ul>
				</div>
				{{/each}}
			<div class="general">
				<span class="title">通用信息</span>
		  		<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;border:1px solid #CCC;"  class="general_information">
					<tr class="name">
							<td>Server IP</td>
						{{if whoShow.a10 }}
							<td>DNS时延(ms)</td>
						{{/if}}	
						{{if whoShow.a4 }}
							<td>链路数(个)</td>
						{{/if}}	
						{{if whoShow.a3 }}
							<td>包数(个)</td>
						{{/if}}
						{{if whoShow.a2 }}
							<td>交互流量(byte)</td>
						{{/if}}
						{{if whoShow.a1 }}
							<td>交互时间(ms)</td>
						{{/if}}
						{{if whoShow.a18 }}
							<td>平均链路时间有效率(%)</td>
						{{/if}}	
					</tr>			
					<tr class="t1">							
							<td>{{each value.serviceIP as value i}}{{if i!=0}}、{{/if}}{{value.serviceIP}}{{/each}}</td>			
						{{if whoShow.a10 }}			
							<td>{{value.dnsTimeDelayedcount}}</td>
						{{/if}}	
						{{if whoShow.a4 }}
							<td>{{value.tcpCount}}</td>
						{{/if}}	
						{{if whoShow.a3 }}
							<td>{{value.packetCount}}</td>
						{{/if}}
						{{if whoShow.a2 }}
							<td>{{value.exchangeFlowCount}}</td>
						{{/if}}
						{{if whoShow.a1 }}
							<td>{{value.exchangeTimeCount}}</td>
						{{/if}}
						{{if whoShow.a18 }}
							<td>{{value.timeEfficiencyAVG}}</td>
						{{/if}}	
					</tr>		
				</table>
            </div>
			<div class="detailed">
				<span class="detailed_click" style="cursor: pointer;">&nbsp;&nbsp;&nbsp;-&nbsp;<em>展开详细信息</em>&nbsp;<i>+</i></span>
		  		<table cellpadding="0" cellspacing="0" border="0" style="border-collapse:collapse;border:1px solid #CCC;"  class="detailed_information">
					<tr class="name" valign="top">
								<td >server IP</td>	

							{{if whoShow.a10}}
								<td class="a10">DNS时延(ms)</td>
							{{/if}}	

								<td>端口号</td>
		
							{{if whoShow.a12}}
								<td class="a12">TCP建链时延(ms)</td>
							{{/if}}	

							{{if whoShow.a6}}
								<td class="a6">请求类别</td>
							{{/if}}	

							{{if whoShow.a8}}
								<td class="a8">建链后首包时延(ms)</td>
							{{/if}}	

							{{if whoShow.a3}}
								<td class="a3">包数(个)</td>
							{{/if}}	

							{{if whoShow.a2}}
								<td class="a2" style="width:30px;">交互流量(byte)</td>
							{{/if}}	

							{{if whoShow.a1}}
								<td class="a1">交互时间(ms)</td>
							{{/if}}	

							{{if whoShow.a17}}
								<td class="a17">链路时间有效率(%)</td>
							{{/if}}	

							{{if whoShow.a13}}
								<td class="a13">重传识别(次)</td>
							{{/if}}	

							{{if whoShow.a11}}
								<td class="a11">平均RTT时间(ms)</td>
							{{/if}}		

							{{if whoShow.a9}}
								<td class="a9">断链时延(ms)</td>
							{{/if}}	

							{{if whoShow.a14}}
								<td class="a14">与上条链路串并行关系</td>
							{{/if}}	
						
							{{if whoShow.a15}}
								<td class="a15">与上条链路间隔时延(ms)</td>
							{{/if}}	

							{{if whoShow.a16}}
								<td class="a16">串行链路频繁拆建链判断(0~1s)</td>
							{{/if}}	

							{{if whoShow.a5}}
								<td class="a5" onload="rowspan">服务器IP归属</td>
							{{/if}}	
					</tr>
               		
{{each value.serviceIP as value i}}
	{{each value.serviceIPInfo as value i}}
					<tr class="t1">
								<td  class="rowspan">{{if i==0}}{{value.serverIP}}{{/if}}</td>

							{{if whoShow.a10}}
								<td class="a10" class="rowspan">{{value.dnsDelsyTs}}</td>
							{{/if}}	
						
								<td style="min-width:60px;">{{value.ports}}</td>

							{{if whoShow.a12}}
								<td class="a12">{{value.tcpTimeDelayed}}</td>
							{{/if}}	

							{{if whoShow.a6}}
								<td class="a6" title="{{value.requestCategory}}">{{value.requestCategoryBreviary}}</td>
							{{/if}}	

							{{if whoShow.a8}}
								<td class="a8">{{value.timeToFirstByte}}</td>
							{{/if}}	

							{{if whoShow.a3}}
								<td class="a3">{{value.packetCount}}</td>
							{{/if}}	

							{{if whoShow.a2}}
								<td class="a2" style="min-width:70px;">{{value.exchangeFlow}}</td>
							{{/if}}	

							{{if whoShow.a1}}
								<td class="a1">{{value.exchangeTime}}</td>
							{{/if}}	

							{{if whoShow.a17}}
								<td class="a17">{{value.timeEfficiency}}</td>
							{{/if}}	

							{{if whoShow.a13}}
								<td class="a13">{{value.againRequestCount}}</td>
							{{/if}}	

							{{if whoShow.a11}}
								<td class="a11">{{value.rttTime}}</td>
							{{/if}}		

							{{if whoShow.a9}}
								<td class="a9">{{value.offTimeDelayed}}</td>
							{{/if}}	

							{{if whoShow.a14}}
								<td class="a14">{{value.lastRelationship}}</td>
							{{/if}}	

							{{if whoShow.a15}}
								<td class="a15">{{value.lastTimeDelayed}}</td>
							{{/if}}	

							{{if whoShow.a16}}
								<td class="a16">{{value.oftenOffVerdict}}</td>
							{{/if}}	

							{{if whoShow.a5}}
								<td class="a5" rowspan
									name="{{if i==0}}getip{{/if}}" 
									id="{{if i==0}}{{value.ipAddrId}}{{/if}}" 
									style="min-width:100px;max-width:300px"></td>
							{{/if}}	
					</tr>					
			{{/each}}				
		{{/each}}	
						{{if whoShow.a1}}
								<tr>
									<td colspan="18" style="color:black;" >
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<i class="wuxiaoclick" style="cursor: pointer;font-style:normal;">无效IP<em>+</em></i>
											</br>
											<span   style="color:black;display:block;max-width:1480px;font-size:12px;word-break:keep-all;overflow:hidden;word-wrap:break-word;" 
													class="wuxiaoIP" 
													title={{each value.others as value i}}&nbsp;&nbsp;{{value}}{{/each}}>
													{{if value.others==""}}(无){{/if}}{{each value.others as value i}}&nbsp;&nbsp;{{value}}{{/each}}
											</span>
									</td>
								</tr>	
						{{/if}}	
				</table>
			</div>	  		
  		</div>
  </div> 
{{/each}}
  </script>		
	<!--这是悬浮 -->
		<div id="comm" class="comm" style="display:none">
			<span>通用</span>
			<div class='table1' id='table1'> 
				<table id="tb1" class="tb1" cellspacing="0"></table>
				<p  class='tb1De' id='tb1De'>&nbsp;&nbsp;&nbsp;
					<a href='JavaScript:normData()' id='tb1DeTen' class='tb1DeTen'></a>
				</p>
				<!-- 位置放到了table中了 -->
		     </div>
		     <div class='table1Info' id='table1Info'> 
	             <div>
					<table id="tb1Detail" class="tb1Detail" style='display: none;' cellspacing="0"></table>
					<div id="tag1" class="tag1" style='display:none;'></div>
				</div>
				 <div id='contr1' class='contr1'  style='display: none;'>
					 <img src="<%=request.getContextPath()%>/design/img/index/assembly.png" width="20" height="20"  onclick="tag1()"/>
					 <img src="<%=request.getContextPath()%>/design/img/index/copy.png" width="20" height="20"  id="img1"/>
				</div>
			</div>			
		</div>
		
		<div id="indiv" class="indiv" style="display:none">
		   	<span>业务个性</span>
			<div class="table1">	
				<table id="tb2" class="tb2" cellspacing="0"></table>
				<div id="tag2" class="tag2"></div>
			</div>
		</div> 
	</div>
	<div class='charts' id='charts'></div>
	<div class="model" id="div_model" style="position: relative;">
		<span>模板选取</span>
		<div class="temp">
			<ul class="box">
				<li>
					<img alt="" src="<%=request.getContextPath()%>/design/img/index/Template.jpg"/>
					<input type="checkbox"/>总结类模板
				</li>
				<li>
					<img alt="" src="<%=request.getContextPath()%>/design/img/index/Template.jpg"/><br/>
					<input type="checkbox"/>研究类模板
				</li><li>
					<img alt="" src="<%=request.getContextPath()%>/design/img/index/Template.jpg"/><br/>
					<input type="checkbox"/>测评类模板
				</li><li>
					<img alt="" src="<%=request.getContextPath()%>/design/img/index/Template.jpg"/><br/>
					<input type="checkbox"/>汇报类模板
				</li>
			</ul>
		</div>
		<div class="report">
			<span>报告格式</span><br/>
			<div>PDF</div><input type="radio" name="FileType"  value="1" onclick="show()" style="cursor: pointer;"/>
			<div>EXCEL</div><input type="radio" name="FileType"  value="2" onclick="hidden1()" style="cursor: pointer;"/>
		</div>
		<div id="showDiv" style="display: none">
			<br/><span>水印编辑</span><br/>
			<input type="text" value="CMLAB-测试所-无线室" id="watermark" style="width:230px;height: 50px;"/>
		</div>
		<input type="button" onclick="ExportExcel()" value="生成报告" class="build" style="cursor: pointer;" />
		
	<!-- 	<div id="gaishu" class="messageSummarize" style="-webkit-border-radius: 0px;-moz-border-radius: 0px;border-radius: 0px;border: 0px solid green;position: absolute;left: 0px;top: 0px;z-index: -1;">
			我是概述
		</div>
		<div id="jielun" style="width: 1250px;position: absolute;left: 0px;top: 0px;">
			我是结论
		</div> -->
	</div>
