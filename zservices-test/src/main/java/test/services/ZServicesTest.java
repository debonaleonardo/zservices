package test.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.model.Customer;

public class ZServicesTest {

	@Test
	public void addCustomerTest() {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://localhost:8080/zservices-rest/customers/add");

		try {
			Customer customer = new Customer();
			customer.setAge(25);
			customer.setName("Joao Marcelo Ribeiro Lima");
			customer.setEmail("joao.ribeiro@gmail.com");
			
			ObjectMapper mapper = new ObjectMapper();
			
//			String json = mapper.writer().withRootName("customer").writeValueAsString(customer);
			String json = mapper.writeValueAsString(customer);
			
			StringRequestEntity requestEntity = new StringRequestEntity(
				    json,
				    "application/json",
				    "UTF-8");
			post.setRequestEntity(requestEntity);
			client.executeMethod(post);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void getCustomerTest()
	{
		
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod("http://localhost:8080/zservices-rest/customers/getcustomer/joao.ribeiro@gmail.com");		
		
		
		try {
			client.executeMethod(get);
			
			StringBuilder sb=new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(get.getResponseBodyAsStream()));
			String read;

			while((read=br.readLine()) != null) {
			    sb.append(read);   
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
