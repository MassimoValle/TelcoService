CREATE TABLE Sales_Report_1(ServicePackage VARCHAR(45), NumberTotalPurchases INT)

DELIMITER //
CREATE TRIGGER TRG_TOTAL_SALES_REPORT_1
AFTER INSERT ON db_Telco_DB2.Order
FOR EACH ROW
BEGIN
   	REPLACE INTO db_Telco_DB2.Sales_Report_1
  		SELECT ServicePackageID, COUNT(ServicePackageID)
    	FROM db_Telco_DB2.Subscription
    	GROUP BY ServicePackageID;
END;//
DELIMITER ;