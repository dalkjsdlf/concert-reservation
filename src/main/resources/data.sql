INSERT INTO concert(CONCERT_NAME, CONCERT_DESC, ARTIST, START_DATE, END_DATE) VALUES('박효신 콘서트', '박효신의 서버 페스티벌', '박효신', '2024-12-24 00:00:00','2024-12-25 00:00:00');

INSERT INTO schedule(concert_id, perform_date) values(1,'2024-12-24 17:00:00');

INSERT INTO seat(seat_no, schedule_id, seat_grade, price, reserve_id) values(1,1,'VIP',240000,-1);
INSERT INTO seat(seat_no, schedule_id, seat_grade, price, reserve_id) values(2,1,'R',120000,-1);
INSERT INTO seat(seat_no, schedule_id, seat_grade, price, reserve_id) values(3,1,'R',120000,-1);