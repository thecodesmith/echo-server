VERSION ?= 1.0.0
NAMESPACE = test

docker-build:
	docker build -t docker.jamf.build/echo-server:$(VERSION) .

docker-push:
	docker push docker.jamf.build/echo-server:$(VERSION)

install:
	kubectl apply -n $(NAMESPACE)    \
		-f manifests/deployment.yaml \
		-f manifests/svc.yaml        \
		-f manifests/ingress.yaml

rm:
	kubectl delete ing echo --ignore-not-found
	kubectl delete svc echo --ignore-not-found
	kubectl delete deployment echo --ignore-not-found