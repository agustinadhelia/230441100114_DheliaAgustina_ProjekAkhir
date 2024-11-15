-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2024 at 05:59 AM
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
-- Database: `inventaris_pertanian`
--

-- --------------------------------------------------------

--
-- Table structure for table `dk_bibit`
--

CREATE TABLE `dk_bibit` (
  `id` int(11) NOT NULL,
  `nama_bibitdk` varchar(255) NOT NULL,
  `jumlah_bibitdk` int(11) NOT NULL,
  `bulan_bibitdk` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dk_bibit`
--

INSERT INTO `dk_bibit` (`id`, `nama_bibitdk`, `jumlah_bibitdk`, `bulan_bibitdk`) VALUES
(1, 'kk', 2, '2024-11-06'),
(2, 'ssss', 2, 'January'),
(3, 'bibit tomat', 3, '2024-11-13');

-- --------------------------------------------------------

--
-- Table structure for table `dk_pupuk`
--

CREATE TABLE `dk_pupuk` (
  `id` int(11) NOT NULL,
  `nama_pupukdk` varchar(255) NOT NULL,
  `jumlah_pupukdk` int(11) NOT NULL,
  `bulan_pupukdk` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dk_pupuk`
--

INSERT INTO `dk_pupuk` (`id`, `nama_pupukdk`, `jumlah_pupukdk`, `bulan_pupukdk`) VALUES
(1, 'yyy', 2, '2024-11-03'),
(2, 'pupuk organik', 1, '2024-11-01'),
(3, 'pupuk kompos', 1, '2024-11-08');

-- --------------------------------------------------------

--
-- Table structure for table `dk_tanaman`
--

CREATE TABLE `dk_tanaman` (
  `id` int(11) NOT NULL,
  `nama_tanamandk` varchar(255) NOT NULL,
  `jumlah_tanamandk` int(11) NOT NULL,
  `harga_tanamandk` int(11) NOT NULL,
  `bulan_tanamandk` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dk_tanaman`
--

INSERT INTO `dk_tanaman` (`id`, `nama_tanamandk`, `jumlah_tanamandk`, `harga_tanamandk`, `bulan_tanamandk`) VALUES
(1, 'wortel', 1, 3000, '2024-11-11'),
(2, 'gubis', 4, 8000, '2024-11-09'),
(3, 'chery tomat', 10, 50000, '2024-11-25');

-- --------------------------------------------------------

--
-- Table structure for table `dm_bibit`
--

CREATE TABLE `dm_bibit` (
  `id` int(11) NOT NULL,
  `nama_bibitdm` varchar(255) NOT NULL,
  `jumlah_bibitdm` int(11) NOT NULL,
  `harga_bibitdm` int(11) NOT NULL,
  `nama_pemasokbibitdm` varchar(255) NOT NULL,
  `bulan_bibitdm` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dm_bibit`
--

INSERT INTO `dm_bibit` (`id`, `nama_bibitdm`, `jumlah_bibitdm`, `harga_bibitdm`, `nama_pemasokbibitdm`, `bulan_bibitdm`) VALUES
(1, 'bibit cabai', 3, 4000, 'dhelia', '2024-11-04'),
(2, 'bibit selada', 1, 3000, 'nisa', '2024-11-07'),
(3, 'bibit kangkung', 5, 4000, 'alin', '2024-11-13');

-- --------------------------------------------------------

--
-- Table structure for table `dm_pupuk`
--

CREATE TABLE `dm_pupuk` (
  `id` int(11) NOT NULL,
  `nama_pupukdm` varchar(255) NOT NULL,
  `jumlah_pupukdm` int(11) NOT NULL,
  `harga_pupukdm` int(11) NOT NULL,
  `nama_pemasokpupukdm` varchar(255) NOT NULL,
  `bulan_pupukdm` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dm_pupuk`
--

INSERT INTO `dm_pupuk` (`id`, `nama_pupukdm`, `jumlah_pupukdm`, `harga_pupukdm`, `nama_pemasokpupukdm`, `bulan_pupukdm`) VALUES
(1, 'Pupuk kompos', 2, 40000, 'jana', '2024-11-06'),
(2, 'pupuk kompos', 2, 6000, 'aji', '2024-11-14');

-- --------------------------------------------------------

--
-- Table structure for table `dm_tanaman`
--

CREATE TABLE `dm_tanaman` (
  `id` int(11) NOT NULL,
  `nama_tanamandm` varchar(255) NOT NULL,
  `jumlah_tanamandm` int(11) NOT NULL,
  `bulan_tanamandm` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dm_tanaman`
--

INSERT INTO `dm_tanaman` (`id`, `nama_tanamandm`, `jumlah_tanamandm`, `bulan_tanamandm`) VALUES
(1, 'cabai', 4, '2024-11-06'),
(2, 'chery tomat', 2, '2024-11-15');

-- --------------------------------------------------------

--
-- Table structure for table `laporan_dk`
--

CREATE TABLE `laporan_dk` (
  `nama_dk` varchar(255) NOT NULL,
  `jumlah_dk` int(11) NOT NULL,
  `bulan_dk` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `laporan_dm`
--

CREATE TABLE `laporan_dm` (
  `nama_dm` varchar(255) NOT NULL,
  `jumlah_dm` int(11) NOT NULL,
  `harga_dm` int(11) NOT NULL,
  `bulan_dm` varchar(255) NOT NULL,
  `nama_pemasokdm` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `laporan_keuangan`
--

CREATE TABLE `laporan_keuangan` (
  `tanggal` varchar(255) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dk_bibit`
--
ALTER TABLE `dk_bibit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dk_pupuk`
--
ALTER TABLE `dk_pupuk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dk_tanaman`
--
ALTER TABLE `dk_tanaman`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dm_bibit`
--
ALTER TABLE `dm_bibit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dm_pupuk`
--
ALTER TABLE `dm_pupuk`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dm_tanaman`
--
ALTER TABLE `dm_tanaman`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dk_bibit`
--
ALTER TABLE `dk_bibit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `dk_pupuk`
--
ALTER TABLE `dk_pupuk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `dk_tanaman`
--
ALTER TABLE `dk_tanaman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `dm_bibit`
--
ALTER TABLE `dm_bibit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `dm_pupuk`
--
ALTER TABLE `dm_pupuk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `dm_tanaman`
--
ALTER TABLE `dm_tanaman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
