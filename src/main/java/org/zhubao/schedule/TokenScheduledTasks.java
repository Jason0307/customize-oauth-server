package org.zhubao.schedule;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class TokenScheduledTasks{

    @Scheduled(fixedRate = 1000 * 10)
    public void updateTokenStatus(){
    	
    }

}