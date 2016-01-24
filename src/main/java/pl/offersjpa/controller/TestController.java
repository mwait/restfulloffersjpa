package pl.offersjpa.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.offersjpa.model.User;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ExampleObjectRest> getTestDate(){
		List<ExampleObjectRest> rezult = Arrays.asList(new ExampleObjectRest(), new ExampleObjectRest());
		return rezult;
	}
}

class ExampleObjectRest  implements Serializable {
	private int id=1;
	private String name ="Test";
	private List<IncludeRest> restOb;
	
	public ExampleObjectRest () {
		restOb=Arrays.asList(new IncludeRest(), new IncludeRest(), new IncludeRest());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IncludeRest> getRestOb() {
		return restOb;
	}

	public void setRestOb(List<IncludeRest> restOb) {
		this.restOb = restOb;
	}

	private class IncludeRest implements Serializable {
		String data1="2015-05-05";
		String data2="2016-05-05";
		int count = 10;
		public String getData1() {
			return data1;
		}
		public void setData1(String data1) {
			this.data1 = data1;
		}
		public String getData2() {
			return data2;
		}
		public void setData2(String data2) {
			this.data2 = data2;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
	}
}





/* dane z jonsona
[
 {
   "id": 1,
   "name": "Test",
   "restOb": [
     {
       "data1": "2015-05-05",
       "data2": "2016-05-05",
       "count": 10
     },
     {
       "data1": "2015-05-05",
       "data2": "2016-05-05",
       "count": 10
     },
     {
       "data1": "2015-05-05",
       "data2": "2016-05-05",
       "count": 10
     }
   ]
 },
 {
   "id": 1,
   "name": "Test",
   "restOb": [
     {
       "data1": "2015-05-05",
       "data2": "2016-05-05",
       "count": 10
     },
     {
       "data1": "2015-05-05",
       "data2": "2016-05-05",
       "count": 10
     },
     {
       "data1": "2015-05-05",
       "data2": "2016-05-05",
       "count": 10
     }
   ]
 }
]*/
