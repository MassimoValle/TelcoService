CREATE TABLE Sales_Report_6(ProductID INT, PRIMARY KEY(ProductID))

DELIMITER //
CREATE TRIGGER TRG_TOTAL_SALES_REPORT_6
AFTER INSERT ON db_Telco_DB2.Order
FOR EACH ROW
BEGIN
   	REPLACE INTO db_Telco_DB2.Sales_Report_6
  		SELECT ID FROM
		(
		  SELECT ID, MonthlyFee*Count AS Sold
		  FROM Product AS P JOIN 
		  	(
		  		SELECT DISTINCT OptionalProductID, COUNT(OptionalProductID) AS Count
				FROM db_Telco_DB2.SOP AS S JOIN db_Telco_DB2.Product AS P
				GROUP BY OptionalProductID
			) AS TMP1
            	  WHERE P.ID = TMP1.OptionalProductID
		  GROUP BY Sold, ID
		) AS TMP2
		ORDER BY Sold DESC
		LIMIT 1;
END;//
DELIMITER ;