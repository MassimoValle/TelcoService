DELIMITER //
CREATE TRIGGER TRG_TOTAL_COST_BASE 
BEFORE INSERT ON db_Telco_DB2.Subscription 
FOR EACH ROW
BEGIN
	SET NEW.TotalPrice = NEW.PeriodID * (SELECT MonthlyFee FROM db_Telco_DB2.Period WHERE Period.Month = NEW.PeriodID);
END;//
DELIMITER ;