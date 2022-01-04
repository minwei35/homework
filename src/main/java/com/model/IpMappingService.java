package com.model;

import com.model.dao.IpRuleDao;
import com.model.pojo.IpRule;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IpMappingService {

    private final IpRuleDao ipRuleDao;

    public String getIpMappingStationHtml(String ipAddr){
        Map<String, IpRule> ipRuleMap = ipRuleDao.getAllIpRule().stream().collect(Collectors.toMap(IpRule::getIpRule, Function.identity(), (t1, t2) -> t1));
        int i = 4;
        String[] ipSplit = ipAddr.split("\\.");
        while (i >= 0){
            for (int j = i; j < ipSplit.length; j++){
                ipSplit[j] = "*";
            }
            String joinIp = String.join(".", ipSplit);
            if (ipRuleMap.containsKey(joinIp)){
                return ipRuleMap.get(joinIp).getStationHtml().getHtmlPath();
            }
            i--;
        }
        return "default";
    }
}
