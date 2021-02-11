
-- alter table calendar_appointments add appointment_date timestamp;

insert into public.address(id, street, street_number, city, country) values (200,'street', 15,'city','country');

INSERT INTO public.calendar(id) VALUES (200);
INSERT INTO public.calendar(id) VALUES (300);

--pharmacy

INSERT INTO public.pharmacy(
    id, description, name, address_id, calendar_id)
VALUES (200, 'description', 'ISA Pharmacy', 200, 200);

INSERT INTO public.pharmacy(
    id, description, name, address_id, calendar_id)
VALUES (300, 'description', 'name', 200, 300);

INSERT INTO pharmacy_ratings(pharmacy_id, ratings) VALUES (200, 4.5);
INSERT INTO pharmacy_ratings(pharmacy_id, ratings) VALUES (200, 5.0);
INSERT INTO pharmacy_ratings(pharmacy_id, ratings) VALUES (200, 3);
INSERT INTO pharmacy_ratings(pharmacy_id, ratings) VALUES (300, 3);
--drugs

INSERT INTO drug_spec(id,composition,recom_consum,side_effects)
VALUES(23,'Top','Sve','nista bre');

INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (200, 457887, 'manufacturer', 'name1', false, 0,'type',23);
INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (300, 457887, 'manufacturer', 'name2', false, 1,'type',null);
INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (400, 457887, 'manufacturer', 'name3', false,2,'type',null);
INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (500, 457887, 'manufacturer', 'name4', false, 3,'type',null);

INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (600, 457887, 'manufacturer', 'name5', false, 1,'type',null);
INSERT INTO public.drug(id, code, manufacturer, name, receipt, shape, type, spec_id)
VALUES (601, 457887, 'manufacturer', 'name6', false, 0,'type',null);

INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount, price) VALUES (200,200, 200,10);
INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount, price) VALUES (200,300,10,20);
INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount, price) VALUES (200,400,25,30);
INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount, price) VALUES (200,500,45,40);
INSERT INTO pharmacy_drug(pharmacy_id, drug_id, amount, price) VALUES (300,300,13,50);

INSERT INTO pharmacy_drug(amount, price, drug_id, pharmacy_id) VALUES (0,0.0,601,200);
-- INSERT INTO pharmacy_drug(amount, price, drug_id, pharmacy_id) VALUES (0,0.0,602,200);


--inserting users

INSERT INTO public.dermatologist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (300, 'dermatologistonepharmacyapp@gmail.com', true, 'Haris', null, 'Dzinovic',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4517', 1, 200);

INSERT INTO public.dermatologist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (301, 'nikolartukov@gmail.com', true, 'Toma', null, 'Zdravkovic',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4517', 1, 200);

INSERT INTO public.dermatologist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (302, 'derma2@live.com', true, 'Ljuba', null, 'Alicic',
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
INSERT INTO derma_ratings(pharmacy_id, derma_id, dermatologist_ratings_id) VALUES (200,301,301);

INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (300,200,4.5);
INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (300,200,3.5);
INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (300,200,5.0);
INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (301,200,5.0);
INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (301,200,5.0);


INSERT INTO dermatologist_ratings_ratings(dermatologist_ratings_derma_id, dermatologist_ratings_pharmacy_id, ratings)
VALUES (300,300,5.0);

--patient

INSERT INTO public.patient(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id, category, penalties, points)
VALUES (400, 'nidza@gmail.com', true, 'patient', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 0, 200, null, null, null);
INSERT INTO public.patient(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id, category, penalties, points)
VALUES (401, 'salecovic@hotmail.com', true, 'Nikola', null, 'Artukov',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 0, 200, null, null, null);

INSERT INTO reservation(id, acceptance_date, accepted,patient_id, pharmacy_id)
VALUES (200, now(), true,400, 200);

INSERT INTO reservation(id, acceptance_date, accepted,patient_id, pharmacy_id)
VALUES (201, now(), false,400, 200);

INSERT INTO reservation(id, acceptance_date, accepted,patient_id, pharmacy_id)
VALUES (202, now(), true,400, 200);

INSERT INTO reservation(id, acceptance_date, accepted,patient_id, pharmacy_id)
VALUES (203, '2022-02-10', false,400, 200);

INSERT INTO reservation_drug(reservation_id, drug_id)
VALUES (200, 200);

INSERT INTO reservation_drug(reservation_id, drug_id)
VALUES (201, 300);

INSERT INTO reservation_drug(reservation_id, drug_id)
VALUES (202, 300);

INSERT INTO reservation_drug(reservation_id, drug_id)
VALUES (203, 300);

--pharmacist

INSERT INTO public.pharmacist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id,
                              pharmacy_id,start_hour,hours )
VALUES (500, 'pharmacistonepharmacyapp@gmail.com' ,true, 'Dzej', null, 'Ramadanovski',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 2, 200, 200, now(),8);

INSERT INTO public.pharmacist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id,
    pharmacy_id,start_hour,hours )
VALUES (501, 'pharmacist1@live.com' ,true, 'Aca', null, 'Lukas',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 2, 200, 200, now(),8);

INSERT INTO public.pharmacist(
    id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id,
    pharmacy_id,start_hour,hours )
VALUES (502, 'pharmacist2@live.com' ,true, 'Mira', null, 'Skoric',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '4576', 2, 200, 200, now(),8);

INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (500 , 3.0);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (500 , 2.0);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (500 , 5.0);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (501 , 3.0);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (501 , 5.0);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (501 , 3.5);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (501 , 2.7);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (502 , 2.0);
INSERT INTO pharmacist_ratings(pharmacist_id, ratings) VALUES (502 , 1.0);


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
VALUES (700, 'supplieronepharmacyapp@gmail.com', true, 'system admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '764',4 ,200);

INSERT INTO supplier(id, email, enabled, firstname, last_password_reset_date, lastname, password, phone_number, role, address_id)
VALUES (701, 'suppliertwopharmacyapp@gmail.com', true, 'system admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '764',4 ,200);

INSERT INTO supplier(id, email, enabled, firstname, last_password_reset_date, lastname, password,
                     phone_number, role, address_id)
VALUES (702, 'srdjan_kralj_97@live.com', true, 'system admin', null, 'lastname',
        '$2a$04$wF4uiW.ZCgD3EoPIHpDBAulwKDZ.i9.754dzkw7EtRzIiVcC8NPy6', '764',4 ,200);

INSERT INTO warehouse(id, size, supplier_id) VALUES (100, 500, 700);
INSERT INTO warehouse(id, size, supplier_id) VALUES (101, 500, 701);
INSERT INTO warehouse(id, size, supplier_id) VALUES (102, 500, 702);

INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 200, 100);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 300, 100);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 400, 100);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 500, 100);

INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 200, 101);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 300, 101);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 400, 101);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 500, 101);

INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (150, 200, 102);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 300, 102);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 400, 102);
INSERT INTO warehouse_drug(amount, drug_id, warehouse_id) VALUES (100, 500, 102);

--supply orders-----------------------------------------

INSERT INTO supply_order(id, deadline_date, admin_id)
VALUES (200,date('2021-02-13'),200);
INSERT INTO supply_order(id, deadline_date, admin_id)
VALUES (201,date('2021-03-13'),200);
INSERT INTO supply_order(id, deadline_date, admin_id)
VALUES (202,date('2020-12-31'),200);
INSERT INTO supply_order(id, deadline_date, admin_id)
VALUES (203, date('2021-05-03'),200);
INSERT INTO supply_order(id, deadline_date, admin_id)
VALUES (204,date('2021-06-25'),200);

INSERT INTO supply_order(id, deadline_date, admin_id) VALUES (205,now(),200);




INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (20,200,200);
INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (30,300,200);
INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (100,400,200);

INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (50,400,201);
INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (60, 300, 201);

INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (100,200,202);

INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (65, 300, 203);
INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (75,200,203);
INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (126, 400, 204);
INSERT INTO supply_drug(amount, drug_id, supply_id)
VALUES (12,200,204);

insert into supply_drug(amount, drug_id, supply_id) VALUES (125,200,205);

----with offers-------------------------------
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (date('2021-02-10'),250.0,0,200,700);
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (date('2021-02-09'),250.0,0,200,701);
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (date('2021-04-23'),25.6,0,203,700);
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (date('2021-04-23'),25.6,0,204,701);

----without offers --------------------------
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (null,null,0,201,700);
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (null,null,0,201,701);
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (null,null,0,203,701);
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (null,null,0,204,700);

INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (null,null,0,205,702);
-- INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
-- VALUES (null,null,0,205,700);
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (null, null, 0, 205, 701);

--accepted order---------------------------
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (date('2020-12-30'),300.0,1,202,700);
--denied order----------------------------
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (date('2020-12-31'),300.0,2,202,701);
INSERT INTO supplier_order(delivery_date, price_offer, status, order_id, supplier_id)
VALUES (null, null,2,205,700);

--inserting authority---------------------------

INSERT INTO public.authority(id,name) VALUES (1,'USER');
INSERT INTO public.authority(id, name) VALUES (2,'ADMIN');
INSERT INTO public.authority(id, name) VALUES (3,'PHARMACY_ADMIN');

--inserting user_authority-------------------------
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

INSERT INTO  examination(id, beg_date, duration, report, patient_id, appointment_id, diagnose, price, derma_id, finished)
VALUES (43, date('08-07-2019'),20,'report',401,null,'diagnose',25.5,300, false);

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

INSERT INTO consultation(id, beg_date, duration, finished, report, patient_id, appointment_id, pharmacist_id)
VALUES (203,now(),25,true,'report',401,null,500);

INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,200,now());
INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,201,now());
INSERT INTO calendar_appointments(calendar_id, appointment_id, appointment_date)
VALUES (200,202,now());

--price list--------------------------------------------------------

INSERT INTO price_list(id, end_date, start_date, pharmacy_id, active)
VALUES (200, date('2020-12-31'), date('2020-09-01'),200, false);

INSERT INTO price_list(id, end_date, start_date, pharmacy_id, active)
VALUES (201, date('2021-03-31'), date('2021-01-01'),200, true);

INSERT INTO price_list(id, end_date, start_date, pharmacy_id, active)
VALUES (202, date('2021-03-31'), date('2021-01-01'),300, true);

INSERT INTO pl_drug(price, drug_id, pricelist_id)
VALUES (200,200,200);
INSERT INTO pl_drug(price, drug_id, pricelist_id)
VALUES (125,300,200);
INSERT INTO pl_drug(price, drug_id, pricelist_id)
VALUES (78,400,200);
INSERT INTO pl_drug(price, drug_id, pricelist_id)
VALUES (225,200,201);
INSERT INTO pl_drug(price, drug_id, pricelist_id)
VALUES (25,300,201);
INSERT INTO pl_drug(price, drug_id, pricelist_id)
VALUES (35.8,400,201);
INSERT INTO pl_drug(price, drug_id, pricelist_id)
VALUES (35.8,300,202);


insert into substitute_drugs(original_id, substitute_id) values(200, 600);

insert into allergy(id,patient_id, allergy_id) values(30,400,null);

insert into allergy_drug(allergy_id,drug_id) values(30,200);


insert into erecepit(id,code,issue_date,patient_id,receipt_id) values (1,1,date('2020-09-01'),400,400);
insert into erecepit(id,code,issue_date,patient_id,receipt_id) values (2,2,date('2020-09-01'),401,401);


insert into recepit_drug(recepit_id,drug_id) values (1,200);
insert into recepit_drug(recepit_id,drug_id) values (1,300);
insert into recepit_drug(recepit_id,drug_id) values (1,400);
insert into recepit_drug(recepit_id,drug_id) values (1,500);
insert into recepit_drug(recepit_id,drug_id) values (2,300);



---absence request---------------
INSERT INTO absence_request(id, description, end_date, start_date, status, pharmacy_id, user_id)
VALUES (200,null,date('2021-06-21'),date('2021-06-10'),null,200,300);
INSERT INTO absence_request(id, description, end_date, start_date, status, pharmacy_id, user_id)
VALUES (201,null,date('2021-09-10'),date('2021-08-15'),null,200,301);

INSERT INTO absence_request(id, description, end_date, start_date, status, pharmacy_id, user_id)
VALUES (202,null,date('2021-07-10'),date('2021-06-28'),null,200,500);
INSERT INTO absence_request(id, description, end_date, start_date, status, pharmacy_id, user_id)
VALUES (203,null,date('2021-10-10'),date('2021-09-28'),null,200,501);
INSERT INTO absence_request(id, description, end_date, start_date, status, pharmacy_id, user_id)
VALUES (204,null,date('2021-05-15'),date('2021-04-30'),null,200,502);

----subscribers to pharmacy promotions-----------
INSERT INTO pharmacy_subscriber(pharmacy_id, subscriber_id) VALUES (200,400);
INSERT INTO pharmacy_subscriber(pharmacy_id, subscriber_id) VALUES (200,401);

INSERT INTO complaints(id, description, referred_id, submitter_id, pharmacy_id ,complaint_id)
VALUES (200,'The service was terrible, there was no  magazines to read while I was waiting',null,400,200, null);
INSERT INTO complaints(id, description, referred_id, submitter_id, pharmacy_id ,complaint_id)
VALUES (201, 'He was rude, dirty and cold towards me',300,401,null,null);