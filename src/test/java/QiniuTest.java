import com.qiniu.util.Auth;

public class QiniuTest {

	public static void main(String[] args) {
		String accessKey = "QLpTtjVnCgwMfTKPbZ91wLNW0EtPGhOnCjYfploK";
		String secretKey = "DCM_Lo2U9Oyact4BE262YVYzu8-ior9tQ-lYb4CR";
		Auth auth = Auth.create(accessKey, secretKey);
		String uploadToken = auth.uploadToken("happycar-img");
		System.out.println(uploadToken);
	}

}
