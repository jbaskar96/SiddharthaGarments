package com.siddhartha.garments.service;

import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;

public interface WorkerEntryService {

	CommonResponse workerEntrySave(WorkerEntryDetailsReq req);

	CommonResponse getWorkerEntryByOrderId(String orderId);

	CommonResponse getAllWorkerEntryDetail();

}
