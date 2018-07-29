import java.io.File;
import java.io.IOException;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyTest {

	@Test
	public void testMethod(){
		OkHttpClient okHttpClient = new OkHttpClient();
		String url = "http://localhost:8080/oa/web/user/registerMachine";
		url="http://localhost:8080/oa/web/auth/registerMachine";
		File file=new File("D:\\estate\\face\\owner\\1.jpg");
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);

		RequestBody requestBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("face", "head_image.jpg", fileBody)
				.addFormDataPart("name", "yzl666")
				.addFormDataPart("sex","1")
				.addFormDataPart("phone","13750618299")
				.addFormDataPart("kind","0")
				.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
            String jsonString = response.body().string();

            if(!response.isSuccessful()){
            	System.out.println("fail!!!!!!");
            }else{
            	System.out.println(jsonString);
            }

        } catch (Exception e) {
        	
        }
		
	}
	
	@Test
	public void test2(){
		testMethod();
		
		OkHttpClient okHttpClient = new OkHttpClient();
		String url = "http://localhost:8080/oa/web/user/registerMachine";
//		url="http://localhost:8080/oa/web/auth/login";
		File file=new File("D:\\estate\\face\\owner\\1.jpg");
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("face", "head_image.jpg", fileBody)
                .addFormDataPart("name", "yzl666")
//                .addFormDataPart("userphone", userPhone)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
            String jsonString = response.body().string();

            if(!response.isSuccessful()){
            	System.out.println("fail!!!!!!");
            }else{
//            	System.out.println("succ----");
            }

        } catch (Exception e) {
        	
        }
        
	
	}
	
}
