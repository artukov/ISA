
alter table calendar_appointments add appointment_date timestamp;

insert into public.address(id, street, street_number, city, country) values (200,'street', 15,'city','country');

INSERT INTO public.calendar(id) VALUES (200);

--pharmacy

INSERT INTO public.pharmacy(
    id, description, name, address_id, calendar_id)
VALUES (200, 'description', 'name', 200, 200);

INSERT INTO public.pharmacy(
    id, description, name, address_id, calendar_id)
VALUES (300, 'description', 'name', 200, 200);

INSERT INTO pharmacy_ratings(pharmacy_id, ratings) VALUES (200, 4.5);
INSERT INTO pharmacy_ratings(pharmacy_id, ratings) VALUES (200, 5.0);
INSERT INTO pharmacy_ratings(pharmacy_id, ratings) VALUES (200, 3);
INSERT INTO pharmacy_ratings(pharmacy_id, ratings) VALUES (300, 3);
--drugs

INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (200, 457887, 'manufacturer', 'name1', false, 'shape','type',null);
INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (300, 457887, 'manufacturer', 'name2', false, 'shape','type',null);
INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (400, 457887, 'manufacturer', 'name3', false, 'shape','type',null);
INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (500, 457887, 'manufacturer', 'name4', false, 'shape','type',null);

INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (600, 457887, 'manufacturer', 'name5', false, 'shape','type',null);

INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount) VALUES (200,200, 200);
INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount) VALUES (200,300,10);
INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount) VALUES (200,400,25);
INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount) VALUES (200,500,45);

--inserting users

INSERT INTO public.dermatologist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (300, 'derma@live.com', true, 'dermatologist', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4517', 1, 200);

INSERT INTO public.dermatologist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (301, 'derma1@live.com', true, 'dermatologist', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4517', 1, 200);

INSERT INTO public.dermatologist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (302, 'derma2@live.com', true, 'dermatologist', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4517', 1, 200);

INSERT INTO public.dermatologist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (303, 'derma3@live.com', true, 'dermatologist', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4517', 1, 200);


--inserting dermatologist's working hours in a pharmacy
INSERT INTO public.pharmacy_derma(pharmacy_id, derma_id, start_hour, hours)
VALUES (200, 300, now(),8);

INSERT INTO public.pharmacy_derma(pharmacy_id, derma_id, start_hour, hours)
VALUES (200, 301, now(),6);

INSERT INTO public.pharmacy_derma(hours, start_hour, pharmacy_id, derma_id)
VALUES (8, now(),300, 303);
--dermatologist ratings for one pharmacy

INSERT INTO derma_ratings(pharmacy_id, derma_id, dermatologist_ratings_id) VALUES (200,300,null);
INSERT INTO derma_ratings(pharmacy_id, derma_id, dermatologist_ratings_id) VALUES (300,300,null);

INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (300,200,4.5);
INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (300,200,3.5);
INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (300,200,5.0);

INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (300,300,5.0);

--patient

INSERT INTO public.patient(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id, category, penalties, points)
VALUES (400, 'patient@live.com', true, 'patient', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 0, 200, null, null, null);
INSERT INTO public.patient(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id, category, penalties, points)
VALUES (401, 'nikola@live.com', true, 'Nikola', null, 'Artukov',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 0, 200, null, null, null);

INSERT INTO reservation(id, acceptance_date, accepted,patient_id, pharmacy_id)
VALUES (200, now(), true,400, 200);

INSERT INTO reservation(id, acceptance_date, accepted,patient_id, pharmacy_id)
VALUES (201, now(), false,400, 200);

INSERT INTO reservation(id, acceptance_date, accepted,patient_id, pharmacy_id)
VALUES (202, now(), true,400, 200);

INSERT INTO reservation_drug(reservation_id, drug_id)
VALUES (200, 200);

INSERT INTO reservation_drug(reservation_id, drug_id)
VALUES (201, 300);

INSERT INTO reservation_drug(reservation_id, drug_id)
VALUES (202, 300);

--pharmacist

INSERT INTO public.pharmacist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id,
                              pharmacy_id,start_hour,hours )
VALUES (500, 'pharmacist@live.com' ,true, 'pharmacist', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 2, 200, 200, now(),8);

INSERT INTO public.pharmacist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id,
    pharmacy_id,start_hour,hours )
VALUES (501, 'pharmacist1@live.com' ,true, 'pharmacist', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 2, 200, 200, now(),8);

INSERT INTO public.pharmacist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id,
    pharmacy_id,start_hour,hours )
VALUES (502, 'pharmacist2@live.com' ,true, 'pharmacist', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 2, 200, 200, now(),8);

INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (500 , 3.0);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (500 , 2.0);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (500 , 5.0);


INSERT INTO public.pharmacy_admin(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id, pharmacy_id)
VALUES (200, 'phAdmin@live.com', true, 'pharmacy admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6',
        '45613',3,200, 200);

INSERT INTO public.system_admin(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (600, 'sysadmin@live.com', true, 'system admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '764', 5,200);


INSERT INTO supplier(id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (700, 'supplier@live.com', true, 'system admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '764',4 ,200);

INSERT INTO supplier(id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (701, 'supplier1@live.com', true, 'system admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '764',4 ,200);

INSERT INTO warehouse(id, size, supplier_id) VALUES (100, 500, 700);
INSERT INTO warehouse(id, size, supplier_id) VALUES (101, 500, 701);

INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 200, 100);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 300, 100);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 400, 100);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 500, 100);

INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 200, 101);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 300, 101);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 400, 101);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 500, 101);

--inserting authority

INSERT INTO public.authority(id,name) VALUES (1,'USER');
INSERT INTO public.authority(id, name) VALUES (2,'ADMIN');
INSERT INTO public.authority(id, name) VALUES (3,'PHARMACY_ADMIN');

--inserting user_authority
INSERT INTO public.user_authority(user_id, authority_id)VALUES (200, 1);
INSERT INTO public.user_authority(user_id, authority_id)VALUES (200, 3);
INSERT INTO public.user_authority(user_id, authority_id)VALUES (300, 1);
INSERT INTO public.user_authority(user_id, authority_id)VALUES (400, 1);
INSERT INTO public.user_authority(user_id, authority_id)VALUES (500, 1);
INSERT INTO public.user_authority(user_id, authority_id)VALUES (700, 1);
INSERT INTO public.user_authority(user_id, authority_id)VALUES (600, 2);


--------------------------------------------------
INSERT INTO  examination(id, beg_date, duration, report, patient_id, appointment_id, diagnose, price, derma_id, finished)
VALUES (10, date('05-05-2020'),20,'report',400,null,'diagnose',25.5,300, true);

INSERT INTO  examination(id, beg_date, duration, report, patient_id, appointment_id, diagnose, price, derma_id, finished)
VALUES (20, date('06-06-2020'),20,'report',400,null,'diagnose',25.5,300, true);

INSERT INTO  examination(id, beg_date, duration, report, patient_id, appointment_id, diagnose, price, derma_id, finished)
VALUES (30, date('07-08-2020'),20,'report',400,null,'diagnose',25.5,300, true);

INSERT INTO  examination(id, beg_date, duration, report, patient_id, appointment_id, diagnose, price, derma_id, finished)
VALUES (40, date('08-07-2019'),20,'report',400,null,'diagnose',25.5,300, true);

INSERT INTO  examination(id, beg_date, duration, report, patient_id, appointment_id, diagnose, price, derma_id, finished)
VALUES (41, date('08-07-2019'),20,'report',null,null,'diagnose',25.5,300, false);

INSERT INTO  examination(id, beg_date, duration, report, patient_id, appointment_id, diagnose, price, derma_id, finished)
VALUES (42, date('08-07-2019'),20,'report',null,null,'diagnose',25.5,300, false);

-----------------------
INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,10,date('05-05-2020'));

INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,20,date('06-06-2020'));

INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,30,date('07-08-2020'));

INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,40,date('08-07-2019'));

INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200, 41, now());

----------------------------------------

INSERT INTO appointment_drug(appointment_id, drug_id) VALUES (10, 200);
INSERT INTO appointment_drug(appointment_id, drug_id) VALUES (10, 300);
INSERT INTO appointment_drug(appointment_id, drug_id) VALUES (10, 400);
INSERT INTO appointment_drug(appointment_id, drug_id) VALUES (20, 200);
INSERT INTO appointment_drug(appointment_id, drug_id) VALUES (30, 500);
INSERT INTO appointment_drug(appointment_id, drug_id) VALUES (40, 500);
INSERT INTO appointment_drug(appointment_id, drug_id) VALUES (40, 300);

------------------------------------------------------------------

INSERT INTO consultation(id, beg_date, duration, finished, report, patient_id, appointment_id, pharmacist_id)
VALUES (200,now(),25,false,'report',400,null,500);

INSERT INTO consultation(id, beg_date, duration, finished, report, patient_id, appointment_id, pharmacist_id)
VALUES (201,now(),25,true,'report',400,null,500);

INSERT INTO consultation(id, beg_date, duration, finished, report, patient_id, appointment_id, pharmacist_id)
VALUES (202,now(),25,true,'report',400,null,500);

INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,200,now());
INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,201,now());
INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,202,now());