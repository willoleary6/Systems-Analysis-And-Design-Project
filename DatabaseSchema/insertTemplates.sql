
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
                'AaronDunne', 
                'AaronD@gmail.com', 
                'pass', 
                '16', 
                '0', 
                '1', 
                NULL
                );
*/
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
                'Emirates', 
                '1', 
                NULL
                )
*/
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
                'Standstead', 
                '1', 
                NULL
                )
*/
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
			'A4532', 
			'1', 
			'2', 
			'{
				\r\n day:\"Thursday\",
				\r\n time: \"14:00\"\r\n
			}', 
			'{
				\r\n day:\"Thursday\",
				\r\n time: \"15:00\"\r\n
			}', 
			'300', 
			'2', 
			'1', 
			NULL
			)

*/

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
