-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Ago 01, 2024 alle 10:36
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sam`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `customer_email` varchar(255) NOT NULL,
  `customer_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `customers`
--

INSERT INTO `customers` (`id`, `customer_email`, `customer_name`) VALUES
(1, 'emcustomerl', 'customername');

-- --------------------------------------------------------

--
-- Struttura della tabella `log_entries`
--

CREATE TABLE `log_entries` (
  `id` bigint(20) NOT NULL,
  `log_action` varchar(255) DEFAULT NULL,
  `log_type` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `milestone_id` bigint(20) DEFAULT NULL,
  `minitask_id` bigint(20) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `milestones`
--

CREATE TABLE `milestones` (
  `id` bigint(20) NOT NULL,
  `completion_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `mile_desc` varchar(255) NOT NULL,
  `mile_name` varchar(255) NOT NULL,
  `mile_status` varchar(255) NOT NULL,
  `owner_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `minitasks`
--

CREATE TABLE `minitasks` (
  `id` bigint(20) NOT NULL,
  `completion_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `minitask_desc` varchar(255) NOT NULL,
  `minitask_name` varchar(255) NOT NULL,
  `minitask_status` varchar(255) NOT NULL,
  `start_date` date DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `projects`
--

CREATE TABLE `projects` (
  `id` bigint(20) NOT NULL,
  `budget` double NOT NULL,
  `completion_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `expected_date` date DEFAULT NULL,
  `project_name` varchar(255) NOT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `projects`
--

INSERT INTO `projects` (`id`, `budget`, `completion_date`, `end_date`, `expected_date`, `project_name`, `start_date`, `status`, `customer_id`, `owner_id`) VALUES
(1, 500000, NULL, '2024-07-23', '2024-07-22', '123 progetto', '2024-07-24', 'da iniziare', NULL, 1),
(6, 3131133, NULL, NULL, NULL, 'progetto2', NULL, '22', 1, 2),
(7, 300000, NULL, NULL, NULL, 'progettotest', NULL, '123123', 1, 2),
(8, 1231313, NULL, NULL, NULL, 'Alitalia Web Service', '2024-07-30', 'in corso', 1, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `project_users`
--

CREATE TABLE `project_users` (
  `project_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `project_users`
--

INSERT INTO `project_users` (`project_id`, `user_id`) VALUES
(1, 1),
(1, 2),
(6, 1),
(6, 3),
(8, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `roles`
--

INSERT INTO `roles` (`id`, `role`, `is_admin`) VALUES
(1, 'consulente', b'0'),
(2, 'manager', b'1');

-- --------------------------------------------------------

--
-- Struttura della tabella `status`
--

CREATE TABLE `status` (
  `id` bigint(20) NOT NULL,
  `color` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `status`
--

INSERT INTO `status` (`id`, `color`, `status`) VALUES
(1, 'red', 'da fare'),
(2, 'green', 'completata');

-- --------------------------------------------------------

--
-- Struttura della tabella `tasks`
--

CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL,
  `completion_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `owner_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL,
  `task_desc` varchar(255) NOT NULL,
  `task_name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `tasks`
--

INSERT INTO `tasks` (`id`, `completion_date`, `end_date`, `owner_id`, `project_id`, `start_date`, `task_desc`, `task_name`, `user_id`, `status_id`) VALUES
(56, NULL, '2024-08-02', 2, 8, '2024-07-31', 'faaf', 'asdasd', 3, 1),
(58, NULL, NULL, 2, 8, '2024-08-01', 'dio', 'dio', 3, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `tokens`
--

CREATE TABLE `tokens` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `tokens`
--

INSERT INTO `tokens` (`id`, `created_date`, `token`, `user_id`, `is_admin`) VALUES
(103, '2024-08-01 10:24:51.000000', 'f404ef36-67c2-45ce-a81d-055d152ce3b8', 2, b'1'),
(104, '2024-08-01 10:34:44.000000', '3c04119f-a7aa-43a4-ae8f-e7231884471e', 5, b'0');

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome_completo` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`id`, `email`, `nome_completo`, `password`, `role_id`) VALUES
(1, 'user', 'user', '1', 1),
(2, 'admin', 'admin', 'admin', 2),
(3, 'mail3', 'carlo cracco', '123123123', 1),
(4, 'afsdasd@b.c', 'luca luca', '12344564', NULL),
(5, 'luca@barreca.com', 'luca luca', 'asdasdasd', 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `log_entries`
--
ALTER TABLE `log_entries`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `milestones`
--
ALTER TABLE `milestones`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `minitasks`
--
ALTER TABLE `minitasks`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4rpwuljjwr5rygq9gwx36q8cj` (`customer_id`),
  ADD KEY `FKmueqy6cpcwpfl8gnnag4idjt9` (`owner_id`);

--
-- Indici per le tabelle `project_users`
--
ALTER TABLE `project_users`
  ADD PRIMARY KEY (`project_id`,`user_id`),
  ADD KEY `FK8w55cu3qmg4yo0vy0b8e3ivk4` (`user_id`);

--
-- Indici per le tabelle `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfmlm4rxoa19247blv9g96eacd` (`status_id`);

--
-- Indici per le tabelle `tokens`
--
ALTER TABLE `tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKna3v9f8s7ucnj16tylrs822qj` (`token`);

--
-- Indici per le tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `log_entries`
--
ALTER TABLE `log_entries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `milestones`
--
ALTER TABLE `milestones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `minitasks`
--
ALTER TABLE `minitasks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `projects`
--
ALTER TABLE `projects`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `status`
--
ALTER TABLE `status`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT per la tabella `tokens`
--
ALTER TABLE `tokens`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT per la tabella `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `FK4rpwuljjwr5rygq9gwx36q8cj` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `FKmueqy6cpcwpfl8gnnag4idjt9` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);

--
-- Limiti per la tabella `project_users`
--
ALTER TABLE `project_users`
  ADD CONSTRAINT `FK8w55cu3qmg4yo0vy0b8e3ivk4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKn2d9w5xxgord5j4k2963p8o1g` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`);

--
-- Limiti per la tabella `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `FKfmlm4rxoa19247blv9g96eacd` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`);

--
-- Limiti per la tabella `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
