(function(){
	
	$().ready(function() {
		var setting = {
				 check: {
				        enable: true,
				        chkboxType : { "Y" : "ps", "N" : "ps" }
				    },
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		$.getJSON("menuTree.do?type="+$('#type').val()+"&roleId="+$('#roleId').val(),function(data){
			$.fn.zTree.init($("#menuTree"),setting, data.tree);
		});
		$.getJSON("rightTree.do?type="+$('#type').val()+"&roleId="+$('#roleId').val(),function(data){
			$.fn.zTree.init($("#rightTree"), setting, data.tree);
		});
		
		
		$('#save').click(function(){
			if(getCheckID('menuTree').length==0){
				alert('请选择菜单权限');
				return;
			}
			var rights = getCheckID('rightTree');
			if(rights.length==0){
				rights=new Array();
				rights.push("");
			}
			//if(getCheckID('rightTypeTree').length==0){
			//	alert('请选择功能权限');
			//	return;
			//}
			$.post("update.do",{menuIds:getCheckID('menuTree'),rightIds:rights,roleId:$('#roleId').val()},function(data){
				if(data.success){
					alertSuccessMsg('保存成功');
					location.reload();
				}else{
					alertErrorMsg('保存失败');
				}
			}, "json");
		});
	});
   
   function getCheckID(treeId) {
       var treeObj = $.fn.zTree.getZTreeObj(treeId);
       var nodes = treeObj.getCheckedNodes(true);
       var checkID = new Array();
       for (var i = 0; i < nodes.length; i++) {
       	checkID.push(nodes[i].id);
       }
       return checkID;
   }   
	
})();