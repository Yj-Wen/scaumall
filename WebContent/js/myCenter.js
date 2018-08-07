$(document).ready(function () {
	 var $modify;
	 var addrId;
	 var $delete;
	 var $deleteIndent;
	 var currentPwd;
    $(".Order-top>ul>li").click(function () {
        $(".Order-list .showli").removeClass("showli").hide();
        $(".isNoPanel").hide();
    	$(".page").show();
        $(this).addClass("active").siblings().removeClass("active");
        var select=$(this).attr("data-select");console.log(select);
        $(".page").show();
        if (select!=0) {
        	console.log($("span.deal-state[data-state=" + select + "]").text());
        	$("span.deal-state[data-state=" + select + "]").parents(".panel").addClass("showli").fadeIn().siblings(".panel:not(.showli)").fadeOut();
        	var $test=$(".panel.showli");
            if($test.length==0){
            	$(".isNoPanel").show();
            	$(".page").hide();
            }
        }
        else
        {
            $(".panel").addClass("showli").fadeIn("fast");
            $(".isNoPanel").hide();
        	$(".page").show();
        	var $test=$(".panel.showli");
            if($test.length==0){
            	$(".isNoPanel").show();
            	$(".page").hide();
            }
        }
    });
//    点击订单状态分类显示相应的订单
    
    
    $(".menu-item>li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var select=$(this).attr("data-select");console.log(select);
        $(".show-area>div[data-state="+select+"]") .show().siblings().hide();
    });
//点击左侧菜单显示相应的div
  
    $(".Address-table").on("click",".Address-delect",function () {
    	var deleteId=$(this).parents("td").siblings(".addressName").attr("data-addrId");
    	$delete=$(this)
    	console.log(deleteId);
    	$.ajax({
    		type:"post",
			async:false,
			url:"deleteAddress",
			dataType:"jsonp",
			jsonp:"callback",
			data:{
				addressID:deleteId,
				t:new Date()
			},
			success:function(res){
				if(res.result==""){
				$delete.parents("tr").remove();
				$(".tip").find("h4").text("删除成功")
				$(".tip").fadeIn();
				$(".tip").delay(1500).fadeOut();
				}
				else{
					alert(res.result);
				}
			},
			error:function(e){
				alert("shibia");
			}
    	});
//        $(this).parents("tr").remove();
    });
//删除地址
      
    $(".Address-add").click(function () {
    	  $("#addressDetail").attr("flag","false");
          $("#addressPostcode").attr("flag","false");
          $("#addressPhone").attr("flag","false");
          $("#addressName").attr("flag","false"); 
        $(".Address-addsubmit").show();
        $(".Address-mod").hide();
    });
//添加地址按钮的点击事件
    
    $(".search-button").click(function () {
        $("div.panel").hide();
        $(".isNoPanel").hide();
    	$(".page").show();
        var orderid=$("#search").val();
       $(".panel-body .goods span:contains("+orderid+")").parents(".panel").show();
       if($(".panel-body .goods span:contains("+orderid+")").length==0){
    	    $(".isNoPanel").show();
       		$(".page").hide();
       }
    });
//订单号查询
    
    $(".panel .panel-heading").on("click","i",function () {
    	var indentId=$(this).siblings(".Order-id").attr("data-indentID");
    	$deleteIndent=$(this);
    	$.ajax({
    		type:"post",
			async:false,
			url:"deleteIndent",
			dataType:"jsonp",
			jsonp:"callback",
			data:{
				indentID:indentId,
				t:new Date()
			},
			success:function(res){
				if(res.result=="")
					{
					$deleteIndent.parents(".panel").remove();
					 	$(".tip").find("h4").text("删除成功").css({"fontSize":"23px","lineHeight":"60px"});
						$(".tip").fadeIn();
						$(".tip").delay(1500).fadeOut();
					}
				else{
					$(".tip").find("h4").text(res.result).css({"fontSize":"23px","lineHeight":"60px"});
					$(".tip").fadeIn();
					$(".tip").delay(1500).fadeOut();
				}
			},
			error:function(e){
				alert("失败");
			}
    	});
//        $(this).parents(".panel").remove();
    });
//删除订单
    
    $(".Address-table tbody").on("click",".Address-modify",function () {
        $modify=$(this);
        var name=$(this).parents("td").siblings(".addressName").text();
        var address=$(this).parents("td").siblings(".addressDetail").text();
        var phone=$(this).parents("td").siblings(".addressPhone").text();
        var postcode=$(this).parents("td").siblings(".addressPostcode").text();
        console.log(name+address+phone+postcode);
        $(".Address-addsubmit").hide();
        $(".Address-mod").show();
        // $("#addressDetail").
        $("#addressName").val(name).attr("flag","true");
        $("#addressDetail").val(address).attr("flag","true");
        $("#addressPostcode").val(postcode).attr("flag","true");
        $("#addressPhone").val(phone).attr("flag","true");
        $(".modal-body .group .inputgroup i.glyphicon-ok").show();
        addrId=$(this).parents("td").siblings(".addressName").attr("data-addrId");
        console.log(addrId);
});
//地址修改
    $(".close").click(function(){
    	 $("#addressName").val("");
         $("#addressDetail").val("");
         $("#addressPhone").val("");
         $("#addressPostcode").val("");
         $(".modal-body .group .inputgroup i,.modal-body .group .inputgroup p").hide();
    });
    $(".Address-mod").click(function () {
    	var addressflag=$("#addressDetail").attr("flag");
        var postcodeflag=$("#addressPostcode").attr("flag");
        var phoneflag=$("#addressPhone").attr("flag");
        var nameflag=$("#addressName").attr("flag"); 
        
        var address=$("#addressDetail").val();
        var postcode=$("#addressPostcode").val();
        var phone=$("#addressPhone").val();
        var name=$("#addressName").val();
        console.log(address+"//"+postcode+"//"+phone+"//"+name+"//"+addrId);
        if(addressflag=="true"&&postcodeflag=="true"&&phoneflag=="true"&&nameflag=="true")
        {
        	$.ajax({
        		type:"post",
    			async:false,
    			url:"changeAddress",
    			dataType:"jsonp",
    			jsonp:"callback",
    			data:{
    				addressDetail:address,
    				addressPostcode:postcode,
    				addressPhone:phone,
    				addressName:name,
    				addressID:addrId,
    				t:new Date()
    			},
    			success:function(res){
    				if(res.result==""){
    						$modify.parents("td").siblings(".addressName").text($("#addressName").val());
    				        $modify.parents("td").siblings(".addressDetail").text($("#addressDetail").val());
    				        $modify.parents("td").siblings(".addressPhone").text($("#addressPhone").val());
    				        $modify.parents("td").siblings(".addressPostcode").text($("#addressPostcode").val());
    				        $("#addressName").val("");
    				        $("#addressDetail").val("");
    				        $("#addressPhone").val("");
    				        $("#addressPostcode").val("");
    				        $(".tip").fadeIn();
    						$(".tip").delay(1500).fadeOut();
    						$("#OldPassword").val(password);
    				}
    				else{
    					alert(res.result);
    				}
    			},
    			error:function(e){
    				alert("false");
    			}
        	});
        }
        else{
        	 $(".modal-body .group .inputgroup i.glyphicon-remove,.modal-body .group .inputgroup p").show();
        	 return false;
        }
//        $modify.parents("td").siblings(".addressName").text($("#addressName").val());
//        $modify.parents("td").siblings(".addressDetail").text($("#addressDetail").val());
//        $modify.parents("td").siblings(".addressPhone").text($("#addressPhone").val());
//        $modify.parents("td").siblings(".addressPostcode").text($("#addressPostcode").val());
//        $("#addressName").val("");
//        $("#addressDetail").val("");
//        $("#addressPhone").val("");
//        $("#addressPostcode").val("");
    });
//地址修改
    
    $(".panel").each(function(){
    	var page=$(this).attr("data-page");
    	var pageNumber=Math.ceil((parseFloat(page)+1.0)/5.0);
    	$(this).attr("data-pagenumber",pageNumber);
    	console.log(pageNumber);	
    });
    console.log($(".panel:last").attr("data-pagenumber"));
    var MaxPage=$(".panel:last").attr("data-pagenumber");
    var j=1;
    for(j;j<=MaxPage;j++){
    	var option='<option value=\"'+j+'\">第'+j+'页</option>';
    	$("#PageNumber").append(option);	
    } 
    console.log($("#PageNumber").val());
    function check(){
    $(".panel").each(function(){
    	if($(this).attr("data-pagenumber")==$("#PageNumber").val()){
    		$(this).show();
    	}
    	else{
    		$(this).hide();
    	}
    });
    }
    $("#pre").click(function(){
    	if($("#PageNumber").val()>1){
    	$("#PageNumber").val($("#PageNumber").val()-1);
    	console.log($("#PageNumber").val());
    	check();
    	}
    	else{
    		alert("已经是第一页了");
    	}
    });
    $("#last").click(function(){
    	if($("#PageNumber").val()<MaxPage){
    	$("#PageNumber").val($("#PageNumber").val()+1);
    	console.log($("#PageNumber").val());
    	check();
    	}
    	else{
    		alert("已经是最后一页了");
    	}
    });
/*    var totalPrice=0;
    $(".totalPrice").each(function(){
    	
    	totalPrice=parseFloat(totalPrice+parseFloat($(this).parents(".panel").find("span.number").text()));
    	console.log(totalPrice);
    });*/
    
    $(".Order-list .cell .deal-state").each(function(){
    	var flag=0;
    	var jspState=$(this).attr("jspstate");
    	var dataState=parseInt(jspState)+1;
    	$(this).attr("data-state",dataState);
    	var $target=$(this).parents(".panel-body").siblings(".panel-footer").find(".target:eq(0)");
    	console.log($target);
    	$target.attr("data-state",dataState);
    	if(jspState==0){
    		$target.parents(".panel").find(".Order-state").text("待付款");
    		$target.parent().find("button").text("去付款");
    	}
    	if(jspState==1){
    		$target.parents(".panel").find(".Order-state").text("待发货");	
    		$target.parent().find("button").remove();
    	}
    	if(jspState==2){
    		$target.parents(".panel").find(".Order-state").text("已发货");
    		$target.parent().find("button").text("确认收货");
    	}
    	if(jspState==3){
    		$target.parents(".panel").find(".Order-state").text("待评价");	
    		$target.parents(".panel").find(".state-button button").show();
    		$target.parent().find("button").remove();
    	}
    	if(jspState==4){
    		$target.parents(".panel").find(".Order-state").text("交易成功");	
    		$target.parent().find("button").remove(); 
    	}
    })
    
    $(".state-button button").click(function(){
    	var imgSrc=$(this).parents(".state-button").siblings(".goods").find("img").attr("src");
    	var goodsName=$(this).parents(".state-button").siblings(".goods").find("span").text();
    	var num=$(this).parents(".state-button").siblings(".cell").find("span:eq(0)").text();
    	var Specify=$(this).parents(".state-button").siblings(".cell").find(".testgoodsSpecify").text();
    	var goodID=$(this).siblings("input").val();
    	console.log(goodID);
    	$(".Evaluate-area #goodsID").val(goodID);
    	$(".Evaluate-area .product-img img").attr("src",imgSrc);
    	$(".Evaluate-area .product-intr .product-name").text(goodsName);
    	$(".Evaluate-area .product-intr .product-type span").text(Specify);
    	$(".Evaluate-area .product-intr .product-number span").text(num);
    	$(".Evaluate-area #goodsSpecify").val(Specify);
    	$(".Evaluate").fadeIn("fast");
    	$(".Evaluate-area").slideDown("fast");
    });
    
    $(".Evaluate-area i.layui-icon-close").click(function(){
    	$(".Evaluate-area").slideUp("fast");
    	$(".Evaluate").fadeOut("fast");
    });
    
    $("#customerName").blur(function () {
        var value=$(this).val();
        var re=/^[\u4E00-\u9FA5A-Za-z0-9_]{5,20}$/;
        if(value.length<5 ||re.test(value)==false){
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-remove").show("fast");
            $(this).parent().parent().find("p").fadeIn("fast");
            $(this).attr("flag","false");
        }
        else{
            $(this).parent().parent().find("p").fadeOut("fast");
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-ok").show("fast");
            $(this).attr("flag","true");
        }
    });
    $("#oldcustomerPwd").blur(function(){
    	var old=$("#OldPassword").val();
    	if($(this).val()!=old){
    		$(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-remove").show("fast");
            $(this).parent().parent().find("p").fadeIn("fast");
            $(this).attr("flag","false");
    	}
    	else{
    		$(this).parent().parent().find("p").fadeOut("fast");
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-ok").show("fast");
            $(this).attr("flag","true");
    	}
    });
    $("#customerPwd").blur(function () {
        var value=$(this).val();
        var re=/^[A-Za-z0-9]{6,20}$/;
        if (!re.test(value)){
        	$(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-remove").show("fast");
            $(this).parent().parent().find("p").fadeIn("fast");
            $(this).attr("flag","false");
        }
        else{
        	$(this).parent().parent().find("p").fadeOut("fast");
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-ok").show("fast");
            $(this).attr("flag","true");
        }
    });
    $("#recustomerPwd").blur(function () {
        var password=$("#customerPwd").val()
        if ($(this).val()===password && $(this).val()!=""){
        	$(this).parent().parent().find("p").fadeOut("fast");
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-ok").show("fast");
            $(this).attr("flag","true");
        }
        else
        {
        	$(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-remove").show("fast");
            $(this).parent().parent().find("p").fadeIn("fast");
            $(this).attr("flag","false");
        }
    });
    $("#addressDetail").blur(function () {
        if ($(this).val()!=""){
        	$(this).parent().parent().find("p").fadeOut("fast");
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-ok").show("fast");
            $(this).attr("flag","true");
        }
        else
        {
        	$(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-remove").show("fast");
            $(this).parent().parent().find("p").fadeIn("fast");
            $(this).attr("flag","false");
        }
    });
    
    $("#addressPostcode").blur(function () {
        var value=$(this).val();
        var re=/^[0-9]{6}$/;
        if (!re.test(value)){
        	$(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-remove").show("fast");
            $(this).parent().parent().find("p").fadeIn("fast");
            $(this).attr("flag","false");
        }
        else{
        	$(this).parent().parent().find("p").fadeOut("fast");
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-ok").show("fast");
            $(this).attr("flag","true");
        }
    });
    
    $("#addressPhone").blur(function () {
        var value=$(this).val();
        var re=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
        if (!re.test(value)){
        	$(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-remove").show("fast");
            $(this).parent().parent().find("p").fadeIn("fast");
            $(this).attr("flag","false");
        }
        else{
        	$(this).parent().parent().find("p").fadeOut("fast");
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-ok").show("fast");
            $(this).attr("flag","true");
        }
    });
    
    $("#addressName").blur(function () {
        var value=$(this).val();
        var re=/^[\u4E00-\u9FA5A-Za-z0-9_]{2,8}$/
        if (!re.test(value)){
        	$(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-remove").show("fast");
            $(this).parent().parent().find("p").fadeIn("fast");
            $(this).attr("flag","false");
        }
        else{
        	$(this).parent().parent().find("p").fadeOut("fast");
            $(this).parent().find("i").hide("fast");
            $(this).parent().find(".glyphicon-ok").show("fast");
            $(this).attr("flag","true");
        }
    });
    
    $(".Address-addsubmit").click(function () {
        var addressflag=$("#addressDetail").attr("flag");
        var postcodeflag=$("#addressPostcode").attr("flag");
        var phoneflag=$("#addressPhone").attr("flag");
        var nameflag=$("#addressName").attr("flag"); 
        
        var address=$("#addressDetail").val();
        var postcode=$("#addressPostcode").val();
        var phone=$("#addressPhone").val();
        var name=$("#addressName").val();
        
        if (addressflag=="true"&&postcodeflag=="true"&&phoneflag=="true"&&nameflag=="true"){
            
        	$.ajax({
        		type:"post",
    			async:false,
    			url:"addAddress",
    			dataType:"jsonp",
    			jsonp:"callback",
    			data:{
    				addressDetail:address,
    				addressPostcode:postcode,
    				addressPhone:phone,
    				addressName:name,
    				t:new Date()
    			},
    			success:function(res)
    			{
    				if(res.result==""){
    				var text="<tr>"+"<td class='addressName' data-addrId='"+res.addressID+"'>"+name+"</td>"+"<td class='addressDetail'>"+address+"</td>"
    	            +"<td class='addressPhone'>"+phone+"</td>"+"<td class='addressPostcode'>"+postcode+"</td>"+ "<td><button class='layui-btn layui-btn-radius layui-btn-primary Address-modify' data-target='#myModal' data-toggle='modal'>修改</button>"+"<button class='layui-btn layui-btn-radius layui-btn-primary Address-delect'>删除</button></td>";
    	            $(".Address-table tbody").append(text);
    				}
    				else{
    					alert(res.result);
    				}
    			},
    			error:function(e){
    				alert("false");
    			}
        	});   	    
        }
        else{
            $(".modal-body .group .inputgroup i.glyphicon-remove,.modal-body .group .inputgroup p").show();
            return false;
        }
    });
    
    $(".whichChose").click(function(){
  $("#resetpwd span.inputgroup").find("i,p").hide();
    	console.log("hidden");
    });
    
    $("#ResetPwd").click(function(){
    	if($("#oldcustomerPwd").attr("flag")=="true"&&$("#customerPwd").attr("flag")=="true"&&$("#recustomerPwd").attr("flag")=="true"){
    		console.log("ok");
    		var id=$("#OldPassword").attr("data-id");
    		var oldPwd=$("#oldcustomerPwd").val();
    		var password=$("#customerPwd").val();
    		var repassword=$("#recustomerPwd").val();
    		console.log(id+"/"+oldPwd+"/"+password+"/"+repassword);
    		$.ajax({
    			type:"post",
    			async:false,
    			url:"changePassword",
    			dataType:"jsonp",
    			jsonp:"callback",
    			data:{
    				customerID:id,
    				oldPassword:oldPwd,
    				newPassword:password,
    				rePassword:repassword,
    				t:new Date()
    			},
    			success:function(res)
    			{
    				console.log(res.result);
    				if(res.result!=""){
						console.log(res.result);
							layer.open({
       				 			title:'修改失败'
       				 			,content: res.result
        						,anim: 6 
    						});
					}
					else{
						$(".tip").fadeIn();
						$(".tip").delay(1500).fadeOut();
						$("#OldPassword").val(password);
					}
    			},
    			error:function(es){
    				alert("tijiaoshibia");
    			}
    		});
    	}
    });
    $(".addressName").each(function(){
    	 console.log($(this).attr("data-addrId"));
    });
    
    $(".form-img").on("change",function(){
    	var index=$(this).attr("data-which");
    	var filePath = $(this).val(),         //获取到input的value，里面是文件的路径
		fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
		src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式
		
	// 检查是否是图片
	if( !fileFormat.match(/.png|.jpg|.jpeg/) ) {
		error_prompt_alert('上传错误,文件格式必须为：png/jpg/jpeg');
    	return;  
    }

    $('.testimg'+index+'').attr('src',src);
    $('.testimg'+index+'').attr('width',"65");
   
    });
    
    var $test=$(".panel.showli");
    if($test.length==0){
    	$(".isNoPanel").show();
    	$(".page").hide();
    }

});