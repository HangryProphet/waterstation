-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2024 at 12:41 PM
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
  `AccountType` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Position` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`UID`, `username`, `password`, `AccountType`, `Position`) VALUES
(1, 'admin', '1234', '', ''),
(2, 'test', 'pass', '', ''),
(3, 'Nathan', '1234', '', ''),
(4, 'ryze', '123456', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `carttable`
--

CREATE TABLE `carttable` (
  `productname` varchar(100) NOT NULL,
  `qty` int(20) NOT NULL,
  `price` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `carttable`
--

INSERT INTO `carttable` (`productname`, `qty`, `price`) VALUES
('Pet Bottle 350ML', 12, 5);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CID` int(11) NOT NULL,
  `Category` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customertable`
--

CREATE TABLE `customertable` (
  `UID` int(50) NOT NULL,
  `customername` varchar(50) NOT NULL,
  `contactnumber` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `comments` varchar(100) NOT NULL,
  `borroweditems` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customertable`
--

INSERT INTO `customertable` (`UID`, `customername`, `contactnumber`, `address`, `comments`, `borroweditems`) VALUES
(1, 'Allen Lazatin', '09876543210', 'Pulilan, Bulacan', 'Hotdog', 'None'),
(2, 'Lance Uy', '9138483515', 'Barangay Pogi, bULACAN', '', 'Slim'),
(3, 'Ahiah de Guzman', '09876543210', 'Bustos, Bulacan', '', 'None'),
(4, 'Nathan Uy', '0987654210', 'Bulacan, Philippines', 'Uy brother', 'None'),
(5, 'Harry Sevilla', '09876543210', 'sddvsdsdsfsdf', 'dfvfddfg', 'None'),
(6, 'Jeremiah C. Jose', '09162476799', '01 Mapulang Lupa, Pandi, Bulacan', 'MALAKI HOTDOG', 'None');

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
(1, 'Pet Bottle 350ML', 823, 5),
(2, 'Slim', 941, 25),
(3, 'Round', 944, 25),
(4, 'Alkaline', 500, 35),
(5, 'Pet Bottle 500ML', 3000, 7),
(6, 'Pet Bottle 1L', 3000, 10);

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `receiptid` int(20) NOT NULL,
  `productname` varchar(100) NOT NULL,
  `qty` int(20) NOT NULL,
  `discount` int(20) NOT NULL,
  `price` double(20,2) NOT NULL,
  `date` varchar(30) NOT NULL DEFAULT current_timestamp(),
  `method` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`UID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CID`);

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
  MODIFY `UID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `CID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
