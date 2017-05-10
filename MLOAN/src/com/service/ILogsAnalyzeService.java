package com.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface ILogsAnalyzeService {

	Map<String, Object> logAscription(String normData, List<String> redisNoFile,
			IGraphDateService service) throws InterruptedException, ExecutionException;

}
