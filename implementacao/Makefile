.PHONY: s db test

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
	@echo "${GREEN}s       artisan serve${RESET}"
	@echo "${GREEN}db      run migrations and seeders${RESET}"
	@echo "${GREEN}test    run tests${RESET}"

s:
	@php -S localhost:8000 -t public

db:
	@php artisan migrate:refresh
	@php artisan db:seed

test:
	@php ./vendor/bin/phpunit --stop-on-defect --stop-on-error --no-coverage --testdox -v
