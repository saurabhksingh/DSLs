package com.dev.saurabh;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Application {
	
	public static void main(String [] args) throws FileNotFoundException, ScriptException
	{
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("groovy");
		InputStream dslImpl = Application.class.getResourceAsStream("ClientOrder.groovy");
		InputStream dslClientScript = Application.class.getResourceAsStream("order.dsl");
		SequenceInputStream sequenceIs = new SequenceInputStream(dslImpl,dslClientScript);
		List<?> orders = (List<?>)engine.eval(new InputStreamReader(new BufferedInputStream(sequenceIs)));
		
		System.out.println(orders.size());
		
		for(Object o : orders)
		{
			System.out.println(o);
		}
	}

}
