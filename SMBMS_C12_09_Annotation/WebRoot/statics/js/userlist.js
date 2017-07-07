var userObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:path+"/sys/user/dodelete.html",
		data:{uid:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data == "success"){//删除成功：移除删除行
				alert("删除成功");
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data == "failure"){//删除失败
				//alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				changeDLGContent("对不起，删除用户【"+obj.attr("username")+"】失败");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();	
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	 */
	$(".viewUser").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		//以页面的形式显示
		//window.location.href=path+"/sys/user/view.html?uid="+ obj.attr("userid");
		
		//ch12  alert(obj.attr("userid"));
		//以ajax请求数据，回显示至用户明细信息div
		
		$.ajax({
			type:"get",
			url:path+"/sys/user/view.html" ,
			data:{uid:obj.attr("userid")},
			dataType:"json",
			success:function(result){
				$("#v_userCode").val(result.userCode);
				$("#v_userName").val(result.userName);
				if(result.gender=="1"){
					$("#v_gender").val("女");
				}else if(result.gender=="2"){
					$("#v_gender").val("男");
				}
				$("#v_birthday").val(result.birthday);
				$("#v_phone").val(result.phone);
				$("#v_address").val(result.address);
				$("#v_userRoleName").val(result.userRoleName);
			},
			error:function(data){
				alert("error!");
			}
			
					
		});
	});
	
	$(".modifyUser").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/sys/user/modify.html?uid="+ obj.attr("userid");
		
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		$('#removeUse').fadeOut();//隐藏对话框
		deleteUser(userObj);//删除用户
		
	});

	$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});
	

});