-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Ago 25, 2024 alle 16:48
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
  `owner_id` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL,
  `projectid` bigint(20) NOT NULL,
  `mile_total` int(11) DEFAULT NULL,
  `mile_value` int(11) DEFAULT NULL,
  `mile_icon` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `milestones`
--

INSERT INTO `milestones` (`id`, `completion_date`, `end_date`, `mile_desc`, `mile_name`, `owner_id`, `start_date`, `projectid`, `mile_total`, `mile_value`, `mile_icon`) VALUES
(1, NULL, NULL, 'mile desc 1', 'mile 1', 1, NULL, 6, 670, 320, 'fa-regular fa-circle-check'),
(2, NULL, NULL, 'DESCRIZIONE MILESTONE 2', 'MILESTONE 2', 2, NULL, 8, 213123, 0, 'fa fa-briefcase'),
(3, '2024-08-25', NULL, 'DESCRIZIONE MILESTONE 3 ', 'MILESTONE 3', 2, NULL, 8, 120, 120, 'fa-solid fa-bars-progress'),
(4, NULL, NULL, 'mile desc caca', 'mile caca', 2, '2024-08-21', 1, 0, 0, 'fa-regular fa-circle-check'),
(5, NULL, '2024-12-31', 'mile desc 4', 'mile 45', 2, NULL, 6, 0, 0, 'fa-solid fa-calendar-check'),
(7, NULL, '2024-12-30', 'mile 44124', 'milestone 4123', 2, NULL, 8, 0, 0, 'fa-solid fa-star'),
(8, NULL, '2027-05-05', 'mila mile', 'mile mila', 2, NULL, 6, 0, 0, 'fa-solid fa-bullseye'),
(9, NULL, '2027-04-04', 'al culo', 'caca', 2, NULL, 6, 0, 0, 'fa-solid fa-calendar-check'),
(10, NULL, '2027-04-04', 'al culo', 'caca', 2, NULL, 8, 0, 0, 'fa-solid fa-calendar-check');

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
  `owner_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `projects`
--

INSERT INTO `projects` (`id`, `budget`, `completion_date`, `end_date`, `expected_date`, `project_name`, `start_date`, `status`, `customer_id`, `owner_id`, `description`, `total`, `value`) VALUES
(1, 500000, '2024-08-21', '2024-07-23', '2024-07-22', '123 progetto', '2024-07-24', 'Completato', 1, 1, 'Progetto \"EcoWave\": sviluppiamo soluzioni innovative per la riduzione dell\'impronta ecologica attraverso tecnologie sostenibili. Il nostro obiettivo è migliorare l\'efficienza energetica a livello globale.', 0, 0),
(6, 3131133, NULL, NULL, NULL, 'progetto2', NULL, 'Completato', 1, 2, 'Progetto \"EcoWave\": sviluppiamo soluzioni innovative per la riduzione dell\'impronta ecologica attraverso tecnologie sostenibili. Il nostro obiettivo è migliorare l\'efficienza energetica a livello globale.', 670, 320),
(7, 300000, '2024-08-21', NULL, NULL, 'progettotest', NULL, 'Completato', 1, 2, 'Progetto \"EcoWave\": sviluppiamo soluzioni innovative per la riduzione dell\'impronta ecologica attraverso tecnologie sostenibili. Il nostro obiettivo è migliorare l\'efficienza energetica a livello globale.', 0, 0),
(8, 1231313, NULL, NULL, NULL, 'Alitalia Web Service', '2024-07-30', 'Completato', 1, 2, 'Progetto \"EcoWave\": sviluppiamo soluzioni innovative per la riduzione dell\'impronta ecologica attraverso tecnologie sostenibili. Il nostro obiettivo è migliorare l\'efficienza energetica a livello globale.', 213243, 120),
(9, 123, NULL, '2024-01-01', '2024-01-01', '123', NULL, '1234', 1, 2, NULL, NULL, NULL);

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
(8, 1),
(9, 1),
(9, 2),
(9, 3);

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
(1, 'red', 'To Do'),
(2, 'gold', 'In corso'),
(3, 'blue', 'Dev OK'),
(4, 'purple', 'Testing'),
(5, 'green', 'Completata');

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
  `status_id` bigint(20) DEFAULT NULL,
  `milestone_id` bigint(20) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `milestoneid` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `tasks`
--

INSERT INTO `tasks` (`id`, `completion_date`, `end_date`, `owner_id`, `project_id`, `start_date`, `task_desc`, `task_name`, `user_id`, `status_id`, `milestone_id`, `value`, `milestoneid`) VALUES
(2, '2024-08-23', '2022-02-01', 2, 8, '2024-08-12', 'asdasdsad', 'asdasd', 1, 2, 2, 213123, NULL),
(9, '2024-08-23', '2024-12-30', 2, 8, '2024-08-19', 'nuova', 'nuova task', 1, 5, 3, 120, NULL),
(11, '2024-08-23', '2024-12-31', 2, 6, '2024-08-19', 'nuova', 'nuova', 1, 5, 1, 120, NULL),
(14, '2024-08-23', '2024-12-30', 2, 6, '2024-08-19', 'nuova task test desc', 'nuova task test', 1, 5, 1, 200, NULL),
(16, NULL, '2025-01-01', 2, 6, '2024-08-23', '123123', 'nuova task', 3, 1, 1, 150, NULL),
(18, NULL, '2024-08-25', 2, 6, '2024-08-24', 'nuova taskona', 'nuova task', 3, 3, 1, 200, NULL);

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
(104, '2024-08-01 10:34:44.000000', '3c04119f-a7aa-43a4-ae8f-e7231884471e', 5, b'0'),
(105, '2024-08-01 10:50:40.000000', 'cea843b0-b3d6-412b-a2cf-cb2c88a71e4a', 2, b'1'),
(106, '2024-08-01 10:52:56.000000', '98d8ffae-bc8b-44cd-a6d7-80ab262a546c', 1, b'0'),
(107, '2024-08-01 10:54:56.000000', 'b8c34a5f-8422-41ae-8f03-caecfb940f3f', 2, b'1'),
(108, '2024-08-01 10:56:19.000000', 'd8cda253-5c81-42af-8daf-bc0208eb04e0', 1, b'0'),
(109, '2024-08-01 11:14:53.000000', '6a5ffd15-542e-4d53-94f4-9641b92e5059', 1, b'0'),
(110, '2024-08-01 11:48:32.000000', '0bf4d0fb-e02c-43f8-8599-791f8f8c2217', 1, b'0'),
(111, '2024-08-01 12:45:58.000000', 'd220db03-7f48-42a9-a320-fb0bee6ce39c', 2, b'1'),
(112, '2024-08-02 09:40:30.000000', 'fc08a8ec-3b91-4b2f-a7ed-c02f99dbe98b', 1, b'0'),
(113, '2024-08-03 15:50:54.000000', 'e727ea75-073c-4ee5-a9e7-db8aa1baa2b0', 2, b'1'),
(114, '2024-08-06 22:06:45.000000', '6b661e39-e06f-4c93-ae02-5b4736fb814e', 2, b'1'),
(115, '2024-08-06 23:17:55.000000', '8ab2eac0-449d-4d5a-8c9a-6ea0642d0545', 1, b'0'),
(116, '2024-08-07 09:42:19.000000', 'd730f626-20d3-49a8-9816-2b705287aa62', 2, b'1'),
(117, '2024-08-07 10:11:20.000000', 'ff88b941-694a-4494-bd40-acf1445a11c3', 2, b'1'),
(118, '2024-08-12 12:15:20.000000', 'b1da13ea-8fbb-42f0-9b9f-aec4fff10001', 1, b'0'),
(119, '2024-08-12 12:29:51.000000', '8a540249-ef9d-4ef3-aaab-0d54405cb7cd', 2, b'1'),
(120, '2024-08-12 13:07:35.000000', '3a4719dd-6517-4178-9ada-6321a33d569f', 1, b'0'),
(121, '2024-08-12 13:07:58.000000', '132d00e2-006e-4408-b123-39bc0967821a', 2, b'1'),
(122, '2024-08-12 13:19:58.000000', '53087556-e548-45ed-a8ef-406892834477', 1, b'0'),
(123, '2024-08-12 13:21:38.000000', 'ef22b982-cc41-4b1f-b2bf-2c7edc305e6b', 1, b'0'),
(124, '2024-08-19 13:25:44.000000', '65ceb9d6-bead-48e2-8720-76eadf286cbb', 2, b'1'),
(125, '2024-08-19 13:31:18.000000', 'a4f8fd29-4536-4cc4-b0a5-19276dca8b95', 1, b'0'),
(126, '2024-08-19 14:43:25.000000', 'b43683f0-ab6d-4648-b9d4-938fda5bee6f', 2, b'1'),
(127, '2024-08-19 16:06:03.000000', '2900a098-1fff-4956-ba08-0db76ec825f9', 2, b'1'),
(128, '2024-08-19 16:14:07.000000', '33567fa9-018b-4812-ae51-ca2c42297662', 2, b'1'),
(129, '2024-08-19 16:24:25.000000', '2dc16058-359e-47d3-a152-ebc59719afd3', 1, b'0'),
(130, '2024-08-19 16:29:30.000000', 'b803b192-1a63-4fb1-8b15-1aa045d3b7f2', 2, b'1'),
(131, '2024-08-19 16:54:06.000000', '3c1db27e-6f5a-4a4a-b68e-c542e4c82adb', 2, b'1'),
(132, '2024-08-19 16:54:51.000000', '9affc52c-1500-4053-a186-7ed087a24e1e', 1, b'0'),
(133, '2024-08-23 15:16:52.000000', '0bdb7c93-f6aa-478c-91f8-0ca21a2e58b4', 2, b'1'),
(134, '2024-08-23 15:22:19.000000', '83727029-b415-4ada-8dbd-2a8e247adeff', 1, b'0'),
(135, '2024-08-23 15:45:13.000000', '686d0504-c7f0-4455-8b75-5a694406a68e', 2, b'1'),
(136, '2024-08-23 15:45:42.000000', 'd4f2ecc7-8553-4346-bc5b-e917575894e1', 1, b'0'),
(137, '2024-08-23 18:16:58.000000', '1a3691c7-0435-483b-9531-7b475bf87a6a', 3, b'0'),
(138, '2024-08-23 18:38:42.000000', 'a90e86ae-0fc8-48b0-a011-a1edce864c46', 1, b'0'),
(139, '2024-08-24 14:46:00.000000', 'cc279c3c-9d10-48b3-9a22-78bdb048238e', 2, b'1');

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
(2, 'admin', 'Mario Rossi', 'admin', 2),
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
  ADD KEY `FKfmlm4rxoa19247blv9g96eacd` (`status_id`),
  ADD KEY `FKm11xypimt5ea052crm4k411ie` (`milestone_id`),
  ADD KEY `FKdup4ormnmr3jki7npqxubye32` (`milestoneid`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `minitasks`
--
ALTER TABLE `minitasks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `projects`
--
ALTER TABLE `projects`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT per la tabella `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `status`
--
ALTER TABLE `status`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT per la tabella `tokens`
--
ALTER TABLE `tokens`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=140;

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
  ADD CONSTRAINT `FKdup4ormnmr3jki7npqxubye32` FOREIGN KEY (`milestoneid`) REFERENCES `milestones` (`id`),
  ADD CONSTRAINT `FKfmlm4rxoa19247blv9g96eacd` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  ADD CONSTRAINT `FKm11xypimt5ea052crm4k411ie` FOREIGN KEY (`milestone_id`) REFERENCES `milestones` (`id`);

--
-- Limiti per la tabella `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
