package com.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.constant.GraphTypeConfig;
import com.vo.GrathVO;

import net.sf.json.JSONArray;

@Service
public class GraphDataServiceImpl implements IGraphDateService {

	private static final Logger log = Logger.getLogger(GraphDataServiceImpl.class);
	private indexDataService ids = new indexDataService();
	
	@SuppressWarnings("unchecked")
	@Override
	public GrathVO graphData(Map<String, Object> map) {
		log.info("图形数据处理开始.................");
		GrathVO grath = new GrathVO();
		
	
		String normData = (String) map.get("normData");
		map.remove("normData");
		if(normData.contains(GraphTypeConfig.GRAPHTYPE_01)) {
			Map<String, Object> mapIpFlow = mapIpFlowData(map);
			grath.setMapIpFlowX((List<String>) mapIpFlow.get("xData"));
			grath.setMapIpFlowY((List<Integer>) mapIpFlow.get("yData"));
		}
//		if(norm.contains("吞吐曲线图"))
//			graph.put("吞吐曲线图", hufPufFlow(map));
//		if(norm.contains("包长分布图"))
//			graph.put("包长分布图", ipFlowData(map));
//		if(norm.contains("链路图"))
//			graph.put("链路图", ipLinkData(map));
//		
//		log.info("图形数据处理结束......返回参数:");
//		return JSON.toJSONString(graph);
		log.info("图形数据处理返回参数:" + JSONArray.fromObject(grath));
		return grath;
		
	}


	@SuppressWarnings("unchecked")
	private Map<String, Object> mapIpFlowData(Map<String, Object> map) {
		Map<String, Object> grathMap = (Map<String, Object>) map.get("tcpGraph");
		Map<String, Integer> mapIpFlow = (Map<String, Integer>) grathMap.get(GraphTypeConfig.GRAPHTYPE_01);
		Map<String, Integer> flowArry = new HashMap<String, Integer>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if (mapIpFlow.size() > 5) {
			List<String> ips = new ArrayList<String>();
			ips.add("other" +"@"+ mapIpFlow.get("other"));
			mapIpFlow.remove("other");
			ByValueComparator bvc = new ByValueComparator(mapIpFlow);
	        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
	        sorted_map.putAll(mapIpFlow);
	        for(String name : sorted_map.keySet()){
	        	ips.add(name + "@" +mapIpFlow.get(name));
	        	mapIpFlow.remove(name);
	        }
	        String[] ipNameByte;
	        ipNameByte = ips.get(0).split("@");
	        mapIpFlow.put("other", Integer.parseInt(ipNameByte[1]));
	        for(int j=1; j<ips.size(); j++) {
	        	ipNameByte = ips.get(j).split("@");
	        	if (j < 4) {
	        		mapIpFlow.put(ipNameByte[0], Integer.parseInt(ipNameByte[1]));
				} else {
					if (mapIpFlow.containsKey("other")) {
						mapIpFlow.put("other", Integer.parseInt(ipNameByte[1]) + mapIpFlow.get("other"));
					} else {
						mapIpFlow.put("other", Integer.parseInt(ipNameByte[1]));
					}
				}
	        }
		}

		Set<String> ips = mapIpFlow.keySet();
        for (String ip : ips) {
            flowArry.put(ip, mapIpFlow.get(ip));
        }

        List<String> x = ids.ipSort(map);
        List<Integer> y = new ArrayList<Integer>();
        Iterator<String> it = x.iterator();
        while(it.hasNext()){
            String ip = it.next();
            if (flowArry.get(ip) != null && !flowArry.get(ip).equals("")) {
        		y.add(flowArry.get(ip));
			} else {
				it.remove();
			}
        }
        
        x.add("other");
        y.add(flowArry.get("other"));
        
        resultMap.put("xData", x);
        resultMap.put("yData", y);
        return resultMap;
	}
	
	static class ByValueComparator implements Comparator<String> {
        Map<String, Integer> base_map;
 
        public ByValueComparator(Map<String, Integer> mapIpFlow) {
            this.base_map = mapIpFlow;
        }
 
        public int compare(String arg0, String arg1) {
            if (!base_map.containsKey(arg0) || !base_map.containsKey(arg1)) {
                return 0;
            }
 
            if (base_map.get(arg0) < base_map.get(arg1)) {
                return 1;
            } else if (base_map.get(arg0) == base_map.get(arg1)) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
