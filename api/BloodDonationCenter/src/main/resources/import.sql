INSERT INTO public.address (id, city, country, number, street) VALUES (1, 'Novi Sad', 'Srbija', '1', 'Ulica');
INSERT INTO public.address (id, city, country, number, street) VALUES (2, 'Novi Sad', 'Srbija', '1', 'ulica1');
INSERT INTO public.address (id, city, country, number, street) VALUES (3, 'Doboj', 'Republika Srpska', '2', 'UlicaNeka');
INSERT INTO public.address (id, city, country, number, street) VALUES (4, 'Prnjavor', 'Republika Srpska', '1', 'ulica_jaka');



INSERT INTO public.survey_questions (id, question) VALUES (1, 'Have you ever been rejected as a blood donor?');
INSERT INTO public.survey_questions (id, question) VALUES (2, 'Have you ever gave blood or blood components?');
INSERT INTO public.survey_questions (id, question) VALUES (3, 'Do you feel rested at the moment?');
INSERT INTO public.survey_questions (id, question) VALUES (4, 'Are you interested in dangerous sports?');
INSERT INTO public.survey_questions (id, question) VALUES (5, 'Do you use some medics at daily basis?');
INSERT INTO public.survey_questions (id, question) VALUES (6, 'Do you use Aspirin?');
INSERT INTO public.survey_questions (id, question) VALUES (7, 'Did you pulled out tooth in last 7 days?');
INSERT INTO public.survey_questions (id, question) VALUES (8, 'Did you have flu in last 7 days?');
INSERT INTO public.survey_questions (id, question) VALUES (9, 'Have you been vaccinated in last 6 months?');
INSERT INTO public.survey_questions (id, question) VALUES (10, 'Did you lost weight in last 6 months?');
INSERT INTO public.survey_questions (id, question) VALUES (11, 'Do you suffer from epilepsy?');
INSERT INTO public.survey_questions (id, question) VALUES (12, 'Do you suffer from chronic illnesses?');
INSERT INTO public.survey_questions (id, question) VALUES (13, 'Are you having some allergies?');
INSERT INTO public.survey_questions (id, question) VALUES (14, 'Do you have tattoo or piercing?');
INSERT INTO public.survey_questions (id, question) VALUES (15, 'Did you have sex with person who has HIV in last 6 months?');

INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (1, 'true', 1);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (2, 'false', 1);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (3, 'true', 2);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (4, 'false', 2);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (5, 'true', 3);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (6, 'false', 3);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (7, 'true', 4);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (8, 'false', 4);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (9, 'true', 5);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (10, 'false', 5);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (11, 'true', 6);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (12, 'false', 6);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (13, 'true', 7);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (14, 'false', 7);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (15, 'true', 8);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (16, 'false', 8);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (17, 'true', 9);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (18, 'false', 9);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (19, 'true', 10);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (20, 'false', 10);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (21, 'true', 11);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (22, 'false', 11);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (23, 'true', 12);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (24, 'false', 12);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (25, 'true', 13);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (26, 'false', 13);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (27, 'true', 14);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (28, 'false', 14);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (29, 'true', 15);
INSERT INTO public.survey_possible_answers (id, answer, question_id) VALUES (30, 'false', 15);

INSERT INTO public.centers (id, average_grade, description, name, address_id) VALUES (1, 3, 'Ovo je centar', 'Novosadski', 2);
INSERT INTO public.centers (id, average_grade, description, name, address_id) VALUES (2, 5, 'Neka deskripcija za dobojski', 'Dobojski', 3);


INSERT INTO public.users (id, email, name, password, profession, role, school, sex, surname, uid, address_id, activated) VALUES (1, 'stjepanovic@gmail.com', 'Srdjan', '$2a$10$4Xqed/OjxhMp3XZARYlN9ue7eGwyqqOP1vzPdV2gcXqIAkJcIFZ1u', 'Profesija', 0, 'Skola', 0, 'Stjepanovic', '1234787898789', 1, true);
INSERT INTO public.users (id, email, name, password, profession, role, school, sex, surname, uid, address_id, activated) VALUES (2, 'ognjen@gmail.com', 'Ognjen', '$2a$10$4Xqed/OjxhMp3XZARYlN9ue7eGwyqqOP1vzPdV2gcXqIAkJcIFZ1u', 'Majstor', 0, 'FTN', 0, 'Svraka', '123123123', 4, false);
INSERT INTO public.users (id, email, name, password, profession, role, school, sex, surname, uid, address_id, activated) VALUES (3, 'david@gmail.com', 'David', '$2a$10$4Xqed/OjxhMp3XZARYlN9ue7eGwyqqOP1vzPdV2gcXqIAkJcIFZ1u', 'Majstor', 1, 'FTN', 0, 'Maka', '123123123', 4, true);
INSERT INTO public.users (id, email, name, password, profession, role, school, sex, surname, uid, address_id, activated) VALUES (4, 'admin@gmail.com', 'Admin', '$2a$10$4Xqed/OjxhMp3XZARYlN9ue7eGwyqqOP1vzPdV2gcXqIAkJcIFZ1u', 'Majstor', 2, 'FTN', 0, 'Maka', '123123123', 4, true);


INSERT INTO public.workers (id, center_id, user_id) VALUES (1, 2, 2);
INSERT INTO public.workers (id, center_id, user_id) VALUES (2, 1, 3);

INSERT INTO public.system_admins(id, first_login, user_id) VALUES (1, false, 4)

INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (1, 0, '16:00:00', '12:00:00', 1);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (2, 1, '16:00:00', '12:00:00', 1);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (3, 2, '16:00:00', '12:00:00', 1);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (4, 3, '16:00:00', '12:00:00', 1);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (5, 4, '16:00:00', '12:00:00', 1);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (6, 5, '16:00:00', '12:00:00', 1);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (7, 6, '16:00:00', '12:00:00', 1);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (8, 0, '16:00:00', '12:00:00', 2);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (9, 1, '16:00:00', '12:00:00', 2);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (10, 2, '16:00:00', '12:00:00', 2);


INSERT INTO public.appointments (id, duration, start_time, state, center_id, user_id, end_time) VALUES (4, 30, '2022-01-21 11:20:00.000000', 2, 1, null, '2022-01-21 11:50:00.000000');
INSERT INTO public.appointments (id, duration, start_time, state, center_id, user_id, end_time) VALUES (1, 30, '2023-01-21 12:20:00.000000', 0, 2, null, '2023-01-21 12:50:00.000000');
INSERT INTO public.appointments (id, duration, start_time, state, center_id, user_id, end_time) VALUES (2, 30, '2023-01-21 12:20:00.000000', 0, 1, null, '2023-01-21 12:50:00.000000');
INSERT INTO public.appointments (id, duration, start_time, state, center_id, user_id, end_time) VALUES (3, 30, '2023-01-21 12:20:00.000000', 0, 1, null, '2023-01-21 12:50:00.000000');
INSERT INTO public.appointments (id, duration, start_time, state, center_id, user_id, end_time) VALUES (5, 30, '2023-01-22 15:20:20.000000', 1, 1, 1, '2023-01-21 15:50:00.000000');

