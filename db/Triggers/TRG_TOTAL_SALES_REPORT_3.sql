CREATE TABLE Sales_Report_3(
ServicePackage VARCHAR(45),
TotalValueOfSales DECIMAL(10,2),
NTotalValueOfSalesWithoutOptionalProducts DECIMAL(10,2),
PRIMARY KEY(ServicePackage)
)


DELIMITER //
CREATE TRIGGER TRG_TOTAL_SALES_REPORT_3
AFTER INSERT ON db_Telco_DB2.Order
FOR EACH ROW
BEGIN
   	REPLACE INTO db_Telco_DB2.Sales_Report_3
  		SELECT DISTINCT SUB.ServicePackageID, SUM(TotalPrice), SUM(TotalPrice - PeriodID*
						(SELECT COALESCE(SUM(MonthlyFee),0)
   						FROM db_Telco_DB2.Product AS P JOIN db_Telco_DB2.SOP AS S
						WHERE S.OptionalProductID = P.ID AND S.SubscriptionID = SUB.ID
                        ))
		FROM Subscription AS SUB
		GROUP BY ServicePackageID;
END;//
DELIMITER ;