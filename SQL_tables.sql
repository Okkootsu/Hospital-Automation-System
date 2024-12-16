CREATE TABLE `appointment` (
  `apt_id` int NOT NULL AUTO_INCREMENT,
  `cid` int NOT NULL,
  `pid` int DEFAULT NULL,
  `doctor` varchar(45) NOT NULL,
  `apt_date` varchar(45) NOT NULL,
  PRIMARY KEY (`apt_id`),
  UNIQUE KEY `unique_apt_date` (`cid`,`apt_date`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `clinics` (
  `fullName` varchar(45) NOT NULL,
  `clinic` varchar(45) NOT NULL,
  PRIMARY KEY (`fullName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullName` varchar(45) NOT NULL,
  `tc` bigint NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`tc`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `diagnoses` (
  `apt_id` int NOT NULL,
  `pid` int NOT NULL,
  `cid` int NOT NULL,
  `diagnose` varchar(255) NOT NULL,
  `apt_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`apt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullName` varchar(45) NOT NULL,
  `tc` bigint NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`tc`),
  UNIQUE KEY `unique_role` (`id`,`role`),
  UNIQUE KEY `tc` (`tc`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci