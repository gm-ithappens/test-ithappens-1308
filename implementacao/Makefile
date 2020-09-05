.PHONY: s db test install deploy down

help:
	@echo
	@echo ".   ,     ,             .      "
	@echo "|\ /|     |        ,- o |      "
	@echo "| V | ,-: | , ,-.  |  . | ,-.  "
	@echo "|   | | | |<  |-'  |- | | |-'  "
	@echo "'   ' '-' ' \` '-'  |  ' ' '-' "
	@echo "                  -'           "
	@echo "Available target rules"
	@echo
	@echo "s        artisan serve"
	@echo "db       run migrations and seeders"
	@echo "test     run tests"
	@echo "install  install dependencies"
	@echo "----"
	@echo "deploy   deploy app in docker"
	@echo "down     destroy containers"

s:
	@php -S localhost:8000 -t public

db:
	@php artisan migrate:refresh --no-ansi
	@php artisan db:seed --no-ansi

test:
	@php ./vendor/bin/phpunit --stop-on-defect --stop-on-error --no-coverage --testdox -v

install:
	@cp .env.example .env
	@composer install --no-ansi

permissions:
	@chmod -R 777 ./storage
	@chmod -R 777 /tmp

deploy:
	@docker-compose up -d
	@docker exec -t pulse_app apk -q add make
	@docker exec -t pulse_app make install
	@docker exec -t pulse_app make db
	@docker exec -t pulse_app make permissions

down:
	@docker-compose down
