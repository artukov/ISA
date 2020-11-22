
insert into public.address(id, street, street_number, city, country) values (200,'street', 15,'city','country');

INSERT INTO public.calendar(id) VALUES (200);

INSERT INTO public.pharmacy(
    id, description, name, address_id, calendar_id)
VALUES (200, 'description', 'name', 200, 200);

INSERT INTO public.pharmacy_admin(
    id, email, firstname, lastname, password, phone_number, address_id, pharmacy_id, pharmacy_admin_id)
VALUES (200, 'email', 'firstname', 'lastname', '1234', '45687', 200, 200, null);

