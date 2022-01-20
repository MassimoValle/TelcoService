CREATE TABLE Sales_Report_4(
ServicePackage VARCHAR(45),
AverageNumberOfOptionalProducts DECIMAL(10,2),
PRIMARY KEY(ServicePackage))


DELIMITER //
CREATE TRIGGER TRG_TOTAL_SALES_REPORT_4
AFTER INSERT ON db_Telco_DB2.Order
FOR EACH ROW
BEGIN
   	REPLACE INTO db_Telco_DB2.Sales_Report_4
  		SELECT ServicePackageID, AVG(Count)
		FROM (
			SELECT ServicePackageID, COUNT(*) AS Count
    			FROM db_Telco_DB2.Subscription AS S JOIN db_Telco_DB2.SOP AS SOP
        			WHERE S.ID = SOP.SubscriptionID
    			GROUP BY ServicePackageID, ID
		) AS TMP
		GROUP BY ServicePackageID;
END;//
DELIMITER ;