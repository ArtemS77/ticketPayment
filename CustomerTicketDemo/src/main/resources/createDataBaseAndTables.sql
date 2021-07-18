
CREATE DATABASE IF NOT EXISTS ticket_payment_db ;

USE ticket_payment_db;

CREATE TABLE IF NOT EXISTS ticket_payment_request_table (
	request_id BIGINT PRIMARY KEY,
	route_number BIGINT UNIQUE NOT NULL,
	client_id BIGINT NOT NULL,
	request_date TIMESTAMP,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)
;

CREATE TABLE IF NOT EXISTS id_status_table (
	request_id BIGINT PRIMARY KEY,
	execution_status ENUM('Temporary Redirect', 'OK', 'Not Found') DEFAULT 'Temporary Redirect',
	request_status_code ENUM('307', '200', '404') DEFAULT '307',
	FOREIGN KEY (request_id) REFERENCES ticket_payment_request_table(request_id))
;



