$(function() {
	$.ajax({
		type : "GET",
		url : path + "/sys/role/list.html",
		dataType : "json",
		success : function(data) {
			if (data != null) {
				userRole.html("");
				var options = "<option value=\"0\">请选择</option>";
				for ( var i = 0; i < data.length; i++) {
					options += "<option value=\"" + data[i].id + "\">"
							+ data[i].roleName + "</option>";
				}
				userRole.html(options);
			}
		},
		error : function(data) {// 当访问时候，404，500 等非200的错误状态码
			validateTip(userRole.next(), {
				"color" : "red"
			}, imgNo + " 获取用户角色列表error", false);
		}
	});
});