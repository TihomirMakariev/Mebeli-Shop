-- some test users

INSERT INTO user_roles (id, user_role)
values
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users (id, email, first_name, image_url, is_active, last_name, password)
VALUES
    (1,	'tihomir@abv.bg', 'Tihomir', null, 1, 'Emilov',	'$2a$10$xEK5r/ldBRXRnEb3k3YfZ.fzFDc.vd0bPtA2q80Dow0NvrD.TnhFO'),
	(2,	'lachezar.balev@gmail.com',	'Lachezar', null,	1,	'Balev', '$2a$10$xXW.ogpQd9TRrMJBh0nwM./Vy1nG1g4SycaT30FizW1ckqH9B8HZa'),
	(3,	'admin@example.com',	'Admin', null,	1,	'Adminov', '$2a$10$lcOVhhcg7Y.iLw7nT5vIm.qcZBhO4bXa5Ne7chyNdVGcuoLGW96UK'),
	(4,	'user@example.com',	'User', null,	1,	'Userov', '$2a$10$6ECn/3auA7VTyZJcHVEnhOVt7oOlKH46Uxc2Dum12xvzcVKuwkU.C');

INSERT INTO users_user_roles (user_entity_id, user_roles_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 1),
    (3, 2),
    (4, 2);

INSERT INTO living_rooms (id, category, color, image_url, name, price, author_id)
VALUES
    (1,	'LIVING_ROOM', 'GREEN',	'https://mebelmag.bg/uploads/product-image/image/original/mebelmag-bg-komplekt-divani-za-dnevna-katalina-kafyav-s-bezhovo.jpg',	'Кухненски диван 1', 356.24, 2),
    (2,	'LIVING_ROOM', 'GREEN',	'https://www.mebeli1.online/image/cache/catalog/m/meka-mebel/divani/idea/holov-ugul-idea-siv-1000x750-product_popup.jpg', 'Кухненски диван 2', 5432.49, 1),
    (3,	'LIVING_ROOM', 'ORANGE', 'https://mondomebeli.com/tools-box-1200-630-00039441/uglovi-divani-cvqt-bejav-idea-onlain-mebeli-mondo.jpg', 'Кухненски диван 3', 354.99, 1),
    (4,	'LIVING_ROOM', 'RED', 'https://www.yavor.bg/cache/images/thumbnails/14056.jpg', 'Кухненски диван 4', 9854.99, 2),
    (5,	'LIVING_ROOM', 'ORANGE', 'https://mebelilenistyle.com/image/cache/catalog/Cam_02-685x380w.jpg', 'Кухненски диван 5', 5487.99, 1),
    (6,	'LIVING_ROOM', 'GREEN', 'https://mebelilenistyle.com/image/cache/catalog/267-685x380w.jpg', 'Кухненски диван 6', 1258.99, 2),
    (7,	'LIVING_ROOM', 'RED', 'https://bestmebel.bg/image/cache/catalog/238-600x440h.jpg', 'Кухненски диван 7', 568.99, 1),
    (8,	'LIVING_ROOM', 'ORANGE', 'https://diskret-bg.com/resource/filestored/image/webp/6474a036c595e.webp', 'Кухненски диван 8', 1582.99, 2),
    (9,	'LIVING_ROOM', 'GREEN', 'https://mebelilenistyle.com/image/cache/catalog/276-685x380w.jpg', 'Кухненски диван 9', 5041.99, 1),
    (10, 'LIVING_ROOM', 'RED', 'https://mebelmag.bg/uploads/product-image/image/original/mebelmag-bg-yglov-divan-valeriq-desen-tymno-siv-sys-sivo.jpg', 'Кухненски диван 10', 3025.99, 2),
    (11, 'LIVING_ROOM', 'NAVY', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnaWn4vAZ2IPYCzU2_f6NytJq5gEIPEmZzUsqlO3KomeIMXcJPI6VmQuteUkJjol2LK9U&usqp=CAU', 'Кухненски диван 11', 2410.99, 1);

INSERT INTO kitchens(id, category, color, image_url, name, price, author_id)
VALUES
    (1, 'KITCHEN', 'PURPLE', 'https://piponkov.eu/wp-content/uploads/2023/03/luksozna-kuhnia-plovdiv-781.jpg', 'Кухня 1', 6213.54, 1),
    (2, 'KITCHEN', 'PURPLE', 'https://www.bitak.net/uf/adata/1000_577564_55f1c9d0b5d940d45c4fb81289899a4d.jpeg', 'Кухня 1', 5432.54, 2),
    (3, 'KITCHEN', 'PURPLE', 'https://www.solvent-bg.com/resources/products/img_518_651d9680ac2a0.jpg', 'Кухня 1', 356.54, 3),
    (4, 'KITCHEN', 'PURPLE', 'https://mebeliestel.com/wp-content/uploads/2018/07/300623-11.jpg', 'Кухня 1', 1258.54, 2),
    (5, 'KITCHEN', 'PURPLE', 'https://diamir-mebel.ru/wp-content/uploads/2022/08/1.jpeg', 'Кухня 1', 9854.54, 1),
    (6, 'KITCHEN', 'PURPLE', 'https://www.mebeliarena.bg/image/cachewebp/0/catalog/gold-apolo/kuhni/kuhnq-marea-sonoma-1-480x331.webp', 'Кухня 1', 568.54, 3),
    (7, 'KITCHEN', 'PURPLE', 'https://piponkov.eu/wp-content/uploads/2016/03/kuhnia-glants-441.jpg', 'Кухня 1', 901.54, 1),
    (8, 'KITCHEN', 'PURPLE', 'https://piponkov.eu/wp-content/uploads/2022/11/luksozni-kuhni-751.jpg', 'Кухня 1', 812.54, 2);