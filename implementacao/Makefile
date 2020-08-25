.PHONY: s db test install deploy

BLACK        := $(shell tput -Txterm setaf 0)
RED          := $(shell tput -Txterm setaf 1)
GREEN        := $(shell tput -Txterm setaf 2)
YELLOW       := $(shell tput -Txterm setaf 3)
LIGHTPURPLE  := $(shell tput -Txterm setaf 4)
PURPLE       := $(shell tput -Txterm setaf 5)
BLUE         := $(shell tput -Txterm setaf 6)
WHITE        := $(shell tput -Txterm setaf 7)
RESET        := $(shell tput -Txterm sgr0)

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
	@echo "${GREEN}s        artisan serve${RESET}"
	@echo "${GREEN}db       run migrations and seeders${RESET}"
	@echo "${GREEN}test     run tests${RESET}"
	@echo "${GREEN}install  install dependencies${RESET}"
	@echo "----"
	@echo "${BLUE}deploy   deploy app in docker${RESET}"
	@echo "${BLUE}down     destroy containers${RESET}"

s:
	@php -S localhost:8000 -t public

db:
	@php artisan migrate:refresh
	@php artisan db:seed

test:
	@php ./vendor/bin/phpunit --stop-on-defect --stop-on-error --no-coverage --testdox -v

install:
	@cp .env.example .env
	@composer install

deploy: install
	@docker-compose up -d
	@docker exec -t pulse_app php artisan migrate:refresh --no-ansi
	@docker exec -t pulse_app php artisan db:seed --no-ansi
	@docker exec -t pulse_app chmod -R 777 ./storage
	@docker exec -t pulse_app chmod -R 777 /tmp
	@docker exec -t pulse_app composer dump --no-ansi

down:
	@docker-compose down
