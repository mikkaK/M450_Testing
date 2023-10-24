insert into users (id, email,first_name,last_name, password)
values ('2ce9437a-b8de-11ed-afa1-0242ac120002', 'admin2@example.com', 'James','Bond', '$2a$12$Erh9KWJf.dx50pIqcG2CouZW3i.Q/35vqpJ.7cD4.Q8RBJ0EWO7j2'),
       ('020a4514-b8de-11ed-afa1-0242ac120002', 'user2@example.com', 'Tyler','Durden', '$2a$12$Erh9KWJf.dx50pIqcG2CouZW3i.Q/35vqpJ.7cD4.Q8RBJ0EWO7j2'),
       ('ac2b5e04-add8-4558-bd61-c7e869a1d872', 'create@example.com', 'Testi','Create', '$2a$12$Erh9KWJf.dx50pIqcG2CouZW3i.Q/35vqpJ.7cD4.Q8RBJ0EWO7j2'),
       ('d0e514f0-9546-4abf-9500-9a63634b28e1', 'delete@example.com', 'Testi','Delete', '$2a$12$Erh9KWJf.dx50pIqcG2CouZW3i.Q/35vqpJ.7cD4.Q8RBJ0EWO7j2')
ON CONFLICT DO NOTHING ;

insert into user_profile(id, address, age, birth_date, profile_picture_url, user_id, created_date, last_modified_date)
values ('16b74f19-d7e4-4724-b446-ae686939a843', 'Admin Address', 12, '2023-03-02 09:03:45.967000', 'http://profile-picture.com', '2ce9437a-b8de-11ed-afa1-0242ac120002', '2023-03-02 09:03:45.967000', '2023-03-02 09:03:45.967000'),
       ('16b74f19-d7e4-4724-b446-ae686939a868', 'User Address', 12, '2023-03-02 09:03:45.967000', 'http://profile-picture.com', '020a4514-b8de-11ed-afa1-0242ac120002', '2023-03-02 09:03:45.967000', '2023-03-02 09:03:45.967000'),
       ('619217ce-bb4c-43e5-9abb-fb54195259e9', 'Delete Address', 12, '2023-03-02 09:03:45.967000', 'http://profile-picture.com', 'd0e514f0-9546-4abf-9500-9a63634b28e1', '2023-03-02 09:03:45.967000', '2023-03-02 09:03:45.967000')
    ON CONFLICT DO NOTHING ;