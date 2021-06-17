-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           10.5.10-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour mariadbcnam
CREATE DATABASE IF NOT EXISTS `mariadbcnam` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mariadbcnam`;

-- Listage de la structure de la table mariadbcnam. categorie
CREATE TABLE IF NOT EXISTS `categorie` (
  `id_categorie` bigint(20) NOT NULL,
  `nom_categorie` varchar(64) NOT NULL,
  PRIMARY KEY (`id_categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.categorie : ~8 rows (environ)
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` (`id_categorie`, `nom_categorie`) VALUES
	(3, 'Viandes rouges'),
	(4, 'Poissons'),
	(5, 'Légumes'),
	(6, 'Sauces'),
	(967, 'Viandes blanches'),
	(1101, 'Plats préparés'),
	(10015, 'Fromages'),
	(10019, 'Salades'),
	(11034, 'Fruits');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. categorie_plat
CREATE TABLE IF NOT EXISTS `categorie_plat` (
  `id_categorie` bigint(20) NOT NULL,
  `nom_categorie` varchar(64) NOT NULL,
  `tva_categorie` float NOT NULL,
  PRIMARY KEY (`id_categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.categorie_plat : ~15 rows (environ)
/*!40000 ALTER TABLE `categorie_plat` DISABLE KEYS */;
INSERT INTO `categorie_plat` (`id_categorie`, `nom_categorie`, `tva_categorie`) VALUES
	(2, 'Entrées', 10),
	(3, 'Salades', 10),
	(4, 'Viandes', 10),
	(5, 'Poissons', 10),
	(6, 'Desserts', 10),
	(8, 'Apéritifs', 20),
	(9, 'Digestifs', 20),
	(10, 'Eaux', 10),
	(11, 'Boissons sans alcools', 10),
	(84, 'Vins Blancs', 20),
	(85, 'Vins Rosés', 20),
	(86, 'Vins Rouges', 20),
	(87, 'Champagne', 20),
	(88, 'Bières', 20),
	(89, 'Boissons chaudes', 10);
/*!40000 ALTER TABLE `categorie_plat` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. frigo
CREATE TABLE IF NOT EXISTS `frigo` (
  `id_frigo` bigint(20) NOT NULL,
  `desc_frigo` varchar(64) DEFAULT NULL,
  `nom_frigo` varchar(30) NOT NULL,
  `temp_maxi` float DEFAULT NULL,
  `temp_mini` float DEFAULT NULL,
  PRIMARY KEY (`id_frigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.frigo : ~4 rows (environ)
/*!40000 ALTER TABLE `frigo` DISABLE KEYS */;
INSERT INTO `frigo` (`id_frigo`, `desc_frigo`, `nom_frigo`, `temp_maxi`, `temp_mini`) VALUES
	(369, 'Frigo cuisine', 'Frigo 1', 4, 2.8),
	(370, 'Congélateur bac', 'Frigo 2', 4.5, 3),
	(383, 'Grand Réfrigérateur', 'Frigo 3', 5.5, 3.5),
	(896, 'Produits frais', 'Frigo 4', 4.5, 2.5);
/*!40000 ALTER TABLE `frigo` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) unsigned NOT NULL,
  `cycle_option` tinyint(1) unsigned NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB SEQUENCE=1;

-- Listage des données de la table mariadbcnam.hibernate_sequence : ~1 rows (environ)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
	(12001, 1, 9223372036854775806, 1, 1, 1000, 0, 0);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. note
CREATE TABLE IF NOT EXISTS `note` (
  `id_note` bigint(20) NOT NULL,
  `nb_couvert_note` int(11) NOT NULL,
  `date_note` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_utilisateur` bigint(20) DEFAULT NULL,
  `id_table` bigint(20) DEFAULT NULL,
  `note_reglee` bit(1) DEFAULT NULL,
  `serveur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_note`),
  KEY `FK36tqo5th8abnwthnnkshoh9rq` (`id_utilisateur`),
  KEY `FKouipb22fnltrg3bhv0ys9pp78` (`id_table`),
  CONSTRAINT `FK36tqo5th8abnwthnnkshoh9rq` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`),
  CONSTRAINT `FKouipb22fnltrg3bhv0ys9pp78` FOREIGN KEY (`id_table`) REFERENCES `table_restau` (`id_table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.note : ~12 rows (environ)
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` (`id_note`, `nb_couvert_note`, `date_note`, `id_utilisateur`, `id_table`, `note_reglee`, `serveur`) VALUES
	(3016, 4, '2021-06-02 11:13:01', NULL, 23, b'1', 'Solo Han'),
	(3017, 6, '2021-06-02 11:23:18', NULL, 23, b'1', 'Solo Han'),
	(3018, 2, '2021-06-15 12:53:12', NULL, 29, b'1', 'Solo Han'),
	(3019, 2, '2021-06-02 11:37:01', NULL, 27, b'1', 'Solo Han'),
	(3020, 1, '2021-06-02 16:13:55', NULL, 28, b'1', 'Solo Han'),
	(3021, 3, '2021-06-02 16:24:38', NULL, 29, b'1', 'Solo Han'),
	(3022, 9, '2021-06-02 16:35:28', NULL, 26, b'0', 'Solo Han'),
	(3032, 4, '2021-06-02 16:51:57', NULL, 29, b'1', 'Solo Han'),
	(4010, 5, '2021-06-10 12:44:07', NULL, 24, b'1', 'Solo Han'),
	(4011, 5, '2021-06-03 11:43:00', NULL, 23, b'1', 'Solo Han'),
	(5001, 6, '2021-06-04 12:49:00', NULL, 25, b'1', 'Solo Han'),
	(8017, 4, '2021-06-08 17:11:54', NULL, 22, b'1', 'Solo Han'),
	(8018, 4, '2021-06-08 17:12:39', NULL, 30, b'1', 'Solo Han'),
	(10026, 5, '2021-06-10 12:41:58', NULL, 25, b'1', 'Solo Han'),
	(11033, 6, '2021-06-15 12:36:13', NULL, 23, b'0', 'Solo Han');
/*!40000 ALTER TABLE `note` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. plat
CREATE TABLE IF NOT EXISTS `plat` (
  `id_plat` bigint(20) NOT NULL,
  `decr_plat` varchar(64) NOT NULL,
  `prix_plat` float NOT NULL,
  `id_categorie` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_plat`),
  KEY `FK171slrsv74ni79b51jkc0kpgc` (`id_categorie`),
  CONSTRAINT `FK171slrsv74ni79b51jkc0kpgc` FOREIGN KEY (`id_categorie`) REFERENCES `categorie_plat` (`id_categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.plat : ~90 rows (environ)
/*!40000 ALTER TABLE `plat` DISABLE KEYS */;
INSERT INTO `plat` (`id_plat`, `decr_plat`, `prix_plat`, `id_categorie`) VALUES
	(41, 'Salade gourmande', 15.9, 3),
	(42, 'Salade Caesar', 16.9, 3),
	(43, 'Salade italienne', 15.5, 3),
	(44, 'Salade périgourdine', 16.9, 3),
	(45, 'Saumon fumé maison', 14.9, 2),
	(46, 'Carpaccios de St-jacques', 12.9, 2),
	(47, 'Tempura de Gambas', 11.9, 2),
	(48, 'Camembert rôti au miel', 13.9, 2),
	(49, 'Fois gras de canard maison', 14, 2),
	(50, 'Cassolette de fruits de mer', 13.9, 2),
	(51, 'tataki de thon au sésame', 11.5, 2),
	(52, 'Crevettes roses poêlées au saté', 12.9, 2),
	(53, 'Tartare de boeuf', 17.2, 4),
	(54, 'Tartare César poêlé aller-retour', 17.5, 4),
	(55, 'Carpaccio de boeuf', 13.9, 4),
	(56, 'Entrecôte grillée', 23.9, 4),
	(57, 'Ongle de boeuf poêlé', 17.9, 4),
	(58, 'Magret de canard français', 17.9, 4),
	(59, 'Aiguillette de volaille', 16.9, 4),
	(60, 'Wok de poulet', 17.9, 4),
	(61, 'Choucroute de la  mer', 18.9, 5),
	(62, 'Filet de bar au beurre Nantais', 21.9, 5),
	(63, 'Noix de Saint-Jacques au safran', 23.9, 5),
	(64, 'Dos de cabillaud', 18.9, 5),
	(65, 'Gambas rôties au saté', 21.5, 5),
	(66, 'Steak de thon façon thaï', 19.5, 5),
	(67, 'Assiette de fromages sur lit de Mesclun', 8.5, 6),
	(68, 'Entremet caramel au sel de Guérande', 6.5, 6),
	(69, 'Tarte tatin', 7, 6),
	(70, 'Mousse au chocolat', 7, 6),
	(71, 'Crème brulée vanillée', 7, 6),
	(72, 'Profiteroles au chocolat', 7.5, 6),
	(73, 'Fondant au chocolat', 7, 6),
	(74, 'Café gourmand', 8, 6),
	(75, 'Glace 1 boule', 3.6, 6),
	(76, 'Glace 2 boules', 5.6, 6),
	(77, 'Glace 3 boules', 6.6, 6),
	(78, 'Coupe Chocolat liégeois', 7.5, 6),
	(79, 'Coupe Café liégeois', 7.5, 6),
	(80, 'Coupe Dame blanche', 7.5, 6),
	(81, 'Coupe Coconuts', 7.5, 6),
	(82, 'Coupe Général', 9.5, 6),
	(83, 'Coupe Colonel', 9.5, 6),
	(90, 'Muscadet Les Lozangères 37.5cl', 13, 84),
	(91, 'Muscadet Les Lozangères 75cl', 26, 84),
	(92, 'Sauvignon Les Genêts 37.5cl', 14, 84),
	(93, 'Sauvignon Les Genêts 75cl', 28, 84),
	(94, 'Chardonnay Domaine La Grave 75cl', 28, 84),
	(95, 'San Pieru 75cl', 25, 85),
	(96, 'Mareuil Collection 37.5cl', 14, 85),
	(97, 'Mareuil Collection 75cl', 28, 85),
	(98, 'Côtes de Provence 75cl', 28, 85),
	(99, 'Saumur Champigny Cabriole 75cl', 28, 86),
	(101, 'St-Nicolas de Bourgueil Le Fresne 75cl', 28, 86),
	(102, 'St-Nicolas de Bourgueil Le Fresne 37.5cl', 13.8, 86),
	(103, 'Côtes du Rhône Roc St Joseph 37.5cl', 13.8, 86),
	(104, 'Côtes du Rhône Roc St Joseph 75cl', 28, 86),
	(105, 'Bourgogne Vieilles Vignes 75cl', 45, 86),
	(106, 'Nicolas Feuillatte 75cl', 75, 87),
	(107, 'Coupe de champagne', 10, 87),
	(108, 'Sangria maison', 5.8, 8),
	(109, 'Kir pétillant', 5, 8),
	(110, 'Coupe de pétillant', 4.5, 8),
	(111, 'Spritz', 10, 8),
	(112, 'Mojito', 10, 8),
	(113, 'Martini', 5, 8),
	(114, 'Porto', 5, 8),
	(115, 'Pastis 51', 4.5, 8),
	(116, 'Whisky', 9, 8),
	(117, 'Rhum ambré', 9.5, 9),
	(118, 'Menthe Pastille', 7.5, 9),
	(119, 'Irish Coffee', 10, 9),
	(120, 'Carlsberg', 3.8, 88),
	(121, 'Desperados', 5.8, 88),
	(122, 'Pelforth Brune', 5.8, 88),
	(123, 'Vittel 25cl', 2.6, 10),
	(124, 'Vittel', 5.8, 10),
	(125, 'San Pellegrino 50cl', 4.3, 10),
	(126, 'San Pellegrino', 6.2, 10),
	(127, 'Perrier', 3.8, 10),
	(128, 'Orangina', 3.8, 11),
	(129, 'Pepsi', 3.8, 11),
	(130, 'Sirop à l\'eau', 2.5, 11),
	(131, 'Jus de fruits', 3.8, 11),
	(132, 'Café', 2, 89),
	(133, 'Décaféiné', 2.2, 89),
	(134, 'Grand café', 3.8, 89),
	(135, 'Café crème', 2.5, 89),
	(136, 'Chocolat chaud', 3.8, 89),
	(137, 'Thé', 3.5, 89);
/*!40000 ALTER TABLE `plat` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. plat_note
CREATE TABLE IF NOT EXISTS `plat_note` (
  `id_plat` bigint(20) NOT NULL,
  `id_note` bigint(20) NOT NULL,
  KEY `FKi5oca1jpblpm6tnnkx07fakpk` (`id_note`),
  KEY `FK59p84i1ctkwoukvxfasjqw01r` (`id_plat`),
  CONSTRAINT `FK59p84i1ctkwoukvxfasjqw01r` FOREIGN KEY (`id_plat`) REFERENCES `plat` (`id_plat`),
  CONSTRAINT `FKi5oca1jpblpm6tnnkx07fakpk` FOREIGN KEY (`id_note`) REFERENCES `note` (`id_note`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.plat_note : ~171 rows (environ)
/*!40000 ALTER TABLE `plat_note` DISABLE KEYS */;
INSERT INTO `plat_note` (`id_plat`, `id_note`) VALUES
	(117, 5001),
	(117, 5001),
	(61, 5001),
	(61, 5001),
	(120, 5001),
	(130, 4011),
	(62, 3021),
	(62, 3021),
	(80, 3021),
	(91, 3021),
	(91, 3021),
	(99, 3017),
	(99, 3017),
	(99, 3017),
	(67, 3017),
	(67, 3017),
	(67, 3017),
	(67, 3021),
	(67, 3021),
	(67, 3017),
	(118, 3017),
	(118, 3017),
	(118, 3017),
	(118, 3021),
	(118, 3021),
	(118, 3021),
	(118, 3021),
	(118, 5001),
	(118, 5001),
	(118, 3032),
	(119, 3021),
	(119, 3032),
	(127, 3032),
	(106, 3032),
	(129, 3016),
	(129, 3016),
	(129, 3021),
	(129, 8017),
	(109, 4011),
	(109, 8017),
	(112, 4011),
	(112, 3021),
	(112, 8017),
	(54, 3017),
	(54, 8018),
	(128, 4011),
	(128, 3016),
	(128, 4011),
	(64, 5001),
	(64, 3021),
	(64, 5001),
	(41, 8017),
	(41, 5001),
	(41, 5001),
	(41, 5001),
	(41, 3032),
	(41, 3019),
	(103, 3020),
	(42, 3019),
	(42, 10026),
	(53, 3017),
	(53, 3017),
	(53, 10026),
	(63, 10026),
	(66, 10026),
	(74, 3020),
	(74, 10026),
	(113, 8017),
	(113, 10026),
	(114, 3021),
	(114, 10026),
	(114, 10026),
	(126, 3032),
	(126, 10026),
	(92, 10026),
	(102, 10026),
	(134, 10026),
	(137, 10026),
	(43, 8017),
	(43, 3017),
	(43, 3017),
	(43, 3017),
	(43, 3017),
	(43, 5001),
	(43, 5001),
	(43, 3019),
	(43, 3021),
	(43, 3032),
	(43, 4010),
	(50, 4011),
	(50, 10026),
	(50, 3019),
	(50, 3021),
	(50, 3032),
	(50, 3032),
	(50, 4010),
	(57, 8018),
	(57, 10026),
	(57, 4010),
	(60, 3016),
	(60, 3016),
	(60, 4011),
	(60, 4010),
	(59, 4010),
	(71, 10026),
	(71, 4010),
	(76, 4010),
	(81, 4010),
	(81, 4010),
	(125, 3018),
	(68, 3017),
	(68, 4010),
	(68, 10026),
	(68, 10026),
	(68, 10026),
	(68, 3021),
	(68, 11033),
	(75, 11033),
	(124, 3032),
	(124, 3018),
	(124, 11033),
	(45, 11033),
	(45, 3021),
	(45, 3032),
	(45, 11033),
	(47, 4011),
	(47, 11033),
	(55, 3016),
	(55, 3016),
	(55, 11033),
	(55, 4010),
	(55, 10026),
	(55, 11033),
	(56, 3017),
	(56, 4011),
	(56, 3020),
	(56, 8018),
	(56, 11033),
	(58, 11033),
	(108, 11033),
	(108, 10026),
	(108, 3021),
	(108, 11033),
	(110, 8017),
	(110, 4011),
	(110, 11033),
	(110, 10026),
	(110, 3022),
	(110, 3022),
	(110, 11033),
	(101, 11033),
	(133, 11033),
	(132, 11033),
	(132, 11033),
	(132, 10026),
	(132, 10026),
	(132, 10026),
	(132, 11033),
	(46, 4010),
	(46, 10026),
	(46, 3020),
	(46, 3022),
	(48, 4011),
	(48, 11033),
	(48, 10026),
	(48, 3022),
	(48, 3019),
	(48, 3032),
	(48, 3022),
	(51, 3019),
	(51, 3022);
/*!40000 ALTER TABLE `plat_note` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. produit
CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` bigint(20) NOT NULL,
  `date_creation` date NOT NULL,
  `duree_conservation` int(11) NOT NULL,
  `nom_produit` varchar(64) NOT NULL,
  `utilisateur` varchar(255) DEFAULT NULL,
  `id_categorie` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `FKlgcxfrlljt10cdwx730b4tujf` (`id_categorie`),
  CONSTRAINT `FKlgcxfrlljt10cdwx730b4tujf` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.produit : ~23 rows (environ)
/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
INSERT INTO `produit` (`id_produit`, `date_creation`, `duree_conservation`, `nom_produit`, `utilisateur`, `id_categorie`) VALUES
	(7, '2021-03-22', 1, 'Steaks hachés', 'Rabiller Guillaume', 3),
	(8, '2021-03-22', 5, 'Sauce tartare', 'Rabiller Guillaume', 6),
	(26, '2021-03-23', 2, 'Cabillaud', 'Rabiller Guillaume', 4),
	(27, '2021-03-23', 3, 'Purée de carotte', 'Rabiller Guillaume', 5),
	(45, '2021-03-24', 1, 'Dorade', 'Rabiller Guillaume', 4),
	(46, '2021-03-24', 1, 'Saumon', 'Rabiller Guillaume', 4),
	(47, '2021-03-24', 3, 'Rondelles de calamars', 'Rabiller Guillaume', 4),
	(48, '2021-03-24', 2, 'Sauce béarnaise', 'Rabiller Guillaume', 6),
	(49, '2021-03-24', 3, 'Sauce pesto', 'Rabiller Guillaume', 6),
	(50, '2021-03-24', 4, 'Poulet', 'Rabiller Guillaume', 967),
	(102, '2021-04-05', 2, 'Purée pomme de terre', 'Durand Francois', 5),
	(106, '2021-04-05', 3, 'Haricots verts', 'Rabiller Guillaume', 5),
	(801, '2021-04-30', 3, 'Magret de canard', 'Rabiller Guillaume', 3),
	(1102, '2021-04-07', 3, 'Lasagnes bolognaise', 'Rabiller Guillaume', 1101),
	(10012, '2021-06-10', 5, 'Saumon fumé', 'Rabiller Guillaume', 4),
	(10013, '2021-06-10', 2, 'Thon', 'Rabiller Guillaume', 4),
	(10014, '2021-06-10', 1, 'St-Jacques', 'Rabiller Guillaume', 4),
	(10016, '2021-06-10', 14, 'Camembert', 'Rabiller Guillaume', 10015),
	(10017, '2021-06-10', 7, 'Fois Gras de canard', 'Rabiller Guillaume', 1101),
	(10020, '2021-06-10', 4, 'Mâche', 'Rabiller Guillaume', 10019),
	(10021, '2021-06-10', 4, 'Laitue', 'Rabiller Guillaume', 10019),
	(10022, '2021-06-10', 2, 'Ongle de boeuf', 'Rabiller Guillaume', 3),
	(10023, '2021-06-10', 2, 'Entrecôte', 'Rabiller Guillaume', 3),
	(10024, '2021-06-10', 4, 'Dinde', 'Rabiller Guillaume', 967),
	(10025, '2021-06-10', 3, 'Petits Pois', 'Rabiller Guillaume', 5);
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. releve_temp
CREATE TABLE IF NOT EXISTS `releve_temp` (
  `id_temp` bigint(20) NOT NULL,
  `date_enreg_temp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nom_utilisateur` varchar(255) DEFAULT NULL,
  `temperature` float NOT NULL,
  `id_frigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_temp`),
  KEY `FK7pl1e60bdedf6u7va1y4hy8q8` (`id_frigo`),
  CONSTRAINT `FK7pl1e60bdedf6u7va1y4hy8q8` FOREIGN KEY (`id_frigo`) REFERENCES `frigo` (`id_frigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.releve_temp : ~53 rows (environ)
/*!40000 ALTER TABLE `releve_temp` DISABLE KEYS */;
INSERT INTO `releve_temp` (`id_temp`, `date_enreg_temp`, `nom_utilisateur`, `temperature`, `id_frigo`) VALUES
	(25, '2021-05-24 16:26:55', 'Rabiller Guillaume', 4.5, 369),
	(26, '2021-05-24 16:27:01', 'Rabiller Guillaume', 5.2, 370),
	(27, '2021-05-24 16:27:07', 'Rabiller Guillaume', 3.8, 383),
	(28, '2021-05-24 16:27:12', 'Rabiller Guillaume', 4.6, 896),
	(1009, '2021-05-28 11:54:51', 'Rabiller Guillaume', 4.3, 369),
	(1010, '2021-05-28 11:54:57', 'Rabiller Guillaume', 5.2, 370),
	(1011, '2021-05-28 11:55:02', 'Rabiller Guillaume', 3.8, 369),
	(1012, '2021-05-28 11:55:07', 'Rabiller Guillaume', 4.1, 369),
	(1013, '2021-05-28 11:55:20', 'Rabiller Guillaume', 4.1, 383),
	(1014, '2021-05-28 11:55:27', 'Rabiller Guillaume', 3.8, 896),
	(2005, '2021-06-01 11:16:32', 'Rabiller Guillaume', 4.2, 369),
	(2006, '2021-06-01 11:16:37', 'Rabiller Guillaume', 3.5, 370),
	(2007, '2021-06-01 11:16:44', 'Rabiller Guillaume', 4.1, 383),
	(2008, '2021-06-01 11:16:51', 'Rabiller Guillaume', 2.8, 896),
	(3001, '2021-06-02 08:26:04', 'Skywalker Anakin', 3.8, 369),
	(3002, '2021-06-02 08:26:10', 'Skywalker Anakin', 4.2, 370),
	(3003, '2021-06-02 08:26:15', 'Skywalker Anakin', 3.1, 383),
	(3004, '2021-06-02 08:26:20', 'Skywalker Anakin', 5.1, 896),
	(3023, '2021-06-02 16:50:28', 'Skywalker Anakin', 4.2, 369),
	(3024, '2021-06-02 16:50:33', 'Skywalker Anakin', 4.3, 370),
	(3025, '2021-06-02 16:50:38', 'Skywalker Anakin', 3.3, 383),
	(3026, '2021-06-02 16:50:44', 'Skywalker Anakin', 4.8, 896),
	(4006, '2021-06-03 08:41:26', 'Skywalker Anakin', 3.5, 369),
	(4007, '2021-06-03 08:41:33', 'Skywalker Anakin', 3.9, 370),
	(4008, '2021-06-03 08:41:38', 'Skywalker Anakin', 5.2, 383),
	(4009, '2021-06-03 08:41:43', 'Skywalker Anakin', 4.8, 896),
	(6005, '2021-06-06 10:51:30', 'Skywalker Anakin', 3.4, 369),
	(6006, '2021-06-06 10:51:37', 'Skywalker Anakin', 4.5, 370),
	(6007, '2021-06-06 10:51:42', 'Skywalker Anakin', 4.2, 383),
	(6008, '2021-06-06 10:51:48', 'Skywalker Anakin', 3.9, 896),
	(7004, '2021-06-07 10:32:44', 'Skywalker Anakin', 3.8, 369),
	(7005, '2021-06-07 10:32:46', 'Skywalker Anakin', 0, 370),
	(7006, '2021-06-07 10:32:56', 'Skywalker Anakin', 3.5, 370),
	(7007, '2021-06-07 10:33:02', 'Skywalker Anakin', 4.5, 383),
	(7008, '2021-06-07 10:33:06', 'Skywalker Anakin', 4.1, 896),
	(8001, '2021-06-08 09:18:25', 'Skywalker Anakin', 3.5, 369),
	(8002, '2021-06-08 09:18:30', 'Skywalker Anakin', 4.2, 370),
	(8003, '2021-06-08 09:18:35', 'Skywalker Anakin', 3.9, 383),
	(8004, '2021-06-08 09:18:40', 'Skywalker Anakin', 3.1, 896),
	(9005, '2021-06-09 10:51:31', 'Skywalker Anakin', 3.5, 369),
	(9006, '2021-06-09 10:51:37', 'Skywalker Anakin', 3.9, 370),
	(9007, '2021-06-09 10:51:42', 'Skywalker Anakin', 4.2, 383),
	(9008, '2021-06-09 10:51:46', 'Skywalker Anakin', 3.6, 896),
	(10003, '2021-06-10 11:03:13', 'Skywalker Anakin', 3.8, 369),
	(10004, '2021-06-10 11:03:19', 'Skywalker Anakin', 4.2, 370),
	(10005, '2021-06-10 11:03:24', 'Skywalker Anakin', 4.5, 383),
	(10006, '2021-06-10 11:03:29', 'Skywalker Anakin', 3.2, 896),
	(11006, '2021-06-15 09:44:05', 'Skywalker Anakin', 3.5, 369),
	(11007, '2021-06-15 09:44:11', 'Skywalker Anakin', 4.2, 370),
	(11008, '2021-06-15 09:44:16', 'Skywalker Anakin', 3.8, 383),
	(11009, '2021-06-15 09:44:21', 'Skywalker Anakin', 4.9, 896),
	(11041, '2021-06-15 14:44:55', 'Skywalker Anakin', 3.8, 369),
	(11042, '2021-06-15 14:45:01', 'Skywalker Anakin', 4.5, 370),
	(11043, '2021-06-15 14:45:07', 'Skywalker Anakin', 0, 383),
	(11044, '2021-06-15 14:48:35', 'Skywalker Anakin', 4.8, 896);
/*!40000 ALTER TABLE `releve_temp` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. statut
CREATE TABLE IF NOT EXISTS `statut` (
  `id_statut` bigint(20) NOT NULL,
  `nom_statut` varchar(64) NOT NULL,
  PRIMARY KEY (`id_statut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.statut : ~5 rows (environ)
/*!40000 ALTER TABLE `statut` DISABLE KEYS */;
INSERT INTO `statut` (`id_statut`, `nom_statut`) VALUES
	(21, 'En Stock'),
	(22, 'A Contrôler'),
	(23, 'Retiré'),
	(24, 'Erreur'),
	(25, 'Consommé');
/*!40000 ALTER TABLE `statut` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. stock
CREATE TABLE IF NOT EXISTS `stock` (
  `id_stock` bigint(20) NOT NULL,
  `date_entree_stock` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_produit` bigint(20) DEFAULT NULL,
  `id_statut` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  KEY `FK25vcuk7gcmjsssn37hgtl1lqw` (`id_produit`),
  KEY `FKb9jl1c9v8r8ixosq67ik3ol35` (`id_statut`),
  CONSTRAINT `FK25vcuk7gcmjsssn37hgtl1lqw` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`),
  CONSTRAINT `FKb9jl1c9v8r8ixosq67ik3ol35` FOREIGN KEY (`id_statut`) REFERENCES `statut` (`id_statut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.stock : ~8 rows (environ)
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`id_stock`, `date_entree_stock`, `id_produit`, `id_statut`) VALUES
	(11015, '2021-06-15 09:44:44', 10016, 21),
	(11017, '2021-06-15 09:44:47', 10020, 21),
	(11021, '2021-06-15 09:44:55', 10024, 21),
	(11023, '2021-06-15 09:44:58', 10023, 21),
	(11028, '2021-06-15 11:57:37', 8, 21),
	(11030, '2021-06-15 12:22:36', 10024, 21),
	(11038, '2021-06-15 14:40:33', 7, 22),
	(11045, '2021-06-15 14:51:20', 10022, 21);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. stock_historique
CREATE TABLE IF NOT EXISTS `stock_historique` (
  `id_stock` bigint(20) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `date_mouvement_stock` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_produit` bigint(20) NOT NULL,
  `produit_stock` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `utilisateur` varchar(255) NOT NULL,
  PRIMARY KEY (`id_stock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.stock_historique : ~140 rows (environ)
/*!40000 ALTER TABLE `stock_historique` DISABLE KEYS */;
INSERT INTO `stock_historique` (`id_stock`, `categorie`, `date_mouvement_stock`, `id_produit`, `produit_stock`, `statut`, `utilisateur`) VALUES
	(2, 'Viandes rouges', '2021-05-24 16:26:07', 1, 'Steaks hachés', 'En Stock', 'Rabiller Guillaume'),
	(3, 'Viandes rouges', '2021-05-24 16:26:08', 1, 'Steaks hachés', 'A Contrôler', 'Rabiller Guillaume'),
	(5, 'Poissons', '2021-05-24 16:26:11', 4, 'Dorade', 'En Stock', 'Rabiller Guillaume'),
	(6, 'Poissons', '2021-05-24 16:26:11', 4, 'Dorade', 'A Contrôler', 'Rabiller Guillaume'),
	(8, 'Poissons', '2021-05-24 16:26:14', 7, 'Rondelles de calamars', 'En Stock', 'Rabiller Guillaume'),
	(10, 'Légumes', '2021-05-24 16:26:16', 9, 'Purée de carotte', 'En Stock', 'Rabiller Guillaume'),
	(12, 'Légumes', '2021-05-24 16:26:19', 11, 'Purée pomme de terre', 'En Stock', 'Rabiller Guillaume'),
	(14, 'Légumes', '2021-05-24 16:26:21', 13, 'Haricots verts', 'En Stock', 'Rabiller Guillaume'),
	(16, 'Sauces', '2021-05-24 16:26:24', 15, 'Sauce béarnaise', 'En Stock', 'Rabiller Guillaume'),
	(18, 'Sauces', '2021-05-24 16:26:31', 17, 'Sauce pesto', 'En Stock', 'Rabiller Guillaume'),
	(20, 'Viandes blanches', '2021-05-24 16:26:34', 19, 'Poulet', 'En Stock', 'Rabiller Guillaume'),
	(22, 'Plats préparés', '2021-05-24 16:26:37', 21, 'Tartiflette', 'En Stock', 'Rabiller Guillaume'),
	(24, 'Plats préparés', '2021-05-24 16:26:41', 23, 'Lasagnes bolognaise', 'En Stock', 'Rabiller Guillaume'),
	(1001, 'Poissons', '2021-05-28 11:54:32', 7, 'Rondelles de calamars', 'A Contrôler', 'Rabiller Guillaume'),
	(1002, 'Légumes', '2021-05-28 11:54:32', 9, 'Purée de carotte', 'A Contrôler', 'Rabiller Guillaume'),
	(1003, 'Légumes', '2021-05-28 11:54:32', 11, 'Purée pomme de terre', 'A Contrôler', 'Rabiller Guillaume'),
	(1004, 'Légumes', '2021-05-28 11:54:32', 13, 'Haricots verts', 'A Contrôler', 'Rabiller Guillaume'),
	(1005, 'Sauces', '2021-05-28 11:54:32', 15, 'Sauce béarnaise', 'A Contrôler', 'Rabiller Guillaume'),
	(1006, 'Sauces', '2021-05-28 11:54:32', 17, 'Sauce pesto', 'A Contrôler', 'Rabiller Guillaume'),
	(1007, 'Viandes blanches', '2021-05-28 11:54:32', 19, 'Poulet', 'A Contrôler', 'Rabiller Guillaume'),
	(1008, 'Plats préparés', '2021-05-28 11:54:32', 23, 'Lasagnes bolognaise', 'A Contrôler', 'Rabiller Guillaume'),
	(1016, 'Poissons', '2021-05-28 11:56:14', 1015, 'Saumon', 'En Stock', 'Rabiller Guillaume'),
	(1017, 'Poissons', '2021-05-28 11:56:14', 1015, 'Saumon', 'A Contrôler', 'Rabiller Guillaume'),
	(1019, 'Viandes rouges', '2021-05-28 11:56:19', 1018, 'Magret de canard', 'En Stock', 'Rabiller Guillaume'),
	(1021, 'Viandes blanches', '2021-05-28 11:56:23', 1020, 'Poulet', 'En Stock', 'Rabiller Guillaume'),
	(1023, 'Légumes', '2021-05-28 11:56:28', 1022, 'Purée pomme de terre', 'En Stock', 'Rabiller Guillaume'),
	(1024, 'Viandes rouges', '2021-05-28 11:56:34', 1, 'Steaks hachés', 'Consommé', 'Rabiller Guillaume'),
	(1025, 'Poissons', '2021-05-28 11:56:37', 4, 'Dorade', 'Consommé', 'Rabiller Guillaume'),
	(1026, 'Poissons', '2021-05-28 11:56:41', 7, 'Rondelles de calamars', 'Retiré', 'Rabiller Guillaume'),
	(1027, 'Légumes', '2021-05-28 11:56:44', 9, 'Purée de carotte', 'Erreur', 'Rabiller Guillaume'),
	(1028, 'Légumes', '2021-05-28 11:56:47', 11, 'Purée pomme de terre', 'Consommé', 'Rabiller Guillaume'),
	(1029, 'Sauces', '2021-05-28 11:56:48', 15, 'Sauce béarnaise', 'Consommé', 'Rabiller Guillaume'),
	(1030, 'Légumes', '2021-05-28 11:56:51', 13, 'Haricots verts', 'Retiré', 'Rabiller Guillaume'),
	(1031, 'Sauces', '2021-05-28 11:56:54', 17, 'Sauce pesto', 'Consommé', 'Rabiller Guillaume'),
	(1032, 'Plats préparés', '2021-05-28 11:56:56', 23, 'Lasagnes bolognaise', 'Consommé', 'Rabiller Guillaume'),
	(1033, 'Viandes blanches', '2021-05-28 11:56:59', 19, 'Poulet', 'Consommé', 'Rabiller Guillaume'),
	(2001, 'Plats préparés', '2021-06-01 11:16:23', 21, 'Tartiflette', 'A Contrôler', 'Rabiller Guillaume'),
	(2002, 'Viandes rouges', '2021-06-01 11:16:23', 1018, 'Magret de canard', 'A Contrôler', 'Rabiller Guillaume'),
	(2003, 'Viandes blanches', '2021-06-01 11:16:23', 1020, 'Poulet', 'A Contrôler', 'Rabiller Guillaume'),
	(2004, 'Légumes', '2021-06-01 11:16:23', 1022, 'Purée pomme de terre', 'A Contrôler', 'Rabiller Guillaume'),
	(2009, 'Plats préparés', '2021-06-01 11:17:00', 21, 'Tartiflette', 'Consommé', 'Rabiller Guillaume'),
	(2010, 'Poissons', '2021-06-01 11:17:03', 1015, 'Saumon', 'Retiré', 'Rabiller Guillaume'),
	(2011, 'Viandes rouges', '2021-06-01 11:17:06', 1018, 'Magret de canard', 'Erreur', 'Rabiller Guillaume'),
	(2012, 'Légumes', '2021-06-01 11:17:09', 1022, 'Purée pomme de terre', 'Consommé', 'Rabiller Guillaume'),
	(2013, 'Viandes blanches', '2021-06-01 11:17:14', 1020, 'Poulet', 'Consommé', 'Rabiller Guillaume'),
	(2015, 'Viandes rouges', '2021-06-01 11:17:18', 2014, 'Steaks hachés', 'En Stock', 'Rabiller Guillaume'),
	(2016, 'Viandes rouges', '2021-06-01 11:17:18', 2014, 'Steaks hachés', 'A Contrôler', 'Rabiller Guillaume'),
	(2018, 'Légumes', '2021-06-01 11:17:21', 2017, 'Purée de carotte', 'En Stock', 'Rabiller Guillaume'),
	(2020, 'Légumes', '2021-06-01 11:17:25', 2019, 'Haricots verts', 'En Stock', 'Rabiller Guillaume'),
	(2022, 'Viandes blanches', '2021-06-01 11:17:27', 2021, 'Poulet', 'En Stock', 'Rabiller Guillaume'),
	(2024, 'Sauces', '2021-06-01 11:17:30', 2023, 'Sauce béarnaise', 'En Stock', 'Rabiller Guillaume'),
	(2026, 'Viandes rouges', '2021-06-01 11:17:34', 2025, 'Magret de canard', 'En Stock', 'Rabiller Guillaume'),
	(3005, 'Viandes rouges', '2021-06-02 08:26:29', 2014, 'Steaks hachés', 'Consommé', 'Skywalker Anakin'),
	(3006, 'Viandes blanches', '2021-06-02 08:26:34', 2021, 'Poulet', 'Consommé', 'Skywalker Anakin'),
	(3007, 'Viandes rouges', '2021-06-02 08:26:38', 2025, 'Magret de canard', 'Consommé', 'Skywalker Anakin'),
	(3009, 'Viandes rouges', '2021-06-02 08:26:41', 3008, 'Magret de canard', 'En Stock', 'Skywalker Anakin'),
	(3011, 'Viandes blanches', '2021-06-02 08:26:45', 3010, 'Poulet', 'En Stock', 'Skywalker Anakin'),
	(3013, 'Poissons', '2021-06-02 08:26:51', 3012, 'Saumon', 'En Stock', 'Skywalker Anakin'),
	(3014, 'Poissons', '2021-06-02 08:26:51', 3012, 'Saumon', 'A Contrôler', 'Skywalker Anakin'),
	(3015, 'Sauces', '2021-06-02 11:01:23', 2023, 'Sauce béarnaise', 'A Contrôler', 'Rabiller Guillaume'),
	(3027, 'Sauces', '2021-06-02 16:50:51', 2023, 'Sauce béarnaise', 'Consommé', 'Skywalker Anakin'),
	(3029, 'Viandes blanches', '2021-06-02 16:50:54', 3028, 'Poulet', 'En Stock', 'Skywalker Anakin'),
	(3031, 'Plats préparés', '2021-06-02 16:50:57', 3030, 'Tartiflette', 'En Stock', 'Skywalker Anakin'),
	(4001, 'Poissons', '2021-06-03 08:41:02', 3012, 'Saumon', 'Consommé', 'Skywalker Anakin'),
	(4002, 'Légumes', '2021-06-03 08:41:06', 2017, 'Purée de carotte', 'Consommé', 'Skywalker Anakin'),
	(4003, 'Viandes blanches', '2021-06-03 08:41:08', 3010, 'Poulet', 'Consommé', 'Skywalker Anakin'),
	(4005, 'Viandes rouges', '2021-06-03 08:41:14', 4004, 'Magret de canard', 'En Stock', 'Skywalker Anakin'),
	(6001, 'Légumes', '2021-06-06 10:51:24', 2019, 'Haricots verts', 'A Contrôler', 'Skywalker Anakin'),
	(6002, 'Viandes rouges', '2021-06-06 10:51:24', 3008, 'Magret de canard', 'A Contrôler', 'Skywalker Anakin'),
	(6003, 'Viandes blanches', '2021-06-06 10:51:24', 3028, 'Poulet', 'A Contrôler', 'Skywalker Anakin'),
	(6004, 'Viandes rouges', '2021-06-06 10:51:24', 4004, 'Magret de canard', 'A Contrôler', 'Skywalker Anakin'),
	(6009, 'Légumes', '2021-06-06 10:52:23', 2019, 'Haricots verts', 'Retiré', 'Skywalker Anakin'),
	(6010, 'Viandes rouges', '2021-06-06 10:52:26', 3008, 'Magret de canard', 'Consommé', 'Skywalker Anakin'),
	(6011, 'Viandes blanches', '2021-06-06 10:52:29', 3028, 'Poulet', 'Consommé', 'Skywalker Anakin'),
	(6012, 'Viandes rouges', '2021-06-06 10:52:32', 4004, 'Magret de canard', 'Retiré', 'Skywalker Anakin'),
	(6014, 'Légumes', '2021-06-06 10:52:38', 6013, 'Purée de carotte', 'En Stock', 'Skywalker Anakin'),
	(6016, 'Légumes', '2021-06-06 10:53:24', 6015, 'Purée pomme de terre', 'En Stock', 'Skywalker Anakin'),
	(6018, 'Sauces', '2021-06-06 10:53:27', 6017, 'Sauce béarnaise', 'En Stock', 'Skywalker Anakin'),
	(6020, 'Viandes blanches', '2021-06-06 10:53:33', 6019, 'Poulet', 'En Stock', 'Skywalker Anakin'),
	(6022, 'Viandes rouges', '2021-06-06 10:53:39', 6021, 'Steaks hachés', 'En Stock', 'Skywalker Anakin'),
	(6023, 'Viandes rouges', '2021-06-06 10:53:39', 6021, 'Steaks hachés', 'A Contrôler', 'Skywalker Anakin'),
	(7001, 'Plats préparés', '2021-06-07 10:32:37', 3030, 'Tartiflette', 'A Contrôler', 'Skywalker Anakin'),
	(7002, 'Légumes', '2021-06-07 10:32:37', 6015, 'Purée pomme de terre', 'A Contrôler', 'Skywalker Anakin'),
	(7003, 'Sauces', '2021-06-07 10:32:37', 6017, 'Sauce béarnaise', 'A Contrôler', 'Skywalker Anakin'),
	(7009, 'Plats préparés', '2021-06-07 10:33:50', 3030, 'Tartiflette', 'Consommé', 'Skywalker Anakin'),
	(7010, 'Légumes', '2021-06-07 10:33:52', 6015, 'Purée pomme de terre', 'Consommé', 'Skywalker Anakin'),
	(7011, 'Sauces', '2021-06-07 10:33:53', 6017, 'Sauce béarnaise', 'Consommé', 'Skywalker Anakin'),
	(7012, 'Viandes rouges', '2021-06-07 10:33:57', 6021, 'Steaks hachés', 'Retiré', 'Skywalker Anakin'),
	(7014, 'Viandes rouges', '2021-06-07 10:34:20', 7013, 'Steaks hachés', 'En Stock', 'Skywalker Anakin'),
	(7015, 'Viandes rouges', '2021-06-07 10:34:20', 7013, 'Steaks hachés', 'A Contrôler', 'Skywalker Anakin'),
	(7017, 'Viandes rouges', '2021-06-07 10:34:22', 7016, 'Magret de canard', 'En Stock', 'Skywalker Anakin'),
	(7019, 'Poissons', '2021-06-07 10:34:25', 7018, 'Cabillaud', 'En Stock', 'Skywalker Anakin'),
	(7021, 'Poissons', '2021-06-07 10:34:27', 7020, 'Saumon', 'En Stock', 'Skywalker Anakin'),
	(7022, 'Poissons', '2021-06-07 10:34:27', 7020, 'Saumon', 'A Contrôler', 'Skywalker Anakin'),
	(7024, 'Légumes', '2021-06-07 10:34:30', 7023, 'Haricots verts', 'En Stock', 'Skywalker Anakin'),
	(8005, 'Viandes rouges', '2021-06-08 09:18:44', 7013, 'Steaks hachés', 'Consommé', 'Skywalker Anakin'),
	(8006, 'Poissons', '2021-06-08 09:18:46', 7020, 'Saumon', 'Consommé', 'Skywalker Anakin'),
	(8007, 'Viandes rouges', '2021-06-08 09:18:50', 7016, 'Magret de canard', 'Consommé', 'Skywalker Anakin'),
	(8009, 'Sauces', '2021-06-08 09:18:53', 8008, 'Sauce tartare', 'En Stock', 'Skywalker Anakin'),
	(8011, 'Viandes rouges', '2021-06-08 09:18:57', 8010, 'Magret de canard', 'En Stock', 'Skywalker Anakin'),
	(8013, 'Viandes rouges', '2021-06-08 09:18:59', 8012, 'Steaks hachés', 'En Stock', 'Skywalker Anakin'),
	(8014, 'Viandes rouges', '2021-06-08 09:18:59', 8012, 'Steaks hachés', 'A Contrôler', 'Skywalker Anakin'),
	(8016, 'Poissons', '2021-06-08 09:19:04', 8015, 'Rondelles de calamars', 'En Stock', 'Skywalker Anakin'),
	(9001, 'Légumes', '2021-06-09 10:51:25', 6013, 'Purée de carotte', 'A Contrôler', 'Skywalker Anakin'),
	(9002, 'Viandes blanches', '2021-06-09 10:51:25', 6019, 'Poulet', 'A Contrôler', 'Skywalker Anakin'),
	(9003, 'Poissons', '2021-06-09 10:51:25', 7018, 'Cabillaud', 'A Contrôler', 'Skywalker Anakin'),
	(9004, 'Légumes', '2021-06-09 10:51:25', 7023, 'Haricots verts', 'A Contrôler', 'Skywalker Anakin'),
	(9009, 'Viandes rouges', '2021-06-09 10:51:54', 8012, 'Steaks hachés', 'Retiré', 'Skywalker Anakin'),
	(9010, 'Légumes', '2021-06-09 10:51:57', 7023, 'Haricots verts', 'Consommé', 'Skywalker Anakin'),
	(9011, 'Poissons', '2021-06-09 10:51:59', 7018, 'Cabillaud', 'Consommé', 'Skywalker Anakin'),
	(9012, 'Viandes blanches', '2021-06-09 10:52:00', 6019, 'Poulet', 'Consommé', 'Skywalker Anakin'),
	(9013, 'Légumes', '2021-06-09 10:52:01', 6013, 'Purée de carotte', 'Consommé', 'Skywalker Anakin'),
	(9015, 'Viandes rouges', '2021-06-09 10:52:05', 9014, 'Steaks hachés', 'En Stock', 'Skywalker Anakin'),
	(9016, 'Viandes rouges', '2021-06-09 10:52:05', 9014, 'Steaks hachés', 'A Contrôler', 'Skywalker Anakin'),
	(9018, 'Viandes blanches', '2021-06-09 10:52:11', 9017, 'Poulet', 'En Stock', 'Skywalker Anakin'),
	(9020, 'Sauces', '2021-06-09 11:47:10', 9019, 'Sauce tartare', 'En Stock', 'Skywalker Anakin'),
	(9022, 'Viandes blanches', '2021-06-09 11:47:14', 9021, 'Poulet', 'En Stock', 'Skywalker Anakin'),
	(9024, 'Plats préparés', '2021-06-09 11:47:17', 9023, 'Tartiflette', 'En Stock', 'Skywalker Anakin'),
	(10001, 'Viandes rouges', '2021-06-10 11:03:05', 8010, 'Magret de canard', 'A Contrôler', 'Skywalker Anakin'),
	(10002, 'Poissons', '2021-06-10 11:03:05', 8015, 'Rondelles de calamars', 'A Contrôler', 'Skywalker Anakin'),
	(10007, 'Viandes rouges', '2021-06-10 11:03:46', 8010, 'Magret de canard', 'Consommé', 'Skywalker Anakin'),
	(10008, 'Poissons', '2021-06-10 11:03:48', 8015, 'Rondelles de calamars', 'Consommé', 'Skywalker Anakin'),
	(10009, 'Viandes rouges', '2021-06-10 11:03:50', 9014, 'Steaks hachés', 'Consommé', 'Skywalker Anakin'),
	(10011, 'Légumes', '2021-06-10 11:03:55', 10010, 'Haricots verts', 'En Stock', 'Skywalker Anakin'),
	(10018, 'Plats préparés', '2021-06-10 11:09:44', 9023, 'Tartiflette', 'Retiré', 'Rabiller Guillaume'),
	(11001, 'Sauces', '2021-06-15 09:40:26', 8008, 'Sauce tartare', 'A Contrôler', 'Skywalker Anakin'),
	(11002, 'Viandes blanches', '2021-06-15 09:40:26', 9017, 'Poulet', 'A Contrôler', 'Skywalker Anakin'),
	(11003, 'Sauces', '2021-06-15 09:40:26', 9019, 'Sauce tartare', 'A Contrôler', 'Skywalker Anakin'),
	(11004, 'Viandes blanches', '2021-06-15 09:40:26', 9021, 'Poulet', 'A Contrôler', 'Skywalker Anakin'),
	(11005, 'Légumes', '2021-06-15 09:40:26', 10010, 'Haricots verts', 'A Contrôler', 'Skywalker Anakin'),
	(11010, 'Sauces', '2021-06-15 09:44:27', 8008, 'Sauce tartare', 'Consommé', 'Skywalker Anakin'),
	(11011, 'Viandes blanches', '2021-06-15 09:44:31', 9017, 'Poulet', 'Erreur', 'Skywalker Anakin'),
	(11012, 'Sauces', '2021-06-15 09:44:34', 9019, 'Sauce tartare', 'Consommé', 'Skywalker Anakin'),
	(11013, 'Viandes blanches', '2021-06-15 09:44:36', 9021, 'Poulet', 'Retiré', 'Skywalker Anakin'),
	(11014, 'Légumes', '2021-06-15 09:44:40', 10010, 'Haricots verts', 'Consommé', 'Skywalker Anakin'),
	(11016, 'Fromages', '2021-06-15 09:44:44', 11015, 'Camembert', 'En Stock', 'Skywalker Anakin'),
	(11018, 'Salades', '2021-06-15 09:44:47', 11017, 'Mâche', 'En Stock', 'Skywalker Anakin'),
	(11020, 'Plats préparés', '2021-06-15 09:44:51', 11019, 'Fois Gras de canard', 'En Stock', 'Skywalker Anakin'),
	(11022, 'Viandes blanches', '2021-06-15 09:44:55', 11021, 'Dinde', 'En Stock', 'Skywalker Anakin'),
	(11024, 'Viandes rouges', '2021-06-15 09:44:58', 11023, 'Entrecôte', 'En Stock', 'Skywalker Anakin'),
	(11029, 'Sauces', '2021-06-15 11:57:37', 11028, 'Sauce tartare', 'En Stock', 'Skywalker Anakin'),
	(11031, 'Viandes blanches', '2021-06-15 12:22:36', 11030, 'Dinde', 'En Stock', 'Skywalker Anakin'),
	(11032, 'Plats préparés', '2021-06-15 12:32:26', 11019, 'Fois Gras de canard', 'Consommé', 'Skywalker Anakin'),
	(11039, 'Viandes rouges', '2021-06-15 14:40:33', 11038, 'Steaks hachés', 'En Stock', 'Skywalker Anakin'),
	(11040, 'Viandes rouges', '2021-06-15 14:40:33', 11038, 'Steaks hachés', 'A Contrôler', 'Skywalker Anakin'),
	(11046, 'Viandes rouges', '2021-06-15 14:51:20', 11045, 'Ongle de boeuf', 'En Stock', 'Skywalker Anakin');
/*!40000 ALTER TABLE `stock_historique` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. table_restau
CREATE TABLE IF NOT EXISTS `table_restau` (
  `id_table` bigint(20) NOT NULL,
  `decr_table` varchar(64) DEFAULT NULL,
  `no_table` int(11) NOT NULL,
  PRIMARY KEY (`id_table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.table_restau : ~9 rows (environ)
/*!40000 ALTER TABLE `table_restau` DISABLE KEYS */;
INSERT INTO `table_restau` (`id_table`, `decr_table`, `no_table`) VALUES
	(22, 'Table carrée 4 pers entrée droite', 1),
	(23, 'Table rectangle 6 pers entrée droite', 2),
	(24, 'Table ronde 5 pers milieu droit', 3),
	(25, 'Table rectangle 6 pers milieu droite', 4),
	(26, 'Table rectangle 10 pers fond droite', 5),
	(27, 'Table rectangle 2 pers entrée gauche 1ere', 11),
	(28, 'Table rectangle 2 pers entrée gauche 2eme', 12),
	(29, 'Table carrée 4 pers milieu gauche', 13),
	(30, 'Table rectangle 6 pers milieu gauche', 14);
/*!40000 ALTER TABLE `table_restau` ENABLE KEYS */;

-- Listage de la structure de la table mariadbcnam. utilisateur
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` bigint(20) NOT NULL,
  `email_utilisateur` varchar(255) DEFAULT NULL,
  `login_utilisateur` varchar(15) NOT NULL,
  `nom_utilisateur` varchar(30) NOT NULL,
  `password_utilisateur` varchar(255) NOT NULL,
  `prenom_utilisateur` varchar(30) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `UK_i96kh89a47nnoklhsjtnjswt` (`email_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table mariadbcnam.utilisateur : ~4 rows (environ)
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` (`id_utilisateur`, `email_utilisateur`, `login_utilisateur`, `nom_utilisateur`, `password_utilisateur`, `prenom_utilisateur`, `role`) VALUES
	(1, 'g.rabiller44@gmail.com', 'guillaume', 'Rabiller', '$2a$10$fmUSh9w67OwRYumiSOo/negQGyDyCd50NxlWLhwNfojD99M6vLGdO', 'Guillaume', 'ROLE_ADMIN'),
	(1312, 'darkvador@starwars.com', 'darkvador', 'Skywalker', '$2a$10$tEp4Qh/juPUxLEUqeep4tu7CiqRVkFx4Tl1/9udEfie0/wKSSKeee', 'Anakin', 'ROLE_UTILISATEUR'),
	(1313, 'maytheforcebewithyou@starwars.com', 'obiwan', 'Kenobi', '$2a$10$hfOLSGHh4.MCVreYsjKv5exfjtNdDXBMpgWdUQSmk9wd7CfK0VXfa', 'Obi-wan', 'ROLE_ADMIN'),
	(1314, 'millenniumfalcon@starwars.com', 'hansolo', 'Solo', '$2a$10$Zx3bUPHUdC9rQQBSg0xWsOniJxGKHclEXFKE7eTA7xd4ISaxIkSba', 'Han', 'ROLE_SERVEUR');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
