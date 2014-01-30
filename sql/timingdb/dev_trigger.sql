CREATE TRIGGER triggername AFTER INSERT ON running BEGIN INSERT INTO activities VALUES (null, 1, 'letsweb'); END
