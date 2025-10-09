SHELL := /bin/sh

# Parametri
REGISTRY ?= luigiciriello7
TAG ?= v1
COMPOSE_FILE ?= docker-compose/default/docker-compose.yml

# Scorciatoie percorsi
CATALOG_DIR := catalog
CUSTOMERS_DIR := customers
ORDERS_DIR := orders

# Target di default: build immagini -> up
.PHONY: all
all: images up

# ===== Build Java (opzionale, separato) =====
.PHONY: build build-skip-tests
build:
	@echo ">> Build maven"
	cd $(CATALOG_DIR) && mvn -q clean package
	cd $(CUSTOMERS_DIR) && mvn -q clean package
	cd $(ORDERS_DIR) && mvn -q clean package

build-skip-tests:
	@echo ">> Build Maven Without tests"
	cd $(CATALOG_DIR) && mvn -q clean package -DskipTests
	cd $(CUSTOMERS_DIR) && mvn -q clean package -DskipTests
	cd $(ORDERS_DIR) && mvn -q clean package -DskipTests

# ===== Costruzione immagini con Jib =====
.PHONY: images image-catalog image-customers image-orders
images: image-catalog image-customers image-orders
	@echo ">> Built all images"

# Nota: parallelizza con `make -j images` o `make -j all`
image-catalog:
	@echo ">> Starting catalog image build"
	cd $(CATALOG_DIR) && mvn -q compile jib:dockerBuild -Dimage=$(REGISTRY)/catalog:$(TAG)

image-customers:
	@echo ">> Starting customers image build"
	cd $(CUSTOMERS_DIR) && mvn -q compile jib:dockerBuild -Dimage=$(REGISTRY)/customers:$(TAG)

image-orders:
	@echo ">> Starting orders image build"
	cd $(ORDERS_DIR) && mvn -q compile jib:dockerBuild -Dimage=$(REGISTRY)/orders:$(TAG)

# ===== Compose =====
.PHONY: up
up: images
	@echo ">> docker compose up"
	docker compose -f $(COMPOSE_FILE) up -d --remove-orphans

.PHONY: down
down:
	@echo ">> docker compose down"
	docker compose -f $(COMPOSE_FILE) down

.PHONY: logs
logs:
	docker compose -f $(COMPOSE_FILE) logs -f

# ===== Utility =====
.PHONY: clean
clean:
	@echo ">> Clean Maven target"
	cd $(CATALOG_DIR) && mvn -q clean
	cd $(CUSTOMERS_DIR) && mvn -q clean
	cd $(ORDERS_DIR) && mvn -q clean

.PHONY: rebuild
rebuild: clean all

.PHONY: push
push:
	docker image push $(REGISTRY)/catalog:$(TAG)
	docker image push $(REGISTRY)/customers:$(TAG)
	docker image push $(REGISTRY)/orders:$(TAG)
