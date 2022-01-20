DELIMITER //
CREATE TRIGGER TRG_TOTAL_COST_WITH_PRODUCTS
AFTER INSERT ON db_Telco_DB2.SOP
FOR EACH ROW
BEGIN
   	UPDATE db_Telco_DB2.Subscription
   	SET Subscription.TotalPrice = Subscription.TotalPrice + 
   	Subscription.PeriodID * (SELECT COALESCE(SUM(MonthlyFee),0)
   							 FROM db_Telco_DB2.Product AS P
							 WHERE new.OptionalProductID = P.ID AND new.SubscriptionID = Subscription.ID)
   	WHERE Subscription.ID = new.SubscriptionID;
END;//
DELIMITER ;