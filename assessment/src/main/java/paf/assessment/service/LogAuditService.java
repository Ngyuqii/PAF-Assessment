package paf.assessment.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import paf.assessment.model.Transfer;

@Service
public class LogAuditService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public int saveToRedis(Transfer t) {

        LocalDate datestamp = LocalDate.now();
        //use LocalDateTime if time required

        JsonObject jsonObj = Json.createObjectBuilder()
                    .add("transactionid", t.getTransactionId())
                    .add("from_account", t.getFromAcct())
                    .add("to_account", t.getToAcct())
                    .add("amount",t.getTransferAmt())
                    .add ("date", datestamp.toString())
                    .build();

        redisTemplate.opsForValue().set(t.getTransactionId(), jsonObj.toString());
        String result = (String) redisTemplate.opsForValue().get(t.getTransactionId());
        if (result != null)
            return 1;
        return 0;
        
    }
    
}
