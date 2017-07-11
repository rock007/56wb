package com.fp.gan.backend.receiver;

import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class LogReceiver implements Consumer<Event<String>> {

	public void accept(Event<String> ev) {
		
		try {
			Thread.sleep(1000*20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("收到值:"+ev.getData()); 

	}

}