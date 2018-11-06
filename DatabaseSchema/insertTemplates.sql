
# users insert
/*
INSERT INTO `users` 
				(
                `userID`, 
                `username`, 
                `email`, 
                `password`, 
                `loyaltyPoints`, 
                `userType`, 
                `isActive`, 
                `extendedDataInJSON`
                ) 
                VALUES (
                NULL, 
                'ColmLeGear', 
                'ColmLeGear@gmail.com', 
                'pass', 
                '25', 
                '0', 
                '1', 
                NULL
                );
*/
 #SELECT * FROM users

# airlines insert
/*
INSERT INTO `airlines` 
				(
                `autoID`, 
                `name`, 
                `isActive`, 
                `extendedDataInJSON`
                ) 
                VALUES 
                (
                NULL, 
                'British Airways', 
                '1', 
                NULL
                );



*/
#select * from airlines;
# airports insert
/*
INSERT INTO `airports` 
				(
                `autoID`, 
                `name`, 
                `isActive`, 
                `extendedDataInJSON`
                ) 
                VALUES 
                (
                NULL, 
                'Athens', 
                '1', 
                NULL
                );


*/
#select * from airports;
# flights insert
/*
INSERT INTO `flights` 
			(
            `autoID`, 
            `flightNumber`, 
            `depatureAirport`, 
            `destinationAirport`, 
            `departureTime`, 
            `arrivalTime`, 
            `price`, 
            `airlineID`, 
            `isActive`, 
            `extendedDataInJSON`
            ) 
	VALUES (
			NULL, 
			'NB328', 
			'6', 
			'7', 
			'{
				\r\n day:\"Thursday\",
				\r\n time: \"07:00\"\r\n
			}', 
			'{
				\r\n day:\"Thursday\",
				\r\n time: \"16:00\"\r\n
			}', 
			'500', 
			'4', 
			'1', 
			NULL
			);

*/
/*
UPDATE `flights` 
		SET `flightNumber` = 'AP3213'
        WHERE `flights`.`autoID` = 29;
*/
#UPDATE `flights` SET `departureTime` = replace(`departureTime`, ' ', '') where autoID =3 ; #departureTime like '\t%';
#update table_name set column_name = replace (column_name , 'oldstring' ,'newstring') where column_name like 'oldstring%'
#UPDATE flights SET departureTime = REPLACE(departureTime, char(9), '');
/*
UPDATE `flights`
SET `departureTime` = REPLACE(`departureTime`,'day:','"day":')
WHERE `departureTime` like '%day:%';

UPDATE `flights`
SET `departureTime` = REPLACE(`departureTime`,'time:','"time":')
WHERE `departureTime` like '%time:%';
*/
 select * from flights 
# INSERT bookings
/*
INSERT INTO `bookings` (
						`autoID`, 
                        `passengerID`, 
                        `flightID`, 
                        `departureTime`, 
                        `arrivalTime`, 
                        `isActive`, 
                        `extendedDataInJSON`
                        ) 
			     VALUES (
						 NULL, 
                         '1', 
                         '1', 
                         '2018-11-06 14:00:00', 
                         '2018-11-06 15:00:00', 
                         '1', 
                         NULL
                         )
*/

# discounts insert
/*
INSERT INTO `discounts` 
					(
                    `autoID`, 
                    `userID`, 
                    `flightID`, 
                    `discountStartDate`, 
                    `discountEndDate`, 
                    `discountPercentage`, 
                    `isActive`, 
                    `extendedDataInJSON`
                    ) 
                    VALUES 
                    (
                    NULL, 
                    '1', 
                    '1', 
                    '2018-11-06 00:00:00', 
                    '2018-11-09 00:00:00', 
                    '20', 
                    '1', 
                    NULL
                    )

*/
