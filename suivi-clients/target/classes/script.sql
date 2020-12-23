-- Entreprise
insert into entreprise (id, nom, pattente) values(nextval('ID_ENTREPRISE_SEQ'), 'Entreprise 1', '1-63-353');
insert into entreprise (id, nom, pattente) values(nextval('ID_ENTREPRISE_SEQ'), 'Entreprise 2', '2-77-250');

-- Clients
insert into client (id, nom, entreprise_id, groupe_id) values(nextval('ID_CLIENT_SEQ'), 'Client 11',  1, null);
insert into client (id, nom, entreprise_id, groupe_id) values(nextval('ID_CLIENT_SEQ'), 'Client 12',  1, null);
insert into client (id, nom, entreprise_id, groupe_id) values(nextval('ID_CLIENT_SEQ'), 'Client 13',  1, null);
insert into client (id, nom, entreprise_id, groupe_id) values(nextval('ID_CLIENT_SEQ'), 'Client 131',  1,  1);
insert into client (id, nom, entreprise_id, groupe_id) values(nextval('ID_CLIENT_SEQ'), 'Client 132',  1,  1);

insert into client (id, nom, entreprise_id, groupe_id) values(nextval('ID_CLIENT_SEQ'), 'Client 21',  51, null);
insert into client (id, nom, entreprise_id, groupe_id) values(nextval('ID_CLIENT_SEQ'), 'Client 22',  51, null);

-- Compte entreprise
insert into compte (id, banque, n_compte, entreprise_id) values(nextval('ID_COMPTE_SEQ'), 'SG MAROC',  '0217R000457845',  1);
insert into compte (id, banque, n_compte, entreprise_id) values(nextval('ID_COMPTE_SEQ'), 'BMCE',  '4877456525336',  1);
insert into compte (id, banque, n_compte, entreprise_id) values(nextval('ID_COMPTE_SEQ'), 'AWB',  '0220R000457845',  1);

insert into compte (id, banque, n_compte, entreprise_id) values(nextval('ID_COMPTE_SEQ'), 'BMCI',  '000000015487',  51);
insert into compte (id, banque, n_compte, entreprise_id) values(nextval('ID_COMPTE_SEQ'), 'BMCE',  '77777775551436',  51);

alter table ENCAISSEMENT add column devise varchar(50);
SELECT * FROM TRANSACTION

insert into transaction(id, date_depot, montant, n_transaction, compte_id) values(nextval('ID_TRANSACTION_SEQ'), '2020-08-28',  7777, '0001',  51);

