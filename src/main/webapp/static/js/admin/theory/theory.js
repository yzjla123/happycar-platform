(function(){
	$().ready(function(){
		$('#myUploader').uploader({
			autoUpload: true,
			multipart: true,
			unique_names: true,
		    qiniu: {
		    	uptoken : qiniu_happycar_img_uptoken,
		        domain: qiniu_happycar_img_domain
		    },
		    responseHandler:function(responseObject, file){
		    	$('#imgUrl').val(qiniu_happycar_img_domain+"/"+JSON.parse(responseObject).key);
		    }
		});
	});
	
})();