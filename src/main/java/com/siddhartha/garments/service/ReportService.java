package com.siddhartha.garments.service;

import com.siddhartha.garments.request.PurchaseReportReq;
import com.siddhartha.garments.request.WorkerEntryDetailsReq;
import com.siddhartha.garments.response.CommonResponse;
import com.siddhartha.garments.response.ExpensiveReportRequest;
import com.siddhartha.garments.response.OrderReportReq;
import com.siddhartha.garments.response.WorkerReportReq;

public interface ReportService {

	CommonResponse getWorkerReport(WorkerReportReq req);

	CommonResponse exportExcel(WorkerEntryDetailsReq req);

	CommonResponse getPurchaseReport(PurchaseReportReq req);

	CommonResponse getOrderReport(OrderReportReq req);

	CommonResponse getExpensiveReport(ExpensiveReportRequest req);

}
