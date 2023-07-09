--
-- Table structure for table `bloc`
--
DROP TABLE IF EXISTS bloc;
CREATE TABLE bloc (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `biomeOrigine` varchar(255) DEFAULT NULL,
                        `nom` varchar(255) DEFAULT NULL,
                        `outilNecessaire` varchar(255) DEFAULT NULL,
                        `type` smallint DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bloc`
--
INSERT INTO bloc
VALUES
    (1,'all','chene','hache_bois',0),
    (2,'all','planche de chene','hache_bois',1),
    (3,'all','minerai de fer','pioche',0),
    (4,'all','lingot de fer','pioche',1),
    (5,'all','minerai de fer','pioche',1);

--
-- Table structure for table `liste`
--

DROP TABLE IF EXISTS liste;
CREATE TABLE liste (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `status` smallint DEFAULT NULL,
                         `titre` varchar(255) DEFAULT NULL,
                         `createur_id` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKdv0u6lpsew0r11cjw7kapmhi7` (`createur_id`),
                         CONSTRAINT `FKdv0u6lpsew0r11cjw7kapmhi7` FOREIGN KEY (`createur_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `liste`
--
INSERT INTO liste
VALUES
    (1, 0,'bjr',4),
    (2, 1,'dvvd',4);

--
-- Table structure for table `liste_collaborateurs`
--

DROP TABLE IF EXISTS liste_collaborateurs;
CREATE TABLE liste_collaborateurs (
                                        `listeIParticipate_id` int NOT NULL,
                                        `collaborateurs_id` int NOT NULL,
                                        KEY `FKo98yjh144iw4tphu1ophth2fw` (`collaborateurs_id`),
                                        KEY `FK3pt2sxr2yo3ubpb6hn17sm5an` (`listeIParticipate_id`),
                                        CONSTRAINT `FK3pt2sxr2yo3ubpb6hn17sm5an` FOREIGN KEY (`listeIParticipate_id`) REFERENCES `liste` (`id`),
                                        CONSTRAINT `FKo98yjh144iw4tphu1ophth2fw` FOREIGN KEY (`collaborateurs_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `liste_collaborateurs`
--
INSERT INTO liste_collaborateurs
VALUES
    (1, 3),
    (2, 4);

--
-- Table structure for table `liste_quantitefarmee`
--

DROP TABLE IF EXISTS liste_quantitefarmee;
CREATE TABLE liste_quantitefarmee (
                                        `Liste_id` int NOT NULL,
                                        `quantiteFarmee_id` int NOT NULL,
                                        `quantiteFarmee_KEY` int NOT NULL,
                                        PRIMARY KEY (`Liste_id`,`quantiteFarmee_KEY`),
                                        KEY `FKe1gt3x8x7lc55b2o8qe1i1mgk` (`quantiteFarmee_id`),
                                        CONSTRAINT `FKe1gt3x8x7lc55b2o8qe1i1mgk` FOREIGN KEY (`quantiteFarmee_id`) REFERENCES `bloc` (`id`),
                                        CONSTRAINT `FKqcgauaq52s9vb6fhe8wwkpcrh` FOREIGN KEY (`Liste_id`) REFERENCES `liste` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `liste_quantitefarmee`
--

--
-- Table structure for table `liste_recettes`
--

DROP TABLE IF EXISTS liste_recettes;
CREATE TABLE liste_recettes (
                                  `Liste_id` int NOT NULL,
                                  `recettes_id` int NOT NULL,
                                  KEY `FKekd58khqpg1w4kr2v4if8bv8h` (`recettes_id`),
                                  KEY `FKakq8p3icx50l17nbpby477vdi` (`Liste_id`),
                                  CONSTRAINT `FKakq8p3icx50l17nbpby477vdi` FOREIGN KEY (`Liste_id`) REFERENCES `liste` (`id`),
                                  CONSTRAINT `FKekd58khqpg1w4kr2v4if8bv8h` FOREIGN KEY (`recettes_id`) REFERENCES `recette` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `liste_recettes`
--
INSERT INTO liste_recettes
VALUES
    (1, 1),
    (2, 2);
--
-- Table structure for table `recette`
--

DROP TABLE IF EXISTS recette;
CREATE TABLE recette (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `nom` varchar(255) DEFAULT NULL,
                           `status` smallint DEFAULT NULL,
                           `resultat_id` int DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKi2r6wppmc28uf0cr225dghiog` (`resultat_id`),
                           CONSTRAINT `FKi2r6wppmc28uf0cr225dghiog` FOREIGN KEY (`resultat_id`) REFERENCES `bloc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `recette`
--
INSERT INTO recette
VALUES
    (1,'Planche de chene',1,2),
    (2,'lingot de fer',0,3),
    (3,'Epee en fer',1,1);


--
-- Table structure for table `recette_ingredients`
--

DROP TABLE IF EXISTS recette_ingredients;
CREATE TABLE recette_ingredients (
                                       `Recette_id` int NOT NULL,
                                       `ingredients_id` int NOT NULL,
                                       `ingredients_KEY` int NOT NULL,
                                       PRIMARY KEY (`Recette_id`,`ingredients_KEY`),
                                       KEY `FKcfbyx7a7skcrmk12wsxhshlcw` (`ingredients_id`),
                                       CONSTRAINT `FKbi951vet1yr9hjdm9jj73cg9a` FOREIGN KEY (`Recette_id`) REFERENCES `recette` (`id`),
                                       CONSTRAINT `FKcfbyx7a7skcrmk12wsxhshlcw` FOREIGN KEY (`ingredients_id`) REFERENCES `bloc` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `recette_ingredients`
--
INSERT INTO recette_ingredients
VALUES
    (1,1,1),
    (2,4,1),
    (2,4,3);

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS user;
CREATE TABLE user (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `password` varchar(255) DEFAULT NULL,
                        `roles` varchar(255) DEFAULT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO user
VALUES
    (1,'admin','ADMIN','admin'),
    (1,'player','PLAYER','player'),
    (1,'bob','ADMIN','bob');


