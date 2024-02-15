package com.siddhartha.garments.service;

import com.siddhartha.garments.request.PurchaseReportReq;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;

public interface ReportService {

	CommonResponse getWorkerReport(WorkerEntryDetailsReq req);

	CommonResponse exportExcel(WorkerEntryDetailsReq req);

	CommonResponse getPurchaseReport(PurchaseReportReq req);

}
