INSERT INTO priorite (status) VALUES
("haute priorité"),
("moyenne priorité"),
("basse priorité");

INSERT INTO utilisateur (nom, password,administrateur) VALUES
("chef",  "$2a$10$iqhQKcm4h7c8asFTWnIxjeeyeYoDDD/oaLHFRVMjoxebPpRY74WsK",1) ,
("employer",  "$2a$10$iqhQKcm4h7c8asFTWnIxjeeyeYoDDD/oaLHFRVMjoxebPpRY74WsK",0);

INSERT INTO tache (description,titre,valide,priorite_id,utilisateur_id) VALUES
("faire une API", "API pour un particulier", true,2,1),
("refaire une page web front", "améliorer une page web", false,3,1),
("entrainer un stagiaire", "nouveau stagiaire", true,1,1);

INSERT INTO utilisateur_tache (tache_id,utilisateur_id) VALUES
(1,1),
(1,2),
(2,2),
(3,1);