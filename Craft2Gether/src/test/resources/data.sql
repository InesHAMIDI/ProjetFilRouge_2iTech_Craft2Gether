CREATE TABLE `bloc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `biome_origine` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `outil_necessaire` varchar(255) DEFAULT NULL,
  `type` smallint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `liste` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` smallint DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `createur_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqo13t8om6yhn1jjd6fax858f8` (`createur_id`),
  CONSTRAINT `FKqo13t8om6yhn1jjd6fax858f8` FOREIGN KEY (`createur_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `liste_collaborateurs` (
  `listeiparticipate_id` int NOT NULL,
  `collaborateurs_id` int NOT NULL,
  KEY `FK9a24bbvb1mymcc771vmlo5lx4` (`collaborateurs_id`),
  KEY `FKsj5lil05haxwsytmp7px2homh` (`listeiparticipate_id`),
  CONSTRAINT `FK9a24bbvb1mymcc771vmlo5lx4` FOREIGN KEY (`collaborateurs_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKsj5lil05haxwsytmp7px2homh` FOREIGN KEY (`listeiparticipate_id`) REFERENCES `liste` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `liste_quantite_farmee` (
  `liste_id` int NOT NULL,
  `quantite_farmee_id` int NOT NULL,
  `quantite_farmee_key` int NOT NULL,
  PRIMARY KEY (`liste_id`,`quantite_farmee_key`),
  KEY `FKicj7wud1ie2g3kqbdo4yl0a3g` (`quantite_farmee_id`),
  CONSTRAINT `FKicj7wud1ie2g3kqbdo4yl0a3g` FOREIGN KEY (`quantite_farmee_id`) REFERENCES `bloc` (`id`),
  CONSTRAINT `FKrgcrrdxdoen2a3njwal0hnovo` FOREIGN KEY (`liste_id`) REFERENCES `liste` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `liste_recettes` (
  `liste_id` int NOT NULL,
  `recettes_id` int NOT NULL,
  KEY `FK95gh3jr221jpn2jvpwrk0fwgs` (`recettes_id`),
  KEY `FK1y1wwa4gqrchs17rnhb7d6htb` (`liste_id`),
  CONSTRAINT `FK1y1wwa4gqrchs17rnhb7d6htb` FOREIGN KEY (`liste_id`) REFERENCES `liste` (`id`),
  CONSTRAINT `FK95gh3jr221jpn2jvpwrk0fwgs` FOREIGN KEY (`recettes_id`) REFERENCES `recette` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recette` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `status` smallint DEFAULT NULL,
  `resultat_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5ypg1s6jwchihxvpek5k4pxsc` (`resultat_id`),
  CONSTRAINT `FK5ypg1s6jwchihxvpek5k4pxsc` FOREIGN KEY (`resultat_id`) REFERENCES `bloc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recette_ingredients` (
  `recette_id` int NOT NULL,
  `ingredients_id` int NOT NULL,
  `ingredients_key` int NOT NULL,
  PRIMARY KEY (`recette_id`,`ingredients_key`),
  KEY `FK9raiqsxn9amjc5ie1joyxab19` (`ingredients_id`),
  CONSTRAINT `FK9raiqsxn9amjc5ie1joyxab19` FOREIGN KEY (`ingredients_id`) REFERENCES `bloc` (`id`),
  CONSTRAINT `FKgfbe0srm3jsa2ydgxiwrmq5wg` FOREIGN KEY (`recette_id`) REFERENCES `recette` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `dtype` varchar(31) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` smallint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
