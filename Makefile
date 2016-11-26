all: rm build up

rm:
	docker-compose rm -f

build:
	docker-compose build

up:
	docker-compose up