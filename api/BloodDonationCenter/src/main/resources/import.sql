INSERT INTO public.address (id, city, country, number, street) VALUES (2, 'Novi Sad', 'Srbija', '1', 'Ulica');
INSERT INTO public.address (id, city, country, number, street) VALUES (4, 'Novi Sad', 'Srbija', '1', 'ulica1');
INSERT INTO public.address (id, city, country, number, street) VALUES (6, 'Doboj', 'Republika Srpska', '2', 'UlicaNeka');
INSERT INTO public.address (id, city, country, number, street) VALUES (23, 'Prnjavor', 'Republika Srpska', '1', 'ulica_jaka');



INSERT INTO public.survey_questions (id, question) VALUES (2, 'Have you ever been rejected as a blood donor?');
INSERT INTO public.survey_questions (id, question) VALUES (1, 'Have you ever gave blood or blood components?');
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

INSERT INTO public.centers (id, average_grade, description, name, address_id) VALUES (3, 3, 'Ovo je centar', 'Novosadski', 4);
INSERT INTO public.centers (id, average_grade, description, name, address_id) VALUES (5, 5, 'Neka deskripcija za dobojski', 'Dobojski', 6);


INSERT INTO public.users (id, email, name, password, profession, role, school, sex, surname, uid, address_id) VALUES (1, 'stjepanovic@gmail.com', 'Srdjan', '12345678', 'Profesija', 0, 'Skola', 0, 'Stjepanovic', '1234787898789', 2);
INSERT INTO public.users (id, email, name, password, profession, role, school, sex, surname, uid, address_id) VALUES (22, 'ognjen@gmail.com', 'Ognjen', '12345678', 'Majstor', 0, 'FTN', 0, 'Svraka', '123123123', 23);


INSERT INTO public.workers (id, center_id, user_id) VALUES (21, 5, 22);

INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (7, null, '16:00:00', '12:00:00', 3);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (8, null, '16:00:00', '12:00:00', 3);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (9, null, '16:00:00', '12:00:00', 3);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (10, null, '16:00:00', '12:00:00', 3);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (11, null, '16:00:00', '12:00:00', 3);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (12, null, '16:00:00', '12:00:00', 3);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (13, null, '16:00:00', '12:00:00', 3);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (14, null, '16:00:00', '12:00:00', 5);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (15, null, '16:00:00', '12:00:00', 5);
INSERT INTO public.work_time (id, day, end_time, start_time, center_id) VALUES (16, null, '16:00:00', '12:00:00', 5);

