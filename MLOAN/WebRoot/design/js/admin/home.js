/*****************  init  *************************/		
$(document).ready(function(){

	var index = localStorage.getItem("index");
	if(index=="1")
		MainTop(eval(1));
	else if(index=="3")
		MainTop(eval(3));
	else
		MainTop(eval(2));
});
/*****************  file input  *************************/
function addFile() {
	var upFile = '<input type="file" name="file1"><br>';
	document.getElementById("files").insertAdjacentHTML("beforeEnd", upFile);}
/*function deleteFile() {
	var file = document.getElementById("files").lastChild;
	if (file == null)
		return;
	document.getElementById("files").removeChild(file);
	file = document.getElementById("files").lastChild; // 移除换行符<br>所以要移两次
	document.getElementById("files").removeChild(file); // 如果在表格里面不加<br>就自动换行的，可以去掉，自己把握
}*/


/*****************  Event  *************************/
	var scrollTop_home_form3=0;
	var scrollLeft_home_form3=0;
	
	var scrollTop_home_form1=0;
	var scrollLeft_home_form1=0;
	
	var scrollTop_home_form2=0;
	var scrollLeft_home_form2=0;
	
	var scrollBody=document.body;
	
	
	window.onload=function(){//检测是什么浏览器
			document.body.scrollLeft=1;
			if(document.body.scrollLeft==0){//不支持“document.body.scrollLeft”
					scrollBody=document.documentElement;
			}else{//支持"document.body.scrollLeft"
					scrollBody=document.body;
					document.body.scrollLeft=0;
			}
	}
	
	function MainTop(index){
			myFunction();
			
			//当前显示的是home_form3并且点击的不是是home_form3(即index!=3)时,才记录scrollTop_home_form3(目的是当恢复显示home_form3时同时恢复scrollTop)
			if(index!=3&&document.getElementById('home_form3').style.display==''){
					scrollTop_home_form3=scrollBody.scrollTop;
					scrollLeft_home_form3=scrollBody.scrollLeft;
			}
			
			if(index!=1&&document.getElementById('home_form1').style.display==''){
				scrollTop_home_form1=scrollBody.scrollTop;
				scrollLeft_home_form1=scrollBody.scrollLeft;
			}
			
			if(index!=2&&document.getElementById('home_form2').style.display==''){
				scrollTop_home_form2=scrollBody.scrollTop;
				scrollLeft_home_form2=scrollBody.scrollLeft;
			}
			
			
			switch (index) {
				case 2:
					localStorage.setItem("index","2");
					document.getElementById('label_Left'+(index)).style.background='#44acdb';
					document.getElementById('label_Left'+(index-1)).style.background='#0089c7';
					document.getElementById('label_Left'+(index+1)).style.background='#0089c7';
					document.getElementById('home_maintop_div'+(index-1)).style.display='none';
					document.getElementById('home_maintop_div'+(index)).style.display='';
					document.getElementById('home_maintop_div'+(index+1)).style.display='none';
					document.getElementById('home_form'+(index-1)).style.display='none';
					document.getElementById('home_form'+(index)).style.display='';
					document.getElementById('home_form'+(index+1)).style.display='none';
					
					scrollBody.scrollTop=scrollTop_home_form2;
					scrollBody.scrollLeft=scrollLeft_home_form2;
					break;
				case 3:
					localStorage.setItem("index","3");
					document.getElementById('label_Left'+(index)).style.background='#44acdb';
					document.getElementById('label_Left'+(index-2)).style.background='#0089c7';
					document.getElementById('label_Left'+(index-1)).style.background='#0089c7';
					document.getElementById('home_maintop_div'+(index-2)).style.display='none';
					document.getElementById('home_maintop_div'+(index-1)).style.display='none';
					document.getElementById('home_maintop_div'+(index)).style.display='';
					document.getElementById('home_form'+(index-2)).style.display='none';
					document.getElementById('home_form'+(index-1)).style.display='none';
					document.getElementById('home_form'+(index)).style.display='';
					
					scrollBody.scrollTop=scrollTop_home_form3;
					scrollBody.scrollLeft=scrollLeft_home_form3;
					normIpRefresh();
					break;
				default:
					localStorage.setItem("index","1");
					document.getElementById('label_Left'+(index)).style.background='#44acdb';
					document.getElementById('label_Left'+(index+1)).style.background='#0089c7';
					document.getElementById('label_Left'+(index+2)).style.background='#0089c7';
					document.getElementById('home_maintop_div'+(index)).style.display='';
					document.getElementById('home_maintop_div'+(index+1)).style.display='none';
					document.getElementById('home_maintop_div'+(index+2)).style.display='none';
					document.getElementById('home_form'+(index)).style.display='';
					document.getElementById('home_form'+(index+1)).style.display='none';
					document.getElementById('home_form'+(index+2)).style.display='none';
					
					scrollBody.scrollTop=scrollTop_home_form1;
					scrollBody.scrollLeft=scrollLeft_home_form1;
					break;
			}
	}
	
	
	//home_form1中的2个tab
	var scrollTop_form1_1=0;
	var scrollLeft_form1_1=0;

	var scrollTop_form1_2=0;
	var scrollLeft_form1_2=0;

	//home_form2中的2个tab
	var scrollTop_form2_1=0;
	var scrollLeft_form2_1=0;

	var scrollTop_form2_2=0;
	var scrollLeft_form2_2=0;

	//home_form3中的2个tab
	var scrollTop_form3_1=0;
	var scrollLeft_form3_1=0;

	var scrollTop_form3_2=0;
	var scrollLeft_form3_2=0;

	var scrollTop_form3_3=0;
	var scrollLeft_form3_3=0;

	
	function resetScrollLeftAndTop(){//点击"分析"后重置tab"数据明细"、"图表展示"、"生成报告"的scrollLeft和scrollTop
		scrollTop_home_form3=0;
		scrollLeft_home_form3=0;
		
		scrollTop_form3_1=0;
		scrollLeft_form3_1=0;

		scrollTop_form3_2=0;
		scrollLeft_form3_2=0;

		scrollTop_form3_3=0;
		scrollLeft_form3_3=0;
	}
		

	$('#home_maintop_div1 ul li').click(function(){
		//如果当前自己处于hit状态，并且要切换显示的不是自己，则此时记录下自己当前的scrollTop和scrollLeft，为的是后面切换hit自己时同时恢复被切换走时候的scroll坐标
											if($(this).index()==0){//点击的是第一个
													if(document.getElementById("ul_li_testControl").className=="hit"){//恰好此时显示的是第二个
														scrollTop_form1_2=scrollBody.scrollTop;
														scrollLeft_form1_2=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#ul_li_testControl").removeClass('hit');
														$('#home_form1>div:eq(0)').show();
														$('#home_form1>div:eq(1)').hide();
														
														scrollBody.scrollTop=scrollTop_form1_1;
														scrollBody.scrollLeft=scrollLeft_form1_1;
													}
											}else if($(this).index()==1){//点击的第二个
													if(document.getElementById("ul_li_testConf").className=="hit"){//此时恰好显示的是第一个
														scrollTop_form1_1=scrollBody.scrollTop;
														scrollLeft_form1_1=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#ul_li_testConf").removeClass('hit');
														$('#home_form1>div:eq(1)').show();
														$('#home_form1>div:eq(0)').hide();
														
														scrollBody.scrollTop=scrollTop_form1_2;
														scrollBody.scrollLeft=scrollLeft_form1_2;
													}
											}
										});
		
	$('#home_maintop_div2 ul li').click(function(){
											if($(this).index()==0){//点击的是第一个
													if(document.getElementById("form2_li2").className=="hit"){//此时恰好显示的第二个
														scrollTop_form2_2=scrollBody.scrollTop;
														scrollLeft_form2_2=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#form2_li2").removeClass('hit');
														$('#home_form2>div:eq(0)').show();
														$('#home_form2>div:eq(1)').hide();
														
														scrollBody.scrollTop=scrollTop_form2_1;
														scrollBody.scrollLeft=scrollLeft_form2_1;
													}
											}else if($(this).index()==1){//点击是第二个
													if(document.getElementById("form2_li1").className=="hit"){//此时恰好显示的是第一个
														scrollTop_form2_1=scrollBody.scrollTop;
														scrollLeft_form2_1=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#form2_li1").removeClass('hit');
														$('#home_form2>div:eq(1)').show();
														$('#home_form2>div:eq(0)').hide();
														
														scrollBody.scrollTop=scrollTop_form2_2;
														scrollBody.scrollLeft=scrollLeft_form2_2;
													}
											}
										});
		
	$('#home_maintop_div3 ul li').click(function(){
											if($(this).index()==0){//点击的是第一个
													if(document.getElementById("ul_li_charts").className=="hit"){//此时恰好显示的是第二个
														scrollTop_form3_2=scrollBody.scrollTop;
														scrollLeft_form3_2=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#ul_li_charts").removeClass('hit');
														$('#home_form3>div:eq(0)').show();
														$('#home_form3>div:eq(1)').hide();
														
														//将scrollTop和left恢复成离开tab"数据明细"离开时的样子
														scrollBody.scrollTop=scrollTop_form3_1;
														scrollBody.scrollLeft=scrollLeft_form3_1;
													}else if(document.getElementById("ul_li_initReport").className=="hit"){//此时恰好显示的是第三个
														scrollTop_form3_3=scrollBody.scrollTop;
														scrollLeft_form3_3=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#ul_li_initReport").removeClass('hit');
														$('#home_form3>div:eq(0)').show();
														$('#home_form3>div:eq(2)').hide();
														
														//将scrollTop和left恢复成离开tab"数据明细"离开时的样子
														scrollBody.scrollTop=scrollTop_form3_1;
														scrollBody.scrollLeft=scrollLeft_form3_1;
													}
											}else if($(this).index()==1){//点击的是第二个
													if(document.getElementById("ul_li_summarize").className=="hit"){//此时恰好显示的是第一个
														scrollTop_form3_1=scrollBody.scrollTop;
														scrollLeft_form3_1=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#ul_li_summarize").removeClass('hit');
														$('#home_form3>div:eq(1)').show();
														$('#home_form3>div:eq(0)').hide();
														
														//将scrollTop和left恢复成离开tab"图表展示"离开时的样子
														scrollBody.scrollTop=scrollTop_form3_2;
														scrollBody.scrollLeft=scrollLeft_form3_2;
													}else if(document.getElementById("ul_li_initReport").className=="hit"){//此时恰好显示的是第三个
														scrollTop_form3_3=scrollBody.scrollTop;
														scrollLeft_form3_3=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#ul_li_initReport").removeClass('hit');
														$('#home_form3>div:eq(1)').show();
														$('#home_form3>div:eq(2)').hide();
														
														//将scrollTop和left恢复成离开tab"图表展示"离开时的样子
														scrollBody.scrollTop=scrollTop_form3_2;
														scrollBody.scrollLeft=scrollLeft_form3_2;
													}
											}else if($(this).index()==2){//点击的是第三个
													if(document.getElementById("ul_li_summarize").className=="hit"){//此时恰好显示的是第一个
														scrollTop_form3_1=scrollBody.scrollTop;
														scrollLeft_form3_1=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#ul_li_summarize").removeClass('hit');
														$('#home_form3>div:eq(2)').show();
														$('#home_form3>div:eq(0)').hide();
														
														//将scrollTop和left恢复成离开tab"生成报告"离开时的样子
														scrollBody.scrollTop=scrollTop_form3_3;
														scrollBody.scrollLeft=scrollLeft_form3_3;
													}else if(document.getElementById("ul_li_charts").className=="hit"){//此时恰好显示的是第二个
														scrollTop_form3_2=scrollBody.scrollTop;
														scrollLeft_form3_2=scrollBody.scrollLeft;
														
														$(this).addClass('hit');
														$("#ul_li_charts").removeClass('hit');
														$('#home_form3>div:eq(2)').show();
														$('#home_form3>div:eq(1)').hide();
														
														//将scrollTop和left恢复成离开tab"生成报告"离开时的样子
														scrollBody.scrollTop=scrollTop_form3_3;
														scrollBody.scrollLeft=scrollLeft_form3_3;
													}
											}
										});
		
	$('#indicator').click(function(){    //  一键分析   跳转至  指标选取
		document.getElementById("form2_li1").className = "";
		document.getElementById("form2_li2").className = "hit";
		$('#home_form2>div:eq(1)').show().siblings().hide();})
		
		
		
	$("#home_form2").show(function(){
		document.getElementById('normid').style.display='none';})
	function show(){
		document.getElementById("showDiv").style.display="";
		}
	function hidden1(){
		document.getElementById("showDiv").style.display="none";
		}