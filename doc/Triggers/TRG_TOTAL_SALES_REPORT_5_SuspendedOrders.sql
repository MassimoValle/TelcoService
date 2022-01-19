CREATE TABLE Sales_Report_5_SuspendedOrders(OrderID INT)

DELIMITER //
CREATE TRIGGER TRG_TOTAL_SALES_REPORT_5_SuspendedOrders
AFTER INSERT ON db_Telco_DB2.Order
FOR EACH ROW
BEGIN
   	REPLACE INTO db_Telco_DB2.Sales_Report_5_SuspendedOrders
  		SELECT DISTINCT OrderID
    	FROM db_Telco_DB2.Order
    	WHERE Status = "rejected";
END;//
DELIMITER ;