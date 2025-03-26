.PHONY: run up wait generate-docs generate-openapi

# Docker Compose로 모든 서비스를 백그라운드에서 실행하고 OpenAPI 문서를 생성
run: up wait generate-docs
	@echo "OpenAPI documentation generated successfully"

# Docker 이미지를 빌드하고 Docker Compose로 서비스 시작
up:
	@echo "Running ./gradlew docker"
	@./gradlew docker
	@echo "Running docker-compose up -d"
	@docker-compose up -d

# 서비스가 준비될 때까지 대기
wait:
	@echo "Waiting for services to initialize..."
	#@sleep 30  # 필요에 따라 시간 조정

# OpenAPI 문서 생성
generate-docs:
	@echo "SEO-BETTER-PAY JSON DOC ... "
	#@./gradlew clean generateOpenApiDocs
