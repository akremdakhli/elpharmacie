-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 26, 2021 at 01:44 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nids`
--

-- --------------------------------------------------------

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE IF NOT EXISTS `advertisement` (
  `id_advertisment` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` varchar(255) DEFAULT NULL,
  `cost` double NOT NULL,
  `date_advertisment` date DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `finalviews` int(11) NOT NULL,
  `targetviews` int(11) NOT NULL,
  `type_advertisement` varchar(255) DEFAULT NULL,
  `client_id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_advertisment`),
  KEY `FKdx59m79n63ti5n74g0iuiklt` (`client_id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
CREATE TABLE IF NOT EXISTS `claim` (
  `id_claim` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL,
  `product_with_prob` bigint(20) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `decision` varchar(255) DEFAULT NULL,
  `delivery_problem` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `product_problem` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `system_problem` varchar(255) DEFAULT NULL,
  `employee_id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_claim`),
  KEY `FKcv8k4l3ldrjad0rixt4nmwpno` (`employee_id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `gender_client` varchar(255) DEFAULT NULL,
  `sub_month` varchar(255) DEFAULT NULL,
  `subscription_date` datetime DEFAULT NULL,
  `workfield_client` varchar(255) DEFAULT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`latitude`, `longitude`, `gender_client`, `sub_month`, `subscription_date`, `workfield_client`, `id_user`) VALUES
(0, 0, 'MALE', 'OCTOBER', '2021-10-25 22:46:32', 'FARMING_FISHING_FORESTRY', 2);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id_comment` bigint(20) NOT NULL AUTO_INCREMENT,
  `likes_comment` int(11) NOT NULL,
  `most_pertinent_comment` bit(1) NOT NULL,
  `text_comment` varchar(255) DEFAULT NULL,
  `subject_id_subject` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `FKkwuicuxyn5olroigy2e6oite3` (`subject_id_subject`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `deliverer`
--

DROP TABLE IF EXISTS `deliverer`;
CREATE TABLE IF NOT EXISTS `deliverer` (
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `available_deliverer` tinyint(1) DEFAULT '1',
  `bonus_deliverer` double NOT NULL,
  `deliverer_of_the_month_deliverer` bit(1) NOT NULL,
  `distance_deliverer` double NOT NULL,
  `score_deliverer` int(11) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
CREATE TABLE IF NOT EXISTS `delivery` (
  `id_delivery` bigint(20) NOT NULL AUTO_INCREMENT,
  `stars_service_delivery` varchar(255) DEFAULT NULL,
  `cost_delivery` float NOT NULL,
  `date_delivery` date DEFAULT NULL,
  `destination_delivery` varchar(255) DEFAULT NULL,
  `distance` double NOT NULL,
  `duration_delivery` varchar(255) DEFAULT NULL,
  `mean_of_transport_delivery` varchar(255) DEFAULT NULL,
  `quality_delivery` varchar(255) DEFAULT NULL,
  `score_delivery` int(11) NOT NULL,
  `deliverer_id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_delivery`),
  KEY `FKpj6l6gdpibfgskxmatwdbhxl7` (`deliverer_id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dictionary`
--

DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE IF NOT EXISTS `dictionary` (
  `id_dictionary_word` bigint(20) NOT NULL AUTO_INCREMENT,
  `bad_word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_dictionary_word`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `donation`
--

DROP TABLE IF EXISTS `donation`;
CREATE TABLE IF NOT EXISTS `donation` (
  `id_donation` int(11) NOT NULL AUTO_INCREMENT,
  `amount_donation` double NOT NULL,
  `product_donation` bigint(20) NOT NULL,
  `event_id_event` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_donation`),
  KEY `FK26j7sggwpuv991jtc3fr2b55l` (`event_id_event`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `nbabsence_employee` int(11) NOT NULL,
  `role_employee` varchar(255) DEFAULT NULL,
  `id_user` bigint(20) NOT NULL,
  `pool_id_pool` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `FK6j8df219arfhc6gfx6ge79nx9` (`pool_id_pool`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`nbabsence_employee`, `role_employee`, `id_user`, `pool_id_pool`) VALUES
(0, 'ADS_MANAGER', 3, NULL),
(0, 'ClAIM_MANAGER', 4, NULL),
(0, 'ADS_MANAGER', 5, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id_event` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_event` datetime DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `nombredeplaceinitiale` int(11) NOT NULL,
  `nombreplace` int(11) NOT NULL,
  `place_event` varchar(255) DEFAULT NULL,
  `raised_amount_event` double NOT NULL,
  `target_event` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_event`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `event_clients`
--

DROP TABLE IF EXISTS `event_clients`;
CREATE TABLE IF NOT EXISTS `event_clients` (
  `events_id_event` bigint(20) NOT NULL,
  `clients_id_user` bigint(20) NOT NULL,
  KEY `FKpboludckdv2c3uh2hce921mqa` (`clients_id_user`),
  KEY `FKi0erlwxw57xw1p8hmrif2ltfk` (`events_id_event`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE IF NOT EXISTS `feedback` (
  `id_feedback` int(11) NOT NULL AUTO_INCREMENT,
  `description_feedback` varchar(255) DEFAULT NULL,
  `note` float NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_feedback`),
  KEY `FKlsfunb44jdljfmbx4un8s4waa` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost` float NOT NULL,
  `date` date DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  `paid` bit(1) NOT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `weight` float NOT NULL,
  `delivery_id_delivery` bigint(20) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmaj0yhcwc9c4jeiurcopa9ggf` (`delivery_id_delivery`),
  KEY `FKhgqyhl4wnj9use215racmlveo` (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `cost`, `date`, `id_user`, `paid`, `payment_type`, `weight`, `delivery_id_delivery`, `payment_id`) VALUES
(1, 71.877, '2019-02-03', NULL, b'0', 'online', 11.4, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl23irn2eexpsjolo8udmwp4lw` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `amount`, `customer_id`, `date`, `description`, `email`, `order_id`) VALUES
(1, 71, 'ch_3JobX3IdCoFRZnwK1PH7YutZ', '2021-10-25', 'Your Payment is Successful', 'mouad19971@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pool`
--

DROP TABLE IF EXISTS `pool`;
CREATE TABLE IF NOT EXISTS `pool` (
  `id_pool` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount_pool` double NOT NULL,
  PRIMARY KEY (`id_pool`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `category_product` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `productofthemonth` bit(1) NOT NULL,
  `quantity_product` bigint(20) NOT NULL,
  `rating` float NOT NULL,
  `weight` double NOT NULL,
  `feedback_id_feedback` int(11) DEFAULT NULL,
  `promotion_id_promotion` bigint(20) DEFAULT NULL,
  `shelf_id_shelf` bigint(20) DEFAULT NULL,
  `supplier_id_supplier` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKehllhwycfht2sgwxht6w0pcw` (`feedback_id_feedback`),
  KEY `FK70fy4nmwgk7ugvx158m2426as` (`promotion_id_promotion`),
  KEY `FKn8q06679ecrhc62bog4cxnwdd` (`shelf_id_shelf`),
  KEY `FK72ip84o5gguko8ex6hpyf6fkh` (`supplier_id_supplier`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `available`, `category`, `category_product`, `description`, `image_path`, `name`, `price`, `productofthemonth`, `quantity_product`, `rating`, `weight`, `feedback_id_feedback`, `promotion_id_promotion`, `shelf_id_shelf`, `supplier_id_supplier`) VALUES
(1, b'1', NULL, NULL, 'description', 'C:\\Users\\mouad\\uploads\\1.jpg', 'Hrissa Sicam', 1.261, b'0', 77, 94.827, 0.2, NULL, NULL, 2, NULL),
(2, b'1', NULL, NULL, 'description', 'C:\\Users\\mouad\\uploads\\2.jpg', 'Durex', 1.261, b'0', 57, 94.827, 0.2, NULL, NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_ord`
--

DROP TABLE IF EXISTS `product_ord`;
CREATE TABLE IF NOT EXISTS `product_ord` (
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  KEY `FK5pnvwt61oitwosjy6u47bsf9v` (`product_id`),
  KEY `FK1614qoqn0w5xlggrivy54snjc` (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_ord`
--

INSERT INTO `product_ord` (`order_id`, `product_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id_promotion` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_promotion_debut` datetime DEFAULT NULL,
  `date_promotion_fin` date DEFAULT NULL,
  `new_product_price` double NOT NULL,
  `type_promotion` varchar(255) DEFAULT NULL,
  `valeur_promotion` double NOT NULL,
  PRIMARY KEY (`id_promotion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `shelf`
--

DROP TABLE IF EXISTS `shelf`;
CREATE TABLE IF NOT EXISTS `shelf` (
  `id_shelf` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `type_shelf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_shelf`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shelf`
--

INSERT INTO `shelf` (`id_shelf`, `description`, `type_shelf`) VALUES
(1, 'all type of meds products', 'meds'),
(2, 'all type of cosmetics products', 'cosmetic');

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
CREATE TABLE IF NOT EXISTS `store` (
  `id_store` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_store` varchar(255) DEFAULT NULL,
  `gain_store` double NOT NULL,
  `name_store` varchar(255) DEFAULT NULL,
  `open_store` bit(1) NOT NULL,
  PRIMARY KEY (`id_store`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `id_subject` bigint(20) NOT NULL AUTO_INCREMENT,
  `description_subject` varchar(255) DEFAULT NULL,
  `featured_subject` bit(1) NOT NULL,
  `likes_subject` int(11) NOT NULL,
  `user_id_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_subject`),
  KEY `FKtmtvdrd25ckpp595i4ivh73o` (`user_id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE IF NOT EXISTS `supplier` (
  `id_supplier` int(11) NOT NULL AUTO_INCREMENT,
  `name_supplier` varchar(255) DEFAULT NULL,
  `phone_number_supplier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_supplier`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_user` varchar(255) DEFAULT NULL,
  `date_birth_user` date DEFAULT NULL,
  `email_address_user` varchar(45) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name_user` varchar(20) NOT NULL,
  `last_name_user` varchar(20) NOT NULL,
  `locked` bit(1) NOT NULL,
  `nbaccess_user` int(11) NOT NULL,
  `password_user` varchar(64) NOT NULL,
  `phone_number_user` varchar(255) DEFAULT NULL,
  `role_user` varchar(255) DEFAULT NULL,
  `salary` double NOT NULL,
  `updated_password` bit(1) NOT NULL,
  `username_user` varchar(30) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK_tkj7nbwntdanxpjiku5xxwkmn` (`email_address_user`),
  UNIQUE KEY `UK_oe4tijj3b6d5lgqpm4hfq02m0` (`username_user`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `address_user`, `date_birth_user`, `email_address_user`, `enabled`, `first_name_user`, `last_name_user`, `locked`, `nbaccess_user`, `password_user`, `phone_number_user`, `role_user`, `salary`, `updated_password`, `username_user`) VALUES
(1, 'Ariena', '1990-09-19', 'managerCon@esprit.tn', b'1', 'manager', 'manager', b'0', 2, '$2a$10$vvP7qYGFpUJuwS0zOhhDAOFOrx3AVs5OPGSUe//iBDKkO9ZoPn0ga', '+216..........', 'EMPLOYEE', 0, b'1', 'managerC'),
(2, 'zawya-Nabeul', '1898-07-30', 'maher.snoussi@esprit.tn', b'1', 'maher', 'snoussi', b'0', 0, '$2a$10$daGRKaqNTn9hweTZtQf3WOfe5M60KIfcESHRkjU7l1..xrRpvFh0u', '22222222', 'CLIENT', 0, b'1', 'maher'),
(3, 'Ariena', '1920-12-10', 'hbluedal@gmail.com', b'1', 'employee20', 'employee20', b'0', 0, '$2a$10$j9bBjDDK4fFsD1BZ/9fc3.Vjtksz6Lk.xsSZ10Zv2D9U92TF77bBW', '+216..........', 'EMPLOYEE', 1000, b'1', 'employee20'),
(4, 'Ariena', '1990-12-10', 'mouadh19971@gmail.com', b'1', 'employee1', 'employee1', b'0', 0, '$2a$10$F6LrT8PC3wsJ2kcNOhFUdus7seJrFix.dPCuiRl2v3qH8I8m4qZF6', '+216..........', 'EMPLOYEE', 1000, b'0', 'employee1'),
(5, 'Ariena', '1990-12-10', 'mouad19971@gmail.com', b'1', 'employee2', 'employee2', b'0', 0, '$2a$10$.9Lq/z8c8t8CEZkSI5pzney3e3Z21FTkGnaBqKgCNJTaUPQqX7gjy', '+216..........', 'EMPLOYEE', 1000, b'0', 'employee2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
