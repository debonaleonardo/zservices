package test.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.model.Service;

public class ServicesTest {

//	@Test
	public void addServicesTest() {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://localhost:8080/zservices-rest/services/add");

		try {
			Service service = new Service();
			
			service.setName("diarista");
			service.setStatus(1);
			
			ObjectMapper mapper = new ObjectMapper();
			
			String json = mapper.writeValueAsString(service);
			
			StringRequestEntity requestEntity = new StringRequestEntity(
				    json,
				    "application/json",
				    "UTF-8");
			post.setRequestEntity(requestEntity);
			client.executeMethod(post);
			
			StringBuilder sb=new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
			String read;

			while((read=br.readLine()) != null) {
			    sb.append(read);   
			}
			
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void listAllServicesTest()
	{
		
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod("http://localhost:8080/zservices-rest/services/listAll/1");		
		
		
		try {
			client.executeMethod(get);
			
			StringBuilder sb=new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream()));
			String read;

			while((read=br.readLine()) != null) {
			    sb.append(read);   
			}
			
			System.out.println(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
