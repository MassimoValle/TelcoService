CREATE TABLE Sales_Report_5_InsolventUsers(User VARCHAR(45), PRIMARY KEY(User))

DELIMITER //
CREATE TRIGGER TRG_TOTAL_SALES_REPORT_5_InsolventUsers
AFTER INSERT ON db_Telco_DB2.Order
FOR EACH ROW
BEGIN
   	REPLACE INTO db_Telco_DB2.Sales_Report_5_InsolventUsers
  		SELECT DISTINCT Username
    	FROM db_Telco_DB2.User
    	WHERE Status = "insolvent";
END;//
DELIMITER ;