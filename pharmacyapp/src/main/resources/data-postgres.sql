
alter table pharmacy_derma add start_hour timestamp;
alter table pharmacy_derma add hours numeric;

insert into public.address(id, street, street_number, city, country) values (200,'street', 15,'city','country');

INSERT INTO public.calendar(id) VALUES (200);

INSERT INTO public.pharmacy(
    id, description, name, address_id, calendar_id)
VALUES (200, 'description', 'name', 200, 200);

INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (200, 457887, 'manufacturer', 'name', false, 'shape','type',null);


--inserting users

INSERT INTO public.dermatologist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, address_id)
VALUES (300, 'derma@live.com', true, 'dermatologist', null, 'lastname', '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4517', 200);

--inserting dermatologist's working hours in a pharmacy
INSERT INTO public.pharmacy_derma(pharmacy_id, derma_id, start_hour, hours)
VALUES (200, 300, now(),8);

INSERT INTO public.patient(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, address_id, category, penatlies, points)
VALUES (400, 'patient@live.com', true, 'patient', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 200, null, null, null);

INSERT INTO public.pharmacist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, address_id,
                              pharmacy_id, pharmacist_id,start_hour,hours )
VALUES (500, 'pharmacist@live.com' ,true, 'pharmacist', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 200, 200, null, now(),8);

INSERT INTO public.pharmacy_admin(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, address_id, pharmacy_id, pharmacy_admin_id)
VALUES (200, 'phAdmin@live.com', true, 'pharmacy admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6',
        '45613', 200, 200, null);

INSERT INTO public.system_admin(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, address_id)
VALUES (600, 'sysadmin@live.com', true, 'system admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '764', 200);

--inserting authority

INSERT INTO public.authority(id,name) VALUES (1,'USER');
INSERT INTO public.authority(id, name) VALUES (2,'ADMIN');

--inserting user_authority
INSERT INTO public.user_authority(
    user_id, authority_id)
VALUES (200, 1);
INSERT INTO public.user_authority(
    user_id, authority_id)
VALUES (300, 1);

INSERT INTO public.user_authority(
    user_id, authority_id)
VALUES (400, 1);

INSERT INTO public.user_authority(
    user_id, authority_id)
VALUES (500, 1);

INSERT INTO public.user_authority(
    user_id, authority_id)
VALUES (600, 2);