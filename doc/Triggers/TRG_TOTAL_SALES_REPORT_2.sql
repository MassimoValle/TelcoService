CREATE TABLE Sales_Report_2(
ServicePackage VARCHAR(45),
Period INT,
NumberTotalPurchasesPerPackageAndValidityPeriod INT,
PRIMARY KEY(ServicePackage, Period)
)


DELIMITER //
CREATE TRIGGER TRG_TOTAL_SALES_REPORT_2
AFTER INSERT ON db_Telco_DB2.Order
FOR EACH ROW
BEGIN
   	REPLACE INTO db_Telco_DB2.Sales_Report_2
  		SELECT DISTINCT ServicePackageID, PeriodID, COUNT(*)
    	FROM db_Telco_DB2.Subscription
    	GROUP BY ServicePackageID, PeriodID;
END;//
DELIMITER ;