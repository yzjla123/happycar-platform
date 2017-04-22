

/**
 * 弹出成功提示信息
 * @param msg 显示的信息
 * @returns
 */
function alertSuccessMsg(msg){
	new $.zui.Messager(msg, {
		type: 'success',	
	    icon: 'ok-sign',
	    placement: 'center'
	}).show();
}

/**
 * 弹出成功提示信息
 * @param msg 显示的信息
 * @returns
 */
function alertAlertMsg(msg){
	new $.zui.Messager(msg, {
		type: 'warning',	
	    icon: 'warning-sign',
	    placement: 'center'
	}).show();
}

/**
 * 弹出错误提示信息
 * @param msg 显示的信息
 * @returns
 */
function alertErrorMsg(msg){
	new $.zui.Messager(msg, {
		type: 'danger',	
	    icon: 'exclamation-sign',
	    placement: 'center'
	}).show();
}
/**
 * 提交表单
 */
$().ready(function(){
	var v = jQuery("#addForm,#updateForm").validate({
		submitHandler: function(form) {
			jQuery(form).ajaxSubmit({
				success: function(data){
					if(data.success){
						alertSuccessMsg(data.msg);
						setTimeout(() => {
							self.location = document.referrer;
						}, 1000);
					}else{
						alertErrorMsg(data.msg);
					}
				}
			});
		}
	});
	$('#back').click(function(){
		history.go(-1);
	});
	// 选择时间和日期
	$(".form-datetime").datetimepicker(
	{
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 2,
	    forceParse: 0,
	    showMeridian: 1,
	    format: "yyyy-mm-dd hh:ii"
	});

	// 仅选择日期
	$(".form-date").datetimepicker(
	{
	    language:  "zh-CN",
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 2,
	    minView: 2,
	    forceParse: 0,
	    format: "yyyy-mm-dd"
	});

	// 选择时间
	$(".form-time").datetimepicker({
	    language:  "zh-CN",
	    weekStart: 1,
	    todayBtn:  1,
	    autoclose: 1,
	    todayHighlight: 1,
	    startView: 1,
	    minView: 0,
	    maxView: 1,
	    forceParse: 0,
	    format: 'hh:ii'
	});
});
/**
 * 删除数据
 * @param id
 * @returns
 */
function del(id){
	if(confirm('确认删除？')){
		$.getJSON('del.do',{'id':id},function(data){
			if(data.success){
				alertSuccessMsg(data.msg);
				location.reload();
			}else{
				alertErrorMsg(data.msg);
			}
		});
	}
}
