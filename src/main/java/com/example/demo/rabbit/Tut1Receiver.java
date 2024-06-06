/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
//@Order(1)
@Component
@Slf4j
//@Order
//@Priority()
//@RabbitListener(queues = "tut.hello",priority = "10")
@RabbitListener(queues = "tut.hello" )
//@DependsOn("tut2Receiver")
public class Tut1Receiver {

	private  int instance =1;

	public Tut1Receiver(int i) {
		this.instance = i;
	}

	public Tut1Receiver() {
		log.info("Tut1Receiver创建了");
	}

	@RabbitHandler
	public void receive(String in) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		log.info("instance " + this.instance + " [x] Received '" + in + "'");
		doWork(in);
		watch.stop();
		log.info("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(500);
			}
		}
	}

}
