-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Feb 2020 um 21:50
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `homestream`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `artist`
--

CREATE TABLE `artist` (
  `id` bigint(20) NOT NULL,
  `bday` date DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `film`
--

CREATE TABLE `film` (
  `length` time DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `film`
--

INSERT INTO `film` (`length`, `id`) VALUES
(NULL, 4),
(NULL, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(8),
(8);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `image`
--

CREATE TABLE `image` (
  `height` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `image`
--

INSERT INTO `image` (`height`, `width`, `id`) VALUES
(0, 0, 1),
(0, 0, 2),
(0, 0, 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `media`
--

CREATE TABLE `media` (
  `id` bigint(20) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `last_streamed_on` date DEFAULT NULL,
  `last_updated` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `thumbnail_name` varchar(255) DEFAULT NULL,
  `uploaded_on` date DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `media`
--

INSERT INTO `media` (`id`, `file_name`, `file_size`, `last_streamed_on`, `last_updated`, `name`, `release_date`, `tags`, `thumbnail_name`, `uploaded_on`, `user_id`) VALUES
(2, 'GreatIdioticAardwolf-size_restricted.gif', 4661705, NULL, NULL, 'Venom', NULL, 'null', 'GreatIdioticAardwolf-size_restricted.gif', NULL, NULL),
(7, 'TheFatRat - Monody (feat. Laura Brehm).mp3', 6996742, NULL, '2020-02-03', 'Monody', NULL, 'null', 'defettjeratte_thumbnail.jpg', '2020-02-03', NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `music`
--

CREATE TABLE `music` (
  `length` time DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `music`
--

INSERT INTO `music` (`length`, `id`) VALUES
(NULL, 3),
(NULL, 7);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `playlist`
--

CREATE TABLE `playlist` (
  `id` bigint(20) NOT NULL,
  `created_on` date DEFAULT NULL,
  `last_streamed` date DEFAULT NULL,
  `last_updated` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `__film_main_actor_table`
--

CREATE TABLE `__film_main_actor_table` (
  `artist_id` bigint(20) NOT NULL,
  `film_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `__film_side_actor_table`
--

CREATE TABLE `__film_side_actor_table` (
  `artist_id` bigint(20) NOT NULL,
  `film_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `__image_artist_role`
--

CREATE TABLE `__image_artist_role` (
  `artist_id` bigint(20) NOT NULL,
  `image_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `__media_role_table`
--

CREATE TABLE `__media_role_table` (
  `role_id` bigint(20) NOT NULL,
  `media_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `__music_artist_table`
--

CREATE TABLE `__music_artist_table` (
  `artist_id` bigint(20) NOT NULL,
  `music_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `__playlist_media_table`
--

CREATE TABLE `__playlist_media_table` (
  `playlist_id` bigint(20) NOT NULL,
  `media_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK64mp2rjcwcgqpnu3weakxvwq0` (`user_id`);

--
-- Indizes für die Tabelle `music`
--
ALTER TABLE `music`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- Indizes für die Tabelle `__film_main_actor_table`
--
ALTER TABLE `__film_main_actor_table`
  ADD KEY `FKprs9dh9c5cc0clg63too4n88a` (`film_id`),
  ADD KEY `FKjuxk3grpj603bh1uv4qp7xwfa` (`artist_id`);

--
-- Indizes für die Tabelle `__film_side_actor_table`
--
ALTER TABLE `__film_side_actor_table`
  ADD KEY `FKmfcjw0pl8umrji75tdpa1li4l` (`film_id`),
  ADD KEY `FK59htxjdpklqfb7t711hw0vx20` (`artist_id`);

--
-- Indizes für die Tabelle `__image_artist_role`
--
ALTER TABLE `__image_artist_role`
  ADD KEY `FKpnevdd5r0gnqgta04mu4eiieb` (`image_id`),
  ADD KEY `FK783bxhnp2q8iyt321b0wv1oy` (`artist_id`);

--
-- Indizes für die Tabelle `__media_role_table`
--
ALTER TABLE `__media_role_table`
  ADD KEY `FK7jdd2vq2jau7rdw660jqvlxs7` (`media_id`),
  ADD KEY `FKjf3jdlw04qd2n6o8b2s4hqs24` (`role_id`);

--
-- Indizes für die Tabelle `__music_artist_table`
--
ALTER TABLE `__music_artist_table`
  ADD KEY `FK67shlj1yvdm0ikstnjpcgn0ph` (`music_id`),
  ADD KEY `FKmkwp532f7meh0mrlh6w97xf57` (`artist_id`);

--
-- Indizes für die Tabelle `__playlist_media_table`
--
ALTER TABLE `__playlist_media_table`
  ADD KEY `FKol8pslbucsd4c8pgxxsifuh3h` (`media_id`),
  ADD KEY `FK3qn89g3nfyc07j9v0uy7lninw` (`playlist_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `artist`
--
ALTER TABLE `artist`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
