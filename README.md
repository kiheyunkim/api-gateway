#Api-Gateway

api-gateway
* 동기형 웹에 최적화된 zuul

```yaml
# FallbackProvider를 사용하기 위해 등록.
zuul:
  routes:
    user-service:
      path: /user-service/**
```