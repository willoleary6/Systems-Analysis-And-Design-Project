-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 31, 2018 at 01:19 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `canoe`
--

-- --------------------------------------------------------

--
-- Table structure for table `airlines`
--

CREATE TABLE `airlines` (
  `autoID` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `extendedDataInJSON` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `airlines`
--

INSERT INTO `airlines` (`autoID`, `name`, `extendedDataInJSON`) VALUES
(1, 'Bryanair', NULL),
(2, 'Air Lickus', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `airports`
--

CREATE TABLE `airports` (
  `autoID` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `extendedDataInJSON` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `airports`
--

INSERT INTO `airports` (`autoID`, `name`, `extendedDataInJSON`) VALUES
(1, 'Bannon', NULL),
(2, 'shork', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `autoID` int(11) NOT NULL,
  `passengerID` int(11) NOT NULL,
  `flightID` int(11) NOT NULL,
  `departureTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `arrivalTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `extendedDataInJSON` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`autoID`, `passengerID`, `flightID`, `departureTime`, `arrivalTime`, `extendedDataInJSON`) VALUES
(1, 1, 1, '2018-10-30 14:00:00', '2018-10-30 15:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `discounts`
--

CREATE TABLE `discounts` (
  `autoID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `flightID` int(11) NOT NULL,
  `discountStartDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `discountEndDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `discountPercentage` double NOT NULL,
  `extendedDataInJSON` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discounts`
--

INSERT INTO `discounts` (`autoID`, `userID`, `flightID`, `discountStartDate`, `discountEndDate`, `discountPercentage`, `extendedDataInJSON`) VALUES
(1, 2, 1, '2018-10-30 12:00:00', '2018-11-06 12:00:00', 20, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
  `autoID` int(11) NOT NULL,
  `flightNumber` varchar(256) NOT NULL,
  `depatureAirport` int(11) NOT NULL,
  `destinationAirport` int(11) NOT NULL,
  `departureTime` varchar(256) NOT NULL,
  `arrivalTime` varchar(256) NOT NULL,
  `price` double NOT NULL,
  `airlineID` int(11) NOT NULL,
  `extendedDataInJSON` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`autoID`, `flightNumber`, `depatureAirport`, `destinationAirport`, `departureTime`, `arrivalTime`, `price`, `airlineID`, `extendedDataInJSON`) VALUES
(1, 'K4535', 1, 2, '{day:\"Tuesday\", time:\"14:00\"}', '{day:\"Tuesday\", time:\"15:00\"}', 40, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL COMMENT ' ',
  `username` varchar(25) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `loyaltyPoints` int(11) NOT NULL DEFAULT '0',
  `userType` int(11) NOT NULL DEFAULT '0',
  `isActive` tinyint(4) NOT NULL DEFAULT '1',
  `extendedDataInJSON` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `username`, `email`, `password`, `loyaltyPoints`, `userType`, `isActive`, `extendedDataInJSON`) VALUES
(1, 'basicCustomer', 'basicCustomer@gmail.com', 'pass', 0, 0, 1, NULL),
(2, 'basicAirlineEmployee', 'basicAirlineEmployee@Bryanair.com', 'pass', 0, 1, 1, '{airlineID: 1}');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airlines`
--
ALTER TABLE `airlines`
  ADD PRIMARY KEY (`autoID`);

--
-- Indexes for table `airports`
--
ALTER TABLE `airports`
  ADD PRIMARY KEY (`autoID`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`autoID`),
  ADD KEY `linkToPassenger` (`passengerID`),
  ADD KEY `linkToFlight` (`flightID`);

--
-- Indexes for table `discounts`
--
ALTER TABLE `discounts`
  ADD PRIMARY KEY (`autoID`),
  ADD KEY `userWhoAppliedDiscount` (`userID`),
  ADD KEY `flightDiscountIsAppliedTo` (`flightID`);

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`autoID`),
  ADD KEY `linkToAirline` (`airlineID`),
  ADD KEY `linkToDepartureAirport` (`depatureAirport`),
  ADD KEY `linkToDestinationAirport` (`destinationAirport`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `airlines`
--
ALTER TABLE `airlines`
  MODIFY `autoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `airports`
--
ALTER TABLE `airports`
  MODIFY `autoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `autoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `discounts`
--
ALTER TABLE `discounts`
  MODIFY `autoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `flights`
--
ALTER TABLE `flights`
  MODIFY `autoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT COMMENT ' ', AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `linkToFlight` FOREIGN KEY (`flightID`) REFERENCES `flights` (`autoID`),
  ADD CONSTRAINT `linkToPassenger` FOREIGN KEY (`passengerID`) REFERENCES `users` (`userID`);

--
-- Constraints for table `discounts`
--
ALTER TABLE `discounts`
  ADD CONSTRAINT `flightDiscountIsAppliedTo` FOREIGN KEY (`flightID`) REFERENCES `flights` (`autoID`),
  ADD CONSTRAINT `userWhoAppliedDiscount` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);

--
-- Constraints for table `flights`
--
ALTER TABLE `flights`
  ADD CONSTRAINT `linkToAirline` FOREIGN KEY (`airlineID`) REFERENCES `airlines` (`autoID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `linkToDepartureAirport` FOREIGN KEY (`depatureAirport`) REFERENCES `airports` (`autoID`),
  ADD CONSTRAINT `linkToDestinationAirport` FOREIGN KEY (`destinationAirport`) REFERENCES `airports` (`autoID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
