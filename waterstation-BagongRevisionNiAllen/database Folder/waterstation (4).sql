-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2024 at 10:27 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `waterstation`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `UID` int(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `OTP` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`UID`, `username`, `password`, `OTP`) VALUES
(1, 'admin', '1234', 0),
(2, 'test', '12345', 0);

-- --------------------------------------------------------

--
-- Table structure for table `carttable`
--

CREATE TABLE `carttable` (
  `productname` varchar(100) NOT NULL,
  `qty` int(20) NOT NULL,
  `price` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customertable`
--

CREATE TABLE `customertable` (
  `UID` int(50) NOT NULL,
  `customername` varchar(50) NOT NULL,
  `contactnumber` int(15) NOT NULL,
  `address` varchar(50) NOT NULL,
  `comments` varchar(100) NOT NULL,
  `borroweditems` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customertable`
--

INSERT INTO `customertable` (`UID`, `customername`, `contactnumber`, `address`, `comments`, `borroweditems`) VALUES
(1, 'nnn', 135, 'asd', 'asd', 'None'),
(2, 'allen', 1234, 'asd', 'asd', 'None'),
(3, 'admin', 1234, 'asd', 'asd', 'None');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `productid` int(20) NOT NULL,
  `productname` varchar(100) NOT NULL,
  `qty` int(20) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`productid`, `productname`, `qty`, `price`) VALUES
(1, 'bottle', 4, 10),
(2, 'gallooon', 21, 10),
(3, 'alle', 0, 10),
(4, 'allen', 990, 90);

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `receiptid` int(20) NOT NULL,
  `productname` varchar(100) NOT NULL,
  `qty` int(20) NOT NULL,
  `discount` double(20,0) NOT NULL,
  `price` decimal(20,0) NOT NULL,
  `date` varchar(30) NOT NULL DEFAULT current_timestamp(),
  `customer` varchar(50) NOT NULL,
  `method` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`receiptid`, `productname`, `qty`, `discount`, `price`, `date`, `customer`, `method`) VALUES
(1, 'bottle (10)\ngallooon (10)', 20, 50, 100, '2024-06-13 10:37:56 PM', '', 'Delivery'),
(2, 'bottle (5)\ngallooon (2)', 7, 0, 70, '2024-06-13 10:58:42 PM', '', 'Pick-up'),
(3, 'bottle (1)', 1, 0, 10, '2024-06-14 03:27:01 AM', '', 'Delivery'),
(4, 'gallooon (12)', 12, 0, 120, '2024-06-14 03:30:46 AM', 'allen', 'Delivery');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`UID`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`productid`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`receiptid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `UID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
